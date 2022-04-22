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
package org.imixs.bpmn.glsp;

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
import org.imixs.bpmn.glsp.utils.ModelTypes;
import org.imixs.bpmn2.Bpmn2Package;

/**
 * Provides configuration constants for a specific diagram implementation (i.e.
 * diagram language), The corresponding configuration for a diagram
 * implementation is identified via its diagram type.
 */
public class BPMNDiagramConfiguration extends BaseDiagramConfiguration {

    public final static List<String> ALL_BPMN_FLOWELEMENTS = Arrays.asList(ModelTypes.MANUAL_TASK,
            ModelTypes.SCRIPT_TASK, ModelTypes.SEND_TASK, ModelTypes.SERVICE_TASK, ModelTypes.EXCLUSIVE_GATEWAY,
            ModelTypes.INCLUSIVE_GATEWAY, ModelTypes.START_EVENT, ModelTypes.END_EVENT, ModelTypes.CATCH_EVENT,
            ModelTypes.THROW_EVENT, ModelTypes.SEQUENCE_FLOW);

    public final static List<String> ALL_PORTS = Arrays.asList(ModelTypes.MANUAL_TASK, ModelTypes.SCRIPT_TASK,
            ModelTypes.START_EVENT, ModelTypes.END_EVENT, ModelTypes.SEND_TASK, ModelTypes.SERVICE_TASK,
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
        mappings.put(ModelTypes.LABEL_HEADING, GraphPackage.Literals.GLABEL);
        mappings.put(ModelTypes.COMP_HEADER, GraphPackage.Literals.GCOMPARTMENT);
        mappings.put(ModelTypes.ICON, Bpmn2Package.Literals.ICON);

        mappings.put(ModelTypes.TASK, Bpmn2Package.Literals.TASK);

        mappings.put(ModelTypes.GATEWAY, Bpmn2Package.Literals.GATEWAY);
        mappings.put(ModelTypes.GATEWAY_PORT, GraphPackage.Literals.GPORT);

        mappings.put(ModelTypes.CATCH_EVENT, Bpmn2Package.Literals.CATCH_EVENT);
        mappings.put(ModelTypes.THROW_EVENT, Bpmn2Package.Literals.THROW_EVENT);
        mappings.put(ModelTypes.START_EVENT, Bpmn2Package.Literals.START_EVENT);
        mappings.put(ModelTypes.END_EVENT, Bpmn2Package.Literals.END_EVENT);
        mappings.put(ModelTypes.EVENT_PORT, GraphPackage.Literals.GPORT);

        mappings.put(ModelTypes.POOL, Bpmn2Package.Literals.POOL);
        mappings.put(ModelTypes.STRUCTURE, GraphPackage.Literals.GCOMPARTMENT);

        mappings.put(ModelTypes.SEQUENCE_FLOW, GraphPackage.Literals.GEDGE);
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
        nodeHints.add(new ShapeTypeHint(ModelTypes.MANUAL_TASK, true, true, true, true));
        nodeHints.add(new ShapeTypeHint(ModelTypes.SCRIPT_TASK, true, true, true, true));
        nodeHints.add(new ShapeTypeHint(ModelTypes.SEND_TASK, true, true, true, true));
        nodeHints.add(new ShapeTypeHint(ModelTypes.SERVICE_TASK, true, true, true, true));

        nodeHints.add(new ShapeTypeHint(ModelTypes.START_EVENT, true, true, false, true));
        nodeHints.add(new ShapeTypeHint(ModelTypes.END_EVENT, true, true, false, true));
        nodeHints.add(new ShapeTypeHint(ModelTypes.CATCH_EVENT, true, true, false, true));
        nodeHints.add(new ShapeTypeHint(ModelTypes.THROW_EVENT, true, true, false, true));

        nodeHints.add(new ShapeTypeHint(ModelTypes.EXCLUSIVE_GATEWAY, true, true, false, true));
        nodeHints.add(new ShapeTypeHint(ModelTypes.INCLUSIVE_GATEWAY, true, true, false, true));

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
        EdgeTypeHint sequenceFlowHint = createDefaultEdgeTypeHint(ModelTypes.SEQUENCE_FLOW);

        sequenceFlowHint.setSourceElementTypeIds(Arrays.asList(//
                ModelTypes.MANUAL_TASK, //
                ModelTypes.SCRIPT_TASK, //
                ModelTypes.SEND_TASK, //
                ModelTypes.SERVICE_TASK, //
                ModelTypes.EVENT_PORT));

        sequenceFlowHint.setTargetElementTypeIds(Arrays.asList(//
                ModelTypes.MANUAL_TASK, //
                ModelTypes.SCRIPT_TASK, //
                ModelTypes.SEND_TASK, //
                ModelTypes.SERVICE_TASK, //
                ModelTypes.EVENT_PORT));

        edgeHints.add(sequenceFlowHint);

        // TODO add other BPMN Flows

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
