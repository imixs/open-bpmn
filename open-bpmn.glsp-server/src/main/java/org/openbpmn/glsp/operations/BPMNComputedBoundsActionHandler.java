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
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.actions.AbstractActionHandler;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.features.core.model.ComputedBoundsAction;
import org.eclipse.glsp.server.types.ElementAndRoutingPoints;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.Process;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.AbstractBPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This ActionHandler reacts on and updates the BPMN Waypoints of a
 * BPMNSequenceFlow.
 * <p>
 * The action handler is only handling ComputedBoundsActions on a
 * BBPMNSequenceFlow element. Other elements will be ignored.
 *
 *
 *
 * @author rsoika
 *
 */
public class BPMNComputedBoundsActionHandler extends AbstractActionHandler<ComputedBoundsAction> {

    private static Logger logger = Logger.getLogger(BPMNComputedBoundsActionHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    protected List<Action> executeAction(final ComputedBoundsAction actualAction) {
        Process context = modelState.getBpmnModel().openDefaultProcess();

        List<ElementAndRoutingPoints> routings = actualAction.getRoutes();

        logger.info("=== updating RoutingPoints.... - count=" + routings.size());
        try {
            for (ElementAndRoutingPoints routingInfo : routings) {

                String id = routingInfo.getElementId();
                logger.info("....element id= " + id);
                SequenceFlow bpmnSequenceFlow = null;

                AbstractBPMNElement element = modelState.getBpmnModel().findElementById(id);
                // do we have a BPMNSequenceFlow ?
                if (element != null && element instanceof SequenceFlow) {
                    // update the BPMN WayPoints.
                    bpmnSequenceFlow = (SequenceFlow) element;

                    List<GPoint> newGLSPRoutingPoints = routingInfo.getNewRoutingPoints();

                    System.out.println("===== Updating " + newGLSPRoutingPoints.size() + " BPMN WayPoints  =======");
                    bpmnSequenceFlow.clearWayPoints();
                    // add the new routing points
                    for (GPoint point : newGLSPRoutingPoints) {
                        BPMNPoint bpmnPoint = null;
                        // if we are in a Pool we need to compute the absolute position
                        Participant participant = bpmnSequenceFlow.getBpmnProcess().findParticipant();
                        if (participant != null) {
                            // compute Pool offset...
                            double xOffset = participant.getBounds().getPosition().getX();
                            double yOffset = participant.getBounds().getPosition().getY();
                            bpmnPoint = new BPMNPoint(xOffset + point.getX(), yOffset + point.getY());

                        } else {
                            // we are in the default process
                            bpmnPoint = new BPMNPoint(point.getX(), point.getY());
                        }

                        logger.info("..add new waypoint: " + point.getX() + "," + point.getY());
                        bpmnSequenceFlow.addWayPoint(bpmnPoint);
                    }
                } else {
                    logger.info("----> Event will be ignored as the element is not a Sequence Flow!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // no more action - the GModel is now up to date
        return none();
    }

}