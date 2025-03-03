/*
 * Copyright (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 */
import {
    GenericEditorAPI,
    GenericEditorField,
    GenericEditorWidgetData,
    IGenericEditor,
    SettingsService
} from 'smarteditcommons';
import { RichTextFieldComponent } from './RichTextFieldComponent';
import {
    GenericEditorSanitizationService,
    RichTextFieldLocalizationService,
    RichTextLoaderService
} from './services';
describe('RichTextField', () => {
    let seRichTextLoaderService: jasmine.SpyObj<RichTextLoaderService>;
    let documentSpy: jasmine.Spy;
    let genericEditorSanitizationService: jasmine.SpyObj<GenericEditorSanitizationService>;
    let seRichTextFieldLocalizationService: jasmine.SpyObj<RichTextFieldLocalizationService>;
    let richTextFieldComponent: RichTextFieldComponent;
    let settingsService: jasmine.SpyObj<SettingsService>;

    let editable: any;
    let editorInstance: any;
    let seRichTextConfiguration: any;

    let originalCKEDITOR: any;
    let spySetSubmitDisabled: any;

    const widget: GenericEditorWidgetData<any> = {
        field: {
            qualifier: 'someQualifier'
        } as GenericEditorField,
        qualifier: 'en',
        isFieldDisabled: () => false,
        model: {},
        id: null,
        editor: {
            api: {
                setInProgress(isInProgress: boolean) {
                    // This is intentional
                }
            } as GenericEditorAPI
        } as IGenericEditor
    };
    beforeAll(() => {
        originalCKEDITOR = window.CKEDITOR;
    });

    afterAll(() => {
        window.CKEDITOR = originalCKEDITOR;
    });

    beforeEach(async () => {
        settingsService = jasmine.createSpyObj<SettingsService>('settingsService', ['get']);
        seRichTextLoaderService = jasmine.createSpyObj<RichTextLoaderService>(
            'seRichTextLoaderService',
            ['load']
        );
        documentSpy = spyOn(document, 'getElementById').and.returnValue({
            addEventListener: () => void 0
        } as any);
        seRichTextConfiguration = {};
        seRichTextFieldLocalizationService = jasmine.createSpyObj<RichTextFieldLocalizationService>(
            'seRichTextFieldLocalizationService',
            ['localizeCKEditor']
        );

        window.CKEDITOR = jasmine.createSpyObj('CKEDITOR', ['replace', 'on']);
        window.CKEDITOR.dtd = {
            $nonBodyContent: {},
            $block: {
                div: 1
            },
            $listItem: {},
            $tableContent: {}
        };

        editorInstance = jasmine.createSpyObj('editorInstance', [
            'destroy',
            'on',
            'getData',
            'fire',
            'editable'
        ]);

        editable = jasmine.createSpyObj('editable', ['attachListener']);
        editorInstance.editable.and.returnValue(editable);
        window.CKEDITOR.replace.and.returnValue(editorInstance);
        editorInstance.getData.and.returnValue('changed value');

        genericEditorSanitizationService = jasmine.createSpyObj<GenericEditorSanitizationService>(
            'genericEditorSanitizationService',
            ['isSanitized']
        );

        seRichTextLoaderService.load.and.returnValue(Promise.resolve());

        settingsService.get.and.returnValue(Promise.resolve('false'));

        richTextFieldComponent = new RichTextFieldComponent(
            widget,
            seRichTextLoaderService,
            seRichTextConfiguration,
            genericEditorSanitizationService,
            seRichTextFieldLocalizationService,
            settingsService
        );

        (richTextFieldComponent as any).textarea = document.createElement('textarea');

        await richTextFieldComponent.ngAfterViewInit();
    });

    describe('component init ', () => {
        it('should set allowed content to false if property "cms.components.allowUnsafeJavaScript" is false', async () => {
            settingsService.get.and.returnValue(Promise.resolve('false'));
            await richTextFieldComponent.ngAfterViewInit();

            expect(seRichTextConfiguration).toEqual(
                jasmine.objectContaining({
                    allowedContent: false
                })
            );
        });

        it('should set allowed content to true if property "cms.components.allowUnsafeJavaScript" is true', async () => {
            settingsService.get.and.returnValue(Promise.resolve('true'));
            await richTextFieldComponent.ngAfterViewInit();

            expect(seRichTextConfiguration).toEqual(
                jasmine.objectContaining({
                    allowedContent: true
                })
            );
        });
    });

    describe('controller', () => {
        describe('onMode', () => {
            it('should be attach editable listener if the mode is source', () => {
                editorInstance.mode = 'source';
                richTextFieldComponent.onMode();
                expect(editable.attachListener).toHaveBeenCalled();
            });

            it('should not be attach editable listener if the mode is not source', () => {
                editorInstance.mode = 'wysiwyg';
                richTextFieldComponent.onMode();
                expect(editable.attachListener).not.toHaveBeenCalled();
            });
        });

        describe('onInstanceReady', () => {
            it('should be called set rules method with attributes', () => {
                const setRules = jasmine.createSpy('setRules');

                const MOCK_EV = {
                    editor: {
                        dataProcessor: {
                            writer: {
                                setRules
                            }
                        }
                    }
                };

                richTextFieldComponent.onInstanceReady(MOCK_EV);
                expect(setRules).toHaveBeenCalledWith('br', {
                    indent: false,
                    breakBeforeOpen: false,
                    breakAfterOpen: false,
                    breakBeforeClose: false,
                    breakAfterClose: false
                });
                expect(setRules).toHaveBeenCalledWith('div', {
                    indent: false,
                    breakBeforeOpen: false,
                    breakAfterOpen: false,
                    breakBeforeClose: false,
                    breakAfterClose: false
                });
            });
        });

        describe('onChange', () => {
            beforeEach(() => {
                spySetSubmitDisabled = spyOn(
                    richTextFieldComponent.widget.editor.api,
                    'setInProgress'
                );
                jasmine.clock().install();
            });

            afterEach(() => {
                jasmine.clock().uninstall();
            });

            it(
                'setSubmitDisabled will set to true WHEN sanitized content changed IF requiresUserCheck is true and user does not' +
                    ' check the warning message',
                () => {
                    richTextFieldComponent.widget.qualifier = 'en';

                    richTextFieldComponent.widget.model = {
                        en: '"http://"'
                    };
                    richTextFieldComponent.widget.field = {
                        isUserChecked: false
                    } as any;

                    richTextFieldComponent.onChange();
                    jasmine.clock().tick(1);

                    expect(spySetSubmitDisabled).toHaveBeenCalledWith(true);
                }
            );

            it(
                'setSubmitDisabled will set to false WHEN sanitized content changed IF requiresUserCheck is true and user' +
                    ' checks the warning message',
                () => {
                    richTextFieldComponent.widget.qualifier = 'en';

                    richTextFieldComponent.widget.model = {
                        en: '"http://"'
                    };
                    richTextFieldComponent.widget.field = {
                        isUserChecked: true
                    } as any;

                    richTextFieldComponent.onChange();
                    jasmine.clock().tick(1);

                    expect(spySetSubmitDisabled).toHaveBeenCalledWith(false);
                }
            );

            it('setSubmitDisabled will set to false WHEN sanitized content changed IF requiresUserCheck is false', () => {
                genericEditorSanitizationService.isSanitized.and.returnValue(true);

                richTextFieldComponent.widget.qualifier = 'en';
                richTextFieldComponent.widget.model = {
                    en: '<p>Valid Html</p>'
                };
                richTextFieldComponent.widget.field = {} as any;
                richTextFieldComponent.onChange();
                jasmine.clock().tick(1);

                expect(spySetSubmitDisabled).toHaveBeenCalledWith(false);
            });
        });
    });

    describe('on data change', () => {
        it('should call genericEditorSanitizationService', () => {
            richTextFieldComponent.widget.qualifier = 'en';
            richTextFieldComponent.widget.model = {
                en: '<div><script>alert(/"I am a snippet/");</script></div>'
            };
            richTextFieldComponent.widget.field = {} as any;
            richTextFieldComponent.reassignUserCheck();
            expect(genericEditorSanitizationService.isSanitized).toHaveBeenCalled();
        });

        it('reassignUserCheck WILL set requiresUserCheck as true on field with javascript snippet WHEN sanitized content does not match unsanitized content', () => {
            richTextFieldComponent.widget.qualifier = 'en';
            richTextFieldComponent.widget.model = {
                en: '<div><script>alert(/"I am a snippet/");</script></div>'
            };
            richTextFieldComponent.widget.field = {} as any;
            richTextFieldComponent.reassignUserCheck();
            expect(
                richTextFieldComponent.widget.field.requiresUserCheck[
                    richTextFieldComponent.widget.qualifier
                ]
            ).toBe(true);
        });

        it('reassignUserCheck WILL set requiresUserCheck as true on field WHEN sanitized content does not match unsanitized content', () => {
            richTextFieldComponent.widget.qualifier = 'en';

            richTextFieldComponent.widget.model = {
                en: '"http://"'
            };
            richTextFieldComponent.widget.field = {} as any;
            richTextFieldComponent.reassignUserCheck();
            expect(
                richTextFieldComponent.widget.field.requiresUserCheck[
                    richTextFieldComponent.widget.qualifier
                ]
            ).toBe(true);
        });

        it('reassignUserCheck WILL not set requiresUserCheck on field WHEN sanitized content matches unsanitized content.', () => {
            genericEditorSanitizationService.isSanitized.and.returnValue(true);

            richTextFieldComponent.widget.qualifier = 'en';
            richTextFieldComponent.widget.model = {
                en: '<p>Valid Html</p>'
            };
            richTextFieldComponent.widget.field = {} as any;
            richTextFieldComponent.reassignUserCheck();

            expect(
                richTextFieldComponent.widget.field.requiresUserCheck[
                    richTextFieldComponent.widget.qualifier
                ]
            ).toBe(false);
        });

        it('reassignUserCheck WILL not set requiresUserCheck on field WHEN there is no content', () => {
            richTextFieldComponent.widget.model = {};
            richTextFieldComponent.widget.field = {} as any;

            richTextFieldComponent.reassignUserCheck();
            expect(
                richTextFieldComponent.widget.field.requiresUserCheck[
                    richTextFieldComponent.widget.qualifier
                ]
            ).toBe(false);
        });

        it('reassignUserCheck WILL not set requiresUserCheck on field WHEN the model is not defined', () => {
            richTextFieldComponent.widget.field = {} as any;
            richTextFieldComponent.widget.model = undefined;

            richTextFieldComponent.reassignUserCheck();
            expect(
                richTextFieldComponent.widget.field.requiresUserCheck[
                    richTextFieldComponent.widget.qualifier
                ]
            ).toBe(false);
        });
    });
});
