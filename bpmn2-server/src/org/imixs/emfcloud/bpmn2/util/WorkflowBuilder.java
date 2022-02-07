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
package org.imixs.emfcloud.bpmn2.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.builder.AbstractGCompartmentBuilder;
import org.eclipse.glsp.graph.builder.AbstractGEdgeBuilder;
import org.eclipse.glsp.graph.builder.AbstractGNodeBuilder;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.imixs.emf.bpmn2.ActivityNode;
import org.imixs.emf.bpmn2.Bpmn2Factory;
import org.imixs.emf.bpmn2.Pool;
import org.imixs.emf.bpmn2.Icon;
import org.imixs.emf.bpmn2.TaskNode; 
import org.imixs.emf.bpmn2.SequenceFlow;


public final class WorkflowBuilder {

   private static final String V_GRAB = "vGrab";
   private static final String H_GRAB = "hGrab";
   private static final String H_ALIGN = "hAlign";

   public static class SequenceFlowBuilder extends AbstractGEdgeBuilder<SequenceFlow, SequenceFlowBuilder> {

      private String condition;

      public SequenceFlowBuilder() {
         super(ModelTypes.SEQUENCE_FLOW);
      }

      public SequenceFlowBuilder probability(final String condition) {
         this.condition = condition;
         return self();
      }

      @Override
      protected void setProperties(final SequenceFlow edge) {
         super.setProperties(edge);
         edge.setCondition(condition);
      }

      @Override
      protected SequenceFlow instantiate() {
         return Bpmn2Factory.eINSTANCE.createSequenceFlow();
      }

      @Override
      protected SequenceFlowBuilder self() {
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
         return Bpmn2Factory.eINSTANCE.createActivityNode();
      }

      @Override
      protected ActivityNodeBuilder self() {
         return this;
      }
   }

   public static class TaskNodeBuilder extends AbstractGNodeBuilder<TaskNode, TaskNodeBuilder> {
      private final String name;
      private final String taskType;
      private final int duration;

      public TaskNodeBuilder(final String type, final String name, final String taskType, final int duration) {
         super(type);
         this.name = name;
         this.taskType = taskType;
         this.duration = duration;

      }

      @Override
      protected TaskNode instantiate() {
         return Bpmn2Factory.eINSTANCE.createTaskNode();
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
         taskNode.setDuration(duration);
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
         return Bpmn2Factory.eINSTANCE.createIcon();
      }

      @Override
      protected IconBuilder self() {
         return this;
      }

   }

   public static class PoolNodeBuilder extends AbstractGNodeBuilder<Pool, PoolNodeBuilder> {
      private String name;

      public PoolNodeBuilder(final String name) {
         super(ModelTypes.POOL);
         this.name = name;
      }

      @Override
      protected Pool instantiate() {
         return Bpmn2Factory.eINSTANCE.createPool();
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
         node.getCssClasses().add("category");
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

   private WorkflowBuilder() {}
}
