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
import { MouseTool } from 'sprotty';

import { GLSP_TYPES } from '../../base/types';
import { RankingMouseTool } from './mouse-tool';

const glspMouseToolModule = new ContainerModule((bind, _unbind, _isBound, rebind) => {
    bind(RankingMouseTool).toSelf().inSingletonScope();
    bind(GLSP_TYPES.MouseTool).toService(RankingMouseTool);
    rebind(MouseTool).toService(RankingMouseTool);
});

export default glspMouseToolModule;
