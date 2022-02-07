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
import { InitializeResult, InstanceRegistry, ServerMessageAction } from '@eclipse-glsp/client';
import { injectable, multiInject, optional } from '@theia/core/shared/inversify';
import { TheiaSprottyConnector } from 'sprotty-theia';

export const TheiaGLSPConnector = Symbol('TheiaGLSPConnector');

export interface TheiaGLSPConnector extends TheiaSprottyConnector {
    readonly diagramType: string;
    readonly diagramManagerId: string;
    readonly initializeResult: Promise<InitializeResult>;
    showMessage(widgetId: string, action: ServerMessageAction): void;
}

export function isTheiaGLSPConnector(connector?: TheiaSprottyConnector): connector is TheiaGLSPConnector {
    return (
        connector !== undefined &&
        'diagramType' in connector &&
        typeof connector['diagramType'] === 'string' &&
        'diagramManagerId' in connector &&
        typeof connector['diagramManagerId'] === 'string' &&
        'showMessage' in connector &&
        typeof connector['showMessage'] === 'function'
    );
}

@injectable()
export class TheiaGLSPConnectorRegistry extends InstanceRegistry<TheiaGLSPConnector> {
    constructor(@multiInject(TheiaGLSPConnector) @optional() connectors: TheiaGLSPConnector[]) {
        super();
        connectors.forEach(connector => this.register(connector.diagramType, connector));
    }
}
