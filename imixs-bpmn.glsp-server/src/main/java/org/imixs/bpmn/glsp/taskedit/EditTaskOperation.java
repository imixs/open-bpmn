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

public class EditTaskOperation extends Operation {

   private String taskId;
   private String feature;
   private String value;

   public EditTaskOperation() {
      super("editTask");
   }

   public EditTaskOperation(final String taskId, final String feature, final String value) {
      this();
      this.taskId = taskId;
      this.feature = feature;
      this.value = value;
   }

   public String getTaskId() { return taskId; }

   public void setTaskId(final String taskId) { this.taskId = taskId; }

   public String getFeature() { return feature; }

   public void setFeature(final String feature) { this.feature = feature; }

   public String getValue() { return value; }

   public void setValue(final String value) { this.value = value; }

}
