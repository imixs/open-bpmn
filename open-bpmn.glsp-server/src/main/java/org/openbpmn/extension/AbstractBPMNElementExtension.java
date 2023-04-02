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

import javax.json.JsonArray;
import javax.json.JsonObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.utils.BPMNGraphUtil;
import org.w3c.dom.Element;

import com.google.inject.Inject;

/**
 * This is Abstract implementation provides some core functionality like update
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
     * <p>
     * The method distinguish between embedded labels (Task) and BPMNLabes.
     * 
     * In case of a LabelGNode elements can support multilineor simple line
     * GLabel elements
     * 
     * @param json
     * @param bpmnElement
     * @param gNodeElement
     */
    public void updateNameProperty(final JsonObject json, BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        // Update the name feature
        String name = json.getString("name", "");
        if (!name.equals(bpmnElement.getName())) {
            bpmnElement.setName(name);
            if (gNodeElement instanceof BPMNGNode) {
                // find MultiLineTextNode used in Tasks and BPMNLabels
                GNode gMultiLineTextNode = BPMNGraphUtil.findMultiLineTextNode((BPMNGNode) gNodeElement);
                if (gMultiLineTextNode != null) {
                    gMultiLineTextNode.getArgs().put("text", name);
                } else {
                    // test if we find a corresponding bpmnLabel (Events, Gateways,...)
                    GModelElement gModelElement = modelState.getIndex().get(gNodeElement.getId() + "_bpmnlabel")
                            .orElse(null);
                    // is it a multiline label?
                    if (gModelElement != null) {
                        // do we have a BPMN LabelGNode
                        if (gModelElement instanceof LabelGNode) {
                            LabelGNode lgn = (LabelGNode) gModelElement;
                            // update the bpmn-text-node of the GNodeElement
                            gMultiLineTextNode = BPMNGraphUtil.findMultiLineTextNode(lgn);
                            if (gMultiLineTextNode != null) {
                                gMultiLineTextNode.getArgs().put("text", name);
                            }
                        } else {
                            // default to GLabel
                            GLabel gLabel = (GLabel) gModelElement;
                            gLabel.setText(name);
                        }
                    }
                }
            }
        }
    }

    /**
     * This helper method verifies if the count of definitions matches the given
     * size of a dataList containing definition data and updates the elements
     * definition list.
     * The method returns an updated list of definition elements.
     * <p>
     * The method is used by the different eventDefinitionExtensions
     * 
     * @param definitionName
     * @param bpmnEvent
     * @param dataList
     * @return - updated list of definition elements
     */
    Set<Element> synchronizeEventDefinitions(final String definitionName, final Event bpmnEvent,
            final JsonArray dataList) {

        // find all named eventDefinitions for this event
        Set<Element> eventDefinitions = bpmnEvent.getEventDefinitionsByType(definitionName);

        if (dataList == null && eventDefinitions.size() == 0) {
            // no update needed at all
            return eventDefinitions;
        }
        // If the size of the eventDefinition List is not equals the size of the
        // dataList we add or remove eventDefinitions...
        while ((dataList == null && eventDefinitions.size() > 0)
                || (eventDefinitions.size() != dataList.size())) {
            try {
                if ((dataList == null && eventDefinitions.size() > 0)
                        || eventDefinitions.size() > dataList.size()) {
                    // delete first condition from the list
                    Element definition = eventDefinitions.iterator().next();
                    String id = definition.getAttribute("id");
                    bpmnEvent.deleteEventDefinition(id);
                } else if (eventDefinitions.size() < dataList.size()) {
                    // add a new empty condition placeholder...
                    bpmnEvent.addEventDefinition(definitionName);
                }

            } catch (BPMNModelException e) {
                logger.error("Failed to update BPMN Event Definition list: " + e.getMessage());
                e.printStackTrace();
            }
            // Update event definition list
            eventDefinitions = bpmnEvent.getEventDefinitionsByType(definitionName);
        }
        return eventDefinitions;
    }
}
