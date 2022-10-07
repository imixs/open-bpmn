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

import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNParticipant;
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
public class DefaultBPMNParticipantExtension extends AbstractBPMNElementExtension {

    private static Logger logger = Logger.getLogger(DefaultBPMNParticipantExtension.class.getName());

    public DefaultBPMNParticipantExtension() {
        super();
    }

    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNTypes.POOL.equals(elementTypeId);
    }

    /**
     * This Extension is for BPMNActivities only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNBaseElement bpmnElement) {
        return (bpmnElement instanceof BPMNParticipant);
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

        dataBuilder //
                .addData("name", bpmnElement.getName()) //
                // .addData("execution", "exec") //
                .addData("documentation", bpmnElement.getDocumentation());

        schemaBuilder. //
                addProperty("name", "string", null). //
                // addProperty("execution", "string", null). //
                addProperty("documentation", "string", "Participant description.");

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name"). //
                addLayout(Layout.VERTICAL). //
                addElement("documentation", "Documentation", multilineOption);

        // update corresponding GModelElement....

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

            logger.info("...update feature = " + feature);

        }

    }

}
