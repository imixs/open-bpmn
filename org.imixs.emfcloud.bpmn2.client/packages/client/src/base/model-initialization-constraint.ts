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
import { Action, UpdateModelAction } from '@eclipse-glsp/protocol';
import { injectable } from 'inversify';
import { InitializeCanvasBoundsAction } from 'sprotty';
import { Deferred } from 'sprotty/lib/utils/async';

/**
 * The constraint defining when the initialization of the GLSP model is completed.
 *
 * Many actions, such as the `CenterAction`, can only be successfully processed if
 * the GLSP model initialization is completed, that is, the model has been set,
 * its bounds have been computed, the canvas bounds are available, etc.
 *
 * An injectable implementation of this constraint will be used by the
 * `GLSPActionDispatcher` to determine when the initialization is completed.
 * The action dispatcher therefore provides a promise via `onceInitialized()`
 * to trigger clients that want to dispatch an action, once the initialization
 * is done.
 *
 * For most of the cases `DefaultInitializationConstraint` can be used. In fact,
 * it is bound by default. However, custom implementations can rebind other
 * implementations of this constraint to, for instance, delay further before the
 * `onceInitialized()` promise is fulfilled by the `GLSPActionDispatcher`.
 */
@injectable()
export abstract class ModelInitializationConstraint {
    protected completion: Deferred<void> = new Deferred();
    protected completed = false;

    get isCompleted(): boolean {
        return this.completed;
    }

    protected setCompleted(isCompleted: boolean): void {
        this.completed = isCompleted;
        if (isCompleted) {
            this.completion.resolve();
        }
    }

    onInitialized(): Promise<void> {
        return this.completion.promise;
    }

    notifyDispatched(action: Action): void {
        if (this.isCompleted) {
            return;
        }
        if (this.isInitializedAfter(action)) {
            this.setCompleted(true);
        }
    }

    abstract isInitializedAfter(action: Action): boolean;
}

/**
 * Default initialization constraint triggers after a non-empty `UpdateModelAction`
 * and a subsequent `InitializeCanvasBoundsAction`.
 */
@injectable()
export class DefaultModelInitializationConstraint extends ModelInitializationConstraint {
    protected seenNonEmptyUpdateModel = false;

    isInitializedAfter(action: Action): boolean {
        if (this.isNonEmptyUpdateModel(action)) {
            this.seenNonEmptyUpdateModel = true;
        } else if (this.seenNonEmptyUpdateModel && action.kind === InitializeCanvasBoundsAction.KIND) {
            return true;
        }
        return false;
    }

    protected isNonEmptyUpdateModel(action: Action): boolean {
        if (action && action.kind === UpdateModelAction.KIND) {
            const updateModelAction = action as UpdateModelAction;
            return updateModelAction.newRoot !== undefined && updateModelAction.newRoot.type !== 'NONE';
        }
        return false;
    }
}
