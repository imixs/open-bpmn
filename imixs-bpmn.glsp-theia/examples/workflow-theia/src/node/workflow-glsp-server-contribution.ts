/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
import { getPort } from '@eclipse-glsp/protocol';
import { JavaSocketServerContribution, JavaSocketServerLaunchOptions } from '@eclipse-glsp/theia-integration/lib/node';
import { injectable } from '@theia/core/shared/inversify';
import { join, resolve } from 'path';
import { WorkflowLanguage } from '../common/workflow-language';

export const DEFAULT_PORT = 5007;
export const PORT_ARG_KEY = 'WF_GLSP';
export const SERVER_DIR = join(__dirname, '..', '..', 'server');
export const JAR_FILE = resolve(join(SERVER_DIR, 'org.eclipse.glsp.example.workflow-0.10.0-SNAPSHOT-glsp.jar'));

@injectable()
export class WorkflowGLServerContribution extends JavaSocketServerContribution {
    readonly id = WorkflowLanguage.contributionId;

    createLaunchOptions(): Partial<JavaSocketServerLaunchOptions> {
        return {
            jarPath: JAR_FILE,
            additionalArgs: ['--consoleLog', 'false', '--fileLog', 'true', '--logDir', SERVER_DIR],
            socketConnectionOptions: {
                port: getPort(PORT_ARG_KEY, DEFAULT_PORT)
            }
        };
    }
}
