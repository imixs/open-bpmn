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
import { createMinimalDiagramContainer } from '@eclipse-glsp-examples/minimal-glsp';
import {
    configureDiagramServer,
    GLSPDiagramConfiguration,
    GLSPTheiaDiagramServer,
    TheiaDiagramServer
} from '@eclipse-glsp/theia-integration/lib/browser';
import { Container, injectable } from 'inversify';
import 'sprotty-theia/css/theia-sprotty.css';
import { MinimalLanguage } from '../../common/minimal-language';

@injectable()
export class MinimalDiagramConfiguration extends GLSPDiagramConfiguration {
    diagramType: string = MinimalLanguage.diagramType;

    doCreateContainer(widgetId: string): Container {
        const container = createMinimalDiagramContainer(widgetId);
        configureDiagramServer(container, GLSPTheiaDiagramServer);
        container.bind(TheiaDiagramServer).toService(GLSPTheiaDiagramServer);
        return container;
    }
}
