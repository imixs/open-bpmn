/**
 * Copyright (c) 2010 Henning Heitkoetter.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Henning Heitkoetter - initial API and implementation
 */
package org.eclipse;

public class NamespaceHelper {

    /**
     * Returns the target namespace of the XML schema for the package denoted by
     * the given XMI namespace URI.
     * 
     * @param xmiNsUri
     *            The XMI namespace of the package (ending with "-XMI").
     * @return The corresponding XSD namespace URI, i.e. the xmiNsUri without
     *         the XMI suffix.
     */
    public static String xmiToXsdNamespaceUri(String xmiNsUri) {
        if (!xmiNsUri.endsWith("-XMI"))
            throw new IllegalArgumentException("XMI namespace expected");

        return xmiNsUri.substring(0, xmiNsUri.length() - 4);
    }

}
