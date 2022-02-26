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
package org.imixs.bpmn.glsp.elements.gateway;

import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.imixs.bpmn.bpmngraph.BpmngraphFactory;
import org.imixs.bpmn.bpmngraph.GatewayNode;
import org.imixs.bpmn.glsp.utils.ModelTypes;

/**
 * The GatewayNodeBuilder defines the layout of BPMN gateways
 *
 * @author rsoika
 *
 */
public class GatewayNodeBuilder extends AbstractGNodeBuilder<GatewayNode, GatewayNodeBuilder> {
   private static final String V_GRAB = "vGrab";
   private static final String H_GRAB = "hGrab";

   private final String name;
   private final String gatewayType;

   public GatewayNodeBuilder(final String type, final String name, final String gatewayType) {
      super(type);
      this.name = name;
      this.gatewayType = gatewayType;

   }

   @Override
   protected GatewayNode instantiate() {
      return BpmngraphFactory.eINSTANCE.createGatewayNode();
   }

   @Override
   protected GatewayNodeBuilder self() {
      return this;
   }

   /**
    * Define layout properties
    */
   @Override
   protected void setProperties(final GatewayNode node) {
      super.setProperties(node);
      node.setName(name);
      node.getCategory().add(gatewayType);

      node.setLayout(GConstants.Layout.VBOX);
      node.getLayoutOptions().put("vAlign", "center");
      node.getLayoutOptions().put(H_GRAB, false);
      node.getLayoutOptions().put(V_GRAB, false);

      node.getChildren().add(createCompartmentHeader(node));

   }

   private GLabel createCompartmentHeader(final GatewayNode taskNode) {

      return new GLabelBuilder(ModelTypes.LABEL_HEADING) //
         .id(taskNode.getId() + "_classname") //
         .text(taskNode.getName())
         .build();
   }

}
