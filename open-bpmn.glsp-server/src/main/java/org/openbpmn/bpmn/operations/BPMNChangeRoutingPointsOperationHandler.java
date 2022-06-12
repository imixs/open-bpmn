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
package org.openbpmn.bpmn.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.ChangeRoutingPointsOperation;
import org.eclipse.glsp.server.types.ElementAndRoutingPoints;
import org.openbpmn.bpmn.elements.BPMNBounds;
import org.openbpmn.bpmn.elements.BPMNFlowElement;
import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.BPMNSequenceFlow;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.model.BPMNGModelState;

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
public class BPMNChangeRoutingPointsOperationHandler extends AbstractOperationHandler<ChangeRoutingPointsOperation> {
    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(BPMNChangeRoutingPointsOperationHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public void executeOperation(final ChangeRoutingPointsOperation operation) {
        BPMNProcess context = modelState.getBpmnModel().getContext();
        List<ElementAndRoutingPoints> routingPoints = operation.getNewRoutingPoints();
        try {
            for (ElementAndRoutingPoints routingPoint : routingPoints) {

                String id = routingPoint.getElementId();
                BPMNSequenceFlow bpmnSequenceFlow = context.findBPMNSequenceFlowById(id);
                if (bpmnSequenceFlow != null) {
                    List<GPoint> newGLSPRoutingPoints = routingPoint.getNewRoutingPoints();
                    ArrayList<BPMNPoint> newBPMNWayPoints = new ArrayList<>();

                    System.out.println("===== Computing New WayPoint Data: " + id + " =======");
                    // add source point
                    BPMNFlowElement bpmnFlowElement = bpmnSequenceFlow.getSourceElement();
                    if (bpmnFlowElement != null) {
                        BPMNBounds bounds;

                        bounds = bpmnFlowElement.getBounds();

                        BPMNPoint center = bounds.getCenter();
                        newBPMNWayPoints.add(center);
                    }
                    // add the new routing points (can be empty)
                    for (GPoint point : newGLSPRoutingPoints) {
                        newBPMNWayPoints.add(new BPMNPoint(point.getX(), point.getY()));
                    }
                    // add target point
                    bpmnFlowElement = bpmnSequenceFlow.getTargetElement();
                    if (bpmnFlowElement != null) {
                        BPMNBounds bounds = bpmnFlowElement.getBounds();
                        BPMNPoint center = bounds.getCenter();
                        newBPMNWayPoints.add(center);
                    }

                    // Now we have the new WayPoint List for the BPMN SequenceFlow element.....
                    // remove existing routing points...
                    bpmnSequenceFlow.clearWayPoints();
                    // add new ones
                    for (BPMNPoint bpmnPoint : newBPMNWayPoints) {
                        System.out.println("    Routing point x=" + bpmnPoint.getX() + " y=" + bpmnPoint.getY());
                        bpmnSequenceFlow.addWayPoint(bpmnPoint);
                    }

                    // finally we update the GModel. Here we just update the routing points
                    // for the GEdge and skip the source and target points as these points
                    // are not relevant for the GModel
                    Optional<GEdge> _edge = modelState.getIndex().findElementByClass(id, GEdge.class);
                    if (_edge.isPresent()) {
                        GEdge edge = _edge.get();
                        edge.getRoutingPoints().clear();
                        System.out.println("===== Updating GLSP RoutingPoints =======");
                        for (GPoint gPoint : newGLSPRoutingPoints) {
                            System.out.println("    Routing point x=" + gPoint.getX() + " y=" + gPoint.getY());
                        }
                        edge.getRoutingPoints().addAll(newGLSPRoutingPoints);
                    }

                }
            }
        } catch (BPMNMissingElementException e) {
            e.printStackTrace();
        }
        // no more action - the GModel is now up to date

    }

}