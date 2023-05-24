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

import javax.json.JsonObject;

import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.w3c.dom.Element;

/**
 * This is the Default BPMNEvent extension providing the JSONForms schemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNTaskExtension extends AbstractBPMNElementExtension {

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

        String documentation = "A Task is work that is performed within the Business Process." +
                " It describes an atomic action performed by an end-user or application which cannot be broken down to a finer level of detail.";
        schemaBuilder. //
                addProperty("name", "string", null). //
                // addProperty("execution", "string", null). //
                addProperty("documentation", "string", documentation);

        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name"). //
                addLayout(Layout.VERTICAL). //
                addElement("documentation", "Documentation", this.getFileEditorOption());

        // Script-Task?
        Activity taskElement = (Activity) bpmnElement;
        if (BPMNTypes.SCRIPT_TASK.equals(taskElement.getType())) {
            dataBuilder //
                    .addData("scriptformat", taskElement.getAttribute("scriptFormat")) //
                    .addData("script", taskElement
                            .getChildNodeContent(BPMNNS.BPMN2, "script"));

            schemaBuilder. //
                    addProperty("scriptformat", "string", "Format of the script"). //
                    addProperty("script", "string", "Script to be run when this Task is performed.");

            uiSchemaBuilder. //
                    addCategory("Script"). //
                    addLayout(Layout.VERTICAL). //
                    addElements("scriptformat"). //
                    addElement("script", "Script", this.getFileEditorOption());

        }
    }

    /**
     * Update the default activity properties.
     */
    @Override
    public void updatePropertiesData(final JsonObject json, final String category, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        if ("General".equals(category)) {
            updateNameProperty(json, bpmnElement, gNodeElement);
            // update attributes and tags
            bpmnElement.setDocumentation(json.getString("documentation", ""));
        }

        // Update script data
        // we are only interested in category condition
        if ("Script".equals(category)) {
            Activity taskElement = (Activity) bpmnElement;
            if (BPMNTypes.SCRIPT_TASK.equals(taskElement.getType())) {
                String dataValue = json.getString("script", "");
                bpmnElement.setAttribute("scriptFormat", json.getString("scriptformat", ""));
                Element childElement = bpmnElement.setChildNodeContent(BPMNNS.BPMN2, "script", dataValue, true);
                // if we have a file:// link than we create an additional open-bpmn attribute
                if (dataValue.startsWith("file://")) {
                    childElement.setAttribute("open-bpmn:file-link", dataValue);
                } else {
                    childElement.removeAttribute("open-bpmn:file-link");
                }

            }
        }
    }

}
