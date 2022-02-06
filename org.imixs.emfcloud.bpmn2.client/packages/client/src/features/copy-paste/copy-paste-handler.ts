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
import { ClipboardData, CutOperation, PasteOperation, RequestClipboardDataAction, SetClipboardDataAction } from '@eclipse-glsp/protocol';
import { inject, injectable } from 'inversify';
import { TYPES, ViewerOptions } from 'sprotty';
import { v4 as uuid } from 'uuid';
import { GLSPActionDispatcher } from '../../base/action-dispatcher';
import { EditorContextService } from '../../base/editor-context-service';
import { GLSP_TYPES } from '../../base/types';

export interface ICopyPasteHandler {
    handleCopy(event: ClipboardEvent): void;
    handleCut(event: ClipboardEvent): void;
    handlePaste(event: ClipboardEvent): void;
}

export interface IAsyncClipboardService {
    clear(): void;
    put(data: ClipboardData, id?: string): void;
    get(id?: string): ClipboardData | undefined;
}

/**
 * A local implementation of the async clipboard interface.
 *
 * This implementation just stores the clipboard data in memory, but not in the clipboard.
 * This implementation can be used if you don't need to support cross-widget/browser/application
 * data transfer and you would like to avoid to require the permission of the user for accessing the
 * system clipboard asynchronously.
 *
 * In order to detect whether the user copied something else since we recorded the clipboard data
 * we put a uuid into the system clipboard synchronously. If on paste this ID has changed or is not
 * available anymore, we know that the user copied in another application or context, so we shouldn't
 * paste what we have stored locally and just return undefined.
 *
 * Real async clipboard service implementations can just ignore the ID that is passed and rely on the
 * system clipboard's content instead.
 */
@injectable()
export class LocalClipboardService implements IAsyncClipboardService {
    protected currentId?: string;
    protected data?: ClipboardData;

    clear(): void {
        this.currentId = undefined;
        this.data = undefined;
    }

    put(data: ClipboardData, id: string): void {
        this.currentId = id;
        this.data = data;
    }

    get(id?: string): ClipboardData | undefined {
        if (id !== this.currentId) {
            return undefined;
        }
        return this.data;
    }
}

interface ClipboardId {
    readonly clipboardId: string;
}

function toClipboardId(clipboardId: string): string {
    return JSON.stringify({ clipboardId });
}

function isClipboardId(jsonData: any): jsonData is ClipboardId {
    return jsonData !== undefined && 'clipboardId' in jsonData;
}

function getClipboardIdFromDataTransfer(dataTransfer: DataTransfer): string | undefined {
    const jsonString = dataTransfer.getData(CLIPBOARD_DATA_FORMAT);
    const jsonObject = jsonString ? JSON.parse(jsonString) : undefined;
    return isClipboardId(jsonObject) ? jsonObject.clipboardId : undefined;
}

const CLIPBOARD_DATA_FORMAT = 'text/plain';

@injectable()
export class ServerCopyPasteHandler implements ICopyPasteHandler {
    @inject(TYPES.IActionDispatcher) protected actionDispatcher: GLSPActionDispatcher;
    @inject(TYPES.ViewerOptions) protected viewerOptions: ViewerOptions;
    @inject(GLSP_TYPES.IAsyncClipboardService) protected clipboadService: IAsyncClipboardService;
    @inject(EditorContextService) protected editorContext: EditorContextService;

    handleCopy(event: ClipboardEvent): void {
        if (event.clipboardData && this.shouldCopy(event)) {
            const clipboardId = uuid();
            event.clipboardData.setData(CLIPBOARD_DATA_FORMAT, toClipboardId(clipboardId));
            this.actionDispatcher
                .request<SetClipboardDataAction>(RequestClipboardDataAction.create(this.editorContext.get()))
                .then(action => this.clipboadService.put(action.clipboardData, clipboardId));
            event.preventDefault();
        } else {
            if (event.clipboardData) {
                event.clipboardData.clearData();
            }
            this.clipboadService.clear();
        }
    }

    handleCut(event: ClipboardEvent): void {
        if (event.clipboardData && this.shouldCopy(event)) {
            this.handleCopy(event);
            this.actionDispatcher.dispatch(new CutOperation(this.editorContext.get()));
            event.preventDefault();
        }
    }

    handlePaste(event: ClipboardEvent): void {
        if (event.clipboardData && this.shouldPaste(event)) {
            const clipboardId = getClipboardIdFromDataTransfer(event.clipboardData);
            const clipboardData = this.clipboadService.get(clipboardId);
            if (clipboardData) {
                this.actionDispatcher.dispatch(new PasteOperation(clipboardData, this.editorContext.get()));
            }
            event.preventDefault();
        }
    }

    protected shouldCopy(_event: ClipboardEvent): boolean | null {
        return this.editorContext.get().selectedElementIds.length > 0 && this.isDiagramActive();
    }

    protected shouldPaste(_event: ClipboardEvent): boolean | null {
        return this.isDiagramActive();
    }

    private isDiagramActive(): boolean | null {
        return (
            document.activeElement instanceof SVGElement &&
            document.activeElement.parentElement &&
            document.activeElement.parentElement.id === this.viewerOptions.baseDiv
        );
    }
}
