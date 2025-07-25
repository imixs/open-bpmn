package org.openbpmn.glsp.utils;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GDimension;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;

/**
 * The BPMNGridSnapper is a Helper Class be used when a new Element is added to
 * the diagram panel or a BoundOperation is send. It snaps the an BPMN Element
 * to the Grid Size of 10x10 pixel.
 */
public class BPMNGridSnapper {
    private static Logger logger = Logger.getLogger(BPMNGridSnapper.class.getName());

    public static final double GRID_SIZE = 10.0;

    private BPMNGridSnapper() {
    }

    /**
     * This method snaps, based on a given GPoint, a BPMN element to the Grid Size.
     * This method is called by the center method and the BoundsOperations.
     * 
     * @param pos
     * @return
     */
    public static GPoint snap(final BPMNElementNode elementNode, final GPoint point) {
        double x = point.getX();
        double y = point.getY();
        x = Math.round(x / GRID_SIZE) * GRID_SIZE;
        y = Math.round(y / GRID_SIZE) * GRID_SIZE;
        // In casse of an event we need to adjust the offset!
        if (elementNode instanceof Event) {
            x = x - 3;
            y = y - 3;
        }
        return GraphUtil.point(x, y);
    }

    /**
     * Helper method that rounds the x/y coordinates of a GPoint
     * 
     * @param point
     * @return
     */
    public static GPoint round(final GPoint point) {
        double x = Math.round(point.getX());
        double y = Math.round(point.getY());
        return GraphUtil.point(x, y);
    }

    /**
     * Helper method to snap routing points to the grid
     */
    public static void round(List<GPoint> points) {
        for (GPoint point : points) {
            // Update the existing point's coordinates
            point.setX(Math.round(point.getX()));
            point.setY(Math.round(point.getY()));
        }
    }

    /**
     * Helper method that rounds the width/height of a GDimension
     * 
     * @param point
     * @return
     */
    public static GDimension round(final GDimension dimension) {
        double w = Math.round(dimension.getWidth());
        double h = Math.round(dimension.getHeight());
        return GraphUtil.dimension(w, h);
    }

    /**
     * This method snaps, based on a given GPoint, a BPMNElement to its center and
     * the Grid Size. This method is called by CreationHandlers
     * 
     * @param elementNode
     * @param point
     * @return a BPMNPoint
     */
    public static BPMNPoint centerBPMNPoint(final BPMNElementNode elementNode, final GPoint point) {
        // center
        point.setX(point.getX() - (elementNode.getDefaultWidth() / 2));
        point.setY(point.getY() - (elementNode.getDefaultHeight() / 2));
        GPoint snapPoint = snap(elementNode, point);
        return new BPMNPoint(snapPoint.getX(), snapPoint.getY());
    }

    /**
     * This method snaps, based on a given GPoint, a BPMNElement to
     * the Grid Size. This method is called by CreationHandlers
     * 
     * @param elementNode
     * @param point
     * @return a BPMNPoint
     */
    public static BPMNPoint snapBPMNPoint(final BPMNElementNode elementNode, final GPoint point) {
        double x = point.getX();
        double y = point.getY();
        x = Math.round(x / GRID_SIZE) * GRID_SIZE;
        y = Math.round(y / GRID_SIZE) * GRID_SIZE;
        // In casse of an event we need to adjust the offset!
        if (elementNode instanceof Event) {
            x = x - 3;
            y = y - 3;
        }
        return new BPMNPoint(x, y);
    }

    /**
     * Snaps a BPMN element based on its current position.
     * This method is used by the AutoAlinOperation which snaps all elements
     * automatically to the grid.
     * 
     * @param pos
     * @return
     * @throws BPMNMissingElementException
     */
    public static void snap(final BPMNElementNode elementNode) {
        try {
            logger.finest("...snap " + elementNode.getId() + "  Pos: " + elementNode.getBounds().getPosition());
            double x = elementNode.getBounds().getPosition().getX();
            double y = elementNode.getBounds().getPosition().getY();
            // default snaping to current gridsize
            x = Math.round(x / GRID_SIZE) * GRID_SIZE;
            y = Math.round(y / GRID_SIZE) * GRID_SIZE;
            // In casse of an event we need to adjust the offset!
            if (elementNode instanceof Event) {
                x = x - 3;
                y = y - 3;
            }
            // set new position.....
            elementNode.setPosition(x, y);
            logger.finest("...after snap " + elementNode.getId() + "  Pos: " + elementNode.getBounds().getPosition());

            // snap bounds for tasks and pools to the current gridsize
            if (elementNode instanceof Participant || elementNode instanceof Activity) {
                double w = elementNode.getBounds().getDimension().getWidth();
                double h = elementNode.getBounds().getDimension().getHeight();
                elementNode.setDimension(w, h);
            }
        } catch (BPMNMissingElementException e) {
            // It may happen that an element (e.g. an default particpant) does not have a
            // BPMNBounds object. In this case we ignore the snap call
            logger.fine(
                    "Skipping snap for " + elementNode.getId() + " : "
                            + e.getMessage());
        }
    }
}