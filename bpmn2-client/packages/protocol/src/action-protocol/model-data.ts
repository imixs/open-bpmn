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
import { isBoolean, isObject, isString } from '../utils/typeguard-util';
import { Action, generateRequestId, isActionKind, RequestAction, ResponseAction } from './base-protocol';
import { SModelRootSchema } from './model-structure';
import { JsonPrimitive } from './types';

/**
 * Sent from the server to the client in order to set the model. If a model is already present, it is replaced.
 */
export class RequestModelAction implements RequestAction<SetModelAction> {
    static readonly KIND = 'requestModel';
    readonly kind = RequestModelAction.KIND;

    constructor(public readonly options?: { [key: string]: JsonPrimitive }, public readonly requestId = '') {}

    /** Factory function to dispatch a request with the `IActionDispatcher` */
    static create(options?: { [key: string]: JsonPrimitive }): RequestAction<SetModelAction> {
        return new RequestModelAction(options, generateRequestId());
    }
}

export function isRequestModelAction(action: any): action is RequestModelAction {
    return isActionKind(action, RequestModelAction.KIND);
}
/**
 * Sent from the model source to the client in order to set the model. If a model is already present, it is replaced.
 */
export class SetModelAction implements ResponseAction {
    static readonly KIND = 'setModel';
    readonly kind = SetModelAction.KIND;

    constructor(public readonly newRoot: SModelRootSchema, public readonly responseId = '') {}
}

export function isSetModelAction(action: any): action is SetModelAction {
    return isActionKind(action, SetModelAction.KIND) && isObject(action, 'newRoot');
}

/**
 * Sent from the server to the client in order to update the model. If no model is present yet, this behaves the same as a SetModelAction.
 * The transition from the old model to the new one can be animated.
 */
export class UpdateModelAction implements Action {
    static readonly KIND = 'updateModel';
    readonly kind = UpdateModelAction.KIND;

    constructor(public readonly newRoot: SModelRootSchema, public readonly animate: boolean = true) {}
}

export function isUpdateModelAction(action: any): action is UpdateModelAction {
    return isActionKind(action, UpdateModelAction.KIND) && isObject(action, 'newRoot') && isBoolean(action, 'animate');
}

export class ModelSourceChangedAction implements Action {
    static KIND = 'modelSourceChanged';
    readonly kind = ModelSourceChangedAction.KIND;
    constructor(public readonly modelSourceName: string) {}
}

export function isModelSourceChangedAction(action?: any): action is ModelSourceChangedAction {
    return isActionKind(action, ModelSourceChangedAction.KIND) && isString(action, 'modelSourceName');
}
