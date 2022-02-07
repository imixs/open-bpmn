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

import { isArray, isString } from '../utils/typeguard-util';
import { isAction, isActionKind, Operation } from './base-protocol';
import { Args, Point } from './types';

export interface CreateOperation extends Operation {
    elementTypeId: string;
    args?: Args;
}

export function isCreateOperation(action: any): action is CreateOperation {
    return isAction(action) && isString(action, 'elementTypeId');
}
/**
 * In order to create a node in the model the client can send a CreateNodeOperation with the necessary information to create that node.
 */
export class CreateNodeOperation implements CreateOperation {
    static readonly KIND = 'createNode';

    constructor(
        public readonly elementTypeId: string,
        public location?: Point,
        public containerId?: string,
        public args?: Args,
        public readonly kind: string = CreateNodeOperation.KIND
    ) {}
}

export function isCreateNodeOperation(action: any): action is CreateNodeOperation {
    return isActionKind(action, CreateNodeOperation.KIND) && isString(action, 'elementTypeId');
}

/**
 * In order to create an edge in the model the client can send a `CreateEdgeOperation` with the necessary information to create that edge.
 */
export class CreateEdgeOperation implements CreateOperation {
    static readonly KIND = 'createEdge';

    constructor(
        public readonly elementTypeId: string,
        public sourceElementId: string,
        public targetElementId: string,
        public args?: Args,
        public readonly kind: string = CreateEdgeOperation.KIND
    ) {}
}

export function isCreateEdgeOperation(action: any): action is CreateEdgeOperation {
    return (
        isActionKind(action, CreateEdgeOperation.KIND) &&
        isString(action, 'elementTypeId') &&
        isString(action, 'sourceElementId') &&
        isString(action, 'targetElementId')
    );
}

/**
 * The client sends a `DeleteElementOperation` to the server to request the deletion of an element from the model.
 */
export class DeleteElementOperation implements Operation {
    static readonly KIND = 'deleteElement';
    constructor(readonly elementIds: string[], public readonly kind: string = DeleteElementOperation.KIND) {}
}

export function isDeleteElementOperation(action: any): action is DeleteElementOperation {
    return isActionKind(action, DeleteElementOperation.KIND) && isArray(action, 'elementIds');
}
