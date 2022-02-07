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
import * as uuid from 'uuid';

import { ActionMessage } from './action-protocol';
import { Args } from './action-protocol/types';

/**
 * A key-value pair structure to map a diagramType to its server-handled action kinds.
 */
export interface ServerActions {
    [key: string]: string[];
}

export interface InitializeParameters {
    /**
     * Unique identifier for the current client application.
     */
    applicationId: string;

    /**
     * GLSP protocol version that this client is implementing.
     */
    protocolVersion: string;

    /**
     * Additional custom arguments e.g. application specific parameters.
     */
    args?: Args;
}

export interface InitializeResult {
    /**
     * GLSP protocol version that the server is implementing.
     */
    protocolVersion: string;

    /**
     * The actions (grouped by diagramType) that the server can handle.
     */
    serverActions: ServerActions;
}

/**
 * Known server actions i.e. action kinds that the server can handle for a specific diagram type.
 */
export interface InitializeClientSessionParameters {
    /**
     * Unique identifier for the new client session.
     */
    clientSessionId: string;

    /**
     * Unique identifier of the diagram type for which the session should be configured.
     */
    diagramType: string;

    /**
     * Additional custom arguments.
     */
    args?: Args;
}

export interface DisposeClientSessionParameters {
    /**
     * Unique identifier of the client session that should be disposed.
     */
    clientSessionId: string;

    /**
     * Additional custom arguments.
     */
    args?: Args;
}

export class ApplicationIdProvider {
    private static _applicationId?: string;
    static get(): string {
        if (!ApplicationIdProvider._applicationId) {
            ApplicationIdProvider._applicationId = uuid.v4();
        }
        return ApplicationIdProvider._applicationId;
    }
}

export type ActionMessageHandler = (message: ActionMessage) => void;

// eslint-disable-next-line no-shadow
export enum ClientState {
    /**
     * The client has been created.
     */
    Initial,
    /**
     * `Start` has been called on the client and the start process is still on-going.
     */
    Starting,
    /**
     * The client failed to complete the start process.
     */
    StartFailed,
    /**
     * The client was successfully started and is now running.
     */
    Running,
    /**
     * `Stop` has been called on the client and the stop process is still on-going.
     */
    Stopping,
    /**
     * The client stopped and disposed the server connection. Thus, action messages can no longer be sent.
     */
    Stopped,
    /**
     * An error was encountered while connecting to the server. No action messages can be sent.
     */
    ServerError
}

export interface GLSPClient {
    /**
     * Unique client Id.
     */
    readonly id: string;

    /**
     * Current client state.
     */
    readonly currentState: ClientState;

    /**
     * Initializes the client and the server connection. During the start procedure the client is in the
     * `Starting` state and will transition to either `Running` or `StartFailed`.
     *
     * @returns A promise that resolves if the startup was successful.
     */
    start(): Promise<void>;

    /**
     * Send an `initialize` request to the server. The server needs to be initialized in order to accept and
     * process other requests and notifications.
     *
     * @param params Initialize parameters
     * @returns A promise of the {@link InitializeResult}.
     */
    initializeServer(params: InitializeParameters): Promise<InitializeResult>;

    /**
     * Send an `initializeClientSession` request to the server. One client application may open several session.
     * Each individual diagram on the client side counts as one session and has to provide
     * a unique clientId.
     *
     * @param params InitializeClientSession parameters
     * @returns A promise that resolves if the initialization was successful
     */
    initializeClientSession(params: InitializeClientSessionParameters): Promise<void>;

    /**
     * Sends a `disposeClientSession` request to the server. This request has to be sent at the end of client session lifecycle
     * e.g. when an editor widget is closed.
     *
     * @param params DisposeClientSession parameters
     * @returns A promise that resolves if the disposal was successful
     */
    disposeClientSession(params: DisposeClientSessionParameters): Promise<void>;

    /**
     * Send a `shutdown` notification to the server.
     */
    shutdownServer(): void;

    /**
     * Stops the client and disposes any resources. During the stop procedure the client is in the `Stopping` state and will
     * transition to either `Stopped` or `ServerError`.
     *
     * @returns A promise that resolves after the server was stopped and disposed.
     */
    stop(): Promise<void>;

    /**
     * Send an action message to the server.
     *
     * @param message The message
     */
    sendActionMessage(message: ActionMessage): void;

    /**
     * Sets a handler/listener for action messages received from the server.
     *
     * @param handler The action message handler
     */
    onActionMessage(handler: ActionMessageHandler): void;
}
export namespace GLSPClient {
    export interface Options {
        id: string;
    }

    // eslint-disable-next-line @typescript-eslint/explicit-module-boundary-types
    export function isOptions(object: any): object is Options {
        return typeof object === 'object' && 'id' in object && typeof object['id'] === 'string';
    }

    export const protocolVersion = '0.9.0';
}
