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
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
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
                BPMNElement element = modelState.getBpmnModel().findElementById(id);
                // do we have a BPMNEdge ?
                if (element != null && element instanceof BPMNElementEdge) {
                    BPMNElementEdge bpmnElementEdge = (BPMNElementEdge) element;
                    // update the BPMN WayPoints.
                    List<GPoint> newGLSPRoutingPoints = routingInfo.getNewRoutingPoints();
                    logger.fine("├── ComputedBoundsAction update " + newGLSPRoutingPoints.size()
                            + " BPMN WayPoints for element " + id
                            + "....");

                    bpmnElementEdge.clearWayPoints();
                    // find the process
                    BPMNProcess process = bpmnElementEdge.getBpmnProcess();
                    BPMNElementNode container = process.findContainer();
                    double xOffset = 0;
                    double yOffset = 0;
                    // in case we are within a Pool we need to compute the x/y offsets first
                    if (!BPMNTypes.MESSAGE_FLOW.equals(bpmnElementEdge.getType())) {
                        if (container != null) {
                            // if we have a participant/pool we can compute the relative position...
                            xOffset = container.getBounds().getPosition().getX();
                            yOffset = container.getBounds().getPosition().getY();
                        }
                    }
                    // add the new routing points
                    for (GPoint point : newGLSPRoutingPoints) {
                        point = modelState.getBpmnGridSnapper().round(point);
                        BPMNPoint bpmnPoint = new BPMNPoint(xOffset + point.getX(), yOffset + point.getY());
                        logger.fine("│   ├── add point: " + point.getX() + "," + point.getY());
                        bpmnElementEdge.addWayPoint(bpmnPoint);
                    }
                } else {
                    logger.fine("ComputedBoundsAction will be ignored as the element is not a BPMNElementEdge!");
                }
            }
        } catch (

        Exception e) {
            e.printStackTrace();
        }
        // no more action - the GModel is now up to date
        return none();
    }

}