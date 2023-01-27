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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GModelElement;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.bpmn.LaneGNode;
import org.openbpmn.glsp.jsonforms.DataBuilder;
import org.openbpmn.glsp.jsonforms.SchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder;
import org.openbpmn.glsp.jsonforms.UISchemaBuilder.Layout;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This is the Default BPMNParticipant extension providing the JSONForms
 * shemata.
 *
 * @author rsoika
 *
 */
public class DefaultBPMNParticipantExtension extends AbstractBPMNElementExtension {

    private static Logger logger = LogManager.getLogger(DefaultBPMNParticipantExtension.class);

    @Inject
    protected BPMNGModelState modelState;

    public DefaultBPMNParticipantExtension() {
        super();
    }

    @Override
    public boolean handlesElementTypeId(final String elementTypeId) {
        return BPMNTypes.POOL.equals(elementTypeId);
    }

    /**
     * This Extension is for BPMNActivities only
     */
    @Override
    public boolean handlesBPMNElement(final BPMNElement bpmnElement) {
        return (bpmnElement instanceof Participant);
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
                .addData("documentation", bpmnElement.getDocumentation());

        schemaBuilder. //
                addProperty("name", "string", null). //
                // addProperty("execution", "string", null). //
                addProperty("documentation", "string", "Participant description.");

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        uiSchemaBuilder. //
                addCategory("General"). //
                addLayout(Layout.HORIZONTAL). //
                addElements("name"). //
                addLayout(Layout.VERTICAL). //
                addElement("documentation", "Documentation", multilineOption);

        // LaneSet
        addLaneSetProperties((Participant) bpmnElement, dataBuilder, schemaBuilder, uiSchemaBuilder);

    }

    @Override
    public void updatePropertiesData(final JsonObject json, final BPMNElement bpmnElement,
            final GModelElement gNodeElement) {

        long l = System.currentTimeMillis();

        Participant participant = (Participant) bpmnElement;
        try {
            BPMNProcess process = modelState.getBpmnModel().openProcess(participant.getProcessRef());
            // check custom features
            Set<String> features = json.keySet();
            for (String feature : features) {
                if ("name".equals(feature)) {
                    bpmnElement.setName(json.getString(feature));
                    process.setName(json.getString(feature));
                    // Update Label...
                    ((BPMNGNode) gNodeElement).setName(json.getString(feature));
                    continue;
                }
                if ("documentation".equals(feature)) {
                    bpmnElement.setDocumentation(json.getString(feature));
                    continue;
                }

                // LaneSet...
                if ("lanes".equals(feature)) {
                    List<String> laneDataIDs = new ArrayList<>(); // collect remaining lanes
                    logger.debug("...update feature = " + feature);
                    JsonArray laneSetValues = json.getJsonArray(feature);
                    for (JsonValue laneValue : laneSetValues) {
                        // update lane properties
                        JsonObject laneData = (JsonObject) laneValue;
                        if (laneData.get("id") != null) {
                            String id = laneData.getJsonString("id").getString();
                            // String id = jsonID.toString();
                            laneDataIDs.add(id);
                            Lane bpmnLane = process.findLaneById(id);
                            if (bpmnLane != null) {
                                bpmnLane.setName(laneData.getString("name"));
                                bpmnLane.setDocumentation(laneData.getString("documentation"));
                                // update gnode...
                                Optional<GModelElement> _gLane = modelState.getIndex().get(bpmnLane.getId());
                                if (_gLane.isPresent()) {
                                    LaneGNode gLane = (LaneGNode) _gLane.get();
                                    gLane.setName(laneData.getString("name"));
                                }
                            }
                        } else {
                            // this is a new lane - construct the lane in the BPMN model first..
                            Lane bpmnLane = process.addLane("Lane " + (process.getLanes().size() + 1));
                            laneDataIDs.add(bpmnLane.getId());
                            modelState.reset();
                        }
                    }
                    // now we need to delete all lanes no longer part of the laneSetValues
                    // We need two loops here to avoid concurrency exceptions!
                    List<String> laneIDsToBeRemoved = new ArrayList<>();
                    for (Lane bpmnLane : process.getLanes()) {
                        if (bpmnLane != null && !laneDataIDs.contains(bpmnLane.getId())) {
                            laneIDsToBeRemoved.add(bpmnLane.getId());
                        }
                    }
                    if (laneIDsToBeRemoved.size() > 0) {
                        modelState.reset();
                        for (String lid : laneIDsToBeRemoved) {
                            // lane was removed, so remove the lane form the process too
                            process.deleteLane(lid);
                        }
                    }
                }
            }
        } catch (BPMNModelException e) {
            logger.error("Failed to update laneSet properties: " + e.getMessage());
        }

        logger.debug("laneSet update in " + (System.currentTimeMillis() - l) + "ms");

    }

    /**
     * Adds the LaneSet schema properties
     *
     * @param eventDefinitions
     * @param dataBuilder
     * @param schemaBuilder
     * @param uiSchemaBuilder
     */
    private void addLaneSetProperties(final Participant participant, final DataBuilder dataBuilder,
            final SchemaBuilder schemaBuilder, final UISchemaBuilder uiSchemaBuilder) {

        Map<String, String> multilineOption = new HashMap<>();
        multilineOption.put("multi", "true");
        BPMNProcess process = participant.openProcess();

        if (process.hasLanes()) {
            Map<String, String> arrayDetailOption = new HashMap<>();
            // GENERATED | DEFAULT | HorizontalLayout
            arrayDetailOption.put("detail", "VerticalLayout");

            uiSchemaBuilder. //
                    addCategory("Lanes"). //
                    addLayout(Layout.VERTICAL);
            // uiSchemaBuilder.addElement("lanes", "Lanes", arrayDetailOption);
            // build the detail schema
            JsonObject multiOptions = Json.createObjectBuilder() //
                    .add("multi", true).build();
            JsonObjectBuilder layoutBuilder = Json.createObjectBuilder().add("type", "VerticalLayout");
            JsonArrayBuilder controlsArrayBuilder = Json.createArrayBuilder();
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

            uiSchemaBuilder.addDetailLayout("lanes", "Lanes", detailBuilder.build());

            // addDetailLayout

            //

            // Schema builder
            schemaBuilder.addArray("lanes");
            schemaBuilder.addProperty("name", "string", null, null);
            schemaBuilder.addProperty("documentation", "string", null, null);

            /*
             * Now we can create the data structure - each lane is represented as a separate
             * object
             */
            dataBuilder.addArray("lanes");
            for (Lane bpmnLane : process.getLanes()) {
                dataBuilder.addObject();
                dataBuilder.addData("name", bpmnLane.getName());
                dataBuilder.addData("documentation", bpmnLane.getDocumentation());
                dataBuilder.addData("id", bpmnLane.getId());
            }
        }

    }

}
