/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH,
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You can receive a copy of the GNU General Public
 * License at http://www.gnu.org/licenses/gpl.html
 *
 * Project:
 *     https://github.com/imixs/open-bpmn
 *
 * Contributors:
 *     Imixs Software Solutions GmbH - Project Management
 *     Ralph Soika - Software Developer
 ********************************************************************************/
package org.openbpmn.glsp.utils;

import java.util.Optional;

import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.util.GraphUtil;

/**
 * Helper Class to snap elements to a 10x10 pixel grid
 *
 * @author rsoika
 *
 */
public final class GridSnapper {
    public static final double GRID_X = 5.0;
    public static final double GRID_Y = 5.0;

    private GridSnapper() {
    }

    public static GPoint snap(final GPoint originalpoint) {
        double snappedX = Math.round(originalpoint.getX() / GRID_X) * GRID_X;
        double snappedY = Math.round(originalpoint.getY() / GRID_Y) * GRID_Y;
        return GraphUtil.point(snappedX, snappedY);
    }

    public static Optional<GPoint> snap(final Optional<GPoint> originalPoint) {
        return originalPoint.map(GridSnapper::snap);
    }

}
