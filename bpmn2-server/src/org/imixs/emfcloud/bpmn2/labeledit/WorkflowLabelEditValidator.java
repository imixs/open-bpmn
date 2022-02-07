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
package org.imixs.emfcloud.bpmn2.labeledit;

import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.features.directediting.LabelEditValidator;
import org.eclipse.glsp.server.features.directediting.ValidationStatus;
import org.eclipse.glsp.server.model.GModelState;
import org.imixs.emf.bpmn2.TaskNode;
import org.imixs.emfcloud.bpmn2.util.ModelTypes;

import com.google.inject.Inject;

public class WorkflowLabelEditValidator implements LabelEditValidator {

   @Inject
   protected GModelState modelState;

   @Override
   public ValidationStatus validate(final String label, final GModelElement element) {
      if (label.length() < 1) {
         return ValidationStatus.error("Name must not be empty");
      }

      Set<TaskNode> taskNodes = modelState.getIndex().getAllByClass(TaskNode.class);
      Stream<GLabel> otherLabels = taskNodes.stream()
         .filter(e -> !e.getId().equals(element.getId()))
         .flatMap(n -> n.getChildren().stream())
         .filter(c -> ModelTypes.LABEL_HEADING.equals(c.getType()))
         .filter(GLabel.class::isInstance)
         .map(GLabel.class::cast);

      boolean hasDuplicate = otherLabels.anyMatch(otherLabel -> label.equals(otherLabel.getText()));

      if (hasDuplicate) {
         return ValidationStatus.warning("Name should be unique");
      }

      return ValidationStatus.ok();
   }

}
