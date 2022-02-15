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
package org.imixs.bpmn.glsp.provider;

import static org.eclipse.glsp.graph.util.GraphUtil.point;

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
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.types.EditorContext;
import org.imixs.bpmn.bpmngraph.TaskNode;
import org.imixs.bpmn.glsp.handler.GridSnapper;
import org.imixs.bpmn.glsp.utils.ModelTypes;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;

public class BPMNCommandPaletteActionProvider implements CommandPaletteActionProvider {

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
         new LabeledAction("Create Manual Task",
            Lists.newArrayList(new CreateNodeOperation(ModelTypes.MANUAL_TASK, lastMousePosition.orElse(point(0, 0)),
               "fa-plus-square"))),
         new LabeledAction("Create User Task",
            Lists.newArrayList(new CreateNodeOperation(ModelTypes.USER_TASK, lastMousePosition.orElse(point(0, 0)),
               "fa-plus-square"))),
         new LabeledAction("Create Send Task",
            Lists.newArrayList(new CreateNodeOperation(ModelTypes.SEND_TASK, lastMousePosition.orElse(point(0, 0)),
               "fa-plus-square"))),
         new LabeledAction("Create Service Task",
            Lists.newArrayList(new CreateNodeOperation(ModelTypes.SERVICE_TASK, lastMousePosition.orElse(point(0, 0)),
               "fa-plus-square"))),
         new LabeledAction("Create Script Task",
            Lists.newArrayList(new CreateNodeOperation(ModelTypes.SCRIPT_TASK, lastMousePosition.orElse(point(0, 0)),
               "fa-plus-square")))

      // add , separated additional entries
      ));

      // more palette options - see workflow example

      return actions;
   }

   private String getLabel(final GNode node) {
      if (node instanceof TaskNode) {
         return ((TaskNode) node).getName();
      }
      return node.getId();
   }

}
