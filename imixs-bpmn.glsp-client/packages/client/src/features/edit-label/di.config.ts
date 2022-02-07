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
import { ContainerModule } from 'inversify';
import { ApplyLabelEditCommand, configureCommand, TYPES } from 'sprotty';

import { GLSP_TYPES } from '../../base/types';
import { DirectLabelEditTool } from './edit-label-tool';
import { BalloonLabelValidationDecorator, ServerEditLabelValidator } from './edit-label-validator';

const glspEditLabelModule = new ContainerModule((bind, _unbind, isBound, _rebind) => {
    bind(TYPES.IEditLabelValidator).to(ServerEditLabelValidator);
    bind(TYPES.IEditLabelValidationDecorator).to(BalloonLabelValidationDecorator);
    bind(GLSP_TYPES.IDefaultTool).to(DirectLabelEditTool);
    configureCommand({ bind, isBound }, ApplyLabelEditCommand);
});

export default glspEditLabelModule;
