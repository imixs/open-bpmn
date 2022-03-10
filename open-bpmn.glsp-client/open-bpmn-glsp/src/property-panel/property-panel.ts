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
import { isSetContextActionsAction, RequestContextActions} from '@eclipse-glsp/protocol';
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

@injectable()
export class EnablePropertyPanelAction implements Action {
    static readonly KIND = 'enableToolPalette';
    readonly kind = EnablePropertyPanelAction.KIND;
}

@injectable()
export class PropertyPanel extends AbstractUIExtension implements IActionHandler, EditModeListener {
    static readonly ID = 'bpmn-property-panel';

    @inject(TYPES.IActionDispatcher) protected readonly actionDispatcher: GLSPActionDispatcher;
    @inject(EditorContextService) protected readonly editorContext: EditorContextService;

    protected paletteItems: FormSection[];
    protected paletteItemsCopy: FormSection[] = [];
    protected bodyDiv?: HTMLElement;
    protected lastActivebutton?: HTMLElement;
    protected defaultToolsButton: HTMLElement;
    protected searchField: HTMLInputElement;
    modelRootId: string;

    id(): string {
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
        return super.initialize();
    }

	/*
	 * Initalize the elemnts of property panel
	 */
    protected initializeContents(_containerElement: HTMLElement): void {
        this.createHeader();
        this.createBody();
        this.lastActivebutton = this.defaultToolsButton;
    }

    protected onBeforeShow(_containerElement: HTMLElement, root: Readonly<SModelRoot>): void {
        this.modelRootId = root.id;
    }

    protected createHeader(): void {
        const headerCompartment = document.createElement('div');
        headerCompartment.classList.add('bpmn-property-header');
        headerCompartment.append(this.createHeaderTitle());
        headerCompartment.appendChild(this.createHeaderTools());
        this.containerElement.appendChild(headerCompartment);
    }

    protected createBody(): void {
        const bodyDiv = document.createElement('div');
        bodyDiv.classList.add('properties-body');
        this.containerElement.appendChild(bodyDiv);
        this.bodyDiv = bodyDiv;
    }

    protected createHeaderTitle(): HTMLElement {
        const header = document.createElement('div');
        header.classList.add('header-icon');
        header.appendChild(createIcon('layers'));
        header.insertAdjacentText('beforeend', 'BPMN Properties');
        return header;
    }

	/*
	 * This method creads the header tool buttons to controle the size of the panel
	 * Icons can be found here:
	 * https://microsoft.github.io/vscode-codicons/dist/codicon.html
	 */
    private createHeaderTools(): HTMLElement {
        const headerTools = document.createElement('div');
        headerTools.classList.add('header-tools');

        const hideToolButton = createIcon('chrome-minimize');
        hideToolButton.title = 'Hide Property Panel';
        hideToolButton.onclick = this.onClickStaticToolButton(hideToolButton,'TOOL_COMMAND_HIDE');
        headerTools.appendChild(hideToolButton);

        const minimizeToolButton = createIcon('chevron-down');
        minimizeToolButton.title = 'Minimize Property Panel';
        minimizeToolButton.onclick = this.onClickStaticToolButton(minimizeToolButton,'TOOL_COMMAND_MINIMIZE');
        headerTools.appendChild(minimizeToolButton);

        const maximizeToolButton = createIcon('chevron-up');
        maximizeToolButton.title = 'Maximize Property Panel';
        maximizeToolButton.onclick = this.onClickStaticToolButton(maximizeToolButton,'TOOL_COMMAND_MAXIMIZE');
        headerTools.appendChild(maximizeToolButton);

        return headerTools;
    }

    protected onClickStaticToolButton(button: HTMLElement, toolId?: string) {
        return (_ev: MouseEvent) => {
            if (!this.editorContext.isReadonly) {
                const action = toolId ? new EnableToolsAction([toolId]) : new EnableDefaultToolsAction();
                this.actionDispatcher.dispatch(action);
                if (toolId === 'TOOL_COMMAND_MINIMIZE') {
					this.containerElement.style.height='33.333%';
				}
                if (toolId === 'TOOL_COMMAND_MAXIMIZE') {
					this.containerElement.style.height='50%';
				}
				if (toolId === 'TOOL_COMMAND_HIDE') {
					this.containerElement.style.height='25px';
				}
				// restore the defautl actions in the diagram panel (like move elements)
				this.actionDispatcher.dispatch(new EnableDefaultToolsAction());
            }
        };
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
            this.restoreFocus();
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

export function createIcon(codiconId: string): HTMLElement {
    const icon = document.createElement('i');
    icon.classList.add(...codiconCSSClasses(codiconId));
    return icon;
}

export function changeCSSClass(element: Element, css: string): void {
    element.classList.contains(css) ? element.classList.remove(css) : element.classList.add(css);
}

export function changeCodiconClass(element: Element, codiconId: string): void {
    element.classList.contains(codiconCSSClasses(codiconId)[1])
        ? element.classList.remove(codiconCSSClasses(codiconId)[1])
        : element.classList.add(codiconCSSClasses(codiconId)[1]);
}
