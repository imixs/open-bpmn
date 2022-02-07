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
import { isArray, isObject, isString } from '../utils/typeguard-util';
import { generateRequestId, isActionKind, Operation, RequestAction, ResponseAction } from './base-protocol';
import { SModelRootSchema } from './model-structure';
import { ElementAndAlignment, ElementAndBounds } from './types';

/** Sent from the server to the client to request bounds for the given model. The model is rendered invisibly so the bounds can
 * derived from the DOM. The response is a ComputedBoundsAction. This hidden rendering round-trip is necessary if the client is responsible
 * for parts of the layout.
 */
export class RequestBoundsAction implements RequestAction<ComputedBoundsAction> {
    static readonly KIND: string = 'requestBounds';
    readonly kind = RequestBoundsAction.KIND;

    constructor(public readonly newRoot: SModelRootSchema, public readonly requestId: string = '') {}

    /** Factory function to dispatch a request with the `IActionDispatcher` */
    static create(newRoot: SModelRootSchema): RequestAction<ComputedBoundsAction> {
        return new RequestBoundsAction(newRoot, generateRequestId());
    }
}

export function isRequestBoundsAction(action: any): action is RequestBoundsAction {
    return isActionKind(action, RequestBoundsAction.KIND) && isObject(action, 'newRoot') && isString(action, 'requestId');
}

/**
 * Sent from the client to the server to transmit the result of bounds computation as a response to a RequestBoundsAction. If the server is
 *  responsible for parts of the layout, it can do so after applying the computed bounds received with this action. Otherwise there is no
 * need to send the computed bounds to the server, so they can be processed locally by the client.
 */
export class ComputedBoundsAction implements ResponseAction {
    static readonly KIND = 'computedBounds';
    readonly kind = ComputedBoundsAction.KIND;

    constructor(
        public readonly bounds: ElementAndBounds[],
        public readonly revision?: number,
        public readonly alignments?: ElementAndAlignment[],
        public readonly responseId = ''
    ) {}
}

export function isComputedBoundsAction(action: any): action is ComputedBoundsAction {
    return isActionKind(action, ComputedBoundsAction.KIND) && isArray(action, 'bounds') && isString(action, 'responseId');
}

/**
 * Request a layout of the diagram or the selected elements only.
 */
export class LayoutOperation implements Operation {
    static readonly KIND = 'layout';

    constructor(public readonly elementIds: string[], public readonly kind = LayoutOperation.KIND) {}
}

export function isLayoutOperation(action: any): action is LayoutOperation {
    return isActionKind(action, LayoutOperation.KIND) && isArray(action, 'elementIds');
}
