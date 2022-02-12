/********************************************************************************
 * Copyright (c) 2020-2022 EclipseSource and others.
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
package org.eclipse.glsp.example.minimal;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.eclipse.glsp.server.di.ServerModule;
import org.eclipse.glsp.server.launch.DefaultCLIParser;
import org.eclipse.glsp.server.launch.GLSPServerLauncher;
import org.eclipse.glsp.server.launch.SocketGLSPServerLauncher;
import org.eclipse.glsp.server.utils.LaunchUtil;

public final class MinimalServerLauncher {
   private MinimalServerLauncher() {}

   @SuppressWarnings("uncommentedmain")
   public static void main(final String[] args) {
      try {
         DefaultCLIParser cliParser = new DefaultCLIParser(args, "minimal server");
         LaunchUtil.configure(cliParser);
         int port = cliParser.parsePort();

         ServerModule serverModule = new ServerModule().configureDiagramModule(new MinimalDiagramModule());
         GLSPServerLauncher launcher = new SocketGLSPServerLauncher(serverModule);
         launcher.start("localhost", port);
      } catch (ParseException | IOException e) {
         e.printStackTrace();
      }
   }

}
