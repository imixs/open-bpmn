/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.imixs.emfcloud.bpmn2.handler;

import java.util.Optional;

import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.util.GraphUtil;

public final class GridSnapper {
   public static final double GRID_X = 10.0;
   public static final double GRID_Y = 10.0;

   private GridSnapper() {}

   public static GPoint snap(final GPoint originalpoint) {
      double snappedX = Math.round(originalpoint.getX() / GRID_X) * GRID_X;
      double snappedY = Math.round(originalpoint.getY() / GRID_Y) * GRID_Y;
      return GraphUtil.point(snappedX, snappedY);
   }

   public static Optional<GPoint> snap(final Optional<GPoint> originalPoint) {
      return originalPoint.map(GridSnapper::snap);
   }

}
