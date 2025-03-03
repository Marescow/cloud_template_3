/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import { ICommonSlot } from '../../entities/commonSlot.entity';
import { INavigationNode } from '../../entities/navigations/navigationNode.entity';
import { IPageRestriction } from '../../entities/pages/pageRestriction.entity';
const HEAD_LINE = 'The Headline';
const CONTENT_TO_EDIT = 'the content to edit';
const TIME_RESTRICTION = 'Some Time restriction A';
const SOME_DESCRIPTION = 'some description';
const APPRAREL_UK_STAGED = 'apparel-ukContentCatalog/Staged';
const IHATE_PANDAS = 'i-hate-pandas';
const APPAREL_UK_ONLINE = 'apparel-ukContentCatalog/Online';
const ADD_EDIT_ADDRESS = 'add-edit-address';
const PRIMARY_CONTENT_PAGE = 'Primary Content Page';
const VARIATION_CONTENT_PAGE = 'Variation Content Page';
const APPAREL_ONLINE = 'apparelContentCatalog/Online';
const CMS_NAVIGATION_NODE_ROOT = 'CMSNavigationNode-root';
const CMS_NAVIGATION_NODE1 = 'CMSNavigationNode-1';
const CMS_NAVIGATION_NODE2 = 'CMSNavigationNode-2';
const CMS_NAVIGATION_NODE4 = 'CMSNavigationNode-4';
const CMS_NAVIGATION_NODE8 = 'CMSNavigationNode-8';
export const componentItems: (ICommonSlot | IPageRestriction | INavigationNode)[] = [
    {
        name: 'SomePageTemplate',
        typeCode: 'Template',
        itemtype: 'Template',
        uid: 'SomePageTemplate',
        uuid: 'SomePageTemplate'
    },
    {
        creationtime: '2016-08-17T16:05:47+0000',
        modifiedtime: '2016-08-17T16:05:47+0000',
        name: 'Component1',
        typeCode: 'AbstractCMSComponent',
        uid: 'component1',
        uuid: 'component1',
        visible: true,
        slots: []
    },
    {
        creationtime: '2016-08-17T16:05:47+0000',
        modifiedtime: '2016-08-17T16:05:47+0000',
        name: 'Component2',
        typeCode: 'AbstractCMSComponent',
        uid: 'component2',
        uuid: 'component2',
        visible: true,
        slots: []
    },
    {
        creationtime: '2016-08-17T16:05:47+0000',
        modifiedtime: '2016-08-17T16:05:47+0000',
        name: 'Component3',
        typeCode: 'AbstractCMSComponent',
        uid: 'component3',
        uuid: 'component3',
        visible: true,
        slots: []
    },
    {
        creationtime: '2016-08-17T16:05:47+0000',
        modifiedtime: '2016-08-17T16:05:47+0000',
        name: 'Component4',
        typeCode: 'AbstractCMSComponent',
        uid: 'component4',
        uuid: 'component4',
        visible: true,
        slots: []
    },
    {
        creationtime: '2016-08-17T16:05:47+0000',
        modifiedtime: '2016-08-17T16:05:47+0000',
        name: 'Component5',
        typeCode: 'AbstractCMSComponent',
        uid: 'component5',
        uuid: 'component5',
        visible: true,
        slots: []
    },
    {
        name: 'Component6',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component6',
        uuid: 'Component6',
        slots: []
    },
    {
        name: 'Component7',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component7',
        uuid: 'Component7',
        slots: []
    },
    {
        name: 'Component8',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component8',
        uuid: 'Component8',
        slots: ['bottomHeaderSlot', 'footerSlot'] // This component is used as a shared component test
    },
    {
        name: 'Component9',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component9',
        uuid: 'Component9',
        slots: []
    },
    {
        name: 'Component10',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component10',
        uuid: 'Component10',
        cloneable: true,
        slots: []
    },
    {
        name: 'Component11',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component11',
        uuid: 'Component11',
        slots: []
    },
    {
        name: 'Component12',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component12',
        uuid: 'Component12',
        slots: []
    },
    {
        name: 'Component13',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component13',
        uuid: 'Component13'
    },
    {
        name: 'Component14',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component14',
        uuid: 'Component14',
        slots: []
    },
    {
        name: 'Component15',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component15',
        uuid: 'Component15',
        slots: []
    },
    {
        name: 'Component16',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component16',
        uuid: 'Component16',
        slots: []
    },
    {
        name: 'Component17',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component17',
        uuid: 'Component6',
        slots: []
    },
    {
        name: 'Component18',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component18',
        uuid: 'Component18',
        slots: []
    },
    {
        name: 'Component19',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component19',
        uuid: 'Component19',
        slots: []
    },
    {
        name: 'Component20',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component20',
        uuid: 'Component20',
        slots: []
    },
    {
        name: 'Component21',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component21',
        uuid: 'Component21',
        slots: []
    },
    {
        name: 'Component22',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component22',
        uuid: 'Component22',
        slots: []
    },
    {
        name: 'Component23',
        typeCode: 'AbstractCMSComponent',
        uid: 'Component23',
        uuid: 'Component23',
        slots: []
    },
    {
        id: 'MyParagraph',
        uid: 'Comp_0006456345634',
        uuid: 'Comp_0006456345634',
        name: 'Custom Paragraph Component',
        headline: HEAD_LINE,
        active: true,
        content: CONTENT_TO_EDIT,
        activationDate: new Date().getTime(),
        creationtime: new Date().getTime(),
        modifiedtime: new Date().getTime(),
        media: '4',
        external: false,
        visible: true,
        restrictions: [],
        onlyOneRestrictionMustApply: false,
        slots: ['mocked_slot_id']
    },
    {
        uid: 'componentWithMedia',
        uuid: 'componentWithMedia',
        media: {
            en: 'contextualmenu_delete_off'
        },
        slots: []
    },
    {
        uid: 'componentWithMediaContainer',
        uuid: 'componentWithMediaContainer',
        media: {
            en: {
                widescreen: 'clone4',
                desktop: 'dnd5'
            }
        },
        slots: []
    },
    {
        uid: 'componentToValidateId',
        uuid: 'componentToValidateId',
        headline: HEAD_LINE,
        active: true,
        content: {
            en: CONTENT_TO_EDIT,
            fr: 'le contenu a editer',
            pl: 'tresc edytowac',
            it: 'il contenuto da modificare',
            hi: 'Sampaadit karanee kee liee saamagree'
        },
        media: {
            en: 'contextualmenu_delete_off',
            hi: 'contextualmenu_delete_on'
        },
        orientation: 'vertical',
        linkToggle: {
            external: false,
            urlLink: '/url-link'
        },
        slots: []
    },
    {
        uid: '8',
        uuid: '8',
        navigationComponent: '8',
        slots: []
    },
    {
        uid: '8',
        uuid: 'navigationComponent',
        navigationComponent: '8',
        slots: []
    },
    {
        uid: '4',
        uuid: 'node4'
    },
    {
        uid: 'HomepageNavLink',
        uuid: 'HomepageNavLink',
        typeCode: 'CMSLinkComponent',
        linkName: {
            en: 'ABC Entry'
        },
        slots: []
    },
    {
        uid: '69SlamLink',
        uuid: '69SlamLink',
        typeCode: '69SlamLink',
        name: {
            en: 'DEF Entry'
        },
        slots: []
    },
    {
        uid: 'DakineLink',
        uuid: 'DakineLink',
        typeCode: 'CMSParagraphComponent',
        name: 'GHI Entry',
        slots: []
    },
    {
        uid: 'AlMerrickLink',
        uuid: 'AlMerrickLink',
        typeCode: 'CMSParagraphComponent',
        name: 'XYZ Entry',
        slots: []
    },
    {
        uid: 'Item-ID-8.1',
        uuid: 'Item-ID-8.1',
        typeCode: 'CMSLinkComponent',
        linkName: {
            en: 'JKL Entry'
        },
        slots: []
    },
    {
        uid: 'Item-ID-8.2',
        uuid: 'Item-ID-8.2',
        typeCode: 'CMSParagraphComponent',
        name: 'MNO Entry',
        slots: []
    },
    {
        uid: 'Item-ID-4.1',
        uuid: 'Item-ID-4.1',
        typeCode: 'CMSParagraphComponent',
        name: 'PQR Entry',
        slots: []
    },
    {
        uid: 'Item-ID-4.2',
        uuid: 'Item-ID-4.2',
        typeCode: 'CMSParagraphComponent',
        name: 'STU Entry',
        slots: []
    },
    {
        name: 'Home Page Nav Link',
        typeCode: 'CMSLinkComponent',
        uid: 'HomepageNavLink',
        uuid: 'HomepageNavLink',
        visible: true,
        slots: []
    },
    {
        name: 'Al Merrick Link',
        typeCode: 'CMSLinkComponent',
        uid: 'AlMerrickLink',
        uuid: 'AlMerrickLink',
        visible: true,
        slots: []
    },
    {
        name: 'Nike Link',
        typeCode: 'CMSLinkComponent',
        uid: 'NikeLink',
        uuid: 'NikeLink',
        visible: true,
        slots: []
    },
    {
        name: '69 Slam Link',
        typeCode: 'CMSLinkComponent',
        uid: '69SlamLink',
        uuid: '69SlamLink',
        visible: true,
        slots: []
    },
    {
        name: 'Dakine Link',
        typeCode: 'CMSLinkComponent',
        uid: 'DakineLink',
        uuid: 'DakineLink',
        slots: []
    },
    {
        typeCode: 'SingleOnlineProductSelector',
        uid: 'singleOnlineProductSelectorEditComponentId',
        uuid: 'singleOnlineProductSelectorEditComponentId',
        product: '300738116',
        slots: []
    },
    {
        type: 'contentPageData',
        creationtime: '2016-06-28T15:23:37+0000',
        defaultPage: false,
        approvalStatus: 'APPROVED',
        displayStatus: 'READY_TO_SYNC',
        modifiedtime: '2016-06-28T15:25:51+0000',
        name: 'Homepage',
        masterTemplate: 'AccountPageTemplateUuid',
        title: {
            de: 'Mes lovens pendas',
            en: 'I love pandas'
        },
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'homepage',
        uuid: 'homepage',
        label: 'i-love-pandas',
        restrictions: [
            {
                uid: 'timeRestrictionIdA',
                uuid: 'timeRestrictionIdA',
                name: TIME_RESTRICTION,
                typeCode: 'CMSTimeRestriction',
                itemtype: 'CMSTimeRestriction',
                description: SOME_DESCRIPTION
            },
            {
                uid: 'timeRestrictionIdB',
                uuid: 'timeRestrictionIdB',
                name: 'another time B',
                typeCode: 'CMSTimeRestriction',
                itemtype: 'CMSTimeRestriction',
                description: SOME_DESCRIPTION
            }
        ],
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        type: 'contentPageData',
        creationtime: '2016-06-28T15:23:37+0000',
        defaultPage: true,
        modifiedtime: '2016-06-28T15:25:51+0000',
        name: 'Some Other Page',
        masterTemplate: 'ProductPageTemplateUuid',
        title: {
            de: 'Mes hatens pendas',
            en: 'I hate pandas'
        },
        typeCode: 'ProductPage',
        itemtype: 'ProductPage',
        uid: 'secondpage',
        uuid: 'secondpage',
        approvalStatus: 'APPROVED',
        displayStatus: 'READY_TO_SYNC',
        label: IHATE_PANDAS,
        pageStatus: 'ACTIVE',
        restrictions: [],
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        type: 'contentPageData',
        creationtime: '2018-06-28T15:23:37+0000',
        defaultPage: true,
        modifiedtime: '2018-06-28T15:25:51+0000',
        name: 'Third Page',
        masterTemplate: 'ProductPageTemplateUuid',
        title: {
            en: 'Third page'
        },
        typeCode: 'ProductPage',
        itemtype: 'ProductPage',
        uid: 'thirdpage',
        uuid: 'thirdpage',
        label: 'third-page',
        approvalStatus: 'APPROVED',
        displayStatus: 'READY_TO_SYNC',
        pageStatus: 'ACTIVE',
        restrictions: [],
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        type: 'contentPageData',
        creationtime: '2016-06-28T15:23:37+0000',
        defaultPage: false,
        approvalStatus: 'CHECK',
        displayStatus: 'IN_PROGRESS',
        modifiedtime: '2016-06-28T15:25:51+0000',
        name: 'otherpage',
        masterTemplate: 'AccountPageTemplateUuid',
        title: {
            de: 'other page - de',
            en: 'other page - en'
        },
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'otherpage',
        uuid: 'otherpage',
        label: '/otherpage',
        restrictions: [],
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        type: 'contentPageData',
        creationtime: '2016-06-28T15:23:37+0000',
        defaultPage: true,
        modifiedtime: '2016-06-28T15:25:51+0000',
        name: 'Synched Page',
        masterTemplate: 'ProductPageTemplateUuid',
        title: {
            en: 'synched page'
        },
        typeCode: 'ProductPage',
        itemtype: 'ProductPage',
        uid: 'synchedPage',
        uuid: 'synchedPage',
        label: 'synched page',
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        type: 'contentPageData',
        creationtime: '2016-04-08T21:16:41+0000',
        modifiedtime: '2016-04-08T21:16:41+0000',
        masterTemplate: 'PageTemplate',
        name: 'productPage1',
        label: 'productPage1',
        typeCode: 'ProductPage',
        title: {
            en: 'productPage1'
        },
        uid: 'auid2',
        uuid: 'auid2',
        defaultPage: true,
        pageStatus: 'ACTIVE',
        itemtype: 'ProductPage',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        type: 'contentPageData',
        creationtime: '2016-04-08T21:16:41+0000',
        modifiedtime: '2016-04-08T21:16:41+0000',
        masterTemplate: 'PageTemplate',
        name: 'productPage1',
        label: 'productPage1',
        typeCode: 'ProductPage',
        title: {
            en: 'productPage1'
        },
        uid: 'auid2',
        uuid: 'auid2',
        defaultPage: true,
        pageStatus: 'ACTIVE',
        itemtype: 'ProductPage',
        catalogVersion: APPAREL_UK_ONLINE
    },
    {
        uid: 'AccountPageTemplate',
        uuid: 'AccountPageTemplateUuid'
    },
    {
        uid: 'ProductPageTemplate',
        uuid: 'ProductPageTemplateUuid'
    },
    {
        // RESTRICTIONS
        uid: 'timeRestrictionIdA',
        uuid: 'timeRestrictionIdA',
        name: TIME_RESTRICTION,
        typeCode: 'CMSTimeRestriction',
        itemtype: 'CMSTimeRestriction',
        description: SOME_DESCRIPTION
    },
    {
        uid: 'timeRestrictionIdB',
        uuid: 'timeRestrictionIdB',
        name: 'another time B',
        typeCode: 'CMSTimeRestriction',
        itemtype: 'CMSTimeRestriction',
        description: SOME_DESCRIPTION
    },
    {
        uid: 'timeRestrictionIdC',
        uuid: 'timeRestrictionIdC',
        name: 'yet another',
        typeCode: 'CMSTimeRestriction',
        itemtype: 'CMSTimeRestriction',
        description: SOME_DESCRIPTION
    },
    {
        uid: 'catalogRestrictionIdD',
        uuid: 'catalogRestrictionIdD',
        name: 'some cat restriction',
        typeCode: 'CMSCategoryRestriction',
        itemtype: 'CMSCategoryRestriction',
        description: SOME_DESCRIPTION,
        categories: ['categoryA']
    },
    {
        uid: 'catalogRestrictionIdE',
        uuid: 'catalogRestrictionIdE',
        name: "I'm a skatman",
        typeCode: 'CMSCatalogRestriction',
        itemtype: 'CMSCatalogRestriction',
        description: SOME_DESCRIPTION
    },
    {
        uid: 'catalogRestrictionIdF',
        uuid: 'catalogRestrictionIdF',
        name: 'Cat restriction E',
        typeCode: 'CMSCatalogRestriction',
        itemtype: 'CMSCatalogRestriction',
        description: SOME_DESCRIPTION
    },
    {
        uid: 'catalogRestrictionIdG',
        uuid: 'catalogRestrictionIdG',
        name: 'catalogRestrictionNameG',
        typeCode: 'CMSCatalogRestriction',
        itemtype: 'CMSCatalogRestriction',
        description: SOME_DESCRIPTION
    },
    {
        uid: 'catalogRestrictionIdH',
        uuid: 'catalogRestrictionIdH',
        name: 'Some User restriciton 1',
        typeCode: 'CMSCatalogRestriction',
        itemtype: 'CMSCatalogRestriction',
        description: SOME_DESCRIPTION
    },
    {
        uid: 'userRestrictionIdI',
        uuid: 'userRestrictionIdI',
        name: 'User restriction 2',
        typeCode: 'CMSUserRestriction',
        itemtype: 'CMSUserRestriction',
        description: SOME_DESCRIPTION
    },
    {
        uid: 'userGroupRestrictionId1',
        uuid: 'userGroupRestrictionId1',
        name: 'User Group Restriction 1',
        typeCode: 'CMSUserGroupRestriction',
        itemtype: 'CMSUserGroupRestriction',
        description: SOME_DESCRIPTION
    },
    {
        // page for restrictions
        type: 'contentPageData',
        uid: ADD_EDIT_ADDRESS,
        uuid: ADD_EDIT_ADDRESS,
        onlyOneRestrictionMustApply: false,
        creationtime: '2016-07-15T23:35:21+0000',
        defaultPage: true,
        modifiedtime: '2016-07-15T23:38:01+0000',
        name: 'Add Edit Address Page',
        template: 'AccountPageTemplate',
        title: {
            de: 'Adresse hinzufügen/bearbeiten'
        },
        label: ADD_EDIT_ADDRESS,
        restrictions: [
            {
                uid: 'timeRestrictionIdA',
                uuid: 'timeRestrictionIdA',
                name: TIME_RESTRICTION,
                typeCode: 'CMSTimeRestriction',
                itemtype: 'CMSTimeRestriction',
                description: SOME_DESCRIPTION
            },
            {
                uid: 'catalogRestrictionIdD',
                uuid: 'catalogRestrictionIdD',
                name: 'some cat restriction',
                typeCode: 'CMSCategoryRestriction',
                itemtype: 'CMSCategoryRestriction',
                description: SOME_DESCRIPTION,
                categories: ['categoryA']
            }
        ]
    },
    {
        // PAGES
        creationtime: '2016-04-08T21:16:41+0000',
        modifiedtime: '2016-04-08T21:16:41+0000',
        template: 'PageTemplate',
        name: 'page1TitleSuffix',
        label: 'page1TitleSuffix',
        typeCode: 'ContentPage',
        uid: 'auid1',
        uuid: 'auid1',
        defaultPage: true,
        pageStatus: 'ACTIVE',
        itemtype: 'ContentPage',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        creationtime: '2016-04-08T21:16:41+0000',
        modifiedtime: '2016-04-08T21:16:41+0000',
        template: 'ActionTemplate',
        name: 'welcomePage',
        typeCode: 'ActionPage',
        uid: 'uid2',
        uuid: 'uid2'
    },
    {
        creationtime: '2016-04-08T21:16:41+0000',
        modifiedtime: '2016-04-08T21:16:41+0000',
        template: 'PageTemplate',
        name: 'Advertise',
        typeCode: 'MyCustomType',
        uid: 'uid3',
        uuid: 'uid3'
    },
    {
        creationtime: '2016-04-08T21:16:41+0000',
        modifiedtime: '2016-04-08T21:16:41+0000',
        template: 'MyCustomPageTemplate',
        name: 'page2TitleSuffix',
        typeCode: 'HomePage',
        uid: 'uid4',
        uuid: 'uid4'
    },
    {
        creationtime: '2016-04-08T21:16:41+0000',
        modifiedtime: '2016-04-08T21:16:41+0000',
        template: 'ZTemplate',
        name: 'page3TitleSuffix',
        typeCode: 'ProductPage',
        uid: 'uid5',
        uuid: 'uid5'
    },
    {
        creationtime: '2016-07-07T14:33:37+0000',
        defaultPage: true,
        modifiedtime: '2016-07-12T01:23:41+0000',
        name: 'My Little Primary Content Page',
        onlyOneRestrictionMustApply: true,
        masterTemplate: 'SomePageTemplate',
        title: {
            en: PRIMARY_CONTENT_PAGE
        },
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'primaryContentPage',
        uuid: 'primaryContentPage',
        label: 'primary-content-page',
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        creationtime: '2016-07-03T14:33:37+0000',
        defaultPage: true,
        modifiedtime: '2016-07-11T01:23:41+0000',
        name: 'Some Other Primary Content Page',
        onlyOneRestrictionMustApply: true,
        masterTemplate: 'SomePageTemplate',
        title: {
            en: PRIMARY_CONTENT_PAGE
        },
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'someOtherPrimaryPageContent',
        uuid: 'someOtherPrimaryPageContent',
        label: 'some-other-primary-page-content',
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        creationtime: '2016-07-02T14:33:37+0000',
        defaultPage: true,
        modifiedtime: '2016-07-10T01:23:41+0000',
        name: 'Special Primary Content Page',
        onlyOneRestrictionMustApply: true,
        masterTemplate: 'SomePageTemplate',
        title: {
            en: PRIMARY_CONTENT_PAGE
        },
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'specialPrimaryPageContent',
        uuid: 'specialPrimaryPageContent',
        label: 'special-primary-page-content',
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        creationtime: '2016-07-07T14:33:37+0000',
        defaultPage: true,
        modifiedtime: '2016-07-12T01:23:41+0000',
        name: 'My Little Variation Content Page',
        onlyOneRestrictionMustApply: true,
        masterTemplate: 'SomePageTemplate',
        title: {
            en: VARIATION_CONTENT_PAGE
        },
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'variationContentPage',
        uuid: 'variationContentPage',
        label: 'variation-content-page',
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        creationtime: '2016-07-07T14:33:37+0000',
        defaultPage: true,
        modifiedtime: '2016-07-12T01:23:41+0000',
        name: 'My Little Primary Category Page',
        onlyOneRestrictionMustApply: true,
        masterTemplate: 'SomePageTemplate',
        title: {
            en: 'Primary Category Page'
        },
        typeCode: 'CategoryPage',
        itemtype: 'CategoryPage',
        uid: 'primaryCategoryPage',
        uuid: 'primaryCategoryPage',
        label: 'primary-category-page',
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        creationtime: '2016-07-07T14:33:37+0000',
        defaultPage: true,
        modifiedtime: '2016-07-12T01:23:41+0000',
        name: 'My Little Variation Category Page',
        onlyOneRestrictionMustApply: true,
        masterTemplate: 'SomePageTemplate',
        title: {
            en: 'Variation Category Page'
        },
        typeCode: 'CategoryPage',
        itemtype: 'CategoryPage',
        uid: 'variationCategoryPage',
        uuid: 'variationCategoryPage',
        label: 'variation-category-page',
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        creationtime: '2016-07-07T14:33:37+0000',
        defaultPage: true,
        modifiedtime: '2016-07-12T01:23:41+0000',
        name: 'My Little Primary Product Page',
        onlyOneRestrictionMustApply: true,
        masterTemplate: 'SomePageTemplate',
        title: {
            en: 'Primary Product Page'
        },
        typeCode: 'ProductPage',
        itemtype: 'ProductPage',
        uid: 'primaryProductPage',
        uuid: 'primaryProductPage',
        label: 'primary-product-page',
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        creationtime: '2016-07-07T14:33:37+0000',
        defaultPage: true,
        modifiedtime: '2016-07-12T01:23:41+0000',
        name: 'My Little Variation Product Page',
        onlyOneRestrictionMustApply: true,
        masterTemplate: 'SomePageTemplate',
        title: {
            en: 'Variation Product Page'
        },
        typeCode: 'ProductPage',
        itemtype: 'ProductPage',
        uid: 'variationProductPage',
        uuid: 'variationProductPage',
        label: 'variation-product-page',
        pageStatus: 'ACTIVE',
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        type: 'contentPageData',
        defaultPage: true,
        name: 'Homepage_uk_online',
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'homepage_uk_online',
        uuid: 'homepage_uk_online',
        catalogVersion: APPAREL_UK_ONLINE,
        label: IHATE_PANDAS,
        restrictions: [],
        pageStatus: 'ACTIVE'
    },
    {
        type: 'contentPageData',
        defaultPage: true,
        name: 'Homepage_global_online',
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'homepage_global_online',
        uuid: 'homepage_global_online',
        catalogVersion: APPAREL_ONLINE,
        label: IHATE_PANDAS,
        restrictions: [],
        pageStatus: 'ACTIVE'
    },
    {
        type: 'contentPageData',
        defaultPage: true,
        name: 'Homepage_global_online',
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'homepage_global_online',
        uuid: 'homepage_global_online',
        catalogVersion: 'apparel-deContentCatalog/Online',
        label: IHATE_PANDAS,
        restrictions: [],
        pageStatus: 'ACTIVE'
    },
    {
        type: 'contentPageData',
        defaultPage: true,
        name: 'Homepage_global_staged',
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'homepage_global_staged',
        uuid: 'homepage_global_staged',
        catalogVersion: 'apparelContentCatalog/Staged',
        restrictions: [],
        pageStatus: 'ACTIVE'
    },
    {
        type: 'contentPageData',
        defaultPage: false,
        name: 'Homepage_gloabl_online_variation',
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'homepage_gloabl_online_variation',
        uuid: 'homepage_gloabl_online_variation',
        catalogVersion: APPAREL_ONLINE,
        restrictions: [],
        pageStatus: 'ACTIVE'
    },
    {
        type: 'contentPageData',
        defaultPage: true,
        name: 'Homepage_gloabl_online_copy_disabled',
        copyToCatalogsDisabled: true,
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'homepage_gloabl_online_copy_disabled',
        uuid: 'homepage_gloabl_online_copy_disabled',
        catalogVersion: APPAREL_ONLINE,
        restrictions: [],
        pageStatus: 'ACTIVE'
    },
    {
        id: 'MyParagraph',
        uid: 'staticDummyComponent',
        uuid: 'staticDummyComponent',
        name: 'Static Dummy Component',
        headline: HEAD_LINE,
        active: true,
        content: CONTENT_TO_EDIT,
        creationtime: new Date().getTime(),
        modifiedtime: new Date().getTime(),
        type: 'componentType1',
        media: '4',
        external: false,
        visible: true,
        cloneable: true,
        restrictions: [],
        onlyOneRestrictionMustApply: false,
        slots: ['mocked_slot_id']
    },
    {
        catalogVersion: APPRAREL_UK_STAGED,
        creationtime: '2016-07-07T14:33:37+0000',
        modifiedtime: '2016-07-12T01:23:41+0000',
        name: 'Trashed Content Page',
        masterTemplate: 'SomePageTemplate',
        title: {
            en: VARIATION_CONTENT_PAGE
        },
        typeCode: 'ContentPage',
        itemtype: 'ContentPage',
        uid: 'trashedContentPage',
        uuid: 'trashedContentPage',
        label: 'trashedContentPage',
        pageStatus: 'DELETED',
        restrictions: []
    },
    {
        catalogVersion: APPRAREL_UK_STAGED,
        creationtime: '2016-07-07T14:33:37+0000',
        modifiedtime: '2016-06-28T15:25:51+0000',
        name: 'Trashed Category Page',
        masterTemplate: 'SomePageTemplate',
        approvalStatus: 'APPROVED',
        title: {
            en: VARIATION_CONTENT_PAGE
        },
        typeCode: 'CategoryPage',
        itemtype: 'CategoryPage',
        uid: 'trashedCategoryPage',
        uuid: 'trashedCategoryPage',
        label: 'trashedCategoryPage',
        pageStatus: 'DELETED',
        restrictions: []
    },
    {
        catalogVersion: APPAREL_UK_ONLINE,
        creationtime: '2016-07-07T14:33:37+0000',
        modifiedtime: '2016-06-28T15:25:51+0000',
        name: 'Trashed Category Page',
        template: 'SomePageTemplate',
        approvalStatus: 'APPROVED',
        title: {
            en: VARIATION_CONTENT_PAGE
        },
        typeCode: 'CategoryPage',
        itemtype: 'CategoryPage',
        uid: 'trashedCategoryPage',
        uuid: 'trashedCategoryPage',
        label: 'trashedCategoryPage',
        pageStatus: 'ACTIVE',
        restrictions: []
    },
    {
        creationtime: '2016-07-07T14:33:37+0000',
        modifiedtime: '2017-09-26T15:22:37+0000',
        name: 'Trashed Product Page',
        masterTemplate: 'SomePageTemplate',
        title: {
            en: VARIATION_CONTENT_PAGE
        },
        typeCode: 'ProductPage',
        itemtype: 'ProductPage',
        uid: 'trashedProductPage',
        uuid: 'trashedProductPage',
        label: 'trashedProductPage',
        pageStatus: 'DELETED',
        restrictions: [],
        catalogVersion: APPRAREL_UK_STAGED
    },
    {
        uid: 'hiddenComponent1',
        uuid: 'hiddenComponent1',
        name: 'Hidden Component 1',
        visible: false,
        cloneable: true,
        typeCode: 'AbstractCMSComponent',
        catalogVersion: APPRAREL_UK_STAGED,
        restrictions: [],
        slots: []
    },
    {
        uid: 'hiddenComponent2',
        uuid: 'hiddenComponent2',
        name: 'Hidden Component 2',
        visible: false,
        cloneable: true,
        typeCode: 'AbstractCMSComponent',
        catalogVersion: APPRAREL_UK_STAGED,
        slots: ['bottomHeaderSlot', 'footerSlot']
    },
    {
        uid: 'hiddenComponent3',
        uuid: 'hiddenComponent3',
        name: 'Hidden Component 3',
        visible: false,
        cloneable: false,
        typeCode: 'AbstractCMSComponent',
        catalogVersion: APPRAREL_UK_STAGED,
        slots: []
    },
    // Navigation nodes
    {
        uid: 'root',
        name: 'ROOT',
        uuid: CMS_NAVIGATION_NODE_ROOT,
        itemtype: 'CMSNavigationNode',
        position: 0,
        hasChildren: true,
        children: [CMS_NAVIGATION_NODE1, CMS_NAVIGATION_NODE2],
        catalogVersion: APPAREL_UK_ONLINE
    },
    {
        uid: '1',
        name: 'node1',
        title: {
            en: 'node1_en',
            fr: 'node1_fr'
        },
        uuid: CMS_NAVIGATION_NODE1,
        itemtype: 'CMSNavigationNode',
        parent: CMS_NAVIGATION_NODE_ROOT,
        parentUid: 'root',
        position: 0,
        hasChildren: true,
        hasEntries: true,
        children: [CMS_NAVIGATION_NODE4, 'CMSNavigationNode-5', 'CMSNavigationNode-7'],
        catalogVersion: APPAREL_UK_ONLINE
    },
    {
        uid: '2',
        name: 'node2',
        title: {
            en: 'node2_en',
            fr: 'node2_fr'
        },
        uuid: CMS_NAVIGATION_NODE2,
        itemtype: 'CMSNavigationNode',
        parent: CMS_NAVIGATION_NODE_ROOT,
        parentUid: 'root',
        position: 1,
        hasChildren: true,
        hasEntries: false,
        children: ['CMSNavigationNode-6'],
        catalogVersion: APPAREL_UK_ONLINE
    },
    {
        uid: '4',
        name: 'node4',
        title: {
            en: 'node4_en',
            fr: 'node4_fr'
        },
        uuid: CMS_NAVIGATION_NODE4,
        itemtype: 'CMSNavigationNode',
        parent: CMS_NAVIGATION_NODE1,
        parentUid: '1',
        position: 0,
        hasChildren: true,
        hasEntries: true,
        children: [CMS_NAVIGATION_NODE8],
        catalogVersion: APPAREL_UK_ONLINE
    },
    {
        uid: '5',
        name: 'node5',
        title: {
            en: 'node5_en',
            fr: 'node5_fr'
        },
        uuid: 'CMSNavigationNode-5',
        itemtype: 'CMSNavigationNode',
        parent: CMS_NAVIGATION_NODE1,
        parentUid: '1',
        position: 1,
        hasChildren: false,
        hasEntries: false,
        children: [],
        catalogVersion: APPAREL_UK_ONLINE
    },
    {
        uid: '6',
        name: 'node6',
        title: {
            en: 'node6_en',
            fr: 'node6_fr'
        },
        uuid: 'CMSNavigationNode-6',
        itemtype: 'CMSNavigationNode',
        parent: CMS_NAVIGATION_NODE2,
        parentUid: '2',
        position: 0,
        hasChildren: false,
        hasEntries: false,
        children: [],
        catalogVersion: APPAREL_UK_ONLINE
    },
    {
        uid: '7',
        name: 'node7',
        title: {
            en: 'node7_en',
            fr: 'node7_fr'
        },
        uuid: 'CMSNavigationNode-7',
        itemtype: 'CMSNavigationNode',
        parent: CMS_NAVIGATION_NODE1,
        parentUid: '1',
        position: 2,
        hasChildren: false,
        hasEntries: false,
        children: [],
        catalogVersion: APPAREL_UK_ONLINE
    },
    {
        uid: '8',
        name: 'node8',
        title: {
            en: 'node8_en',
            fr: 'node8_fr'
        },
        uuid: CMS_NAVIGATION_NODE8,
        itemtype: 'CMSNavigationNode',
        parent: CMS_NAVIGATION_NODE4,
        parentUid: '4',
        position: 0,
        hasChildren: true,
        hasEntries: true,
        children: ['CMSNavigationNode-9'],
        catalogVersion: APPAREL_UK_ONLINE
    },
    {
        uid: '9',
        name: 'node9',
        title: {
            en: 'node9_en',
            fr: 'node9_fr'
        },
        uuid: 'CMSNavigationNode-9',
        itemtype: 'CMSNavigationNode',
        parent: CMS_NAVIGATION_NODE8,
        parentUid: '8',
        position: 0,
        hasChildren: false,
        hasEntries: false,
        children: [],
        catalogVersion: APPAREL_UK_ONLINE
    },
    {
        uuid: 'navigationComponent',
        uid: 'navigationComponent',
        navigationComponent: CMS_NAVIGATION_NODE8,
        catalogVersion: APPAREL_UK_ONLINE
    }
];
