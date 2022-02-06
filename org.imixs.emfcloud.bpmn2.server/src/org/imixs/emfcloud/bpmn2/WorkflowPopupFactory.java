/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
package org.imixs.emfcloud.bpmn2;

import java.util.Optional;

import org.eclipse.glsp.graph.GBounds;
import org.eclipse.glsp.graph.GHtmlRoot;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GPreRenderedElement;
import org.eclipse.glsp.graph.builder.impl.GHtmlRootBuilder;
import org.eclipse.glsp.graph.builder.impl.GPreRenderedElementBuilder;
import org.eclipse.glsp.server.features.popup.PopupModelFactory;
import org.eclipse.glsp.server.features.popup.RequestPopupModelAction;
import org.imixs.emf.bpmn2.TaskNode;

public class WorkflowPopupFactory implements PopupModelFactory {

   private String generateTitle(final TaskNode task) {
      return task.getName();
   }

   private String generateBody(final TaskNode task) {
      return String.format(NL + "Type: %s" + NL + "Duration: %s" + NL + " Reference: %s" + NL, task.getTaskType(),
         task.getDuration(), task.getReference());
   }

   private static final String NL = "<br/>";

   @Override
   public Optional<GHtmlRoot> createPopupModel(final GModelElement element, final RequestPopupModelAction action) {
      if (element != null && element instanceof TaskNode) {
         TaskNode task = (TaskNode) element;
         GBounds bounds = action.getBounds();

         GPreRenderedElement popupTitle = new GPreRenderedElementBuilder()
            .id("popup-title")
            .code("<div class=\"sprotty-popup-title\">" + generateTitle(task) + "</div>")
            .build();

         GPreRenderedElement popupBody = new GPreRenderedElementBuilder()
            .id("popup-body")
            .code("<div class=\"sprotty-popup-body\">" + generateBody(task) + "</div>")
            .build();

         GHtmlRoot root = new GHtmlRootBuilder()
            .canvasBounds(bounds)
            .id("sprotty-popup")
            .add(popupTitle)
            .add(popupBody)
            .build();

         return Optional.of(root);
      }
      return Optional.empty();

   }

}
