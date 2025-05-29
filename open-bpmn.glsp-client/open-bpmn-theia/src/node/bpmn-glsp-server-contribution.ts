/********************************************************************************
 * Copyright (c) 2020-2022 EclipseSource and others.
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
import {
    getPort,
    GLSPSocketServerContribution,
    GLSPSocketServerContributionOptions
} from '@eclipse-glsp/theia-integration/lib/node';
import { injectable } from '@theia/core/shared/inversify';
import { readdirSync } from 'fs';
import { join, resolve } from 'path';
import { BPMNLanguage } from '../common/bpmn-language';

export const DEFAULT_PORT = 0;
export const PORT_ARG_KEY = 'GLSP_PORT';
export const LOG_DIR = join(__dirname, '..', '..', 'logs');
const SERVER_DIR = resolve(join(__dirname, '..', '..', '..', '..', 'open-bpmn.glsp-server', 'target'));

// find latest .jar version
function findJarFile(): string {
    const files = readdirSync(SERVER_DIR);
    const jarFile = files.find(file => file.endsWith('-glsp.jar') || file.endsWith('.jar'));
    if (!jarFile) {
        throw new Error(`No JAR file found in ${SERVER_DIR}`);
    }
    return join(SERVER_DIR, jarFile);
}

const JAR_FILE = findJarFile();

@injectable()
export class BPMNGLSPSocketServerContribution extends GLSPSocketServerContribution {
    readonly id = BPMNLanguage.contributionId;

    createContributionOptions(): Partial<GLSPSocketServerContributionOptions> {
        console.log('├── LOG_DIR = '+ LOG_DIR);
        console.log('├── PORT_ARG_KEY = '+ PORT_ARG_KEY);
        const _port=getPort(PORT_ARG_KEY, DEFAULT_PORT);
        console.log('├── PORT = '+ _port);
        console.log('├── JAR_FILE = '+ JAR_FILE); // Debug output for the found JAR file
        return {
            executable: JAR_FILE,
            additionalArgs: ['--consoleLog', 'false', '--fileLog', 'true', '--logDir', LOG_DIR],
            socketConnectionOptions: {
                port: getPort(PORT_ARG_KEY, DEFAULT_PORT)
            }
        };
    }

    protected override processLogInfo(line: string): void {
        super.processLogInfo(line);
        console.info(`${this.id}: ${line}`);
    }
}
