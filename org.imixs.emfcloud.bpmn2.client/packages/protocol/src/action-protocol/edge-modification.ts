/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
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
import { isActionKind, Operation } from './base-protocol';
import { ElementAndRoutingPoints } from './types';

/**
 * If the source and/or target element of an edge should be adapted, the client can send a `ReconnectEdgeOperation` to the server.
 */
export class ReconnectEdgeOperation implements Operation {
    static readonly KIND = 'reconnectEdge';
    constructor(
        public readonly edgeElementId: string,
        public readonly sourceElementId: string,
        public readonly targetElementId: string,
        public readonly kind: string = ReconnectEdgeOperation.KIND
    ) {}
}

export function isReconnectEdgeOperation(action: any): action is ReconnectEdgeOperation {
    return (
        isActionKind(action, ReconnectEdgeOperation.KIND) &&
        isString(action, 'edgeElementId') &&
        isString(action, 'sourceElementId') &&
        isString(action, 'targetElementId')
    );
}

/**
 * An edge may have zero or more routing points that "re-direct" the edge between the source and the target element. In order to set these
 * routing points the client may send a `ChangeRoutingPointsOperation`.
 */
export class ChangeRoutingPointsOperation implements Operation {
    static readonly KIND = 'changeRoutingPoints';
    constructor(public newRoutingPoints: ElementAndRoutingPoints[], public readonly kind: string = ChangeRoutingPointsOperation.KIND) {}
}

export function isChangeRoutingsPointsOperation(action: any): action is ChangeRoutingPointsOperation {
    return isActionKind(action, ChangeRoutingPointsOperation.KIND) && isArray(action, 'newRoutingPoints');
}
