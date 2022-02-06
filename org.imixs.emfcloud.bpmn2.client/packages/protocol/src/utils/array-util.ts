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
export type MaybeArray<T> = T | T[];

export function remove<T>(array: T[], value: T): boolean {
    const index = array.indexOf(value);
    if (index >= 0) {
        array.splice(index, 1);
        return true;
    }
    return false;
}

export function flatPush<T>(array: T[], toPush: MaybeArray<T>[]): void {
    toPush.forEach(value => (Array.isArray(value) ? array.push(...value) : array.push(value)));
}

export function distinctAdd<T>(array: T[], value: T): boolean {
    if (!array.includes(value)) {
        array.push(value);
        return true;
    }
    return false;
}

interface Constructor<T> {
    new (...args: any[]): T;
}
type PrimitiveType = 'bigint' | 'boolean' | 'function' | 'number' | 'object' | 'string' | 'symbol' | 'undefined';

export function isArrayOfType<T>(object: any, typeGuard: (elem: any) => elem is T, supportEmpty = false): object is T[] {
    return isArrayMatching(object, element => typeGuard(element), supportEmpty);
}

export function isArrayOfClass<T>(object: any, className: Constructor<T>, supportEmpty = false): object is T[] {
    return isArrayMatching(object, element => element instanceof className, supportEmpty);
}

export function isArrayOfPrimitive<T>(object: any, primitiveType: PrimitiveType, supportEmpty = false): object is T[] {
    return isArrayMatching(object, element => typeof element === primitiveType, supportEmpty);
}

export function isStringArray(object: any, supportEmpty = false): object is string[] {
    return isArrayOfPrimitive(object, 'string', supportEmpty);
}

export function isArrayMatching(object: any, predicate: (elem: any) => boolean, supportEmpty = false): boolean {
    return Array.isArray(object) && object.every(predicate) && (supportEmpty || object.length > 0);
}
