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
import { Action, isSetModelAction, UpdateModelAction } from '@eclipse-glsp/protocol';
import { inject, injectable, multiInject, optional, postConstruct } from 'inversify';
import {
    ActionHandlerRegistry,
    Command,
    CommandActionHandler,
    CommandExecutionContext,
    CommandReturn,
    IActionHandler,
    ILogger,
    SModelRoot,
    TYPES,
    UpdateModelCommand
} from 'sprotty';
import { IFeedbackActionDispatcher } from '../../features/tool-feedback/feedback-action-dispatcher';
import { FeedbackCommand } from '../../features/tool-feedback/model';
import { GLSP_TYPES } from '../types';

/* ActionHandler that transforms a SetModelAction into an (feedback-aware) UpdateModelAction. This can be done because in sprotty
 *  UpdateModel behaves the same as SetModel if no model is present yet.*/
@injectable()
export class SetModelActionHandler implements IActionHandler {
    handle(action: Action): Action | void {
        if (isSetModelAction(action)) {
            return new UpdateModelAction(action.newRoot, false);
        }
    }
}

export interface SModelRootListener {
    modelRootChanged(root: Readonly<SModelRoot>): void;
}

/**
 * A special`UpdateModelCommand` that retrieves all registered `actions` from the `IFeedbackActionDispatcher` (if present) and applies their
 * feedback to the `newRoot` before performing the update
 */
@injectable()
export class FeedbackAwareUpdateModelCommand extends UpdateModelCommand {
    @inject(TYPES.ILogger) protected logger: ILogger;
    @inject(GLSP_TYPES.IFeedbackActionDispatcher) @optional() protected readonly feedbackActionDispatcher: IFeedbackActionDispatcher;
    @inject(TYPES.ActionHandlerRegistryProvider) protected actionHandlerRegistryProvider: () => Promise<ActionHandlerRegistry>;
    @multiInject(GLSP_TYPES.SModelRootListener) @optional() protected modelRootListeners: SModelRootListener[] = [];

    protected actionHandlerRegistry?: ActionHandlerRegistry;

    constructor(@inject(TYPES.Action) action: UpdateModelAction) {
        super(action);
    }

    @postConstruct()
    protected initialize(): void {
        this.actionHandlerRegistryProvider().then(registry => (this.actionHandlerRegistry = registry));
    }

    protected performUpdate(oldRoot: SModelRoot, newRoot: SModelRoot, context: CommandExecutionContext): CommandReturn {
        if (this.feedbackActionDispatcher && this.actionHandlerRegistry) {
            // Create a temporary context wich defines the `newRoot` as `root`
            // This way we do not corrupt the redo/undo behavior of the super class
            const tempContext: CommandExecutionContext = {
                root: newRoot,
                duration: context.duration,
                logger: context.logger,
                modelChanged: context.modelChanged,
                modelFactory: context.modelFactory,
                syncer: context.syncer
            };

            const feedbackCommands = this.getFeedbackCommands(this.actionHandlerRegistry);
            feedbackCommands.forEach(command => command.execute(tempContext));
        }

        this.modelRootListeners.forEach(listener => listener.modelRootChanged(newRoot));
        return super.performUpdate(oldRoot, newRoot, context);
    }

    protected getFeedbackCommands(registry: ActionHandlerRegistry): Command[] {
        const result: Command[] = [];
        this.feedbackActionDispatcher.getRegisteredFeedback().forEach(action => {
            const handler = registry.get(action.kind).find(h => h instanceof CommandActionHandler);
            if (handler instanceof CommandActionHandler) {
                result.push(handler.handle(action));
            }
        });
        // sort commands descanding by priority
        return result.sort((a, b) => getPriority(b) - getPriority(a));
    }
}

function getPriority(command: Command): number {
    if (command instanceof FeedbackCommand) {
        return command.priority;
    }
    return 0;
}
