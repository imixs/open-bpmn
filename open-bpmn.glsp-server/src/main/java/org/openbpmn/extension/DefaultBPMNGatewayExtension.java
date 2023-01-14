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
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import javax.json.JsonObject;

import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.utils.BPMNGraphUtil;

import com.google.inject.Inject;

/**
 * This is the Default BPMNEvent extension providing the JSONForms shemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNGatewayExtension extends AbstractBPMNElementExtension {

    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(DefaultBPMNGatewayExtension.class.getName());

    @Inject
    protected BPMNGModelState modelState;

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
                addLayout(Layout.VERTICAL). //
                addElement("documentation", "Documentation", multilineOption);

    }

    @Override
    public void updatePropertiesData(final JsonObject json, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        Set<String> features = json.keySet();
        for (String feature : features) {

            if ("name".equals(feature)) {
                bpmnElement.setName(json.getString(feature));
                // Update Label...
                Optional<GModelElement> label = modelState.getIndex().get(gNodeElement.getId() + "_bpmnlabel");
                if (!label.isEmpty()) {
                    LabelGNode lgn = (LabelGNode) label.get();
                    GLabel glabel = BPMNGraphUtil.findCompartmentHeader((lgn));
                    if (glabel != null) {
                        glabel.setText(json.getString(feature));
                    }
                }

                continue;
            }
            if ("documentation".equals(feature)) {
                bpmnElement.setDocumentation(json.getString(feature));
                continue;
            }

            if ("gatewaydirection".equals(feature)) {
                bpmnElement.setAttribute("gatewayDirection", json.getString(feature));
                continue;
            }
            // TODO implement Event features
        }

    }

}
