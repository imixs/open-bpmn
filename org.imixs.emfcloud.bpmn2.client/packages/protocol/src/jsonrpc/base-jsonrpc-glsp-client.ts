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
import { ActionMessage } from 'sprotty';
import { Message, MessageConnection } from 'vscode-ws-jsonrpc';

import {
    ActionMessageHandler,
    ClientState,
    DisposeClientSessionParameters,
    GLSPClient,
    InitializeClientSessionParameters,
    InitializeParameters,
    InitializeResult
} from '../glsp-client';
import { ConnectionProvider, JsonrpcGLSPClient } from './glsp-jsonrpc-client';

export class BaseJsonrpcGLSPClient implements GLSPClient {
    readonly id: string;
    protected readonly connectionProvider: ConnectionProvider;
    protected connectionPromise?: Promise<MessageConnection>;
    protected resolvedConnection?: MessageConnection;
    protected state: ClientState;
    protected onStop?: Promise<void>;

    constructor(options: JsonrpcGLSPClient.Options) {
        Object.assign(this, options);
        this.state = ClientState.Initial;
    }

    shutdownServer(): void {
        if (this.checkConnectionState()) {
            this.resolvedConnection!.sendNotification(JsonrpcGLSPClient.ShutdownNotification);
        }
    }

    initializeServer(params: InitializeParameters): Promise<InitializeResult> {
        if (this.checkConnectionState()) {
            return this.resolvedConnection!.sendRequest(JsonrpcGLSPClient.InitializeRequest, params);
        }
        return Promise.reject(JsonrpcGLSPClient.ClientNotReadyMsg);
    }

    initializeClientSession(params: InitializeClientSessionParameters): Promise<void> {
        if (this.checkConnectionState()) {
            return this.resolvedConnection!.sendRequest(JsonrpcGLSPClient.InitializeClientSessionRequest, params);
        }
        return Promise.reject(JsonrpcGLSPClient.ClientNotReadyMsg);
    }

    disposeClientSession(params: DisposeClientSessionParameters): Promise<void> {
        if (this.checkConnectionState()) {
            return this.resolvedConnection!.sendRequest(JsonrpcGLSPClient.DisposeClientSessionRequest, params);
        }
        return Promise.reject(JsonrpcGLSPClient.ClientNotReadyMsg);
    }

    onActionMessage(handler: ActionMessageHandler): void {
        if (this.checkConnectionState()) {
            this.resolvedConnection!.onNotification(JsonrpcGLSPClient.ActionMessageNotification, handler);
        }
    }

    sendActionMessage(message: ActionMessage): void {
        if (this.checkConnectionState()) {
            this.resolvedConnection!.sendNotification(JsonrpcGLSPClient.ActionMessageNotification, message);
        }
    }

    protected checkConnectionState(): boolean {
        if (!this.isConnectionActive()) {
            throw new Error(JsonrpcGLSPClient.ClientNotReadyMsg);
        }
        return true;
    }

    async start(): Promise<void> {
        try {
            this.state = ClientState.Starting;
            const connection = await this.resolveConnection();
            connection.listen();
            this.resolvedConnection = connection;
            this.state = ClientState.Running;
        } catch (error) {
            JsonrpcGLSPClient.error('Failed to start connection to server', error);
            this.state = ClientState.StartFailed;
        }
    }

    stop(): Promise<void> {
        if (!this.connectionPromise) {
            this.state = ClientState.Stopped;
            return Promise.resolve();
        }
        if (this.state === ClientState.Stopping && this.onStop) {
            return this.onStop;
        }
        this.state = ClientState.Stopping;
        return (this.onStop = this.resolveConnection().then(connection => {
            connection.dispose();
            this.state = ClientState.Stopped;
            this.onStop = undefined;
            this.connectionPromise = undefined;
            this.resolvedConnection = undefined;
        }));
    }

    private resolveConnection(): Promise<MessageConnection> {
        if (!this.connectionPromise) {
            this.connectionPromise = this.doCreateConnection();
        }
        return this.connectionPromise;
    }

    protected async doCreateConnection(): Promise<MessageConnection> {
        const connection = typeof this.connectionProvider === 'function' ? await this.connectionProvider() : this.connectionProvider;
        connection.onError((data: [Error, Message, number]) => this.handleConnectionError(data[0], data[1], data[2]));
        connection.onClose(() => this.handleConnectionClosed());
        return connection;
    }

    protected handleConnectionError(error: Error, message: Message, count: number): void {
        JsonrpcGLSPClient.error('Connection to server is erroring. Shutting down server.', error);
        this.stop();
        this.state = ClientState.ServerError;
    }

    protected handleConnectionClosed(): void {
        if (this.state === ClientState.Stopping || this.state === ClientState.Stopped) {
            return;
        }
        try {
            if (this.resolvedConnection) {
                this.resolvedConnection.dispose();
                this.connectionPromise = undefined;
                this.resolvedConnection = undefined;
            }
        } catch (error) {
            // Disposing a connection could fail if error cases.
        }

        JsonrpcGLSPClient.error('Connection to server got closed. Server will not be restarted.');
        this.state = ClientState.ServerError;
    }

    protected isConnectionActive(): boolean {
        return this.state === ClientState.Running && !!this.resolvedConnection;
    }

    get currentState(): ClientState {
        return this.state;
    }
}
