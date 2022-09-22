/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
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
import {
    AbstractUIExtension,
    Action,
    ActionDispatcher,
    EditModeListener,
    EditorContextService,
    EnableDefaultToolsAction,
    EnableToolsAction,SModelElement,
    hasArguments,MouseListener,
    SetUIExtensionVisibilityAction,GLSPActionDispatcher,IActionHandler,
    SModelRoot,
    TYPES
} from '@eclipse-glsp/client';
import { inject, injectable, postConstruct } from 'inversify';
import { codiconCSSClasses } from 'sprotty/lib/utils/codicon';
import { SelectionListener, SelectionService } from '@eclipse-glsp/client/lib/features/select/selection-service';
import { isBPMNNode } from '@open-bpmn/open-bpmn-model';
// Import Instruction sReact and JsonForms
import { JsonForms } from '@jsonforms/react';
import { vanillaCells, vanillaRenderers } from '@jsonforms/vanilla-renderers';
import * as React from 'react';
import * as ReactDOM from 'react-dom';

@injectable()
export class BPMNPropertyPanel extends AbstractUIExtension implements EditModeListener, SelectionListener, IActionHandler {
  
    static readonly ID = 'bpmn-property-panel';

    @inject(TYPES.IActionDispatcher)
    protected readonly actionDispatcher: ActionDispatcher;

    @inject(EditorContextService)
    protected readonly editorContext: EditorContextService;

    @inject(SelectionService)
    protected selectionService: SelectionService;

    protected bodyDiv: HTMLElement;
    modelRootId: string;
    selectedElementId: string;
    initForm: boolean;
    headerTitle: HTMLElement;

    @postConstruct()
    postConstruct(): void {
		console.log('...running postConstruct');
        this.editorContext.register(this);
        this.selectionService.register(this);
    }

    id(): string {
        return BPMNPropertyPanel.ID;
    }
    containerClass(): string {
        return BPMNPropertyPanel.ID;
    }
    
    handle(action: BPMNPropertyPanelAction): void | Action {
        console.log(" ...... handle for BPMNPropertyPanelAction not yet implemented - info="+action.additionalInformation);
    }

    /*
     * Initalize the elemnts of property panel
     */
    protected override initializeContents(_containerElement: HTMLElement): void {
		console.log('...running initializeContents');
        this.createHeader();
        this.createBody();
    }

    protected override onBeforeShow(_containerElement: HTMLElement, root: Readonly<SModelRoot>): void {
        console.log('....onBeforeShow - root.id='+root.id);
        this.modelRootId = root.id;
    }

    protected createHeader(): void {
        console.log("....createHeader...");
        const headerCompartment = document.createElement('div');
        console.log("...headerCompartment="+headerCompartment);
        headerCompartment.classList.add('bpmn-property-header');
        headerCompartment.append(this.createHeaderTitle());
        headerCompartment.appendChild(this.createHeaderTools());
        this.containerElement.appendChild(headerCompartment);
    }

    protected createBody(): void {
        const bodyDiv = document.createElement('div');
        // , 'jsonforms-property-view'
        bodyDiv.classList.add('bpmn-properties-body');
        this.containerElement.appendChild(bodyDiv);
        this.bodyDiv = bodyDiv;
    }

    protected createHeaderTitle(): HTMLElement {
        const header = document.createElement('div');
        header.classList.add('header-icon');
        header.appendChild(createIcon('extensions'));
        this.headerTitle = document.createElement('span');
        header.appendChild(this.headerTitle);
        this.headerTitle.textContent = 'BPMN Properties';
        return header;
    }

    /*
     * This method creads the header tool buttons to controle the size of the panel
     * Icons can be found here:
     * https://microsoft.github.io/vscode-codicons/dist/codicon.html
     */
    private createHeaderTools(): HTMLElement {
	console.log('...running createHeaderTools');
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
                const action = toolId ? EnableToolsAction.create([toolId]) : EnableDefaultToolsAction.create();
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
                this.actionDispatcher.dispatch(EnableDefaultToolsAction.create());
            }
        };
    }

    editModeChanged(_oldValue: string, _newValue: string): void {
        // eslint-disable-next-line max-len
        this.actionDispatcher.dispatch(
            SetUIExtensionVisibilityAction.create({ extensionId: BPMNPropertyPanel.ID, visible: !this.editorContext.isReadonly })
        );
    }

    /*
     * This mehtod reacts on the selection of a BPMN element
     * and updates the property panel input fields
     */
    selectionChanged(root: Readonly<SModelRoot>, selectedElements: string[]): void {
        // first we need to verify if a Symbol/BPMNLabel combination was selected.
        // In this case we are only interested in the BPMNFlowElement and not in the label
        if (selectedElements.length > 1) {
            const filteredArr = selectedElements.filter(val => !val.endsWith('_bpmnlabel'));
            selectedElements = filteredArr;
        }

        // Check if we now have exactly one elemnt selected. Only in this case we show
        // a property panel.
        if (selectedElements.length === 1) {
            const element = root.index.getById(selectedElements[0]);
            if (element) {
                // did we have a change?
                // avoid message loop...
                if (element.id === this.selectedElementId) {
                    // skip this event!
                    return;
                }
                // set new selectionId
                this.selectedElementId = element.id;
                console.log('======== > setup new property panel - selectionID=' + element.id);
                // because the jsonFomrs send a onchange event after init we mark this state here
                this.initForm = true;
                // update header
                // this.header.insertAdjacentText('beforeend',element.type);
                this.headerTitle.textContent = element.type;

                // Build a generic JSONForms Property panel
                if (isBPMNNode(element)) {
                    // BPMN Node selected, collect JSONForms schemata....
                    let bpmnPropertiesData;
                    let bpmnPropertiesSchema;
                    let bpmnPropertiesUISchema;
                    if (hasArguments(element)) {
                        // parse the jsonForms schema details
                        bpmnPropertiesData = JSON.parse(element.args.JSONFormsData + '');
                        bpmnPropertiesSchema = JSON.parse(element.args.JSONFormsSchema + '');
                        bpmnPropertiesUISchema = JSON.parse(element.args.JSONFormsUISchema + '');
                    }

                    // list of renderers declared outside the App component
                    const bpmnRenderers = [
                        ...vanillaRenderers,
                        // register custom renderers
                        //BPMNArrayRenderer
                        // { tester: ratingControlTester, renderer: RatingControl }
                    ];

                    // render JSONForm // vanillaRenderers
                    ReactDOM.render(
                        <JsonForms
                            data={bpmnPropertiesData}
                            schema={bpmnPropertiesSchema}
                            uischema={bpmnPropertiesUISchema}
                            cells={vanillaCells}
                            renderers={bpmnRenderers}
                            onChange={({ errors, data }) => this.setState({ data })}
                        />,
                        this.bodyDiv
                    );
                }
            } else {
                // element not defined!
                this.selectedElementId = '';
                this.headerTitle.textContent = 'BPMN Properties';
            }
        } else {
            // no single element selected!
            this.selectedElementId = '';
            if (this.bodyDiv) {
                this.headerTitle.textContent = 'BPMN Properties';
                if (!this.selectionService.hasSelectedElements()) {
                    // show an empty pane (or later the process panel)
                    if (this.bodyDiv) {
                        ReactDOM.render(<React.Fragment>Please select an element </React.Fragment>, this.bodyDiv);
                    }
                } else {
                    // multi selection - we can not show a property panel
                    if (this.bodyDiv) {
                        ReactDOM.render(<React.Fragment>Please select a single element </React.Fragment>, this.bodyDiv);
                    }
                }
            }
        }
    }

    /*
     * This method is responsible to send the new data in a
     * ApplyEditOperation Action to the server....
     */
    setState(_newData: any): void {
        if (this.initForm) {
            // we ignore the first onChange event
            // see https://jsonforms.io/docs/integrations/react/#onchange
            this.initForm = false;
            return;
        }
        const newJsonData = JSON.stringify(_newData.data);
        const action = new BPMNApplyPropertiesUpdateOperation(this.selectedElementId, newJsonData);
        this.actionDispatcher.dispatch(action);
    }
}

/**
 * This action is send after a property change to the backend providing the ID and the new value
 */
export class BPMNApplyPropertiesUpdateOperation implements Action {
    static readonly KIND = 'applyBPMNPropertiesUpdate';
    readonly kind = BPMNApplyPropertiesUpdateOperation.KIND;
    constructor(readonly id: string, readonly jsonData: string) {}
}

export function createIcon(codiconId: string): HTMLElement {
    const icon = document.createElement('i');
    icon.classList.add(...codiconCSSClasses(codiconId));
    return icon;
}

export class BPMNPropertyPanelAction implements Action {
  static readonly KIND = "BPMNPropertyPanelAction";
  kind = BPMNPropertyPanelAction.KIND;
  constructor(public readonly additionalInformation: string) {}
}

@injectable()
export class BPMNPropertyMouseListener extends MouseListener {
	@inject(TYPES.IActionDispatcher) 
	protected actionDispatcher: GLSPActionDispatcher;
	
//	@inject(BPMNPropertyPanel)
//  protected readonly proprtyPanel: BPMNPropertyPanel;
    
    override doubleClick(target: SModelElement, event: MouseEvent): (Action | Promise<Action>)[] {
        console.log("...we have a double click event!");
        // TODO your implementation
        this.actionDispatcher.dispatch(new BPMNPropertyPanelAction("toggle"));
        // this can return an action or get other services injected and call them on double-click
        return [];
    }
}
