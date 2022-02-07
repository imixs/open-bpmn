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
import { inject, injectable } from 'inversify';
import {
    Command,
    CommandExecutionContext,
    SelectAllCommand as SprottySelectAllCommand,
    SelectCommand as SprottySelectCommand,
    SModelRoot,
    TYPES
} from 'sprotty';

export class SelectFeedbackAction {
    constructor(
        public readonly selectedElementsIDs: string[] = [],
        public readonly deselectedElementsIDs: string[] = [],
        public readonly kind: string = SelectFeedbackCommand.KIND
    ) {}
}

export class SelectAllFeedbackAction {
    /**
     * If `select` is true, all elements are selected, othewise they are deselected.
     */
    constructor(public readonly select: boolean = true, public readonly kind: string = SelectFeedbackCommand.KIND) {}
}

@injectable()
export class SelectFeedbackCommand extends Command {
    static readonly KIND = 'elementSelectedFeedback';
    private sprottySelectCommand: SprottySelectCommand;

    constructor(@inject(TYPES.Action) public action: SelectFeedbackAction) {
        super();
        this.sprottySelectCommand = new SprottySelectCommand(action);
    }

    execute(context: CommandExecutionContext): SModelRoot {
        return this.sprottySelectCommand.execute(context);
    }

    undo(context: CommandExecutionContext): SModelRoot {
        return this.sprottySelectCommand.undo(context);
    }

    redo(context: CommandExecutionContext): SModelRoot {
        return this.sprottySelectCommand.redo(context);
    }
}

@injectable()
export class SelectAllFeedbackCommand extends Command {
    static readonly KIND = 'allSelectedFeedback';
    private sprottySelectAllCommand: SprottySelectAllCommand;

    constructor(@inject(TYPES.Action) public action: SelectAllFeedbackAction) {
        super();
        this.sprottySelectAllCommand = new SprottySelectAllCommand(action);
    }

    execute(context: CommandExecutionContext): SModelRoot {
        return this.sprottySelectAllCommand.execute(context);
    }

    undo(context: CommandExecutionContext): SModelRoot {
        return this.sprottySelectAllCommand.undo(context);
    }

    redo(context: CommandExecutionContext): SModelRoot {
        return this.sprottySelectAllCommand.redo(context);
    }
}
