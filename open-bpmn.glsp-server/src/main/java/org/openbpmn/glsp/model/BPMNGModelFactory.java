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

package org.openbpmn.glsp.model;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.GPort;
import org.eclipse.glsp.graph.GraphFactory;
import org.eclipse.glsp.graph.builder.impl.GArguments;
import org.eclipse.glsp.graph.builder.impl.GGraphBuilder;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.features.core.model.GModelFactory;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Association;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.DataObject;
import org.openbpmn.bpmn.elements.DataStoreReference;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.elements.Message;
import org.openbpmn.bpmn.elements.MessageFlow;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.TextAnnotation;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelUtil;
import org.openbpmn.extensions.BPMNElementExtension;
import org.openbpmn.glsp.bpmn.BPMNGEdge;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.bpmn.DataObjectGNode;
import org.openbpmn.glsp.bpmn.DataStoreGNode;
import org.openbpmn.glsp.bpmn.EventGNode;
import org.openbpmn.glsp.bpmn.GatewayGNode;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.bpmn.LaneGNode;
import org.openbpmn.glsp.bpmn.MessageGNode;
import org.openbpmn.glsp.bpmn.PoolGNode;
import org.openbpmn.glsp.bpmn.TaskGNode;
import org.openbpmn.glsp.bpmn.TextAnnotationGNode;
import org.openbpmn.glsp.elements.data.DataObjectGNodeBuilder;
import org.openbpmn.glsp.elements.data.DataStoreGNodeBuilder;
import org.openbpmn.glsp.elements.data.MessageGNodeBuilder;
import org.openbpmn.glsp.elements.data.TextAnnotationGNodeBuilder;
import org.openbpmn.glsp.elements.edge.BPMNGEdgeBuilder;
import org.openbpmn.glsp.elements.event.EventGNodeBuilder;
import org.openbpmn.glsp.elements.gateway.GatewayGNodeBuilder;
import org.openbpmn.glsp.elements.label.LabelGNodeBuilder;
import org.openbpmn.glsp.elements.pool.LaneGNodeBuilder;
import org.openbpmn.glsp.elements.pool.PoolGNodeBuilder;
import org.openbpmn.glsp.elements.task.TaskGNodeBuilder;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.utils.BPMNGModelUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.google.inject.Inject;

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
 * The factory class is parsing the bpmndi:BPMNPlane elements to build a GModel
 * Tree.
 * 
 **/
public class BPMNGModelFactory implements GModelFactory {

    private static Logger logger = LogManager.getLogger(BPMNGModelFactory.class);

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected Set<BPMNElementExtension> extensions;

    /**
     * Create a GModelRoot from a BPMNModel specified in the modelState. This method
     * also produces and sets a GModelIndex in the model state that allows mapping
     * from graph model elements to source model elements and vice versa.
     */
    @Override
    public void createGModel() {
        long l = System.currentTimeMillis();
        // verify extensions....
        if (extensions == null || extensions.size() == 0) {
            logger.warn("no BPMNExtension found! Check DiagramModule->configureBPMNExtensions");
        }

        if (!modelState.isInitialized()) {

            int revision = 0;
            if (modelState.getRoot() != null) {
                revision = modelState.getRoot().getRevision();
            }

            logger.debug("===>  Build new GModel - revision=" + revision);

            GGraph newGModel = buildGGraph(modelState.getBpmnModel());

            modelState.updateRoot(newGModel);

            if (newGModel != null) {
                // restore revision
                newGModel.setRevision(revision);
                // do not reset revision!
                // see https://github.com/eclipse-glsp/glsp/discussions/949
            } else {
                logger.warn("Unable to create model - no processes found - creating an empty  model");
                newGModel = (GGraph) createNewEmptyRoot("process_0");
            }

            modelState.setInitialized(true);
            modelState.storeRevision();

            // If the model is empty we set the auto-align feature = true
            if (modelState.getBpmnModel().isEmpty()) {
                modelState.setAutoAlign(true);
            }

            logger.debug("===> createGModel took " + (System.currentTimeMillis() - l) + "ms - revision="
                    + modelState.getRoot().getRevision());
        } else {
            logger.debug("===> createGModel skipped!");
        }
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
     * The GModel tree is build from the bpmndi:BPMNPlane.
     * The GPoint of a BPMNFlowElement contained in a BPMNParticipant element need
     * to be computed relative to the GPoint of the Pool
     *
     * @return new GGraph
     */
    public GGraph buildGGraph(final BPMNModel model) {

        // create the RootBuilder
        GGraphBuilder rootBuilder = new GGraphBuilder() //
                .id(modelState.getRootID());

        List<GModelElement> gRootNodeList = new ArrayList<>();
        Map<String, PoolGNode> poolGNodeList = new HashMap<String, PoolGNode>();

        BPMNProcess defaultProcess = model.openDefaultProces();
        try {
            // In case we have collaboration diagram we iterate over all participants and
            // create a pool if the contained process is private. Otherwise we create the
            // default process
            if (model.isCollaborationDiagram()) {
                Set<Participant> participants = model.getParticipants();
                for (Participant participant : participants) {
                    if ((participant.getProcessRef() == null || participant.getProcessRef().isEmpty())
                            && !"Public".equals(participant.getType())) {
                        // in case the participant does not refer to a process then we skip this element
                        continue;
                    }
                    logger.debug(
                            "participant: " + participant.getName() + " BPMNProcess=" + participant.getProcessRef());
                    BPMNProcess bpmnProcess = model.openProcess(participant.getProcessRef());
                    // Add a Pool if the process is private
                    if (BPMNTypes.PROCESS_TYPE_PRIVATE.equals(bpmnProcess.getProcessType())) {
                        List<GModelElement> childList = computeGModelElements(bpmnProcess, participant, gRootNodeList);

                        PoolGNode pool = new PoolGNodeBuilder(participant) //
                                .addAll(childList) //
                                .build();
                        // apply BPMN Extensions
                        applyBPMNElementExtensions(pool, participant);

                        // apply lane-divider
                        addLaneDividers(participant, pool);

                        gRootNodeList.add(pool);
                        poolGNodeList.put(participant.getId(), pool);
                    } else {
                        // add default process without a pool
                        // here it is important to verify if the participant refers this process!
                        gRootNodeList.addAll(computeGModelElements(bpmnProcess, null, gRootNodeList));
                    }
                }
            } else {
                // We have a simple 'Process Diagram' type - build the GModel from default
                // process
                BPMNProcess bpmnProcess = model.openDefaultProces();
                gRootNodeList.addAll(computeGModelElements(bpmnProcess, null, gRootNodeList));
            }

            // Next add all Message objects.
            // A message object is not assigned to any process!
            for (Message message : modelState.getBpmnModel().getMessages()) {
                logger.debug("message: " + message.getName());
                GPoint point = computeRelativeGPoint(message.getBounds(), null);
                List<GModelElement> containerNodeList = gRootNodeList; // default add message to root

                // Build the GLSP Node....
                MessageGNode messageNode = new MessageGNodeBuilder(message) //
                        .position(point) //
                        .build();
                containerNodeList.add(messageNode);
                // now add a GLabel
                BPMNLabel bpmnLabel = message.getLabel();
                LabelGNode labelNode = createLabelNode(bpmnLabel, message, null);
                containerNodeList.add(labelNode);

                // finally apply BPMN Extensions
                applyBPMNElementExtensions(messageNode, message);
            }

            // Add all MessageFlow elements...
            createMessageFlowGEdges(model.getMessageFlows(), gRootNodeList);

            // Add all Associations elements...
            Set<BPMNProcess> processList = model.getProcesses();
            for (BPMNProcess _process : processList) {
                // apply the associations for each process separately.
                // NOTE: It is necessary to verify if the Association is inside a Pool. In this
                // case the Association becomes a child of the PoolGNode
                if (!_process.isPublicProcess()) {
                    // the Associations inside a Pool, we need to add the edge to the
                    // PoolGNode childList
                    Participant _participant = _process.findParticipant();
                    PoolGNode pool = poolGNodeList.get(_participant.getId());
                    createAssociationGEdges(_process.getAssociations(), pool.getChildren(), _participant);
                } else {
                    createAssociationGEdges(_process.getAssociations(), gRootNodeList, null);
                }
            }
        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        // add the rootNodeList
        rootBuilder.addAll(gRootNodeList);
        GGraph newGModel = rootBuilder.build();

        // finally add the root extensions
        applyBPMNElementExtensions(newGModel, defaultProcess);

        return newGModel;
    }

    /**
     * This helper method adds the gLaneDividers to a pool.
     * The method takes care about the issue #304, that the order of a LaneSet must
     * not necessarily be the same as the visual position. For this reason the
     * method first sorts the lanes by its y-position and later computes the correct
     * divider position. This is just a GNode Display problem and has nothing to do
     * with the BPMN meta model
     * 
     * @param participant
     * @param pool
     * @throws BPMNMissingElementException
     */
    private void addLaneDividers(Participant participant, PoolGNode pool) throws BPMNMissingElementException {
        if (participant.getBpmnProcess().getLanes().size() > 1) {
            // add a divider between each lane
            double poolYPos = participant.getBounds().getPosition().getY();
            // Issue #304:
            // The lane order must not necessarily be the same as the visual position
            // For this reason we first sort the lanes by its y-position to compute
            // the laneDivider positions correctly.
            List<Lane> orderLaneList = new ArrayList<>();
            orderLaneList.addAll(participant.getBpmnProcess().getLanes());
            // Sort the list based on the 'y' attribute
            Collections.sort(orderLaneList, new Comparator<Lane>() {
                @Override
                public int compare(Lane obj1, Lane obj2) {
                    try {
                        double y1 = obj1.getBounds().getPosition().getY();
                        double y2 = obj2.getBounds().getPosition().getY();
                        return Double.compare(y1, y2);
                    } catch (BPMNMissingElementException e) {
                        return 0;
                    }
                }
            });

            // now we can iterate the ordered list of lanes to compute the divider positions
            Iterator<Lane> laneIterator = orderLaneList.iterator();
            Lane currentLane = laneIterator.next();
            while (currentLane != null) {
                Lane nextLane = null;
                if (!laneIterator.hasNext()) {
                    break; // skip last lane
                }
                nextLane = laneIterator.next();
                double laneYPos = currentLane.getBounds().getPosition().getY() - poolYPos;
                double laneHeight = currentLane.getBounds().getDimension().getHeight();
                double laneDividerYPos = laneYPos + laneHeight - 1;
                double laneMinYPos = laneDividerYPos - laneHeight + Lane.MIN_HEIGHT + 1;
                double laneMaxYPos = laneDividerYPos + nextLane.getBounds().getDimension().getHeight()
                        - Lane.MIN_HEIGHT + 1;
                pool.getChildren()
                        .add(BPMNGModelUtil.createLaneDivider(participant, laneDividerYPos,
                                laneMinYPos,
                                laneMaxYPos,
                                currentLane.getId(),
                                nextLane.getId()));
                currentLane = nextLane;
            }
        }
    }

    /**
     * This method apply all possible BPMNElementExtension to the GNode. This is to
     * build
     * the JSONForms Sections and add additional classes for non-default Extensions.
     *
     * @param elementNode
     * @param bpmnElement
     */
    public void applyBPMNElementExtensions(final GModelElement elementNode, final BPMNElement bpmnElement) {
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

            // sort extensions by priority
            List<BPMNElementExtension> sortedExtensions = new ArrayList<>();
            sortedExtensions.addAll(extensions);
            Comparator<BPMNElementExtension> byPriority = Comparator.comparing(BPMNElementExtension::getPriority);
            Collections.sort(sortedExtensions, byPriority);

            for (BPMNElementExtension extension : sortedExtensions) {
                // validate if the extension can handle this BPMN element
                if (extension.handlesBPMNElement(bpmnElement)) {
                    // add JSONForms Schemata
                    extension.buildPropertiesForm(bpmnElement, dataBuilder, schemaBuilder, uiSchemaBuilder);
                    dataBuilder.closeArray();
                    schemaBuilder.closeArray();

                    // set the optional extension label
                    String extensionInfo = extension.getInfo(bpmnElement);
                    if (extensionInfo != null && !extensionInfo.isEmpty()) {
                        elementNode.getArgs().put(BPMNElementExtension.INFO, extensionInfo);
                        bpmnElement.getArgs().put(BPMNElementExtension.INFO, extensionInfo);
                    }
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
            String dataSchema = dataBuilder.build();
            logger.debug(dataSchema);
            elementNode.getArgs().put("JSONFormsData", dataSchema);
            bpmnElement.getArgs().put("JSONFormsData", dataSchema);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Build JSONForms UISchema
        try (Writer writer = new StringWriter()) {
            String uiSchema = uiSchemaBuilder.build();
            logger.debug(uiSchema);
            elementNode.getArgs().put("JSONFormsUISchema", uiSchema);
            bpmnElement.getArgs().put("JSONFormsUISchema", uiSchema);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Build JSONForms Schema
        try (Writer writer = new StringWriter()) {
            String schema = schemaBuilder.build();
            logger.debug(schema);
            elementNode.getArgs().put("JSONFormsSchema", schema);
            bpmnElement.getArgs().put("JSONFormsSchema", schema);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This helper method finds an GModelElement by its ID in a given List of
     * GModelElements. The method returns null if no element with the given ID
     * exists.
     * 
     * If the list of elements contains Pools the method searches recursively the
     * elements within the pool.
     *
     * @param entityNodes - List of GModelElements
     * @param id          - id to search for
     * @return GModelElement - or null if no elment was found.
     */
    GModelElement findGElementById(final List<GModelElement> entityNodes, final String id) {
        if (entityNodes != null) {
            for (GModelElement element : entityNodes) {
                if (element.getId().equals(id)) {
                    return element;
                }
                // do we have a childs....

                EList<GModelElement> childs = element.getChildren();
                if (childs != null && childs.size() > 0) {
                    // recursive call....
                    GModelElement child = findGElementById(childs, id);
                    if (child != null) {
                        return child;
                    }
                }
                /*
                 * if (element instanceof PoolGNode) {
                 * // recursive call!
                 * PoolGNode gPool = (PoolGNode) element;
                 * GModelElement child = findGElementById(gPool.getChildren(), id);
                 * if (child != null) {
                 * return child;
                 * }
                 * }
                 */
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
    List<GModelElement> computeGModelElements(final BPMNProcess process, final Participant participant,
            List<GModelElement> gRootNodeList)
            throws BPMNMissingElementException {

        logger.debug("...computeGModel for process '" + process.getId() + "' type=" + process.getProcessType());
        List<GModelElement> gNodeList = new ArrayList<>();

        // Add all Lanes
        for (Lane lane : process.getLanes()) {
            logger.debug("lane: " + lane.getName());
            // compute relative position
            GPoint point = computeRelativeGPoint(lane.getBounds(), participant);
            // build GNode
            LaneGNode laneNode = new LaneGNodeBuilder(lane) //
                    .position(point) //
                    .build();

            gNodeList.add(laneNode);
        }

        // Add all Tasks
        for (Activity activity : process.getActivities()) {
            logger.debug("activity: " + activity.getName());
            // compute relative position
            GPoint point = computeRelativeGPoint(activity.getBounds(), participant);
            // build GNode
            TaskGNode taskNode = new TaskGNodeBuilder(activity) //
                    .position(point) //
                    .build();

            // apply BPMN Extensions
            applyBPMNElementExtensions(taskNode, activity);

            GLabel extensionLabelNode = BPMNGModelUtil.findExtensionLabel(taskNode);
            if (extensionLabelNode != null) {
                extensionLabelNode.setText((String) taskNode.getArgs().get(BPMNElementExtension.INFO));
            }

            gNodeList.add(taskNode);
        }

        // Add all Events...
        for (Event event : process.getEvents()) {
            logger.debug("Event: " + event.getName() + " x=" + event.getBounds().getPosition().getX() + " y="
                    + event.getBounds().getPosition().getY());
            // compute relative position
            GPoint point = computeRelativeGPoint(event.getBounds(), participant);
            // build GNode
            EventGNode eventNode = new EventGNodeBuilder(event) //
                    .position(point) //
                    .build();

            if (BPMNTypes.BOUNDARY_EVENT.equals(event.getType())) {
                // resolve task ref
                String taskRefID = event.getAttribute("attachedToRef");
                eventNode.getArgs().put("attachedToRef", taskRefID);
            }

            // compute the symbol for the BPMNEvent
            String kind = null;
            Set<Element> eventDefinitionList = event.getEventDefinitions();
            if (eventDefinitionList.size() > 0) {
                for (Node eventDefinition : eventDefinitionList) {
                    if (kind == null) {
                        kind = eventDefinition.getLocalName();
                    } else {
                        // we already have a symbol - Switch to Multiple Symbol?
                        if (!kind.equals(eventDefinition.getLocalName())) {
                            kind = BPMNTypes.MULTIPLE_EVENT_DEFINITIONS;
                        }
                    }
                }
                eventNode.setKind(kind);
            }
            gNodeList.add(eventNode);
            // apply BPMN Extensions
            applyBPMNElementExtensions(eventNode, event);

            // now add a GLabel
            BPMNLabel bpmnLabel = event.getLabel();
            // create only if name is defined
            if (!event.getName().isEmpty()) {
                LabelGNode labelNode = createLabelNode(bpmnLabel, event, participant);
                gNodeList.add(labelNode);
            } else {
                // we do not draw gLabel because name is empty
                // reset the position
                BPMNModelUtil.resetLabelBounds(event);
            }
        }

        // Add all Gateways...
        for (Gateway gateway : process.getGateways()) {
            logger.debug("Gateway: " + gateway.getName() + " x=" + gateway.getBounds().getPosition().getX() + " y="
                    + gateway.getBounds().getPosition().getY());
            GPoint point = computeRelativeGPoint(gateway.getBounds(), participant);

            // Build the GLSP Node....
            GatewayGNode gatewayNode = new GatewayGNodeBuilder(gateway) //
                    .position(point) //
                    .build();

            gNodeList.add(gatewayNode);
            // apply BPMN Extensions
            applyBPMNElementExtensions(gatewayNode, gateway);

            // now add a GLabel
            BPMNLabel bpmnLabel = gateway.getLabel();
            // create only if name is defined
            if (!gateway.getName().isEmpty()) {
                LabelGNode labelNode = createLabelNode(bpmnLabel, gateway, participant);
                gNodeList.add(labelNode);
            } else {
                // we do not draw gLabel because name is empty
                // reset the position
                BPMNModelUtil.resetLabelBounds(gateway);
            }
        }

        // Add all Dataobjects...
        for (DataObject dataObject : process.getDataObjects()) {
            logger.debug("dataObject: " + dataObject.getName());
            GPoint point = computeRelativeGPoint(dataObject.getBounds(), participant);

            // Build the GLSP Node....
            DataObjectGNode dataObjectNode = new DataObjectGNodeBuilder(dataObject) //
                    .position(point) //
                    .build();
            gNodeList.add(dataObjectNode);
            // apply BPMN Extensions
            applyBPMNElementExtensions(dataObjectNode, dataObject);

            // now add a GLabel
            BPMNLabel bpmnLabel = dataObject.getLabel();
            LabelGNode labelNode = createLabelNode(bpmnLabel, dataObject, participant);
            gNodeList.add(labelNode);
        }

        // Add all DataStores...
        for (DataStoreReference dataStoreReference : process.getDataStoreReferences()) {
            logger.debug("dataStoreReference: " + dataStoreReference.getName());
            GPoint point = computeRelativeGPoint(dataStoreReference.getBounds(), participant);

            // Build the GLSP Node....
            DataStoreGNode dataStoreNode = new DataStoreGNodeBuilder(dataStoreReference) //
                    .position(point) //
                    .build();
            gNodeList.add(dataStoreNode);
            // apply BPMN Extensions
            applyBPMNElementExtensions(dataStoreNode, dataStoreReference);

            // now add a GLabel
            BPMNLabel bpmnLabel = dataStoreReference.getLabel();
            LabelGNode labelNode = createLabelNode(bpmnLabel, dataStoreReference, participant);
            gNodeList.add(labelNode);
        }

        // Add all Text Annotations
        for (TextAnnotation textAnnotation : process.getTextAnnotations()) {
            logger.debug("textAnnotation: " + textAnnotation.getId());
            // compute relative position
            GPoint point = computeRelativeGPoint(textAnnotation.getBounds(), participant);
            // build GNode
            TextAnnotationGNode textAnnotationNode = new TextAnnotationGNodeBuilder(textAnnotation) //
                    .position(point) //
                    .build();

            // apply BPMN Extensions
            applyBPMNElementExtensions(textAnnotationNode, textAnnotation);
            gNodeList.add(textAnnotationNode);
        }

        // Add all SequenceFlows
        createSequenceFlowGEdges(process.getSequenceFlows(), gNodeList, participant);

        // finally check if the positions of the node elements make sense.
        // In case all elements have the position 0,0 we adust the position here..
        autoLayout(gNodeList);

        return gNodeList;
    }

    /**
     * This is a helper method to detect missing bounds information.
     * The method tries to init some meaningful bounds in such a situation.
     * 
     * @param nodelist
     */
    private void autoLayout(List<GModelElement> nodelist) {
        boolean samePos = true;
        GPoint lastPoint = null;
        int gnodeCount = 0;
        for (GModelElement gElement : nodelist) {
            if (gElement instanceof BPMNGNode) {
                BPMNGNode gn = (BPMNGNode) gElement;
                GPoint position = gn.getPosition();
                if (lastPoint == null) {
                    lastPoint = position;
                }
                if (gn instanceof LabelGNode) {
                    continue;
                }
                gnodeCount++;
                if (lastPoint.getX() != position.getX()
                        || lastPoint.getY() != position.getY()) {
                    samePos = false;
                    // We found a new element position, so we assume all is fine
                    return;
                }
            }
        }
        // See issue #388 and #386
        // Maybe we need another strategy here and recompute the source model instead of
        // the GModel....?
        if (samePos && gnodeCount > 1) {
            // all elements seem to have the same position!
            // start auto layout
            long xPos = 0;
            for (GModelElement gElement : nodelist) {
                if (gElement instanceof BPMNGNode) {
                    BPMNGNode gn = (BPMNGNode) gElement;
                    gn.getPosition().setX(xPos);
                    if (gn instanceof LabelGNode) {
                        // ignore x offset for labels
                        continue;
                    }
                    xPos = xPos + 200;
                }
            }
        }
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
    GPoint computeRelativeGPoint(final BPMNPoint bpmnPoint, final Participant participant) {
        GPoint result = GraphUtil.point(bpmnPoint.getX(), bpmnPoint.getY());
        return computeRelativeGPoint(result, participant);
    }

    GPoint computeRelativeGPoint(final BPMNBounds bpmnBounds, final Participant participant) {
        return computeRelativeGPoint(bpmnBounds.getPosition(), participant);
    }

    GPoint computeRelativeGPoint(final GPoint basisPoint, final Participant participant) {
        // compute relative position if we have a container...
        if (participant != null && !participant.getBpmnProcess().isPublicProcess()) {
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

    /**
     * Helper method to import a list of BPMN SequenceFlows into a gModel
     * 
     * The Target and the Source must be contained by teh given participant.
     *
     * @param sequenceFlows
     * @param gNodeList
     * @param participant
     */
    private void createSequenceFlowGEdges(final Set<SequenceFlow> sequenceFlows, final List<GModelElement> gNodeList,
            final Participant participant) {
        // Add all SequenceFlows
        for (SequenceFlow sequenceFlow : sequenceFlows) {
            // first we need to verify if the target and source objects exist in our model
            // if not we need to skip this sequenceFlow element!
            GModelElement source = findGElementById(gNodeList, sequenceFlow.getSourceRef());
            GModelElement target = findGElementById(gNodeList, sequenceFlow.getTargetRef());
            if (source == null) {
                logger.warn("createGEdge '" + sequenceFlow.getId() + "' failed: Source element '"
                        + sequenceFlow.getSourceRef()
                        + "' not found");
                continue;
            }
            if (target == null) {
                logger.warn("createGEdge '" + sequenceFlow.getId() + "' failed: Target element '"
                        + sequenceFlow.getTargetRef()
                        + "' not found");
                continue;
            }

            // now construct the GNode and add it to the model....
            BPMNGEdgeBuilder builder = new BPMNGEdgeBuilder(sequenceFlow);
            builder.target(computeGPort(target));
            builder.source(computeGPort(source));
            // We add an edge Padding of 10 pixel.
            // See the view renderer in bpmn-routing-views.tsx.
            builder.addArgument(GArguments.edgePadding(10));
            BPMNGEdge bpmnGEdge = builder.build();
            bpmnGEdge.setKind("");
            for (BPMNPoint wayPoint : sequenceFlow.getWayPoints()) {
                // add the waypoint to the GLSP model....
                GPoint point = computeRelativeGPoint(wayPoint, participant);
                bpmnGEdge.getRoutingPoints().add(point);
            }

            // if the edge is a SequenceFlow and also is the Default flow, than we add the
            // argument "default=true"
            if (sequenceFlow.isDefault()) {
                bpmnGEdge.getArgs().put("default", "true");
            }

            // apply BPMN Extensions
            applyBPMNElementExtensions(bpmnGEdge, sequenceFlow);

            gNodeList.add(bpmnGEdge);
        }
    }

    /**
     * This helper method adds a list of Associations to gModel
     * list.
     * <p>
     * This method expects that all process instances are already resolved.
     * <p>
     * NOTE: The Association has a mixed behaviour. In case the Association is part
     * of a Participant, than the edge waypoints are relative in the GModel. If the
     * Association is part of the default process thant the waypoints are absolute.
     */
    private void createAssociationGEdges(final Set<Association> associations, final List<GModelElement> gRootNodeList,
            final Participant participant)
            throws BPMNMissingElementException {

        // Add all SequenceFlows
        for (Association association : associations) {
            // first we need to verify if the target and source objects exist in our model
            // if not we need to skip this messageFlow element!
            GModelElement source = findGElementById(gRootNodeList, association.getSourceRef());
            GModelElement target = findGElementById(gRootNodeList, association.getTargetRef());
            if (source == null) {
                logger.warn("Source element '" + association.getSourceRef() + "' not found - skip MessageFlow id="
                        + association.getId());
                continue;
            }
            if (target == null) {
                logger.warn("Target element '" + association.getTargetRef() + "' not found - skip MessageFlow id="
                        + association.getId());
                continue;
            }

            // now construct the GNode and add it to the model....
            BPMNGEdgeBuilder builder = new BPMNGEdgeBuilder(association);
            builder.target(computeGPort(target));
            builder.source(computeGPort(source));
            // We add an edge Padding of 10 pixel.
            // See the view renderer in bpmn-routing-views.tsx.
            builder.addArgument(GArguments.edgePadding(10));
            BPMNGEdge bpmnGEdge = builder.build();
            bpmnGEdge.setKind("");
            // add the waypoints to the GLSP model....
            for (BPMNPoint wayPoint : association.getWayPoints()) {
                // compute relative waypoints in case we are inside a Particpant
                GPoint point = null;
                if (participant != null) {
                    point = computeRelativeGPoint(wayPoint, participant);
                } else {
                    // compute absolute waypoints, because the association is part of the default
                    // process.
                    point = GraphUtil.point(wayPoint.getX(), wayPoint.getY());
                }
                bpmnGEdge.getRoutingPoints().add(point);
            }

            gRootNodeList.add(bpmnGEdge);
            // apply BPMN Extensions
            applyBPMNElementExtensions(bpmnGEdge, association);

        }

    }

    /**
     * This helper method adds a list of MessageFlows to a given rootNodeElement
     * list.
     * <p>
     * This method expects that all process instances are already resolved.
     */
    private void createMessageFlowGEdges(final Set<MessageFlow> messageFlows, final List<GModelElement> gRootNodeList)
            throws BPMNMissingElementException {
        // List<GModelElement> gNodeList = new ArrayList<>();

        // Add all SequenceFlows
        for (MessageFlow messageFlow : messageFlows) {
            // first we need to verify if the target and source objects exist in our model
            // if not we need to skip this messageFlow element!
            GModelElement source = findGElementById(gRootNodeList, messageFlow.getSourceRef());
            GModelElement target = findGElementById(gRootNodeList, messageFlow.getTargetRef());
            if (source == null) {
                logger.warn("Source element '" + messageFlow.getSourceRef() + "' not found - skip MessageFlow id="
                        + messageFlow.getId());
                continue;
            }
            if (target == null) {
                logger.warn("Target element '" + messageFlow.getTargetRef() + "' not found - skip MessageFlow id="
                        + messageFlow.getId());
                continue;
            }

            // now construct the GNode and add it to the model....
            BPMNGEdgeBuilder builder = new BPMNGEdgeBuilder(messageFlow);
            builder.target(computeGPort(target));
            builder.source(computeGPort(source));
            // We add an edge Padding of 10 pixel.
            // See the view renderer in bpmn-routing-views.tsx.
            builder.addArgument(GArguments.edgePadding(10));
            BPMNGEdge bpmnGEdge = builder.build();
            bpmnGEdge.setKind("");
            for (BPMNPoint wayPoint : messageFlow.getWayPoints()) {
                // add the waypoint to the GLSP model....
                GPoint point = GraphUtil.point(wayPoint.getX(), wayPoint.getY());
                bpmnGEdge.getRoutingPoints().add(point);
            }
            gRootNodeList.add(bpmnGEdge);
            // apply BPMN Extensions
            applyBPMNElementExtensions(bpmnGEdge, messageFlow);

        }

    }

    /**
     * This helper method creates a GLSP LableGNode element to a corresponding
     * BPMNFlowElement (Event|Gateway|DataObject|DataStore)
     *
     * @param bpmnLabel
     * @param flowElement
     * @param participant
     * @return
     * @throws BPMNMissingElementException
     */
    private LabelGNode createLabelNode(final BPMNLabel bpmnLabel, final BPMNElementNode flowElement,
            final Participant participant) throws BPMNMissingElementException {
        logger.debug("BPMNLabel: x=" + bpmnLabel.getBounds().getPosition().getX() + " y="
                + bpmnLabel.getBounds().getPosition().getY());

        LabelGNode labelNode = new LabelGNodeBuilder(flowElement).build();
        GPoint labelPoint = GraphUtil.point(bpmnLabel.getPosition().getX(), bpmnLabel.getPosition().getY());
        labelPoint = computeRelativeGPoint(labelPoint, participant);
        // update postion
        logger.debug("label GPoint: x=" + labelPoint.getX() + " y=" + labelPoint.getY());
        labelNode.setPosition(labelPoint);
        return labelNode;
    }
}
