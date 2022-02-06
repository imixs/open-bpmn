/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
package org.imixs.emfcloud.bpmn2.taskedit;

import org.eclipse.glsp.server.features.directediting.ContextEditValidator;
import org.eclipse.glsp.server.features.directediting.RequestEditValidationAction;
import org.eclipse.glsp.server.features.directediting.ValidationStatus;
import org.eclipse.glsp.server.model.GModelState;

import com.google.inject.Inject;

public class TaskEditValidator implements ContextEditValidator {

   @Override
   public String getContextId() { return "task-editor"; }

   @Inject
   protected GModelState modelState;

   @SuppressWarnings("checkstyle:cyclomaticComplexity")
   @Override
   public ValidationStatus validate(final RequestEditValidationAction action) {
      String text = action.getText();
      if (text.startsWith(TaskEditContextActionProvider.DURATION_PREFIX)) {
         String durationString = text.substring(TaskEditContextActionProvider.DURATION_PREFIX.length());
         try {
            int duration = Integer.parseInt(durationString);
            if (duration < 0 || duration > 100) {
               return ValidationStatus.warning("'" + durationString + "' should be between 0 and 100.");
            }
         } catch (NumberFormatException e) {
            return ValidationStatus.error("'" + durationString + "' is not a valid number.");
         }
      } else if (text.startsWith(TaskEditContextActionProvider.TYPE_PREFIX)) {
         String typeString = text.substring(TaskEditContextActionProvider.TYPE_PREFIX.length());
         if (!typeString.equals("automated") && !typeString.equals("manual")) {
            return ValidationStatus
               .error("Type of task can only be manual or automatic. You entered '" + typeString + "'.");
         }
      }
      return ValidationStatus.ok();
   }

}
