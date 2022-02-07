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
import { Action, EditorContextService } from '@eclipse-glsp/client';
import { ApplicationShell } from '@theia/core/lib/browser';
import { DiagramCommandHandler } from 'sprotty-theia';

import { GLSPDiagramWidget } from './glsp-diagram-widget';

export interface GLSPCommand {
    actions: (context: EditorContextService) => Action[];
    isEnabled?: (context: EditorContextService) => boolean;
    isVisible?: (context: EditorContextService) => boolean;
    isToggled?: (context: EditorContextService) => boolean;
}

export class GLSPCommandHandler extends DiagramCommandHandler {
    constructor(shell: ApplicationShell, protected readonly command: GLSPCommand) {
        super(shell, () => this.dispatch());
    }

    protected dispatch(): Promise<void> | undefined {
        return (
            this.glspDiagramWidget &&
            this.glspDiagramWidget.actionDispatcher.dispatchAll(this.command.actions(this.glspDiagramWidget.editorContext))
        );
    }

    get glspDiagramWidget(): GLSPDiagramWidget | undefined {
        return this.diagramWidget instanceof GLSPDiagramWidget ? (this.diagramWidget as GLSPDiagramWidget) : undefined;
    }

    isEnabled(): boolean {
        return (
            super.isEnabled() &&
            this.glspDiagramWidget !== undefined &&
            (this.command.isEnabled === undefined || this.command.isEnabled(this.glspDiagramWidget.editorContext))
        );
    }

    isVisible(): boolean {
        return (
            this.glspDiagramWidget !== undefined &&
            (this.command.isVisible === undefined || this.command.isVisible(this.glspDiagramWidget.editorContext))
        );
    }

    isToggled(): boolean {
        return (
            this.glspDiagramWidget !== undefined &&
            this.command.isToggled !== undefined &&
            this.command.isToggled(this.glspDiagramWidget.editorContext)
        );
    }
}
