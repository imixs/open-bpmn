/********************************************************************************
 * Copyright (c) 2021-2022 EclipseSource and others.
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
package org.imixs.bpmn.glsp.elements.pool;

import java.util.Map;
import java.util.Optional;

import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GArguments;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.utils.GModelUtil;
import org.imixs.bpmn.bpmngraph.BpmngraphPackage;
import org.imixs.bpmn.glsp.handler.CreateBPMNNodeOperationHandler;
import org.imixs.bpmn.glsp.utils.ModelTypes;

public class CreatePoolHandler extends CreateBPMNNodeOperationHandler {

   public CreatePoolHandler() {
      super(ModelTypes.POOL);
   }

   protected PoolNodeBuilder builder(final Optional<GPoint> point, final GModelState modelState) {

      int nodeCounter = GModelUtil.generateId(BpmngraphPackage.Literals.POOL, "pool", modelState);
      String name = "Pool " + nodeCounter;

      return new PoolNodeBuilder(name) //
         .position(point.orElse(null))
         .addArguments(GArguments.cornerRadius(5));
   }

   @Override
   protected GNode createNode(final Optional<GPoint> point, final Map<String, String> args) {
      return builder(point, modelState).build();
   }

   @Override
   public String getLabel() { return "Pool"; }
}
