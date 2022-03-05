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

import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.imixs.bpmn.bpmngraph.BpmngraphFactory;
import org.imixs.bpmn.bpmngraph.GatewayNode;
import org.imixs.bpmn.glsp.utils.BPMNBuilderHelper;

/**
 * The GatewayNodeBuilder defines the layout of BPMN gateways
 *
 * @author rsoika
 *
 */
public class GatewayNodeBuilder extends AbstractGNodeBuilder<GatewayNode, GatewayNodeBuilder> {

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
      node.setLayout(GConstants.Layout.FREEFORM);

      node.getChildren().add(BPMNBuilderHelper.createCompartmentHeader(node));
      node.getChildren().add(BPMNBuilderHelper.createPort(node, -5.0, -5.0, "_north"));
      node.getChildren().add(BPMNBuilderHelper.createPort(node, -35.0, 25.0, "_west"));
      node.getChildren().add(BPMNBuilderHelper.createPort(node, 25.0, 25.0, "_east"));
      node.getChildren().add(BPMNBuilderHelper.createPort(node, -5.0, 55.0, "_south"));
   }

}
