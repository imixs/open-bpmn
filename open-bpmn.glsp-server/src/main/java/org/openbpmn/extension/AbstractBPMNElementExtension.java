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

import java.util.Optional;

import javax.json.JsonObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.utils.BPMNGraphUtil;

import com.google.inject.Inject;

/**
 * This is Abstract implementation provides some core funtionallity like update
 * the data fields name and documentation which is identically for all
 * BPMNElements.
 *
 * @author rsoika
 *
 */
abstract class AbstractBPMNElementExtension implements BPMNExtension {

    private static Logger logger = LogManager.getLogger(AbstractBPMNElementExtension.class);

    @Inject
    protected BPMNGModelState modelState;

    public BPMNGModelState getModelState() {
        return modelState;
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
        return 1;
    }

    /**
     * This method updates the name attribute of a BPMNElement and also the
     * corresponding GNode Element in the diagram plane.
     * 
     * @param json
     * @param bpmnElement
     * @param gNodeElement
     */
    public void updateNameProperty(final JsonObject json, BPMNElement bpmnElement, final GModelElement gNodeElement) {
        // Update the name feature
        String name = json.getString("name", "");
        if (!name.equals(bpmnElement.getName())) {
            bpmnElement.setName(name);
            // Update Label...
            Optional<GModelElement> label = modelState.getIndex().get(gNodeElement.getId() + "_bpmnlabel");
            if (!label.isEmpty()) {
                LabelGNode lgn = (LabelGNode) label.get();
                // update the bpmn-text-node of the GNodeElement
                GNode gnode = BPMNGraphUtil.findMultiLineTextNode(lgn);
                if (gnode != null) {
                    gnode.getArgs().put("text", name);
                }
            }
        }
    }

}
