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

import org.openbpmn.bpmn.elements.BPMNBaseElement;
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

    /**
     * This Helper Method generates a JSON Object by adding the corresponding
     * BPMNElement properties.
     * <p>
     * You can add a new data field by calling
     * <p>
     * {@code builder.add("myField","someData")}
     * <p>
     * This JsonObjectBuilder is used on the BPMNGmodelFactory to generate the
     * JsonForms
     *
     */
    void addFormsData(DataBuilder dataBuilder, BPMNBaseElement bpmnElement);

    /**
     * This Helper Method adds categories to a JSONForms UISchema
     * <p>
     * You can add a new data field by calling
     *
     * <pre>
     * {@code
        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name", "category"). //
    }
     * </pre>
     * <p>
     * This JsonObjectBuilder is used on the BPMNGmodelFactory to generate the
     * JsonForms
     *
     */
    void addCategories(UISchemaBuilder uiSchemaBuilder, BPMNBaseElement bpmnElement);

    /**
     * This Helper Method adds new schema information to a JSONForms Schema
     * <p>
     * You can add a new data field by calling
     *
     * <pre>
     * {@code
         schemaBuilder.addProperty("name", "string", "Please enter your name");
     *
       }
     * </pre>
     *
     *
     * <p>
     * This SchemaBuilder is used on the BPMNGmodelFactory to generate the JsonForms
     *
     */
    void addSchema(SchemaBuilder schemaBuilder, BPMNBaseElement bpmnElement);

    /**
     * Validates whether the given {@link BPMNBaseElement} can be handled by this
     * BPMN extension. The default implementation returns true. Implementations can
     * accept only specific BPMN element types or elements containing specific BPMN
     * 2.0 extensions.
     *
     * @param bpmnElement The BPMNBaseElement that should be handled.
     * @return `true` if the given bpmnElement can be handled by this extension.
     */
    default boolean handles(final BPMNBaseElement bpmnElement) {
        return true;
    }

    /**
     * Returns the priority of this action handler. The priority is used to derive
     * the execution order if multiple extension handlers should execute the same
     * {@link BPMNBaseElement}. The default priority is `0` and the priority is
     * sorted descending. This means handlers with a priority &gt; 0 are executed
     * before handlers with a default priority and handlers with a priority &lt; 0
     * are executed afterwards.
     *
     * @return the priority as integer.
     */
    default int getPriority() {
        return 0;
    }

    /**
     * Updates the properties of a BPMN Element
     *
     * @param json        - a JSON structure representing the data
     * @param bpmnElement - the BPMN element to be updated
     */
    void updateData(JsonObject json, BPMNBaseElement bpmnElement);

    /**
     * Unique identifier specifying the kind of Extension. The kind is used as the
     * tag namespace
     */
    String getKind();

    /**
     * Returns the Extension label to be used in the Tool Palette
     *
     * @return String
     */
    String getLabel();
}
