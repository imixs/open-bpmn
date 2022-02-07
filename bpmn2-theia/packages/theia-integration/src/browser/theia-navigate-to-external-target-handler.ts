/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
import { Action, Args, IActionHandler, isNavigateToExternalTargetAction } from '@eclipse-glsp/client/lib';
import { open, OpenerService } from '@theia/core/lib/browser/opener-service';
import URI from '@theia/core/lib/common/uri';
import { inject, injectable } from '@theia/core/shared/inversify';

@injectable()
export class TheiaNavigateToExternalTargetHandler implements IActionHandler {
    static JSON_OPENER_OPTIONS = 'jsonOpenerOptions';

    constructor(@inject(OpenerService) protected readonly openerService: OpenerService) {}

    handle(action: Action): void {
        if (isNavigateToExternalTargetAction(action)) {
            this.navigateTo(action.target.uri, action.target.args);
        }
    }

    /**
     * Opens the specified URI in Theia using Theia's opener service.
     *
     * If `args` contain a property `jsonOpenerOptions`, the string of this property will be parsed as JSON
     * and merged into the opener options. This allows GLSP servers to pass additional opener options, such
     * as a selection, etc.
     */
    async navigateTo(uri: string, args?: Args): Promise<void> {
        if (args && args[TheiaNavigateToExternalTargetHandler.JSON_OPENER_OPTIONS]) {
            args = { args, ...JSON.parse(args[TheiaNavigateToExternalTargetHandler.JSON_OPENER_OPTIONS].toString()) };
        }
        await open(this.openerService, new URI(uri), { ...args });
    }
}
