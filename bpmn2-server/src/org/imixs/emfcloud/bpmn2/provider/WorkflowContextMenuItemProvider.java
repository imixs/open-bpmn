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
package org.imixs.emfcloud.bpmn2.provider;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.features.contextmenu.ContextMenuItemProvider;
import org.eclipse.glsp.server.features.contextmenu.MenuItem;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.imixs.emfcloud.bpmn2.handler.GridSnapper;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import static org.imixs.emfcloud.bpmn2.util.ModelTypes.COMP_HEADER;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.ICON;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.LABEL_HEADING;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.LABEL_ICON;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.LABEL_TEXT;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.STRUCTURE;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.TASK;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.SEQUENCE_FLOW;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.ACTIVITY_NODE;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.POOL;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.MANUAL_TASK;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.AUTOMATED_TASK;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.DECISION_NODE;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.FORK_NODE;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.JOIN_NODE;
import static org.imixs.emfcloud.bpmn2.util.ModelTypes.MERGE_NODE;
public class WorkflowContextMenuItemProvider implements ContextMenuItemProvider {

   @Inject
   protected GModelState modelState;

   @Override
   public List<MenuItem> getItems(final List<String> selectedElementIds, final GPoint position,
      final Map<String, String> args) {
      if (modelState.isReadonly()) {
         return Collections.emptyList();
      }
      GPoint snappedPosition = GridSnapper.snap(position);
      MenuItem newAutTask = new MenuItem("newAutoTask", "Automated Task",
         Arrays.asList(new CreateNodeOperation(AUTOMATED_TASK, snappedPosition)), true);
      MenuItem newManTask = new MenuItem("newManualTask", "Manual Task",
         Arrays.asList(new CreateNodeOperation(MANUAL_TASK, snappedPosition)), true);
      MenuItem newChildMenu = new MenuItem("new", "New", Arrays.asList(newAutTask, newManTask), "add", "0_new");
      return Lists.newArrayList(newChildMenu);
   }

}
