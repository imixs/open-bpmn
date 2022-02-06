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
import { ActionMessage } from 'sprotty';
import { MessageConnection, NotificationType, RequestType } from 'vscode-jsonrpc';
import {
    createMessageConnection,
    Logger,
    NotificationType0,
    toSocket,
    WebSocketMessageReader,
    WebSocketMessageWriter
} from 'vscode-ws-jsonrpc';
import {
    DisposeClientSessionParameters,
    GLSPClient,
    InitializeClientSessionParameters,
    InitializeParameters,
    InitializeResult
} from '../glsp-client';

export type MaybePromise<T> = T | Promise<T> | PromiseLike<T>;
export type ConnectionProvider = MessageConnection | (() => MaybePromise<MessageConnection>);

export namespace JsonrpcGLSPClient {
    export interface Options extends GLSPClient.Options {
        connectionProvider: ConnectionProvider;
    }

    // eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
    export function isOptions(object: any): object is Options {
        return GLSPClient.isOptions(object) && 'connectionProvider' in object;
    }

    export const ActionMessageNotification = new NotificationType<ActionMessage, void>('process');
    export const InitializeRequest = new RequestType<InitializeParameters, InitializeResult, void, void>('initialize');
    export const InitializeClientSessionRequest = new RequestType<InitializeClientSessionParameters, void, void, void>(
        'initializeClientSession'
    );
    export const DisposeClientSessionRequest = new RequestType<DisposeClientSessionParameters, void, void, void>('disposeClientSession');

    export const ShutdownNotification = new NotificationType0<void>('shutdown');
    export const ClientNotReadyMsg = 'JsonrpcGLSPClient is not ready yet';

    export function createWebsocketConnectionProvider(websocket: WebSocket, logger?: Logger): ConnectionProvider {
        const socket = toSocket(websocket);
        const reader = new WebSocketMessageReader(socket);
        const writer = new WebSocketMessageWriter(socket);

        return createMessageConnection(reader, writer, logger);
    }

    export function error(message: string, ...optionalParams: any[]): void {
        console.error(`[JsonrpcGLSPClient] ${message}`, optionalParams);
    }
}
