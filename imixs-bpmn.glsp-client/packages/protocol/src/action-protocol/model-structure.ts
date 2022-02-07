/********************************************************************************
 * Copyright (c) 2021 STMicroelectronics and others.
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
import { isString } from '../utils/typeguard-util';
import { Bounds } from './types';

/**
 * The schema of an SModelElement describes its serializable form. The actual model is created from
 * its schema with an IModelFactory.
 * Each model element must have a unique ID and a type that is used to look up its view.
 */
export interface SModelElementSchema {
    type: string;
    id: string;
    children?: SModelElementSchema[];
    cssClasses?: string[];
}

export function isSModelElementSchema(schema: any): schema is SModelElementSchema {
    return schema !== undefined && typeof schema === 'object' && isString(schema, 'type') && isString(schema, 'id');
}

/**
 * Serializable schema for the root element of the model tree.
 */
export interface SModelRootSchema extends SModelElementSchema {
    canvasBounds?: Bounds;
    revision?: number;
}

export function isSModelRootSchema(schema: any): schema is SModelRootSchema {
    return isSModelElementSchema(schema) && !('parent' in schema);
}
