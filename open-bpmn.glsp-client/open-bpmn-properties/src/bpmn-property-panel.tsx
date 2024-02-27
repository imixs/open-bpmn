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
    EnableDefaultToolsAction,
    EnableToolsAction,
    GModelElement,
    GModelRoot,
    IActionDispatcher,
    IActionHandler,
    ICommand,
    IDiagramStartup,
    ISelectionListener,
    MaybePromise,
    MouseListener,
    Operation,
    SetUIExtensionVisibilityAction,
    TYPES,
    hasArgs
} from '@eclipse-glsp/client';
import { Action, Args } from '@eclipse-glsp/protocol';
import { JsonForms } from '@jsonforms/react';
import { vanillaCells, vanillaRenderers } from '@jsonforms/vanilla-renderers';
import { isBPMNEdge, isBPMNNode, isBoundaryEvent } from '@open-bpmn/open-bpmn-model';
import { inject, injectable } from 'inversify';
import * as React from 'react';
import { createRoot } from 'react-dom/client';
import { codiconCSSClasses } from 'sprotty/lib/utils/codicon';
import { SelectItemComboRendererEntry, SelectItemRendererEntry } from './SelectItemControl';
import { TextFileEditorRendererEntry } from './TextFileEditorControl';

/**
 * The BPMNPropertyPanel allows to edit different properties of a selected BPMN element.
 *
 * The property Panel sends an {@link BPMNApplyPropertiesUpdateOperation} in case the user edits data
 * of an element.
 *
 * The Server sends back an optional {@link BPMNPropertiesUpdateAction}  if the structure of the model
 * has changed. For example this happens in case a list element like a lane or a signal definition was
 * created on the server side
 */
// eslint-disable-next-line @typescript-eslint/explicit-function-return-type
@injectable()
export class BPMNPropertyPanel extends AbstractUIExtension implements IDiagramStartup, ISelectionListener, IActionHandler {
    static readonly ID = 'bpmn-property-panel';
    protected headerDiv: HTMLElement;
    modelRoot: Readonly<GModelRoot>;
    headerTitle: HTMLElement;
    protected bodyDiv: HTMLElement;
    protected panelContainer: any;
    selectedElementId: string;
    initForm: boolean;
    noUpdate: boolean;
    lastCategory: string;
    isResizing: boolean;
    panelToggle: boolean;
    currentY: number;

    @inject(TYPES.IActionDispatcher)
    protected readonly actionDispatcher: IActionDispatcher;

    override id(): string {
        return BPMNPropertyPanel.ID;
    }
    override containerClass(): string {
        return BPMNPropertyPanel.ID;
    }

    // Set enableOnStartup to true
    enableOnStartup: boolean = true;

    /**
     * This method is called after the diagram model is fully initialized.
     * This is the moment to render the HTML element.
     *
     * See also: https://github.com/eclipse-glsp/glsp/discussions/1273
     */
    postModelInitialization(): MaybePromise<void> {
        this.actionDispatcher.dispatch(SetUIExtensionVisibilityAction.create(
            { extensionId: BPMNPropertyPanel.ID, visible: true, contextElementsId: [] }));
    }

    /**
     * Updates the containerElement under the given context before it becomes visible.
     * We override this method to preselect the root element as a default selection.
     *
     * @param _containerElement
     * @param root
     */
    protected override onBeforeShow(_containerElement: HTMLElement, root: Readonly<GModelRoot>): void {
        this.modelRoot = root;
        if (!root) {
            // no op
        } else {
            // preselect the root element showing the diagram properties
            this.selectionChanged(root, []);
        }
    }

    /*
     * Initialize the elements of property panel
     */
    protected override initializeContents(_containerElement: HTMLElement): void {
        this.createHeader();
        this.createBody();
    }

    /*
     * This method creates a header bar that can be grabbed with the mouse
     * to resize the height of the property panel.
     */
    protected createHeader(): void {
        const headerCompartment = document.createElement('div');
        headerCompartment.classList.add('bpmn-property-header');
        headerCompartment.append(this.createHeaderTitle());
        headerCompartment.appendChild(this.createHeaderTools());
        this.containerElement.appendChild(headerCompartment);
        this.headerDiv = headerCompartment;

        // eslint-disable-next-line arrow-parens
        this.headerDiv.addEventListener('mousedown', (e) => {
            this.isResizing = true;
            this.currentY = e.clientY;
        });
        // eslint-disable-next-line arrow-parens
        this.headerDiv.addEventListener('mouseup', (e) => {
            this.isResizing = false;
        });
        // eslint-disable-next-line arrow-parens
        window.addEventListener('mousemove', (e) => {
            if (!this.isResizing) {
                return;
            }
            const parent = this.containerElement.parentElement;
            let _newheight = this.containerElement.offsetHeight - (e.clientY - this.currentY);
            // fix minheigt
            if (_newheight < 28) {
                _newheight = 28;
                this.isResizing = false;
            }
            // fix maxheight
            if (parent && _newheight > parent.offsetHeight - 28) {
                _newheight = parent.offsetHeight - 28;
                this.isResizing = false;
            }
            this.containerElement.style.height = `${_newheight}px`;
            this.currentY = e.clientY;

            // if the mouse is no longer within the diagram plane, we stop the resizing
            if (parent && !parent.matches(':hover')) {
                this.isResizing = false;
            }
        });
    }

    protected createBody(): void {
        const bodyDiv = document.createElement('div');
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
     * This method creates the header tool buttons to control the size of the panel
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

        const maximizeToolButton = createIcon('chevron-up');
        maximizeToolButton.title = 'Maximize Property Panel';
        maximizeToolButton.onclick = this.onClickResizePanel(maximizeToolButton, 'TOOL_COMMAND_MAXIMIZE');
        headerTools.appendChild(maximizeToolButton);

        return headerTools;
    }

    /**
     * Helper method to resize the property panel
     *
     * @param button
     * @param toolId
     * @returns
     */
    protected onClickResizePanel(button: HTMLElement, toolId?: string) {
        return (_ev: MouseEvent) => {
            const action = toolId ? EnableToolsAction.create([toolId]) : EnableDefaultToolsAction.create();
            this.actionDispatcher.dispatch(action);
            if (toolId === 'TOOL_COMMAND_MAXIMIZE') {
                this.containerElement.style.height = '50%';
            }
            if (toolId === 'TOOL_COMMAND_HIDE') {
                this.containerElement.style.height = '28px';
            }
            // restore the default actions in the diagram panel (like move elements)
            this.actionDispatcher.dispatch(EnableDefaultToolsAction.create());
        };
    }

    /*
     * We react on different Action events.
     *
     * The EnableToolPaletteAction is used to make the property panel visible
     * If we already have the bodyDiv defined, than we skip this event.
     * See Discussion: https://github.com/eclipse-glsp/glsp/discussions/910
     *
     * The BPMNPropertiesToggleAction is used to show/hide the
     * propertyPanel. This action is called by context menus.
     *
     * The BPMNPropertiesUpdateAction is send by the server during an update
     * of the data state and indicates that the property panel need to be refreshed.
     */
    handle(action: Action): ICommand | Action | void {

        // Toggle the property panel. Action is triggered by context menu
        if (action.kind === BPMNPropertiesToggleAction.KIND) {
            if (!this.panelToggle) {
                this.containerElement.style.height = '50%';
            } else {
                this.containerElement.style.height = '28px';
            }
            this.panelToggle = !this.panelToggle;
        }

        // Update content of the property panel. Action is triggered by the server
        if (action.kind === BPMNPropertiesUpdateAction.KIND) {
            if (BPMNPropertiesUpdateAction.is(action)) {
                // we read the new property data directly form the action event
                this.updatePropertyPanel(this.selectedElementId, action.data, action.schema, action.uiSchema);
            }
        }
    }

    /*
     * This method reacts on the selection of a BPMN element. It can handel different
     * situations of a selection and updates the property panel input fields.
     */
    selectionChanged(root: Readonly<GModelRoot>, selectedElements: string[]): void {
        // return if we do not yet have a body DIV.
        if (!this.bodyDiv || !root) {
            return;
        }

        this.modelRoot = root;
        if (selectedElements && selectedElements.length > 0) {
            // first we need to verify if a Symbol/BPMNLabel combination was selected.
            // In this case we are only interested in the BPMNFlowElement and not in the label
            if (selectedElements.length > 1) {
                const filteredArr = selectedElements.filter(val => !val.endsWith('_bpmnlabel'));
                selectedElements = filteredArr;
            }
            // Next verify if we have task/Boundary selection
            // In this case we are only interested in the Task  and not in the Event
            if (selectedElements.length === 2) {
                const element_test = root.index.getById(selectedElements[1]);
                if (element_test && isBoundaryEvent(element_test)) {
                    // remove Boundary event from selection list
                    selectedElements.pop();
                }
            }
        }

        // if no element is selected we default to the root element (diagram plane)
        let _id;
        if (!selectedElements || selectedElements.length === 0) {
            _id = root.id;
        } else if (selectedElements.length === 1) {
            // we  have exactly one element selected.
            _id = selectedElements[0];
        }

        // avoid message loop...
        if (!_id || _id === this.selectedElementId || _id === 'EMPTY') {
            // skip this event!
            return;
        }

        // now if we have an element we show the panel..
        if (_id) {
            // did we have a change?
            // set new selectionId
            this.selectedElementId = _id;
            // because the jsonForms send a onchange event after init we mark this state here
            this.initForm = true;
            // init the react container only once....
            if (!this.panelContainer) {
                this.panelContainer = createRoot(this.bodyDiv);
            }
            // finally update the panel
            this.updatePropertyPanel(_id);
        } else {
            // no single element selected!
            this.selectedElementId = '';
            if (this.bodyDiv && this.panelContainer) {
                this.headerTitle.textContent = 'BPMN Properties';
                // multi selection - we can not show a property panel
                this.panelContainer.render(<React.Fragment>Please select a single element </React.Fragment>);
            }
        }
    }

    /**
     * This helper method is responsible to refresh teh property panel.
     * The method loads the element from the root model context and updates
     * the JsonForms schemata.
     *
     * The method can be called with only an elementID, in that case the method
     * evaluates the form schema data form the GNode element.args.
     * Optional the method can also be called with the data, schema, uiSchema.
     *
     * @param _elementID
     */
    updatePropertyPanel(_elementID: string, _data?: string, _schema?: string, _uiSchema?: string): void {

        // return if we do not yet have a body DIV.
        if (!this.bodyDiv) {
            console.log(' ------ no body div!!');
            return;
        }

        let element: GModelElement | undefined;
        // first we load the element from the model root
        if (this.modelRoot.id === _elementID) {
            element = this.modelRoot;
            this.headerTitle.textContent = 'Default Process';
        } else {
            // load element from index
            element = this.modelRoot.index.getById(_elementID);
            // skip undefined elements or laneDivider
            if (!element || (!isBPMNNode(element) && !isBPMNEdge(element))) {
                element = this.modelRoot;
                this.headerTitle.textContent = 'Default Process';
            } else {
                this.headerTitle.textContent = element.type;
            }
        }

        // Update the property panel if we have a valid element.
        // New data can be provided by optional by the action event.
        // In that case we use this data, otherwise we take it from the GNode.
        if (element) {
            // BPMN Node selected, collect JSONForms schemata....
            let bpmnPropertiesData;
            let bpmnPropertiesSchema;
            let bpmnPropertiesUISchema;
            if (hasArgs(element)) {
                // parse the jsonForms schema details
                if (_data) {
                    bpmnPropertiesData = JSON.parse(_data);
                } else {
                    bpmnPropertiesData = JSON.parse(element.args.JSONFormsData + '');
                }
                if (_schema) {
                    bpmnPropertiesSchema = JSON.parse(_schema);
                } else {
                    bpmnPropertiesSchema = JSON.parse(element.args.JSONFormsSchema + '');
                }
                if (_uiSchema) {
                    bpmnPropertiesUISchema = JSON.parse(_uiSchema);
                } else {
                    bpmnPropertiesUISchema = JSON.parse(element.args.JSONFormsUISchema + '');
                }
            }
            // Build a generic JSONForms Property panel if we have at least an UISchema
            if (bpmnPropertiesUISchema) {
                // list of renderers declared outside the App component
                const bpmnRenderers = [
                    ...vanillaRenderers,
                    // optional register custom renderers...
                    SelectItemRendererEntry,
                    SelectItemComboRendererEntry,
                    TextFileEditorRendererEntry
                ];
                this.noUpdate = true;
                // render JSONForm // vanillaRenderers
                // we also set the key to the current elementID to reinitialize the form panel
                this.panelContainer.render(<JsonForms
                    data={bpmnPropertiesData}
                    schema={bpmnPropertiesSchema}
                    uischema={bpmnPropertiesUISchema}
                    cells={vanillaCells}
                    renderers={bpmnRenderers}
                    onChange={({ errors, data }) => this.setState({ data })}
                    key={this.selectedElementId}
                />);
            } else {
                // we have no UISchema!
                this.selectedElementId = '';
                this.headerTitle.textContent = 'BPMN Properties';
            }
        } else {
            // we have no UISchema!
            this.selectedElementId = '';
            this.headerTitle.textContent = 'BPMN Properties';
        }
    }

    /*
     * This method is responsible to send the new data in a
     * ApplyEditOperation Action to the server....
     */
    setState(_newData: any): void {
        if (this.initForm) {
            // try to pre-select the last category
            const listCategories = this.bodyDiv.querySelectorAll('ul.category-subcategories li');
            for (let i = 0; i < listCategories.length; i++) {
                // Check if the LI element's text content matches our lastCategory
                if (listCategories[i].textContent === this.lastCategory) {
                    // Simulate the click event on the LI element
                    const event = new MouseEvent('click', {
                        view: window,
                        bubbles: true,
                        cancelable: true
                    });
                    listCategories[i].dispatchEvent(event);
                    break;
                }
            }
            // we do not dispatch the first onChange event
            // see https://jsonforms.io/docs/integrations/react/#onchange
            this.initForm = false;
            this.noUpdate = false;
            return;
        }

        if (this.noUpdate) {
            this.noUpdate = false;
            return;
        }
        // figure out the active category
        this.updateLastCategory();
        const newJsonData = JSON.stringify(_newData.data);
        const action = new BPMNPropertiesApplyOperation(this.selectedElementId, newJsonData, this.lastCategory);
        this.actionDispatcher.dispatch(action);
    }

    /**
     * Computes the current selected category
     */
    protected updateLastCategory(): void {
        const selectedListItem = this.bodyDiv.querySelector('ul.category-subcategories li.selected');
        if (selectedListItem && selectedListItem.textContent) {
            this.lastCategory = selectedListItem.textContent;
        }
    }
}

/**
 * This action is send after a property change to the backend providing the ID and the new value
 */
export class BPMNPropertiesApplyOperation implements Operation {
    static readonly KIND = 'BPMNPropertiesApplyOperation';
    readonly kind = BPMNPropertiesApplyOperation.KIND;
    constructor(readonly id: string, readonly jsonData: string, readonly category: string) { }
    isOperation: true;
    args?: Args | undefined;
}

/**
 * Open-BPMN Toggle Property Panel Action
 */
export interface BPMNPropertiesToggleAction extends Action {
    kind: typeof BPMNPropertiesToggleAction.KIND;
}
export namespace BPMNPropertiesToggleAction {
    export const KIND = 'BPMNPropertiesToggleAction';
    export function is(object: any): object is BPMNPropertiesToggleAction {
        return Action.hasKind(object, KIND);
    }
    export function create(): BPMNPropertiesToggleAction {
        return { kind: KIND };
    }
}

/**
 * Open-BPMN Update Property Panel Action
 */
export interface BPMNPropertiesUpdateAction extends Action {
    kind: typeof BPMNPropertiesUpdateAction.KIND;
    data?: string;
    schema?: string;
    uiSchema?: string;
}
export namespace BPMNPropertiesUpdateAction {
    export const KIND = 'BPMNPropertiesUpdateAction';
    export function is(object: any): object is BPMNPropertiesUpdateAction {
        return Action.hasKind(object, KIND);
    }
    export function create(options: { _data?: string, schema?: string, uiSchema?: string }): BPMNPropertiesUpdateAction {
        return {
            kind: KIND,
            ...options
        };
    }
}

/*
 * This mouse listener reacts on double click events and opens/closes
 * the properties panel by creating a BPMNPropertiesToggleAction
 */
@injectable()
export class BPMNPropertiesMouseListener extends MouseListener {
    override doubleClick(target: GModelElement, event: MouseEvent): (Action | Promise<Action>)[] {
        // return a BPMNPropertiesToggleAction...
        return [BPMNPropertiesToggleAction.create()];
    }
}

export function createIcon(codiconId: string): HTMLElement {
    const icon = document.createElement('i');
    icon.classList.add(...codiconCSSClasses(codiconId));
    return icon;
}
