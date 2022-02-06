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
package org.imixs.emfcloud.bpmn2.server.launch;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.glsp.server.launch.DefaultCLIParser;

public class WorkflowCLIParser extends DefaultCLIParser {
   public static final String OPTION_WEBSOCKET = "websocket";

   public WorkflowCLIParser(final String[] args, final String processName)
      throws ParseException {
      super(args, WorkflowCLIParser.getDefaultOptions(), processName);
   }

   public boolean isWebsocket() { return hasOption(OPTION_WEBSOCKET); }

   public static Options getDefaultOptions() {
      Options options = DefaultCLIParser.getDefaultOptions();
      options.addOption(null, OPTION_WEBSOCKET, false,
         "Use websocket launcher instead of default launcher. [default='false']");
      return options;
   }

}
