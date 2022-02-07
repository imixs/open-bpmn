/********************************************************************************
 * Copyright (C) 2017-2021 TypeFox and others.
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
import {
    ApplicationIdProvider,
    Args,
    ClientState,
    ConnectionProvider,
    GLSPClient,
    InitializeParameters,
    InitializeResult,
    MaybePromise
} from '@eclipse-glsp/client';
import { Disposable, DisposableCollection, MessageService } from '@theia/core';
import { FrontendApplication, WebSocketConnectionProvider } from '@theia/core/lib/browser';
import { inject, injectable, multiInject } from '@theia/core/shared/inversify';
import { WorkspaceService } from '@theia/workspace/lib/browser';
import { DiagramManagerProvider } from 'sprotty-theia';
import 'sprotty-theia/css/theia-sprotty.css';
import 'sprotty/css/sprotty.css';
import '../../css/command-palette.css';
import '../../css/decoration.css';
import '../../css/diagram.css';
import '../../css/theia-dialogs.css';
import '../../css/tool-palette.css';
import { GLSPContribution } from '../common';
import { TheiaJsonrpcGLSPClient } from './theia-jsonrpc-glsp-client';

export const GLSPClientContribution = Symbol.for('GLSPClientContribution');

export interface GLSPClientContribution extends GLSPContribution {
    readonly running: boolean;
    readonly initializeResult: Promise<InitializeResult>;
    readonly glspClient: Promise<GLSPClient>;
    waitForActivation(app: FrontendApplication): Promise<void>;
    activate(app: FrontendApplication): Disposable;
    deactivate(app: FrontendApplication): void;
}

@injectable()
export abstract class BaseGLSPClientContribution implements GLSPClientContribution {
    abstract readonly id: string;

    protected _glspClient: GLSPClient | undefined;

    protected resolveReady: (glspClient: GLSPClient) => void;
    protected ready: Promise<GLSPClient>;
    protected readonly toDeactivate = new DisposableCollection();
    protected _initializeResult: InitializeResult | undefined;

    @inject(WorkspaceService) protected readonly workspaceService: WorkspaceService;
    @inject(MessageService) protected readonly messageService: MessageService;
    @inject(WebSocketConnectionProvider) protected readonly connectionProvider: WebSocketConnectionProvider;
    @multiInject(DiagramManagerProvider) protected diagramManagerProviders: DiagramManagerProvider[];

    constructor() {
        this.waitForReady();
    }

    get glspClient(): Promise<GLSPClient> {
        return this._glspClient ? Promise.resolve(this._glspClient) : this.ready;
    }

    get initializeResult(): Promise<InitializeResult> {
        return this.glspClient.then(_client => {
            if (!this._initializeResult) {
                throw new Error('Server is not yet initialized!');
            }
            return this._initializeResult;
        });
    }

    waitForActivation(app: FrontendApplication): Promise<any> {
        const activationPromises: Promise<any>[] = [];
        const workspaceContains = this.workspaceContains;
        if (workspaceContains.length !== 0) {
            activationPromises.push(this.waitForItemInWorkspace());
        }
        if (activationPromises.length !== 0) {
            return Promise.all([
                this.ready,
                Promise.race(
                    activationPromises.map(
                        p =>
                            // eslint-disable-next-line no-async-promise-executor
                            new Promise<void>(async resolve => {
                                try {
                                    await p;
                                    resolve();
                                } catch (e) {
                                    console.error(e);
                                }
                            })
                    )
                )
            ]);
        }
        return this.ready;
    }

    activate(): Disposable {
        if (this.toDeactivate.disposed) {
            // eslint-disable-next-line @typescript-eslint/no-empty-function
            this.toDeactivate.push(new DisposableCollection(Disposable.create(() => {}))); // mark as not disposed
            this.doActivate(this.toDeactivate);
        }
        return this.toDeactivate;
    }

    deactivate(_app: FrontendApplication): void {
        this.toDeactivate.dispose();
    }

    protected async doActivate(toStop: DisposableCollection): Promise<void> {
        try {
            this.connectionProvider.listen(
                {
                    path: GLSPContribution.getPath(this),
                    onConnection: messageConnection => {
                        if (toStop.disposed) {
                            messageConnection.dispose();
                            return;
                        }
                        const languageClient = this.createGLSPCLient(messageConnection);
                        this.onWillStart(languageClient);
                        toStop.pushAll([
                            messageConnection,
                            Disposable.create(() => {
                                languageClient.shutdownServer();
                                languageClient.stop();
                            })
                        ]);
                    }
                },
                { reconnecting: false }
            );
        } catch (e) {
            console.error(e);
        }
    }

    get running(): boolean {
        return !this.toDeactivate.disposed && this._glspClient !== undefined && this._glspClient.currentState === ClientState.Running;
    }

    protected async onWillStart(languageClient: GLSPClient): Promise<void> {
        await languageClient.start();
        this._initializeResult = await this.initialize(languageClient);
        this.onReady(languageClient);
    }

    protected async initialize(languageClient: GLSPClient): Promise<InitializeResult> {
        try {
            const parameters = await this.createInitializeParameters();
            return await languageClient.initializeServer(parameters);
        } catch (error) {
            const errorMsg = `Failed to initialize ${this.id} glsp server with: ${error}`;
            this.messageService.error(errorMsg);
            return Promise.reject(errorMsg);
        }
    }

    protected async createInitializeParameters(): Promise<InitializeParameters> {
        const args = await this.createInitializeOptions();
        return {
            applicationId: ApplicationIdProvider.get(),
            protocolVersion: GLSPClient.protocolVersion,
            args
        };
    }

    protected createInitializeOptions(): MaybePromise<Args | undefined> {
        return undefined;
    }

    protected onReady(languageClient: GLSPClient): void {
        this._glspClient = languageClient;
        this.resolveReady(this._glspClient);
        this.waitForReady();
    }

    protected waitForReady(): void {
        this.ready = new Promise<GLSPClient>(resolve => (this.resolveReady = resolve));
    }

    protected createGLSPCLient(connectionProvider: ConnectionProvider): GLSPClient {
        return new TheiaJsonrpcGLSPClient({
            id: this.id,
            connectionProvider,
            messageService: this.messageService
        });
    }

    protected get workspaceContains(): string[] {
        return [];
    }

    protected async waitForItemInWorkspace(): Promise<any> {
        const doesContain = await this.workspaceService.containsSome(this.workspaceContains);
        if (!doesContain) {
            // eslint-disable-next-line @typescript-eslint/no-empty-function
            return new Promise(resolve => {});
        }
        return doesContain;
    }
}
