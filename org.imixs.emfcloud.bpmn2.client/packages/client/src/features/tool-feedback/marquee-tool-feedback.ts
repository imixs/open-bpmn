/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
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
import { Action, Point } from '@eclipse-glsp/protocol';
import { inject, injectable } from 'inversify';
import {
    AnchorComputerRegistry,
    CommandExecutionContext,
    CommandReturn,
    MouseListener,
    SChildElement,
    SModelElement,
    SModelRoot,
    TYPES
} from 'sprotty';
import { FeedbackCommand } from './model';

export class DrawMarqueeAction implements Action {
    constructor(
        public readonly startPoint: Point,
        public readonly endPoint: Point,
        public readonly kind: string = DrawMarqueeCommand.KIND
    ) {}
}

@injectable()
export class DrawMarqueeCommand extends FeedbackCommand {
    static readonly KIND = 'drawMarquee';

    constructor(@inject(TYPES.Action) protected action: DrawMarqueeAction) {
        super();
    }

    execute(context: CommandExecutionContext): CommandReturn {
        drawMarquee(context, this.action.startPoint, this.action.endPoint);
        return context.root;
    }
}

export class RemoveMarqueeAction implements Action {
    constructor(public readonly kind: string = RemoveMarqueeCommand.KIND) {}
}

@injectable()
export class RemoveMarqueeCommand extends FeedbackCommand {
    static readonly KIND = 'removeMarqueeCommand';

    execute(context: CommandExecutionContext): CommandReturn {
        removeMarquee(context.root);
        return context.root;
    }
}

export class MarqueeEndMovingMouseListener extends MouseListener {
    constructor(protected anchorRegistry: AnchorComputerRegistry) {
        super();
    }

    mouseMove(target: SModelElement, event: MouseEvent): Action[] {
        return [];
    }
}

export function marqueeId(root: SModelRoot): string {
    return root.id + '_' + MARQUEE;
}

export const MARQUEE = 'marquee';

export function drawMarquee(context: CommandExecutionContext, startPoint: Point, endPoint: Point): void {
    const root = context.root;

    removeMarquee(root);

    const marqueeSchema = {
        type: MARQUEE,
        id: marqueeId(root),
        startPoint: startPoint,
        endPoint: endPoint
    };

    const marquee = context.modelFactory.createElement(marqueeSchema);
    root.add(marquee);
}

export function removeMarquee(root: SModelRoot): void {
    const marquee = root.index.getById(marqueeId(root));
    if (marquee instanceof SChildElement) {
        root.remove(marquee);
    }
}
