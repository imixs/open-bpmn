/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
import { GLSPClient } from '@eclipse-glsp/client';
import { ContributionProvider } from '@theia/core';
import { inject, injectable, named } from '@theia/core/shared/inversify';
import { GLSPClientContribution } from './glsp-client-contribution';

export const GLSPClientProvider = Symbol.for('GLSPClientProvider');

export interface GLSPClientProvider {
    getLanguageClient(languageId: string): Promise<GLSPClient | undefined>;
}

@injectable()
export class GLSPClientProviderImpl implements GLSPClientProvider {
    @inject(ContributionProvider)
    @named(GLSPClientContribution)
    private readonly contributions: ContributionProvider<GLSPClientContribution>;

    async getLanguageClient(languageId: string): Promise<GLSPClient | undefined> {
        const contribution = this.getLanguageContribution(languageId);
        if (contribution) {
            return contribution.glspClient;
        }
        return undefined;
    }

    protected getLanguageContribution(languageId: string): GLSPClientContribution | undefined {
        const contributions = this.contributions.getContributions();
        if (contributions) {
            for (const contribution of contributions) {
                if (contribution.id === languageId) {
                    return contribution;
                }
            }
        }
        return undefined;
    }
}
