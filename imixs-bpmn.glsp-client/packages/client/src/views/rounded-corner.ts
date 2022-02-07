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
import { Dimension } from '@eclipse-glsp/protocol';
import { Hoverable, Selectable, SShapeElement } from 'sprotty';
import { CornerRadius } from '../utils/argument-utils';

export interface RoundedCorner {
    radiusX: number;
    radiusY: number;
}

export class RoundedCornerWrapper {
    protected _topLeftCorner: RoundedCorner;
    protected _topRightCorner: RoundedCorner;
    protected _bottomRightCorner: RoundedCorner;
    protected _bottomLeftCorner: RoundedCorner;

    constructor(public readonly element: SShapeElement & Hoverable & Selectable, public readonly cornerRadius: CornerRadius) {}

    get size(): Dimension {
        return this.element.size;
    }

    get topLeftCorner(): RoundedCorner {
        if (!this._topLeftCorner) {
            this._topLeftCorner = {
                radiusX: scaledRadius(this.cornerRadius.topLeft, this.element.size.width / 2),
                radiusY: scaledRadius(this.cornerRadius.topLeft, this.element.size.height / 2)
            };
        }
        return this._topLeftCorner;
    }

    get topRightCorner(): RoundedCorner {
        if (!this._topRightCorner) {
            this._topRightCorner = {
                radiusX: scaledRadius(this.cornerRadius.topRight, this.element.size.width / 2),
                radiusY: scaledRadius(this.cornerRadius.topRight, this.element.size.height / 2)
            };
        }
        return this._topRightCorner;
    }

    get bottomRightCorner(): RoundedCorner {
        if (!this._bottomRightCorner) {
            this._bottomRightCorner = {
                radiusX: scaledRadius(this.cornerRadius.bottomRight, this.element.size.width / 2),
                radiusY: scaledRadius(this.cornerRadius.bottomRight, this.element.size.height / 2)
            };
        }
        return this._bottomRightCorner;
    }

    get bottomLeftCorner(): RoundedCorner {
        if (!this._bottomLeftCorner) {
            this._bottomLeftCorner = {
                radiusX: scaledRadius(this.cornerRadius.bottomLeft, this.element.size.width / 2),
                radiusY: scaledRadius(this.cornerRadius.bottomLeft, this.element.size.height / 2)
            };
        }
        return this._bottomLeftCorner;
    }
}

/*
 * Scales the radius if necessary. (Percentual downscaling if the radius is bigger then the maximal allowed length)
 */
export function scaledRadius(radius: number, maximalLength: number): number {
    if (radius <= maximalLength) {
        return radius;
    } else {
        return radius * (maximalLength / radius);
    }
}
