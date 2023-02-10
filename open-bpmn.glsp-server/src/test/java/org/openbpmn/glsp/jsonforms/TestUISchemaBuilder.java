/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH,
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You can receive a copy of the GNU General Public
 * License at http://www.gnu.org/licenses/gpl.html
 *
 * Project:
 *     https://github.com/imixs/open-bpmn
 *
 * Contributors:
 *     Imixs Software Solutions GmbH - Project Management
 *     Ralph Soika - Software Developer
 ********************************************************************************/
package org.openbpmn.glsp.jsonforms;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.eclipse.glsp.server.model.DefaultGModelState;
import org.junit.jupiter.api.Test;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;

/**
 * Test class to test the UISchemaBuilder used to generate JSONForms uiSchematas
 *
 * @author rsoika
 *
 */
public class TestUISchemaBuilder extends DefaultGModelState {

    private static Logger logger = Logger.getLogger(TestUISchemaBuilder.class.getName());

    @Test
    public void testHorizontalLayout() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.HORIZONTAL);
        builder.addElements(new String[] { "firstName", "lastName", "role" });

        String json = builder.build();

        assertNotNull(json);

        logger.info(json);

    }

    @Test
    public void testHorizontalLayoutWithLabel() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.HORIZONTAL);
        builder.addElement("firstName", "First Name", null). //
                addElements("lastName", "role");

        String json = builder.build();

        assertNotNull(json);

        logger.info(json);

    }

    /**
     * Test a simple CATEGORIZATION layout with one tab
     */
    @Test
    public void testCategorization() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.CATEGORIZATION);

        builder.addCategory("Cat-1"). //
                addLayout(Layout.HORIZONTAL). //
                addElements(new String[] { "firstName", "lastName", "role" });

        String json = builder.build();

        assertNotNull(json);
        // assertTrue(json.contains("#/properties/lastName"));
        logger.info(json);

    }

    /**
     * A Category with mixed layout sections
     */
    @Test
    public void testCategorizationMixed() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.CATEGORIZATION);

        builder.addCategory("Cat-1"). //
                addLayout(Layout.HORIZONTAL). //
                addElements(new String[] { "firstName", "lastName", "role" });

        builder.addLayout(Layout.VERTICAL). //
                addElements(new String[] { "age", "city" });

        String json = builder.build();

        assertNotNull(json);
        // assertTrue(json.contains("#/properties/lastName"));
        logger.info(json);

    }

    @Test
    public void testCategorization2Sections() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.CATEGORIZATION);

        builder.addCategory("Cat-1"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("color", "rule"). //
                addCategory("Cat-2"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("firstName", "lastName", "role");

        String json = builder.build();

        assertNotNull(json);

        logger.info(json);

    }

    @Test
    public void testCategorization2SectionsMixed() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.CATEGORIZATION);

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        builder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name"). //
                addElement("documentation", "Documentation", multilineOption). //

                addLayout(Layout.VERTICAL). //
                addElements(new String[] { "age", "city" }). //

                addCategory("Event"). //
                addLayout(Layout.VERTICAL). //
                addElements(new String[] { "a", "b" }). //

                addCategory("Workflow"). //
                addLayout(Layout.HORIZONTAL);

        String json = builder.build();

        assertNotNull(json);

        logger.info(json);

    }

    /**
     * Test the creation of tabs with a missing layout. So we expect the default
     * VerticalLayout
     */
    @Test
    public void testCategorization2SectionsWithoutLayout() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.CATEGORIZATION);

        builder.addCategory("Cat-1"). //
        // missing Layout
                addElements("color", "rule"). //
                addCategory("Cat-2"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("firstName", "lastName", "role");

        String json = builder.build();

        assertNotNull(json);
        logger.info(json);
        assertTrue(json.contains("VerticalLayout"));

    }

    /**
     * Test the LaneSet layout
     */
    @SuppressWarnings("unused") 
    @Test
    public void testLaneSetLayout() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.CATEGORIZATION);

        Map<String, Object> arrayDetailOption = new HashMap<>();

        // detail : 'GENERATED'
        // arrayDetailOption.put("detail", "GENERATED");

//        "type": "HorizontalLayout",
//        "elements": [
//            {
//                "type": "Control",
//                "scope": "#/properties/name"
//            },
//            {
//                "type": "Control",
//                "scope": "#/properties/documentation",
//                "label": "Documentation",
//                "options": {
//                    "multi": true
//                }
//            }
//        ]

        JsonObjectBuilder layoutBuilder = Json.createObjectBuilder().add("type", "HorizontalLayout");
        JsonArrayBuilder controlsArrayBuilder = Json.createArrayBuilder();

        JsonObject multiOptions = Json.createObjectBuilder() //
                .add("multi", true).build();

        controlsArrayBuilder //
                .add(Json.createObjectBuilder() //
                        .add("type", "Control") //
                        .add("scope", "#/properties/name"))//
                .add(Json.createObjectBuilder() //
                        .add("type", "Control") //
                        .add("scope", "#/properties/documentation") //
                        .add("label", "Documentation") //
                        .add("options", multiOptions) //
                );
        layoutBuilder.add("elements", controlsArrayBuilder);
        JsonObjectBuilder detailBuilder = Json.createObjectBuilder(). //
                add("detail", layoutBuilder.build());

        builder.addCategory("Lanes") //
                .addLayout(Layout.VERTICAL);

        builder.addDetailLayout("lanes", "Lanes", detailBuilder.build());

        String json = builder.build();

        assertNotNull(json);

        logger.info(json);
    }

    /**
     * Test create controls with options
     */
    @Test
    public void testOptions() {

        UISchemaBuilder builder = new UISchemaBuilder(Layout.HORIZONTAL);

        Map<String, String> options = new HashMap<>();
        options.put("multi", "true");

        builder.addElements("firstName", "lastName");

        builder.addElement("description", "Description", options);
        String json = builder.build();

        assertNotNull(json);

        logger.info(json);

    }

    /**
     * Test build a native json structure for array details
     */
    @Test
    public void testDetailBuilder() {
//  detail : {
//      "type": "HorizontalLayout",
//      "elements": [
//          {
//              "type": "Control",
//              "scope": "#/properties/name"
//          },
//          {
//              "type": "Control",
//              "scope": "#/properties/documentation",
//              "label": "Documentation",
//              "options": {
//                  "multi": true
//              }
//          }
//      ]
//    }

        JsonObjectBuilder layoutBuilder = Json.createObjectBuilder().add("type", "HorizontalLayout");

        JsonArrayBuilder controlsArrayBuilder = Json.createArrayBuilder();

        JsonObjectBuilder controlBuilder = Json.createObjectBuilder(). //
                add("type", "Control"). //
                add("scope", "#/properties/");

        controlsArrayBuilder.add(controlBuilder);

        layoutBuilder.add("elements", controlsArrayBuilder);

        JsonObjectBuilder detailBuilder = Json.createObjectBuilder(). //
                add("detail", layoutBuilder.build());

        JsonObject jsonObject = detailBuilder.build();

        assertNotNull(jsonObject);
        logger.info("" + jsonObject);

    }
}
