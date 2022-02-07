/********************************************************************************
 * Copyright (C) 2019-2021 EclipseSource and others.
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
import { bindContributionProvider, ConnectionHandler, ILogger, JsonRpcConnectionHandler } from '@theia/core/lib/common';
import { MessagingService } from '@theia/core/lib/node/messaging/messaging-service';
import { ContainerModule } from '@theia/core/shared/inversify';

import { GLSPContribution } from '../common';
import { GLSPBackendContribution } from './glsp-backend-contribution';
import { GLSPServerContribution } from './glsp-server-contribution';

export default new ContainerModule(bind => {
    bind(GLSPBackendContribution).toSelf().inSingletonScope();
    bind(MessagingService.Contribution).toService(GLSPBackendContribution);
    bind(GLSPContribution.Service).toService(GLSPBackendContribution);
    bindContributionProvider(bind, GLSPServerContribution);

    bind(ConnectionHandler)
        .toDynamicValue(
            ctx => new JsonRpcConnectionHandler(GLSPContribution.servicePath, () => ctx.container.get(GLSPContribution.Service))
        )
        .inSingletonScope();

    bind(ILogger)
        .toDynamicValue(ctx => {
            const logger = ctx.container.get<ILogger>(ILogger);
            return logger.child('glsp');
        })
        .inSingletonScope()
        .whenTargetNamed('glsp');
});
