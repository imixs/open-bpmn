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
package org.imixs.emfcloud.bpmn2.provider;

import java.util.Collection;

import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.server.model.GModelState;
import org.imixs.emf.bpmn2.TaskNode;

public class NextNodeNavigationTargetProvider extends AbstractNextOrPreviousNavigationTargetProvider {

   @Override
   public String getTargetTypeId() { return "next"; }

   @Override
   protected Collection<GEdge> getEdges(final TaskNode taskNode, final GModelState modelState) {
      return modelState.getIndex().getOutgoingEdges(taskNode);
   }

   @Override
   protected String getSourceOrTarget(final GEdge edge) {
      return edge.getTargetId();
   }

}
