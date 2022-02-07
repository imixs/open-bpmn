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
import { Bounds, Point } from '@eclipse-glsp/protocol';
import {
    BoundsAware,
    Dimension,
    findParentByFeature,
    isAlignable,
    isViewport,
    ORIGIN_POINT,
    SModelElement,
    translateBounds,
    Viewport
} from 'sprotty';

/**
 * Return the position corresponding to this mouse event (Browser coordinates)
 * in the diagram coordinates system (i.e. relative to the Diagram's 0;0 point)
 *
 * This functions takes into account the following transformations:
 * - Location of the Diagram Canvas inside of the browser's page
 * - Current viewport Scroll and Zoom
 *
 * @param target
 *  An element from the diagram
 * @param mouseEvent
 *  A mouseEvent
 */
export function getAbsolutePosition(target: SModelElement, mouseEvent: MouseEvent): Point {
    let xPos = mouseEvent.pageX;
    let yPos = mouseEvent.pageY;
    const canvasBounds = target.root.canvasBounds;
    xPos -= canvasBounds.x;
    yPos -= canvasBounds.y;

    const viewport: Viewport | undefined = findParentByFeature(target, isViewport);
    const zoom = viewport ? viewport.zoom : 1;
    if (viewport) {
        const scroll: Point = { x: viewport.scroll.x, y: viewport.scroll.y };
        xPos += scroll.x * zoom;
        yPos += scroll.y * zoom;

        xPos /= zoom;
        yPos /= zoom;
    }

    return {
        x: xPos,
        y: yPos
    };
}

/**
 * Translates the bounds of the diagram element (local coordinates) into the diagram coordinates system
 * (i.e. relative to the Diagram's 0;0 point)
 *
 * @param target  A bounds-aware element from the diagram
 */
export function toAbsoluteBounds(element: SModelElement & BoundsAware): Bounds {
    const location = isAlignable(element) ? element.alignment : ORIGIN_POINT;
    const x = location.x;
    const y = location.y;
    const width = element.bounds.width;
    const height = element.bounds.height;
    return translateBounds({ x, y, width, height }, element, element.root);
}

/**
 * Translates the position of the diagram element (local coordinates) into the diagram coordinates system
 * (i.e. relative to the Diagram's 0;0 point)
 *
 * @param target  A bounds-aware element from the diagram
 */
export function toAbsolutePosition(target: SModelElement & BoundsAware): Point {
    return toAbsoluteBounds(target);
}

/**
 * Translates the size of the diagram element (local coordinates) into the diagram coordinates system
 * (i.e. relative to the Diagram's 0;0 point)
 *
 * @param target  A bounds-aware element from the diagram
 */
export function toAbsoluteSize(target: SModelElement & BoundsAware): Dimension {
    return toAbsoluteBounds(target);
}
