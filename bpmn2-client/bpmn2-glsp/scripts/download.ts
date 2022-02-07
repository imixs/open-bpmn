/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
import download from 'mvn-artifact-download';
import { join, resolve } from 'path';

const downloadDir = resolve(join(__dirname)) + '/../../..';
const mavenRepository = 'https://oss.sonatype.org/content/repositories/snapshots/';
const groupId = 'org.eclipse.glsp.example';
const artifactId = 'org.eclipse.glsp.example.workflow';
const version = '0.9.0';
const classifier = 'glsp';

console.log('Downloading latest version of the Workflow Example Java Server from the maven repository...');
download({ groupId, artifactId, version, classifier, isSnapShot: true }, downloadDir, mavenRepository).then(() =>
    console.log(
        'Download completed. Start the server using this command: \njava -jar org.eclipse.glsp.example.workflow-' +
            version +
            '-SNAPSHOT-glsp.jar org.eclipse.glsp.example.workflow.launch.ExampleServerLauncher --port=8081 --websocket\n\n' +
            'After starting the server, access the following file locally in your browser to see the running example:\n' +
            './examples/workflow-standalone/app/diagram.html'
    )
);
