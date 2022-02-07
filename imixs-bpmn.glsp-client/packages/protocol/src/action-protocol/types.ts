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
import { Action } from './base-protocol';

export type JsonPrimitive = string | number | boolean;

/**
 * A key-value pair structure for primitive typed custom arguments.
 */
export interface Args {
    [key: string]: JsonPrimitive;
}

export interface Point {
    readonly x: number;
    readonly y: number;
}

export const ORIGIN_POINT: Point = Object.freeze({
    x: 0,
    y: 0
});

export interface Dimension {
    readonly width: number;
    readonly height: number;
}

export const EMPTY_DIMENSION: Dimension = Object.freeze({
    width: -1,
    height: -1
});

/**
 * The bounds are the position (x, y) and dimension (width, height) of an object.
 */
export interface Bounds extends Point, Dimension {}

export const EMPTY_BOUNDS: Bounds = Object.freeze({ ...EMPTY_DIMENSION, ...ORIGIN_POINT });

export interface ElementAndBounds {
    elementId: string;
    newPosition?: Point;
    newSize: Dimension;
}

/**
 * Associates a new alignment with a model element, which is referenced via its id.
 */
export interface ElementAndAlignment {
    elementId: string;
    newAlignment: Point;
}

/**
 * The `ElementAndRoutingPoints` type is used to associate an edge with specific routing points.
 */
export interface ElementAndRoutingPoints {
    /**
     * The identifier of an element.
     */
    elementId: string;

    /**
     * The new list of routing points.
     */
    newRoutingPoints?: Point[];
}

/**
 * The `EditorContext` may be used to represent the current state of the editor for particular actions.
 * It encompasses the last recorded mouse position, the list of selected elements, and may contain
 * custom arguments to encode additional state information.
 */
export interface EditorContext {
    /**
     * The list of selected element identifiers.
     */
    readonly selectedElementIds: string[];

    /**
     * The last recorded mouse position.
     */
    readonly lastMousePosition?: Point;

    /**
     * Custom arguments.
     */
    readonly args?: Args;
}

/**
 *Labeled actions are used to denote a group of actions in a user-interface context, e.g., to define an entry in the command palette or
 *in the context menu.
 */
export class LabeledAction {
    constructor(readonly label: string, readonly actions: Action[], readonly icon?: string) {}
}

export function isLabeledAction(element: any): element is LabeledAction {
    return element !== undefined && typeof element === 'object' && 'label' in element && 'actions' in element;
}

export type JsonAny = JsonPrimitive | Args | JsonArray | null;

export interface JsonArray extends Array<JsonAny> {}
