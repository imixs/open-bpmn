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
import {
    Action,
    Args,
    CenterAction,
    GLSPServerStatusAction,
    isNavigateToExternalTargetAction,
    isNavigateToTargetAction,
    isSetNavigationTargetsAction,
    NavigateToExternalTargetAction,
    NavigateToTargetAction,
    NavigationTarget,
    RequestNavigationTargetsAction,
    SelectAction,
    SelectAllAction,
    ServerMessageAction,
    SetResolvedNavigationTargetAction
} from '@eclipse-glsp/protocol';
import { inject, injectable } from 'inversify';
import { ActionHandlerRegistry, IActionDispatcher, IActionHandler, ICommand, ILogger, TYPES } from 'sprotty';

import { EditorContextServiceProvider } from '../../base/editor-context-service';
import { GLSP_TYPES } from '../../base/types';
import { NavigationTargetResolver } from './navigation-target-resolver';

/**
 * Action for triggering a navigation of a certain target type.
 *
 * Examples for target types could be `documentation`, `implementation`, etc.
 * but this is up to the domain-specific diagram implementation to decide.
 * Such an action will eventually trigger a `RequestNavigationTargetsAction`
 * (see `NavigationActionHandler`) in order to request the navigation targets
 * from the server.
 *
 * This action is typically triggered by a user action.
 */
export class NavigateAction implements Action {
    static readonly KIND = 'navigate';
    readonly kind = NavigateAction.KIND;
    constructor(readonly targetTypeId: string, readonly args?: Args) {}
}

export function isNavigateAction(action: Action): action is NavigateAction {
    return action !== undefined && action.kind === NavigateAction.KIND && (action as NavigateAction).targetTypeId !== undefined;
}

/** Action to trigger the processing of additional navigation arguments.
 *
 * The resolution of a `NavigationTarget` may entail additional arguments. In this case, this action is
 * triggered allowing the client to react to those arguments. The default `NavigationActionHandler` will
 * only process the arguments' keys `info`, `warning`, and `error` to present them to the user.
 * Customizations, however, may add domain-specific arguments and register custom handler to also process
 * other arguments and trigger some additional behavior (e.g. update other views, etc.).
 */
export class ProcessNavigationArgumentsAction implements Action {
    static readonly KIND = 'processNavigationArguments';
    readonly kind = ProcessNavigationArgumentsAction.KIND;
    constructor(readonly args: Args) {}
}

export function isProcessNavigationArgumentsAction(action: Action): action is ProcessNavigationArgumentsAction {
    return (
        action !== undefined &&
        action.kind === ProcessNavigationArgumentsAction.KIND &&
        (action as ProcessNavigationArgumentsAction).args !== undefined
    );
}

/**
 * Default handler for all actions that are related to the navigation.
 *
 * For a `NavigateAction` this handler triggers a `RequestNavigationTargetAction` to obtain the actual
 * navigation targets for the navigation type that is specified in the `NavigateAction`.
 * Once the navigation targets are available, it will trigger a `NavigateToTargetAction` to actually
 * perform the navigation.
 *
 * In other scenarios, clients may also trigger the `NavigateToTargetAction` directly, e.g. when opening
 * the diagram.
 *
 * Depending on the URI and arguments of the navigation target we may encounter three cases:
 *   *(a)* the navigation target already specifies element IDs, in which case this action handler navigates
 *         to the specified elements directly, by the selecting them and centering them in the viewport.
 *   *(b)* the arguments of the navigation targets don't contain element IDs, but other arguments, the
 *         navigation target needs to be resolved into actual elment IDs by the `NavigationTargetResolver`.
 *         This can for instance be useful, if the navigation deals with queries or some other more complex
 *         logic that can't be dealt with on the client.
 *  *(c)* the target isn't resolved by the `NavigationTargetResolver`, e.g. because the `uri` doesn't match
 *        the URI of the current diagram. In this case, the navigation request is forwarded by dispatching
 *        a `NavigateToExternalTargetAction`.
 */
@injectable()
export class NavigationActionHandler implements IActionHandler {
    readonly notificationTimeout = 5000;

    @inject(TYPES.ILogger) protected logger: ILogger;
    @inject(TYPES.IActionDispatcher) protected dispatcher: IActionDispatcher;
    @inject(TYPES.ActionHandlerRegistryProvider) protected actionHandlerRegistryProvider: () => Promise<ActionHandlerRegistry>;
    @inject(GLSP_TYPES.IEditorContextServiceProvider) protected editorContextService: EditorContextServiceProvider;
    @inject(NavigationTargetResolver) protected resolver: NavigationTargetResolver;

    handle(action: Action): ICommand | Action | void {
        if (isNavigateAction(action)) {
            this.handleNavigateAction(action);
        } else if (isNavigateToTargetAction(action)) {
            this.handleNavigateToTarget(action);
        } else if (isProcessNavigationArgumentsAction(action)) {
            this.processNavigationArguments(action.args);
        } else if (isNavigateToExternalTargetAction(action)) {
            this.handleNavigateToExternalTarget(action);
        }
    }

    protected async handleNavigateAction(action: NavigateAction): Promise<void> {
        try {
            const editorContextService = await this.editorContextService();
            const context = editorContextService.get(action.args);
            const response = await this.dispatcher.request(new RequestNavigationTargetsAction(action.targetTypeId, context));
            if (isSetNavigationTargetsAction(response) && response.targets && response.targets.length === 1) {
                if (response.targets.length > 1) {
                    this.logger.warn(
                        this,
                        'Processing of multiple targets is not supported yet. ' + 'Only the first is being processed.',
                        response.targets
                    );
                }
                return this.dispatcher.dispatch(new NavigateToTargetAction(response.targets[0]));
            }
            this.warnAboutFailedNavigation('No valid navigation target found');
        } catch (reason) {
            this.logger.error(this, 'Failed to obtain navigation target', reason, action);
        }
    }

    protected async handleNavigateToTarget(action: NavigateToTargetAction): Promise<void> {
        try {
            const resolvedElements = await this.resolveElements(action);
            if (this.containsElementIdsOrArguments(resolvedElements)) {
                this.navigateTo(resolvedElements);
                this.handleResolutionArguments(resolvedElements);
                return;
            } else {
                this.navigateToExternal(action.target);
                return;
            }
        } catch (reason) {
            this.logger.error(this, 'Failed to navigate', reason, action);
        }
    }

    protected resolveElements(action: NavigateToTargetAction): Promise<SetResolvedNavigationTargetAction | undefined> {
        return this.resolver.resolve(action.target);
    }

    protected containsElementIdsOrArguments(
        target: SetResolvedNavigationTargetAction | undefined
    ): target is SetResolvedNavigationTargetAction {
        return target !== undefined && (this.containsElementIds(target.elementIds) || this.containsArguments(target.args));
    }

    protected containsElementIds(elementIds: string[] | undefined): elementIds is string[] {
        return elementIds !== undefined && elementIds.length > 0;
    }

    protected containsArguments(args: Args | undefined): args is Args {
        return args !== undefined && args !== undefined && Object.keys(args).length > 0;
    }

    protected navigateTo(target: SetResolvedNavigationTargetAction): void {
        const elementIds = target.elementIds;
        if (!this.containsElementIds(elementIds)) {
            return;
        }
        this.dispatcher.dispatchAll([new SelectAllAction(false), new SelectAction(elementIds), new CenterAction(elementIds)]);
    }

    protected handleResolutionArguments(target: SetResolvedNavigationTargetAction): void {
        const args = target.args;
        if (!this.containsArguments(args)) {
            return;
        }
        this.dispatcher.dispatch(new ProcessNavigationArgumentsAction(args));
    }

    protected navigateToExternal(target: NavigationTarget): Promise<void> {
        return this.dispatcher.dispatch(new NavigateToExternalTargetAction(target));
    }

    protected processNavigationArguments(args: Args): void {
        if (args.info && args.info.toString().length > 0) {
            this.notify('INFO', args.info.toString());
        }
        if (args.warning && args.warning.toString().length > 0) {
            this.notify('WARNING', args.warning.toString());
        }
        if (args.error && args.error.toString().length > 0) {
            this.notify('ERROR', args.error.toString());
        }
    }

    protected async handleNavigateToExternalTarget(action: NavigateToExternalTargetAction): Promise<void> {
        const registry = await this.actionHandlerRegistryProvider();
        const handlers = registry.get(NavigateToExternalTargetAction.KIND);
        if (handlers.length === 1) {
            // we are the only handler so we know nobody took care of it
            this.warnAboutFailedNavigation('Could not resolve or navigate to target', action.target);
        }
    }

    protected warnAboutFailedNavigation(msg: string, target?: NavigationTarget): void {
        const message = `${msg}` + (target ? `: '${target.uri}' (arguments: ${JSON.stringify(target.args)})` : '');
        this.logger.warn(this, msg, target);
        this.notify('WARNING', message);
    }

    private notify(severity: string, message: string): void {
        const timeout = this.notificationTimeout;
        this.dispatcher.dispatchAll([
            { kind: GLSPServerStatusAction.KIND, severity, timeout, message } as GLSPServerStatusAction,
            { kind: ServerMessageAction.KIND, severity, timeout, message } as ServerMessageAction
        ]);
    }
}
