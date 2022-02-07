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
import { configureCommand, TYPES } from 'sprotty';

import { GLSP_TYPES } from '../../base/types';
import { SelectAllFeedbackCommand, SelectFeedbackCommand } from './select-feedback-action';
import { RankedSelectMouseListener } from './select-mouse-listener';
import { SelectAllCommand, SelectCommand, SelectionService } from './selection-service';

const glspSelectModule = new ContainerModule((bind, _unbind, isBound) => {
    bind(SelectionService).toSelf().inSingletonScope();
    bind(GLSP_TYPES.SelectionService).toService(SelectionService);
    configureCommand({ bind, isBound }, SelectCommand);
    configureCommand({ bind, isBound }, SelectAllCommand);
    configureCommand({ bind, isBound }, SelectFeedbackCommand);
    configureCommand({ bind, isBound }, SelectAllFeedbackCommand);
    bind(TYPES.MouseListener).to(RankedSelectMouseListener);
    bind(GLSP_TYPES.SModelRootListener).toService(SelectionService);
});

export default glspSelectModule;
