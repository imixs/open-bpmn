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
package org.imixs.bpmn.glsp.handler;

import java.util.Map;
import java.util.Optional;

import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.imixs.bpmn.glsp.util.ModelTypes;
import org.imixs.bpmn.glsp.util.WorkflowBuilder.ActivityNodeBuilder;

public class CreateDecisionNodeHandler extends CreateActivityNodeHandler {

   public CreateDecisionNodeHandler() {
      super(ModelTypes.DECISION_NODE, "Decision Node");
   }

   @Override
   protected GNode createNode(final Optional<GPoint> point, final Map<String, String> args) {
      String nodeType = ModelTypes.toNodeType(getElementTypeId());
      return new ActivityNodeBuilder(getElementTypeId(), nodeType) //
         .layoutOptions(new GLayoutOptions().minHeight(32d).minWidth(32d)) //
         .position(point.orElse(null)) //
         .build();
   }

}
