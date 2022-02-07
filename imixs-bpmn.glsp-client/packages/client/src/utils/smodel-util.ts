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
import { ElementAndRoutingPoints } from '@eclipse-glsp/protocol';
import {
    BoundsAware,
    ElementAndBounds,
    isBoundsAware,
    isMoveable,
    isSelectable,
    isSelected,
    Selectable,
    SModelElement,
    SModelElementSchema,
    SModelIndex,
    SRoutableElement,
    SRoutingHandle
} from 'sprotty';

export function getIndex(element: SModelElement): SModelIndex<SModelElement> {
    return element.root.index;
}

export function forEachElement<T>(
    element: SModelElement,
    predicate: (modelElement: SModelElement) => modelElement is SModelElement & T,
    runnable: (modelElement: SModelElement & T) => void
): void {
    getIndex(element).all().filter(predicate).forEach(runnable);
}

export function getMatchingElements<T>(
    element: SModelElement,
    predicate: (modelElement: SModelElement) => modelElement is SModelElement & T
): (SModelElement & T)[] {
    const matching: (SModelElement & T)[] = [];
    forEachElement(element, predicate, item => matching.push(item));
    return matching;
}

export function hasSelectedElements(element: SModelElement): boolean {
    return getSelectedElementCount(element) > 0;
}

export function getSelectedElementCount(element: SModelElement): number {
    let selected = 0;
    getIndex(element)
        .all()
        .filter(isSelected)
        .forEach(e => (selected = selected + 1));
    return selected;
}

export function isNotUndefined<T>(element: T | undefined): element is T {
    return element !== undefined;
}

export function addCssClasses(root: SModelElement, cssClasses: string[]): void {
    if (root.cssClasses === undefined) {
        root.cssClasses = [];
    }
    for (const cssClass of cssClasses) {
        if (root.cssClasses.indexOf(cssClass) < 0) {
            root.cssClasses.push(cssClass);
        }
    }
}

export function removeCssClasses(root: SModelElement, cssClasses: string[]): void {
    if (root.cssClasses === undefined || root.cssClasses.length === 0) {
        return;
    }
    for (const cssClass of cssClasses) {
        const index = root.cssClasses.indexOf(cssClass);
        if (index !== -1) {
            root.cssClasses.splice(root.cssClasses.indexOf(cssClass), 1);
        }
    }
}

export function isNonRoutableSelectedMovableBoundsAware(element: SModelElement): element is SelectableBoundsAware {
    return isNonRoutableSelectedBoundsAware(element) && isMoveable(element);
}

export function isNonRoutableSelectedBoundsAware(element: SModelElement): element is SelectableBoundsAware {
    return isBoundsAware(element) && isSelected(element) && !isRoutable(element);
}

export function isRoutable<T extends SModelElement>(element: T): element is T & SRoutableElement {
    return element instanceof SRoutableElement && (element as any).routingPoints !== undefined;
}

export function isRoutingHandle(element: SModelElement | undefined): element is SRoutingHandle {
    return element !== undefined && element instanceof SRoutingHandle;
}

export function isSelectableAndBoundsAware(element: SModelElement): element is BoundsAware & Selectable & SModelElement {
    return isSelectable(element) && isBoundsAware(element);
}

export type SelectableBoundsAware = SModelElement & BoundsAware & Selectable;

export type BoundsAwareModelElement = SModelElement & BoundsAware;

export function toElementAndBounds(element: SModelElement & BoundsAware): ElementAndBounds {
    return {
        elementId: element.id,
        newPosition: {
            x: element.bounds.x,
            y: element.bounds.y
        },
        newSize: {
            width: element.bounds.width,
            height: element.bounds.height
        }
    };
}

export function toElementAndRoutingPoints(element: SRoutableElement): ElementAndRoutingPoints {
    return {
        elementId: element.id,
        newRoutingPoints: element.routingPoints
    };
}

/**
 * Checks if the model is compatible with the passed type string.
 * (either has the same type or a subtype of this type)
 */
export function hasCompatibleType(input: SModelElement | SModelElementSchema | string, type: string): boolean {
    const inputType = getElementTypeId(input);
    return inputType === type ? true : inputType.split(':').includes(type);
}

export function getElementTypeId(input: SModelElement | SModelElementSchema | string): string {
    if (typeof input === 'string') {
        return input as string;
    } else {
        return (input as any)['type'] as string;
    }
}
