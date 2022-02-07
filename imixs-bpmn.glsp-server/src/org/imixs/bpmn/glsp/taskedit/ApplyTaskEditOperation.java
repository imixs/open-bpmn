/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
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

import org.eclipse.glsp.server.operations.Operation;

public class ApplyTaskEditOperation extends Operation {

   private String taskId;
   private String expression;

   public ApplyTaskEditOperation() {
      super("applyTaskEdit");
   }

   public ApplyTaskEditOperation(final String taskId, final String expression) {
      this();
      this.taskId = taskId;
      this.expression = expression;
   }

   public String getTaskId() { return taskId; }

   public void setTaskId(final String taskId) { this.taskId = taskId; }

   public String getExpression() { return expression; }

   public void setExpression(final String expression) { this.expression = expression; }

}
