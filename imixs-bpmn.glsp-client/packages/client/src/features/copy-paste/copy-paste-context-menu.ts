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
import { Action, GLSPServerStatusAction, Point, ServerMessageAction } from '@eclipse-glsp/protocol';
import { inject, injectable } from 'inversify';
import { IActionDispatcher, IActionHandler, IContextMenuItemProvider, isSelected, MenuItem, SModelRoot, TYPES } from 'sprotty';

export class InvokeCopyAction implements Action {
    static readonly KIND = 'invoke-copy';
    constructor(public readonly kind = InvokeCopyAction.KIND) {}
}

export class InvokePasteAction implements Action {
    static readonly KIND = 'invoke-paste';
    constructor(public readonly kind = InvokePasteAction.KIND) {}
}

export class InvokeCutAction implements Action {
    static readonly KIND = 'invoke-cut';
    constructor(public readonly kind = InvokeCutAction.KIND) {}
}

@injectable()
export class InvokeCopyPasteActionHandler implements IActionHandler {
    @inject(TYPES.IActionDispatcher) protected dispatcher: IActionDispatcher;
    handle(action: Action): void {
        switch (action.kind) {
            case InvokeCopyAction.KIND:
                if (supportsCopy()) {
                    document.execCommand('copy');
                } else {
                    this.notifyUserToUseShortcut('copy');
                }
                break;
            case InvokePasteAction.KIND:
                if (supportsPaste()) {
                    document.execCommand('paste');
                } else {
                    this.notifyUserToUseShortcut('paste');
                }
                break;
            case InvokeCutAction.KIND:
                if (supportsCut()) {
                    document.execCommand('cut');
                } else {
                    this.notifyUserToUseShortcut('cut');
                }
                break;
        }
    }

    protected notifyUserToUseShortcut(operation: string): void {
        const message = `Please use the browser's ${operation} command or shortcut.`;
        const timeout = 10000;
        const severity = 'WARNING';
        this.dispatcher.dispatchAll([
            { kind: GLSPServerStatusAction.KIND, severity, timeout, message } as GLSPServerStatusAction,
            { kind: ServerMessageAction.KIND, severity, timeout, message } as ServerMessageAction
        ]);
    }
}

@injectable()
export class CopyPasteContextMenuItemProvider implements IContextMenuItemProvider {
    getItems(root: Readonly<SModelRoot>, _lastMousePosition?: Point): Promise<MenuItem[]> {
        const hasSelectedElements = Array.from(root.index.all().filter(isSelected)).length > 0;
        return Promise.resolve([
            this.createCopyMenuItem(hasSelectedElements),
            this.createCutMenuItem(hasSelectedElements),
            this.createPasteMenuItem()
        ]);
    }

    protected createPasteMenuItem(): MenuItem {
        return {
            id: 'paste',
            label: 'Paste',
            group: 'copy-paste',
            actions: [new InvokePasteAction()],
            isEnabled: () => true
        };
    }

    protected createCutMenuItem(hasSelectedElements: boolean): MenuItem {
        return {
            id: 'cut',
            label: 'Cut',
            group: 'copy-paste',
            actions: [new InvokeCutAction()],
            isEnabled: () => hasSelectedElements
        };
    }

    protected createCopyMenuItem(hasSelectedElements: boolean): MenuItem {
        return {
            id: 'copy',
            label: 'Copy',
            group: 'copy-paste',
            actions: [new InvokeCopyAction()],
            isEnabled: () => hasSelectedElements
        };
    }
}

export function supportsCopy(): boolean {
    return isNative() || document.queryCommandSupported('copy');
}

export function supportsCut(): boolean {
    return isNative() || document.queryCommandSupported('cut');
}

export function supportsPaste(): boolean {
    const isChrome = userAgent().indexOf('Chrome') >= 0;
    return isNative() || (!isChrome && document.queryCommandSupported('paste'));
}

export function isNative(): boolean {
    return typeof (window as any).process !== 'undefined';
}

function userAgent(): string {
    return typeof navigator !== 'undefined' ? navigator.userAgent : '';
}
