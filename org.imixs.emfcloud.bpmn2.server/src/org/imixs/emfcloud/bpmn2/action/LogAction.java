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
package org.imixs.emfcloud.bpmn2.action;

import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.types.Severity;

public class LogAction extends Action {
   public static final String KIND = "logAction";

   private Severity severity;
   private String message;

   public LogAction() {
      super(KIND);
   }

   public LogAction(final Severity severity, final String message) {
      this();
      this.severity = severity;
      this.message = message;
   }

   public void setSeverity(final Severity severity) { this.severity = severity; }

   public Severity getSeverity() { return severity; }

   public void setMessage(final String message) { this.message = message; }

   public String getMessage() { return message; }

}
