/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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

public class BPMNDiagramConfiguration extends BaseDiagramConfiguration {

   @Override
   public Map<String, EClass> getTypeMappings() {
      Map<String, EClass> mappings = DefaultTypes.getDefaultTypeMappings();
      mappings.put(ModelTypes.LABEL_HEADING, GraphPackage.Literals.GLABEL);
      // mappings.put(ModelTypes.LABEL_TEXT, GraphPackage.Literals.GLABEL);
      // mappings.put(ModelTypes.COMP_HEADER, GraphPackage.Literals.GCOMPARTMENT);
      // mappings.put(ModelTypes.LABEL_ICON, GraphPackage.Literals.GLABEL);
      mappings.put(ModelTypes.WEIGHTED_EDGE, GraphPackage.Literals.GEDGE);
      mappings.put(ModelTypes.ICON, BpmngraphPackage.Literals.ICON);
      mappings.put(ModelTypes.ACTIVITY_NODE, BpmngraphPackage.Literals.ACTIVITY_NODE);
      mappings.put(ModelTypes.TASK, BpmngraphPackage.Literals.TASK_NODE);
      // mappings.put(CATEGORY, WfgraphPackage.Literals.CATEGORY);
      // mappings.put(STRUCTURE, GraphPackage.Literals.GCOMPARTMENT);
      return mappings;
   }

   @Override
   public List<ShapeTypeHint> getShapeTypeHints() {
      List<ShapeTypeHint> nodeHints = new ArrayList<>();
      nodeHints.add(new ShapeTypeHint(ModelTypes.MANUAL_TASK, true, true, true, true));
      nodeHints.add(new ShapeTypeHint(ModelTypes.SCRIPT_TASK, true, true, true, true));
      nodeHints.add(new ShapeTypeHint(ModelTypes.SEND_TASK, true, true, true, true));
      nodeHints.add(new ShapeTypeHint(ModelTypes.SERVICE_TASK, true, true, true, true));
      // ShapeTypeHint catHint = new ShapeTypeHint(CATEGORY, true, true, true, true);
      // catHint.setContainableElementTypeIds(
      // Arrays.asList(DECISION_NODE, MERGE_NODE, FORK_NODE, JOIN_NODE, AUTOMATED_TASK, MANUAL_TASK, CATEGORY));
      // nodeHints.add(catHint);
      // nodeHints.add(createDefaultShapeTypeHint(FORK_NODE));
      // nodeHints.add(createDefaultShapeTypeHint(JOIN_NODE));
      // nodeHints.add(createDefaultShapeTypeHint(DECISION_NODE));
      // nodeHints.add(createDefaultShapeTypeHint(MERGE_NODE));
      return nodeHints;
   }

   @Override
   public ShapeTypeHint createDefaultShapeTypeHint(final String elementId) {
      // Override the default-default: for the Workflow example, we want all nodes
      // to be reparentable
      return new ShapeTypeHint(elementId, true, true, true, true);
   }

   @Override
   public List<EdgeTypeHint> getEdgeTypeHints() {
      List<EdgeTypeHint> edgeHints = new ArrayList<>();
      edgeHints.add(createDefaultEdgeTypeHint(EDGE));
      EdgeTypeHint weightedEdgeHint = super.createDefaultEdgeTypeHint(ModelTypes.WEIGHTED_EDGE);
      // weightedEdgeHint.setSourceElementTypeIds(Arrays.asList(DECISION_NODE));
      weightedEdgeHint
         .setTargetElementTypeIds(Arrays.asList(ModelTypes.MANUAL_TASK, ModelTypes.SEND_TASK, ModelTypes.SCRIPT_TASK));
      edgeHints.add(weightedEdgeHint);
      return edgeHints;
   }

   @Override
   public EdgeTypeHint createDefaultEdgeTypeHint(final String elementId) {
      EdgeTypeHint hint = super.createDefaultEdgeTypeHint(elementId);
      hint.setSourceElementTypeIds(
         Arrays.asList(ModelTypes.MANUAL_TASK, ModelTypes.SCRIPT_TASK, ModelTypes.SEND_TASK, ModelTypes.SERVICE_TASK));
      hint.setTargetElementTypeIds(
         Arrays.asList(ModelTypes.MANUAL_TASK, ModelTypes.SCRIPT_TASK, ModelTypes.SEND_TASK, ModelTypes.SERVICE_TASK));
      return hint;
   }

   @Override
   public ServerLayoutKind getLayoutKind() { return ServerLayoutKind.MANUAL; }

   @Override
   public boolean needsClientLayout() {
      return true;
   }

}
