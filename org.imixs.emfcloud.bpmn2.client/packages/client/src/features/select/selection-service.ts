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
import { Action, distinctAdd, remove, SelectAction, SelectAllAction } from '@eclipse-glsp/protocol';
import { inject, injectable, multiInject, optional } from 'inversify';
import {
    Command,
    CommandExecutionContext,
    ILogger,
    isSelectable,
    isSelected,
    SChildElement,
    SelectAllCommand as SprottySelectAllCommand,
    SelectCommand as SprottySelectCommand,
    SModelElement,
    SModelRoot,
    SParentElement,
    TYPES
} from 'sprotty';
import { SModelRootListener } from '../../base/model/update-model-command';
import { GLSP_TYPES } from '../../base/types';
import { IFeedbackActionDispatcher } from '../tool-feedback/feedback-action-dispatcher';
import { SelectFeedbackAction } from './select-feedback-action';

export interface SelectionListener {
    selectionChanged(root: Readonly<SModelRoot>, selectedElements: string[]): void;
}

@injectable()
export class SelectionService implements SModelRootListener {
    private root: Readonly<SModelRoot>;
    private selectedElementIDs: Set<string> = new Set();

    @inject(GLSP_TYPES.IFeedbackActionDispatcher) protected feedbackDispatcher: IFeedbackActionDispatcher;
    @inject(TYPES.ILogger) protected logger: ILogger;

    constructor(@multiInject(GLSP_TYPES.SelectionListener) @optional() protected selectionListeners: SelectionListener[] = []) {}

    register(selectionListener: SelectionListener): void {
        distinctAdd(this.selectionListeners, selectionListener);
    }

    deregister(selectionListener: SelectionListener): void {
        remove(this.selectionListeners, selectionListener);
    }

    modelRootChanged(root: Readonly<SModelRoot>): void {
        this.updateSelection(root, [], []);
    }

    updateSelection(root: Readonly<SModelRoot>, select: string[], deselect: string[]): void {
        if (root === undefined && select.length === 0 && deselect.length === 0) {
            return;
        }
        const prevRoot = this.root;
        const prevSelectedElementIDs = new Set(this.selectedElementIDs);

        // update root
        this.root = root;

        // update selected element IDs and collect deselected elements
        // - select all elements that are not deselected at the same time (no-op)
        // - deselect all elements that are not selected at the same time (no-op) but was selected
        const toSelect = [...select].filter(selectId => deselect.indexOf(selectId) === -1);
        const toDeselect = [...deselect].filter(deselectId => select.indexOf(deselectId) === -1 && this.selectedElementIDs.has(deselectId));
        for (const id of toDeselect) {
            this.selectedElementIDs.delete(id);
        }
        for (const id of toSelect) {
            this.selectedElementIDs.add(id);
        }

        const deselectedElementIDs = new Set(toDeselect);
        // see if selected elements still exist in the updated root
        for (const id of this.selectedElementIDs) {
            const element = root.index.getById(id);
            if (element === undefined) {
                this.selectedElementIDs.delete(id);
                if (prevRoot !== undefined && prevRoot.index.getById(id)) {
                    deselectedElementIDs.add(id);
                }
            }
        }

        // only send out changes if there actually are changes, i.e., the root or the selected elements changed
        const selectionChanged =
            prevSelectedElementIDs.size !== this.selectedElementIDs.size ||
            ![...prevSelectedElementIDs].every(value => this.selectedElementIDs.has(value));
        if (selectionChanged) {
            // aggregate to feedback action handling all elements as only the last feedback is restored
            this.dispatchFeedback([new SelectFeedbackAction([...this.selectedElementIDs], [...deselectedElementIDs])]);
        }

        const rootChanged = prevRoot !== root;
        if (rootChanged || selectionChanged) {
            // notify listeners after the feedback action
            this.notifyListeners(this.root, this.selectedElementIDs);
        }
    }

    dispatchFeedback(actions: Action[]): void {
        this.feedbackDispatcher.registerFeedback(this, actions);
    }

    notifyListeners(root: SModelRoot, selectedElementIDs: Set<string>): void {
        this.selectionListeners.forEach(listener => listener.selectionChanged(root, Array.from(selectedElementIDs)));
    }

    getModelRoot(): Readonly<SModelRoot> {
        return this.root;
    }

    getSelectedElements(): Readonly<SModelElement>[] {
        return Array.from(this.root.index.all().filter(isSelected));
    }

    /**
     * QUERY METHODS
     */

    getSelectedElementIDs(): Set<string> {
        return this.selectedElementIDs;
    }

    hasSelectedElements(): boolean {
        return this.selectedElementIDs.size > 0;
    }

    isSingleSelection(): boolean {
        return this.selectedElementIDs.size === 1;
    }

    isMultiSelection(): boolean {
        return this.selectedElementIDs.size > 1;
    }
}

@injectable()
export class SelectCommand extends Command {
    static readonly KIND = SprottySelectCommand.KIND;

    protected selected: SModelElement[] = [];
    protected deselected: SModelElement[] = [];

    constructor(
        @inject(TYPES.Action) public action: SelectAction,
        @inject(GLSP_TYPES.SelectionService) public selectionService: SelectionService
    ) {
        super();
    }

    execute(context: CommandExecutionContext): SModelRoot {
        const model = context.root;
        this.action.selectedElementsIDs.forEach(id => {
            const element = model.index.getById(id);
            if (element instanceof SChildElement && isSelectable(element)) {
                this.selected.push(element);
            }
        });
        this.action.deselectedElementsIDs.forEach(id => {
            const element = model.index.getById(id);
            if (element instanceof SChildElement && isSelectable(element)) {
                this.deselected.push(element);
            }
        });
        return this.redo(context);
    }

    undo(context: CommandExecutionContext): SModelRoot {
        const select = this.deselected.map(element => element.id);
        const deselect = this.selected.map(element => element.id);
        this.selectionService.updateSelection(context.root, select, deselect);
        return context.root;
    }

    redo(context: CommandExecutionContext): SModelRoot {
        const select = this.selected.map(element => element.id);
        const deselect = this.deselected.map(element => element.id);
        this.selectionService.updateSelection(context.root, select, deselect);
        return context.root;
    }
}

@injectable()
export class SelectAllCommand extends Command {
    static readonly KIND = SprottySelectAllCommand.KIND;
    protected previousSelection: Map<string, boolean> = new Map<string, boolean>();

    constructor(
        @inject(TYPES.Action) public action: SelectAllAction,
        @inject(GLSP_TYPES.SelectionService) public selectionService: SelectionService
    ) {
        super();
    }

    execute(context: CommandExecutionContext): SModelRoot {
        return this.redo(context);
    }

    undo(context: CommandExecutionContext): SModelRoot {
        const index = context.root.index;
        for (const previousState of this.previousSelection) {
            const element = index.getById(previousState[0]);
            if (element !== undefined && isSelectable(element)) {
                element.selected = previousState[1];
            }
        }
        return context.root;
    }

    redo(context: CommandExecutionContext): SModelRoot {
        const selectables: string[] = [];
        this.selectAll(context.root, this.action.select, selectables);
        if (this.action.select) {
            this.selectionService.updateSelection(context.root, selectables, []);
        } else {
            this.selectionService.updateSelection(context.root, [], selectables);
        }
        return context.root;
    }

    protected selectAll(element: SParentElement, newState: boolean, selected: string[]): void {
        if (isSelectable(element)) {
            this.previousSelection.set(element.id, element.selected);
            selected.push(element.id);
        }
        for (const child of element.children) {
            this.selectAll(child, newState, selected);
        }
    }
}
