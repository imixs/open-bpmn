/********************************************************************************
 * Copyright (c) 2021 STMicroelectronics and others.
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

import { isString } from '../utils/typeguard-util';
import { Action, isAction } from './base-protocol';
import { Args } from './types';

export abstract class TriggerElementCreationAction implements Action {
    constructor(public readonly elementTypeId: string, readonly args?: Args, public readonly kind: string = 'unknown') {}
}

export function isTriggerElementTypeCreationAction(action: any): action is TriggerElementCreationAction {
    return isAction(action) && isString(action, 'elementTypeId');
}

/**
 * Triggers the enablement of the tool that is responsible for creating nodes and initializes it with the creation of nodes of the given
 * `elementTypeId`.
 */
export class TriggerNodeCreationAction extends TriggerElementCreationAction {
    static readonly KIND = 'triggerNodeCreation';

    constructor(public readonly elementTypeId: string, readonly args?: Args, public readonly kind = TriggerNodeCreationAction.KIND) {
        super(elementTypeId, args, kind);
    }
}

export function isTriggerNodeCreationAction(action: any): action is TriggerNodeCreationAction {
    return isTriggerElementTypeCreationAction(action) && action.kind === TriggerNodeCreationAction.KIND;
}

/**
 * Triggers the enablement of the tool that is responsible for creating edges and initializes it with the creation of edges of the given
 * `elementTypeId`.
 */
export class TriggerEdgeCreationAction extends TriggerElementCreationAction {
    static readonly KIND = 'triggerEdgeCreation';

    constructor(
        public readonly elementTypeId: string,
        readonly args?: Args,
        public readonly kind: string = TriggerEdgeCreationAction.KIND
    ) {
        super(elementTypeId, args, kind);
    }
}

export function isTriggerEdgeCreationAction(action: any): action is TriggerEdgeCreationAction {
    return isTriggerElementTypeCreationAction(action) && action.kind === TriggerEdgeCreationAction.KIND;
}
