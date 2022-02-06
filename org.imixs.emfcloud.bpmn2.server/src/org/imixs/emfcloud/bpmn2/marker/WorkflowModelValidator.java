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
package org.imixs.emfcloud.bpmn2.marker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.features.validation.Marker;
import org.eclipse.glsp.server.features.validation.MarkerKind;
import org.eclipse.glsp.server.features.validation.ModelValidator;
import org.eclipse.glsp.server.model.GModelState;
import org.imixs.emf.bpmn2.ActivityNode;
import org.imixs.emf.bpmn2.TaskNode;
import org.imixs.emfcloud.bpmn2.util.ModelTypes;

import com.google.inject.Inject;

public class WorkflowModelValidator implements ModelValidator {

   @Inject
   protected GModelState modelState;

   @Override
   public List<Marker> validate(final GModelElement... elements) {
      List<Marker> markers = new ArrayList<>();

      for (GModelElement element : elements) {
         if (element instanceof TaskNode) {
            markers.addAll(validateTaskNode(modelState, element));
         } else if (element instanceof ActivityNode) {
            ActivityNode activityNode = (ActivityNode) element;
            if ("decisionNode".equals(activityNode.getNodeType())) {
               markers.addAll(validateDecisionNode(modelState, element));
            } else if ("mergeNode".equals(activityNode.getNodeType())) {
               markers.addAll(validateMergeNode(modelState, element));
            }
         }
         if (element.getChildren() != null) {
            markers.addAll(validate(element.getChildren().toArray(new GModelElement[element.getChildren().size()])));
         }
      }
      return markers;
   }

   private static List<Marker> validateTaskNode(final GModelState modelState, final GModelElement taskNode) {
      List<Marker> markers = new ArrayList<>();
      validateTaskNode_isAutomated(modelState, taskNode).ifPresent(m -> markers.add(m));
      validateTaskNode_labelStartsUpperCase(modelState, taskNode).ifPresent(m -> markers.add(m));
      return markers;
   }

   @SuppressWarnings("checkstyle:MethodName")
   private static Optional<Marker> validateTaskNode_isAutomated(final GModelState modelState,
      final GModelElement element) {
      TaskNode taskNode = (TaskNode) element;
      if ("automated".equals(taskNode.getTaskType())) {
         return Optional
            .of(new Marker("Automated task", "This is an automated task", element.getId(), MarkerKind.INFO));
      }
      return Optional.empty();
   }

   @SuppressWarnings("checkstyle:MethodName")
   private static Optional<Marker> validateTaskNode_labelStartsUpperCase(final GModelState modelState,
      final GModelElement element) {
      TaskNode taskNode = (TaskNode) element;

      boolean hasLowerCaseLabel = taskNode.getChildren().stream()
         .filter(c -> ModelTypes.LABEL_HEADING.equals(c.getType()))
         .filter(GLabel.class::isInstance)
         .map(GLabel.class::cast)
         .map(GLabel::getText)
         .anyMatch(text -> text.length() > 0 && !Character.isUpperCase(text.charAt(0)));

      if (hasLowerCaseLabel) {
         return Optional.of(new Marker("Task node label in upper case",
            "Task node names should start with upper case letters", element.getId(), MarkerKind.WARNING));
      }
      return Optional.empty();
   }

   private static List<Marker> validateDecisionNode(final GModelState modelState,
      final GModelElement decisionNode) {
      List<Marker> markers = new ArrayList<>();
      validateDecisionNode_hasOneIncomingEdge(modelState, decisionNode).ifPresent(m -> markers.add(m));
      return markers;
   }

   @SuppressWarnings("checkstyle:MethodName")
   private static Optional<Marker> validateDecisionNode_hasOneIncomingEdge(final GModelState modelState,
      final GModelElement decisionNode) {
      Collection<GEdge> incomingEdges = modelState.getIndex().getIncomingEdges(decisionNode);
      if (incomingEdges.size() > 1) {
         return Optional.of(new Marker("Too many incoming edges", "Decision node may only have one incoming edge.",
            decisionNode.getId(), MarkerKind.ERROR));
      } else if (incomingEdges.size() == 0) {
         return Optional.of(new Marker("Missing incoming edge", "Decision node must have one incoming edge.",
            decisionNode.getId(), MarkerKind.ERROR));
      }
      return Optional.empty();
   }

   private static List<Marker> validateMergeNode(final GModelState modelState, final GModelElement mergeNode) {
      List<Marker> markers = new ArrayList<>();
      validateMergeNode_hasOneOutgoingEdge(modelState, mergeNode).ifPresent(m -> markers.add(m));
      return markers;
   }

   @SuppressWarnings("checkstyle:MethodName")
   private static Optional<Marker> validateMergeNode_hasOneOutgoingEdge(final GModelState modelState,
      final GModelElement mergeNode) {
      Collection<GEdge> outgoingEdges = modelState.getIndex().getOutgoingEdges(mergeNode);
      if (outgoingEdges.size() > 1) {
         return Optional.of(new Marker("Too many outgoing edges", "Merge node may only have one outgoing edge.",
            mergeNode.getId(), MarkerKind.ERROR));
      } else if (outgoingEdges.size() == 0) {
         return Optional.of(new Marker("Missing outgoing edge", "Merge node must have one incoming edge.",
            mergeNode.getId(), MarkerKind.ERROR));
      }
      return Optional.empty();
   }
}
