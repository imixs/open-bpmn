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
package org.openbpmn.glsp.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.bpmn.IconGCompartment;
import org.openbpmn.glsp.elements.IconGCompartmentBuilder;
import org.openbpmn.glsp.model.BPMNGModelState;

/**
 * The BPMNGraphUtil provides helper methods to create GNode Elements and
 * resolve GNodes within a bpmn diagram
 *
 * @author rsoika
 *
 */
public class BPMNGraphUtil {

    private static Logger logger = LogManager.getLogger(BPMNGraphUtil.class);

    private static final String V_GRAB = "vGrab";

    public static IconGCompartment createCompartmentIcon(final BPMNGNode node) {
        return new IconGCompartmentBuilder(). //
                id(node.getId() + "_icon"). //
                build();
    }

    /**
     * Creates a GLabel for the name of a BaseElement
     *
     * @param node
     * @return GPort
     */
    @Deprecated
    public static GLabel createCompartmentHeader(final BPMNGNode node) {
        return new GLabelBuilder(ModelTypes.LABEL_HEADING) //
                .id(node.getId() + "_header") //
                .text(node.getName()) //
                .build();
    }

    /**
     * Creates a Multiline GLable node
     *
     * @param id
     * @param text
     * @return - GNode element containing the text
     */
    public static GNode createMultiLineTextNode(final String id, final String text) {
        return new GNodeBuilder("bpmn-text-node"). //
                id(id). //
                addArgument("text", text). //
                build();
    }

    /**
     * Creates the Header for a BPMNPool or BPMNLane
     *
     * @param node
     * @return GCompartment
     */
    public static GCompartment createBPMNContainerHeader(final BPMNGNode node) {
        Map<String, Object> layoutOptions = new HashMap<>();
        layoutOptions.put(V_GRAB, true);
        layoutOptions.put(GLayoutOptions.KEY_V_ALIGN, "center");

        return new GCompartmentBuilder(ModelTypes.COMP_HEADER) //
                .id(node.getId() + "_header") //
                .layout(GConstants.Layout.STACK) //
                .layoutOptions(layoutOptions) //
                .build();
    }

    /**
     * This method tests if the given element has a Child of type GLabel. This is
     * the case for Task Elements. In this case the method returns the GLabel.
     * Otherwise the method returns null.
     *
     * @return GLabel of an element or null if no GLabel was found
     */
    @Deprecated
    public static GLabel findCompartmentHeader(final BPMNGNode element) {

        EList<GModelElement> childs = element.getChildren();
        for (GModelElement child : childs) {
            if (child instanceof GLabel) {
                // return Optional.of(child);
                return (GLabel) child;
            }
        }
        // we did not found a GLabel
        return null;
    }

    /**
     * Helper method computes the container BPMNProcess for a CreateNodeOperation.
     * The method first computes the GModel container Element. Than the method tests
     * if the container is the Model root or a BPMNPool. In the later case the
     * method computes the BPMNProcess from the corresponding BPMNParticipant.
     *
     * @param modelState - current model state
     * @param container  - the GModeElement
     * @return the corresponding BPMNProcess
     */
    public static BPMNProcess findProcessByContainer(final BPMNGModelState modelState, final GModelElement container) {
        String containerId = container.getId();
        logger.debug(" ==> Container ID : " + container.getId());

        BPMNProcess bpmnProcess = null;
        // is it the root?
        if (modelState.getRoot().getId().equals(containerId)) {
            bpmnProcess = modelState.getBpmnModel().openDefaultProcess();
        } else {
            // it should be a participant container
            if (containerId.startsWith("participant_")) {
                // compute participant
                String participantID = containerId.substring(0, containerId.lastIndexOf("_"));
                Participant bpmnParticipant = modelState.getBpmnModel().findParticipantById(participantID);
                if (bpmnParticipant != null) {
                    bpmnProcess = bpmnParticipant.openProcess();
                }
            }
        }
        return bpmnProcess;
    }

    /**
     * Creates a BPMNPoint from a GPoint
     *
     * @param point
     * @return - bpmn point
     */
    public static BPMNPoint createBPMNPoint(final GPoint point) {
        if (point == null) {
            return null;
        }
        return new BPMNPoint(point.getX(), point.getY());
    }

    /**
     * This method returns true if a given GNodeID is assigned to a BPMNLabel
     * element
     *
     * @param id
     * @return true if the id is a bpmnlabel id
     */
    public static boolean isBPMNLabelID(final String id) {
        return id.endsWith("_bpmnlabel");
    }

    /**
     * Returns the FlowElent ID to a given BPMNLabel ID
     *
     * @param id
     * @return the id of the assigned BPMN Flow Element or null if no such a element
     *         exits.
     */
    public static String resolveFlowElementIDfromLabelID(final String id) {
        if (isBPMNLabelID(id)) {
            return id.substring(0, id.lastIndexOf("_bpmnlabel"));
        }
        return null;
    }

}
