/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
import { Action, Args, distinctAdd, EditMode, EditorContext, isSetEditModeAction, remove } from '@eclipse-glsp/protocol';
import { inject, injectable, multiInject, optional } from 'inversify';
import { IActionHandler, ModelSource, MousePositionTracker, SModelElement, SModelRoot, TYPES } from 'sprotty';
import { SelectionService } from '../features/select/selection-service';
import { isSourceUriAware } from './source-uri-aware';
import { GLSP_TYPES } from './types';

export interface EditModeListener {
    editModeChanged(newValue: string, oldvalue: string): void;
}

@injectable()
export class EditorContextService implements IActionHandler {
    @inject(GLSP_TYPES.SelectionService) protected selectionService: SelectionService;
    @inject(MousePositionTracker) protected mousePositionTracker: MousePositionTracker;
    @inject(TYPES.ModelSourceProvider) protected modelSource: () => Promise<ModelSource>;
    protected _editMode: string;

    constructor(@multiInject(GLSP_TYPES.IEditModeListener) @optional() protected editModeListeners: EditModeListener[] = []) {}

    register(editModeListener: EditModeListener): void {
        distinctAdd(this.editModeListeners, editModeListener);
    }

    deregister(editModeListener: EditModeListener): void {
        remove(this.editModeListeners, editModeListener);
    }

    get(args?: Args): EditorContext {
        return {
            selectedElementIds: Array.from(this.selectionService.getSelectedElementIDs()),
            lastMousePosition: this.mousePositionTracker.lastPositionOnDiagram,
            args
        };
    }

    getWithSelection(selectedElementIds: string[], args?: Args): EditorContext {
        return {
            selectedElementIds,
            lastMousePosition: this.mousePositionTracker.lastPositionOnDiagram,
            args
        };
    }

    handle(action: Action): void {
        if (isSetEditModeAction(action)) {
            const oldValue = this._editMode;
            this._editMode = action.editMode;
            this.notifiyEditModeListeners(oldValue);
        }
    }

    protected notifiyEditModeListeners(oldValue: string): void {
        this.editModeListeners.forEach(listener => listener.editModeChanged(oldValue, this.editMode));
    }

    async getSourceUri(): Promise<string | undefined> {
        const modelSource = await this.modelSource();
        if (isSourceUriAware(modelSource)) {
            return modelSource.getSourceURI();
        }
        return undefined;
    }

    get editMode(): string {
        return this._editMode;
    }

    get modelRoot(): Readonly<SModelRoot> {
        return this.selectionService.getModelRoot();
    }

    get selectedElements(): Readonly<SModelElement>[] {
        return this.selectionService.getSelectedElements();
    }

    get isReadonly(): boolean {
        return this.editMode === EditMode.READONLY;
    }
}

export type EditorContextServiceProvider = () => Promise<EditorContextService>;
