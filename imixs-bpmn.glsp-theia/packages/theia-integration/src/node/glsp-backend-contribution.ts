/********************************************************************************
 * Copyright (C) 2018-2021 TypeFox and others.
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
import { ContributionProvider, ILogger } from '@theia/core/lib/common';
import { MessagingService } from '@theia/core/lib/node/messaging/messaging-service';
import { inject, injectable, named } from '@theia/core/shared/inversify';

import { GLSPContribution } from '../common';
import { GLSPServerContribution, GLSPServerLaunchOptions } from './glsp-server-contribution';

@injectable()
export class GLSPBackendContribution implements MessagingService.Contribution, GLSPContribution.Service {
    @inject(ILogger)
    @named('glsp')
    protected readonly logger: ILogger;

    @inject(ContributionProvider)
    @named(GLSPServerContribution)
    protected readonly contributors: ContributionProvider<GLSPServerContribution>;

    protected nextId = 1;
    protected readonly sessions = new Map<string, any>();

    async create(_contributionId: string, startParameters: any): Promise<string> {
        const id = this.nextId;
        this.nextId++;
        const sessionId = String(id);
        this.sessions.set(sessionId, startParameters);
        return sessionId;
    }

    async destroy(sessionId: string): Promise<void> {
        this.sessions.delete(sessionId);
    }

    configure(service: MessagingService): void {
        for (const contribution of this.contributors.getContributions()) {
            const path = GLSPContribution.getPath(contribution);
            if (GLSPServerLaunchOptions.shouldLaunchOnApplicationStart(contribution)) {
                contribution.launch!().then(() => this.forward(service, path, contribution));
            } else {
                this.forward(service, path, contribution);
            }
        }
    }

    protected forward(service: MessagingService, path: string, contribution: GLSPServerContribution): void {
        service.forward(path, async ({ id }: { id: string }, connection) => {
            try {
                connection.onClose(() => this.destroy(id));
                await contribution.connect(connection);
            } catch (e) {
                this.logger.error(`Error occurred while starting GLSP contribution. ${path}.`, e);
            }
        });
    }
}
