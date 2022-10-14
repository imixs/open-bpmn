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

import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.ChangeRoutingPointsOperation;
import org.eclipse.glsp.server.types.ElementAndRoutingPoints;
import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.BPMNSequenceFlow;
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

    private static Logger logger = Logger.getLogger(BPMNChangeRoutingPointsOperationHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public void executeOperation(final ChangeRoutingPointsOperation operation) {
        BPMNProcess context = modelState.getBpmnModel().openDefaultProcess();
        List<ElementAndRoutingPoints> routingPoints = operation.getNewRoutingPoints();
        try {
            for (ElementAndRoutingPoints routingPoint : routingPoints) {

                String id = routingPoint.getElementId();
                BPMNSequenceFlow bpmnSequenceFlow = context.findBPMNSequenceFlowById(id);
                if (bpmnSequenceFlow != null) {

                    List<GPoint> newGLSPRoutingPoints = routingPoint.getNewRoutingPoints();
                    for (GPoint point : newGLSPRoutingPoints) {
                        logger.info("    new Routing point x=" + point.getX() + " y=" + point.getY());
                    }

                    // update the GModel.
                    Optional<GEdge> _edge = modelState.getIndex().findElementByClass(id, GEdge.class);
                    if (_edge.isPresent()) {
                        logger.info("===== Updating GLSP RoutingPoints =======");
                        GEdge edge = _edge.get();
                        edge.getRoutingPoints().clear();
                        edge.getRoutingPoints().addAll(newGLSPRoutingPoints);
                    }

                    System.out.println("===== Updating BPMN WayPoints  =======");
                    bpmnSequenceFlow.clearWayPoints();
                    // add the new routing points
                    for (GPoint point : newGLSPRoutingPoints) {
                        BPMNPoint bpmnPoint = new BPMNPoint(point.getX(), point.getY());
                        bpmnSequenceFlow.addWayPoint(bpmnPoint);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // no more action - the GModel is now up to date

    }

}