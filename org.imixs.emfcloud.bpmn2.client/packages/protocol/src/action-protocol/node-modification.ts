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
import { ElementAndBounds, Point } from './types';

/**
 * Triggers the position or size change of elements. This action concerns only the element's graphical size and position.
 * Whether an element can be resized or repositioned may be specified by the server with a `TypeHint` to allow for immediate user feedback
 * before resizing or repositioning.
 */
export class ChangeBoundsOperation implements Operation {
    static readonly KIND = 'changeBounds';
    constructor(public newBounds: ElementAndBounds[], public readonly kind: string = ChangeBoundsOperation.KIND) {}
}

export function isChangeBoundsOperation(action: any): action is ChangeBoundsOperation {
    return isActionKind(action, ChangeBoundsOperation.KIND) && isArray(action, 'newBounds');
}

/**
 * The client sends a `ChangeContainerOperation` to the server to request the execution of a changeContainer operation.
 */
export class ChangeContainerOperation implements Operation {
    static readonly KIND = 'changeContainer';
    constructor(
        public readonly elementId: string,
        public readonly targetContainerId: string,
        public readonly location?: Point,
        public readonly kind: string = ChangeContainerOperation.KIND
    ) {}
}

export function isChangeContainerOperation(action: any): action is ChangeContainerOperation {
    return isActionKind(action, ChangeContainerOperation.KIND) && isString(action, 'elementId') && isString(action, 'targetContainerId');
}
