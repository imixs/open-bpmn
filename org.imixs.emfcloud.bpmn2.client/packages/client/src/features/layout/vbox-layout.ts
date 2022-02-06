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
import { injectable } from 'inversify';
import {
    Bounds,
    BoundsData,
    Dimension,
    EMPTY_BOUNDS,
    isBoundsAware,
    isLayoutableChild,
    isValidDimension,
    LayoutContainer,
    Point,
    SChildElement,
    SModelElement,
    SParentElement,
    StatefulLayouter,
    VBoxLayouter,
    VBoxLayoutOptions
} from 'sprotty';

export interface VBoxLayoutOptionsExt extends VBoxLayoutOptions {
    hGrab: boolean;
    vGrab: boolean;
    prefWidth: number | null;
    prefHeight: number | null;
}

/**
 * Extends VBoxLayouter to support additional layout options
 */
@injectable()
export class VBoxLayouterExt extends VBoxLayouter {
    static KIND = VBoxLayouter.KIND;

    layout(container: SParentElement & LayoutContainer, layouter: StatefulLayouter): void {
        const boundsData = layouter.getBoundsData(container);
        const options = this.getLayoutOptions(container);
        const childrenSize = this.getChildrenSize(container, options, layouter);

        const fixedSize = this.getFixedContainerBounds(container, options, layouter);

        const maxWidth =
            options.paddingFactor *
            (options.resizeContainer
                ? Math.max(fixedSize.width - options.paddingLeft - options.paddingRight, childrenSize.width)
                : Math.max(0, fixedSize.width - options.paddingLeft - options.paddingRight));
        const maxHeight =
            options.paddingFactor *
            (options.resizeContainer
                ? Math.max(fixedSize.height - options.paddingTop - options.paddingBottom, childrenSize.height)
                : Math.max(0, fixedSize.height - options.paddingTop - options.paddingBottom));

        // Remaining size that can be grabbed by children with the vGrab option
        const grabHeight: number = maxHeight - childrenSize.height;
        // Number of children that request vGrab
        // FIXME: This approach works fine when only 1 child uses VGrab, but may cause rounding issues
        // when the grabHeight can't be equally shared by all children.
        const grabbingChildren = container.children
            .map(child => this.getChildLayoutOptions(child, options))
            .filter(opt => opt.vGrab).length;

        if (maxWidth > 0 && maxHeight > 0) {
            const offset = this.layoutChildren(container, layouter, options, maxWidth, maxHeight, grabHeight, grabbingChildren);
            boundsData.bounds = this.getFinalContainerBounds(container, offset, options, childrenSize.width, childrenSize.height);
            boundsData.boundsChanged = true;
        }
    }

    protected getChildrenSize(
        container: SParentElement & LayoutContainer,
        containerOptions: VBoxLayoutOptionsExt,
        layouter: StatefulLayouter
    ): Dimension {
        let maxWidth = -1;
        let maxHeight = 0;
        let isFirst = true;
        container.children.forEach(child => {
            if (isLayoutableChild(child)) {
                const bounds = layouter.getBoundsData(child).bounds;
                if (bounds !== undefined && isValidDimension(bounds)) {
                    maxHeight += bounds.height;
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        maxHeight += containerOptions.vGap;
                    }
                    maxWidth = Math.max(maxWidth, bounds.width);
                }
            }
        });
        const result = {
            width: maxWidth,
            height: maxHeight
        };
        return result;
    }

    protected layoutChildren(
        container: SParentElement & LayoutContainer,
        layouter: StatefulLayouter,
        containerOptions: VBoxLayoutOptionsExt,
        maxWidth: number,
        maxHeight: number,
        grabHeight?: number,
        grabbingChildren?: number
    ): Point {
        let currentOffset: Point = {
            x: containerOptions.paddingLeft + 0.5 * (maxWidth - maxWidth / containerOptions.paddingFactor),
            y: containerOptions.paddingTop + 0.5 * (maxHeight - maxHeight / containerOptions.paddingFactor)
        };

        container.children.forEach(child => {
            if (isLayoutableChild(child)) {
                const boundsData = layouter.getBoundsData(child);
                const bounds = boundsData.bounds;
                const childOptions = this.getChildLayoutOptions(child, containerOptions);
                if (bounds !== undefined && isValidDimension(bounds)) {
                    currentOffset = this.layoutChild(
                        child,
                        boundsData,
                        bounds,
                        childOptions,
                        containerOptions,
                        currentOffset,
                        maxWidth,
                        maxHeight,
                        grabHeight,
                        grabbingChildren
                    );
                }
            }
        });
        return currentOffset;
    }

    protected layoutChild(
        child: SChildElement,
        boundsData: BoundsData,
        bounds: Bounds,
        childOptions: VBoxLayoutOptionsExt,
        containerOptions: VBoxLayoutOptionsExt,
        currentOffset: Point,
        maxWidth: number,
        maxHeight: number,
        grabHeight?: number,
        grabbingChildren?: number
    ): Point {
        let offset = super.layoutChild(child, boundsData, bounds, childOptions, containerOptions, currentOffset, maxWidth, maxHeight);
        if (childOptions.hGrab) {
            boundsData.bounds = {
                x: boundsData.bounds!.x,
                y: boundsData.bounds!.y,
                width: maxWidth,
                height: boundsData.bounds!.height
            };
            boundsData.boundsChanged = true;
        }
        if (childOptions.vGrab && grabHeight && grabbingChildren) {
            const height = boundsData.bounds!.height + grabHeight / grabbingChildren;
            boundsData.bounds = {
                x: boundsData.bounds!.x,
                y: boundsData.bounds!.y,
                width: boundsData.bounds!.width,
                height: height
            };
            boundsData.boundsChanged = true;
            offset = { x: currentOffset.x, y: currentOffset.y + height };
        }
        return offset;
    }

    protected getFixedContainerBounds(container: SModelElement, layoutOptions: VBoxLayoutOptionsExt, layouter: StatefulLayouter): Bounds {
        const currentContainer = container;
        // eslint-disable-next-line no-constant-condition
        if (isBoundsAware(currentContainer)) {
            const bounds = currentContainer.bounds;
            const elementOptions = this.getElementLayoutOptions(currentContainer);
            const width = elementOptions?.prefWidth ?? 0;
            const height = elementOptions?.prefHeight ?? 0;
            return { ...bounds, width, height };
        }
        return EMPTY_BOUNDS;
    }

    protected getChildLayoutOptions(child: SChildElement, containerOptions: VBoxLayoutOptionsExt): VBoxLayoutOptionsExt {
        return super.getChildLayoutOptions(child, containerOptions) as VBoxLayoutOptionsExt;
    }

    protected getLayoutOptions(element: SModelElement): VBoxLayoutOptionsExt {
        return super.getLayoutOptions(element) as VBoxLayoutOptionsExt;
    }

    protected getElementLayoutOptions(element: SModelElement): VBoxLayoutOptionsExt | undefined {
        return (element as any).layoutOptions;
    }

    protected getFinalContainerBounds(
        container: SParentElement & LayoutContainer,
        lastOffset: Point,
        options: VBoxLayoutOptionsExt,
        maxWidth: number,
        maxHeight: number
    ): Bounds {
        const elementOptions = this.getElementLayoutOptions(container);
        const width = elementOptions?.prefWidth ?? options.minWidth;
        const height = elementOptions?.prefHeight ?? options.minHeight;

        const result = {
            x: container.bounds.x,
            y: container.bounds.y,
            width: Math.max(width, maxWidth + options.paddingLeft + options.paddingRight),
            height: Math.max(height, maxHeight + options.paddingTop + options.paddingBottom)
        };

        return result;
    }

    protected getDefaultLayoutOptions(): VBoxLayoutOptionsExt {
        return {
            resizeContainer: true,
            paddingTop: 5,
            paddingBottom: 5,
            paddingLeft: 5,
            paddingRight: 5,
            paddingFactor: 1,
            vGap: 1,
            hAlign: 'center',
            minWidth: 0,
            minHeight: 0,
            hGrab: false,
            vGrab: false,
            // eslint-disable-next-line no-null/no-null
            prefHeight: null,
            // eslint-disable-next-line no-null/no-null
            prefWidth: null
        };
    }

    protected spread(a: VBoxLayoutOptionsExt, b: VBoxLayoutOptionsExt): VBoxLayoutOptionsExt {
        return { ...a, ...b };
    }
}
