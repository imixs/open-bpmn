/********************************************************************************
 * Copyright (C) 2017 TypeFox and others.
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

import * as React from 'react';
import { injectable } from 'inversify';
import { GettingStartedWidget } from '@theia/getting-started/lib/browser/getting-started-widget';

/**
 * The `CustomGettingStartedWidget` extends the base `GettingStartedWidget`
 * by simply updating the method `renderHeader()` to display some other
 * content than that of the base class.
 */
@injectable() // Informs `Inversify` that the following class can be injected into a `Container`.
export class CustomGettingStartedWidget extends GettingStartedWidget {
    /**
     * The `renderHeader` method overrides that of the base class.
     */
    protected renderHeader(): React.ReactNode {
        return (
            <div className='gs-header'>
                <h1>Open BPMN</h1>
            </div>
        );
    }
}