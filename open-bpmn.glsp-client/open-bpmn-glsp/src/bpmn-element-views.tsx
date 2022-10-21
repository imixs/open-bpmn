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
    ActionDispatcher,
    getElements,
    getSubType,
    RenderingContext,
    SelectAction,
    setAttr,SShapeElement,
    SModelRoot,
    Hoverable,Selectable,IViewArgs,SPort,SNode,
    TYPES
} from '@eclipse-glsp/client';
import { SelectionListener } from '@eclipse-glsp/client/lib/features/select/selection-service';
import { Icon, isContainerNode, isBPMNLabelNode, isEventNode, isGatewayNode, isTaskNode } from '@open-bpmn/open-bpmn-model';
import { inject, injectable } from 'inversify';
import { VNode } from 'snabbdom';
import { findParentByFeature, ShapeView, svg } from 'sprotty';
/****************************************************************************
 * This module provides BPMN element views like Gateways, or Events
 *
 ****************************************************************************/
// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

/*
 * The IconView shows a icon within a BPMN Node
 *
 * The SVG icons have a fixed dimension. Dependign on the BPMN Node type a scale factor is used to size the symbol to the
 * BPMN element type. E.g. a symbol in a gateway node is larger than in a task node
 */
@injectable()
export class IconView extends ShapeView {
    render(element: Icon, context: RenderingContext): VNode | undefined {
        let scaleFactor = 1;
        let translate = 0;
        if (!this.isVisible(element, context)) {
            return undefined;
        }

        const taskNode = findParentByFeature(element, isTaskNode);
        const eventNode = findParentByFeature(element, isEventNode);
        const gatewayNode = findParentByFeature(element, isGatewayNode);
        if (!(eventNode || taskNode || gatewayNode)) {
            return undefined;
        }

        let icon;
        if (taskNode) {
            if (taskNode.type === 'manualTask') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
                icon =
                    // eslint-disable-next-line max-len
                    'M10.54 2c.289.001.57.088.81.25a1.38 1.38 0 0 1 .45 1.69l-.97 2.17h2.79a1.36 1.36 0 0 1 1.16.61 1.35 1.35 0 0 1 .09 1.32c-.67 1.45-1.87 4.07-2.27 5.17a1.38 1.38 0 0 1-1.29.9H2.38A1.4 1.4 0 0 1 1 12.71V9.2a1.38 1.38 0 0 1 1.38-1.38h1.38L9.6 2.36a1.41 1.41 0 0 1 .94-.36zm.77 11.11a.39.39 0 0 0 .36-.25c.4-1.09 1.47-3.45 2.33-5.24a.39.39 0 0 0 0-.36.37.37 0 0 0-.38-.15h-3.3l-.52-.68v-.46l1.09-2.44a.37.37 0 0 0-.13-.46.38.38 0 0 0-.48 0L4.22 8.66l-.47.13H2.38A.38.38 0 0 0 2 9.2v3.51a.4.4 0 0 0 .38.4h8.93z';
            } else if (taskNode.type === 'serviceTask') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
                icon = 'M2.5 2H4v12H2.5V2zm4.936.39L6.25 3v10l1.186.61 7-5V7.39l-7-5zM12.71 8l-4.96 3.543V4.457L12.71 8z';
            } else if (taskNode.type === 'userTask') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
                icon =
                    // eslint-disable-next-line max-len
                    'M16 7.992C16 3.58 12.416 0 8 0S0 3.58 0 7.992c0 2.43 1.104 4.62 2.832 6.09.016.016.032.016.032.032.144.112.288.224.448.336.08.048.144.111.224.175A7.98 7.98 0 0 0 8.016 16a7.98 7.98 0 0 0 4.48-1.375c.08-.048.144-.111.224-.16.144-.111.304-.223.448-.335.016-.016.032-.016.032-.032 1.696-1.487 2.8-3.676 2.8-6.106zm-8 7.001c-1.504 0-2.88-.48-4.016-1.279.016-.128.048-.255.08-.383a4.17 4.17 0 0 1 .416-.991c.176-.304.384-.576.64-.816.24-.24.528-.463.816-.639.304-.176.624-.304.976-.4A4.15 4.15 0 0 1 8 10.342a4.185 4.185 0 0 1 2.928 1.166c.368.368.656.8.864 1.295.112.288.192.592.24.911A7.03 7.03 0 0 1 8 14.993zm-2.448-7.4a2.49 2.49 0 0 1-.208-1.024c0-.351.064-.703.208-1.023.144-.32.336-.607.576-.847.24-.24.528-.431.848-.575.32-.144.672-.208 1.024-.208.368 0 .704.064 1.024.208.32.144.608.336.848.575.24.24.432.528.576.847.144.32.208.672.208 1.023 0 .368-.064.704-.208 1.023a2.84 2.84 0 0 1-.576.848 2.84 2.84 0 0 1-.848.575 2.715 2.715 0 0 1-2.064 0 2.84 2.84 0 0 1-.848-.575 2.526 2.526 0 0 1-.56-.848zm7.424 5.306c0-.032-.016-.048-.016-.08a5.22 5.22 0 0 0-.688-1.406 4.883 4.883 0 0 0-1.088-1.135 5.207 5.207 0 0 0-1.04-.608 2.82 2.82 0 0 0 .464-.383 4.2 4.2 0 0 0 .624-.784 3.624 3.624 0 0 0 .528-1.934 3.71 3.71 0 0 0-.288-1.47 3.799 3.799 0 0 0-.816-1.199 3.845 3.845 0 0 0-1.2-.8 3.72 3.72 0 0 0-1.472-.287 3.72 3.72 0 0 0-1.472.288 3.631 3.631 0 0 0-1.2.815 3.84 3.84 0 0 0-.8 1.199 3.71 3.71 0 0 0-.288 1.47c0 .352.048.688.144 1.007.096.336.224.64.4.927.16.288.384.544.624.784.144.144.304.271.48.383a5.12 5.12 0 0 0-1.04.624c-.416.32-.784.703-1.088 1.119a4.999 4.999 0 0 0-.688 1.406c-.016.032-.016.064-.016.08C1.776 11.636.992 9.91.992 7.992.992 4.14 4.144.991 8 .991s7.008 3.149 7.008 7.001a6.96 6.96 0 0 1-2.032 4.907z';
            } else if (taskNode.type === 'sendTask') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/mail.svg?short_path=d02764e
                icon =
                    // eslint-disable-next-line max-len
                    'M1 3.5l.5-.5h13l.5.5v9l-.5.5h-13l-.5-.5v-9zm1 1.035V12h12V4.536L8.31 8.9H7.7L2 4.535zM13.03 4H2.97L8 7.869 13.03 4z';
            } else if (taskNode.type === 'scriptTask') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
                icon =
                    // eslint-disable-next-line max-len
                    'M6 10V9h9v1H6zm4-4h5v1h-5V6zm5-3v1H6V3h9zm-9 9v1h9v-1H6z"/><path fill-rule="evenodd" clip-rule="evenodd" d="M1 2.795l.783-.419 5.371 3.581v.838l-5.371 3.581L1 9.957V2.795zm1.007.94v5.281l3.96-2.64-3.96-2.64z';
            }
        }

        if (gatewayNode) {
            scaleFactor = 1.5;
            translate = 9.0;
            if (gatewayNode.type === 'parallelGateway') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/add.svg
                icon =
                    // eslint-disable-next-line max-len
                    'M14 7v1H8v6H7V8H1V7h6V1h1v6h6z';
            } else if (gatewayNode.type === 'exclusiveGateway') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/chrome-close.svg?short_path=6b9a55a
                icon =
                    // eslint-disable-next-line max-len
                    'M7.116 8l-4.558 4.558.884.884L8 8.884l4.558 4.558.884-.884L8.884 8l4.558-4.558-.884-.884L8 7.116 3.442 2.558l-.884.884L7.116 8z';
            } else if (gatewayNode.type === 'inclusiveGateway') {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/circle-large-outline.svg?short_path=f801c32
                icon =
                    // eslint-disable-next-line max-len
                    'M9.588 2.215A5.808 5.808 0 0 0 8 2c-.554 0-1.082.073-1.588.215l-.006.002c-.514.141-.99.342-1.432.601A6.156 6.156 0 0 0 2.82 4.98l-.002.004A5.967 5.967 0 0 0 2.21 6.41 5.986 5.986 0 0 0 2 8c0 .555.07 1.085.21 1.591a6.05 6.05 0 0 0 1.548 2.651c.37.365.774.677 1.216.94a6.1 6.1 0 0 0 1.435.609A6.02 6.02 0 0 0 8 14c.555 0 1.085-.07 1.591-.21.515-.145.99-.348 1.426-.607l.004-.002a6.16 6.16 0 0 0 2.161-2.155 5.85 5.85 0 0 0 .6-1.432l.003-.006A5.807 5.807 0 0 0 14 8c0-.554-.072-1.082-.215-1.588l-.002-.006a5.772 5.772 0 0 0-.6-1.423l-.002-.004a5.9 5.9 0 0 0-.942-1.21l-.008-.008a5.902 5.902 0 0 0-1.21-.942l-.004-.002a5.772 5.772 0 0 0-1.423-.6l-.006-.002zm4.455 9.32a7.157 7.157 0 0 1-2.516 2.508 6.966 6.966 0 0 1-1.668.71A6.984 6.984 0 0 1 8 15a6.984 6.984 0 0 1-1.86-.246 7.098 7.098 0 0 1-1.674-.711 7.3 7.3 0 0 1-1.415-1.094 7.295 7.295 0 0 1-1.094-1.415 7.098 7.098 0 0 1-.71-1.675A6.985 6.985 0 0 1 1 8c0-.643.082-1.262.246-1.86a6.968 6.968 0 0 1 .711-1.667 7.156 7.156 0 0 1 2.509-2.516 6.895 6.895 0 0 1 1.675-.704A6.808 6.808 0 0 1 8 1a6.8 6.8 0 0 1 1.86.253 6.899 6.899 0 0 1 3.083 1.805 6.903 6.903 0 0 1 1.804 3.083C14.916 6.738 15 7.357 15 8s-.084 1.262-.253 1.86a6.9 6.9 0 0 1-.704 1.674z';
            }
        }

        if (eventNode) {
            scaleFactor = 1.5;
            translate = 4.0;

            if (eventNode.symbol === 'messageEventDefinition') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/mail.svg?short_path=d02764e
                icon =
                    // eslint-disable-next-line max-len
                    'M1 3.5l.5-.5h13l.5.5v9l-.5.5h-13l-.5-.5v-9zm1 1.035V12h12V4.536L8.31 8.9H7.7L2 4.535zM13.03 4H2.97L8 7.869 13.03 4z';
            } else if (eventNode.symbol === 'conditionalEventDefinition') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/debug-line-by-line.svg?short_path=a0335ca
                icon =
                    // eslint-disable-next-line max-len
                    'M1 2.795l.783-.419 5.371 3.581v.838l-5.371 3.581L1 9.957V2.795zm1.007.94v5.281l3.96-2.64-3.96-2.64z';
            } else if (eventNode.symbol === 'compensationEventDefinition') {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/debug-reverse-continue.svg?short_path=5509580
                icon =
                    // eslint-disable-next-line max-len
                    'M13.5 2H12v12h1.5V2zm-4.936.39L9.75 3v10l-1.186.61-7-5V7.39l7-5zM3.29 8l4.96 3.543V4.457L3.29 8z';
            } else if (eventNode.symbol === 'timerEventDefinition') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/history.svg?short_path=53d41f7
                icon =
                    // eslint-disable-next-line max-len
                    'M13.507 12.324a7 7 0 0 0 .065-8.56A7 7 0 0 0 2 4.393V2H1v3.5l.5.5H5V5H2.811a6.008 6.008 0 1 1-.135 5.77l-.887.462a7 7 0 0 0 11.718 1.092zm-3.361-.97l.708-.707L8 7.792V4H7v4l.146.354 3 3z';
            } else if (eventNode.symbol === 'signalEventDefinition') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/pulse.svg?short_path=6ffbc15
                icon =
                    // eslint-disable-next-line max-len
                    'M11.8 9L10 3H9L7.158 9.64 5.99 4.69h-.97L3.85 9H1v.99h3.23l.49-.37.74-2.7L6.59 12h1.03l1.87-7.04 1.46 4.68.48.36H15V9h-3.2z';
            } else if (eventNode.symbol === 'linkEventDefinition') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/run-all.svg?short_path=06e6ef9
                icon =
                    // eslint-disable-next-line max-len
                    'M2.78 2L2 2.41v12l.78.42 9-6V8l-9-6zM3 13.48V3.35l7.6 5.07L3 13.48z';
            } else if (eventNode.symbol === 'errorEventDefinition') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/github-action.svg?short_path=987d495
                icon =
                    // eslint-disable-next-line max-len
                    'M3.04 10h2.58l.65 1H2.54l-.5-.5v-9l.5-.5h12l.5.5v4.77l-1-1.75V2h-11v8zm5.54 1l-1.41 3.47h2.2L15 8.7 14.27 7h-1.63l.82-1.46L12.63 4H9.76l-.92.59-2.28 5L7.47 11h1.11zm1.18-6h2.87l-1.87 3h3.51l-5.76 5.84L10.2 10H7.47l2.29-5zM6.95 7H4.04V6H7.4l-.45 1zm-.9 2H4.04V8H6.5l-.45 1z';
            } else if (eventNode.symbol === 'multipleEventDefinition') {
                // From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/layers.svg?short_path=f67ac65
                icon =
                    // eslint-disable-next-line max-len
                    'M7.62706 1.08717L8.18535 1.08325L14.2762 5.1203L14.2727 5.95617L8.1818 9.91912L7.63062 9.91528L1.72152 5.95233L1.71796 5.12422L7.62706 1.08717ZM7.91335 2.10268L2.89198 5.53323L7.91329 8.90079L13.0891 5.5332L7.91335 2.10268ZM1.79257 8.5L7.63059 12.4153L8.18177 12.4191L14.2053 8.5H12.3716L7.91326 11.4008L3.58794 8.5H1.79257ZM7.63059 14.9153L1.79257 11H3.58794L7.91326 13.9008L12.3716 11H14.2053L8.18177 14.9191L7.63059 14.9153Z';
            }
        }

        // did we have now a icon?
        if (!icon) {
            return undefined;
        }

        const vnode: any = (
            <g>
                <path transform={'scale(' + scaleFactor + '),translate(' + translate + ',' + translate + ')'} d={icon} />
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
 * The ContainerHeaderView is used to show the label in a BPMNPool or BPMNLane element
 *
 * The label is displayed vertical on the left side of the container
 */
@injectable()
export class ContainerHeaderView extends ShapeView {
    render(element: SShapeElement, context: RenderingContext): VNode | undefined {
        if (!this.isVisible(element, context)) {
            return undefined;
        }

        const containerNode = findParentByFeature(element,isContainerNode);
        let containerLabel='undefined';
        if (containerNode) {
            containerLabel=containerNode.name;
        }
        // we estimate the length of the label to translate the y position (just a guess)
        // see also: https://github.com/imixs/open-bpmn/issues/91
        const yOffset=containerLabel.length*3;
        const vnode: any = (
            <g class-node={true}>
                <text class-sprotty-label={true} transform={'scale(1),translate(14,'+yOffset+'),rotate(-90)'}>{containerLabel}</text>
            </g>
        );

       // const vnode = <text class-sprotty-label={true}>{containerLabel}</text>;
        const subType = getSubType(element);
        if (subType) {
            setAttr(vnode, 'class', subType);
        }
        return vnode;
    }
}

@injectable()
export class DataObjectNodeView extends ShapeView {
    render(node: Readonly<SShapeElement & Hoverable & Selectable>, context: RenderingContext, args?: IViewArgs): VNode | undefined {
        if (!this.isVisible(node, context)) {
            return undefined;
        }
        return <g>
            <rect class-sprotty-node={node instanceof SNode} class-sprotty-port={node instanceof SPort}
                  class-mouseover={node.hoverFeedback} class-selected={node.selected}
                  x="0" y="0" width={Math.max(node.size.width, 0)} height={Math.max(node.size.height, 0)}></rect>
            {context.renderChildren(node)}
        </g>;
    }
}

/**
 * This selectionListener selects an associated BPMNLabel of a
 * selected BPMN Event, Gatweway, DataObject.
 * This allows to move both independent Nodes (GNode and GLabel)
 */
@injectable()
export class BPMNLabelNodeSelectionListener implements SelectionListener {
    @inject(TYPES.IActionDispatcher)
    protected actionDispatcher: ActionDispatcher;
    selectionChanged(root: Readonly<SModelRoot>, selectedElements: string[]): void {
        // we are only intersted in Events and Gateways
        const eventNodes = getElements(root.index, selectedElements, isBPMNLabelNode);
        if (eventNodes.length > 0) {
            // find the associated BPMNLabels
            const eventLabelIds = eventNodes.map(node => node.id + '_bpmnlabel');
            this.actionDispatcher.dispatch(SelectAction.create({ selectedElementsIDs: eventLabelIds }));
        }
    }
}
