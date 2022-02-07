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

/**
 * A utility object that provides configuration constants
 * for a glsp diagram language implementation
 */
export interface GLSPDiagramLanguage {
    /** Id of the glsp client contribution that is associated with this language */
    readonly contributionId: string;
    /** The diagram type that correlates to this language */
    readonly diagramType: string;
    /** File extensions that can be handled by this language. (Format: '.myext') */
    readonly fileExtensions: string[];
    /** Display-label for this language. Is used in UI elements like the editor-widget title */
    readonly label: string;
    /** The icon class that is associated with this language. If undefined, a default icon class will be used  */
    readonly iconClass?: string;
    /** The id of the diagram manager that is responsible for this language. If undefined, a default id derived
     * from the diagram type will be used.
     */
    readonly diagramManagerId?: string;
}
