package org.openbpmn.glsp.utils;

import java.util.Optional;

import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.core.BPMNPoint;

/**
 * Helper Class to be used when a new Element is added to the diagram panel. It
 * snaps the initial coordinates to 10x10 px.
 */
public class GridSnapper {
    public static final double GRID_X = 10.0;
    public static final double GRID_Y = 10.0;

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

    public static BPMNPoint snap(final double elementX, double elementY) {
        double snappedX = Math.round(elementX / GRID_X) * GRID_X;
        double snappedY = Math.round(elementY / GRID_Y) * GRID_Y;
        return new BPMNPoint(snappedX, snappedY);
    }

    /**
     * Snaps a x or y coordinate...
     * 
     * @param pos
     * @return
     */
    public static double snap(final double pos) {
        return Math.round(pos / GRID_X) * GRID_X;
    }

}