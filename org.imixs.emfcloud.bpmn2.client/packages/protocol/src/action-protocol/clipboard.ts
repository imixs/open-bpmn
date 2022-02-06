/********************************************************************************
 * Copyright (c) 2021 STMicroelectronics and others.
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

import { isObject, isString } from '../utils/typeguard-util';
import { generateRequestId, isActionKind, Operation, RequestAction, ResponseAction } from './base-protocol';
import { EditorContext } from './types';

/**
 * Requests the clipboard data for the current editor context, i.e., the selected elements, in a clipboard-compatible format.
 */
export class RequestClipboardDataAction implements RequestAction<SetClipboardDataAction> {
    static readonly KIND = 'requestClipboardData';

    constructor(
        public readonly editorContext: EditorContext,
        public readonly requestId: string = generateRequestId(),
        public readonly kind: string = RequestClipboardDataAction.KIND
    ) {}

    static create(editorContext: EditorContext): RequestAction<SetClipboardDataAction> {
        return new RequestClipboardDataAction(editorContext);
    }
}

export function isRequestClipboardDataAction(action: any): action is RequestClipboardDataAction {
    return isActionKind(action, RequestClipboardDataAction.KIND) && isObject(action, 'editorContext') && isString(action, 'requestId');
}

/**
 * Server response to a {@link RequestClipboardDataAction} containing the selected elements as clipboard-compatible format.
 */
export class SetClipboardDataAction implements ResponseAction {
    static readonly KIND = 'setClipboardData';

    constructor(
        public readonly clipboardData: ClipboardData,
        public readonly responseId: string = '',
        public readonly kind: string = SetClipboardDataAction.KIND
    ) {}
}

export function isSetClipboardDataAction(action: any): action is SetClipboardDataAction {
    return isActionKind(action, SetClipboardDataAction.KIND) && isObject(action, 'clipboardData') && isString(action, 'responseId');
}

/**
 * Requests a cut operation from the server, i.e., deleting the selected elements from the model. Before submitting a `CutOperation`
 * a client should ensure that the cut elements are put into the clipboard.
 */
export class CutOperation implements Operation {
    static readonly KIND = 'cut';

    constructor(public readonly editorContext: EditorContext, public readonly kind: string = CutOperation.KIND) {}
}

export function isCutOperation(action: any): action is CutOperation {
    return isActionKind(action, CutOperation.KIND) && isObject(action, 'editorContext');
}

/**
 * Requests a paste operation from the server by providing the current clipboard data. Typically this means that elements should be created
 *  based on the data in the clipboard.
 */
export class PasteOperation implements Operation {
    static readonly KIND = 'paste';

    constructor(
        public readonly clipboardData: ClipboardData,
        public readonly editorContext: EditorContext,
        public readonly kind: string = PasteOperation.KIND
    ) {}
}

export function isPasteOperation(action: any): action is PasteOperation {
    return isActionKind(action, PasteOperation.KIND) && isObject(action, 'clipboardData') && isObject(action, 'editorContext');
}

export interface ClipboardData {
    [format: string]: string;
}
