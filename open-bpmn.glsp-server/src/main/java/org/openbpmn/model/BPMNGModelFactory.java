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

package org.openbpmn.model;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.eclipse.glsp.server.operations.OperationHandler;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNBounds;
import org.openbpmn.bpmn.elements.BPMNDataObject;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNFlowElement;
import org.openbpmn.bpmn.elements.BPMNGateway;
import org.openbpmn.bpmn.elements.BPMNLabel;
import org.openbpmn.bpmn.elements.BPMNLane;
import org.openbpmn.bpmn.elements.BPMNParticipant;
import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.BPMNSequenceFlow;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.extension.BPMNExtension;
import org.openbpmn.glsp.bpmn.DataObjectGNode;
import org.openbpmn.glsp.bpmn.EventGNode;
import org.openbpmn.glsp.bpmn.GatewayGNode;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.bpmn.LaneGNode;
import org.openbpmn.glsp.bpmn.PoolGNode;
import org.openbpmn.glsp.bpmn.SequenceFlowGNode;
import org.openbpmn.glsp.bpmn.TaskGNode;
import org.openbpmn.glsp.elements.dataobject.DataObjectGNodeBuilder;
import org.openbpmn.glsp.elements.event.EventGNodeBuilder;
import org.openbpmn.glsp.elements.flow.SequenceFlowGNodeBuilder;
import org.openbpmn.glsp.elements.gateway.GatewayGNodeBuilder;
import org.openbpmn.glsp.elements.label.LabelGNodeBuilder;
import org.openbpmn.glsp.elements.pool.LaneGNodeBuilder;
import org.openbpmn.glsp.elements.pool.PoolGNodeBuilder;
import org.openbpmn.glsp.elements.task.TaskGNodeBuilder;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
    private static Logger logger = LogManager.getLogger(BPMNGModelFactory.class);

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected Set<BPMNExtension> extensions;

    private BPMNModel bpmnModel;

    /**
     * Create a GModelRoot from a BPMNModel specified in the modelState. This method
     * also produces and sets a GModelIndex in the model state that allows mapping
     * from graph model elements to source model elements and vice versa.
     */
    @Override
    public void createGModel() {
        // verify extensions....
        if (extensions == null || extensions.size() == 0) {
            logger.warn("no BPMNExtension found! Check DiagramModule->configureBPMNExtensions");
        }

        if (!modelState.isInitalized()) {
            long l = System.currentTimeMillis();
            GGraph newGModel = buildGGraph(getBpmnModel());
            modelState.updateRoot(newGModel);
            modelState.getRoot().setRevision(-1);

            if (newGModel == null) {
                logger.warn("Unable to create model - no processes found - creating an empty model");
                createNewEmptyRoot("process_0");
            }

            modelState.setInitalized(true);
            logger.info("===> createGModel took " + (System.currentTimeMillis() - l) + "ms");
        } else {
            logger.debug("===> createGModel skipped!");
        }
    }

    /**
     * Getter method also used by jUnit tests
     *
     * @return BPMNModel
     */
    public BPMNModel getBpmnModel() {
        if (bpmnModel == null) {
            bpmnModel = modelState.getBpmnModel();
        }
        return bpmnModel;
    }

    public void setBpmnModel(final BPMNModel bpmnModel) {
        this.bpmnModel = bpmnModel;
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

    /**
     * This method creates a GModel form the current BPMNModel instance
     * <p>
     * There are two diagram types currently supported
     * <ul>
     * <li>'Process Diagram' contains only one default process
     * <li>'Collaboration Diagram' contains a participant list with Pools and at
     * least one default process
     * <p>
     * The GPoint of a BPMNFlowElement contained in a BPMNParticipant element need
     * to be computed relative to the GPoint of the Pool
     *
     * @return new GGraph
     */
    public GGraph buildGGraph(final BPMNModel model) {

        // create the RootBuiler
        GGraphBuilder rootBuilder = new GGraphBuilder() //
                .id("root_" + BPMNModel.generateShortID());

        List<GModelElement> gRootNodeList = new ArrayList<>();
        try {
            // In case we have collaboration diagram we iterate over all participants and
            // create a pool if the contained process is private. Otherwise we create the
            // default process
            if (model.isCollaborationDiagram()) {
                Set<BPMNParticipant> participants = model.getParticipants();
                for (BPMNParticipant participant : participants) {
                    logger.debug(
                            "participant: " + participant.getName() + " BPMNProcess=" + participant.getProcessRef());
                    BPMNProcess bpmnProcess = model.openProcess(participant.getProcessRef());
                    // Add a Pool if the process is private
                    if (BPMNTypes.PROCESS_TYPE_PRIVATE.equals(bpmnProcess.getProcessType())) {
                        List<GModelElement> childList = computeGModelElements(bpmnProcess, participant);

                        PoolGNode pool = new PoolGNodeBuilder(participant) //
                                .addAll(childList) //
                                .build();

                        // apply BPMN Extensions
                        applyBPMNExtensions(pool, participant);
                        gRootNodeList.add(pool);

                    } else {
                        // add default process without a pool
                        gRootNodeList.addAll(computeGModelElements(bpmnProcess, null));
                    }
                }
            } else {
                // We have a simple 'Process Diagram' type - build the GModel from default
                // process
                BPMNProcess bpmnProcess = model.openDefaultProcess();
                gRootNodeList.addAll(computeGModelElements(bpmnProcess, null));
            }

        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        // add the rootNodeList
        rootBuilder.addAll(gRootNodeList);
        GGraph newGModel = rootBuilder.build();
        // finally add the root extensions
        applyBPMNExtensions(newGModel, model.openDefaultProcess());

        return newGModel;
    }

    /**
     * This method apply all possible BPMNExtension to the GNode. This is to build
     * the JSONForms Sections and add additional classes for non-default Extensions.
     *
     * @param elementNode
     * @param bpmnElement
     */
    void applyBPMNExtensions(final GModelElement elementNode, final BPMNBaseElement bpmnElement) {
        // finally we define the JSONForms schemata
        DataBuilder dataBuilder = new DataBuilder();
        UISchemaBuilder uiSchemaBuilder = new UISchemaBuilder(Layout.CATEGORIZATION);
        SchemaBuilder schemaBuilder = new SchemaBuilder();
        List<String> extensionNamespaces = new ArrayList<>();

        /*
         * Now we iterate over all extension to build the JSONForms sections. The
         * extensions can be default extensions or external extension. For external
         * extensions we also add a CSS class
         */
        if (extensions != null) {
            for (BPMNExtension extension : extensions) {
                // validate if the extension can handle this BPMN element
                if (extension.handlesBPMNElement(bpmnElement)) {
                    // add JSONForms Schemata
                    extension.buildPropertiesForm(bpmnElement, dataBuilder, schemaBuilder, uiSchemaBuilder);

                    // if the extension is not a Default Extension then we add the extension css
                    // class
                    if (!BPMNNS.BPMN2.name().equals(extension.getNamespace())
                            && !extensionNamespaces.contains(extension.getNamespace())) {
                        elementNode.getCssClasses().add("bpmnextension-" + extension.getNamespace());
                        // avoid duplicates css classes
                        extensionNamespaces.add(extension.getNamespace());
                    }
                }
            }
        }

        // Build JSONForms Data
        try (Writer writer = new StringWriter()) {
            elementNode.getArgs().put("JSONFormsData", dataBuilder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Build JSONForms UISchema
        try (Writer writer = new StringWriter()) {
            elementNode.getArgs().put("JSONFormsUISchema", uiSchemaBuilder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Build JSONForms Schema
        try (Writer writer = new StringWriter()) {
            elementNode.getArgs().put("JSONFormsSchema", schemaBuilder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds an GModelElement by its ID in a given List of GModelElements. The
     * method returns null if not element with the given ID exists.
     *
     * @param entityNodes - List of GModelElements
     * @param id          - id to search for
     * @return GModelElement - or null if no elment was found.
     */
    GModelElement findElementById(final List<GModelElement> entityNodes, final String id) {
        for (GModelElement element : entityNodes) {
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }

    /**
     * This helper method returns a GModelElement list from all BPMNLanes and
     * BPMNFlowElements contained in a given BPMNProcess.
     * <p>
     * If an optional BPMNParticipant is provided, than the position of the
     * GModelElement will be computed relative to the position to the GModel Pool
     * Element. This is necessary because in BPMN the position of contained elements
     * is absolute and in a GModel the position is relative to the container.
     *
     */
    List<GModelElement> computeGModelElements(final BPMNProcess process, final BPMNParticipant participant)
            throws BPMNMissingElementException {

        List<GModelElement> gNodeList = new ArrayList<>();

        // Add all Lanes
        for (BPMNLane lane : process.getLanes()) {
            logger.info("lane: " + lane.getName());
            // compute relative position
            GPoint point = computeRelativeGPoint(lane.getBounds(), participant);
            // build GNode
            LaneGNode laneNode = new LaneGNodeBuilder(lane) //
                    .position(point) //
                    .build();

            gNodeList.add(laneNode);
        }

        // Add all Tasks
        for (BPMNActivity activity : process.getActivities()) {
            logger.debug("activity: " + activity.getName());
            // compute relative position
            GPoint point = computeRelativeGPoint(activity.getBounds(), participant);
            // build GNode
            TaskGNode taskNode = new TaskGNodeBuilder(activity) //
                    .position(point) //
                    .build();

            // apply BPMN Extensions
            applyBPMNExtensions(taskNode, activity);
            gNodeList.add(taskNode);
        }

        // Add all Events...
        for (BPMNEvent event : process.getEvents()) {
            logger.debug("BPMNEvent: " + event.getName() + " x=" + event.getBounds().getPosition().getX() + " y="
                    + event.getBounds().getPosition().getY());
            // compute relative position
            GPoint point = computeRelativeGPoint(event.getBounds(), participant);
            // build GNode
            EventGNode eventNode = new EventGNodeBuilder(event) //
                    .position(point) //
                    .build();

            if (BPMNTypes.BOUNDARY_EVENT.equals(event.getType())) {
                // resolve task ref
                String taskrefID = event.getAttribute("attachedToRef");
                eventNode.getArgs().put("attachedToRef", taskrefID);
            }

            // compute the symbol for the BPMNEvent
            String symbol = null;
            Set<Element> eventDefinitionList = event.getEventDefinitions();
            if (eventDefinitionList.size() > 0) {
                for (Node eventDefinition : eventDefinitionList) {
                    if (symbol == null) {
                        symbol = eventDefinition.getLocalName();
                    } else {
                        // we already have a symbol - Switch to Multiple Symbol?
                        if (!symbol.equals(eventDefinition.getLocalName())) {
                            symbol = BPMNTypes.MULTIPLE_EVENT_DEFINITIONS;
                        }
                    }
                }
                eventNode.setSymbol(symbol);
            }
            gNodeList.add(eventNode);
            // apply BPMN Extensions
            applyBPMNExtensions(eventNode, event);

            // now add a GLabel
            BPMNLabel bpmnLabel = event.getLabel();
            LabelGNode labelNode = createLabelNode(bpmnLabel, event, participant);
            gNodeList.add(labelNode);
        }

        // Add all Gateways...
        for (BPMNGateway gateway : process.getGateways()) {
            logger.debug("BPMNGateway: " + gateway.getName() + " x=" + gateway.getBounds().getPosition().getX() + " y="
                    + gateway.getBounds().getPosition().getY());
            GPoint point = computeRelativeGPoint(gateway.getBounds(), participant);

            // Build the GLSP Node....
            GatewayGNode gatewayNode = new GatewayGNodeBuilder(gateway) //
                    .position(point) //
                    .build();

            gNodeList.add(gatewayNode);
            // apply BPMN Extensions
            applyBPMNExtensions(gatewayNode, gateway);

            // now add a GLabel
            BPMNLabel bpmnLabel = gateway.getLabel();
            LabelGNode labelNode = createLabelNode(bpmnLabel, gateway, participant);
            gNodeList.add(labelNode);
        }

        // Add all Dataobjects...
        for (BPMNDataObject dataObject : process.getDataObjects()) {
            logger.debug("dataObject: " + dataObject.getName());
            GPoint point = computeRelativeGPoint(dataObject.getBounds(), participant);

            // Build the GLSP Node....
            DataObjectGNode dataObjectNode = new DataObjectGNodeBuilder(dataObject) //
                    .position(point) //
                    .build();

            gNodeList.add(dataObjectNode);
            // apply BPMN Extensions
            applyBPMNExtensions(dataObjectNode, dataObject);

            // now add a GLabel
            BPMNLabel bpmnLabel = dataObject.getLabel();
            LabelGNode labelNode = createLabelNode(bpmnLabel, dataObject, participant);
            gNodeList.add(labelNode);
        }

        // Add all SequenceFlows
        for (BPMNSequenceFlow sequenceFlow : process.getSequenceFlows()) {
            // first we need to verify if the target and source objects exist in our model
            // if not we need to skip this sequenceFlow element!
            GModelElement source = findElementById(gNodeList, sequenceFlow.getSourceRef());
            GModelElement target = findElementById(gNodeList, sequenceFlow.getTargetRef());
            if (source == null) {
                logger.warn("Source element '" + sequenceFlow.getSourceRef() + "' not found - skip BPMNSequenceFlow id="
                        + sequenceFlow.getId());
                continue;
            }
            if (target == null) {
                logger.warn("Target element '" + sequenceFlow.getTargetRef() + "' not found - skip BPMNSequenceFlow id="
                        + sequenceFlow.getId());
                continue;
            }

            // now construct the GNode and add it to the model....
            SequenceFlowGNodeBuilder builder = new SequenceFlowGNodeBuilder();
            builder.target(computeGPort(target));
            builder.source(computeGPort(source));
            builder.id(sequenceFlow.getId());
            SequenceFlowGNode sequenceFlowEdge = builder.build();
            // because it is unclear how to deal with anchor points we ignore the
            // achorpoints for now
            // See issue #102
            Iterator<BPMNPoint> wayPointIterator = sequenceFlow.getWayPoints().iterator();
            // skip the first (anchor point) if exists
            if (wayPointIterator.hasNext()) {
                wayPointIterator.next();
                // skip...
            }
            while (wayPointIterator.hasNext()) {
                BPMNPoint wayPoint = wayPointIterator.next();
                // skip the last (anchor point) if exists
                if (!wayPointIterator.hasNext()) {
                    break;
                    // skip...
                }
                // add the waypoint to the GLSP model....
                // GPoint point = GraphUtil.point(wayPoint.getX(), wayPoint.getY());
                GPoint point = computeRelativeGPoint(wayPoint, participant);
                sequenceFlowEdge.getRoutingPoints().add(point);
            }
//            for (BPMNPoint wayPoint : sequenceFlow.getWayPoints()) {
//                GPoint point = GraphUtil.point(wayPoint.getX(), wayPoint.getY());
//                sequenceFlowEdge.getRoutingPoints().add(point);
//            }
            gNodeList.add(sequenceFlowEdge);
        }

        return gNodeList;
    }

    /**
     * This helper method creates a GLSP LableGNode element to a corresponding
     * BPMNFlowElement (Event|Gateway|DataObject)
     *
     * @param bpmnLabel
     * @param flowElement
     * @param participant
     * @return
     * @throws BPMNMissingElementException
     */
    private LabelGNode createLabelNode(final BPMNLabel bpmnLabel, final BPMNFlowElement flowElement,
            final BPMNParticipant participant) throws BPMNMissingElementException {
        logger.debug("BPMNLabel: x=" + bpmnLabel.getBounds().getPosition().getX() + " y="
                + bpmnLabel.getBounds().getPosition().getY());
        GPoint labelPoint = GraphUtil.point(bpmnLabel.getPosition().getX(), bpmnLabel.getPosition().getY());
        // compute relative point...
        labelPoint = computeRelativeGPoint(labelPoint, participant);
        labelPoint.setX(labelPoint.getX() - 0);
        // now we build the LabelGNode
        logger.debug("label GPoint: x=" + labelPoint.getX() + " y=" + labelPoint.getY());
        LabelGNode labelNode = new LabelGNodeBuilder(flowElement) //
                .position(labelPoint) //
                .size(bpmnLabel.getBounds().getDimension().getWidth(), bpmnLabel.getBounds().getDimension().getHeight()) //
                .build();

        return labelNode;
    }

    /**
     * This method computes a GPoint based on a BPMNBounds object.
     * <p>
     * In case an optional Pool is provided the coordinates are computed relative to
     * the Pool position within the diagram.
     *
     * @param bpmnBounds
     * @param gPointReference
     * @return
     * @throws BPMNMissingElementException
     */
    GPoint computeRelativeGPoint(final BPMNPoint bpmnPoint, final BPMNParticipant participant) {
        GPoint result = GraphUtil.point(bpmnPoint.getX(), bpmnPoint.getY());
        return computeRelativeGPoint(result, participant);
    }

    GPoint computeRelativeGPoint(final BPMNBounds bpmnBounds, final BPMNParticipant participant) {
        return computeRelativeGPoint(bpmnBounds.getPosition(), participant);
    }

    GPoint computeRelativeGPoint(final GPoint basisPoint, final BPMNParticipant participant) {
        // compute relative position if we have a container...
        if (participant != null) {
            try {
                BPMNBounds bpmnPoolBounds = participant.getBounds();
                GPoint poolGPoint = GraphUtil.point(bpmnPoolBounds.getPosition().getX(),
                        bpmnPoolBounds.getPosition().getY());
                basisPoint.setX(basisPoint.getX() - poolGPoint.getX());
                basisPoint.setY(basisPoint.getY() - poolGPoint.getY());
            } catch (BPMNMissingElementException e) {
                logger.fatal("Failed to compute relative GPoint of Pool element: " + e.getMessage());
            }
        }
        return basisPoint;
    }

    /**
     * This method tests if the given element has a Child of type GPort. This is the
     * case for Events and Gateways. In this case the method returns the GPort.
     * Otherwise the method returns the element.
     *
     * @return GPort of a GModelElement or the element if no GPort exists
     */
    GModelElement computeGPort(final GModelElement element) {

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
}
