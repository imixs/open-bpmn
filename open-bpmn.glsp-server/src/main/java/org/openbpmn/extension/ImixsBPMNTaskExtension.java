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

import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
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
public class ImixsBPMNTaskExtension extends AbstractBPMNElementExtension {

    private static Logger logger = Logger.getLogger(ImixsBPMNTaskExtension.class.getName());

    public ImixsBPMNTaskExtension() {
        super();
    }

    /**
     * This Helper Method generates a JSON Object with the BPMNElement properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     */
    @Override
    public void addFormsData(final DataBuilder dataBuilder, final BPMNBaseElement bpmnTask) {

        dataBuilder //
                .addData("form", "blub") //
                .addData("abstract", "exec") //
                .addData("summary", "some test docu");
    }

    /**
     * Adds the default JSONForms schema for a BPMNEvent.
     *
     */
    @Override
    public void addSchema(final SchemaBuilder schemaBuilder, final BPMNBaseElement bpmnTask) {

        schemaBuilder. //
                addProperty("form", "string", null). //
                addProperty("abstract", "string", null). //
                addProperty("summary", "string", null);

    }

    /**
     * This Helper Method generates the default UISchema for a BPMNEvent
     *
     * @see UISchemaBuilder
     */
    @Override
    public void addCategories(final UISchemaBuilder uiSchemaBuilder, final BPMNBaseElement bpmnTask) {

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        uiSchemaBuilder. //

                addCategory("Workflow"). //
                addLayout(Layout.VERTICAL). //
                addElements("form", "abstract", "summary");

    }

    /**
     * This Extension is for BPMNActivities only
     */
    @Override
    public boolean handles(final BPMNBaseElement bpmnElement) {
        return (bpmnElement instanceof BPMNActivity);
    }

    @Override
    public void updateData(final JsonObject json, final BPMNBaseElement bpmnElement) {

        // default update of name and documentation
        super.updateData(json, bpmnElement);

        // check custom features
        Set<String> features = json.keySet();
        String value = null;
        for (String feature : features) {
            value = json.getString(feature);

            logger.fine("...update feature = " + feature);

            // TODO implement Event features
        }

    }

    @Override
    public String getKind() {
        return "imixs";
    }

    @Override
    public String getLabel() {
        return "Imixs-Workflow";
    }

}
