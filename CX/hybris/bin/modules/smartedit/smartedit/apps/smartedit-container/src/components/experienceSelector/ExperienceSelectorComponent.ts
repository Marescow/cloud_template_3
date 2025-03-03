/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import { Component, EventEmitter, Input, Output } from '@angular/core';

import { take } from 'rxjs/operators';
import {
    dateUtils,
    urlUtils,
    EVENTS,
    GenericEditorAPI,
    IBaseCatalog,
    IBaseCatalogVersion,
    ICatalogService,
    IConfiguration,
    IExperience,
    IExperienceCatalogVersion,
    IExperienceParams,
    IExperienceService,
    IIframeClickDetectionService,
    IPreviewCatalogVersionData,
    IPreviewData,
    ISharedDataService,
    ISite,
    Payload,
    PREVIEW_RESOURCE_URI,
    SeDowngradeComponent,
    SystemEventService,
    TYPES_RESOURCE_URI,
    L10nPipe,
    EXPERIENCE_STORAGE_KEY,
    IUserTrackingService,
    USER_TRACKING_FUNCTIONALITY
} from 'smarteditcommons';
import { IframeManagerService } from '../../services/iframe/IframeManagerService';
import { SiteService } from '../../services/SiteService';

/** @internal */
interface SiteCatalog {
    siteId: string;
    catalogId: string;
    catalogVersion: string;
    productCatalogs: IBaseCatalog[];
    productCatalogVersions: string[];
    language: string;
}

/** @internal */
export interface IExperienceContent extends Payload {
    activeSite: string;
    language: string;
    pageId: string;
    previewCatalog: string;
    productCatalogVersions: string[];
    time: string;
}

/**
 * The experience selector component is responsible for changing the experience including content catalog version, language, time, product catalog version
 * on submitting the generic editor form and this is based off of the current page id.
 *
 * The page id plays essential role here meaning that the experience params along with the current page id are sent to backend to create a preview ticket when submitting the experience selector form.
 * The preview ticket response will have the page id if that page id is available in the selected catalog (or parent catalog). If not, page id will be set to null.
 */
/** @internal */
@SeDowngradeComponent()
@Component({
    selector: 'se-experience-selector',
    templateUrl: './ExperienceSelectorComponent.html',
    providers: [L10nPipe]
})
export class ExperienceSelectorComponent {
    @Input() experience: IExperience;
    @Input() dropdownStatus: { isOpen: boolean };
    @Input() resetExperienceSelector?: () => void;

    @Output() resetExperienceSelectorChange: EventEmitter<() => void> = new EventEmitter();

    public smarteditComponentType: string;
    public structureApi: string;
    public contentApi: string;
    public content: IExperienceContent;
    public modalHeaderTitle = 'se.experience.selector.header';
    public smarteditComponentId: string;
    public isReady: boolean;

    private siteCatalogs: SiteCatalog;
    private unRegCloseExperienceFn: () => void;
    private unRegFn: () => void;

    constructor(
        private systemEventService: SystemEventService,
        private siteService: SiteService,
        private sharedDataService: ISharedDataService,
        private iframeClickDetectionService: IIframeClickDetectionService,
        private iframeManagerService: IframeManagerService,
        private experienceService: IExperienceService,
        private catalogService: ICatalogService,
        private l10nPipe: L10nPipe,
        private userTrackingService: IUserTrackingService
    ) {
        this.siteCatalogs = {} as SiteCatalog;
    }

    ngOnInit(): void {
        setTimeout(() => {
            // To prevent "ExpressionChangeAfterItHasBeenChecked" error, because we change the "reset" value.
            // It's required for backward compatibility.
            this.resetExperienceSelectorChange.emit(() => this.resetExperienceSelectorFn());
        });

        this.unRegCloseExperienceFn = this.iframeClickDetectionService.registerCallback(
            'closeExperienceSelector',
            () => {
                if (this.dropdownStatus && this.dropdownStatus.isOpen) {
                    this.dropdownStatus.isOpen = false;
                }
            }
        );

        this.unRegFn = this.systemEventService.subscribe('OVERLAY_DISABLED', () => {
            if (this.dropdownStatus && this.dropdownStatus.isOpen) {
                this.dropdownStatus.isOpen = false;
            }
        });
    }

    ngOnDestroy(): void {
        if (this.unRegFn) {
            this.unRegFn();
        }
        if (this.unRegCloseExperienceFn) {
            this.unRegCloseExperienceFn();
        }
    }

    async preparePayload(experienceContent: IExperienceContent): Promise<IPreviewData> {
        [
            this.siteCatalogs.siteId,
            this.siteCatalogs.catalogId,
            this.siteCatalogs.catalogVersion
        ] = experienceContent.previewCatalog.split('|');

        const productCatalogs: IBaseCatalog[] = await this.catalogService.getProductCatalogsForSite(
            this.siteCatalogs.siteId
        );
        const { domain }: IConfiguration = (await this.sharedDataService.get(
            'configuration'
        )) as IConfiguration;
        const { previewUrl, uid: siteId }: ISite = await this.siteService.getSiteById(
            this.siteCatalogs.siteId
        );
        const { language, time, pageId, productCatalogVersions } = experienceContent;

        this.siteCatalogs.productCatalogs = productCatalogs;
        this.siteCatalogs.productCatalogVersions = productCatalogVersions;
        this.userTrackingService.trackingUserAction(
            USER_TRACKING_FUNCTIONALITY.TOOL_BAR,
            'update Experience'
        );

        return {
            ...experienceContent,
            resourcePath: urlUtils.getAbsoluteURL(domain, previewUrl),
            catalogVersions: [
                ...this._getProductCatalogsByUuids(productCatalogVersions),
                {
                    catalog: this.siteCatalogs.catalogId,
                    catalogVersion: this.siteCatalogs.catalogVersion
                }
            ],
            siteId,
            language,
            time,
            pageId
        };
    }

    /**
     * updateCallback method is called on the onSuccess callback when submitting the experience selector generic editor form.
     * As soon as preview ticket is created with the new experience based off of the form, updateCallback is called to do the below things:
     * build and set experience, publish EVENTS.EXPERIENCE_UPDATE event and load the storefront iframe with new preview url and preview ticket id.
     */
    async updateCallback(payload: IExperienceContent, response: IPreviewData): Promise<void> {
        // Then perform the actual update.;
        const { siteId, catalogId, catalogVersion, productCatalogVersions } = this.siteCatalogs;
        const { time } = payload;
        const { pageId, language, ticketId } = response;

        this.smarteditComponentId = null; // to force a permanent POST
        this.dropdownStatus.isOpen = false;

        const experienceParams: IExperienceParams = {
            ...response,
            siteId,
            catalogId,
            catalogVersion,
            productCatalogVersions,
            time: dateUtils.formatDateAsUtc(time),
            pageId,
            language
        };

        const experience: IExperience = await (this.experienceService as any).buildAndSetExperience(
            experienceParams
        );

        await this.sharedDataService.set(EXPERIENCE_STORAGE_KEY, experience);

        this.systemEventService.publishAsync(EVENTS.EXPERIENCE_UPDATE);

        this.iframeManagerService.loadPreview(experience.siteDescriptor.previewUrl, ticketId);
    }

    public getApi($api: GenericEditorAPI): void {
        $api.setPreparePayload(this.preparePayload.bind(this));
        $api.setUpdateCallback(this.updateCallback.bind(this));
        $api.setAlwaysShowSubmit(true);
        $api.setAlwaysShowReset(true);
        $api.setSubmitButtonText('se.componentform.actions.apply');
        $api.setCancelButtonText('se.componentform.actions.cancel');
        $api.setOnReset(() => {
            this.dropdownStatus.isOpen = false;
            return this.dropdownStatus.isOpen;
        });
    }

    private _getProductCatalogsByUuids(versionUuids: string[]): IPreviewCatalogVersionData[] {
        return this.siteCatalogs.productCatalogs.map(({ versions, catalogId }) => ({
            catalogVersion: versions.find(
                ({ uuid }: IBaseCatalogVersion) => versionUuids.indexOf(uuid) > -1
            ).version,
            catalog: catalogId
        }));
    }

    private async resetExperienceSelectorFn(): Promise<void> {
        const experience: IExperience = (await this.sharedDataService.get(
            EXPERIENCE_STORAGE_KEY
        )) as IExperience;
        const configuration: IConfiguration = (await this.sharedDataService.get(
            'configuration'
        )) as IConfiguration;

        this.smarteditComponentType = 'PreviewData';
        this.smarteditComponentId = null;
        this.structureApi = TYPES_RESOURCE_URI + '?code=:smarteditComponentType&mode=DEFAULT';
        this.contentApi = (configuration && configuration.previewTicketURI) || PREVIEW_RESOURCE_URI;

        const activeSiteTranslated = await this.l10nPipe
            .transform(experience.siteDescriptor.name)
            .pipe(take(1))
            .toPromise();

        this.content = {
            ...experience,
            activeSite: activeSiteTranslated,
            time: experience.time,
            pageId: experience.pageId,
            productCatalogVersions: experience.productCatalogVersions.map(
                (productCatalogVersion: IExperienceCatalogVersion) => productCatalogVersion.uuid
            ),
            language: experience.languageDescriptor.isocode,
            previewCatalog: `${experience.siteDescriptor.uid}|${experience.catalogDescriptor.catalogId}|${experience.catalogDescriptor.catalogVersion}`
        } as IExperienceContent;

        if (!this.isReady) {
            this.isReady = true;
        }
    }
}
