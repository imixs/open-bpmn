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
package org.openbpmn.glsp.launch;

import org.apache.commons.cli.ParseException;
import org.eclipse.glsp.server.di.ServerModule;
import org.eclipse.glsp.server.launch.GLSPServerLauncher;
import org.eclipse.glsp.server.launch.SocketGLSPServerLauncher;
import org.eclipse.glsp.server.utils.LaunchUtil;
import org.eclipse.glsp.server.websocket.WebsocketServerLauncher;
import org.openbpmn.glsp.BPMNDiagramModule;

public final class BPMNServerLauncher {
   private BPMNServerLauncher() {
   }

   @SuppressWarnings("uncommentedmain")
   public static void main(final String[] args) {
      String processName = "open-bpmn.server-X.X.X-glsp.jar";
      try {
         BPMNCLIParser parser = new BPMNCLIParser(args, processName);
         // ElkLayoutEngine.initialize(new LayeredMetaDataProvider());

         int port = parser.parsePort();
         ServerModule bpmnServerModule = new BPMNServerModule()
               .configureDiagramModule(new BPMNDiagramModule());

         GLSPServerLauncher launcher = parser.isWebsocket()
               ? new WebsocketServerLauncher(bpmnServerModule, "/bpmn", parser.parseWebsocketLogLevel())
               : new SocketGLSPServerLauncher(bpmnServerModule);

         launcher.start("localhost", port, parser);

      } catch (ParseException ex) {
         ex.printStackTrace();
         System.out.println();
         LaunchUtil.printHelp(processName, BPMNCLIParser.getDefaultOptions());
      }
   }
}
