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

export namespace DefaultTypes {
    // HTML elements
    export const HTML = 'html';

    // generic types
    export const FOREIGN_OBJECT = 'foreign-object';
    export const PRE_RENDERED = 'pre-rendered';
    export const SHAPE_PRE_RENDERED = 'shape-pre-rendered';

    // SVG elements
    export const SVG = 'svg';

    // graph types
    export const GRAPH = 'graph';
    export const NODE = 'node';
    export const COMPARTMENT = 'comp';
    export const COMPARTMENT_HEADER = 'comp:header';
    export const EDGE = 'edge';
    export const PORT = 'port';
    export const ROUTING_POINT = 'routing-point';
    export const VOLATILE_ROUTING_POINT = `volatile-${ROUTING_POINT}`;
    export const LABEL = 'label';

    // UI elements
    export const BUTTON_EXPAND = 'button:expand';
    export const ISSUE_MARKER = 'marker';

    // shapes
    export const NODE_CIRCLE = 'node:circle';
    export const NODE_RECTANGLE = 'node:rectangle';
    export const NODE_DIAMOND = 'node:diamond';
}
