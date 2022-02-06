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
import {
    BoundsAware,
    Hoverable,
    hoverFeedbackFeature,
    isBoundsAware,
    isMoveable,
    isSelectable,
    Locateable,
    SChildElement,
    Selectable,
    SModelElement,
    SParentElement
} from 'sprotty';

export const resizeFeature = Symbol('resizeFeature');

export interface Resizable extends BoundsAware, Selectable {}

export function isResizable(element: SModelElement): element is SParentElement & Resizable {
    return isBoundsAware(element) && isSelectable(element) && element instanceof SParentElement && element.hasFeature(resizeFeature);
}

// eslint-disable-next-line no-shadow
export enum ResizeHandleLocation {
    TopLeft = 'top-left',
    TopRight = 'top-right',
    BottomLeft = 'bottom-left',
    BottomRight = 'bottom-right'
}

export function isBoundsAwareMoveable(element: SModelElement): element is SModelElement & Locateable & BoundsAware {
    return isMoveable(element) && isBoundsAware(element);
}

export class SResizeHandle extends SChildElement implements Hoverable {
    static readonly TYPE = 'resize-handle';

    constructor(
        public readonly location?: ResizeHandleLocation,
        public readonly type: string = SResizeHandle.TYPE,
        public readonly hoverFeedback: boolean = false
    ) {
        super();
    }

    hasFeature(feature: symbol): boolean {
        return feature === hoverFeedbackFeature;
    }

    isNwSeResize(): boolean {
        return this.location === ResizeHandleLocation.TopLeft || this.location === ResizeHandleLocation.BottomRight;
    }

    isNeSwResize(): boolean {
        return this.location === ResizeHandleLocation.TopRight || this.location === ResizeHandleLocation.BottomLeft;
    }
}

export function addResizeHandles(element: SParentElement): void {
    removeResizeHandles(element);
    element.add(new SResizeHandle(ResizeHandleLocation.TopLeft));
    element.add(new SResizeHandle(ResizeHandleLocation.TopRight));
    element.add(new SResizeHandle(ResizeHandleLocation.BottomLeft));
    element.add(new SResizeHandle(ResizeHandleLocation.BottomRight));
}

export function removeResizeHandles(element: SParentElement): void {
    element.removeAll(child => child instanceof SResizeHandle);
}
