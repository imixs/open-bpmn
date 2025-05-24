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

import java.util.Set;

import javax.json.JsonObject;

import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.extensions.AbstractBPMNElementExtension;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This is the Default BPMNEvent extension providing the JSONForms schemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNEventExtension extends AbstractBPMNElementExtension {

    public DefaultBPMNEventExtension() {
        super();
    }

    /**
     * Returns if this Extension can be applied to the given elementTypeID
     */
    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNTypes.BPMN_EVENTS.contains(elementTypeId);
    }

    /**
     * This Extension is for BPMNEvents only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {
        return (bpmnElement instanceof Event);
    }

    /**
     * This Helper Method generates a JSONForms Object with the BPMNElement
     * properties.
     * <p>
     * This JSON object is used on the GLSP Client to generate the EMF JsonForms
     * <p>
     * The method iterates over all eventDefinitions and adds a separate tab
     * including definitions of this type. Note: an event can have multiple
     * definitions of the same type.
     */
    @Override
    public void buildPropertiesForm(final BPMNElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        dataBuilder. //
                addData("name", bpmnElement.getName()). //
                addData("documentation", bpmnElement.getDocumentation());

        String documentation = "An Event is something that “happens” during the course of a Process. ";

        Event event = (Event) bpmnElement;
        if (BPMNTypes.CATCH_EVENT.equals(event.getType())) {
            documentation = documentation
                    + "An Intermediate Catch Event is expected to occur in the future and requires an internal or external action.";
        }
        if (BPMNTypes.THROW_EVENT.equals(event.getType())) {
            documentation = documentation
                    + "An Intermediate Throw Event is a reaction on an internal or external action that is caught by a subsequent event in the process flow.";
        }
        // has an impact and requires in general a reaction.

        schemaBuilder. //
                addProperty("name", "string", null). //
                addProperty("documentation", "string", documentation);

        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.VERTICAL). //
                addElements("name"). //
                addElement("documentation", "Documentation", this.getFileEditorOption());

    }

    /**
     * Updates the core attributes of the BPMN element and also the
     * eventDefinitions based on the different property tabs for each definition
     * type.
     */
    @Override
    public boolean updatePropertiesData(final JsonObject json, final String category, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        // we are only interested in category general
        if ("General".equals(category)) {
            updateNameProperty(json, bpmnElement, gNodeElement);
            // update attributes and tags
            bpmnElement.setDocumentation(json.getString("documentation", ""));
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
        if (bpmnElement instanceof Event) {

            // first we need to determine the kind of the event depending on the listed
            // event definitions
            Event event = (Event) bpmnElement;
            String kind = null;
            Set<Element> eventDefinitionList = event.getEventDefinitions();
            if (eventDefinitionList.size() > 0) {
                for (Node eventDefinition : eventDefinitionList) {
                    if (kind == null) {
                        kind = eventDefinition.getLocalName();
                    } else {
                        // we already have a symbol - Switch to Multiple Symbol?
                        if (!kind.equals(eventDefinition.getLocalName())) {
                            kind = BPMNTypes.MULTIPLE_EVENT_DEFINITIONS;
                            break;
                        }
                    }
                }
            }
            if (kind == null) {
                return icon;
            }

            if (BPMNTypes.EVENT_DEFINITION_MESSAGE.equals(kind)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/mail.svg?short_path=d02764e
                icon = "M1 3.5l.5-.5h13l.5.5v9l-.5.5h-13l-.5-.5v-9zm1 1.035V12h12V4.536L8.31 8.9H7.7L2 4.535zM13.03 4H2.97L8 7.869 13.03 4z";
            } else if (BPMNTypes.EVENT_DEFINITION_CANCEL.equals(kind)) {
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/close.svg?short_path=a63b1e0
                icon = "M8 8.707l3.646 3.647.708-.707L8.707 8l3.647-3.646-.707-.708L8 7.293 4.354 3.646l-.707.708L7.293 8l-3.646 3.646.707.708L8 8.707z";
            } else if (BPMNTypes.EVENT_DEFINITION_CONDITIONAL.equals(kind)) {
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/checklist.svg?short_path=e850b9d
                icon = "M3.75 4.48h-.71L2 3.43l.71-.7.69.68L4.81 2l.71.71-1.77 1.77zM6.99 3h8v1h-8V3zm0 3h8v1h-8V6zm8 3h-8v1h8V9zm-8 3h8v1h-8v-1zM3.04 7.48h.71l1.77-1.77-.71-.7L3.4 6.42l-.69-.69-.71.71 1.04 1.04zm.71 3.01h-.71L2 9.45l.71-.71.69.69 1.41-1.42.71.71-1.77 1.77zm-.71 3.01h.71l1.77-1.77-.71-.71-1.41 1.42-.69-.69-.71.7 1.04 1.05z";
            } else if (BPMNTypes.EVENT_DEFINITION_COMPENSATION.equals(kind)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/debug-reverse-continue.svg?short_path=5509580
                icon = "M13.5 2H12v12h1.5V2zm-4.936.39L9.75 3v10l-1.186.61-7-5V7.39l7-5zM3.29 8l4.96 3.543V4.457L3.29 8z";
            } else if (BPMNTypes.EVENT_DEFINITION_ESCALATION.equals(kind)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/fold-up.svg?short_path=a63b1e0
                icon = "M1 7.4l.7.7 6-6 6 6 .7-.7L8.1 1h-.7L1 7.4zm0 6l.7.7 6-6 6 6 .7-.7L8.1 7h-.7L1 13.4z";
            } else if (BPMNTypes.EVENT_DEFINITION_TIMER.equals(kind)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/history.svg?short_path=53d41f7
                icon = "M13.507 12.324a7 7 0 0 0 .065-8.56A7 7 0 0 0 2 4.393V2H1v3.5l.5.5H5V5H2.811a6.008 6.008 0 1 1-.135 5.77l-.887.462a7 7 0 0 0 11.718 1.092zm-3.361-.97l.708-.707L8 7.792V4H7v4l.146.354 3 3z";
            } else if (BPMNTypes.EVENT_DEFINITION_SIGNAL.equals(kind)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/pulse.svg?short_path=6ffbc15
                icon = "M11.8 9L10 3H9L7.158 9.64 5.99 4.69h-.97L3.85 9H1v.99h3.23l.49-.37.74-2.7L6.59 12h1.03l1.87-7.04 1.46 4.68.48.36H15V9h-3.2z";
            } else if (BPMNTypes.EVENT_DEFINITION_LINK.equals(kind)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/run-all.svg?short_path=06e6ef9
                icon = "M2.78 2L2 2.41v12l.78.42 9-6V8l-9-6zM3 13.48V3.35l7.6 5.07L3 13.48z";
                // translateX = 6;
            } else if (BPMNTypes.EVENT_DEFINITION_ERROR.equals(kind)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/github-action.svg?short_path=987d495
                icon = "M3.04 10h2.58l.65 1H2.54l-.5-.5v-9l.5-.5h12l.5.5v4.77l-1-1.75V2h-11v8zm5.54 1l-1.41 3.47h2.2L15 8.7 14.27 7h-1.63l.82-1.46L12.63 4H9.76l-.92.59-2.28 5L7.47 11h1.11zm1.18-6h2.87l-1.87 3h3.51l-5.76 5.84L10.2 10H7.47l2.29-5zM6.95 7H4.04V6H7.4l-.45 1zm-.9 2H4.04V8H6.5l-.45 1z";
            } else if (BPMNTypes.EVENT_DEFINITION_TERMINATE.equals(kind)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/circle-large.svg?short_path=f801c32
                icon =
                        // eslint-disable-next-line max-len
                        "M9.588 2.215A5.808 5.808 0 0 0 8 2c-.554 0-1.082.073-1.588.215l-.006.002c-.514.141-.99.342-1.432.601A6.156 6.156 0 0 0 2.82 4.98l-.002.004A5.967 5.967 0 0 0 2.21 6.41 5.986 5.986 0 0 0 2 8c0 .555.07 1.085.21 1.591a6.05 6.05 0 0 0 1.548 2.651c.37.365.774.677 1.216.94a6.1 6.1 0 0 0 1.435.609A6.02 6.02 0 0 0 8 14c.555 0 1.085-.07 1.591-.21.515-.145.99-.348 1.426-.607l.004-.002a6.16 6.16 0 0 0 2.161-2.155 5.85 5.85 0 0 0 .6-1.432l.003-.006A5.807 5.807 0 0 0 14 8c0-.554-.072-1.082-.215-1.588l-.002-.006a5.772 5.772 0 0 0-.6-1.423l-.002-.004a5.9 5.9 0 0 0-.942-1.21l-.008-.008a5.902 5.902 0 0 0-1.21-.942l-.004-.002a5.772 5.772 0 0 0-1.423-.6l-.006-.002zm4.455 9.32a7.157 7.157 0 0 1-2.516 2.508 6.966 6.966 0 0 1-1.668.71A6.984 6.984 0 0 1 8 15a6.984 6.984 0 0 1-1.86-.246 7.098 7.098 0 0 1-1.674-.711 7.3 7.3 0 0 1-1.415-1.094 7.295 7.295 0 0 1-1.094-1.415 7.098 7.098 0 0 1-.71-1.675A6.985 6.985 0 0 1 1 8c0-.643.082-1.262.246-1.86a6.968 6.968 0 0 1 .711-1.667 7.156 7.156 0 0 1 2.509-2.516 6.895 6.895 0 0 1 1.675-.704A6.808 6.808 0 0 1 8 1a6.8 6.8 0 0 1 1.86.253 6.899 6.899 0 0 1 3.083 1.805 6.903 6.903 0 0 1 1.804 3.083C14.916 6.738 15 7.357 15 8s-.084 1.262-.253 1.86a6.9 6.9 0 0 1-.704 1.674z";
                // translateX = 2;
                // translateY = 2;
                // scaleFactor = 1.8;
            } else if (BPMNTypes.MULTIPLE_EVENT_DEFINITIONS.equals(kind)) {
                // From codicons:
                // https://github.com/microsoft/vscode-codicons/blob/main/src/icons/layers.svg?short_path=f67ac65
                icon =
                        // eslint-disable-next-line max-len
                        "M7.62706 1.08717L8.18535 1.08325L14.2762 5.1203L14.2727 5.95617L8.1818 9.91912L7.63062 9.91528L1.72152 5.95233L1.71796 5.12422L7.62706 1.08717ZM7.91335 2.10268L2.89198 5.53323L7.91329 8.90079L13.0891 5.5332L7.91335 2.10268ZM1.79257 8.5L7.63059 12.4153L8.18177 12.4191L14.2053 8.5H12.3716L7.91326 11.4008L3.58794 8.5H1.79257ZM7.63059 14.9153L1.79257 11H3.58794L7.91326 13.9008L12.3716 11H14.2053L8.18177 14.9191L7.63059 14.9153Z";
            }
        }
        return icon;
    }
}
