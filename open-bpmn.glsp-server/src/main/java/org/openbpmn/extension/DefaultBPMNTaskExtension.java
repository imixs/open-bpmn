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

import javax.json.JsonObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.utils.BPMNGraphUtil;

/**
 * This is the Default BPMNEvent extension providing the JSONForms shemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNTaskExtension extends AbstractBPMNElementExtension {

    private static Logger logger = LogManager.getLogger(DefaultBPMNTaskExtension.class);

    public DefaultBPMNTaskExtension() {
        super();
    }

    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNTypes.BPMN_TASKS.contains(elementTypeId);
    }

    /**
     * This Extension is for BPMNActivities only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {
        return (bpmnElement instanceof Activity);
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
                .addData("name", bpmnElement.getName()) //
                // .addData("execution", "exec") //
                .addData("documentation", bpmnElement.getDocumentation());

        schemaBuilder. //
                addProperty("name", "string", null). //
                // addProperty("execution", "string", null). //
                addProperty("documentation", "string", "Task description.");

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name"). //
                addLayout(Layout.VERTICAL). //
                addElement("documentation", "Documentation", multilineOption);

        // Script-Task?
        Activity taskElement = (Activity) bpmnElement;
        if (BPMNTypes.SCRIPT_TASK.equals(taskElement.getType())) {
            dataBuilder //
                    .addData("scriptformat", taskElement.getAttribute("scriptFormat")) //
                    .addData("script", taskElement.getChildNodeContent("script"));

            schemaBuilder. //
                    addProperty("scriptformat", "string", "Format of the script"). //
                    addProperty("script", "string", "Script to be run when this Task is performed.");

            uiSchemaBuilder. //
                    addCategory("Script"). //
                    addLayout(Layout.VERTICAL). //
                    addElements("scriptformat"). //
                    addElement("script", "Script", multilineOption); //
        }

        // update corresponding GModelElement....

    }

    @Override
    public void updatePropertiesData(final JsonObject json, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        // default update of name and documentation

        Set<String> features = json.keySet();
        for (String feature : features) {
            if ("name".equals(feature)) {
                // update the task CompartmentHeader (GLabel)
                GLabel label = BPMNGraphUtil.findCompartmentHeader((BPMNGNode) gNodeElement);
                if (label != null) {
                    label.setText(json.getString(feature));
                }
                continue;
            }
            if ("documentation".equals(feature)) {
                bpmnElement.setDocumentation(json.getString(feature));
                continue;
            }

            logger.debug("...update feature = " + feature);

            if ("scriptformat".equals(feature)) {
                bpmnElement.setAttribute("scriptFormat", json.getString(feature));
                continue;
            }

            if ("script".equals(feature)) {
                bpmnElement.setChildNodeContent("script", json.getString(feature));
                continue;
            }
        }

    }

}
