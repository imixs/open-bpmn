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
import { createBPMNDiagramContainer } from '@open-bpmn/open-bpmn-glsp';
import { configureDiagramServer,GLSPDiagramConfiguration} from '@eclipse-glsp/theia-integration';
import { Container, injectable } from '@theia/core/shared/inversify';

import { BPMNLanguage } from '../../common/bpmn-language';
import { BPMNDiagramServer } from './bpmn-diagram-server';

@injectable()
export class BPMNDiagramConfiguration extends GLSPDiagramConfiguration {
    diagramType: string = BPMNLanguage.diagramType;

    doCreateContainer(widgetId: string): Container {
        const container = createBPMNDiagramContainer(widgetId);
        configureDiagramServer(container, BPMNDiagramServer);
        return container;
    }
}
