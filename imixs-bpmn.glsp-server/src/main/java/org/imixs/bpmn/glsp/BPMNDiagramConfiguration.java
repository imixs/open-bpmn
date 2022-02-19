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

import static org.eclipse.glsp.graph.DefaultTypes.EDGE;

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
import org.imixs.bpmn.bpmngraph.BpmngraphPackage;
import org.imixs.bpmn.glsp.utils.ModelTypes;

/**
 * Provides configuration constants for a specific diagram implementation (i.e. diagram language),
 * The corresponding configuration for a diagram implementation is identified via its diagram type.
 */
public class BPMNDiagramConfiguration extends BaseDiagramConfiguration {

   public final static List<String> ALL_BPMN_ACTIVITIES = Arrays.asList(ModelTypes.MANUAL_TASK, ModelTypes.SCRIPT_TASK,
      ModelTypes.SEND_TASK,
      ModelTypes.SERVICE_TASK, ModelTypes.EXCLUSIVE_GATEWAY, ModelTypes.INCLUSIVE_GATEWAY, ModelTypes.END_EVENT,
      ModelTypes.START_EVENT, ModelTypes.EXCLUSIVE_GATEWAY);

   /**
    * Returns the type mappings for the diagram implementation. Type mappings are used by GSON to construct the correct
    * {@link EClass} based on the "type" property of the JSON object.
    *
    * @return A complete map of all type mappings for the diagram implementation.
    */
   @Override
   public Map<String, EClass> getTypeMappings() {

      Map<String, EClass> mappings = DefaultTypes.getDefaultTypeMappings();
      mappings.put(ModelTypes.LABEL_HEADING, GraphPackage.Literals.GLABEL);
      mappings.put(ModelTypes.LABEL_TEXT, GraphPackage.Literals.GLABEL);
      mappings.put(ModelTypes.COMP_HEADER, GraphPackage.Literals.GCOMPARTMENT);
      mappings.put(ModelTypes.LABEL_ICON, GraphPackage.Literals.GLABEL);
      mappings.put(ModelTypes.SEQUENCE_FLOW, GraphPackage.Literals.GEDGE);
      mappings.put(ModelTypes.ICON, BpmngraphPackage.Literals.ICON);
      mappings.put(ModelTypes.ACTIVITY_NODE, BpmngraphPackage.Literals.ACTIVITY_NODE);

      mappings.put(ModelTypes.GATEWAY, BpmngraphPackage.Literals.GATEWAY_NODE);

      mappings.put(ModelTypes.TASK, BpmngraphPackage.Literals.TASK_NODE);
      mappings.put(ModelTypes.EVENT, BpmngraphPackage.Literals.EVENT_NODE);
      mappings.put(ModelTypes.POOL, BpmngraphPackage.Literals.POOL);
      mappings.put(ModelTypes.STRUCTURE, GraphPackage.Literals.GCOMPARTMENT);
      return mappings;

   }

   /**
    * Returns the diagram type of the diagram implementation that corresponds to this configuration.
    *
    * @return The diagram type.
    */
   @Override
   public String getDiagramType() { return "BPMN 2.0"; }

   /**
    * Returns the shape type hints for the diagram implementation. Shape type hints are sent to the client and used to
    * validate whether certain operations for shapes/nodes are allowed without having to query the server again.
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

      ShapeTypeHint catHint = new ShapeTypeHint(ModelTypes.POOL, true, true, true, true);
      catHint.setContainableElementTypeIds(ALL_BPMN_ACTIVITIES);
      nodeHints.add(catHint);

      nodeHints.add(new ShapeTypeHint(ModelTypes.EXCLUSIVE_GATEWAY, true, true, false, true));
      nodeHints.add(new ShapeTypeHint(ModelTypes.INCLUSIVE_GATEWAY, true, true, false, true));

      return nodeHints;
   }

   /**
    * Set the default shape hints.
    */
   @Override
   public ShapeTypeHint createDefaultShapeTypeHint(final String elementId) {
      // Override the default-default
      return new ShapeTypeHint(elementId, true, true, false, true);
   }

   /**
    * Returns the edge type hints for the diagram implementation. Edge type hints are sent to the client and used to
    * validate whether certain operations for edges are allowed without having to query the server again.
    *
    * @return List of all edge type hints for the diagram implementation.
    */
   @Override
   public List<EdgeTypeHint> getEdgeTypeHints() {
      List<EdgeTypeHint> edgeHints = new ArrayList<>();
      edgeHints.add(createDefaultEdgeTypeHint(EDGE));
      EdgeTypeHint sequenceFlowHint = super.createDefaultEdgeTypeHint(ModelTypes.SEQUENCE_FLOW);

      // allow all elements
      sequenceFlowHint.setSourceElementTypeIds(ALL_BPMN_ACTIVITIES);
      sequenceFlowHint.setTargetElementTypeIds(ALL_BPMN_ACTIVITIES);
      edgeHints.add(sequenceFlowHint);
      return edgeHints;
   }

   @Override
   public EdgeTypeHint createDefaultEdgeTypeHint(final String elementId) {
      EdgeTypeHint hint = super.createDefaultEdgeTypeHint(elementId);

      // allow all
      hint.setSourceElementTypeIds(ALL_BPMN_ACTIVITIES);
      hint.setTargetElementTypeIds(ALL_BPMN_ACTIVITIES);
      return hint;
   }

   /**
    * Returns the supported layout kind for this diagram implementation.
    *
    * @return The layout kind.
    */
   @Override
   public ServerLayoutKind getLayoutKind() { return ServerLayoutKind.MANUAL; }

   /**
    * Boolean flag to specific whether the diagram implementation expects the client to provide layout information for
    * certain diagram element. Default is 'true'.
    *
    * @return Boolean flag to indicate whether the client needs to be involved in the layout process.
    */
   @Override
   public boolean needsClientLayout() {
      return true;
   }

}
