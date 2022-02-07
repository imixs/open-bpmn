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
import { generateRequestId, isActionKind, RequestAction, ResponseAction } from './base-protocol';
import { SModelRootSchema } from './model-structure';
import { Bounds } from './types';

/**
 * Triggered when the user hovers the mouse pointer over an element to get a popup with details on that element.
 * This action is sent from the client to the server. The response is a `SetPopupModelAction`.
 */
export class RequestPopupModelAction implements RequestAction<SetPopupModelAction> {
    static readonly KIND = 'requestPopupModel';
    readonly kind = RequestPopupModelAction.KIND;

    constructor(public readonly elementId: string, public readonly bounds: Bounds, public readonly requestId = '') {}

    /** Factory function to dispatch a request with the `IActionDispatcher` */
    static create(elementId: string, bounds: Bounds): RequestAction<SetPopupModelAction> {
        return new RequestPopupModelAction(elementId, bounds, generateRequestId());
    }
}

export function isRequestPopupModelAction(action: any): action is RequestPopupModelAction {
    return (
        isActionKind(action, RequestPopupModelAction.KIND) &&
        isString(action, 'elementId') &&
        isObject(action, 'bounds') &&
        isString(action, 'requestId')
    );
}

/**
 * Sent from the server to the client to display a popup in response to a RequestPopupModelAction. This action can also be used to remove
 * any existing popup by choosing EMPTY_ROOT as root element.
 */
export class SetPopupModelAction implements ResponseAction {
    static readonly KIND = 'setPopupModel';
    readonly kind = SetPopupModelAction.KIND;

    constructor(public readonly newRoot: SModelRootSchema, public readonly responseId = '') {}
}

export function isSetPopupModelAction(action: any): action is SetPopupModelAction {
    return isActionKind(action, SetPopupModelAction.KIND) && isObject(action, 'newRoot') && isString(action, 'responseId');
}
