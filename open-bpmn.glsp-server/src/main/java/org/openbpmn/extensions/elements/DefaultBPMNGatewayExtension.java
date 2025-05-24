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
package org.openbpmn.extensions.elements;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.json.JsonObject;

import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.extensions.AbstractBPMNElementExtension;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;

/**
 * This is the Default BPMNEvent extension providing the JSONForms shemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNGatewayExtension extends AbstractBPMNElementExtension {

    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(DefaultBPMNGatewayExtension.class.getName());

    public DefaultBPMNGatewayExtension() {
        super();
    }

    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNTypes.BPMN_GATEWAYS.contains(elementTypeId);
    }

    /**
     * This Extension is for BPMNGateways only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {
        return (bpmnElement instanceof Gateway);
    }

    /**
     * This Helper Method generates a JSON Object with the BPMNElement properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     */
    @Override
    public void buildPropertiesForm(final BPMNElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        dataBuilder. //
                addData("name", bpmnElement.getName()). //
                addData("documentation", bpmnElement.getDocumentation()). //
                addData("gatewaydirection", bpmnElement.getAttribute("gatewayDirection"));

        String[] gatewayDirections = { "Converging", "Diverging", "Mixed", "Unspecified" };

        String documentation = "A Gateway controls how Sequence Flows interact as they converge and diverge within a Process.";

        schemaBuilder. //
                addProperty("name", "string", null). //
                addProperty("documentation", "string", documentation). //
                addProperty("gatewaydirection", "string", null, gatewayDirections);

        Map<String, String> radioOption = new HashMap<>();
        radioOption.put("format", "radio");

        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name"). //
                addElement("gatewaydirection", "Direction", radioOption). //
                addLayout(Layout.VERTICAL). //
                addElement("documentation", "Documentation", this.getFileEditorOption());

    }

    /**
     * Update the Gateway Properties
     */
    @Override
    public boolean updatePropertiesData(final JsonObject json, final String category, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        // we are only interested in category general
        if ("General".equals(category)) {
            updateNameProperty(json, bpmnElement, gNodeElement);
            // update attributes and tags
            bpmnElement.setDocumentation(json.getString("documentation", ""));
            bpmnElement.setAttribute("gatewayDirection", json.getString("gatewaydirection", ""));
        }
        return false;
    }

    /**
     * This method returns the Event symbol depending on the kind (associated
     * EventDefinitions)
     *
     */
    @Override
    public String getSymbol(BPMNElement bpmnElement) {
        String icon = "";
        if (bpmnElement instanceof Gateway) {
            String type = ((Gateway) bpmnElement).getType();
            if (BPMNTypes.PARALLEL_GATEWAY.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/add.svg
                icon = "M14 7v1H8v6H7V8H1V7h6V1h1v6h6z";
            } else if (BPMNTypes.EXCLUSIVE_GATEWAY.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/chrome-close.svg?short_path=6b9a55a
                icon = "M7.116 8l-4.558 4.558.884.884L8 8.884l4.558 4.558.884-.884L8.884 8l4.558-4.558-.884-.884L8 7.116 3.442 2.558l-.884.884L7.116 8z";
            } else if (BPMNTypes.INCLUSIVE_GATEWAY.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/circle-large-outline.svg?short_path=f801c32
                icon = "M9.588 2.215A5.808 5.808 0 0 0 8 2c-.554 0-1.082.073-1.588.215l-.006.002c-.514.141-.99.342-1.432.601A6.156 6.156 0 0 0 2.82 4.98l-.002.004A5.967 5.967 0 0 0 2.21 6.41 5.986 5.986 0 0 0 2 8c0 .555.07 1.085.21 1.591a6.05 6.05 0 0 0 1.548 2.651c.37.365.774.677 1.216.94a6.1 6.1 0 0 0 1.435.609A6.02 6.02 0 0 0 8 14c.555 0 1.085-.07 1.591-.21.515-.145.99-.348 1.426-.607l.004-.002a6.16 6.16 0 0 0 2.161-2.155 5.85 5.85 0 0 0 .6-1.432l.003-.006A5.807 5.807 0 0 0 14 8c0-.554-.072-1.082-.215-1.588l-.002-.006a5.772 5.772 0 0 0-.6-1.423l-.002-.004a5.9 5.9 0 0 0-.942-1.21l-.008-.008a5.902 5.902 0 0 0-1.21-.942l-.004-.002a5.772 5.772 0 0 0-1.423-.6l-.006-.002zm4.455 9.32a7.157 7.157 0 0 1-2.516 2.508 6.966 6.966 0 0 1-1.668.71A6.984 6.984 0 0 1 8 15a6.984 6.984 0 0 1-1.86-.246 7.098 7.098 0 0 1-1.674-.711 7.3 7.3 0 0 1-1.415-1.094 7.295 7.295 0 0 1-1.094-1.415 7.098 7.098 0 0 1-.71-1.675A6.985 6.985 0 0 1 1 8c0-.643.082-1.262.246-1.86a6.968 6.968 0 0 1 .711-1.667 7.156 7.156 0 0 1 2.509-2.516 6.895 6.895 0 0 1 1.675-.704A6.808 6.808 0 0 1 8 1a6.8 6.8 0 0 1 1.86.253 6.899 6.899 0 0 1 3.083 1.805 6.903 6.903 0 0 1 1.804 3.083C14.916 6.738 15 7.357 15 8s-.084 1.262-.253 1.86a6.9 6.9 0 0 1-.704 1.674z";

            } else if (BPMNTypes.EVENTBASED_GATEWAY.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/debug-stackframe.svg?short_path=a6a09b8
                icon = "M14.5 7.15l-4.26-4.74L9.31 2H4.25L3 3.25v9.48l1.25 1.25h5.06l.93-.42 4.26-4.74V7.15zm-5.19 5.58H4.25V3.25h5.06l4.26 4.73-4.26 4.75z";

            } else if (BPMNTypes.COMPLEX_GATEWAY.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/gear.svg?short_path=8ee3ec4
                icon = "M9.1 4.4L8.6 2H7.4l-.5 2.4-.7.3-2-1.3-.9.8 1.3 2-.2.7-2.4.5v1.2l2.4.5.3.8-1.3 2 .8.8 2-1.3.8.3.4 2.3h1.2l.5-2.4.8-.3 2 1.3.8-.8-1.3-2 .3-.8 2.3-.4V7.4l-2.4-.5-.3-.8 1.3-2-.8-.8-2 1.3-.7-.2zM9.4 1l.5 2.4L12 2.1l2 2-1.4 2.1 2.4.4v2.8l-2.4.5L14 12l-2 2-2.1-1.4-.5 2.4H6.6l-.5-2.4L4 13.9l-2-2 1.4-2.1L1 9.4V6.6l2.4-.5L2.1 4l2-2 2.1 1.4.4-2.4h2.8zm.6 7c0 1.1-.9 2-2 2s-2-.9-2-2 .9-2 2-2 2 .9 2 2zM8 9c.6 0 1-.4 1-1s-.4-1-1-1-1 .4-1 1 .4 1 1 1z";
            }
        }

        return icon;
    }
}
