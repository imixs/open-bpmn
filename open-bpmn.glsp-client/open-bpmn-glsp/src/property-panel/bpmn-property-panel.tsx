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
import { inject, injectable, postConstruct } from 'inversify';
import {
	AbstractUIExtension,
	EnableDefaultToolsAction,
	EnableToolsAction,
	SetUIExtensionVisibilityAction,
	ActionDispatcher,
	SModelRoot,
	svg,
	TYPES
} from 'sprotty';
import { codiconCSSClasses } from 'sprotty/lib/utils/codicon';
import {
	Action,
	EditModeListener,
	EditorContextService
} from '@eclipse-glsp/client';

import {
	SelectionListener,
	SelectionService
} from '@eclipse-glsp/client/lib/features/select/selection-service';

import { TaskNode, GatewayNode, EventNode } from '../model';

// Import Instruction sReact and JsonForms
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { JsonForms } from '@jsonforms/react';
import {
	vanillaCells,
	vanillaRenderers
} from '@jsonforms/vanilla-renderers';

// import { JsonFormsCore, JsonSchema, UISchemaElement } from '@jsonforms/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class EnableBPMNPropertyPanelAction implements Action {
	static readonly KIND = 'enableBPMNPropertyPanel';
	readonly kind = EnableBPMNPropertyPanelAction.KIND;
}

@injectable()
export class BPMNPropertyPanel extends AbstractUIExtension implements EditModeListener, SelectionListener {
	static readonly ID = 'bpmn-property-panel';

	@inject(TYPES.IActionDispatcher)
	protected readonly actionDispatcher: ActionDispatcher;

	@inject(EditorContextService)
	protected readonly editorContext: EditorContextService;

	@inject(SelectionService)
	protected selectionService: SelectionService;

	protected bodyDiv: HTMLElement;
	modelRootId: string;

	@postConstruct()
	postConstruct(): void {
		this.editorContext.register(this);
		this.selectionService.register(this);
	}

	id(): string {
		return BPMNPropertyPanel.ID;
	}
	containerClass(): string {
		return BPMNPropertyPanel.ID;
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
		hideToolButton.onclick = this.onClickResizePanel(hideToolButton, 'TOOL_COMMAND_HIDE');
		headerTools.appendChild(hideToolButton);

		const minimizeToolButton = createIcon('chevron-down');
		minimizeToolButton.title = 'Minimize Property Panel';
		minimizeToolButton.onclick = this.onClickResizePanel(minimizeToolButton, 'TOOL_COMMAND_MINIMIZE');
		headerTools.appendChild(minimizeToolButton);

		const maximizeToolButton = createIcon('chevron-up');
		maximizeToolButton.title = 'Maximize Property Panel';
		maximizeToolButton.onclick = this.onClickResizePanel(maximizeToolButton, 'TOOL_COMMAND_MAXIMIZE');
		headerTools.appendChild(maximizeToolButton);

		return headerTools;
	}

	protected onClickResizePanel(button: HTMLElement, toolId?: string) {
		return (_ev: MouseEvent) => {
			if (!this.editorContext.isReadonly) {
				const action = toolId ? new EnableToolsAction([toolId]) : new EnableDefaultToolsAction();
				this.actionDispatcher.dispatch(action);
				if (toolId === 'TOOL_COMMAND_MINIMIZE') {
					this.containerElement.style.height = '33.333%';
				}
				if (toolId === 'TOOL_COMMAND_MAXIMIZE') {
					this.containerElement.style.height = '50%';
				}
				if (toolId === 'TOOL_COMMAND_HIDE') {
					this.containerElement.style.height = '25px';
				}
				// restore the defautl actions in the diagram panel (like move elements)
				this.actionDispatcher.dispatch(new EnableDefaultToolsAction());
			}
		};
	}

	editModeChanged(_oldValue: string, _newValue: string): void {
		console.log('...bin in editModeChanged: ' + _newValue);
		this.actionDispatcher.dispatch(new SetUIExtensionVisibilityAction(BPMNPropertyPanel.ID, !this.editorContext.isReadonly));
	}

	/*
	 * This mehtod reacts on the selection of a BPMN element
	 * and updates the property panel input fields
	 */
	selectionChanged(root: Readonly<SModelRoot>, selectedElements: string[]): void {
		console.log('selection change  received:', root, selectedElements);
		if (this.selectionService.isSingleSelection()) {
			const element = root.index.getById(selectedElements[0]);
			if (element instanceof TaskNode) {
				console.log('...Task selected');
				const task: TaskNode = element;
				console.log('...category=' + task.category + ' documentation ' + task.documentation);
				const documentationInput = document.createElement('input');
				documentationInput.insertAdjacentText('beforeend', '' + task.documentation);
				documentationInput.onchange = function (this) {
					console.log('...do something... ');
				};
				this.bodyDiv?.appendChild(documentationInput);
			}

			if (element instanceof GatewayNode) {
				console.log('...Gateway selected');
				const gateway: GatewayNode = element;
				const elementDataAsJson = {};
				ReactDOM.render(
					<JsonForms
						data={elementDataAsJson}
						cells={vanillaCells}
						renderers={vanillaRenderers}
						readonly
					/>,
					this.bodyDiv
				);
				console.log('...eventtype=' + gateway.category);
			}

			if (element instanceof EventNode) {
				console.log('...Event selected....');
				const event: EventNode = element;
				ReactDOM.render(
					<React.Fragment>Please select an element </React.Fragment>,
					this.bodyDiv
				);
				console.log('...eventtype=' + event.category);
			}
		}
	}

}

export function createIcon(codiconId: string): HTMLElement {
	const icon = document.createElement('i');
	icon.classList.add(...codiconCSSClasses(codiconId));
	return icon;
}

