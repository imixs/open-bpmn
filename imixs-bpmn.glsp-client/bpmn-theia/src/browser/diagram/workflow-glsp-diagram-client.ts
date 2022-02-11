/********************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
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
import { GLSPDiagramClient } from '@eclipse-glsp/theia-integration/lib/browser';
import { EditorManager } from '@theia/editor/lib/browser';
import { inject, injectable } from 'inversify';

import { WorkflowGLSPClientContribution } from '../language/workflow-glsp-client-contribution';

@injectable()
export class WorkflowGLSPDiagramClient extends GLSPDiagramClient {
    constructor(
        @inject(WorkflowGLSPClientContribution) glspCLientContribution: WorkflowGLSPClientContribution,
        @inject(EditorManager) editorManager: EditorManager
    ) {
        super(glspCLientContribution, editorManager);
    }
}
