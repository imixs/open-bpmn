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
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.extension.BPMNExtension;
import org.openbpmn.glsp.bpmn.BpmnPackage;
import org.openbpmn.glsp.utils.ModelTypes;

import com.google.inject.Inject;

/**
 * Provides configuration constants for a specific diagram implementation (i.e.
 * diagram language), The corresponding configuration for a diagram
 * implementation is identified via its diagram type.
 */
public class BPMNDiagramConfiguration extends BaseDiagramConfiguration {
    @Inject
    protected Set<BPMNExtension> extensions;

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
        // mappings.put(ModelTypes.EVENT_PORT, GraphPackage.Literals.GPORT);
        mappings.put(ModelTypes.POOL, BpmnPackage.Literals.POOL);
        mappings.put(ModelTypes.STRUCTURE, GraphPackage.Literals.GCOMPARTMENT);

        // BPMN Types
        mappings.put(BPMNTypes.TASK, BpmnPackage.Literals.TASK_NODE);
        mappings.put(BPMNTypes.GATEWAY, BpmnPackage.Literals.GATEWAY_NODE);
        mappings.put(BPMNTypes.EVENT, BpmnPackage.Literals.EVENT_NODE);
        mappings.put(BPMNTypes.SEQUENCE_FLOW, BpmnPackage.Literals.SEQUENCE_FLOW);

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

        // Task ShapeTypeHints
        nodeHints.add(createTaskHint(BPMNTypes.TASK));
        nodeHints.add(createTaskHint(BPMNTypes.MANUAL_TASK));
        nodeHints.add(createTaskHint(BPMNTypes.SCRIPT_TASK));
        nodeHints.add(createTaskHint(BPMNTypes.SEND_TASK));
        nodeHints.add(createTaskHint(BPMNTypes.SERVICE_TASK));

        // Event ShapeTypeHints
        nodeHints.add(createStartEventHint());
        nodeHints.add(createEndEventHint());
        nodeHints.add(createCatchEventHint());
        nodeHints.add(createThrowEventHint());

        // Gateway ShapeEventTypes
        nodeHints.add(new ShapeTypeHint(BPMNTypes.EXCLUSIVE_GATEWAY, true, true, false, true));
        nodeHints.add(new ShapeTypeHint(BPMNTypes.INCLUSIVE_GATEWAY, true, true, false, true));

        // Add Pool
        ShapeTypeHint catHint = new ShapeTypeHint(ModelTypes.POOL, true, true, true, true);
        catHint.setContainableElementTypeIds(BPMNModel.BPMN_FLOWELEMENTS);
        nodeHints.add(catHint);

        return nodeHints;
    }

    /**
     * Creates a Task ShapeTypeHint
     * <p>
     * The method defines the containable Extensions
     *
     * @return
     */
    private ShapeTypeHint createTaskHint(final String taskType) {
        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(taskType, true, true, true, true);

        List<String> containables = new ArrayList<>();
        // Add optional Extension Hints...
        addExtensionHints(taskType, containables);

        shapeTypeHint.setContainableElementTypeIds(containables);
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
                BPMNTypes.EVENT_DEFINITION_CONDITIONAL, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL, //
                BPMNTypes.EVENT_DEFINITION_ERROR, //
                BPMNTypes.EVENT_DEFINITION_TIMER, //
                BPMNTypes.EVENT_DEFINITION_MESSAGE//
        }));

        // Add optional Extension Hints...
        addExtensionHints(BPMNTypes.START_EVENT, containables);

        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.START_EVENT, true, true, false, true);

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
                BPMNTypes.EVENT_DEFINITION_COMPENSATION, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL, //
                BPMNTypes.EVENT_DEFINITION_ERROR, //
                BPMNTypes.EVENT_DEFINITION_TIMER, //
                BPMNTypes.EVENT_DEFINITION_ESCALATION, //
                BPMNTypes.EVENT_DEFINITION_CANCEL, //
                BPMNTypes.EVENT_DEFINITION_MESSAGE, //
                BPMNTypes.EVENT_DEFINITION_TERMINATE });
        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.END_EVENT, true, true, false, true);

        // Add optional Extension Hints...
        addExtensionHints(BPMNTypes.END_EVENT, containables);

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
                BPMNTypes.EVENT_DEFINITION_CONDITIONAL, //
                BPMNTypes.EVENT_DEFINITION_LINK, //
                BPMNTypes.EVENT_DEFINITION_MESSAGE, //
                BPMNTypes.EVENT_DEFINITION_TIMER, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL //
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
                BPMNTypes.EVENT_DEFINITION_LINK, //
                BPMNTypes.EVENT_DEFINITION_SIGNAL //
        }));

        ShapeTypeHint shapeTypeHint = new ShapeTypeHint(BPMNTypes.THROW_EVENT, true, true, false, true);

        // Add optional Extension Hints...
        addExtensionHints(BPMNTypes.THROW_EVENT, containables);
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
            for (BPMNExtension extension : extensions) {
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
     * Returns the edge type hints for the diagram implementation. Edge type hints
     * are sent to the client and used to validate whether certain operations for
     * edges are allowed without having to query the server again.
     * <p>
     * TODO We need to define a EdgeTypeHint for all types of flows in BPMN.
     * Currently we only support the SequenceFlow
     *
     * @return List of all edge type hints for the diagram implementation.
     */
    @Override
    public List<EdgeTypeHint> getEdgeTypeHints() {
        List<EdgeTypeHint> edgeHints = new ArrayList<>();

        // SequenceFLow
        EdgeTypeHint sequenceFlowHint = createDefaultEdgeTypeHint(BPMNTypes.SEQUENCE_FLOW);
        sequenceFlowHint.setSourceElementTypeIds(BPMNModel.BPMN_FLOWELEMENTS);
        sequenceFlowHint.setTargetElementTypeIds(BPMNModel.BPMN_FLOWELEMENTS);
        edgeHints.add(sequenceFlowHint);

        return edgeHints;
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
