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
package org.openbpmn.glsp.elements.flow;

import java.util.Optional;

import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.builder.impl.GArguments;
import org.eclipse.glsp.graph.builder.impl.GEdgeBuilder;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.gmodel.CreateEdgeOperationHandler;

public class XXCreateEdgeHandler extends CreateEdgeOperationHandler {

   public XXCreateEdgeHandler() {
      super(DefaultTypes.EDGE, "Edge");
   }

   @Override
   protected Optional<GEdge> createEdge(final GModelElement source, final GModelElement target,
      final GModelState modelState) {
      return Optional.of(new GEdgeBuilder() //
         .source(source) //
         .target(target) //
         .addArgument(GArguments.edgePadding(10)) //
         .build());
   }

}
