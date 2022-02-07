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
import * as uuid from 'uuid';
import { isArray, isString } from '../utils/typeguard-util';
import { JsonAny } from './types';

/**
 * A general message serves as an envelope carrying an action to be transmitted between the client and the server via a DiagramServer.
 */
export interface ActionMessage<A extends Action = Action> {
    /**
     * Used to identify a specific client session.
     */
    clientId: string;
    /**
     * The action to execute.
     */
    action: A;
}

export function isActionMessage(object: any): object is ActionMessage {
    return object !== undefined && isString(object, 'clientId') && isAction(object['action']);
}

export function isActionMessageOfType<A extends Action>(object: any, guard: (action: any) => action is A): object is ActionMessage<A> {
    return isActionMessage(object) && guard(object.action);
}

/**
 * An action is a declarative description of a behavior that shall be invoked by the receiver upon receipt of the action.
 * It is a plain data structure, and as such transferable between server and client. An action must never contain actual
 * SModelElement instances, but either refer to them via their ids or contain serializable schema for model elements.
 */
export interface Action {
    /**
     * Unique identifier specifying the kind of action to process.
     */
    readonly kind: string;
}

export function isAction(action: any): action is Action {
    return action !== undefined && isString(action, 'kind');
}

export function isActionKind(action: any, kind: string): action is Action {
    return isAction(action) && action.kind === kind;
}

/**
 * A request action is tied to the expectation of receiving a corresponding response action. The requestId property is used to match the
 * received response with the original request.
 */
// eslint-disable-next-line @typescript-eslint/no-unused-vars
export interface RequestAction<Res extends ResponseAction> extends Action {
    /**
     * Unique id for this request. In order to match a response to this request, the response needs to have the same id.
     */
    readonly requestId: string;
}

export function isRequestAction(action?: any): action is RequestAction<ResponseAction> {
    return isAction(action) && isString(action, 'requestId');
}

export function generateRequestId(): string {
    return uuid.v4();
}
/**
 * A response action is sent to respond to a request action. The responseId must match the requestId of the preceding request.
 * In case the responseId is empty or undefined, the action is handled as standalone, i.e. it was fired without a preceding request.
 */
export interface ResponseAction extends Action {
    /**
     * Id corresponding to the request this action responds to.
     */
    readonly responseId: string;
}

export function isResponseAction(action?: any): action is ResponseAction {
    return isAction(action) && 'responseId' in action && typeof action['responseId'] === 'string' && action['responseId'] !== '';
}

/**
 * A reject action is a response fired to indicate that a request must be rejected.
 */
export class RejectAction implements ResponseAction {
    static readonly KIND = 'rejectRequest';
    readonly kind = RejectAction.KIND;

    constructor(public readonly message: string, public readonly responseId: string, public readonly detail?: JsonAny) {}
}

export function isRejectAction(action: any): action is RejectAction {
    return isResponseAction(action) && action.kind === RejectAction.KIND && isString(action, 'message');
}

/**
 * Operations are actions that denote requests from the client to _modify_ the model. Model modifications are always performed by the
 * server. After a successful modification, the server sends the updated model back to the client using the `UpdateModelAction`.
 */
export interface Operation extends Action {}

/**
 * An operation that executes a list of sub-operations.
 */
export class CompoundOperation implements Operation {
    static readonly KIND = 'compound';
    constructor(public operationList: Operation[], public readonly kind: string = CompoundOperation.KIND) {}
}

export function isCompoundOperation(operation?: any): operation is CompoundOperation {
    return isActionKind(operation, CompoundOperation.KIND) && isArray(operation, 'operationList');
}
