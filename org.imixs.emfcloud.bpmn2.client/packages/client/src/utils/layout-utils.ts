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
import { Point } from '@eclipse-glsp/protocol';
import { BoundsAware, Dimension, ElementAndBounds, ElementMove, ModelLayoutOptions, SModelElement } from 'sprotty';
import { IMovementRestrictor } from '../features/change-bounds/movement-restrictor';

export function minWidth(element: SModelElement & BoundsAware): number {
    const layoutOptions = getLayoutOptions(element);
    if (layoutOptions !== undefined && typeof layoutOptions.minWidth === 'number') {
        return layoutOptions.minWidth;
    }
    return 1;
}

export function minHeight(element: SModelElement & BoundsAware): number {
    const layoutOptions = getLayoutOptions(element);
    if (layoutOptions !== undefined && typeof layoutOptions.minHeight === 'number') {
        return layoutOptions.minHeight;
    }
    return 1;
}

export function getLayoutOptions(element: SModelElement): ModelLayoutOptions | undefined {
    const layoutOptions = (element as any).layoutOptions;
    if (layoutOptions !== undefined) {
        return layoutOptions as ModelLayoutOptions;
    }
    return undefined;
}

export function isValidSize(element: SModelElement & BoundsAware, size: Dimension): boolean {
    return size.width >= minWidth(element) && size.height >= minHeight(element);
}

export function isValidMove(element: SModelElement & BoundsAware, newPosition: Point, movementRestrictor?: IMovementRestrictor): boolean {
    if (movementRestrictor) {
        return movementRestrictor.validate(newPosition, element);
    }
    return true;
}

export function toValidElementMove(
    element: SModelElement & BoundsAware,
    move: WriteableElementMove,
    movementRestrictor?: IMovementRestrictor
): WriteableElementMove | undefined {
    if (!isValidMove(element, move.toPosition, movementRestrictor)) {
        return;
    }
    return move;
}

export function toValidElementAndBounds(
    element: SModelElement & BoundsAware,
    bounds: WriteableElementAndBounds,
    movementRestrictor?: IMovementRestrictor
): WriteableElementAndBounds | undefined {
    if (!isValidMove(element, bounds.newPosition, movementRestrictor)) {
        return;
    }
    const elementMinWidth = minWidth(element);
    if (bounds.newSize.width < elementMinWidth) {
        bounds.newSize.width = elementMinWidth;
    }
    const elementMinHeight = minHeight(element);
    if (bounds.newSize.height < elementMinHeight) {
        bounds.newSize.height = elementMinHeight;
    }
    return bounds;
}

export interface WriteablePoint extends Point {
    x: number;
    y: number;
}

export interface WriteableElementMove extends ElementMove {
    fromPosition?: WriteablePoint;
    toPosition: WriteablePoint;
}

export interface WriteableDimension extends Dimension {
    width: number;
    height: number;
}

export interface WriteableElementAndBounds extends ElementAndBounds {
    newPosition: WriteablePoint;
    newSize: WriteableDimension;
}
