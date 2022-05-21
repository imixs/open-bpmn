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

import javax.inject.Inject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.GPort;
import org.eclipse.glsp.graph.GraphFactory;
import org.eclipse.glsp.graph.builder.impl.GGraphBuilder;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.features.core.model.GModelFactory;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNGateway;
import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.BPMNSequenceFlow;
import org.openbpmn.glsp.bpmn.EventNode;
import org.openbpmn.glsp.bpmn.GatewayNode;
import org.openbpmn.glsp.bpmn.SequenceFlow;
import org.openbpmn.glsp.bpmn.TaskNode;
import org.openbpmn.glsp.elements.event.EventNodeBuilder;
import org.openbpmn.glsp.elements.flow.SequenceFlowBuilder;
import org.openbpmn.glsp.elements.gateway.GatewayNodeBuilder;
import org.openbpmn.glsp.elements.task.TaskNodeBuilder;

/**
 * The BPMNGModelFactory is responsible to produce a graph model from the BPMN
 * Meta model.
 * <p>
 * The BPMNGModelState holds an instance of the BPMN Meta model which is created
 * by the {@link BPMNSourceModelStorage}
 * <p>
 * The graph model factory is invoked after initial load of the source model and
 * after each operation that is applied to the source model by an
 * {@link OperationHandler} in order to update the graph model before sending it
 * to the client for rendering.
 * </p>
 **/
public class BPMNGModelFactory implements GModelFactory {
    private static Logger logger = Logger.getLogger(BPMNGModelFactory.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public void createGModel() {

        if (!modelState.isInitalized()) {
            long l = System.currentTimeMillis();
            GGraph newGModel = null;
            BPMNModel model = modelState.getBpmnModel();
            if (model != null) {
                BPMNProcess process = model.openContext(null);
                if (process != null) {
                    newGModel = createGModelFromProcess(process, modelState);
                    modelState.updateRoot(newGModel);
                    modelState.getRoot().setRevision(-1);
                }
            }
            if (newGModel == null) {
                logger.info("Unable to create model - no processes found - creating an empty model");
                createNewEmptyRoot("process_0");
            }

            modelState.setInitalized(true);
            logger.info("===> createGModel took " + (System.currentTimeMillis() - l) + "ms");
        } else {
            logger.info("===> createGModel skipped!");
        }
    }

    /**
     * This method creates a GModel form a BPMN process instance
     * <p>
     * The method is called by the {@link BPMNGModelFactory}
     *
     * @param process
     * @param modelState
     * @return new GGraph
     */
    public static GGraph createGModelFromProcess(final BPMNProcess process, final GModelState modelState) {

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
            EventNodeBuilder builder = new EventNodeBuilder(event.getType(), event.getName());
            // Build the GLSP Node....
            EventNode eventNode = builder //
                    .id(event.getId()) //
                    .position(point) //
                    .build();
            entityNodes.add(eventNode);
        }

        // Add all Gateways...
        for (BPMNGateway gateway : process.getGateways()) {
            GPoint point = GraphUtil.point(gateway.getBounds().getX(), gateway.getBounds().getY());
            GatewayNodeBuilder builder = new GatewayNodeBuilder(gateway.getType(), gateway.getName());
            // Build the GLSP Node....
            GatewayNode gatewayNode = builder //
                    .id(gateway.getId()) //
                    .position(point) //
                    .build();
            entityNodes.add(gatewayNode);
        }

        // Add all SequenceFlows
        for (BPMNSequenceFlow sequenceFlow : process.getSequenceFlows()) {
            SequenceFlowBuilder builder = new SequenceFlowBuilder();

            GModelElement target = findElementById(entityNodes, sequenceFlow.getTargetRef());
            if (target != null) {
                builder.target(findPort(target));
            }
            GModelElement source = findElementById(entityNodes, sequenceFlow.getSourceRef());
            if (source != null) {
                builder.source(findPort(source));
            }

            builder.id(sequenceFlow.getId());

            SequenceFlow sequenceFlowEdge = builder.build();
            for (BPMNPoint wayPoint : sequenceFlow.getWayPoints()) {
                GPoint point = GraphUtil.point(wayPoint.getX(), wayPoint.getY());
                sequenceFlowEdge.getRoutingPoints().add(point);
            }

            entityNodes.add(sequenceFlowEdge);
        }

        // add to root node...
        GGraph newGModel = new GGraphBuilder() //
                .id(process.getId()) //
                .addAll(entityNodes) //
                .build();

        return newGModel;
    }

    /**
     * This method tests if the given element has a Child of type GPort. This is the
     * case for Events and Gateways. In this case the method returns the GPort.
     * Otherwise the method returns the element.
     *
     * @return GPort of an alement or the element if no GPort exists
     */
    private static GModelElement findPort(final GModelElement element) {

        EList<GModelElement> childs = element.getChildren();
        for (GModelElement child : childs) {
            if (child instanceof GPort) {
                // return Optional.of(child);
                return child;
            }
        }
        // we did not found a GPort
        return element;
    }

    /**
     * Finds an GModelElement by its ID in a given List of GModelElements. The
     * method returns null if not element with the given ID exists.
     *
     * @param entityNodes - List of GModelElements
     * @param id          - id to search for
     * @return GModelElement - or null if no elment was found.
     */
    private static GModelElement findElementById(final List<GModelElement> entityNodes, final String id) {
        for (GModelElement element : entityNodes) {
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
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
