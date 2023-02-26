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

import javax.json.JsonObject;

import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;

/**
 * The BPMNExtension is an extension point which allows you to add custom
 * properties to any BPMN Element managed within the BPMN modeling tool.
 *
 * Extensions are a core functionality of BPMN 2.0. An Extension defines the
 * ExtensionDefinition and ExtensionAttributeDefinition. The latter defines a
 * list of attributes that can be attached to any BPMN element. The attribute
 * list defines the name and type of the new attribute. This allows BPMN
 * adopters to integrate any meta model into the BPMN meta model and reuse
 * already existing model elements.
 * <p>
 * OpenBPMN adapts this concept with the BPMNExtension interface. As an adopter
 * you can implement a new BPMNExtension and describe your properties in a
 * separate schemata. The schemata are defined by the JSONForms project. This is
 * a very flexible an easy to use extension mechanism.
 *
 *
 * @author rsoika
 *
 */
public interface BPMNExtension {

    public final static String INFO = "bpmnExtensionInfo";

    /**
     * Unique identifier specifying the Extension namespace. The default namespace
     * is 'bpmn2'. Implementations should overwrite this method.
     *
     */
    String getNamespace();

    /**
     * Unique target namespace URI
     *
     * @return String - namespace uri
     */
    String getNamespaceURI();

    /**
     * Returns the Extension label to be used in the Tool Palette
     *
     * @return String
     */
    String getLabel();

    /**
     * Returns an optional Extension information label to be displayed within the
     * diagram plane
     *
     * @return String
     */
    default String getInfo(final BPMNElement bpmnElement) {
        return ""; // return empty string as default
    }

    /**
     * Returns true if the given ElementTypeID can be handled by this extension.
     * This method is used to verify if a custom implementation of an extension can
     * be applied to a BPMNModelElement.
     *
     * @param elementTypeId - the elementTypeId
     * @return - true if the elementTypeId can be handled by this extension.
     */
    boolean handlesElementTypeId(String elementTypeId);

    /**
     * Validates whether the given {@link BPMNElement} can be handled by this BPMN
     * extension.
     * 
     * Implementations must overwrite this method and accept only specific BPMN
     * element types or elements containing specific BPMN 2.0 extensions.
     *
     * @param bpmnElement - the BPMNBaseElement that should be handled.
     * @return `true` if the given bpmnElement can be handled by this extension.
     */
    boolean handlesBPMNElement(final BPMNElement bpmnElement);

    /**
     * Returns the priority of this action handler. The priority is used to derive
     * the execution order if multiple extension handlers should execute the same
     * {@link BPMNElement}. The default priority is `0` and the priority is sorted
     * descending. This means handlers with a priority &gt; 0 are executed before
     * handlers with a default priority and handlers with a priority &lt; 0 are
     * executed afterwards.
     *
     * @return the priority as integer.
     */
    default int getPriority() {
        return 0;
    }

    /**
     * Adds an extension to a given BPMN Element
     *
     * @param bpmnElement
     */
    void addExtension(BPMNElement bpmnElement);

    /**
     * This Helper Method is called to generate a JSON Forms Object by adding the
     * corresponding BPMNElement properties.
     * <p>
     * You can add a new data field by calling
     * <p>
     * {@code builder.add("myField","someData")
     *         uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name", "category"). //
    
         schemaBuilder.addProperty("name", "string", "Please enter your name");
     *
     * }
     * <p>
     * This JsonObjectBuilder is used on the BPMNGmodelFactory to generate the
     * JsonForms
     *
     */
    void buildPropertiesForm(BPMNElement bpmnElement, DataBuilder dataBuilder, SchemaBuilder schemaBuilder,
            UISchemaBuilder uiSchemaBuilder);

    /**
     * Updates the properties data of a BPMN Element.
     * <p>
     * The new data is provided in the JsonObject 'json'.
     * <p>
     * The parameter 'category' is optional and specifies a part of the data
     * structure to be updated. This can be useful for complex BPMN objects to
     * updated only parts of the data, which can have a positive performance issue.
     * <p>
     * An extension can also update the given json object during this operation if
     * needed.
     *
     * @param json         - a JSON structure representing the data
     * @param category     - an optional data category
     * @param bpmnElement  - the BPMN element to be updated
     * @param gNodeElement - the GModelElement element
     */
    void updatePropertiesData(JsonObject json, String category, BPMNElement bpmnElement, GModelElement gNodeElement);

}
