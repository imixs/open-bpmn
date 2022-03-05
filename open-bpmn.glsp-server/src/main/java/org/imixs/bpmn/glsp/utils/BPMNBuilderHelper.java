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
package org.imixs.bpmn.glsp.utils;

import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GPort;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.builder.impl.GPortBuilder;
import org.imixs.bpmn.bpmngraph.BaseElement;

/**
 * The GatewayNodeBuilder provides helper methods to create GNode Elements
 *
 * @author rsoika
 *
 */
public class BPMNBuilderHelper {

   /**
    * Creates a GLabel for the name of a BaseElement
    *
    * @param node
    * @return GPort
    */
   public static GLabel createCompartmentHeader(final BaseElement node) {
      return new GLabelBuilder(ModelTypes.LABEL_HEADING) //
         .id(node.getId() + "_classname") //
         .text(node.getName())
         .build();
   }

   /**
    * Creates a GPort for a BaseElement
    * See:
    * https://github.com/eclipse-glsp/glsp/issues/264
    *
    * @param node
    * @param x
    * @param y
    * @return GPort
    */
   public static GPort createPort(final BaseElement node, final Double x, final Double y, final String subId) {
      return new GPortBuilder()
         .id(node.getId() + subId)
         .position(x, y)
         .size(10.0, 10.0)
         .addCssClass("bpmn-port")
         .build();
   }
}
