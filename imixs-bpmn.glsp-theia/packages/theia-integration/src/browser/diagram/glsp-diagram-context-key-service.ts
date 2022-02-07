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
import {
    EditMode,
    EditModeListener,
    EditorContextService,
    GLSP_TYPES,
    isDeletable,
    isMoveable,
    isNotUndefined,
    SModelElement,
    SModelRoot
} from '@eclipse-glsp/client';
import { SelectionService } from '@eclipse-glsp/client/lib/features/select/selection-service';
import { ApplicationShell } from '@theia/core/lib/browser';
import { ContextKey, ContextKeyService } from '@theia/core/lib/browser/context-key-service';
import { inject, injectable, postConstruct } from '@theia/core/shared/inversify';
import { isDiagramWidgetContainer } from 'sprotty-theia';
import { GLSPDiagramWidget } from './glsp-diagram-widget';

@injectable()
export abstract class AbstractGLSPDiagramContextKeyService {
    @inject(ApplicationShell)
    protected readonly shell: ApplicationShell;

    @inject(ContextKeyService)
    protected readonly contextKeyService: ContextKeyService;

    protected currentSelectionService: SelectionService | undefined;
    protected currentEditorContextService: EditorContextService | undefined;

    protected readonly selectionChangeListener = {
        selectionChanged: (root: Readonly<SModelRoot>, selectedElements: string[]) =>
            // eslint-disable-next-line no-invalid-this
            this.updateSelectionContextKeys(root, selectedElements)
    };

    protected readonly editModeChangeListener = {
        // eslint-disable-next-line no-invalid-this
        editModeChanged: (oldValue, newValue) => this.doUpdateEditModeContextKeys(newValue)
    } as EditModeListener;

    @postConstruct()
    protected init(): void {
        this.registerContextKeys();
        this.updateContextKeys();
        this.shell.onDidChangeActiveWidget(() => this.updateContextKeys());
    }

    protected updateContextKeys(): void {
        if (this.currentSelectionService) {
            this.currentSelectionService.deregister(this.selectionChangeListener);
        }
        if (this.currentEditorContextService) {
            this.currentEditorContextService.deregister(this.editModeChangeListener);
        }
        const glspDiagramWidget = this.getDiagramWidget();
        if (glspDiagramWidget) {
            this.doUpdateStaticContextKeys(glspDiagramWidget);
            this.currentSelectionService = this.getSelectionService(glspDiagramWidget);
            this.currentSelectionService.register(this.selectionChangeListener);
            this.updateSelectionContextKeys(
                this.currentSelectionService.getModelRoot(),
                Array.from(this.currentSelectionService.getSelectedElementIDs())
            );
            this.currentEditorContextService = this.getEditorContextService(glspDiagramWidget);
            this.currentEditorContextService.register(this.editModeChangeListener);
            this.doUpdateEditModeContextKeys(this.currentEditorContextService.editMode);
        } else {
            this.resetContextKeys();
        }
    }

    protected updateSelectionContextKeys(root: Readonly<SModelRoot>, selectedElementIds: string[]): void {
        if (selectedElementIds.length < 1) {
            this.doResetSelectionContextKeys();
            return;
        }
        this.doUpdateSelectionContextKeys(selectedElementIds.map(id => root.index.getById(id)).filter(isNotUndefined));
    }

    protected getSelectionService(glspDiagramWidget: GLSPDiagramWidget): SelectionService {
        return glspDiagramWidget.diContainer.get(GLSP_TYPES.SelectionService);
    }

    protected getEditorContextService(glspDiagramWidget: GLSPDiagramWidget): EditorContextService {
        return glspDiagramWidget.diContainer.get(EditorContextService);
    }

    protected getDiagramWidget(): GLSPDiagramWidget | undefined {
        const widget = this.shell.activeWidget || this.shell.currentWidget;
        if (widget instanceof GLSPDiagramWidget) {
            return widget as GLSPDiagramWidget;
        } else if (isDiagramWidgetContainer(widget) && widget.diagramWidget instanceof GLSPDiagramWidget) {
            return widget.diagramWidget as GLSPDiagramWidget;
        }
        return undefined;
    }

    protected resetContextKeys(): void {
        this.doResetStaticContextKeys();
        this.doResetSelectionContextKeys();
        this.doResetEditModeContextKeys();
    }

    protected abstract registerContextKeys(): void;
    protected abstract doUpdateStaticContextKeys(glspDiagramWidget: GLSPDiagramWidget): void;
    protected abstract doResetStaticContextKeys(): void;
    protected abstract doUpdateSelectionContextKeys(selectedElements: SModelElement[]): void;
    protected abstract doResetSelectionContextKeys(): void;
    protected abstract doUpdateEditModeContextKeys(editMode: string): void;
    protected abstract doResetEditModeContextKeys(): void;
}

export class GLSPDiagramContextKeyService extends AbstractGLSPDiagramContextKeyService {
    protected _glspEditorFocus: ContextKey<boolean>;
    get glspEditorFocus(): ContextKey<boolean> {
        return this._glspEditorFocus;
    }

    protected _glspEditorDiagramType: ContextKey<string>;
    get glspEditorDiagramType(): ContextKey<string> {
        return this._glspEditorDiagramType;
    }

    protected _glspEditorHasSelection: ContextKey<boolean>;
    get glspEditorHasSelection(): ContextKey<boolean> {
        return this._glspEditorHasSelection;
    }

    protected _glspEditorHasSelectionOfType: ContextKey<string>;
    get glspEditorHasSelectionOfType(): ContextKey<string> {
        return this._glspEditorHasSelectionOfType;
    }

    protected _glspEditorHasMultipleSelection: ContextKey<boolean>;
    get glspEditorHasMultipleSelection(): ContextKey<boolean> {
        return this._glspEditorHasMultipleSelection;
    }

    protected _glspEditorHasDeletableSelection: ContextKey<boolean>;
    get glspEditorHasDeletableSelection(): ContextKey<boolean> {
        return this._glspEditorHasDeletableSelection;
    }

    protected _glspEditorHasMoveableSelection: ContextKey<boolean>;
    get glspEditorHasMoveableSelection(): ContextKey<boolean> {
        return this._glspEditorHasMoveableSelection;
    }

    protected _glspEditorIsReadonly: ContextKey<boolean>;
    get glspEditorIsReadonly(): ContextKey<boolean> {
        return this._glspEditorIsReadonly;
    }

    protected registerContextKeys(): void {
        this._glspEditorFocus = this.contextKeyService.createKey<boolean>('glspEditorFocus', false);
        this._glspEditorDiagramType = this.contextKeyService.createKey<string>('glspEditorDiagramType', undefined);
        this._glspEditorHasSelection = this.contextKeyService.createKey<boolean>('glspEditorHasSelection', false);
        this._glspEditorHasSelectionOfType = this.contextKeyService.createKey<string>('glspEditorHasSelectionOfType', undefined);
        this._glspEditorHasMultipleSelection = this.contextKeyService.createKey<boolean>('glspEditorHasMultipleSelection', false);
        this._glspEditorHasDeletableSelection = this.contextKeyService.createKey<boolean>('glspEditorHasDeletableSelection', false);
        this._glspEditorHasMoveableSelection = this.contextKeyService.createKey<boolean>('glspEditorHasMoveableSelection', false);
        this._glspEditorIsReadonly = this.contextKeyService.createKey<boolean>('glspEditorIsReadonly', false);
    }

    protected doUpdateStaticContextKeys(glspDiagramWidget: GLSPDiagramWidget): void {
        this.glspEditorFocus.set(true);
        this.glspEditorDiagramType.set(glspDiagramWidget.diagramType);
    }

    protected doResetStaticContextKeys(): void {
        this.glspEditorFocus.reset();
        this.glspEditorDiagramType.reset();
    }

    protected doUpdateSelectionContextKeys(selectedElements: SModelElement[]): void {
        this.glspEditorHasSelection.set(true);
        this.glspEditorHasMultipleSelection.set(selectedElements.length > 1);
        this.glspEditorHasDeletableSelection.set(selectedElements.filter(isDeletable).length > 0);
        this.glspEditorHasMoveableSelection.set(selectedElements.filter(isMoveable).length > 0);
        if (selectedElements.length === 1 && selectedElements[0]) {
            this.glspEditorHasSelectionOfType.set(selectedElements[0].type);
        }
    }

    protected doUpdateEditModeContextKeys(editMode: string): void {
        this.glspEditorIsReadonly.set(editMode === EditMode.READONLY);
    }

    protected doResetSelectionContextKeys(): void {
        this.glspEditorHasDeletableSelection.reset();
        this.glspEditorHasMoveableSelection.reset();
        this.glspEditorHasMultipleSelection.reset();
        this.glspEditorHasSelection.reset();
        this.glspEditorHasSelectionOfType.reset();
    }

    protected doResetEditModeContextKeys(): void {
        this.glspEditorIsReadonly.reset();
    }
}
