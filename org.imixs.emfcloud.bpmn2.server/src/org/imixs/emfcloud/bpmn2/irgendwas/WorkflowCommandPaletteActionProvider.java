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
package org.imixs.emfcloud.bpmn2.irgendwas;

import static org.eclipse.glsp.graph.DefaultTypes.EDGE;
import static org.eclipse.glsp.graph.util.GraphUtil.point;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GModelIndex;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.features.commandpalette.CommandPaletteActionProvider;
import org.eclipse.glsp.server.features.directediting.LabeledAction;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.operations.DeleteOperation;
import org.eclipse.glsp.server.types.EditorContext;
import org.imixs.emfcloud.bpmn2.handler.GridSnapper;
import org.imixs.emfcloud.bpmn2.model.bpmn2.Task;
import org.imixs.emfcloud.bpmn2.util.ModelTypes;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;

public class WorkflowCommandPaletteActionProvider implements CommandPaletteActionProvider {

   @Inject
   protected GModelState modelState;

   @Override
   public List<LabeledAction> getActions(final EditorContext editorContext) {
      List<LabeledAction> actions = Lists.newArrayList();
      if (modelState.isReadonly()) {
         return actions;
      }
      GModelIndex index = modelState.getIndex();
      List<String> selectedIds = editorContext.getSelectedElementIds();
      Optional<GPoint> lastMousePosition = GridSnapper.snap(editorContext.getLastMousePosition());
      Set<GModelElement> selectedElements = index.getAll(selectedIds);

      // Create node actions are always possible
      actions.addAll(Sets.newHashSet(
         new LabeledAction("Create Task", Lists.newArrayList(
            new CreateNodeOperation(ModelTypes.TASK,
               lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),
         new LabeledAction("Create Pool", Lists.newArrayList(new CreateNodeOperation(
            ModelTypes.POOL, lastMousePosition.orElse(point(0, 0)), "fa-plus-square")))));

      // Create edge actions between two nodes
      if (selectedElements.size() == 1) {
         GModelElement element = selectedElements.iterator().next();
         if (element instanceof GNode) {
            actions.addAll(createEdgeActions((GNode) element, index.getAllByClass(Task.class)));
         }
      } else if (selectedElements.size() == 2) {
         Iterator<GModelElement> iterator = selectedElements.iterator();
         GModelElement firstElement = iterator.next();
         GModelElement secondElement = iterator.next();
         if (firstElement instanceof Task && secondElement instanceof Task) {
            GNode firstNode = (GNode) firstElement;
            GNode secondNode = (GNode) secondElement;
            actions.add(createEdgeAction("Connect with Edge", firstNode, secondNode));
            actions.add(createWeightedEdgeAction("Connect with Sequence Flow", firstNode, secondNode));
         }
      }

      // Delete action
      if (selectedElements.size() == 1) {
         actions
            .add(new LabeledAction("Delete", Lists.newArrayList(new DeleteOperation(selectedIds)), "fa-minus-square"));
      } else if (selectedElements.size() > 1) {
         actions.add(
            new LabeledAction("Delete All", Lists.newArrayList(new DeleteOperation(selectedIds)), "fa-minus-square"));
      }

      return actions;
   }

   private Set<LabeledAction> createEdgeActions(final GNode source, final Set<? extends GNode> targets) {
      Set<LabeledAction> actions = Sets.newLinkedHashSet();
      // add first all edge, then all weighted edge actions to keep a nice order
      targets.forEach(node -> actions.add(createEdgeAction("Create Edge to " + getLabel(node), source, node)));
      targets.forEach(node -> actions
         .add(createWeightedEdgeAction("Create Weighted Edge to " + getLabel(node), source, node)));
      return actions;
   }

   private LabeledAction createWeightedEdgeAction(final String label, final GNode source, final GNode node) {
      return new LabeledAction(label, Lists.newArrayList(
         new CreateEdgeOperation(ModelTypes.SEQUENCE_FLOW, source.getId(), node.getId())), "fa-plus-square");
   }

   private LabeledAction createEdgeAction(final String label, final GNode source, final GNode node) {
      return new LabeledAction(label, Lists.newArrayList(
         new CreateEdgeOperation(EDGE, source.getId(), node.getId())), "fa-plus-square");
   }

   private String getLabel(final GNode node) {
      if (node instanceof Task) {
         return ((Task) node).getName();
      }
      return node.getId();
   }

}
