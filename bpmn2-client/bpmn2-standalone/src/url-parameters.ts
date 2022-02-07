/********************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
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
export function getParameters(): { [key: string]: string } {
    let search = window.location.search.substring(1);
    const result = {};
    while (search.length > 0) {
        const nextParamIndex = search.indexOf('&');
        let param: string;
        if (nextParamIndex < 0) {
            param = search;
            search = '';
        } else {
            param = search.substring(0, nextParamIndex);
            search = search.substring(nextParamIndex + 1);
        }
        const valueIndex = param.indexOf('=');
        if (valueIndex > 0 && valueIndex < param.length - 1) {
            result[param.substring(0, valueIndex)] = decodeURIComponent(param.substring(valueIndex + 1));
        }
    }
    return result;
}
