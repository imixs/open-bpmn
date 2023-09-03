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
import org.eclipse.glsp.graph.GDimension;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.bpmn.IconGCompartment;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.elements.IconGCompartmentBuilder;
import org.openbpmn.glsp.model.BPMNGModelState;

/**
 * The BPMNGraphUtil provides helper methods to create GNode Elements and
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
    public static GNode createMultiLineTextNode(final BPMNGNode node, final String text, final String align) {
        return new GNodeBuilder(ModelTypes.BPMN_TEXT_NODE). //
                id(node.getId() + "_multiline"). //
                addArgument("text", text). //
                addArgument("align", align). //
                build();
    }

    public static GCompartment createMultiLineTextNodex(final BPMNGNode node, final String text) {
        Map<String, Object> layoutOptions = new HashMap<>();
        layoutOptions.put(V_GRAB, true);
        layoutOptions.put(GLayoutOptions.KEY_V_ALIGN, "center");
        return new GCompartmentBuilder(ModelTypes.BPMN_TEXT_NODE) //
                .id(node.getId() + "_multiline") //
                .layout(GConstants.Layout.FREEFORM) //
                .layoutOptions(layoutOptions) //
                .addArgument("text", text) //
                .build();
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
                .layout(GConstants.Layout.FREEFORM) //
                .layoutOptions(layoutOptions) //
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
            bpmnProcess = modelState.getBpmnModel().openDefaultProces();
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

    /**
     * This helper method optimizes the height of a BPMNLabel element based on the
     * length of the text. The method splits the text with New-Lines between words.
     * 
     * @param label
     * @param bpmnLabel
     * @param text
     */
    public static void optimizeBPMNLabelHeight(LabelGNode label, BPMNLabel bpmnLabel, String text) {
        int FONT_SIZE = 14;

        // resize based on the lines....
        int estimatedLines = estimateLineCount(text, FONT_SIZE, 100);
        GDimension newLabelSize = GraphUtil.dimension(100, FONT_SIZE * estimatedLines);
        label.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, newLabelSize.getWidth());
        label.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, newLabelSize.getHeight());
        // calling the size method does not have an effect.
        // see:
        // https://github.com/eclipse-glsp/glsp/discussions/741#discussioncomment-3688606
        label.setSize(newLabelSize);
        // Update the BPMNLabel bounds...
        try {
            if (bpmnLabel != null) {
                bpmnLabel.getBounds().setDimension(newLabelSize.getWidth(), newLabelSize.getHeight());
            }
        } catch (BPMNMissingElementException e) {
            e.printStackTrace();
        }
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
