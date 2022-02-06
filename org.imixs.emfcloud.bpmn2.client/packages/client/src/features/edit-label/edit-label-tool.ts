/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
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
import { injectable } from 'inversify';
import { EditLabelKeyListener, EditLabelMouseListener, KeyListener, MouseListener } from 'sprotty';

import { BaseGLSPTool } from '../tools/base-glsp-tool';

@injectable()
export class DirectLabelEditTool extends BaseGLSPTool {
    static readonly ID = 'glsp.direct-label-edit-tool';

    protected editLabelMouseListener: MouseListener;
    protected editLabelKeyListener: KeyListener;

    get id(): string {
        return DirectLabelEditTool.ID;
    }

    protected createEditLabelMouseListener(): MouseListener {
        return new EditLabelMouseListener();
    }

    protected createEditLabelKeyListener(): KeyListener {
        return new EditLabelKeyListener();
    }

    enable(): void {
        this.editLabelKeyListener = this.createEditLabelKeyListener();
        this.editLabelMouseListener = this.createEditLabelMouseListener();
        this.mouseTool.register(this.editLabelMouseListener);
        this.keyTool.register(this.editLabelKeyListener);
    }

    disable(): void {
        this.keyTool.deregister(this.editLabelKeyListener);
        this.mouseTool.deregister(this.editLabelMouseListener);
    }
}
