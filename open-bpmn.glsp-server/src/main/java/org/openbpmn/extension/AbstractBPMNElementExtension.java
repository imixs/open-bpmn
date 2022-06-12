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

import java.util.Set;
import java.util.logging.Logger;

import javax.json.JsonObject;

import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.BPMNBaseElement;

/**
 * This is Abstract implementation proivides some core funtionallity like update
 * the data fields name and documentation which is identically for all
 * BPMNElements.
 *
 * @author rsoika
 *
 */
abstract class AbstractBPMNElementExtension implements BPMNExtension {
    private static Logger logger = Logger.getLogger(AbstractBPMNElementExtension.class.getName());

    /**
     * Default Implementation updates name and documentation only. Sub classes
     * should overwrite this method.
     */
    @Override
    public void updateData(final JsonObject json, final BPMNBaseElement bpmnElement) {

        Set<String> features = json.keySet();
        String value = null;
        for (String feature : features) {
            value = json.getString(feature);

            logger.fine("...update feature = " + feature);
            if ("name".equals(feature)) {
                bpmnElement.setName(value);
                continue;
            }

            if ("documentation".equals(feature)) {
                bpmnElement.setDocumentation(value);
                continue;
            }

        }

    }

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
    public void addExtension(final BPMNBaseElement bpmnElement) {
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

}
