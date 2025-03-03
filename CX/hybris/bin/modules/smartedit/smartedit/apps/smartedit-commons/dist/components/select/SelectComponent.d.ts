import './SelectComponent.scss';
import { CdkDragDrop } from '@angular/cdk/drag-drop';
import { ChangeDetectorRef, EventEmitter, OnChanges, OnInit, SimpleChanges, Type } from '@angular/core';
import { LogService } from '@smart/utils';
import { L10nService } from '../../services';
import { ActionableSearchItem } from './actionableSearchItem';
import { FetchStrategy, SelectApi, SelectDisableChoice, SelectItem, SelectKeyup, SelectOnChange, SelectOnRemove, SelectOnSelect, SelectReset } from './interfaces';
import { SearchInputComponent } from './searchInput';
export declare class SelectComponent<T extends SelectItem> implements OnInit, OnChanges {
    private readonly l10nService;
    private readonly logService;
    private readonly cdr;
    get isSingleCss(): boolean;
    get isMultiCss(): boolean;
    get isPagedCss(): boolean;
    id: string;
    model: string | string[];
    modelChange: EventEmitter<string | string[]>;
    fetchStrategy: FetchStrategy<T>;
    controls: boolean;
    multiSelect: boolean;
    keepModelOnReset: boolean;
    isReadOnly: boolean;
    actionableSearchItem?: ActionableSearchItem;
    resultsHeaderComponent?: Type<any>;
    resultsHeaderLabel?: string;
    disableChoiceFn?: SelectDisableChoice<T>;
    placeholder?: string;
    itemComponent?: Type<any>;
    searchEnabled: boolean;
    resetSearchInput: boolean;
    onChange?: SelectOnChange;
    onRemove?: SelectOnRemove<T>;
    onSelect?: SelectOnSelect<T>;
    keyup?: SelectKeyup;
    showRemoveButton: boolean;
    getApi: EventEmitter<SelectApi>;
    reset: SelectReset;
    resetChange: EventEmitter<SelectReset>;
    searchInputCmp: SearchInputComponent;
    isOpen: boolean;
    items: T[];
    selected: T | T[];
    search: string;
    private fetchAllItems;
    private modelChangeOld;
    private readonly api;
    private validationState;
    private filterFn;
    private languageSwitchSubscription;
    private searchInputChange$;
    private searchInputChangeSubject;
    constructor(l10nService: L10nService, logService: LogService, cdr: ChangeDetectorRef);
    clickHandler(event: MouseEvent): void;
    ngOnInit(): void;
    ngOnChanges(changes: SimpleChanges): void;
    ngOnDestroy(): void;
    onSingleSelectIsOpenChange(isOpen: boolean): void;
    onSearchInputKeyup(event: KeyboardEvent, value: string): void;
    onSearchInputChange(value: string): void;
    onOptionClick(item: T): void;
    onSearchChange(value: string): void;
    onDrop(event: CdkDragDrop<string[]>): void;
    removeSelectedOption(_: Event, item: T): void;
    closeAndReset(): void;
    showResultsHeaderLabel(): boolean;
    onInfiniteScrollItemsChange(): void;
    showPlaceholder(): boolean;
    itemTrackBy(_: any, item: SelectItem): string;
    setValidationState(validationState: string): void;
    resetValidationState(): void;
    hasError(): boolean;
    hasWarning(): boolean;
    fetchEntity(modelId: string): Promise<T>;
    updateModelIfNotFoundInItems(items: T[]): void;
    refreshOptions(mask: string): void;
    isValidConfiguration(): void;
    updateModel(model: string | string[]): void;
    private internalKeyup;
    private internalOnRemove;
    private internalOnChange;
    private internalOnSelect;
    private internalFetchAll;
    private internalFetchEntities;
    private onMultiSelectIsOpenChange;
    private initSearchInputFilter;
    private filterItemsBySearch;
    private close;
    private resetOnClose;
    private fetchData;
    private internalFetchAllAndCheckModel;
    private getItemByModel;
    private getItemsByModel;
    private mapSelectedToModel;
    private setSelected;
    private isItemSelected;
    private internalReset;
    private resetModel;
    private isPagedDropdown;
    private isModelEmpty;
    private resolveAndSetSelected;
}
