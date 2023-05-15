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

import java.util.Set;

import javax.json.JsonObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.w3c.dom.Element;

import com.google.inject.Inject;

/**
 * The DefaultBPMNSequenceFlowExtension add an additional section for the
 * conditional SequenceFlows.
 * <p>
 * The Extension also updates the default attribute of an optional connected
 * Gateway and reset the modelState in case the Gateway default status has
 * changed.
 * This mechanism will automatically update the SequenceFlow Default Symbol in
 * the diagram.
 * 
 * @author rsoika
 *
 */
public class DefaultBPMNSequenceFlowExtension extends AbstractBPMNElementExtension {

    @SuppressWarnings("unused")
    private static Logger logger = LogManager.getLogger(DefaultBPMNSequenceFlowExtension.class);

    @Inject
    protected BPMNGModelState modelState;

    public DefaultBPMNSequenceFlowExtension() {
        super();
    }

    @Override
    public int getPriority() {
        return 100; // below default settings from Edge element
    }

    /**
     * Returns if this Extension is of type SEQUENCE_FLOW
     */
    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNTypes.SEQUENCE_FLOW.equals(elementTypeId);
    }

    /**
     * This Extension is for SequenceFlow elements only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {
        return (bpmnElement instanceof SequenceFlow);
    }

    /**
     * This Helper Method generates a JSON Object with the BPMNElement properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     */
    @Override
    public void buildPropertiesForm(final BPMNElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        String description = "An optional boolean Expression that acts as a gating condition. " + //
                "A token will only  be placed on this Sequence Flow if this conditionExpression" + //
                "evaluates to true." + //
                "The default Sequence Flow should not have a conditionExpression. ";
        SequenceFlow sequenceFlow = (SequenceFlow) bpmnElement;
        String conditionalExpression = sequenceFlow.getConditionExpression();
        if (conditionalExpression == null) {
            conditionalExpression = "";
        }
        dataBuilder //
                .addData("conditionExpression", conditionalExpression) //
        ;

        schemaBuilder. //
                addProperty("conditionExpression", "string", description); //

        uiSchemaBuilder. //
                addCategory("Condition"). //
                addLayout(Layout.VERTICAL). //
                addElement("conditionExpression", "Expression", this.getFileEditorOption());

    }

    /**
     * This method updates the conditions of a sequenceFlow.
     * If no conditionExpression feature exits in the current json dataset the
     * method clears existing conditions from the sequence flow.
     * <p>
     * The method also updates the default state of an optional gateway and reset
     * the modelState in case the default status has changed.
     */
    @Override
    public void updatePropertiesData(final JsonObject json, final String category, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        // we are only interested in category condition
        if (!"Condition".equals(category)) {
            return;
        }

        boolean stateUpdate = false;
        try {
            SequenceFlow sequenceFlow = (SequenceFlow) bpmnElement;
            // check conditionExpression features
            Set<String> features = json.keySet();
            boolean conditionsFound = false;
            for (String feature : features) {
                if ("conditionExpression".equals(feature)) {
                    String expression = json.getString(feature);
                    stateUpdate = sequenceFlow.setConditionExpression(expression);
                    conditionsFound = true;
                    // if we have a file:// link than we create an additional open-bpmn attribute
                    Element childElement = sequenceFlow.getChildNode(BPMNNS.BPMN2, "conditionExpression");
                    if (childElement != null) {
                        if (expression.startsWith("file://")) {
                            childElement.setAttribute("open-bpmn:file-link", expression);
                        } else {
                            childElement.removeAttribute("open-bpmn:file-link");
                        }
                    }
                    continue;
                }
            }

            // if no conditions exits we clear them form the sequenceflow
            if (!conditionsFound) {
                stateUpdate = sequenceFlow.setConditionExpression(null);
            }
        } catch (BPMNModelException e) {
            logger.warn("Failed to update condition: " + e.getMessage());
            e.printStackTrace();
        }

        if (stateUpdate) {
            // in case the default state for a gateway has changed we need to reset the
            // model
            modelState.reset();
        }
    }

}
