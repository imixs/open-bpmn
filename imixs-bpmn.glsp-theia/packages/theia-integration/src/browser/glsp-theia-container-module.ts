/********************************************************************************
 * Copyright (C) 2021 EclipseSource and others.
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
import { codiconCSSString } from '@eclipse-glsp/client';
import { ContainerModule, injectable, interfaces } from '@theia/core/shared/inversify';
import { GLSPDiagramLanguage } from '../common/glsp-diagram-language';
import { registerCopyPasteContextMenu } from './copy-paste-context-menu-contribution';
import { BaseTheiaGLSPConnector } from './diagram/base-theia-glsp-connector';
import { GLSPDiagramManager, registerDiagramManager } from './diagram/glsp-diagram-manager';
import { registerDiagramLayoutCommands } from './diagram/glsp-layout-commands';
import { TheiaGLSPConnector } from './diagram/theia-glsp-connector';
import { BaseGLSPClientContribution, GLSPClientContribution } from './glsp-client-contribution';
import { registerMarkerNavigationCommands } from './theia-navigate-to-marker-contribution';

/**
 * A wrapper interface to get access to the binding related functions
 * for a inversify container.
 */
export interface ContainerContext {
    bind: interfaces.Bind;
    unbind: interfaces.Unbind;
    isBound: interfaces.IsBound;
    rebind: interfaces.Rebind;
}

/**
 *  The `GLSPTheiaFrontendModule` provides the necessary required module configuration
 *  to implement a theia GLSP diagram language integration.
 */
export abstract class GLSPTheiaFrontendModule extends ContainerModule {
    protected abstract diagramLanguage: GLSPDiagramLanguage;

    protected enableLayoutCommands = true;
    protected enableMarkerNavigationCommands = true;
    protected enableCopyPaste = false;

    constructor() {
        super((bind, unbind, isBound, rebind) => this.initialize({ bind, unbind, isBound, rebind }));
    }

    initialize(context: ContainerContext): void {
        this.bindTheiaGLSPConnector(context);
        this.bindGLSPClientContribution(context);
        this.bindDiagramConfiguration(context);
        this.configureDiagramManager(context);

        // Optional default configuration
        this.configureDiagramLayoutCommands(context);
        this.configureCopyPasteContextMenu(context);
        this.configureMarkerNavigationCommands(context);
        this.configure(context);
    }

    /**
     * Defines the binding for the theia glsp connector of the diagram integration. A {@link ConfigurableTheiaGLSPConnector}
     * is bound and configured using the diagramLanguage property. Can be overwritten in subclasses to provide a
     * binding using the {@link TheiaGLSPConnector} service identifier.
     *
     * For example:
     * ```typescript
     * context.bind(TheiaGLSPConnector).toSelf(MyCustomTheiaGLSPConnector);
     * ```
     * Note that theia glsp connector bindings are consumed via multi-injection. This means binding the {@link TheiaGLSPConnector}
     * service identifier in singleton scope has no effect.
     *
     * @param context the container context
     */
    bindTheiaGLSPConnector(context: ContainerContext): void {
        context.bind(TheiaGLSPConnector).toDynamicValue(dynamicContext => {
            const connector = dynamicContext.container.resolve(ConfigurableTheiaGLSPConnector);
            connector.doConfigure(this.diagramLanguage);
            return connector;
        });
    }

    /**
     * Defines the binding for the glsp client contribution of the diagram integration. A {@link ConfigurableGLSPClientContribution}
     * with the same id as the diagramLanguage is bound. Can be overwritten in subclasses to provide a custom binding using
     * the {@link GLSPClientContribution} service identifier.
     *
     * For example:
     * ```typescript
     * context.bind(GLSPClientContribution).toSelf(MyCustomGLSPClientContribution);
     * ```
     * Note that glsp client contribution bindings are consumed via multi-injection. This means binding the {@link GLSPClientContribution}
     * service identifier in singleton scope has no effect.
     *
     * @param context the container context
     */
    bindGLSPClientContribution(context: ContainerContext): void {
        context.bind(GLSPClientContribution).toDynamicValue(dynamicContext => {
            const contribution = dynamicContext.container.resolve(ConfigurableGLSPClientContribution);
            contribution.id = this.diagramLanguage.contributionId;
            return contribution;
        });
    }

    /**
     * Defines the binding for the diagram configuration of the diagram integration. Diagram configurations are multi-injected
     * and typically just bound to the `DiagramConfiguration` service identifier with the to() syntax.
     *
     * For example:
     * ```typescript
     * context.bind(DiagramConfiguration).to(MyCustomDiagramConfiguration);
     * ```
     *
     * Note that diagram configuration bindings are consumed via multi-injection. This means binding the `DiagramConfiguration`
     * service identifier in singleton scope has no effect.
     *
     * @param context the container context
     */
    abstract bindDiagramConfiguration(context: ContainerContext): void;

    /**
     * Configures the bindings for the diagram manager of the diagram integration. A {@link ConfigurableGLSPDiagramManager}
     * is bound to a generated service identifer in singleton scope and then additional bindings for this service
     * identifer are registered. Can be overwritten in subclasses to provide a custom binding.
     *
     *  For example:
     * ```typescript
     * context.bind(MyDiagramManager).to(MyDiagramManager).inSingletonScope();
     * registerDiagramManager(context.bind, MyDiagramManager);
     * ```
     * @param context the container context
     */
    configureDiagramManager(context: ContainerContext): void {
        const diagramManagerServiceId = Symbol(`DiagramManager_${this.diagramLanguage.diagramType}`);
        context
            .bind(diagramManagerServiceId)
            .toDynamicValue(dynamicContext => {
                const manager = dynamicContext.container.resolve(ConfigurableGLSPDiagramManager);
                manager.doConfigure(this.diagramLanguage);
                return manager;
            })
            .inSingletonScope();
        registerDiagramManager(context.bind, diagramManagerServiceId, false);
    }

    /**
     * Can be overwritten by subclasses to provide additional configuration (e.g. extra bindings)
     * for this module.
     *
     * For example:
     * ```typescript
     * context.bind(MyCustomClass).toSelf().inSingletonScope()
     * ```
     * @param context the container context
     */
    configure(context: ContainerContext): void {
        // Empty per default
    }

    /**
     * Optional configuration to enable the default diagram layout commands for the diagram integration.
     * Can be enabled/disabled using the {@link GLSPTheiaFrontendModule.enableLayoutCommands}  property flag.
     *
     * @param context the diagram context
     */
    configureDiagramLayoutCommands(context: ContainerContext): void {
        if (this.enableLayoutCommands) {
            registerDiagramLayoutCommands(context.bind);
        }
    }

    /**
     * Optional configuration for copy & paste functionality of the diagram integration.
     * Can be enabled/disabled using the {@link GLSPTheiaFrontendModule.enableCopyPaste} property flag.
     * Note that the glsp server also needs to support copy & paste for this diagram configuration.
     *
     * @param context the diagram context
     */
    configureCopyPasteContextMenu(context: ContainerContext): void {
        if (this.enableCopyPaste) {
            registerCopyPasteContextMenu(context.bind);
        }
    }

    /**
     * Optional configuration to enable marker navigation for the diagram integration.
     * Can be enabled/disabled using the {@link GLSPTheiaFrontendModule.enableMarkerNavigationCommands}  property flag.
     * Note that the glsp server also needs to support copy & paste for this diagram configuration.
     *
     * @param context the diagram context
     */
    configureMarkerNavigationCommands(context: ContainerContext): void {
        if (this.enableMarkerNavigationCommands) {
            registerMarkerNavigationCommands(context.bind);
        }
    }
}

/**
 * Internal class that is used in {@link GLSPTheiaFrontendModule.bindTheiaGLSPConnector} to
 * bind a default implementation for {@link TheiaGLSPConnector}. A custom {@link TheiaGLSPConnector} should
 * never extend this class. Use {@link BaseTheiaGLSPConnector} instead.
 */
@injectable()
class ConfigurableTheiaGLSPConnector extends BaseTheiaGLSPConnector {
    private _contributionId: string;
    private _diagramType?: string;

    public doConfigure(diagramLanguage: GLSPDiagramLanguage): void {
        this._contributionId = diagramLanguage.contributionId;
        this._diagramType = diagramLanguage.diagramType;
        this.initialize();
    }
    get diagramType(): string {
        if (!this._diagramType) {
            throw new Error('No diagramType has been set for this ConfigurableTheiaGLSPConnector');
        }
        return this._diagramType;
    }

    get contributionId(): string {
        if (!this._contributionId) {
            throw new Error('No contributionId has been set for this ConfigurableTheiaGLSPConnector');
        }
        return this._contributionId;
    }

    protected initialize(): void {
        if (this._diagramType && this._contributionId) {
            super.initialize();
        }
    }
}

/**
 * Internal class that is used in {@link GLSPTheiaFrontendModule.configureDiagramManager} to
 * bind a default implementation for `DiagramManager`. A custom `DiagramManager` should
 * never extend this class. Use {@link GLSPDiagramManager} instead.
 */
@injectable()
class ConfigurableGLSPDiagramManager extends GLSPDiagramManager {
    private _diagramType?: string;
    private _label: string;
    private _fileExtensions: string[] = [];
    private _iconClass = codiconCSSString('type-hierarchy-sub');

    public doConfigure(diagramLanguage: GLSPDiagramLanguage): void {
        this._fileExtensions = diagramLanguage.fileExtensions;
        this._diagramType = diagramLanguage.diagramType;
        this._label = diagramLanguage.label;
        this._iconClass = diagramLanguage.iconClass || this._iconClass;
        this.initialize();
    }

    protected async initialize(): Promise<void> {
        if (this._diagramType) {
            return super.initialize();
        }
    }

    get fileExtensions(): string[] {
        return this._fileExtensions;
    }

    get diagramType(): string {
        if (!this._diagramType) {
            throw new Error('No diagramType has been set for this ConfigurableGLSPDiagramManager');
        }
        return this._diagramType;
    }

    get label(): string {
        return this._label;
    }

    get iconClass(): string {
        return this._iconClass;
    }
}

/**
 * Internal class that is used in {@link GLSPTheiaFrontendModule.bindGLSPClientContribution} to
 * bind a default implementation for {@link GLSPClientContribution}. A custom {@link GLSPClientContribution}  should
 * never extend this class. Use {@link BaseGLSPClientContribution} instead.
 */
@injectable()
class ConfigurableGLSPClientContribution extends BaseGLSPClientContribution {
    protected _id?: string;

    get id(): string {
        if (!this._id) {
            throw new Error('No id has been set for this DefaultTheiaGLSPConnector');
        }
        return this._id;
    }

    set id(value: string) {
        if (this._id) {
            throw new Error(`A value for id has already been set. Cannot set new value'${value}'`);
        }
        this._id = value;
    }
}
