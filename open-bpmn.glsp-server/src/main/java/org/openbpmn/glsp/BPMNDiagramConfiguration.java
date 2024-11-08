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
package org.openbpmn.glsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GraphPackage;
import org.eclipse.glsp.server.diagram.BaseDiagramConfiguration;
import org.eclipse.glsp.server.layout.ServerLayoutKind;
import org.eclipse.glsp.server.types.EdgeTypeHint;
import org.eclipse.glsp.server.types.ShapeTypeHint;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.extensions.BPMNElementExtension;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.utils.ModelTypes;

import com.google.inject.Inject;

/**
 * Provides configuration constants for a specific diagram implementation (i.e.
 * diagram language), The corresponding configuration for a diagram
 * implementation is identified via its diagram type.
 */
public class BPMNDiagramConfiguration extends BaseDiagramConfiguration {

    public static String DIAGRAM_TYPE = "bpmn-diagram";

    @Inject
    protected Set<BPMNElementExtension> extensions;

    /**
     * Returns the type mappings for the diagram implementation. Type mappings are
     * used by GSON to construct the correct {@link EClass} based on the "type"
     * property of the JSON object.
     *
     * @return A complete map of all type mappings for the diagram implementation.
     */
    @Override
    public Map<String, EClass> getTypeMappings() {
        Map<String, EClass> mappings = DefaultTypes.getDefaultTypeMappings();

        // Layout Model types
        mappings.put(ModelTypes.LABEL_HEADING, GraphPackage.Literals.GLABEL);
        // mappings.put(ModelTypes.COMP_HEADER, GraphPackage.Literals.GCOMPARTMENT);
        mappings.put(ModelTypes.ICON, BpmnPackage.Literals.ICON_GCOMPARTMENT);
        mappings.put(ModelTypes.CONTAINER, GraphPackage.Literals.GCOMPARTMENT);

        // BPMN Types
        mappings.put(BPMNTypes.TASK, BpmnPackage.Literals.TASK_GNODE);
        mappings.put(BPMNTypes.GATEWAY, BpmnPackage.Literals.GATEWAY_GNODE);
        mappings.put(BPMNTypes.EVENT, BpmnPackage.Literals.EVENT_GNODE);
        mappings.put(BPMNTypes.SEQUENCE_FLOW, BpmnPackage.Literals.BPMNG_EDGE);
        mappings.put(BPMNTypes.POOL, BpmnPackage.Literals.POOL_GNODE);
        mappings.put(BPMNTypes.LANE, BpmnPackage.Literals.LANE_GNODE);
        mappings.put(BPMNTypes.DATAOBJECT, BpmnPackage.Literals.DATA_OBJECT_GNODE);
        mappings.put(BPMNTypes.MESSAGE, BpmnPackage.Literals.MESSAGE_GNODE);
        mappings.put(BPMNTypes.TEXTANNOTATION, BpmnPackage.Literals.TEXT_ANNOTATION_GNODE);

        return mappings;
    }

    /**
     * Returns the shape type hints for the diagram implementation. Shape type hints
     * are sent to the client and used to validate whether certain operations for
     * shapes/nodes are allowed without having to query the server again.
     *
     * @return List of all shape type hints for the diagram implementation.
     */
    @Override
    public List<ShapeTypeHint> getShapeTypeHints() {
        List<ShapeTypeHint> nodeHints = new ArrayList<>();

        nodeHints.add(createRootHint());

        // Task ShapeTypeHints
        nodeHints.add(createTaskHint(BPMNTypes.TASK));
        nodeHints.add(createTaskHint(BPMNTypes.BUSINESSRULE_TASK));
        nodeHints.add(createTaskHint(BPMNTypes.MANUAL_TASK));
        nodeHints.add(createTaskHint(BPMNTypes.SCRIPT_TASK));
        nodeHints.add(createTaskHint(BPMNTypes.SERVICE_TASK));
        nodeHints.add(createTaskHint(BPMNTypes.SEND_TASK));
        nodeHints.add(createTaskHint(BPMNTypes.RECEIVE_TASK));
        nodeHints.add(createTaskHint(BPMNTypes.USER_TASK));

        // Event ShapeTypeHints
        nodeHints.add(createStartEventHint());
        nodeHints.add(createEndEventHint());
        nodeHints.add(createCatchEventHint());
        nodeHints.add(createThrowEventHint());
        nodeHints.add(createBoundaryEventHint());

        // Gateway ShapeEventTypes
        nodeHints.add(createGatewayHint(BPMNTypes.EXCLUSIVE_GATEWAY));
        nodeHints.add(createGatewayHint(BPMNTypes.PARALLEL_GATEWAY));
        nodeHints.add(createGatewayHint(BPMNTypes.INCLUSIVE_GATEWAY));
        nodeHints.add(createGatewayHint(BPMNTypes.EVENTBASED_GATEWAY));
        nodeHints.add(createGatewayHint(BPMNTypes.COMPLEX_GATEWAY));

        // DataObject
        nodeHints.add(new ShapeTypeHint(BPMNTypes.DATAOBJECT, true, true, false, false));
        // DataStore
        nodeHints.add(new ShapeTypeHint(BPMNTypes.DATASTORE, true, true, false, false));
        // Message
        nodeHints.add(new ShapeTypeHint(BPMNTypes.MESSAGE, true, true, false, false));
        // TextAnnotation
        nodeHints.add(new ShapeTypeHint(BPMNTypes.TEXTANNOTATION, true, true, true, false));
        // Pool ShapeEventTypes
        nodeHints.add(createPoolHint());
        nodeHints.add(createLaneHint());

        // BPMNLabel ShapeEventTypes
        nodeHints.add(createBPMNLabelHint());

        return nodeHints;
    }

    /**
     * Returns the edge type hints for the diagram implementation. Edge type hints
     * are sent to the client and used to validate whether certain operations for
     * edges are allowed without having to query the server again.
     * <p>
     * Note: here we need to add the :bpmn prefix which is used in the diagram.
     *
     * @return List of all edge type hints for the diagram implementation.
     */
    @Override
    public List<EdgeTypeHint> getEdgeTypeHints() {
        List<EdgeTypeHint> edgeHints = new ArrayList<>();

        // SequenceFLow
        EdgeTypeHint sequenceFlowHint = createDefaultEdgeTypeHint(BPMNTypes.SEQUENCE_FLOW);
        sequenceFlowHint.setSourceElementTypeIds(BPMNTypes.BPMN_FLOWELEMENT_NODES);
        sequenceFlowHint.setTargetElementTypeIds(BPMNTypes.BPMN_FLOWELEMENT_NODES);
        edgeHints.add(sequenceFlowHint);

        // MessageFLow
        EdgeTypeHint messageFlowHint = createDefaultEdgeTypeHint(BPMNTypes.MESSAGE_FLOW);
        messageFlowHint.setSourceElementTypeIds(BPMNTypes.BPMN_NODE_ELEMENTS);
        messageFlowHint.setTargetElementTypeIds(BPMNTypes.BPMN_NODE_ELEMENTS);
        edgeHints.add(messageFlowHint);

        // Association
        EdgeTypeHint associationHint = createDefaultEdgeTypeHint(BPMNTypes.ASSOCIATION);
        associationHint.setSourceElementTypeIds(BPMNTypes.BPMN_NODE_ELEMENTS);
        associationHint.setTargetElementTypeIds(BPMNTypes.BPMN_NODE_ELEMENTS);
        edgeHints.add(associationHint);

        return edgeHints;
    }

    /**
     * Defines the hints for root diagram plane
     *
     * @return
     */
    private ShapeTypeHint createRootHint() {
        ShapeTypeHint rootHint = new ShapeTypeHint(DefaultTypes.GRAPH, false, false, false, false);
        List<String> elementList = new ArrayList<>();
        elementList.addAll(BPMNTypes.BPMN_FLOWELEMENT_NODES);
        // remove Boundary Event
        elementList.remove(BPMNTypes.BOUNDARY_EVENT);

        // add special types
        elementList.add(BPMNTypes.DATAOBJECT);
        elementList.add(BPMNTypes.DATASTORE);
        elementList.add(BPMNTypes.MESSAGE);
        elementList.add(BPMNTypes.TEXTANNOTATION);
        elementList.add(BPMNTypes.POOL);

        rootHint.setContainableElementTypeIds(elementList);
        return rootHint;
    }

    private ShapeTypeHint createPoolHint() {
        ShapeTypeHint poolHint = new ShapeTypeHint(BPMNTypes.POOL, true, true, true, true);
        List<String> elementList = new ArrayList<>();
        elementList.addAll(BPMNTypes.BPMN_FLOWELEMENT_NODES);
        // remove Boundary Event
        elementList.remove(BPMNTypes.BOUNDARY_EVENT);
        // add lane
        elementList.add(BPMNTypes.LANE);
        // add TextAnnotation
        elementList.add(BPMNTypes.TEXTANNOTATION);

        poolHint.setContainableElementTypeIds(elementList);
        return poolHint;
    }

    /**
     * Lanes are not moveable...
     *
     * @return
     */
    private ShapeTypeHint createLaneHint() {
        ShapeTypeHint laneHint = new ShapeTypeHint(BPMNTypes.LANE, false, true, false, true);
        List<String> elementList = new ArrayList<>();
        elementList.addAll(BPMNTypes.BPMN_FLOWELEMENT_NODES);
        // remove Boundary Event
        elementList.remove(BPMNTypes.BOUNDARY_EVENT);

        laneHint.setContainableElementTypeIds(elementList);
        return laneHint;
    }

    /**
     * Creates a Task ShapeTypeHint
     * <p>
     * The method defines the containable BoundaryEvent
     *
     * @return
     */
    private ShapeTypeHint createTaskHint(final String taskType) {
        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(taskType, true, true, true, true);

        List<String> containable = new ArrayList<>();
        // add Boundary Event as the only containable for a Task Element
        containable.addAll(Arrays.asList(new String[] { //
                BPMNTypes.BOUNDARY_EVENT //
        }));

        // Add optional Extension Hints...
        addExtensionHints(taskType, containable);

        shapeTypeHint.setContainableElementTypeIds(containable);
        return shapeTypeHint;
    }

    private ShapeTypeHint createGatewayHint(final String gatewayType) {
        ShapeTypeHint hint = new ShapeTypeHint(gatewayType, true, true, false, true);
        return hint;
    }

    /**
     * A BPMN Label is moveable but not deleteabel or resizeable
     *
     * @return
     */
    private ShapeTypeHint createBPMNLabelHint() {
        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.BPMNLABEL, true, false, false, true);

        return shapeTypeHint;
    }

    /**
     * Creates a StartEvent ShapeTypeHint
     * <p>
     * The method defines the containable Event Definitions
     *
     * @return
     */
    private ShapeTypeHint createStartEventHint() {
        List<String> containables = new ArrayList<>();

        // add Event Definitions
        containables.addAll(Arrays.asList(new String[] { //
                BPMNTypes.EVENT_DEFINITION_COMPENSATION, //
                BPMNTypes.EVENT_DEFINITION_CONDITIONAL, //
                BPMNTypes.EVENT_DEFINITION_ERROR, //
                BPMNTypes.EVENT_DEFINITION_ESCALATION, //
                BPMNTypes.EVENT_DEFINITION_MESSAGE, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL, //
                BPMNTypes.EVENT_DEFINITION_TIMER //
        }));

        // Add optional Extension Hints...
        addExtensionHints(BPMNTypes.START_EVENT, containables);

        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.START_EVENT, true, true, false, true);

        shapeTypeHint.setContainableElementTypeIds(containables);
        return shapeTypeHint;
    }

    /**
     * Creates a CatchEvent ShapeTypeHint
     * <p>
     * The method defines the containable Event Definitions
     *
     * @return
     */
    private ShapeTypeHint createCatchEventHint() {
        List<String> containables = new ArrayList<>();
        // add Event Definitions
        containables.addAll(Arrays.asList(new String[] { //
                BPMNTypes.EVENT_DEFINITION_CANCEL, //
                BPMNTypes.EVENT_DEFINITION_COMPENSATION, //
                BPMNTypes.EVENT_DEFINITION_CONDITIONAL, //
                BPMNTypes.EVENT_DEFINITION_ERROR, //
                BPMNTypes.EVENT_DEFINITION_ESCALATION, //
                BPMNTypes.EVENT_DEFINITION_LINK, //
                BPMNTypes.EVENT_DEFINITION_MESSAGE, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL, //
                BPMNTypes.EVENT_DEFINITION_TIMER //
        }));

        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.CATCH_EVENT, true, true, false, true);

        // Add optional Extension Hints...
        addExtensionHints(BPMNTypes.CATCH_EVENT, containables);

        shapeTypeHint.setContainableElementTypeIds(containables);
        return shapeTypeHint;
    }

    /**
     * Creates a CatchEvent ShapeTypeHint
     * <p>
     * The method defines the containable Event Definitions
     *
     * @return
     */
    private ShapeTypeHint createThrowEventHint() {
        List<String> containables = new ArrayList<>();
        // add Event Definitions
        containables.addAll(Arrays.asList(new String[] { //
                BPMNTypes.EVENT_DEFINITION_COMPENSATION, //
                BPMNTypes.EVENT_DEFINITION_ESCALATION, //
                BPMNTypes.EVENT_DEFINITION_LINK, //
                BPMNTypes.EVENT_DEFINITION_MESSAGE, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL //

        }));

        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.THROW_EVENT, true, true, false, true);

        // Add optional Extension Hints...
        addExtensionHints(BPMNTypes.THROW_EVENT, containables);
        shapeTypeHint.setContainableElementTypeIds(containables);
        return shapeTypeHint;
    }

    /**
     * Creates a EndEvent ShapeTypeHint
     * <p>
     * The method defines the containable Event Definitions
     *
     * @return
     */
    private ShapeTypeHint createEndEventHint() {
        List<String> containables = Arrays.asList(new String[] { //
                BPMNTypes.EVENT_DEFINITION_CANCEL, //
                BPMNTypes.EVENT_DEFINITION_COMPENSATION, //
                BPMNTypes.EVENT_DEFINITION_ERROR, //
                BPMNTypes.EVENT_DEFINITION_ESCALATION, //
                BPMNTypes.EVENT_DEFINITION_MESSAGE, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL, //
                BPMNTypes.EVENT_DEFINITION_TERMINATE });
        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.END_EVENT, true, true, false, true);

        // Add optional Extension Hints...
        addExtensionHints(BPMNTypes.END_EVENT, containables);

        shapeTypeHint.setContainableElementTypeIds(containables);
        return shapeTypeHint;
    }

    /**
     * Creates a BoundaryEvent ShapeTypeHint
     * <p>
     * The method defines the containable Event Definitions
     *
     * @return
     */
    private ShapeTypeHint createBoundaryEventHint() {
        List<String> containables = new ArrayList<>();
        // add Event Definitions
        containables.addAll(Arrays.asList(new String[] { //
                BPMNTypes.EVENT_DEFINITION_CONDITIONAL, //
                BPMNTypes.EVENT_DEFINITION_COMPENSATION, //
                BPMNTypes.EVENT_DEFINITION_MESSAGE, //
                BPMNTypes.EVENT_DEFINITION_TIMER, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL //
        }));

        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.BOUNDARY_EVENT, true, true, false, true);

        // Add optional Extension Hints...
        addExtensionHints(BPMNTypes.BOUNDARY_EVENT, containables);
        shapeTypeHint.setContainableElementTypeIds(containables);
        return shapeTypeHint;
    }

    /**
     * This helper method tests if a extension is supported and adds the
     * corresponding extension type.
     *
     * @param bpmnType
     * @param containables
     */
    private void addExtensionHints(final String bpmnType, final List<String> containables) {
        // Add optional Extension Hints...
        if (extensions != null) {
            for (BPMNElementExtension extension : extensions) {
                String extensionType = "extension:" + extension.getNamespace();
                // validate if the extension is no Default Extension kind and if the extension
                // can handle this task Type
                if (!BPMNNS.BPMN2.name().equals(extension.getNamespace()) //
                        && !containables.contains(extensionType) //
                        && extension.handlesElementTypeId(bpmnType)) {
                    containables.add(extensionType);
                }
            }
        }

    }

    /**
     * Set the BPMN default shape hints.
     * <ul>
     * <li>repositionable=true
     * <li>deletable = true
     * <li>resizable = false
     * <li>reparentable = true
     * <p>
     */
    @Override
    public ShapeTypeHint createDefaultShapeTypeHint(final String elementId) {
        // Override the default-default
        return new ShapeTypeHint(elementId, true, true, false, true);
    }

    /**
     * <ul>
     * <li>repositionable=true
     * <li>deletable = true
     * <li>routeable = true
     */
    @Override
    public EdgeTypeHint createDefaultEdgeTypeHint(final String elementId) {
        EdgeTypeHint hint = super.createDefaultEdgeTypeHint(elementId);

        // TODO can we really define a default source/target here ?
        return hint;
    }

    /**
     * Returns the supported layout kind for this diagram implementation.
     *
     * @return The layout kind.
     */
    @Override
    public ServerLayoutKind getLayoutKind() {
        return ServerLayoutKind.MANUAL;
    }

    @Override
    public boolean animatedUpdate() {
        return false;
    }

}
