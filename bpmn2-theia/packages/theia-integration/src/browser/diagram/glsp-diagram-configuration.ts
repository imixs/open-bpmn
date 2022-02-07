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
import { configureActionHandler, ExternalModelSourceChangedHandler, NavigateToExternalTargetAction, TYPES } from '@eclipse-glsp/client';
import { CommandService, SelectionService } from '@theia/core';
import { OpenerService } from '@theia/core/lib/browser';
import { Container, inject, injectable } from '@theia/core/shared/inversify';
import { DiagramConfiguration, TheiaContextMenuService, TheiaDiagramServer } from 'sprotty-theia';
import { TheiaModelSourceChangedHandler } from '../theia-model-source-changed-handler';
import { TheiaNavigateToExternalTargetHandler } from '../theia-navigate-to-external-target-handler';
import { connectTheiaContextMenuService, TheiaContextMenuServiceFactory } from './theia-context-menu-service';
import { TheiaGLSPConnector, TheiaGLSPConnectorRegistry } from './theia-glsp-connector';
import { TheiaGLSPSelectionForwarder } from './theia-glsp-selection-forwarder';
import { connectTheiaMarkerManager, TheiaMarkerManager, TheiaMarkerManagerFactory } from './theia-marker-manager';

@injectable()
export abstract class GLSPDiagramConfiguration implements DiagramConfiguration {
    @inject(SelectionService) protected selectionService: SelectionService;
    @inject(OpenerService) protected openerService: OpenerService;
    @inject(CommandService) protected readonly commandService: CommandService;
    @inject(TheiaModelSourceChangedHandler) protected modelSourceChangedHandler: TheiaModelSourceChangedHandler;
    @inject(TheiaContextMenuServiceFactory) protected readonly contextMenuServiceFactory: () => TheiaContextMenuService;
    @inject(TheiaMarkerManagerFactory) protected readonly theiaMarkerManager: () => TheiaMarkerManager;
    @inject(TheiaGLSPConnectorRegistry) protected readonly connectorRegistry: TheiaGLSPConnectorRegistry;

    abstract readonly diagramType: string;

    createContainer(widgetId: string): Container {
        const container = this.doCreateContainer(widgetId);

        this.initializeContainer(container);
        return container;
    }

    abstract doCreateContainer(widgetId: string): Container;

    protected initializeContainer(container: Container): void {
        container.bind(TheiaGLSPConnector).toConstantValue(this.connectorRegistry.get(this.diagramType));
        container.bind(TYPES.IActionHandlerInitializer).to(TheiaGLSPSelectionForwarder);
        container.bind(SelectionService).toConstantValue(this.selectionService);
        container.bind(OpenerService).toConstantValue(this.openerService);
        container.bind(CommandService).toConstantValue(this.commandService);
        container.bind(ExternalModelSourceChangedHandler).toConstantValue(this.modelSourceChangedHandler);

        connectTheiaContextMenuService(container, this.contextMenuServiceFactory);
        connectTheiaMarkerManager(container, this.theiaMarkerManager, this.diagramType);
        configureActionHandler(container, NavigateToExternalTargetAction.KIND, TheiaNavigateToExternalTargetHandler);
    }
}

export function configureDiagramServer<T>(container: Container, server: { new (...args: any[]): T }): void {
    container.bind(server).toSelf().inSingletonScope();
    container.bind(TYPES.ModelSource).toService(server);
    container.bind(TheiaDiagramServer).toService(server);
}
