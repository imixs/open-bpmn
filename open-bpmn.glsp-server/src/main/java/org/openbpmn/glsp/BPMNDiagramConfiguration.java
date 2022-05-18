/********************************************************************************
 * Copyright (c) 2020-2022 EclipseSource and others.
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
package org.openbpmn.glsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GraphPackage;
import org.eclipse.glsp.server.diagram.BaseDiagramConfiguration;
import org.eclipse.glsp.server.layout.ServerLayoutKind;
import org.eclipse.glsp.server.types.EdgeTypeHint;
import org.eclipse.glsp.server.types.ShapeTypeHint;
import org.openbpmn.bpmn.BPMNEventType;
import org.openbpmn.bpmn.BPMNGatewayType;
import org.openbpmn.bpmn.BPMNSequenceFlowType;
import org.openbpmn.bpmn.BPMNTaskType;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.utils.ModelTypes;

/**
 * Provides configuration constants for a specific diagram implementation (i.e.
 * diagram language), The corresponding configuration for a diagram
 * implementation is identified via its diagram type.
 */
public class BPMNDiagramConfiguration extends BaseDiagramConfiguration {

    public final static List<String> ALL_BPMN_FLOWELEMENTS = Arrays.asList(//
            BPMNTaskType.MANUAL, //
            BPMNTaskType.SCRIPT, //
            BPMNTaskType.SEND, //
            BPMNTaskType.SERVICE, //
            BPMNGatewayType.EXCLUSIVE, //
            BPMNGatewayType.INCLUSIVE, //
            BPMNEventType.START, //
            BPMNEventType.END, //
            BPMNEventType.CATCH, //
            BPMNEventType.THROW, //
            BPMNSequenceFlowType.SEQUENCE_FLOW);

    public final static List<String> ALL_PORTS = Arrays.asList( //
            BPMNTaskType.MANUAL, //
            BPMNTaskType.SCRIPT, //
            BPMNEventType.START, //
            BPMNEventType.END, //
            BPMNTaskType.SEND, //
            BPMNTaskType.TASK, //
            BPMNTaskType.SERVICE, //
            ModelTypes.EVENT_PORT);

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
        mappings.put(ModelTypes.COMP_HEADER, GraphPackage.Literals.GCOMPARTMENT);
        mappings.put(ModelTypes.ICON, BpmnPackage.Literals.ICON);
        mappings.put(ModelTypes.EVENT_PORT, GraphPackage.Literals.GPORT);
        mappings.put(ModelTypes.POOL, BpmnPackage.Literals.POOL);
        mappings.put(ModelTypes.STRUCTURE, GraphPackage.Literals.GCOMPARTMENT);

        // BPMN Types
        mappings.put(BPMNTaskType.TASK, BpmnPackage.Literals.TASK_NODE);
        mappings.put(BPMNGatewayType.GATEWAY, BpmnPackage.Literals.GATEWAY_NODE);
        mappings.put(BPMNEventType.EVENT, BpmnPackage.Literals.EVENT_NODE);
        mappings.put(BPMNSequenceFlowType.SEQUENCE_FLOW, BpmnPackage.Literals.SEQUENCE_FLOW);

        return mappings;
    }

    /**
     * Returns the diagram type of the diagram implementation that corresponds to
     * this configuration.
     *
     * @return The diagram type.
     */
    @Override
    public String getDiagramType() {
        return "BPMN 2.0";
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
        nodeHints.add(new ShapeTypeHint(DefaultTypes.NODE, true, true, true, false));
        nodeHints.add(new ShapeTypeHint(BPMNTaskType.MANUAL, true, true, true, true));
        nodeHints.add(new ShapeTypeHint(BPMNTaskType.SCRIPT, true, true, true, true));
        nodeHints.add(new ShapeTypeHint(BPMNTaskType.SEND, true, true, true, true));
        nodeHints.add(new ShapeTypeHint(BPMNTaskType.SERVICE, true, true, true, true));

        nodeHints.add(new ShapeTypeHint(BPMNEventType.START, true, true, false, true));
        nodeHints.add(new ShapeTypeHint(BPMNEventType.END, true, true, false, true));
        nodeHints.add(new ShapeTypeHint(BPMNEventType.CATCH, true, true, false, true));
        nodeHints.add(new ShapeTypeHint(BPMNEventType.THROW, true, true, false, true));

        nodeHints.add(new ShapeTypeHint(BPMNGatewayType.EXCLUSIVE, true, true, false, true));
        nodeHints.add(new ShapeTypeHint(BPMNGatewayType.INCLUSIVE, true, true, false, true));

        ShapeTypeHint catHint = new ShapeTypeHint(ModelTypes.POOL, true, true, true, true);

        catHint.setContainableElementTypeIds(ALL_BPMN_FLOWELEMENTS);
        nodeHints.add(catHint);

        return nodeHints;
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
     * Returns the edge type hints for the diagram implementation. Edge type hints
     * are sent to the client and used to validate whether certain operations for
     * edges are allowed without having to query the server again.
     * <p>
     * TODO We need to define a EdgeTypeHint for all typs of flows in BPMN.
     * Currently we only support the SequenceFlow
     *
     * @return List of all edge type hints for the diagram implementation.
     */
    @Override
    public List<EdgeTypeHint> getEdgeTypeHints() {
        List<EdgeTypeHint> edgeHints = new ArrayList<>();

        // SequenceFLow
        EdgeTypeHint sequenceFlowHint = createDefaultEdgeTypeHint(BPMNSequenceFlowType.SEQUENCE_FLOW);

        sequenceFlowHint.setSourceElementTypeIds(Arrays.asList(//
                BPMNTaskType.MANUAL, //
                BPMNTaskType.SCRIPT, //
                BPMNTaskType.SEND, //
                BPMNTaskType.SERVICE, //
                ModelTypes.EVENT_PORT));

        sequenceFlowHint.setTargetElementTypeIds(Arrays.asList(//
                BPMNTaskType.MANUAL, //
                BPMNTaskType.SCRIPT, //
                BPMNTaskType.SEND, //
                BPMNTaskType.SERVICE, //
                ModelTypes.EVENT_PORT));

        edgeHints.add(sequenceFlowHint);

        return edgeHints;
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

    /**
     * Boolean flag to specific whether the diagram implementation expects the
     * client to provide layout information for certain diagram element. Default is
     * 'true'.
     *
     * @return Boolean flag to indicate whether the client needs to be involved in
     *         the layout process.
     */
    @Override
    public boolean needsClientLayout() {
        return true;
    }

}
