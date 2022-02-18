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
import org.imixs.bpmn.bpmngraph.BpmngraphFactory;
import org.imixs.bpmn.bpmngraph.Gateway;
import org.imixs.bpmn.bpmngraph.Icon;
import org.imixs.bpmn.glsp.elements.IconBuilder;

/**
 * The GatewayNodeBuilder defines the layout of BPMN gateways
 *
 * @author rsoika
 *
 */
public class GatewayNodeBuilder extends AbstractGNodeBuilder<Gateway, GatewayNodeBuilder> {
   private final String name;
   private final String gatewayType;

   public GatewayNodeBuilder(final String type, final String name, final String gatewayType) {
      super(type);
      this.name = name;
      this.gatewayType = gatewayType;

   }

   @Override
   protected Gateway instantiate() {
      return BpmngraphFactory.eINSTANCE.createGateway();
   }

   /**
    * Define layout properties
    */
   @Override
   protected void setProperties(final Gateway node) {
      super.setProperties(node);

      node.setName(name);
      node.setGatewayType(gatewayType);

      node.getChildren().add(createCompartmentIcon(node));

   }

   private Icon createCompartmentIcon(final Gateway gatewayNode) {
      return new IconBuilder().id(gatewayNode.getId() + "_icon").build();
   }

   @Override
   protected GatewayNodeBuilder self() {
      return this;
   }
}
