/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.openbpmn.glsp;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.glsp.server.di.ServerModule;
import org.eclipse.glsp.server.launch.DefaultCLIParser;
import org.eclipse.glsp.server.launch.GLSPServerLauncher;
import org.eclipse.glsp.server.launch.SocketGLSPServerLauncher;
import org.eclipse.glsp.server.utils.LaunchUtil;

public final class BPMNServerLauncher {

    private BPMNServerLauncher() {
    }

    @SuppressWarnings("uncommentedmain")
    public static void main(final String[] args) {
        try {

            System.out.println("[Open-BPMN-Server] Version " + getMavenProjectVersion());
            DefaultCLIParser cliParser = new DefaultCLIParser(args, "bpmn server");
            LaunchUtil.configure(cliParser);
            int port = cliParser.parsePort();

            ServerModule serverModule = new ServerModule().configureDiagramModule(new BPMNDiagramModule());
            GLSPServerLauncher launcher = new SocketGLSPServerLauncher(serverModule);
            launcher.start("localhost", port);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to receive dynamically the maven version
     * See:
     * https://stackoverflow.com/questions/3697449/retrieve-version-from-maven-pom-xml-in-code
     * 
     * @return
     */
    public static String getMavenProjectVersion() {
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));

            return model.getVersion();
        } catch (IOException | XmlPullParserException e) {
            System.out.println("Failed to read server version: " + e.getMessage());
        }
        return "";

    }
}
