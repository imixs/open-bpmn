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
package org.openbpmn.bpmn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.GraphFactory;
import org.eclipse.glsp.graph.builder.impl.GGraphBuilder;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.model.GModelState;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNGateway;
import org.openbpmn.glsp.bpmn.EventNode;
import org.openbpmn.glsp.bpmn.GatewayNode;
import org.openbpmn.glsp.bpmn.TaskNode;
import org.openbpmn.glsp.elements.event.EventNodeBuilder;
import org.openbpmn.glsp.elements.gateway.GatewayNodeBuilder;
import org.openbpmn.glsp.elements.task.TaskNodeBuilder;

/**
 * The BPMNGModelUtil is a helper class to generate a GModel form a BPMN meta
 * model
 * </p>
 **/
public class BPMNGModelUtil {
    private static Logger logger = Logger.getLogger(BPMNGModelUtil.class.getName());

    /**
     * This method creates a GModel form a BPMN process instance
     * <p>
     * The method is called by the {@link BPMNGModelFactory}
     *
     * @param process
     * @param modelState
     * @return new GGraph
     */
    public static GGraph createGModelFromProcess(final org.openbpmn.bpmn.elements.BPMNProcess process,
            final GModelState modelState) {

        List<GModelElement> entityNodes = new ArrayList<>();

        // Add all Tasks
        for (BPMNActivity activity : process.getActivities()) {
            System.out.println("activity: " + activity.getName());
            GPoint point = GraphUtil.point(activity.getBounds().getX(), activity.getBounds().getY());
            TaskNodeBuilder builder = new TaskNodeBuilder(activity.getType(), activity.getName());

            // Build the GLSP Node....
            TaskNode taskNode = builder //
                    .id(activity.getId()) //
                    .position(point) //
                    .build();
            entityNodes.add(taskNode);
        }

        // Add all Events...
        for (BPMNEvent event : process.getEvents()) {
            logger.fine("event: " + event.getName());
            GPoint point = GraphUtil.point(event.getBounds().getX(), event.getBounds().getY());
            EventNodeBuilder builder = new EventNodeBuilder(event.getType(), event.getName());// ,
                                                                                              // "event:start"
            EventNode eventNode = builder //
                    .id(event.getId()) //
                    .position(point) //
                    .build();
            entityNodes.add(eventNode);
        }

        // Add all Gateways...
        for (BPMNGateway gateway : process.getGateways()) {
            logger.fine("gateway: " + gateway.getName());
            GPoint point = GraphUtil.point(gateway.getBounds().getX(), gateway.getBounds().getY());
            GatewayNodeBuilder builder = new GatewayNodeBuilder(BPMNGatewayType.EXCLUSIVE, gateway.getName());// ,
                                                                                                              // "event:start"
            GatewayNode gatewayNode = builder //
                    .id(gateway.getId()) //
                    .position(point) //
                    .build();
            entityNodes.add(gatewayNode);
        }

        // Add all SequenceFlows
//        for (BPMNSequenceFlow sequenceFlow : process.getSequenceFlows()) {
//            logger.fine("sequenceFlow: " + sequenceFlow.getName());
//            SequenceFlowBuilder builder = new SequenceFlowBuilder();
//            GModelElement target = findElementById(entityNodes, sequenceFlow.getTargetRef());
//            if (target != null) {
//                builder.target(target);
//            }
//            GModelElement source = findElementById(entityNodes, sequenceFlow.getSourceRef());
//            if (source != null) {
//                builder.source(source);
//            }
//            logger.info("..................bin noch dabei");
//            org.openbpmn.glsp.bpmn.SequenceFlow sequenceFlowEdge = builder.build();
//            for (BPMNPoint wayPoint : sequenceFlow.getWayPoints()) {
//                GPoint point = GraphUtil.point(wayPoint.getX(), wayPoint.getY());
//                sequenceFlowEdge.getRoutingPoints().add(point);
//            }
//
//            entityNodes.add(sequenceFlowEdge);
//        }

        logger.info("..................ready to build GGraph");
        // add to root node...
        GGraph newGModel = new GGraphBuilder() //
                .id(process.getId()) //
                .addAll(entityNodes) //
                .build();

        return newGModel;
    }

    /**
     * This method generates an empty BPMN GModel
     *
     * @param rootID
     * @return empty model
     */
    public static GModelRoot createNewEmptyRoot(final String rootID) {
        GModelRoot root = GraphFactory.eINSTANCE.createGGraph();
        root.setId(rootID);
        root.setType(DefaultTypes.GRAPH);
        return root;
    }
}
