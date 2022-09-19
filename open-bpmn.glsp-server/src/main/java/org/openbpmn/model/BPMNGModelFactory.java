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
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.GPort;
import org.eclipse.glsp.graph.GraphFactory;
import org.eclipse.glsp.graph.builder.impl.GGraphBuilder;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.features.core.model.GModelFactory;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNFlowElement;
import org.openbpmn.bpmn.elements.BPMNGateway;
import org.openbpmn.bpmn.elements.BPMNLabel;
import org.openbpmn.bpmn.elements.BPMNParticipant;
import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.BPMNSequenceFlow;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.extension.BPMNExtension;
import org.openbpmn.glsp.bpmn.EventNode;
import org.openbpmn.glsp.bpmn.GatewayNode;
import org.openbpmn.glsp.bpmn.Pool;
import org.openbpmn.glsp.bpmn.SequenceFlow;
import org.openbpmn.glsp.bpmn.TaskNode;
import org.openbpmn.glsp.elements.event.EventNodeBuilder;
import org.openbpmn.glsp.elements.flow.SequenceFlowBuilder;
import org.openbpmn.glsp.elements.gateway.GatewayNodeBuilder;
import org.openbpmn.glsp.elements.pool.PoolNodeBuilder;
import org.openbpmn.glsp.elements.task.TaskNodeBuilder;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.utils.BPMNBuilderHelper;
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
    private static Logger logger = Logger.getLogger(BPMNGModelFactory.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected Set<BPMNExtension> extensions;

    @Override
    public void createGModel() {
        // verify extensions....
        if (extensions == null || extensions.size() == 0) {
            logger.warning("no BPMNExtension found! Check DiagramModule->configureBPMNExtensions");
        }

        if (!modelState.isInitalized()) {
            long l = System.currentTimeMillis();
            GGraph newGModel = null;
            BPMNModel model = modelState.getBpmnModel();
            if (model != null) {
                try {
                    BPMNProcess process = model.openContext(null);
                    if (process != null) {
                        newGModel = createGModelFromProcess(process, modelState);
                        modelState.updateRoot(newGModel);
                        modelState.getRoot().setRevision(-1);
                    }
                } catch (BPMNModelException e) {
                    e.printStackTrace();
                }
            }
            if (newGModel == null) {
                logger.warning("Unable to create model - no processes found - creating an empty model");
                createNewEmptyRoot("process_0");
            }

            modelState.setInitalized(true);
            logger.info("===> createGModel took " + (System.currentTimeMillis() - l) + "ms");
        } else {
            logger.fine("===> createGModel skipped!");
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
     * This method creates a GModel form a BPMN process instance
     * <p>
     * The method is called by the {@link BPMNGModelFactory}
     *
     * @param process
     * @param modelState
     * @return new GGraph
     */
    public GGraph createGModelFromProcess(final BPMNProcess process, final GModelState modelState) {

        List<GModelElement> entityNodes = new ArrayList<>();
        try {

            // Add all Pools
            for (BPMNParticipant participant : process.getParticipants()) {
                logger.info("participant: " + participant.getName());
                BPMNPoint bpmnPoint = participant.getBounds().getPosition();
                GPoint point = GraphUtil.point(bpmnPoint.getX(), bpmnPoint.getY());

                PoolNodeBuilder builder = new PoolNodeBuilder(participant.getType(), participant.getName());
                // Build the GLSP Node....
                Pool pool = builder //
                        .id(participant.getId()) //
                        .position(point) //
                        .build();

                // applyBPMNExtensions(taskNode, activity);

                entityNodes.add(pool);
            }

            // Add all Tasks
            for (BPMNActivity activity : process.getActivities()) {
                logger.fine("activity: " + activity.getName());
                BPMNPoint bpmnPoint = activity.getBounds().getPosition();
                GPoint point = GraphUtil.point(bpmnPoint.getX(), bpmnPoint.getY());
                TaskNodeBuilder builder = new TaskNodeBuilder(activity.getType(), activity.getName());
                // Build the GLSP Node....
                TaskNode taskNode = builder //
                        .id(activity.getId()) //
                        .position(point) //
                        .build();

                applyBPMNExtensions(taskNode, activity);

                entityNodes.add(taskNode);
            }

            // Add all Events...
            for (BPMNEvent event : process.getEvents()) {
                logger.fine("event: " + event.getName());

                BPMNPoint bpmnPoint = event.getBounds().getPosition();
                GPoint point = GraphUtil.point(bpmnPoint.getX(), bpmnPoint.getY());
                EventNodeBuilder builder = new EventNodeBuilder(event.getType(), event.getName());
                // Build the GLSP Node....
                EventNode eventNode = builder //
                        .id(event.getId()) //
                        .position(point) //
                        .build();

                // compute the symbol for the BPMNEvent
                String symbol = null;
                List<Element> eventDefinitionList = event.getEventDefinitions();
                if (eventDefinitionList.size() > 0) {
                    for (Node eventDefinition : eventDefinitionList) {
                        if (symbol == null) {
                            symbol = eventDefinition.getLocalName();
                        } else {
                            // we already have a symbol - Switch ot Multiple Symbol?
                            if (!symbol.equals(eventDefinition.getLocalName())) {
                                symbol = BPMNTypes.MULTIPLE_EVENT_DEFINITIONS;
                            }
                        }
                    }
                    eventNode.setSymbol(symbol);
                }

                applyBPMNExtensions(eventNode, event);

                entityNodes.add(eventNode);

                // now add a GLabel
                GLabel label = null;
                BPMNLabel bpmnLabel = event.getLabel();
                if (bpmnLabel != null) {
                    label = BPMNBuilderHelper.createBPMNLabel(event.getId(), eventNode.getName(),
                            bpmnLabel.getPosition().getX() - 3, bpmnLabel.getPosition().getY());
                } else {
                    // create default position
                    label = BPMNBuilderHelper.createBPMNLabel(event.getId(), eventNode.getName(), point.getX() - 3,
                            point.getY() + 36);
                }
                entityNodes.add(label);
            }

            // Add all Gateways...
            for (BPMNGateway gateway : process.getGateways()) {
                logger.fine("gateway: " + gateway.getName());
                BPMNPoint bpmnPoint = gateway.getBounds().getPosition();
                GPoint point = GraphUtil.point(bpmnPoint.getX(), bpmnPoint.getY());
                GatewayNodeBuilder builder = new GatewayNodeBuilder(gateway.getType(), gateway.getName());
                // Build the GLSP Node....
                GatewayNode gatewayNode = builder //
                        .id(gateway.getId()) //
                        .position(point) //
                        .build();
                entityNodes.add(gatewayNode);

                applyBPMNExtensions(gatewayNode, gateway);

                // now add a GLabel
                GLabel label = null;
                BPMNLabel bpmnLabel = gateway.getLabel();
                if (bpmnLabel != null) {
                    label = BPMNBuilderHelper.createBPMNLabel(gateway.getId(), gatewayNode.getName(),
                            bpmnLabel.getPosition().getX() - 5, bpmnLabel.getPosition().getY());
                } else {
                    // create default position
                    label = BPMNBuilderHelper.createBPMNLabel(gateway.getId(), gatewayNode.getName(), point.getX() - 5,
                            point.getY() + 50);
                }
                entityNodes.add(label);
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
        } catch (BPMNModelException e) {
            e.printStackTrace();
        }
        // add to root node...
        GGraph newGModel = new GGraphBuilder() //
                .id(process.getId()) //
                .addAll(entityNodes) //
                .build();

        return newGModel;
    }

    /**
     * This method apply all possible BPMNExtension to the GNode. This is to build
     * the JSONForms Sections and add additional classes for non-default Extensions.
     *
     * @param elementNode
     * @param bpmnElement
     */
    void applyBPMNExtensions(final GNode elementNode, final BPMNFlowElement bpmnElement) {
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
     * This method tests if the given element has a Child of type GPort. This is the
     * case for Events and Gateways. In this case the method returns the GPort.
     * Otherwise the method returns the element.
     *
     * @return GPort of an alement or the element if no GPort exists
     */
    private GModelElement findPort(final GModelElement element) {

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
