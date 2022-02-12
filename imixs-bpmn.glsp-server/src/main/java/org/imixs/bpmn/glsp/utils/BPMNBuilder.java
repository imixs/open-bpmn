/********************************************************************************
 * Copyright (c) 2019-2022 EclipseSource and others.
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
import org.eclipse.glsp.graph.builder.AbstractGCompartmentBuilder;
import org.eclipse.glsp.graph.builder.AbstractGEdgeBuilder;
import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.imixs.bpmn.bpmngraph.ActivityNode;
import org.imixs.bpmn.bpmngraph.BpmngraphFactory;
import org.imixs.bpmn.bpmngraph.Icon;
import org.imixs.bpmn.bpmngraph.TaskNode;
import org.imixs.bpmn.bpmngraph.WeightedEdge;

public final class BPMNBuilder {

   private static final String V_GRAB = "vGrab";
   private static final String H_GRAB = "hGrab";
   private static final String H_ALIGN = "hAlign";

   public static class WeightedEdgeBuilder extends AbstractGEdgeBuilder<WeightedEdge, WeightedEdgeBuilder> {

      private String probability;

      public WeightedEdgeBuilder() {
         super(ModelTypes.WEIGHTED_EDGE);
      }

      public WeightedEdgeBuilder probability(final String probability) {
         this.probability = probability;
         return self();
      }

      @Override
      protected void setProperties(final WeightedEdge edge) {
         super.setProperties(edge);
         edge.setProbability(probability);
      }

      @Override
      protected WeightedEdge instantiate() {
         return BpmngraphFactory.eINSTANCE.createWeightedEdge();
      }

      @Override
      protected WeightedEdgeBuilder self() {
         return this;
      }

   }

   public static class ActivityNodeBuilder extends AbstractGNodeBuilder<ActivityNode, ActivityNodeBuilder> {
      protected String nodeType;

      public ActivityNodeBuilder(final String type, final String nodeType) {
         super(type);
         this.nodeType = nodeType;
      }

      @Override
      protected void setProperties(final ActivityNode node) {
         super.setProperties(node);
         node.setNodeType(nodeType);
      }

      @Override
      protected ActivityNode instantiate() {
         return BpmngraphFactory.eINSTANCE.createActivityNode();
      }

      @Override
      protected ActivityNodeBuilder self() {
         return this;
      }
   }

   public static class TaskNodeBuilder extends AbstractGNodeBuilder<TaskNode, TaskNodeBuilder> {
      private final String name;
      private final String taskType;

      public TaskNodeBuilder(final String type, final String name, final String taskType) {
         super(type);
         this.name = name;
         this.taskType = taskType;

      }

      @Override
      protected TaskNode instantiate() {
         return BpmngraphFactory.eINSTANCE.createTaskNode();
      }

      @Override
      protected TaskNodeBuilder self() {
         return this;
      }

      @Override
      public void setProperties(final TaskNode taskNode) {
         super.setProperties(taskNode);
         taskNode.setName(name);
         taskNode.setTaskType(taskType);
         taskNode.setLayout(GConstants.Layout.HBOX);
         taskNode.getLayoutOptions().put("paddingRight", 10);
         taskNode.getChildren().add(createCompartmentIcon(taskNode));
         taskNode.getChildren().add(createCompartmentHeader(taskNode));
      }

      private Icon createCompartmentIcon(final TaskNode taskNode) {
         return new IconBuilder().id(taskNode.getId() + "_icon").build();
      }

      private GLabel createCompartmentHeader(final TaskNode taskNode) {
         return new GLabelBuilder(ModelTypes.LABEL_HEADING) //
            .id(taskNode.getId() + "_classname") //
            .text(taskNode.getName()) //
            .build();
      }

   }

   public static class IconBuilder extends AbstractGCompartmentBuilder<Icon, IconBuilder> {

      public IconBuilder() {
         super(ModelTypes.ICON);
      }

      @Override
      protected Icon instantiate() {
         return BpmngraphFactory.eINSTANCE.createIcon();
      }

      @Override
      protected IconBuilder self() {
         return this;
      }

   }

   private BPMNBuilder() {}
}
