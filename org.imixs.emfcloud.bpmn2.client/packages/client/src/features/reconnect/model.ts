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
    edgeInProgressID,
    edgeInProgressTargetHandleID,
    RoutingHandleKind,
    selectFeature,
    SModelElement,
    SModelExtension,
    SRoutableElement,
    SRoutingHandle
} from 'sprotty';

export const reconnectFeature = Symbol('reconnectFeature');

export interface Reconnectable extends SModelExtension {}

export function isReconnectable(element: SModelElement): element is SRoutableElement & Reconnectable {
    return element instanceof SRoutableElement && element.hasFeature(reconnectFeature);
}

const ROUTING_HANDLE_SOURCE_INDEX = -2;

export function isReconnectHandle(element: SModelElement | undefined): element is SReconnectHandle {
    return element !== undefined && element instanceof SReconnectHandle;
}

export function addReconnectHandles(element: SRoutableElement): void {
    removeReconnectHandles(element);
    createReconnectHandle(element, 'source', ROUTING_HANDLE_SOURCE_INDEX);
    createReconnectHandle(element, 'target', element.routingPoints.length);
}

export function removeReconnectHandles(element: SRoutableElement): void {
    element.removeAll(child => child instanceof SReconnectHandle);
}

export function isSourceRoutingHandle(edge: SRoutableElement, routingHandle: SReconnectHandle): boolean {
    return routingHandle.pointIndex === ROUTING_HANDLE_SOURCE_INDEX;
}

export function isTargetRoutingHandle(edge: SRoutableElement, routingHandle: SReconnectHandle): boolean {
    return routingHandle.pointIndex === edge.routingPoints.length;
}

export function createReconnectHandle(edge: SRoutableElement, kind: RoutingHandleKind, routingPointIndex: number): SReconnectHandle {
    const handle = new SReconnectHandle();
    handle.kind = kind;
    handle.pointIndex = routingPointIndex;
    handle.type = 'routing-point';
    if (kind === 'target' && edge.id === edgeInProgressID) {
        handle.id = edgeInProgressTargetHandleID;
    }
    edge.add(handle);
    return handle;
}

export class SReconnectHandle extends SRoutingHandle {
    hasFeature(feature: symbol): boolean {
        return feature !== selectFeature && super.hasFeature(feature);
    }
}
