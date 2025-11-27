/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
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
    Bounds,
    CornerRadius,
    Diamond,
    EdgeRouterRegistry,
    GNode, GPort,
    GShapeElement,
    Hoverable,
    IView,
    IViewArgs,
    Point,
    RenderingContext,
    RoundedCornerWrapper,
    SGraphImpl,
    Selectable,
    ShapeView,
    ZoomMouseListener,
    findParentByFeature,
    getSubType,
    isBoundsAware,
    setAttr,
    svg,
    toClipPathId
} from '@eclipse-glsp/client';
import {
    DataObjectNode,
    GatewayNode,
    Icon,
    MultiLineTextNode, TaskNode,
    isContainerNode
} from '@open-bpmn/open-bpmn-model';
import { inject, injectable } from 'inversify';
import { Classes, VNode } from 'snabbdom';

/****************************************************************************
 * This module provides BPMN element views like Tasks, Gateways, or Events
 *
 ****************************************************************************/
// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

/**
 * Grid View shows a zoomable grid
 */
@injectable()
export class BPMNGridView implements IView {
    @inject(EdgeRouterRegistry) edgeRouterRegistry: EdgeRouterRegistry;

    render(model: Readonly<SGraphImpl>, context: RenderingContext): VNode {
        const edgeRouting = this.edgeRouterRegistry.routeAllChildren(model);

        const gridBounds: Bounds = {
            x: model.scroll.x,
            y: model.scroll.y,
            width: Math.max(10, model.canvasBounds.width / model.zoom),
            height: Math.max(10, model.canvasBounds.height / model.zoom)
        };

        const transform = `scale(${model.zoom}) translate(${-model.scroll.x},${-model.scroll.y})`;
        return (
            <svg class-sprotty-graph={true}>
                <defs>
                    <pattern id={'bpmn-grid-' + model.id} x='-5' y='-5' width='10' height='10' patternUnits='userSpaceOnUse'>
                        <line x1="0" y1="5" x2="10" y2="5" class-bpmn-grid-line={true} />
                        <line x1="5" y1="0" x2="5" y2="10" class-bpmn-grid-line={true} />
                    </pattern>
                </defs>
                <g transform={transform}>
                    <rect
                        x={gridBounds.x}
                        y={gridBounds.y}
                        width={gridBounds.width}
                        height={gridBounds.height}
                        fill={'url(#bpmn-grid-' + model.id + ')'}
                    />
                    <g class-graph-content={true}>{context.renderChildren(model, { edgeRouting })}</g>
                </g>
            </svg>
        );
    }
}

/*
 * The BPMNZoomMouseListener overwrites the origin GLSP ZoomMouseListener
 * and changes the zoomFactor from 0.5 to 0.1
 */
@injectable()
export class BPMNZoomMouseListener extends ZoomMouseListener {
  protected override getZoomFactor(event: WheelEvent): number {
        if (event.deltaMode === event.DOM_DELTA_PAGE) {
            return Math.exp(-event.deltaY * 0.1);  // 0.5
        } else if (event.deltaMode === event.DOM_DELTA_LINE) {
            return Math.exp(-event.deltaY * 0.01); // 0.05
        } else { // deltaMode === DOM_DELTA_PIXEL
            return Math.exp(-event.deltaY * 0.001); // 0.005
        }
    }
}

/*
 * Deprecated: The IconView is no longer used but needed for di.config.
 */
@injectable()
export class IconView extends ShapeView {
    render(element: Icon, context: RenderingContext): VNode | undefined {
        return undefined;
    }
}

/*
 * The PoolHeaderView is used to show the label in a BPMNPool element
 *
 * The label is displayed vertical on the left side of the container
 */
@injectable()
export class PoolHeaderView extends ShapeView {
    render(element: Readonly<GShapeElement & Hoverable>, context: RenderingContext): VNode | undefined {
        if (!this.isVisible(element, context)) {
            return undefined;
        }
        const containerNode = findParentByFeature(element, isContainerNode);
        let headerHeight = 0;
        let labelYOffset = 0;
        let containerLabel = 'undefined';
        // we center the label vertical to the height of the container
        if (containerNode) {
            containerLabel = containerNode.name;
            headerHeight = containerNode.bounds.height - 0;
            labelYOffset = containerNode.bounds.height * 0.5;
        }
        const vnode: any = (
            <g>
                <rect x="0" y="0" width="30" height={headerHeight}></rect>
                <text class-sprotty-label={true}
                    transform={'scale(1),translate(20,' + labelYOffset + '),rotate(-90)'}>{containerLabel}</text>
            </g>
        );
        const subType = getSubType(element);
        if (subType) {
            setAttr(vnode, 'class', subType);
        }
        return vnode;
    }
}

/*
 * The LaneHeaderView is used to show the label in a BPMNLane element
 *
 * The label is displayed vertical on the left side of the container.
 * The y-offset is 1 point!.
 */
@injectable()
export class LaneHeaderView extends ShapeView {
    render(element: GShapeElement, context: RenderingContext): VNode | undefined {
        if (!this.isVisible(element, context)) {
            return undefined;
        }
        const containerNode = findParentByFeature(element, isContainerNode);
        let headerHeight = 0;
        let labelYOffset = 0;
        let containerLabel = 'undefined';
        // we center the label vertical to the height of the container
        if (containerNode) {
            containerLabel = containerNode.name;
            headerHeight = containerNode.bounds.height - 0;
            labelYOffset = containerNode.bounds.height * 0.5;
        }
        const vnode: any = (
            <g class-sprotty-node={element instanceof GNode}>
                <rect class-sprotty-node={element instanceof GNode}
                    x="0" y="1" width="30" height={headerHeight - 2}></rect>
                <text class-sprotty-label={true}
                    transform={'scale(1),translate(20,' + labelYOffset + '),rotate(-90)'}>{containerLabel}</text>
            </g>
        );
        const subType = getSubType(element);
        if (subType) {
            setAttr(vnode, 'class', subType);
        }
        return vnode;
    }
}

/*
 * Render a Divider to change the height of a BPMN Lane
 * The divider shows a line that marks the border of a two lanes.
 */
@injectable()
export class LaneDividerView extends ShapeView {
    render(node: Readonly<GShapeElement & Hoverable & Selectable>, context: RenderingContext, args?: IViewArgs): VNode | undefined {
        if (!this.isVisible(node, context)) {
            return undefined;
        }
        let laneWidth = 200;
        const parent = node.parent;
        if (isBoundsAware(parent)) {
            laneWidth = parent.bounds.width;
        }
        const dividerBorder = '0,1 ' + (laneWidth - 30) + ' 1';
        return <g>
            <rect x="1" y="-10" width={laneWidth - 32} height="20"></rect>
            <polyline points={dividerBorder} />
            {context.renderChildren(node)}
        </g>;
    }
}

/*
 * Render a BPMN Task element
 * A ActivityNodeView contains an optional icon and an extension text
 *
 * The implementation is a variant form the RoundedCornerNodeView but customizes the content.
 *
 * SShapeElement
 */
@injectable()
export class TaskNodeView extends ShapeView {
    render(node: Readonly<TaskNode & Hoverable & Selectable>, context: RenderingContext, args?: IViewArgs): VNode | undefined {
        if (!this.isVisible(node, context)) {
            return undefined;
        }
        const cornerRadius = new CornerRadius(5);
        // extract attribute info
        const infoTextYOffset = node.bounds.height - 4;
        let infoText = '';
        if (node.args.bpmnExtensionInfo) {
            infoText = '' + node.args.bpmnExtensionInfo;
        }
        const wrapper = new RoundedCornerWrapper(node, cornerRadius);
        return (
            <g class-node={true}>
                <defs>
                    <clipPath id={toClipPathId(node)}>
                        <path d={this.renderPath(wrapper, context, 2)}></path>
                    </clipPath>
                </defs>
                {this.renderPathNode(wrapper, context)}
                {this.computeIconPath(node)}
                <g class-extension={true}>
                    <text transform={'translate(3,' + infoTextYOffset + ')'}>{infoText}</text>
                </g>
                {context.renderChildren(node)}
            </g>
        );
    }

    // Helper method to render a rounded border
    protected renderPathNode(wrapper: Readonly<RoundedCornerWrapper>, context: RenderingContext): VNode {
        return (
            <path
                d={this.renderPath(wrapper, context, 0)}
                class-sprotty-node={wrapper.element instanceof GNode}
                class-sprotty-port={wrapper.element instanceof GPort}
                class-mouseover={wrapper.element.hoverFeedback}
                class-selected={wrapper.element.selected}
                {...this.additionalClasses(wrapper.element, context)}
            />
        );
    }

    // Helper method to ..?
    protected additionalClasses(_node: Readonly<GShapeElement & Hoverable & Selectable>, _context: RenderingContext): Classes {
        return {};
    }

    // Helper method to render a rounded border path
    protected renderPath(wrapper: Readonly<RoundedCornerWrapper>, _context: RenderingContext, inset: number): string {
        // Calculate length of straight line segments
        const topLineLength = Math.max(0, wrapper.size.width - wrapper.cornerRadius.topLeft - wrapper.cornerRadius.topRight);
        const rightLineLength = Math.max(0, wrapper.size.height - wrapper.cornerRadius.topRight - wrapper.cornerRadius.bottomRight);
        const bottomLineLength = Math.max(0, wrapper.size.width - wrapper.cornerRadius.bottomLeft - wrapper.cornerRadius.bottomRight);

        const path =
            `M${0 + inset},${0 + wrapper.topLeftCorner.radiusY}` +
            `q${0},${-(wrapper.topLeftCorner.radiusY - inset)} ${wrapper.topLeftCorner.radiusX - inset},${-(
                wrapper.topLeftCorner.radiusY - inset
            )}` +
            `h${topLineLength}` +
            `q${wrapper.topRightCorner.radiusX - inset},0 ${wrapper.topRightCorner.radiusX - inset},${wrapper.topRightCorner.radiusY - inset
            }` +
            `v${rightLineLength}` +
            `q0,${wrapper.bottomRightCorner.radiusY - inset} 
                ${-(wrapper.bottomRightCorner.radiusX - inset)},${wrapper.bottomRightCorner.radiusY - inset
            }` +
            `h${-bottomLineLength}` +
            `q${-(wrapper.bottomLeftCorner.radiusX - inset)},0 ${-(wrapper.bottomLeftCorner.radiusX - inset)},${-(
                wrapper.bottomLeftCorner.radiusY - inset
            )}` +
            'z ';
        return path;
    }

    // This helper method computes the icon path for TaskNodes depending on its type.
    protected computeIconPath(taskNode: Readonly<GShapeElement & TaskNode>): any {
        let icon;

        if (taskNode.args.bpmnSymbol) {
            icon = '' + taskNode.args.bpmnSymbol;
        }

        let vnode: any;
        if (!icon) {
            return undefined;
        } else {
            vnode = (
                <g class-symbol={true}>
                    <path transform={'scale(1),translate(5,5)'} d={icon} />
                </g>
            );
        }
        return vnode;
    }
}

/*
 * Render a BPMN Task element
 * A ActivityNodeView contains an optional icon and an extension text
 *
 * The implementation is a variant form the RoundedCornerNodeView but customizes the content.
 *
 * SShapeElement
 */
@injectable()
export class SubTaskNodeView extends ShapeView {
    render(node: Readonly<TaskNode & Hoverable & Selectable>, context: RenderingContext, args?: IViewArgs): VNode | undefined {
        if (!this.isVisible(node, context)) {
            return undefined;
        }
        const cornerRadius = new CornerRadius(5);
        // compute expand-icon position
        const infoTextXOffset = node.bounds.width *0.5-9;
        const infoTextYOffset = node.bounds.height -20;
        const wrapper = new RoundedCornerWrapper(node, cornerRadius);

        // expandIcon
        // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/ungroup-by-ref-type.svg
        // eslint-disable-next-line max-len
        let subProcessIcon='M2.9 1L5 3.1l-.8.7L3 2.6V7H2V2.5L.8 3.8l-.7-.7L2.2 1h.7zM3 13.4V9H2v4.4L.8 12.2l-.7.7L2.2 15h.7L5 12.9l-.7-.7L3 13.4zM8.5 7h-2L6 6.5v-2l.5-.5h2l.5.5v2l-.5.5zM7 6h1V5H7v1zm7.5 1h-3l-.5-.5v-3l.5-.5h3l.5.5v3l-.5.5zM12 6h2V4h-2v2zm-3.5 6h-2l-.5-.5v-2l.5-.5h2l.5.5v2l-.5.5zM7 11h1v-1H7v1zm7.5 2h-3l-.5-.5v-3l.5-.5h3l.5.5v3l-.5.5zM12 12h2v-2h-2v2zm-1-2H9v1h2v-1zm0-5H9v1h2V5z';
        // show embedded icon
        const argExpandableSubProcess=node.args.expandable_subprocess;
        const isExpandable='true'===argExpandableSubProcess;
        if (!isExpandable) {
          // eslint-disable-next-line max-len
          subProcessIcon='M1.5 1l-.5.5v3l.5.5h3l.5-.5v-3L4.5 1h-3zM2 4V2h2v2H2zm-.5 2l-.5.5v3l.5.5h3l.5-.5v-3L4.5 6h-3zM2 9V7h2v2H2zm-1 2.5l.5-.5h3l.5.5v3l-.5.5h-3l-.5-.5v-3zm1 .5v2h2v-2H2zm10.5-7l-.5.5v6l.5.5h3l.5-.5v-6l-.5-.5h-3zM15 8h-2V6h2v2zm0 3h-2V9h2v2zM9.1 8H6v1h3.1l-1 1 .7.6 1.8-1.8v-.7L8.8 6.3l-.7.7 1 1z';
        }
        return (
            <g class-node={true}>
                <defs>
                    <clipPath id={toClipPathId(node)}>
                        <path d={this.renderPath(wrapper, context, 2)}></path>
                    </clipPath>
                </defs>
                {this.renderPathNode(wrapper, context)}
                <rect class-mouseover={node.hoverFeedback} class-expanded={!isExpandable} class-collapsed={isExpandable}
                    x={infoTextXOffset} y={infoTextYOffset} width="15" height="15"></rect>
                <g class-symbol={true} class-mouseover={node.hoverFeedback}>
                    <path transform={'scale(1),translate(' + infoTextXOffset + ',' + infoTextYOffset + ')'} d={subProcessIcon}
                     style={{ pointerEvents: 'none' }} />
                </g> );
                {context.renderChildren(node)}
            </g>
        );
    }

    // Helper method to render a rounded border
    protected renderPathNode(wrapper: Readonly<RoundedCornerWrapper>, context: RenderingContext): VNode {
        return (
            <path
                d={this.renderPath(wrapper, context, 0)}
                class-sprotty-node={wrapper.element instanceof GNode}
                class-sprotty-port={wrapper.element instanceof GPort}
                class-mouseover={wrapper.element.hoverFeedback}
                class-selected={wrapper.element.selected}
                {...this.additionalClasses(wrapper.element, context)}
            />
        );
    }

    // Helper method to ..?
    protected additionalClasses(_node: Readonly<GShapeElement & Hoverable & Selectable>, _context: RenderingContext): Classes {
        return {};
    }

    // Helper method to render a rounded border path
    protected renderPath(wrapper: Readonly<RoundedCornerWrapper>, _context: RenderingContext, inset: number): string {
        // Calculate length of straight line segments
        const topLineLength = Math.max(0, wrapper.size.width - wrapper.cornerRadius.topLeft - wrapper.cornerRadius.topRight);
        const rightLineLength = Math.max(0, wrapper.size.height - wrapper.cornerRadius.topRight - wrapper.cornerRadius.bottomRight);
        const bottomLineLength = Math.max(0, wrapper.size.width - wrapper.cornerRadius.bottomLeft - wrapper.cornerRadius.bottomRight);

        const path =
            `M${0 + inset},${0 + wrapper.topLeftCorner.radiusY}` +
            `q${0},${-(wrapper.topLeftCorner.radiusY - inset)} ${wrapper.topLeftCorner.radiusX - inset},${-(
                wrapper.topLeftCorner.radiusY - inset
            )}` +
            `h${topLineLength}` +
            `q${wrapper.topRightCorner.radiusX - inset},0 ${wrapper.topRightCorner.radiusX - inset},${wrapper.topRightCorner.radiusY - inset
            }` +
            `v${rightLineLength}` +
            `q0,${wrapper.bottomRightCorner.radiusY - inset} 
                ${-(wrapper.bottomRightCorner.radiusX - inset)},${wrapper.bottomRightCorner.radiusY - inset
            }` +
            `h${-bottomLineLength}` +
            `q${-(wrapper.bottomLeftCorner.radiusX - inset)},0 ${-(wrapper.bottomLeftCorner.radiusX - inset)},${-(
                wrapper.bottomLeftCorner.radiusY - inset
            )}` +
            'z ';
        return path;
    }

    // This helper method computes the icon path for TaskNodes depending on its type.
    protected computeIconPath(taskNode: Readonly<GShapeElement & TaskNode>): any {
        let icon;

        if (taskNode.args.bpmnSymbol) {
            icon = '' + taskNode.args.bpmnSymbol;
        }

        let vnode: any;
        if (!icon) {
            return undefined;
        } else {
            vnode = (
                <g class-symbol={true}>
                    <path transform={'scale(1),translate(5,5)'} d={icon} />
                </g>
            );
        }
        return vnode;
    }
}

@injectable()
export class EventNodeView extends ShapeView {
    render(node: Readonly<GatewayNode & Hoverable & Selectable>, context: RenderingContext, args?: IViewArgs): VNode | undefined {
        if (!this.isVisible(node, context)) {
            return undefined;
        }
        const scaleFactor = 1.5;
        const translateX = 4.0;
        const translateY = 4.0;
        let icon = '';
        if (node.args.bpmnSymbol) {
            icon = '' + node.args.bpmnSymbol;
        }

        const radius = this.getRadius(node);
        return <g>
            <circle class-sprotty-node={node instanceof GNode} class-sprotty-port={node instanceof GPort}
                    class-mouseover={node.hoverFeedback} class-selected={node.selected}
                    r={radius} cx={radius} cy={radius}></circle>
            <g class-symbol={true}>
                <path transform={'scale(' + scaleFactor + '),translate(' + translateX + ',' + translateY + ')'} d={icon} />
            </g>
            {context.renderChildren(node)}
        </g>;
    }
    protected getRadius(node: GShapeElement): number {
        const d = Math.min(node.size.width, node.size.height);
        return d > 0 ? d / 2 : 0;
    }
}

@injectable()
export class GatewayNodeView extends ShapeView {
    render(node: Readonly<GatewayNode & Hoverable & Selectable>, context: RenderingContext, args?: IViewArgs): VNode | undefined {
        if (!this.isVisible(node, context)) {
            return undefined;
        }
        const scaleFactor = 1.5;
        const translateX = 9.0;
        const translateY = 9.0;
        let icon = '';
        if (node.args.bpmnSymbol) {
            icon = '' + node.args.bpmnSymbol;
        }
        const diamond = new Diamond({ height: Math.max(node.size.height, 0), width: Math.max(node.size.width, 0), x: 0, y: 0 });
        // eslint-disable-next-line max-len
        const points = `${this.svgStr(diamond.topPoint)} ${this.svgStr(diamond.rightPoint)} ${this.svgStr(diamond.bottomPoint)} ${this.svgStr(diamond.leftPoint)}`;
        return <g>
            <polygon class-sprotty-node={node instanceof GNode} class-sprotty-port={node instanceof GPort}
                  class-mouseover={node.hoverFeedback} class-selected={node.selected}
                  points={points} />
            <g class-symbol={true}>
                <path transform={'scale(' + scaleFactor + '),translate(' + translateX + ',' + translateY + ')'} d={icon} />
            </g>

            {context.renderChildren(node)}
        </g>;
    }

    protected svgStr(point: Point): string {
        return `${point.x},${point.y}`;
    }
}

/*
 * Render a BPMN DataObject
 * A DataObject may contain a symbol.
 *
 * See: https://www.eclipse.org/glsp/documentation/rendering/#default-views
 */
@injectable()
export class DataObjectNodeView extends ShapeView {
    render(node: Readonly<DataObjectNode & Hoverable & Selectable>, context: RenderingContext, args?: IViewArgs): VNode | undefined {
        if (!this.isVisible(node, context)) {
            return undefined;
        }
        const scaleFactor = 1.5;
        const translateX = 4.0;
        const translateY = 8.0;
        let icon = '';
        if (node.args.bpmnSymbol) {
            icon = '' + node.args.bpmnSymbol;
        }
        // compute size
        const width = Math.max(node.size.width, 0);
        const height = Math.max(node.size.height, 0);
        const bevelSize = 10; // Größe der Abschrägung
        // Element path
        const pathData = `
            M 0,0 
            L ${width - bevelSize},0
            L ${width},${bevelSize}
            L ${width},${height}
            L 0,${height}
            Z`;

        return <g>
            <rect class-sprotty-node={node instanceof GNode}
                class-sprotty-port={node instanceof GPort}
                class-mouseover={node.hoverFeedback}
                class-selected={node.selected}
                x="0"
                y="0"
                width={Math.max(node.size.width, 0)}
                height={Math.max(node.size.height, 0)}></rect>
            <g class-sprotty-icon={'icon'} ><path stroke="currentColor" stroke-width="1.5" fill="none"
                d={pathData} />
            <g class-symbol={true}>
                <path transform={'scale(' + scaleFactor + '),translate(' + translateX + ',' + translateY + ')'} d={icon} />
            </g>
            </g>
            {context.renderChildren(node)}
        </g>;
    }
}

/*
 * Render a BPMN DataStore
 * A TextAnnotation contains a text attribute which is displayed using the ForeignObjectView
 *
 * See: https://www.eclipse.org/glsp/documentation/rendering/#default-views
 */
@injectable()
export class DataStoreNodeView extends ShapeView {
    render(node: Readonly<GShapeElement & Hoverable & Selectable>, context: RenderingContext, args?: IViewArgs): VNode | undefined {
        if (!this.isVisible(node, context)) {
            return undefined;
        }
        return <g>
            <rect class-sprotty-node={node instanceof GNode}
                class-sprotty-port={node instanceof GPort}
                class-mouseover={node.hoverFeedback}
                class-selected={node.selected}
                x="0"
                y="0"
                width={Math.max(node.size.width, 0)}
                height={Math.max(node.size.height, 0)}></rect>
            {/* Database symbol starting at (5,5) */}
            <g class-sprotty-icon={'icon'} transform="translate(0,10)">
                <path d="M0 0 L0 30" stroke="currentColor" stroke-width="1.5" fill="none" />
                <path d="M50 0 L50 30" stroke="currentColor" stroke-width="1.5" fill="none" />
                <path d="M0 30 A25 8.5 0 0 0 50 30" fill="none" stroke="currentColor" stroke-width="1.5" />
                <path d="M0 15 A25 8.5 0 0 0 50 15" fill="none" stroke="currentColor" stroke-width="1.5" />
                <ellipse cx="25" cy="0" rx="25" ry="8.5" fill="none" stroke="currentColor" stroke-width="1.5" />
            </g>

            {context.renderChildren(node)}
        </g>;
    }
}

@injectable()
export class MessageNodeView extends ShapeView {
    render(node: Readonly<GShapeElement & Hoverable & Selectable>, context: RenderingContext, args?: IViewArgs): VNode | undefined {
        if (!this.isVisible(node, context)) {
            return undefined;
        }
        const messageIcon = '0,0 15,10 30,0';
        return <g>
            <rect class-sprotty-node={node instanceof GNode} class-sprotty-port={node instanceof GPort}
                class-mouseover={node.hoverFeedback} class-selected={node.selected}
                x="0" y="0" width={Math.max(node.size.width, 0)} height={Math.max(node.size.height, 0)}></rect>
            <polyline points={messageIcon} />
            {context.renderChildren(node)}
        </g>;
    }
}

/*
 * Render a BPMN TextAnnotation
 * A TextAnnotation contains a text attribute which is displayed using the ForeignObjectView
 *
 * See: https://www.eclipse.org/glsp/documentation/rendering/#default-views
 */
@injectable()
export class TextAnnotationNodeView extends ShapeView {
    render(node: Readonly<GShapeElement & Hoverable & Selectable>, context: RenderingContext, args?: IViewArgs): VNode | undefined {
        if (!this.isVisible(node, context)) {
            return undefined;
        }
        const textBorder = '20,0 0,0 0,' + node.size.height + ' 20,' + node.size.height;
        return <g>
            <rect class-sprotty-node={node instanceof GNode} class-sprotty-port={node instanceof GPort}
                class-mouseover={node.hoverFeedback} class-selected={node.selected}
                x="0" y="0" width={Math.max(node.size.width, 0)} height={Math.max(node.size.height, 0)}></rect>
            <polyline points={textBorder} />
            {context.renderChildren(node)}
        </g>;
    }
}

/*
 * Render a Multi-Line BPMN Label
 */
@injectable()
export class MultiLineTextNodeView extends ShapeView {
    render(label: Readonly<MultiLineTextNode>, context: RenderingContext): VNode | undefined {
        if (!this.isVisible(label, context)) {
            return undefined;
        }
        let nodeWidth = 100;
        const parent = label.parent;
        if (isBoundsAware(parent)) {
            nodeWidth = parent.bounds.width;
        }
        // split text into lines...
        const lines: string[] = textLineSplitter(label.args.text, nodeWidth);
        // depending on the attribute 'align' we move the text element into the center
        let xOffset = 5;
        if (label.args.align === 'middle') {
            xOffset = nodeWidth * 0.5;
        }
        const vnode = <g class-sprotty-node={label instanceof GNode}>
            <text class-sprotty-label={true} transform={'translate(' + xOffset + ',0)'}>
                {lines!.map((_line, _index) => (
                    <tspan x="0" dy={_index === 0 ? 10 : 15}>{_line}</tspan>
                ))}
            </text>
        </g>;

        const subType = getSubType(label);
        if (subType) {
            setAttr(vnode, 'class', subType);
        }
        return vnode;
    }
}

/**
 * This method splits a text into an array of separate lines.
 * Beside the new-line character also the length of a line is considered.
 * This is needed to display a long text in multiple SVG tspan elements.
 * The CHARACTER_SPACE defines the average with of a single character.
 * However, this value is only an approximation.
 *
 * @param text - The text to be split.
 * @returns an array of text lines
 */
function textLineSplitter(text: any, nodeWidth: number): string[] {
    const CHARACTER_SPACE = 6.5;
    const result: string[] = [];
    // first split only by newlines
    const lines = text.split('\n');
    // Next we iterate of each line and verify if the words
    // in this line fit into the given nodesWidth
    for (let i = 0; i < lines.length; i++) {
        // split line into words....
        const words = lines[i].split(' ');
        // now lets add word by word and verify if we need to add a newline...
        let line = '';
        for (let j = 0; j < words.length; j++) {
            const word = words[j];
            line += word + ' ';
            if (line.length > (nodeWidth / CHARACTER_SPACE)) {
                line = line.substring(0, line.length - word.length - 2);
                result.push(line);
                line = word + ' ';
            }
        }
        result.push(line);
    }
    return result;
}
