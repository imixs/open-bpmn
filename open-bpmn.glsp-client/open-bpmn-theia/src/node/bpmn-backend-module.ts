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
/********************************************************************************
 * Copyright (c) 2019-2023 EclipseSource and others.
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
import { bindAsService } from '@eclipse-glsp/protocol';
import { GLSPServerContribution } from '@eclipse-glsp/theia-integration/lib/node';
import { ContainerModule } from '@theia/core/shared/inversify';
import { BPMNGLSPSocketServerContribution } from './bpmn-glsp-server-contribution';

export default new ContainerModule(bind => {
    if (isDirectWebSocketConnection()) {
        return;
    }
    bindAsService(bind, GLSPServerContribution, BPMNGLSPSocketServerContribution);
});

const directWebSocketArg = '--directWebSocket';
/**
 * Utility function to parse if the frontend should connect directly to a running GLSP WebSocket Server instance
 * and skip the binding of the backend contribution.
 * i.e. if the {@link directWebSocketArg `--directWebSocket`} argument has been passed.
 * @returns `true` if the {@link directWebSocketArg `--directWebSocket`} argument has been set.
 */
function isDirectWebSocketConnection(): boolean {
    const args = process.argv.filter(a => a.toLowerCase().startsWith(directWebSocketArg.toLowerCase()));
    return args.length > 0;
}

export const integratedArg = '--integratedNode';

/**
 * Utility function to parse if the frontend should connect to a GLSP server running directly in the backend
 * i.e. if the {@link integratedArg `--integratedNode`} argument has been passed.
 * @returns `true` if the {@link integratedArg `--integratedNode`} argument has been set.
 */
export function isIntegratedNodeServer(): boolean {
    const args = process.argv.filter(a => a.toLowerCase().startsWith(integratedArg.toLowerCase()));
    return args.length > 0;
}
