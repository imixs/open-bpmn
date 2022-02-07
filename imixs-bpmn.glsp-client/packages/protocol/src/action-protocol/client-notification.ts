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
import { isNumber, isString } from '../utils/typeguard-util';
import { Action, isActionKind } from './base-protocol';

export type ServerSeverity = 'NONE' | 'INFO' | 'WARNING' | 'ERROR' | 'FATAL' | 'OK';
/**
 * This action is typically sent by the server to signal a state change. This action extends the corresponding Sprotty action to include
 * a timeout. If a timeout is given the respective status should disappear after the timeout is reached.
 */
export class GLSPServerStatusAction implements Action {
    static KIND = 'serverStatus';

    constructor(
        public severity: ServerSeverity,
        public message: string,
        public timeout = -1,
        readonly kind = GLSPServerStatusAction.KIND
    ) {}
}

export function isGLSPServerStatusAction(action: any): action is GLSPServerStatusAction {
    return (
        isActionKind(action, GLSPServerStatusAction.KIND) &&
        isString(action, 'severity') &&
        isString(action, 'message') &&
        isNumber(action, 'timeout')
    );
}
/**
 * This action is typically sent by the server to notify the user about something of interest.
 */
export class ServerMessageAction implements Action {
    static KIND = 'serverMessage';

    constructor(
        public severity: ServerSeverity,
        public message: string,
        public details?: string,
        public timeout = -1,
        readonly kind = ServerMessageAction.KIND
    ) {}
}

export function isServerMessageAction(action?: any): action is ServerMessageAction {
    return (
        isActionKind(action, ServerMessageAction.KIND) &&
        isString(action, 'severity') &&
        isString(action, 'message') &&
        isNumber(action, 'timeout')
    );
}
