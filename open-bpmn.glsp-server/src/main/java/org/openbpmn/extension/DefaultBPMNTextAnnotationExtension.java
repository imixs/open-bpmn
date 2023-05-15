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

import java.util.Optional;

import javax.json.JsonObject;

import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.TextAnnotation;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;

/**
 * This is the Default TextAnnotation extension providing the JSONForms
 * schemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNTextAnnotationExtension extends AbstractBPMNElementExtension {

    public DefaultBPMNTextAnnotationExtension() {
        super();
    }

    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNTypes.TEXTANNOTATION.equals(elementTypeId);
    }

    /**
     * This Extension is for BPMNActivities only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {
        return (bpmnElement instanceof TextAnnotation);
    }

    /**
     * This Helper Method generates a JSON Object with the BPMNElement properties.
     * <p>
     * This json object is used on the GLSP Client to generate the EMF JsonForms
     */
    @Override
    public void buildPropertiesForm(final BPMNElement bpmnElement, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        String text = ((TextAnnotation) bpmnElement).getText();

        dataBuilder //
                .addData("textFormat", bpmnElement.getAttribute("textFormat")) //
                .addData("documentation", bpmnElement.getDocumentation()) //
                .addData("text", text);

        schemaBuilder. //
                addProperty("textFormat", "string", "e.g. text/plan | text/html"). //
                addProperty("documentation", "string", null). //
                addProperty("text", "string", "Content");

        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.VERTICAL). //
                addElement("text", "Text", this.getFileEditorOption()). //
                addElement("textFormat", "textformat", null). //
                addElement("documentation", "Documentation", this.getFileEditorOption());

    }

    /**
     * Updates the textAnnotation properties
     */
    @Override
    public void updatePropertiesData(final JsonObject json, final String category, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        // we are only interested in category general
        if (!"General".equals(category)) {
            return;
        }

        // update attributes and tags
        bpmnElement.setDocumentation(json.getString("documentation", ""));
        bpmnElement.setAttribute("textFormat", json.getString("textFormat", ""));

        // Update the text property
        String text = json.getString("text", "");
        ((TextAnnotation) bpmnElement).setText(text);
        // Update GModelElement Text Node...
        Optional<GModelElement> textNode = modelState.getIndex().get(gNodeElement.getId() + "_bpmntext");
        if (!textNode.isEmpty()) {
            textNode.get().getArgs().put("text", text);
        }
    }

}
