/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
package org.imixs.bpmn.glsp.taskedit;

import java.util.Optional;

import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.types.GLSPServerException;
import org.imixs.bpmn.bpmngraph.TaskNode;

import com.google.inject.Inject;

public class EditTaskOperationHandler extends AbstractOperationHandler<EditTaskOperation> {

   @Inject
   protected GModelState modelState;

   @Override
   protected void executeOperation(final EditTaskOperation operation) {
      Optional<TaskNode> task = modelState.getIndex().findElementByClass(operation.getTaskId(), TaskNode.class);
      if (task.isEmpty()) {
         throw new RuntimeException("Cannot find task with id '" + operation.getTaskId() + "'");
      }
      switch (operation.getFeature()) {

         case "taskType":
            task.get().setTaskType(operation.getValue());
            break;
         default:
            throw new GLSPServerException("Cannot edit task at feature '" + operation.getFeature() + "'");
      }
   }

}
