/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
import { isSetContextActionsAction, RequestContextActions, RequestMarkersAction, SetContextActions } from '@eclipse-glsp/protocol';
import { inject, injectable, postConstruct } from 'inversify';
import {
    AbstractUIExtension,
    Action,
    EnableDefaultToolsAction,
    EnableToolsAction,
    IActionHandler,
    ICommand,
    IToolManager,
    SetUIExtensionVisibilityAction,
    SModelRoot,
    TYPES
} from 'sprotty';
import { codiconCSSClasses } from 'sprotty/lib/utils/codicon';
import { matchesKeystroke } from 'sprotty/lib/utils/keyboard';
import { GLSPActionDispatcher } from '../../base/action-dispatcher';
import { EditModeListener, EditorContextService } from '../../base/editor-context-service';
import { MouseDeleteTool } from '../tools/delete-tool';
import { MarqueeMouseTool } from '../tools/marquee-mouse-tool';
import { PaletteItem } from './palette-item';

const CLICKED_CSS_CLASS = 'clicked';
const SEARCH_ICON_ID = 'search';
const PALETTE_ICON_ID = 'symbol-color';
const CHEVRON_DOWN_ICON_ID = 'chevron-right';
const PALETTE_HEIGHT = '500px';

@injectable()
export class EnableToolPaletteAction implements Action {
    static readonly KIND = 'enableToolPalette';
    readonly kind = EnableToolPaletteAction.KIND;
}

@injectable()
export class ToolPalette extends AbstractUIExtension implements IActionHandler, EditModeListener {
    static readonly ID = 'tool-palette';

    @inject(TYPES.IActionDispatcher) protected readonly actionDispatcher: GLSPActionDispatcher;
    @inject(TYPES.IToolManager) protected readonly toolManager: IToolManager;
    @inject(EditorContextService) protected readonly editorContext: EditorContextService;

    protected paletteItems: PaletteItem[];
    protected paletteItemsCopy: PaletteItem[] = [];
    protected bodyDiv?: HTMLElement;
    protected lastActivebutton?: HTMLElement;
    protected defaultToolsButton: HTMLElement;
    protected searchField: HTMLInputElement;
    modelRootId: string;

    id(): string {
        return ToolPalette.ID;
    }
    containerClass(): string {
        return ToolPalette.ID;
    }

    @postConstruct()
    postConstruct(): void {
        this.editorContext.register(this);
    }

    initialize(): boolean {
        if (!this.paletteItems) {
            return false;
        }
        return super.initialize();
    }

    protected initializeContents(_containerElement: HTMLElement): void {
        this.createHeader();
        this.createBody();
        this.lastActivebutton = this.defaultToolsButton;
    }

    protected onBeforeShow(_containerElement: HTMLElement, root: Readonly<SModelRoot>): void {
        this.modelRootId = root.id;
        this.containerElement.style.maxHeight = PALETTE_HEIGHT;
    }

    protected addMinimizePaletteButton(): void {
        const baseDiv = document.getElementById(this.options.baseDiv);
        const minPaletteDiv = document.createElement('div');
        minPaletteDiv.classList.add('minimize-palette-button');
        this.containerElement.classList.add('collapsible-palette');
        if (baseDiv) {
            const insertedDiv = baseDiv.insertBefore(minPaletteDiv, baseDiv.firstChild);
            const minimizeIcon = createIcon(CHEVRON_DOWN_ICON_ID);
            this.updateMinimizePaletteButtonTooltip(minPaletteDiv);
            minimizeIcon.onclick = _event => {
                if (this.isPaletteMaximized()) {
                    this.containerElement.style.maxHeight = '0px';
                } else {
                    this.containerElement.style.maxHeight = PALETTE_HEIGHT;
                }
                this.updateMinimizePaletteButtonTooltip(minPaletteDiv);
                changeCodiconClass(minimizeIcon, PALETTE_ICON_ID);
                changeCodiconClass(minimizeIcon, CHEVRON_DOWN_ICON_ID);
            };
            insertedDiv.appendChild(minimizeIcon);
        }
    }

    protected updateMinimizePaletteButtonTooltip(button: HTMLDivElement): void {
        if (this.isPaletteMaximized()) {
            button.title = 'Minimize palette';
        } else {
            button.title = 'Maximize palette';
        }
    }

    protected isPaletteMaximized(): boolean {
        return this.containerElement && this.containerElement.style.maxHeight !== '0px';
    }

    protected createBody(): void {
        const bodyDiv = document.createElement('div');
        bodyDiv.classList.add('palette-body');
        let tabIndex = 0;
        this.paletteItems.sort(compare).forEach(item => {
            if (item.children) {
                const group = createToolGroup(item);
                item.children.sort(compare).forEach(child => group.appendChild(this.createToolButton(child, tabIndex++)));
                bodyDiv.appendChild(group);
            } else {
                bodyDiv.appendChild(this.createToolButton(item, tabIndex++));
            }
        });
        if (this.paletteItems.length === 0) {
            const noResultsDiv = document.createElement('div');
            noResultsDiv.innerText = 'No results found.';
            noResultsDiv.classList.add('tool-button');
            bodyDiv.appendChild(noResultsDiv);
        }
        // Remove existing body to refresh filtered entries
        if (this.bodyDiv) {
            this.containerElement.removeChild(this.bodyDiv);
        }
        this.containerElement.appendChild(bodyDiv);
        this.bodyDiv = bodyDiv;
    }

    protected createHeader(): void {
        this.addMinimizePaletteButton();
        const headerCompartment = document.createElement('div');
        headerCompartment.classList.add('palette-header');
        headerCompartment.append(this.createHeaderTitle());
        headerCompartment.appendChild(this.createHeaderTools());
        headerCompartment.appendChild((this.searchField = this.createHeaderSearchField()));
        this.containerElement.appendChild(headerCompartment);
    }

    private createHeaderTools(): HTMLElement {
        const headerTools = document.createElement('div');
        headerTools.classList.add('header-tools');

        this.defaultToolsButton = this.createDefaultToolButton();
        headerTools.appendChild(this.defaultToolsButton);

        const deleteToolButton = this.createMouseDeleteToolButton();
        headerTools.appendChild(deleteToolButton);

        const marqueeToolButton = this.createMarqueeToolButton();
        headerTools.appendChild(marqueeToolButton);

        const validateActionButton = this.createValidateButton();
        headerTools.appendChild(validateActionButton);

        // Create button for Search
        const searchIcon = this.createSearchButton();
        headerTools.appendChild(searchIcon);

        return headerTools;
    }

    protected createDefaultToolButton(): HTMLElement {
        const button = createIcon('inspect');
        button.id = 'btn_default_tools';
        button.title = 'Enable selection tool';
        button.onclick = this.onClickStaticToolButton(this.defaultToolsButton);
        return button;
    }

    protected createMouseDeleteToolButton(): HTMLElement {
        const deleteToolButton = createIcon('chrome-close');
        deleteToolButton.title = 'Enable deletion tool';
        deleteToolButton.onclick = this.onClickStaticToolButton(deleteToolButton, MouseDeleteTool.ID);
        return deleteToolButton;
    }

    protected createMarqueeToolButton(): HTMLElement {
        const marqueeToolButton = createIcon('screen-full');
        marqueeToolButton.title = 'Enable marquee tool';
        marqueeToolButton.onclick = this.onClickStaticToolButton(marqueeToolButton, MarqueeMouseTool.ID);
        return marqueeToolButton;
    }

    protected createValidateButton(): HTMLElement {
        const validateActionButton = createIcon('pass');
        validateActionButton.title = 'Validate model';
        validateActionButton.onclick = _event => {
            const modelIds: string[] = [this.modelRootId];
            this.actionDispatcher.dispatch(new RequestMarkersAction(modelIds));
        };
        return validateActionButton;
    }

    protected createSearchButton(): HTMLElement {
        const searchIcon = createIcon(SEARCH_ICON_ID);
        searchIcon.onclick = _ev => {
            const searchField = document.getElementById(this.containerElement.id + '_search_field');
            if (searchField) {
                if (searchField.style.display === 'none') {
                    searchField.style.display = '';
                    searchField.focus();
                } else {
                    searchField.style.display = 'none';
                }
            }
        };
        searchIcon.classList.add('search-icon');
        searchIcon.title = 'Filter palette entries';
        return searchIcon;
    }

    protected createHeaderSearchField(): HTMLInputElement {
        const searchField = document.createElement('input');
        searchField.classList.add('search-input');
        searchField.id = this.containerElement.id + '_search_field';
        searchField.type = 'text';
        searchField.placeholder = ' Search...';
        searchField.style.display = 'none';
        searchField.onkeyup = () => this.requestFilterUpdate(this.searchField.value);
        searchField.onkeydown = ev => this.clearOnEscape(ev);
        return searchField;
    }

    protected createHeaderTitle(): HTMLElement {
        const header = document.createElement('div');
        header.classList.add('header-icon');
        header.appendChild(createIcon(PALETTE_ICON_ID));
        header.insertAdjacentText('beforeend', 'Palette');
        return header;
    }

    protected createToolButton(item: PaletteItem, index: number): HTMLElement {
        const button = document.createElement('div');
        button.tabIndex = index;
        button.classList.add('tool-button');
        if (item.icon) {
            button.appendChild(createIcon(item.icon));
        }
        button.insertAdjacentText('beforeend', item.label);
        button.onclick = this.onClickCreateToolButton(button, item);
        button.onkeydown = ev => this.clearToolOnEscape(ev);
        return button;
    }

    protected onClickCreateToolButton(button: HTMLElement, item: PaletteItem) {
        return (_ev: MouseEvent) => {
            if (!this.editorContext.isReadonly) {
                this.actionDispatcher.dispatchAll(item.actions);
                this.changeActiveButton(button);
                button.focus();
            }
        };
    }

    protected onClickStaticToolButton(button: HTMLElement, toolId?: string) {
        return (_ev: MouseEvent) => {
            if (!this.editorContext.isReadonly) {
                const action = toolId ? new EnableToolsAction([toolId]) : new EnableDefaultToolsAction();
                this.actionDispatcher.dispatch(action);
                this.changeActiveButton(button);
                button.focus();
            }
        };
    }

    changeActiveButton(button?: HTMLElement): void {
        if (this.lastActivebutton) {
            this.lastActivebutton.classList.remove(CLICKED_CSS_CLASS);
        }
        if (button) {
            button.classList.add(CLICKED_CSS_CLASS);
            this.lastActivebutton = button;
        } else {
            this.defaultToolsButton.classList.add(CLICKED_CSS_CLASS);
            this.lastActivebutton = this.defaultToolsButton;
        }
    }

    handle(action: Action): ICommand | Action | void {
        if (action.kind === EnableToolPaletteAction.KIND) {
            const requestAction = new RequestContextActions(ToolPalette.ID, {
                selectedElementIds: []
            });
            this.actionDispatcher.requestUntil(requestAction).then(response => {
                if (isSetContextActionsAction(response)) {
                    this.paletteItems = response.actions.map(e => e as PaletteItem);
                    this.actionDispatcher.dispatch(new SetUIExtensionVisibilityAction(ToolPalette.ID, !this.editorContext.isReadonly));
                }
            });
        } else if (action instanceof EnableDefaultToolsAction) {
            this.changeActiveButton();
            this.restoreFocus();
        }
    }

    editModeChanged(_oldValue: string, _newValue: string): void {
        this.actionDispatcher.dispatch(new SetUIExtensionVisibilityAction(ToolPalette.ID, !this.editorContext.isReadonly));
    }

    protected clearOnEscape(event: KeyboardEvent): void {
        if (matchesKeystroke(event, 'Escape')) {
            this.searchField.value = '';
            this.requestFilterUpdate('');
        }
    }

    protected clearToolOnEscape(event: KeyboardEvent): void {
        if (matchesKeystroke(event, 'Escape')) {
            this.actionDispatcher.dispatch(new EnableDefaultToolsAction());
        }
    }

    protected handleSetContextActions(action: SetContextActions): void {
        this.paletteItems = action.actions.map(e => e as PaletteItem);
        this.createBody();
    }

    protected requestFilterUpdate(filter: string): void {
        // Initialize the copy if it's empty
        if (this.paletteItemsCopy.length === 0) {
            // Creating deep copy
            this.paletteItemsCopy = JSON.parse(JSON.stringify(this.paletteItems));
        }

        // Reset the paletteItems before searching
        this.paletteItems = JSON.parse(JSON.stringify(this.paletteItemsCopy));
        // Filter the entries
        const filteredPaletteItems: PaletteItem[] = [];
        for (const itemGroup of this.paletteItems) {
            if (itemGroup.children) {
                // Fetch the labels according to the filter
                const matchingChildren = itemGroup.children.filter(child => child.label.toLowerCase().includes(filter.toLowerCase()));
                // Add the itemgroup containing the correct entries
                if (matchingChildren.length > 0) {
                    // Clear existing children
                    itemGroup.children.splice(0, itemGroup.children.length);
                    // Push the matching children
                    matchingChildren.forEach(child => itemGroup.children!.push(child));
                    filteredPaletteItems.push(itemGroup);
                }
            }
        }
        this.paletteItems = filteredPaletteItems;
        this.createBody();
    }
}

export function compare(a: PaletteItem, b: PaletteItem): number {
    const sortStringBased = a.sortString.localeCompare(b.sortString);
    if (sortStringBased !== 0) {
        return sortStringBased;
    }
    return a.label.localeCompare(b.label);
}

export function createIcon(codiconId: string): HTMLElement {
    const icon = document.createElement('i');
    icon.classList.add(...codiconCSSClasses(codiconId));
    return icon;
}

export function createToolGroup(item: PaletteItem): HTMLElement {
    const group = document.createElement('div');
    group.classList.add('tool-group');
    group.id = item.id;
    const header = document.createElement('div');
    header.classList.add('group-header');
    if (item.icon) {
        header.appendChild(createIcon(item.icon));
    }
    header.insertAdjacentText('beforeend', item.label);
    header.ondblclick = _ev => {
        const css = 'collapsed';
        changeCSSClass(group, css);
        Array.from(group.children).forEach(child => changeCSSClass(child, css));
        window!.getSelection()!.removeAllRanges();
    };

    group.appendChild(header);
    return group;
}

export function changeCSSClass(element: Element, css: string): void {
    element.classList.contains(css) ? element.classList.remove(css) : element.classList.add(css);
}

export function changeCodiconClass(element: Element, codiconId: string): void {
    element.classList.contains(codiconCSSClasses(codiconId)[1])
        ? element.classList.remove(codiconCSSClasses(codiconId)[1])
        : element.classList.add(codiconCSSClasses(codiconId)[1]);
}
