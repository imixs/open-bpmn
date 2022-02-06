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
import { generateRequestId, isActionKind, RequestAction, ResponseAction } from './base-protocol';
import { Args, EditorContext, LabeledAction } from './types';

/**
 * The `RequestContextActions` is sent from the client to the server to request the available actions for the context with id contextId.
 */
export class RequestContextActions implements RequestAction<SetContextActions> {
    static readonly KIND = 'requestContextActions';
    constructor(
        public readonly contextId: string,
        public readonly editorContext: EditorContext,
        public readonly requestId: string = generateRequestId(),
        public readonly kind: string = RequestContextActions.KIND
    ) {}
}

export function isRequestContextActions(action: any): action is RequestContextActions {
    return (
        isActionKind(action, RequestContextActions.KIND) &&
        isString(action, 'contextId') &&
        isObject(action, 'editorContext') &&
        isString(action, 'requestId')
    );
}

/**
 * The `SetContextActions` is the response to a {@link RequestContextActions} containing all actions for the queried context.
 */
export class SetContextActions implements ResponseAction {
    static readonly KIND = 'setContextActions';
    constructor(
        public readonly actions: LabeledAction[],
        public readonly responseId: string = '',
        public readonly args?: Args,
        public readonly kind: string = SetContextActions.KIND
    ) {}
}

export function isSetContextActionsAction(action: any): action is SetContextActions {
    return isActionKind(action, SetContextActions.KIND) && isArray(action, 'actions') && isString(action, 'responseId');
}
