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
import { SelectMouseListener } from 'sprotty';

import { DEFAULT_RANK, Ranked } from '../rank/model';

/**
 * Ranked select mouse listener that is executed before default mouse listeners when using the RankedMouseTool.
 * This ensures that default mouse listeners are working on a model that has selection changes already applied.
 */
export class RankedSelectMouseListener extends SelectMouseListener implements Ranked {
    rank: number = DEFAULT_RANK - 1; /* we want to be executed before all default mouse listeners */
}
