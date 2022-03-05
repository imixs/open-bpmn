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
package org.imixs.bpmn.glsp.elements.pool;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.imixs.bpmn.bpmngraph.BpmngraphFactory;
import org.imixs.bpmn.bpmngraph.Pool;
import org.imixs.bpmn.glsp.utils.ModelTypes;

/**
 * BPMN 2.0 Pool Element
 *
 * @author rsoika
 *
 */
public class PoolNodeBuilder extends AbstractGNodeBuilder<Pool, PoolNodeBuilder> {
   private String name;

   private static final String V_GRAB = "vGrab";
   private static final String H_GRAB = "hGrab";
   private static final String H_ALIGN = "hAlign";

   public PoolNodeBuilder(final String name) {
      super(ModelTypes.POOL);
      this.name = name;
   }

   @Override
   protected Pool instantiate() {
      return BpmngraphFactory.eINSTANCE.createPool();
   }

   @Override
   protected PoolNodeBuilder self() {
      return this;
   }

   public void setName(final String name) { this.name = name; }

   @Override
   protected void setProperties(final Pool node) {
      super.setProperties(node);
      node.setName(name);
      node.setLayout(GConstants.Layout.VBOX);
      node.getLayoutOptions().put(H_ALIGN, "center");
      node.getLayoutOptions().put(H_GRAB, false);
      node.getLayoutOptions().put(V_GRAB, false);
      node.getCssClasses().add("pool");
      node.getChildren().add(createLabelCompartment(node));
      node.getChildren().add(createStructCompartment(node));
   }

   private GCompartment createLabelCompartment(final Pool node) {
      Map<String, Object> layoutOptions = new HashMap<>();

      return new GCompartmentBuilder(ModelTypes.COMP_HEADER) //
         .id(node.getId() + "_header") //
         .layout(GConstants.Layout.HBOX) //
         .layoutOptions(layoutOptions) //
         .add(createCompartmentHeader(node)) //
         .build();
   }

   private GLabel createCompartmentHeader(final Pool node) {
      return new GLabelBuilder(ModelTypes.LABEL_HEADING) //
         .id(node.getId() + "_classname") //
         .text(node.getName()) //
         .build();
   }

   private GCompartment createStructCompartment(final Pool node) {
      Map<String, Object> layoutOptions = new HashMap<>();
      layoutOptions.put(H_ALIGN, "left");
      layoutOptions.put(H_GRAB, true);
      layoutOptions.put(V_GRAB, true);
      GCompartmentBuilder structCompartmentBuilder = new GCompartmentBuilder(ModelTypes.STRUCTURE) //
         .id(node.getId() + "_struct") //
         .layout(GConstants.Layout.FREEFORM) //
         .layoutOptions(layoutOptions);
      return structCompartmentBuilder //
         .build();
   }
}
