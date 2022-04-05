/********************************************************************************
 * Copyright (c) 2019-2022 EclipseSource and others.
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
	getSubType,
	RenderingContext,
	SLabel,
	setAttr
} from '@eclipse-glsp/client';

import { injectable } from 'inversify';
import { VNode } from 'snabbdom';
import { findParentByFeature, ShapeView, svg } from 'sprotty';
import { Icon, EventNode, GatewayNode, isTaskNode, isEventNode, isGatewayNode } from '@open-bpmn/open-bpmn-model';

/****************************************************************************
 * This module provides BPMN element views like Gateways, or Events
 *
 ****************************************************************************/
// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

/*
 * The IconView shows a icon within a TaskNode
 */
@injectable()
export class IconView extends ShapeView {
	render(element: Icon, context: RenderingContext): VNode | undefined {

		if (!this.isVisible(element, context)) {
			return undefined;
		}

		const taskNode = findParentByFeature(element, isTaskNode);
		const eventNode = findParentByFeature(element, isEventNode);
		const gatewayNode = findParentByFeature(element, isGatewayNode);
		if (!(eventNode || taskNode || gatewayNode)) {
			return undefined;
		}
		/*
		const gatewayNode = findParentByFeature(element, isGatewayNode);
		if (!gatewayNode) {
			return undefined;
		}
		*/

		let icon;
		if (taskNode) {
			if (taskNode.type === 'task:manual') {
				// From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
				icon =
					// eslint-disable-next-line max-len
					'M10.54 2c.289.001.57.088.81.25a1.38 1.38 0 0 1 .45 1.69l-.97 2.17h2.79a1.36 1.36 0 0 1 1.16.61 1.35 1.35 0 0 1 .09 1.32c-.67 1.45-1.87 4.07-2.27 5.17a1.38 1.38 0 0 1-1.29.9H2.38A1.4 1.4 0 0 1 1 12.71V9.2a1.38 1.38 0 0 1 1.38-1.38h1.38L9.6 2.36a1.41 1.41 0 0 1 .94-.36zm.77 11.11a.39.39 0 0 0 .36-.25c.4-1.09 1.47-3.45 2.33-5.24a.39.39 0 0 0 0-.36.37.37 0 0 0-.38-.15h-3.3l-.52-.68v-.46l1.09-2.44a.37.37 0 0 0-.13-.46.38.38 0 0 0-.48 0L4.22 8.66l-.47.13H2.38A.38.38 0 0 0 2 9.2v3.51a.4.4 0 0 0 .38.4h8.93z';
			} else if (taskNode.type === 'task:service') {
				// From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
				icon =
					'M2.5 2H4v12H2.5V2zm4.936.39L6.25 3v10l1.186.61 7-5V7.39l-7-5zM12.71 8l-4.96 3.543V4.457L12.71 8z';
			} else if (taskNode.type === 'task:user') {
				// From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
				icon =
					// eslint-disable-next-line max-len
					'M16 7.992C16 3.58 12.416 0 8 0S0 3.58 0 7.992c0 2.43 1.104 4.62 2.832 6.09.016.016.032.016.032.032.144.112.288.224.448.336.08.048.144.111.224.175A7.98 7.98 0 0 0 8.016 16a7.98 7.98 0 0 0 4.48-1.375c.08-.048.144-.111.224-.16.144-.111.304-.223.448-.335.016-.016.032-.016.032-.032 1.696-1.487 2.8-3.676 2.8-6.106zm-8 7.001c-1.504 0-2.88-.48-4.016-1.279.016-.128.048-.255.08-.383a4.17 4.17 0 0 1 .416-.991c.176-.304.384-.576.64-.816.24-.24.528-.463.816-.639.304-.176.624-.304.976-.4A4.15 4.15 0 0 1 8 10.342a4.185 4.185 0 0 1 2.928 1.166c.368.368.656.8.864 1.295.112.288.192.592.24.911A7.03 7.03 0 0 1 8 14.993zm-2.448-7.4a2.49 2.49 0 0 1-.208-1.024c0-.351.064-.703.208-1.023.144-.32.336-.607.576-.847.24-.24.528-.431.848-.575.32-.144.672-.208 1.024-.208.368 0 .704.064 1.024.208.32.144.608.336.848.575.24.24.432.528.576.847.144.32.208.672.208 1.023 0 .368-.064.704-.208 1.023a2.84 2.84 0 0 1-.576.848 2.84 2.84 0 0 1-.848.575 2.715 2.715 0 0 1-2.064 0 2.84 2.84 0 0 1-.848-.575 2.526 2.526 0 0 1-.56-.848zm7.424 5.306c0-.032-.016-.048-.016-.08a5.22 5.22 0 0 0-.688-1.406 4.883 4.883 0 0 0-1.088-1.135 5.207 5.207 0 0 0-1.04-.608 2.82 2.82 0 0 0 .464-.383 4.2 4.2 0 0 0 .624-.784 3.624 3.624 0 0 0 .528-1.934 3.71 3.71 0 0 0-.288-1.47 3.799 3.799 0 0 0-.816-1.199 3.845 3.845 0 0 0-1.2-.8 3.72 3.72 0 0 0-1.472-.287 3.72 3.72 0 0 0-1.472.288 3.631 3.631 0 0 0-1.2.815 3.84 3.84 0 0 0-.8 1.199 3.71 3.71 0 0 0-.288 1.47c0 .352.048.688.144 1.007.096.336.224.64.4.927.16.288.384.544.624.784.144.144.304.271.48.383a5.12 5.12 0 0 0-1.04.624c-.416.32-.784.703-1.088 1.119a4.999 4.999 0 0 0-.688 1.406c-.016.032-.016.064-.016.08C1.776 11.636.992 9.91.992 7.992.992 4.14 4.144.991 8 .991s7.008 3.149 7.008 7.001a6.96 6.96 0 0 1-2.032 4.907z';
			} else if (taskNode.type === 'task:send') {
				// From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/mail.svg?short_path=d02764e
				icon =
					// eslint-disable-next-line max-len
					'M1 3.5l.5-.5h13l.5.5v9l-.5.5h-13l-.5-.5v-9zm1 1.035V12h12V4.536L8.31 8.9H7.7L2 4.535zM13.03 4H2.97L8 7.869 13.03 4z';
			} else if (taskNode.type === 'task:script') {
				// From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
				icon =
					// eslint-disable-next-line max-len
					'M6 10V9h9v1H6zm4-4h5v1h-5V6zm5-3v1H6V3h9zm-9 9v1h9v-1H6z"/><path fill-rule="evenodd" clip-rule="evenodd" d="M1 2.795l.783-.419 5.371 3.581v.838l-5.371 3.581L1 9.957V2.795zm1.007.94v5.281l3.96-2.64-3.96-2.64z';
			}
		}

		// did we have now a icon?
		if (!icon) {
			return undefined;
		}

		const vnode: any = (
			<g>
				<path
					transform={'scale(1.15),translate(0.75,0.75)'}
					// From codicons: https://github.com/microsoft/vscode-codicons/blob/main/src/icons/terminal.svg?short_path=2ffc08e
					// eslint-disable-next-line max-len
					d={icon}
				/>
				<rect class-icon-background x={0} y={0} width={25} height={20} />
				{context.renderChildren(element)}
			</g>
		);

		const subType = getSubType(element);
		if (subType) {
			setAttr(vnode, 'class', subType);
		}
		return vnode;

	}
}

/**
 * This  gateway...
 */
@injectable()
export class GatewayNodeView extends ShapeView {
	render(element: GatewayNode, context: RenderingContext): VNode | undefined {

		if (!this.isVisible(element, context)) {
			return undefined;
		}

		// first we compute the gateway symbol based on the gateway type
		let gatewaySymbol = undefined;
		if (element.type === 'gateway:exclusive') {
			// eslint-disable-next-line max-len
			gatewaySymbol = 'M14 7v1H8v6H7V8H1V7h6V1h1v6h6z';
		} else {
			// default symbol
			// eslint-disable-next-line max-len
			gatewaySymbol = 'M7.116 8l-4.558 4.558.884.884L8 8.884l4.558 4.558.884-.884L8.884 8l4.558-4.558-.884-.884L8 7.116 3.442 2.558l-.884.884L7.116 8z';
		}

		// find the label:heading and adust position
		for (const entry of element.children) {
			if (entry instanceof SLabel && entry.type === 'label:heading') {
				// adjust allignment and position
				entry.alignment = { x: 0, y: 0 };
				entry.position = { x: 0, y: 75 };
			}
		}

		/*  text-anchor="middle"  waere die lösung */
		let vnode: any = undefined;
		if (gatewaySymbol) {
			vnode = (
				// render circle with a event symbol and the label:heading
				<g transform={'scale(1) translate(0,0)'} class-sprotty-node={true} class-mouseover={element.hoverFeedback}>
					<rect x="0" y="0" width="42.5" height="42.5" transform={'rotate(45)'}></rect>
					<g class-bpmn-symbol={true}>
						<path transform={'scale(2.0) translate(-7.5,6.5)'}
							d={gatewaySymbol} />
					</g>
					{context.renderChildren(element)}
				</g>
			);
		} else {
			// we do not found a header so simply draw a circle...
			vnode = (
				<g transform={'scale(1) translate(0,0)'} class-sprotty-node={true}>
					<rect x="0" y="0" ></rect>
				</g>
			);
		}
		const subType = getSubType(element);
		if (subType) {
			setAttr(vnode, 'class', subType);
		}
		return vnode;
	}
}

@injectable()
export class EventNodeView extends ShapeView {
	
	render(element: EventNode, context: RenderingContext): VNode | undefined {
		if (!this.isVisible(element, context)) {
			return undefined;
		}

		// first we compute the event symbol based on the event type
		let eventSymbol = undefined;
		if (element.type === 'event:start') {
			// eslint-disable-next-line max-len
			eventSymbol = 'M14 7v1H8v6H7V8H1V7h6V1h1v6h6z';
		} else {
			// default symbol
			// eslint-disable-next-line max-len
			eventSymbol = 'M7.116 8l-4.558 4.558.884.884L8 8.884l4.558 4.558.884-.884L8.884 8l4.558-4.558-.884-.884L8 7.116 3.442 2.558l-.884.884L7.116 8z';
		}
		// find the label:heading and adust position
		for (const entry of element.children) {
			if (entry instanceof SLabel && entry.type === 'label:heading') {
				// adjust allignment and position
				entry.alignment = { x: 0, y: 0 };
				entry.position = { x: 0, y: 40 };
			}
		}

		let vnode: any = undefined;
		if (eventSymbol) {
			vnode = (
				// render circle with a event symbol and the label:heading
				// the second circle is a workarround for moveing the node
				// https://github.com/eclipse-glsp/glsp/discussions/608
				<g class-sprotty-node={true} class-mouseover={element.hoverFeedback}>
					<circle r='20' cx='0' cy='0' ></circle>
					<g class-bpmn-symbol={true} transform="translate(-11 -11),scale(1.5)">
						<path d={eventSymbol} />
					</g>
					{context.renderChildren(element)}
				</g>
			);
		} else {
			// we do not found a symbol so simply draw an empty circle...
			vnode = (
				<g class-sprotty-node={true} class-mouseover={element.hoverFeedback}>
					<circle r='20' cx='0' cy='0' ></circle>
					{context.renderChildren(element)}
					<circle r='12' cx='0' cy='0' ></circle>
				</g>
			);
		}
		const subType = getSubType(element);
		if (subType) {
			setAttr(vnode, 'class', subType);
		}
		return vnode;
	}
}

