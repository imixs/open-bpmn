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
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNInvalidTypeException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.bpmn.IconGCompartment;
import org.openbpmn.glsp.elements.IconGCompartmentBuilder;
import org.openbpmn.glsp.model.BPMNGModelState;

/**
 * The BPMNGModelUtil provides helper methods to create GNode Elements and
 * resolve GNodes within a bpmn diagram
 *
 * @author rsoika
 *
 */
public class BPMNGModelUtil {

    private static Logger logger = LogManager.getLogger(BPMNGModelUtil.class);

    private static final String V_GRAB = "vGrab";
    private static final String H_GRAB = "hGrab";
    public static final String MULTILINETEXT_ALIGN_LEFT = "left";
    public static final String MULTILINETEXT_ALIGN_RIGHT = "right";
    public static final String MULTILINETEXT_ALIGN_MIDDLE = "middle";

    public static IconGCompartment createCompartmentIcon(final BPMNGNode node) {
        return new IconGCompartmentBuilder(). //
                id(node.getId() + "_icon"). //
                build();
    }

    /**
     * Creates a Multiline GLable node
     *
     * @param id
     * @param text
     * @return - GNode element containing the text
     */
    public static GNode createMultiLineTextNode(final BPMNGNode node, final String text, final String align,
            int yOffset) {
        return new GNodeBuilder(ModelTypes.BPMN_TEXT_NODE). //
                id(node.getId() + "_multiline"). //
                position(0, yOffset). //
                addArgument("text", text). //
                addArgument("align", align). //
                build();
    }

    /**
     * Creates a Lane-Divider GNode. The parameters ymin and ymax are stored as
     * attributes in the gnode
     *
     * @param participant - containing Pool element
     * @param y           - y position of the divider
     * @param yMin        - minimum y position for resizing
     * @param yMax        - maximum y position for resizing
     * @param upperLaneId - upper lane id
     * @param lowerLaneId - lower lane id
     * 
     */
    public static GNode createLaneDivider(final Participant participant, final double y, final double yMin,
            final double yMax, String upperLaneId, String lowerLaneId) {
        double width = 100;
        BPMNBounds bounds;
        try {
            bounds = participant.getBounds();
            width = bounds.getDimension().getWidth();
        } catch (BPMNMissingElementException e) {
            e.printStackTrace();
        }
        // create a random id
        String dividerID = BPMNModel.generateShortID("divider");
        return new GNodeBuilder(ModelTypes.LANE_DIVIDER). //
                id(dividerID). //
                position(30, y). //
                size(width, 5). //
                addArgument("ymin", yMin). //
                addArgument("ymax", yMax). //
                addArgument("upperlaneid", upperLaneId). //
                addArgument("lowerlaneid", lowerLaneId). //
                addCssClass("lane-divider"). //
                build();
    }

    /**
     * Creates a container for a MultiLineTextNode. This method is called by the
     * builder classes
     * 
     * @param node
     * @return
     */
    public static GCompartment createMultiLineContainer(final BPMNGNode node) {
        Map<String, Object> layoutOptions = new HashMap<>();
        layoutOptions.put(V_GRAB, true);
        layoutOptions.put(H_GRAB, true);
        // layoutOptions.put(GLayoutOptions.KEY_H_GAP, 0);
        // layoutOptions.put(GLayoutOptions.KEY_V_GAP, 0);
        layoutOptions.put(GLayoutOptions.KEY_RESIZE_CONTAINER, false);

        return new GCompartmentBuilder()
                .type(DefaultTypes.COMPARTMENT)
                .position(1, 1) //
                .size(node.getSize().getWidth() - 2, node.getSize().getHeight() - 2)
                .layoutOptions(layoutOptions)
                .addCssClass("label-container")
                .build();
    }

    /**
     * Creates the Header for a BPMNPool or BPMNLane. The layout is defined by
     * custom element-views on the client side.
     *
     * @param node
     * @return GCompartment
     */
    public static GCompartment createBPMNContainerHeader(final BPMNGNode node) {
        Map<String, Object> layoutOptions = new HashMap<>();
        layoutOptions.put(V_GRAB, true);
        layoutOptions.put(GLayoutOptions.KEY_V_ALIGN, "center");
        return new GCompartmentBuilder(node.getType() + "_header") //
                .id(node.getId() + "_header") //
                .layout(GConstants.Layout.FREEFORM) //
                .layoutOptions(layoutOptions) //
                .addCssClass(node.getType() + "_header") //
                .build();
    }

    /**
     * This method tests if the given element has a BPMN extension label.
     * The extension label is used for example in task nodes to display additional
     * information
     * 
     * @return GLabel of an element or null if no GLabel was found
     */
    public static GLabel findExtensionLabel(final BPMNGNode element) {

        EList<GModelElement> childs = element.getChildren();
        for (GModelElement child : childs) {
            if (child instanceof GCompartment) {
                // test css class
                if (child.getCssClasses().contains("extension")) {
                    // return first label of this compartment
                    return (GLabel) child.getChildren().get(0);
                }
            }
        }
        // we did not found a extension GLabel
        return null;
    }

    /**
     * This method returns a BPMN extension GCompartment.
     * An Extension is used by Tasks to display additional information
     * 
     * @return GLabel of an element or null if no GLabel was found
     */
    public static GCompartment findExtensionCompartment(final BPMNGNode element) {

        EList<GModelElement> childs = element.getChildren();
        for (GModelElement child : childs) {
            if (child instanceof GCompartment) {
                // test css class
                if (child.getCssClasses().contains("extension")) {
                    // return first label of this compartment
                    return (GCompartment) child;
                }
            }
        }
        // we did not found a extension GCompartment
        return null;
    }

    /**
     * This method tests if the given element has a Child of type bpmn-text-node.
     * In this case the method returns the
     * GNode. Otherwise the method returns null.
     *
     * @return GNode of an element or null if no bpmn-text-node was found
     */
    public static GNode findMultiLineTextNode(final GModelElement element) {

        EList<GModelElement> childs = element.getChildren();
        for (GModelElement child : childs) {

            if (child instanceof GNode) {
                if (ModelTypes.BPMN_TEXT_NODE.equals(child.getType())) {
                    return (GNode) child;
                }
            }
            if (child.getChildren().size() > 0) {
                GNode subResult = findMultiLineTextNode(child);
                if (subResult != null) {
                    return subResult;
                }
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
                    bpmnProcess = bpmnParticipant.getBpmnProcess();
                }
            }
        }
        return bpmnProcess;
    }

    /**
     * Helper method resolves the containing BPMNProcess by a given Drop Point.
     * This can be either the default process or the process of a Participant
     * (Pool).
     * <p>
     * The method computes the matching process by testing the DropPoint with the
     * dimensions of the existing BPMN Pools (Participants). In case the model is
     * not a collaboration diagram, the method always returns the default process.
     * <p>
     */
    public static BPMNProcess findProcessByPoint(final BPMNGModelState modelState, final GPoint dropPoint)
            throws BPMNInvalidTypeException {
        if (!modelState.getBpmnModel().isCollaborationDiagram() || dropPoint == null) {
            return modelState.getBpmnModel().openDefaultProcess();
        }
        Participant participant = modelState.getBpmnModel()
                .findParticipantByPoint(BPMNGModelUtil.createBPMNPoint(dropPoint));

        if (participant != null) {
            return participant.getBpmnProcess();
        } else {
            // default to the default process
            return modelState.getBpmnModel().openDefaultProcess();
        }
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

    /**
     * Helper method to estimate the number of lines a text will need in a panel
     * with a given width
     *
     * @param text
     * @param fontSize
     * @param width
     * @return estimated number of lines
     */
    public static int estimateLineCount(final String text, final int fontSize, final int width) {
        int result = 0;
        // first split the text by hard line breaks...
        String[] paragraphs = text.split("\n");

        // Estimate the number of characters per line based on the font size and the
        // given width
        double charactersPerLine = width / (fontSize * 0.5);

        // Estimate the number of lines per paragraph based on the number of characters
        for (String paragraph : paragraphs) {
            int lineCount = (int) Math.ceil(paragraph.length() / charactersPerLine);
            result += lineCount;
        }
        return result;
    }

}
