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
import { NavigateToTargetAction, NavigationTarget } from '@eclipse-glsp/client/lib';
import { OpenerOptions } from '@theia/core/lib/browser';
import { injectable } from '@theia/core/shared/inversify';
import { Range } from '@theia/editor/lib/browser';

/**
 * Service for translating `OpenerOptions` into a `NavigateToTargetAction`.
 *
 * This service is typically used to determine requested selections from Theia
 * opener options on open or reveal of a diagram widget. Diagram widgets can
 * then dispatch the determined diagram navigation actions once the widget is
 * initialized.
 *
 * This service will forward the `selection` property of the `OpenerOptions`
 * in the form of arguments in the issued `NavigateToTargetAction` as follows:
 *   *(a)* the property `elementIds: string[]` as element ids
 *   *(b)* `start` with `line: number` and `character: number`
 *   *(c)* any simple-typed properties that are direct properties of `selection`
 */
@injectable()
export class TheiaOpenerOptionsNavigationService {
    determineNavigations(uri: string, options?: OpenerOptions): NavigateToTargetAction[] {
        if (OptionsWithSelection.is(options)) {
            const selection = options.selection;
            const navigationTarget = { uri };
            Object.keys(selection).forEach(key => {
                const value = selection[key];
                if (typeof value !== 'object') {
                    NavigationTarget.addArgument(navigationTarget, key, value);
                }
            });
            if (SelectionWithElementIds.is(selection)) {
                NavigationTarget.setElementIds(navigationTarget, selection.elementIds);
            }
            if (Range.is(selection)) {
                NavigationTarget.setTextPosition(navigationTarget, {
                    line: selection.start.line,
                    character: selection.start.character
                });
            }
            return [new NavigateToTargetAction(navigationTarget)];
        }
        return [];
    }
}

export interface OptionsWithSelection {
    readonly selection: any;
}

export namespace OptionsWithSelection {
    export function is(options: OpenerOptions | undefined): options is OptionsWithSelection {
        return options !== undefined && 'selection' in options;
    }
    export function elementId(options: OpenerOptions | undefined): string[] | undefined {
        if (!OptionsWithSelection.is(options)) {
            return undefined;
        }
        if (!SelectionWithElementIds.is(options.selection)) {
            return undefined;
        }
        return options.selection.elementIds;
    }
}

export interface SelectionWithElementIds {
    readonly elementIds: string[];
}

export namespace SelectionWithElementIds {
    export function is(selection: any | undefined): selection is SelectionWithElementIds {
        return selection !== undefined && 'elementIds' in selection;
    }
    export function createRange(elementIds: string[]): Range & SelectionWithElementIds {
        return { elementIds, start: { line: -1, character: -1 }, end: { line: -1, character: -1 } };
    }
}

export namespace SelectionWithRange {
    export function is(selection: any | undefined): selection is Range {
        return Range.is(selection);
    }
}
