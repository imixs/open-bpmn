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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.json.JsonObject;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNGateway;
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

    private static Logger logger = Logger.getLogger(DefaultBPMNGatewayExtension.class.getName());

    public DefaultBPMNGatewayExtension() {
        super();
    }

    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNModel.BPMN_GATEWAYS.contains(elementTypeId);
    }

    /**
     * This Extension is for BPMNGateways only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNBaseElement bpmnElement) {
        return (bpmnElement instanceof BPMNGateway);
    }

    @Override
    public int getPriority() {
        return 999999;
    }

    /**
     * This Helper Method generates a JSON Object with the BPMNElement properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     */
    @Override
    public void buildPropertiesForm(final BPMNBaseElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        dataBuilder. //
                addData("name", bpmnElement.getName()). //
                addData("documentation", bpmnElement.getDocumentation()). //
                addData("gatewaydirection", bpmnElement.getAttribute("gatewayDirection"));

        String[] gatewayDirections = { "Converging", "Diverging", "Mixed", "Unspecified" };

        schemaBuilder. //
                addProperty("name", "string", null). //
                addProperty("documentation", "string", "Element description"). //
                addProperty("gatewaydirection", "string", null, gatewayDirections);

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");

        Map<String, String> radioOption = new HashMap<>();
        radioOption.put("format", "radio");

        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name"). //
                addElement("gatewaydirection", "Direction", radioOption). //
                addCategory("Attributes"). //
                addLayout(Layout.VERTICAL). //
                addElement("documentation", "Documentation", multilineOption). //
                addCategory("Workflow"). //
                addLayout(Layout.HORIZONTAL);

    }

    @Override
    public void updatePropertiesData(final JsonObject json, final BPMNBaseElement bpmnElement) {

        // default update of name and documentation
        super.updatePropertiesData(json, bpmnElement);

        // check custom features
        Set<String> features = json.keySet();
        String value = null;
        for (String feature : features) {
            value = json.getString(feature);

            logger.fine("...update feature = " + feature);

            if ("gatewaydirection".equals(feature)) {
                bpmnElement.setAttribute("gatewayDirection", value);
                continue;
            }
            // TODO implement Event features
        }

    }

}
