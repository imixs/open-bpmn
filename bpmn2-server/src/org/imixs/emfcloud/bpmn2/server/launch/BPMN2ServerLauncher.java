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
package org.imixs.emfcloud.bpmn2.server.launch;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.eclipse.elk.alg.layered.options.LayeredMetaDataProvider;
import org.eclipse.glsp.layout.ElkLayoutEngine;
import org.eclipse.glsp.server.di.ServerModule;
import org.eclipse.glsp.server.launch.SocketGLSPServerLauncher;
import org.eclipse.glsp.server.launch.GLSPServerLauncher;
import org.eclipse.glsp.server.utils.LaunchUtil;
import org.eclipse.glsp.server.websocket.WebsocketServerLauncher;
import org.imixs.emfcloud.bpmn2.WorkflowDiagramModule;

public final class BPMN2ServerLauncher {
   private BPMN2ServerLauncher() {}

 
   public static void main(final String[] args) {
      String processName = "WorkflowExampleGlspServer";
      try {
         BPMN2CLIParser parser = new BPMN2CLIParser(args, processName);
         LaunchUtil.configure(parser);
         ElkLayoutEngine.initialize(new LayeredMetaDataProvider());

         int port = parser.parsePort();
         ServerModule workflowServerModule = new ServerModule()
            .configureDiagramModule(new WorkflowDiagramModule());

         GLSPServerLauncher launcher = parser.isWebsocket()
            ? new WebsocketServerLauncher(workflowServerModule, "/workflow")
            : new SocketGLSPServerLauncher(workflowServerModule);

         launcher.start("localhost", port);

      } catch (ParseException | IOException ex) {
         ex.printStackTrace();
         System.out.println();
         LaunchUtil.printHelp(processName, BPMN2CLIParser.getDefaultOptions());
      }
   }
}
