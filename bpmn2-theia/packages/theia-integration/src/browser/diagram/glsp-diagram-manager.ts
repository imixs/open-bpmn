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
import { codiconCSSString, configureServerActions, EditMode, GLSPActionDispatcher } from '@eclipse-glsp/client';
import {
    ApplicationShell,
    FrontendApplicationContribution,
    NavigatableWidgetOptions,
    OpenHandler,
    StorageService,
    WidgetFactory,
    WidgetOpenerOptions
} from '@theia/core/lib/browser';
import { SelectionService } from '@theia/core/lib/common/selection-service';
import URI from '@theia/core/lib/common/uri';
import { inject, injectable, interfaces, postConstruct } from '@theia/core/shared/inversify';
import { EditorPreferences } from '@theia/editor/lib/browser';
import { DiagramConfiguration, DiagramManager, DiagramManagerProvider, DiagramWidget, DiagramWidgetOptions } from 'sprotty-theia';
import { TheiaOpenerOptionsNavigationService } from '../theia-opener-options-navigation-service';
import { GLSPDiagramContextKeyService } from './glsp-diagram-context-key-service';
import { GLSPDiagramWidget } from './glsp-diagram-widget';
import { TheiaGLSPConnector } from './theia-glsp-connector';

export function registerDiagramManager(
    bind: interfaces.Bind,
    diagramManagerServiceId: interfaces.ServiceIdentifier<DiagramManager>,
    bindToSelf = true
): void {
    if (bindToSelf) {
        bind(diagramManagerServiceId).toSelf().inSingletonScope();
    }
    bind(DiagramManager).toService(diagramManagerServiceId);
    bind(FrontendApplicationContribution).toService(diagramManagerServiceId);
    bind(OpenHandler).toService(diagramManagerServiceId);
    bind(WidgetFactory).toService(diagramManagerServiceId);
    bind(DiagramManagerProvider).toProvider<DiagramManager>(
        context => () =>
            new Promise<DiagramManager>(resolve => {
                resolve(context.container.get(diagramManagerServiceId));
            })
    );
}

export const TheiaGLSPConnectorProvider = Symbol('TheiaGLSPConnectorProvider');

export type TheiaGLSPConnectorProvider = (diagramType: string) => Promise<TheiaGLSPConnector>;
@injectable()
export abstract class GLSPDiagramManager extends DiagramManager {
    @inject(EditorPreferences)
    protected readonly editorPreferences: EditorPreferences;

    @inject(StorageService)
    protected readonly storage: StorageService;

    @inject(TheiaOpenerOptionsNavigationService)
    protected readonly diagramNavigationService: TheiaOpenerOptionsNavigationService;

    @inject(GLSPDiagramContextKeyService)
    protected readonly contextKeyService: GLSPDiagramContextKeyService;

    @inject(ApplicationShell)
    protected readonly shell: ApplicationShell;

    @inject(SelectionService)
    theiaSelectionService: SelectionService;

    @inject(TheiaGLSPConnectorProvider)
    protected readonly connectorProvider: TheiaGLSPConnectorProvider;

    abstract get fileExtensions(): string[];

    protected _diagramConnector: TheiaGLSPConnector;

    @postConstruct()
    protected async initialize(): Promise<void> {
        this._diagramConnector = await this.connectorProvider(this.diagramType);
        if (!this._diagramConnector) {
            throw new Error(`No diagram connector is registered for diagramType: ${this.diagramType}!`);
        }
    }

    async doOpen(widget: DiagramWidget, options?: WidgetOpenerOptions): Promise<void> {
        const widgetWasAttached = widget.isAttached;
        await super.doOpen(widget);
        const navigations = this.diagramNavigationService.determineNavigations(widget.uri.toString(true), options);
        if (navigations.length > 0) {
            if (widget.actionDispatcher instanceof GLSPActionDispatcher) {
                widget.actionDispatcher.onceModelInitialized().then(() => widget.actionDispatcher.dispatchAll(navigations));
            } else {
                widget.actionDispatcher.dispatchAll(navigations);
            }
        } else if (!widgetWasAttached && widget instanceof GLSPDiagramWidget) {
            widget.restoreViewportDataFromStorageService();
        }
    }

    async createWidget(options?: any): Promise<DiagramWidget> {
        if (DiagramWidgetOptions.is(options)) {
            const clientId = this.createClientId();
            const widgetId = this.createWidgetId(options);
            const config = this.getDiagramConfiguration(options);
            const diContainer = config.createContainer(clientId);
            const initializeResult = await this.diagramConnector.initializeResult;
            await configureServerActions(initializeResult, this.diagramType, diContainer);
            const widget = new GLSPDiagramWidget(
                options,
                widgetId,
                diContainer,
                this.editorPreferences,
                this.storage,
                this.theiaSelectionService,
                this.diagramConnector
            );
            widget.listenToFocusState(this.shell);
            return widget;
        }
        throw Error('DiagramWidgetFactory needs DiagramWidgetOptions but got ' + JSON.stringify(options));
    }

    protected createWidgetOptions(uri: URI, options?: GLSPWidgetOpenerOptions): DiagramWidgetOptions & GLSPWidgetOptions {
        return {
            diagramType: this.diagramType,
            kind: 'navigatable',
            uri: uri.toString(true),
            iconClass: this.iconClass,
            label: uri.path.base,
            editMode: options && options.editMode ? options.editMode : EditMode.EDITABLE
        } as DiagramWidgetOptions & GLSPWidgetOptions;
    }

    protected createWidgetId(options: DiagramWidgetOptions): string {
        return `${this.diagramType}:${options.uri}`;
    }

    protected getDiagramConfiguration(options: DiagramWidgetOptions): DiagramConfiguration {
        return this.diagramConfigurationRegistry.get(options.diagramType);
    }

    canHandle(uri: URI, _options?: WidgetOpenerOptions | undefined): number {
        for (const extension of this.fileExtensions) {
            if (uri.path.toString().endsWith(extension)) {
                return 1001;
            }
        }
        return 0;
    }

    get diagramConnector(): TheiaGLSPConnector {
        return this._diagramConnector;
    }

    get id(): string {
        return deriveDiagramManagerId(this.diagramType);
    }

    get iconClass(): string {
        return codiconCSSString('type-hierarchy-sub');
    }
}

export interface GLSPWidgetOpenerOptions extends WidgetOpenerOptions {
    editMode?: string;
}

export interface GLSPWidgetOptions extends NavigatableWidgetOptions {
    editMode: string;
}

export function deriveDiagramManagerId(diagramType: string): string {
    return diagramType + '-diagram-manager';
}
