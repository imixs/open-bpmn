/********************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
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
package org.imixs.bpmn.glsp.handler;

import java.util.Optional;

import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.model.GModelState;
import org.imixs.bpmn.glsp.utils.BPMNBuilder.TaskNodeBuilder;
import org.imixs.bpmn.glsp.utils.ModelTypes;

public class CreateManualTaskHandler extends CreateTaskHandler {

   public CreateManualTaskHandler() {
      super(ModelTypes.MANUAL_TASK, i -> "ManualTask" + i);
   }

   @Override
   public String getLabel() { return "Manual Task"; }

   @Override
   protected TaskNodeBuilder builder(final Optional<GPoint> point, final GModelState modelState) {
      return super.builder(point, modelState)
         .addCssClass("manual");
   }
}
