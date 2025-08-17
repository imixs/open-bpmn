
/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
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
package org.openbpmn.glsp.operations;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.glsp.graph.GBoundsAware;
import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.operations.ChangeRoutingPointsOperation;
import org.eclipse.glsp.server.operations.GModelOperationHandler;
import org.eclipse.glsp.server.types.ElementAndRoutingPoints;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Association;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.utils.BPMNGridSnapper;

import com.google.inject.Inject;

/**
 * This OperationHandler update the Routing Points of a BPMNSequenceFlow.
 * <p>
 * NOTE: GLSP only provides the new routing points here without the Source and
 * Target point. In BPMN the di:waypoint list contains also the source and
 * target point. So the GModel Routing Point list can be empty and the BPMN
 * di:waypoint list contains as a minimum 2 points!
 *
 * @author rsoika
 *
 */
public class BPMNChangeRoutingPointsOperationHandler extends GModelOperationHandler<ChangeRoutingPointsOperation> {

    private static Logger logger = Logger.getLogger(BPMNChangeRoutingPointsOperationHandler.class.getName());
    private boolean debug = false;

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public Optional<Command> createCommand(ChangeRoutingPointsOperation operation) {
        return commandOf(() -> executeOperation(operation));
    }

    private void executeOperation(final ChangeRoutingPointsOperation operation) {
        List<ElementAndRoutingPoints> routingPoints = operation.getNewRoutingPoints();

        if (debug)
            System.out.println("├── ChangeRoutingPointsOperation - got " + routingPoints.size() + " routing points");
        try {
            for (ElementAndRoutingPoints routingPoint : routingPoints) {
                String id = routingPoint.getElementId();
                List<GPoint> newGLSPRoutingPoints = routingPoint.getNewRoutingPoints();

                // round routing points ...
                BPMNGridSnapper.round(newGLSPRoutingPoints);

                // update the GModel.
                GEdge edge = (GEdge) modelState.getIndex().get(id).orElse(null);

                if (edge != null) {
                    if (debug)
                        System.out.println("├── edge: " + id);

                    EList<GPoint> oldPoints = edge.getRoutingPoints();
                    if (debug)
                        System.out.println("│   ├── old points:");
                    for (GPoint ding : oldPoints) {
                        if (debug)
                            System.out.println("│   │   ├── " + ding.getX() + "." + ding.getY());
                    }
                    if (debug)
                        System.out.println("│   ├── new points:");
                    for (GPoint ding : newGLSPRoutingPoints) {
                        if (debug)
                            System.out.println("│   │   ├── " + ding.getX() + "." + ding.getY());
                    }

                    // Adjust routing points for Events
                    if (!newGLSPRoutingPoints.isEmpty()) {
                        adjustEventRoutingPorts(edge, newGLSPRoutingPoints);
                    }

                    // Update BPMN Routing
                    edge.getRoutingPoints().clear();
                    edge.getRoutingPoints().addAll(newGLSPRoutingPoints);

                    updateBPMNWayPoints(id, newGLSPRoutingPoints);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // no more action - the GModel is now up to date
    }

    /**
     * This helper method adjusts the docking point of the last/first routing point
     * on a BPMN Event element.
     * 
     * @throws BPMNMissingElementException
     * 
     */
    private void adjustEventRoutingPorts(GEdge edge, List<GPoint> newGLSPRoutingPoints)
            throws BPMNMissingElementException {

        GPoint pointOfInterest = null;
        // ist die Source ein Event?
        BPMNElementNode sourceBPMNElement = modelState.getBpmnModel().findElementNodeById(edge.getSourceId());
        if (sourceBPMNElement != null && BPMNModel.isEvent(sourceBPMNElement)) {
            pointOfInterest = newGLSPRoutingPoints.get(0);
            GPoint center = computeCenter(edge.getSource());
            if (yInEventsBounds(center, pointOfInterest)) {
                pointOfInterest.setY(center.getY());
            }
            if (xInEventsBounds(center, pointOfInterest)) {
                pointOfInterest.setX(center.getX());
            }
        }

        // ist das Target ein Event?
        BPMNElementNode targetBPMNElement = modelState.getBpmnModel().findElementNodeById(edge.getTargetId());
        if (targetBPMNElement != null && BPMNModel.isEvent(targetBPMNElement)) {
            pointOfInterest = newGLSPRoutingPoints.get(newGLSPRoutingPoints.size() - 1);
            GPoint center = computeCenter(edge.getTarget());
            if (yInEventsBounds(center, pointOfInterest)) {
                pointOfInterest.setY(center.getY());
            }
            if (xInEventsBounds(center, pointOfInterest)) {
                pointOfInterest.setX(center.getX());
            }
        }
    }

    /**
     * Returns the center of a GMoelElement
     * 
     * @param element
     * @return
     */
    private GPoint computeCenter(GModelElement element) {
        if (element instanceof GBoundsAware) {
            GBoundsAware boundsElement = (GBoundsAware) element;
            double radius = Event.DEFAULT_HEIGHT / 2;
            double xCenter = boundsElement.getPosition().getX() + radius;
            double yCenter = boundsElement.getPosition().getY() + radius;
            return GraphUtil.point(xCenter, yCenter);
        }
        return GraphUtil.point(0, 0);

    }

    private boolean yInEventsBounds(GPoint center, GPoint routingPoint) throws BPMNMissingElementException {
        double radius = Event.DEFAULT_HEIGHT / 2;

        // Get first routing point and test position....
        if (debug) {
            System.out.println("│   ├── event center      = " + center);
            System.out.println("│   ├── 1st routing point = " + routingPoint);
        }
        // test y
        double rY = routingPoint.getY();
        if (debug) {
            System.out.println("│   ├── eY - radius =  " + (center.getY() - radius));
            System.out.println("│   ├── eY + radius =  " + (center.getY() + radius));
        }
        if (rY >= (center.getY() - radius)
                && rY <= (center.getY() + radius)) {
            if (debug)
                System.out.println("│   ├── Adjusting necessary");
            return true;
        }
        return false;
    }

    private boolean xInEventsBounds(GPoint center, GPoint routingPoint) throws BPMNMissingElementException {
        double radius = Event.DEFAULT_HEIGHT / 2;

        // Get first routing point and test position....
        if (debug) {
            System.out.println("│   ├── event center      = " + center);
            System.out.println("│   ├── 1st routing point = " + routingPoint);
        }
        // test y
        double rX = routingPoint.getX();
        if (debug) {
            System.out.println("│   ├── eX - radius =  " + (center.getX() - radius));
            System.out.println("│   ├── eX + radius =  " + (center.getX() + radius));
        }
        if (rX >= (center.getX() - radius)
                && rX <= (center.getX() + radius)) {
            if (debug)
                System.out.println("│   ├── Adjusting necessary");
            return true;
        }
        return false;
    }

    /**
     * This helper method is necessary to update parts of the BPMN waypoints
     * immediately.
     * 
     * Note: The full update is performed in the BPMNComputeBoundsActionHandler.
     * But this code is even necessary.
     * 
     * @param id
     * @param newGLSPRoutingPoints
     */
    private void updateBPMNWayPoints(String id, List<GPoint> newGLSPRoutingPoints) {
        if (debug)
            System.out.println("├── ChangeRoutingPointsOperation - Update BPMN way points for " + id);
        BPMNElement element = modelState.getBpmnModel().findElementById(id);
        if (element == null) {
            logger.warning("Failed to resolve waypoints for " + id);
            return;
        }
        BPMNElementEdge bpmnElementEdge = (BPMNElementEdge) element;

        try {
            if (debug)
                System.out.println("│   ├── clearing all waypoints");
            bpmnElementEdge.clearWayPoints();

            // find the process
            Participant participant = null;
            BPMNProcess process = null;
            if (bpmnElementEdge instanceof SequenceFlow) {
                process = ((SequenceFlow) bpmnElementEdge).getBpmnProcess();
            }
            if (bpmnElementEdge instanceof Association) {
                process = ((Association) bpmnElementEdge).getBpmnProcess();
            }
            if (process != null && !process.isPublicProcess()) {
                participant = process.findParticipant();
            }

            // add the new routing points
            for (GPoint point : newGLSPRoutingPoints) {
                BPMNPoint bpmnPoint = null;
                double xOffset = 0;
                double yOffset = 0;
                // in case we are within a Pool we need to compute the x/y offsets first
                if (!BPMNTypes.MESSAGE_FLOW.equals(bpmnElementEdge.getType())) {
                    if (participant != null) {
                        // if we have a participant/pool we can compute the relative position...
                        xOffset = participant.getBounds().getPosition().getX();
                        yOffset = participant.getBounds().getPosition().getY();
                    }
                }
                bpmnPoint = new BPMNPoint(xOffset + point.getX(), yOffset + point.getY());
                if (debug)
                    System.out.println("│   ├── add waypoint : " + point.getX() + "," + point.getY());
                bpmnElementEdge.addWayPoint(bpmnPoint);
            }
        } catch (Exception e) {
            logger.warning("Unable to update bpmn way points: " + e.getMessage());
            e.printStackTrace();
        }
    }
}