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
import org.openbpmn.extensions.AbstractBPMNElementExtension;
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
    public boolean updatePropertiesData(final JsonObject json, final String category, final BPMNElement bpmnElement,
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
        return false;
    }

    /**
     * This method returns the Task symbol depending on the type
     *
     */
    @Override
    public String getSymbol(BPMNElement bpmnElement) {
        String icon = "";

        if (bpmnElement instanceof Activity) {
            String type = ((Activity) bpmnElement).getType();
            if (BPMNTypes.MANUAL_TASK.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
                icon = "M10.54 2c.289.001.57.088.81.25a1.38 1.38 0 0 1 .45 1.69l-.97 2.17h2.79a1.36 1.36 0 0 1 1.16.61 1.35 1.35 0 0 1 .09 1.32c-.67 1.45-1.87 4.07-2.27 5.17a1.38 1.38 0 0 1-1.29.9H2.38A1.4 1.4 0 0 1 1 12.71V9.2a1.38 1.38 0 0 1 1.38-1.38h1.38L9.6 2.36a1.41 1.41 0 0 1 .94-.36zm.77 11.11a.39.39 0 0 0 .36-.25c.4-1.09 1.47-3.45 2.33-5.24a.39.39 0 0 0 0-.36.37.37 0 0 0-.38-.15h-3.3l-.52-.68v-.46l1.09-2.44a.37.37 0 0 0-.13-.46.38.38 0 0 0-.48 0L4.22 8.66l-.47.13H2.38A.38.38 0 0 0 2 9.2v3.51a.4.4 0 0 0 .38.4h8.93z";
            } else if (BPMNTypes.USER_TASK.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
                icon = "M16 7.992C16 3.58 12.416 0 8 0S0 3.58 0 7.992c0 2.43 1.104 4.62 2.832 6.09.016.016.032.016.032.032.144.112.288.224.448.336.08.048.144.111.224.175A7.98 7.98 0 0 0 8.016 16a7.98 7.98 0 0 0 4.48-1.375c.08-.048.144-.111.224-.16.144-.111.304-.223.448-.335.016-.016.032-.016.032-.032 1.696-1.487 2.8-3.676 2.8-6.106zm-8 7.001c-1.504 0-2.88-.48-4.016-1.279.016-.128.048-.255.08-.383a4.17 4.17 0 0 1 .416-.991c.176-.304.384-.576.64-.816.24-.24.528-.463.816-.639.304-.176.624-.304.976-.4A4.15 4.15 0 0 1 8 10.342a4.185 4.185 0 0 1 2.928 1.166c.368.368.656.8.864 1.295.112.288.192.592.24.911A7.03 7.03 0 0 1 8 14.993zm-2.448-7.4a2.49 2.49 0 0 1-.208-1.024c0-.351.064-.703.208-1.023.144-.32.336-.607.576-.847.24-.24.528-.431.848-.575.32-.144.672-.208 1.024-.208.368 0 .704.064 1.024.208.32.144.608.336.848.575.24.24.432.528.576.847.144.32.208.672.208 1.023 0 .368-.064.704-.208 1.023a2.84 2.84 0 0 1-.576.848 2.84 2.84 0 0 1-.848.575 2.715 2.715 0 0 1-2.064 0 2.84 2.84 0 0 1-.848-.575 2.526 2.526 0 0 1-.56-.848zm7.424 5.306c0-.032-.016-.048-.016-.08a5.22 5.22 0 0 0-.688-1.406 4.883 4.883 0 0 0-1.088-1.135 5.207 5.207 0 0 0-1.04-.608 2.82 2.82 0 0 0 .464-.383 4.2 4.2 0 0 0 .624-.784 3.624 3.624 0 0 0 .528-1.934 3.71 3.71 0 0 0-.288-1.47 3.799 3.799 0 0 0-.816-1.199 3.845 3.845 0 0 0-1.2-.8 3.72 3.72 0 0 0-1.472-.287 3.72 3.72 0 0 0-1.472.288 3.631 3.631 0 0 0-1.2.815 3.84 3.84 0 0 0-.8 1.199 3.71 3.71 0 0 0-.288 1.47c0 .352.048.688.144 1.007.096.336.224.64.4.927.16.288.384.544.624.784.144.144.304.271.48.383a5.12 5.12 0 0 0-1.04.624c-.416.32-.784.703-1.088 1.119a4.999 4.999 0 0 0-.688 1.406c-.016.032-.016.064-.016.08C1.776 11.636.992 9.91.992 7.992.992 4.14 4.144.991 8 .991s7.008 3.149 7.008 7.001a6.96 6.96 0 0 1-2.032 4.907z";

            } else if (BPMNTypes.SCRIPT_TASK.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
                icon = "M6 10V9h9v1H6zm4-4h5v1h-5V6zm5-3v1H6V3h9zm-9 9v1h9v-1H6z";
            } else if (BPMNTypes.BUSINESSRULE_TASK.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/table.svg?short_path=8f21e22
                icon = "M13.5 2h-12l-.5.5v11l.5.5h12l.5-.5v-11l-.5-.5zM2 3h11v1H2V3zm7 4H6V5h3v2zm0 1v2H6V8h3zM2 5h3v2H2V5zm0 3h3v2H2V8zm0 5v-2h3v2H2zm4 0v-2h3v2H6zm7 0h-3v-2h3v2zm0-3h-3V8h3v2zm-3-3V5h3v2h-3z";
            } else if (BPMNTypes.SERVICE_TASK.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/account.svg?short_path=8135b2d
                icon = "M2.5 2H4v12H2.5V2zm4.936.39L6.25 3v10l1.186.61 7-5V7.39l-7-5zM12.71 8l-4.96 3.543V4.457L12.71 8z";

            } else if (BPMNTypes.SEND_TASK.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/mail.svg?short_path=d02764e
                icon = "M1 3.5l.5-.5h13l.5.5v9l-.5.5h-13l-.5-.5v-9zm1 1.035V12h12V4.536L8.31 8.9H7.7L2 4.535zM13.03 4H2.97L8 7.869 13.03 4z";
            } else if (BPMNTypes.RECEIVE_TASK.equals(type)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/mail-read.svg?short_path=f74817b
                icon = "M8.25 1.57h-.51L1 5.56v7.94l.5.5h13l.5-.5V5.56L8.25 1.57zM8 2.58l5.63 3.32-1.37 1.59H3.74L2.43 5.9 8 2.58zM14 13H2V6.92L3.11 8.3l.39.19h9l.39-.19L14 6.92V13z";
            } else {
                // no icon defined at all - See Issue #215
            }

        }
        return icon;
    }

}
