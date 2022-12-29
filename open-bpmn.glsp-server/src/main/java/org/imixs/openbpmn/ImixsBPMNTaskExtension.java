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
package org.imixs.openbpmn;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.json.JsonObject;

import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.extension.BPMNExtension;
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
public class ImixsBPMNTaskExtension implements BPMNExtension {

    private static Logger logger = Logger.getLogger(ImixsBPMNTaskExtension.class.getName());

    public ImixsBPMNTaskExtension() {
        super();
    }

    @Override
    public String getNamespace() {
        return "imixs";
    }

    @Override
    public String getNamespaceURI() {
        return "http://www.imixs.org/bpmn2";
    }

    @Override
    public String getLabel() {
        return "Imixs-Workflow";
    }

    /**
     * The ImixsBPMNTaskExtension can only be applied to a BPMN Task element
     */
    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return (BPMNTypes.CATCH_EVENT.equals(elementTypeId) //
                || BPMNTypes.TASK.equals(elementTypeId));
    }

    /**
     * This Extension is for BPMN Task Elements only
     * <p>
     * The method also verifies if the element has a imixs:processid attribute. This
     * attribute is added in the 'addExtesnion' method call
     */
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {

        if (bpmnElement instanceof Activity) {
            Activity task = (Activity) bpmnElement;
            if (task.getType().equals(BPMNTypes.TASK)) {
                // next check the extension attribute imixs:processid
                if (task.hasAttribute(getNamespace() + ":processid")) {
                    return true;
                }
            }
        }
        if (bpmnElement instanceof Event) {
            Event event = (Event) bpmnElement;
            if (event.getType().equals(BPMNTypes.CATCH_EVENT)) {
                // next check the extension attribute imixs:processid
                if (event.hasAttribute(getNamespace() + ":activityid")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method adds a unique identifier to the corresponding BPMNElement
     */
    @Override
    public void addExtension(final BPMNElement bpmnElement) {
        if (bpmnElement instanceof Activity) {
            bpmnElement.setExtensionAttribute(getNamespace(), "processid", "100");
        }

        if (bpmnElement instanceof Event) {
            bpmnElement.setExtensionAttribute(getNamespace(), "activityid", "10");
        }
    }

    /**
     * This Helper Method generates a JSON Object with the BPMNElement properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     */
    @Override
    public void buildPropertiesForm(final BPMNElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        dataBuilder //
                .addData("form", "blub") //
                .addData("abstract", "exec") //
                .addData("summary", "some test docu");

        schemaBuilder. //
                addProperty("form", "string", null). //
                addProperty("abstract", "string", null). //
                addProperty("summary", "string", null);

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        uiSchemaBuilder. //

                addCategory("Workflow"). //
                addLayout(Layout.VERTICAL). //
                addElements("form", "abstract", "summary");

    }

    @Override
    public void updatePropertiesData(final JsonObject json, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        // check custom features
        Set<String> features = json.keySet();
        String value = null;
        for (String feature : features) {
            value = json.getString(feature);

            logger.fine("...update feature = " + feature);

            // TODO implement Event features
        }

    }

}
