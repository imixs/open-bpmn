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
import { SetContextActions, isSetContextActionsAction, RequestContextActions} from '@eclipse-glsp/protocol';
import { inject, injectable, postConstruct } from 'inversify';
import {
    AbstractUIExtension,
    Action,
    EnableDefaultToolsAction,
    EnableToolsAction,
    IActionHandler,
    ICommand,
    // IToolManager,
    SetUIExtensionVisibilityAction,
    SModelRoot,
    TYPES
} from 'sprotty';
import { codiconCSSClasses } from 'sprotty/lib/utils/codicon';
import { matchesKeystroke } from 'sprotty/lib/utils/keyboard';
import {
	GLSPActionDispatcher,
	EditModeListener,
	EditorContextService
} from '@eclipse-glsp/client';
import { FormSection } from './form-section';

const CLICKED_CSS_CLASS = 'clicked';
const PALETTE_HEIGHT = '500px';

@injectable()
export class EnablePropertyPanelAction implements Action {
    static readonly KIND = 'enableToolPalette';
    readonly kind = EnablePropertyPanelAction.KIND;
}

@injectable()
export class PropertyPanel extends AbstractUIExtension implements IActionHandler, EditModeListener {
    static readonly ID = 'bpmn-property-panel';

    @inject(TYPES.IActionDispatcher) protected readonly actionDispatcher: GLSPActionDispatcher;
    // @inject(TYPES.IToolManager) protected readonly toolManager: IToolManager;
    @inject(EditorContextService) protected readonly editorContext: EditorContextService;

    protected paletteItems: FormSection[];
    protected paletteItemsCopy: FormSection[] = [];
    protected bodyDiv?: HTMLElement;
    protected lastActivebutton?: HTMLElement;
    protected defaultToolsButton: HTMLElement;
    protected searchField: HTMLInputElement;
    modelRootId: string;

    id(): string {
		console.log('.......jemand will meine id');
        return PropertyPanel.ID;
    }
    containerClass(): string {
        return PropertyPanel.ID;
    }

    @postConstruct()
    postConstruct(): void {
        this.editorContext.register(this);
    }

    initialize(): boolean {
		console.log('.......bin in inizalize');
        return super.initialize();
    }

    protected initializeContents(_containerElement: HTMLElement): void {
		console.log('.......bin in inizalizeContents der uebergebne container='+_containerElement.id);
        this.createHeader();
        this.createBody();
        this.lastActivebutton = this.defaultToolsButton;
    }

    protected onBeforeShow(_containerElement: HTMLElement, root: Readonly<SModelRoot>): void {
		console.log('...bin in onBeforeShow die conainerElementid='+_containerElement.id);
        this.modelRootId = root.id;
        this.containerElement.style.maxHeight = PALETTE_HEIGHT;
        console.log('...bin in onBeforeShow fertig. Meine ID= '+this.containerElement.id);
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
		console.log('...ich bin in create body containerElement ID='+this.containerElement.id);
        const bodyDiv = document.createElement('div');
        bodyDiv.classList.add('properties-body');
        this.containerElement.appendChild(bodyDiv);
        this.bodyDiv = bodyDiv;
		console.log('...bin mit create body fertig');
    }

    protected createHeader(): void {
        const headerCompartment = document.createElement('div');
        headerCompartment.classList.add('bpmn-property-header');
        headerCompartment.append(this.createHeaderTitle());
        headerCompartment.appendChild(this.createHeaderTools());
        this.containerElement.appendChild(headerCompartment);
    }

    private createHeaderTools(): HTMLElement {
        const headerTools = document.createElement('div');
        headerTools.classList.add('header-tools');
		// add something
		this.defaultToolsButton = this.createDefaultToolButton();
        headerTools.appendChild(this.defaultToolsButton);

        return headerTools;
    }

    protected createDefaultToolButton(): HTMLElement {
        const button = document.createElement('button');
        button.id = 'btn_default_tools';
        button.title = 'Enable selection tool';
		// button.onclick = this.onClickStaticToolButton(this.defaultToolsButton);
        return button;
    }

    protected createHeaderTitle(): HTMLElement {
        const header = document.createElement('div');
        header.classList.add('header-icon');
        header.insertAdjacentText('beforeend', 'Ich bin der Properties Header');
        return header;
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
		console.log('...bin in der handle methode');
        if (action.kind === EnablePropertyPanelAction.KIND) {
			console.log('...bin in handle - EnablePropertyPanelAction');
            const requestAction = new RequestContextActions(PropertyPanel.ID, {
                selectedElementIds: []
            });
            this.actionDispatcher.requestUntil(requestAction).then(response => {
                if (isSetContextActionsAction(response)) {
                    this.paletteItems = response.actions.map(e => e as FormSection);
                    this.actionDispatcher.dispatch(new SetUIExtensionVisibilityAction(PropertyPanel.ID, !this.editorContext.isReadonly));
                }
            });
        } else if (action instanceof EnableDefaultToolsAction) {
            this.changeActiveButton();
            this.restoreFocus();
            console.log('..habe gehandeld');
        }
    }

    editModeChanged(_oldValue: string, _newValue: string): void {
		console.log('bin in editModeChanged: '+_newValue);
        this.actionDispatcher.dispatch(new SetUIExtensionVisibilityAction(PropertyPanel.ID, !this.editorContext.isReadonly));
    }

    protected clearToolOnEscape(event: KeyboardEvent): void {
        if (matchesKeystroke(event, 'Escape')) {
            this.actionDispatcher.dispatch(new EnableDefaultToolsAction());
        }
    }

    protected handleSetContextActions(action: SetContextActions): void {
		console.log('...bin in handelSetContextActions');
        // this.paletteItems = action.actions.map(e => e as PaletteItem);
        this.createBody();
    }

}

export function createToolGroup(item: FormSection): HTMLElement {
    const group = document.createElement('div');
    group.classList.add('tool-group');
    group.id = item.id;
    const header = document.createElement('div');
    header.classList.add('group-header');

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
