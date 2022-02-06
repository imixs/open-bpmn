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
import { RequestAction, ResponseAction } from '.';
import { isArray } from '../utils/typeguard-util';
import { isActionKind } from './base-protocol';

export interface TypeHint {
    /**
     * The id of the element.
     */
    readonly elementTypeId: string;

    /**
     * Specifies whether the element can be relocated.
     */
    readonly repositionable: boolean;

    /**
     * Specifies whether the element can be deleted
     */
    readonly deletable: boolean;
}

export interface ShapeTypeHint extends TypeHint {
    /**
     * Specifies whether the element can be resized.
     */
    readonly resizable: boolean;

    /**
     * Specifies whether the element can be moved to another parent
     */
    readonly reparentable: boolean;

    /**
     * The types of elements that can be contained by this element (if any)
     */
    readonly containableElementTypeIds?: string[];
}

export interface EdgeTypeHint extends TypeHint {
    /**
     * Specifies whether the routing of this element can be changed.
     */
    readonly routable: boolean;

    /**
     * Allowed source element types for this edge type
     */
    readonly sourceElementTypeIds: string[];

    /**
     * Allowed targe element types for this edge type
     */
    readonly targetElementTypeIds: string[];
}

/**
 * Sent from the client to the server in order to request hints on whether certain modifications are allowed for a specific element type.
 * The `RequestTypeHintsAction` is optional, but should usually be among the first messages sent from the client to the server after
 * receiving the model via RequestModelAction. The response is a {@link SetTypeHintsAction}.
 */
export class RequestTypeHintsAction implements RequestAction<SetTypeHintsAction> {
    static readonly KIND = 'requestTypeHints';
    constructor(public readonly requestId = '', public readonly kind: string = RequestTypeHintsAction.KIND) {}
}

export function isRequestTypeHintsAction(action: any): action is RequestTypeHintsAction {
    return isActionKind(action, RequestTypeHintsAction.KIND);
}

/**
 * Sent from the server to the client in order to provide hints certain modifications are allowed for a specific element type.
 */
export class SetTypeHintsAction implements ResponseAction {
    static readonly KIND = 'setTypeHints';
    constructor(
        public readonly shapeHints: ShapeTypeHint[],
        public readonly edgeHints: EdgeTypeHint[],
        public readonly responseId = '',
        public readonly kind: string = SetTypeHintsAction.KIND
    ) {}
}

export function isSetTypeHintsAction(action: any): action is SetTypeHintsAction {
    return isActionKind(action, SetTypeHintsAction.KIND) && isArray(action, 'shapeHints') && isArray(action, 'edgeHints');
}
