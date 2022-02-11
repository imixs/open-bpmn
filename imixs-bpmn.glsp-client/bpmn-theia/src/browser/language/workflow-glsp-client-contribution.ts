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
import { BaseGLSPClientContribution } from '@eclipse-glsp/theia-integration/lib/browser';
import { injectable } from 'inversify';

import { WorkflowLanguage } from '../../common/workflow-language';

export interface WorkflowInitializeOptions {
    timestamp: Date;
    message: string;
}

@injectable()
export class WorkflowGLSPClientContribution extends BaseGLSPClientContribution {
    readonly id = WorkflowLanguage.Id;
    readonly name = WorkflowLanguage.Name;
    readonly fileExtensions = [WorkflowLanguage.FileExtension];

    protected createInitializeOptions(): WorkflowInitializeOptions {
        return {
            timestamp: new Date(),
            message: 'Custom Options Available'
        };
    }
}
