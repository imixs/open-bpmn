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
package org.openbpmn.extension;

import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.core.BPMNElement;

/**
 * This is Abstract implementation provides some core funtionallity like update
 * the data fields name and documentation which is identically for all
 * BPMNElements.
 *
 * @author rsoika
 *
 */
abstract class AbstractBPMNElementExtension implements BPMNExtension {

    /**
     * Returns the Extension label to be used in the Tool Palette. The default name
     * is the namespace. Implementations should overwrite this method.
     *
     * @return namespace String per default
     */
    @Override
    public String getLabel() {
        return getNamespace();
    }

    @Override
    public void addExtension(final BPMNElement bpmnElement) {
        // no op for default extensions
    }

    /**
     * Unique identifier specifying the Extension namespace. The default namespace
     * is 'bpmn2'. Implementations should overwrite this method.
     *
     */
    @Override
    public String getNamespace() {
        return BPMNNS.BPMN2.name();
    }

    @Override
    public String getNamespaceURI() {
        return "http://www.omg.org/spec/BPMN/20100524/MODEL";
    }

    @Override
    public int getPriority() {
        return 999999;
    }

}
