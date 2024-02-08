package org.openbpmn.glsp.utils;

import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;

/**
 * Helper Class to be used when a new Element is added to the diagram panel. It
 * snaps the initial coordinates to 10x10 px, depending on the element type
 */
public class BPMNGridSnapper {
    private static Logger logger = Logger.getLogger(BPMNGridSnapper.class.getName());

    public static final double GRID_X = 10.0;
    public static final double GRID_Y = 10.0;

    private BPMNGridSnapper() {
    }

    public static GPoint snap(final GPoint originalpoint) {
        double snappedX = Math.round(originalpoint.getX() / GRID_X) * GRID_X;
        double snappedY = Math.round(originalpoint.getY() / GRID_Y) * GRID_Y;
        return GraphUtil.point(snappedX, snappedY);
    }

    public static Optional<GPoint> snap(final Optional<GPoint> originalPoint) {
        return originalPoint.map(BPMNGridSnapper::snap);
    }

    public static BPMNPoint snap(final double elementX, double elementY) {
        double snappedX = Math.round(elementX / GRID_X) * GRID_X;
        double snappedY = Math.round(elementY / GRID_Y) * GRID_Y;
        return new BPMNPoint(snappedX, snappedY);
    }

    /**
     * Snaps a BPMN element based on a given point to the center of the element.
     * 
     * @param pos
     * @return
     */
    public static BPMNPoint snap(final BPMNElementNode elementNode, final GPoint point) {
        // center
        double x = point.getX() - (elementNode.getDefaultWidth() / 2);
        double y = point.getY() - (elementNode.getDefaultHeight() / 2);
        // snap
        x = Math.round(x / GRID_X) * GRID_X;
        y = Math.round(y / GRID_Y) * GRID_Y;

        // compute offset
        if (elementNode instanceof Event) {
            // In casse of an event we need to adjust the offset!
            x = x - 3;
            y = y - 3;
        }

        return new BPMNPoint(x, y);
    }

    /**
     * Snaps a BPMN element based on a given point to the center of the element.
     * 
     * @param pos
     * @return
     * @throws BPMNMissingElementException
     */
    public static void snap(final BPMNElementNode elementNode) throws BPMNMissingElementException {

        logger.finest("...snap " + elementNode.getId() + "  Pos: " + elementNode.getBounds().getPosition());
        double x = elementNode.getBounds().getPosition().getX();
        double y = elementNode.getBounds().getPosition().getY();
        // snap
        x = Math.round(x / GRID_X) * GRID_X;
        y = Math.round(y / GRID_Y) * GRID_Y;

        // compute offset
        if (elementNode instanceof Event) {
            // In casse of an event we need to adjust the offset!
            x = x - 3;
            y = y - 3;
        }
        // update pos
        elementNode.setPosition(x, y);
        logger.finest("...after snap " + elementNode.getId() + "  Pos: " + elementNode.getBounds().getPosition());

        // snap bounds for tasks and pools
        if (elementNode instanceof Participant || elementNode instanceof Activity) {
            double w = elementNode.getBounds().getDimension().getWidth();
            double h = elementNode.getBounds().getDimension().getHeight();
            elementNode.setDimension(w, h);
        }
    }

}