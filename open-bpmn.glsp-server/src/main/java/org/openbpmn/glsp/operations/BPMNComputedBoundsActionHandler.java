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
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Association;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.AbstractBPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
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
 * @author rsoika
 *
 */
public class BPMNComputedBoundsActionHandler extends AbstractActionHandler<ComputedBoundsAction> {

    private static Logger logger = Logger.getLogger(BPMNComputedBoundsActionHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    protected List<Action> executeAction(final ComputedBoundsAction actualAction) {
        List<ElementAndRoutingPoints> routings = actualAction.getRoutes();
        try {
            for (ElementAndRoutingPoints routingInfo : routings) {
                String id = routingInfo.getElementId();
                AbstractBPMNElement element = modelState.getBpmnModel().findElementById(id);
                // do we have a BPMNSequenceFlow ?
                if (element != null && element instanceof BPMNElementEdge) {
                    BPMNElementEdge bpmnElementEdge = (BPMNElementEdge) element;
                    // update the BPMN WayPoints.
                    List<GPoint> newGLSPRoutingPoints = routingInfo.getNewRoutingPoints();
                    logger.fine("...updating " + newGLSPRoutingPoints.size() + " BPMN WayPoints for element " + id
                            + "....");
                    bpmnElementEdge.clearWayPoints();
                    // add the new routing points
                    for (GPoint point : newGLSPRoutingPoints) {
                        BPMNPoint bpmnPoint = null;
                        Participant participant = null;
                        BPMNProcess process = null;
                        double xOffset = 0;
                        double yOffset = 0;

                        // in case we are within a Pool we need to compute the x/y offsets first
                        if (!BPMNTypes.MESSAGE_FLOW.equals(bpmnElementEdge.getType())) {
                            // find the process
                            if (bpmnElementEdge instanceof SequenceFlow) {
                                process = ((SequenceFlow) bpmnElementEdge).getProcess();

                            }
                            if (bpmnElementEdge instanceof Association) {
                                process = ((Association) bpmnElementEdge).getProcess();

                            }

                            if (process != null && !process.isPublicProcess()) {
                                participant = process.findParticipant();
                            }

                            if (participant != null) {
                                // if we have a participant/pool we can compute the relative position...
                                xOffset = participant.getBounds().getPosition().getX();
                                yOffset = participant.getBounds().getPosition().getY();
                            }
                        }

                        bpmnPoint = new BPMNPoint(xOffset + point.getX(), yOffset + point.getY());
                        logger.fine("...add new waypoint: " + point.getX() + "," + point.getY());
                        bpmnElementEdge.addWayPoint(bpmnPoint);
                    }
                } else {
                    logger.warning("ComputedBoundsAction will be ignored as the element is not a BPMNElementEdge!");
                }
            }
        } catch (

        Exception e) {
            e.printStackTrace();
        }
        // no more action - the GModel is now up to date
        return

        none();
    }

}