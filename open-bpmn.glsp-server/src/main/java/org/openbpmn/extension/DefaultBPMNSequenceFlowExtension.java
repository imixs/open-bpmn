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
import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * The DefaultBPMNSequenceFlowExtension add an addional section for the
 * conditional SquenceFlows.
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

        SequenceFlow sequenceFlow = (SequenceFlow) bpmnElement;
        dataBuilder //
                .addData("conditionExpression", sequenceFlow.getConditionExpression()) //
                .addData("Default", "test");

        schemaBuilder. //
                addProperty("conditionExpression", "string", "add an optional conditional expression."). //
                addProperty("default", "boolean", null);

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        uiSchemaBuilder. //
                addCategory("Condition"). //
                addLayout(Layout.VERTICAL). //
                addElement("conditionExpression", "Expression", multilineOption);

    }

    @Override
    public void updatePropertiesData(final JsonObject json, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        SequenceFlow sequenceFlow = (SequenceFlow) bpmnElement;
        // check conditionExpression features
        Set<String> features = json.keySet();
        for (String feature : features) {
            if ("conditionExpression".equals(feature)) {
                sequenceFlow.setConditionExpression(json.getString(feature));
                continue;
            }
        }

    }

}
