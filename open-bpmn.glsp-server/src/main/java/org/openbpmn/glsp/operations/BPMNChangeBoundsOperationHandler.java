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
package org.openbpmn.glsp.operations;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GDimension;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.ChangeBoundsOperation;
import org.eclipse.glsp.server.types.ElementAndBounds;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNBounds;
import org.openbpmn.bpmn.elements.BPMNFlowElement;
import org.openbpmn.bpmn.elements.BPMNLabel;
import org.openbpmn.bpmn.elements.BPMNLane;
import org.openbpmn.bpmn.elements.BPMNParticipant;
import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This OperationHandler is used to update the BPMN model regarding BPMNBounds
 * elements.
 * <p>
 * The operation synchronizes the BPMN Model by updating the BPMNBounds element
 * (position and dimension) for all BPMNFlow elements. In addition the operator
 * also updates the GModel. In this way we avoid a complete new initialization
 * of the GModel which is not necessary in this scenario.
 * <p>
 * For BPMN Event and Gateway elements we have in addition a special handling of
 * the corresponding label. In case only the label was moved (id ends with
 * "_bpmnlabel") then the method updates the Label position for these elements
 * in the BPMN source model. In case a event or gateway symbol was selected, the
 * label is moved automatically. This works because we select the label with a
 * selection listener in those cases. (See the client implementation
 * BPMNLabelNodeSelectionListener)
 * <p>
 * In case the Bounds of a Pool are updated, we also update an embedded laneSet.
 * <p>
 * Updating the dimensions of a GNode is done by updating the node properties
 * GLayoutOptions.KEY_PREF_WIDTH and GLayoutOptions.KEY_PREF_HEIGHT. Calling
 * size() does not have any effect for GNodes with the HBox or VBox layout. See
 * also discussion here:
 * https://github.com/eclipse-glsp/glsp/discussions/741#discussioncomment-3688606
 *
 * @author rsoika
 *
 */
public class BPMNChangeBoundsOperationHandler extends AbstractOperationHandler<ChangeBoundsOperation> {
    private static Logger logger = Logger.getLogger(BPMNChangeBoundsOperationHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    /**
     * Update the bounds for all selected elements in the GModel and also in the
     * BPMNModel
     */
    @Override
    public void executeOperation(final ChangeBoundsOperation operation) {
        // iterate over all new Bounds...
        logger.info("=== ChangeBoundsOperation - " + operation.getNewBounds().size() + " new bounds");

        try {
            List<ElementAndBounds> elementBounds = operation.getNewBounds();
            for (ElementAndBounds elementBound : elementBounds) {
                String id = elementBound.getElementId();

                GPoint newPoint = elementBound.getNewPosition();
                GDimension newSize = elementBound.getNewSize();

                // find the corresponding GNode Element
                Optional<GNode> _node = modelState.getIndex().findElementByClass(id, GNode.class);
                if (!_node.isPresent()) {
                    // this case should not happen!
                    logger.severe("GNode '" + id + "' not found in current modelState!");
                    continue;
                }

                GNode gNode = _node.get();
                // first we compute the x/x offset
                double offsetX = newPoint.getX() - gNode.getPosition().getX();
                double offsetY = newPoint.getY() - gNode.getPosition().getY();
                double offsetWidth = newSize.getWidth() - gNode.getSize().getWidth();
                double offsetHeight = newSize.getHeight() - gNode.getSize().getHeight();
                logger.info("...bounds update for: " + id + " Offset= x:" + offsetX + " y:" + offsetY + " width: "
                        + newSize.getWidth() + " height: " + newSize.getHeight());

                // Update GNode according to the new position....
                gNode.setPosition(newPoint);
                // Update GNode dimension....
                gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, newSize.getWidth());
                gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, newSize.getHeight());
                // calling the size method does not have an effect.
                // see:
                // https://github.com/eclipse-glsp/glsp/discussions/741#discussioncomment-3688606
                gNode.setSize(newSize);

                // Now update the corresponding BPMN element
                BPMNBounds bpmnBounds = modelState.getBpmnModel().findBPMNBoundsById(id);
                if (bpmnBounds != null) {

                    // finally update BPMNElement bounds....
                    bpmnBounds.setPosition(bpmnBounds.getPosition().getX() + offsetX,
                            bpmnBounds.getPosition().getY() + offsetY);
                    bpmnBounds.setDimension(newSize.getWidth(), newSize.getHeight());

                    // if we have a Participant element selected than we need to update
                    // all embedded BPMNFlow Elements and also the LaneSet if available.
                    BPMNParticipant participant = modelState.getBpmnModel().findBPMNParticipantById(id);
                    if (participant != null) {
                        logger.info("...update participant pool elements: offset= " + offsetX + " , " + offsetY);
                        BPMNProcess process = participant.openProcess();

                        updateLaneSet(participant, offsetWidth, offsetHeight);
                        updateFlowElements(process, offsetX, offsetY, offsetWidth, offsetHeight);
                    }

                } else {
                    // test if we have a BPMNLable element was selected?
                    if (id.endsWith("_bpmnlabel")) {
                        // yes, update the GLabel position
                        gNode.setPosition(newPoint);

                        // update the BPMNLabel bounds
                        // Therefor we need first find the corresponding BPMNFlowElement
                        String labelID = id.substring(0, id.lastIndexOf("_bpmnlabel"));
                        BPMNBaseElement bpmnBaseElement = modelState.getBpmnModel().findBPMNBaseElementById(labelID);
                        BPMNLabel bpmnLabel = ((BPMNFlowElement) bpmnBaseElement).getLabel();
                        if (bpmnLabel != null) {
                            BPMNPoint labelPoint = bpmnLabel.getBounds().getPosition();
                            bpmnLabel.updateLocation(labelPoint.getX() + offsetX, labelPoint.getY() + offsetY);
                        }
                    }
                }

            }
        } catch (BPMNMissingElementException e) {
            e.printStackTrace();
        }
        // no more action - the GModel is now up to date
    }

    /**
     * This helper method updates the bounds of all lanes in the current process by
     * a given x/y offset and width/height offset.
     * <p>
     * When the dimensions of a pool changes, than we compute a new size for all
     * lanes part of the current laneSet.
     *
     * @param process
     * @param offsetX
     * @param offsetY
     * @param offsetWidth
     * @param offsetHeight
     * @throws BPMNMissingElementException
     */
    void updateLaneSet(final BPMNParticipant participant, final double offsetWidth, final double offsetHeight)
            throws BPMNMissingElementException {
        BPMNProcess process = participant.openProcess();
        Set<BPMNLane> bpmnLaneSet = process.getLanes();

        String processID = process.getId();
        logger.info("update laneSet for Process : " + processID);
        if (bpmnLaneSet.size() == 0) {
            return; // no op
        }

        // first we update the position of all Lanes if available
        int laneCount = process.getLanes().size();
        int offsetHeightRatio = (int) (offsetHeight / laneCount);
        BPMNBounds poolBounds = participant.getBounds();
        logger.info("  ===> Pool Height = " + poolBounds.getDimension().getHeight());

        int bpmnLaneX = (int) (poolBounds.getPosition().getX() + BPMNParticipant.POOL_OFFSET);
        int bpmnLaneY = (int) poolBounds.getPosition().getY();
        int bpmnLaneWidth = (int) (poolBounds.getDimension().getWidth() - BPMNParticipant.POOL_OFFSET);
        int gLaneX = (int) BPMNParticipant.POOL_OFFSET;
        int gLaneY = 0;
        int maxLaneHeight = (int) poolBounds.getDimension().getHeight();

        Iterator<BPMNLane> laneSetIterator = bpmnLaneSet.iterator();
        while (laneSetIterator.hasNext()) {
            // get BPMNBounds and GNode for this lane...
            BPMNLane lane = laneSetIterator.next();
            BPMNBounds bpmnLaneBounds = lane.getBounds();
            GNode gNode = null;
            Optional<GNode> _node = modelState.getIndex().findElementByClass(lane.getId(), GNode.class);
            if (_node.isPresent()) {
                gNode = _node.get();
            }
            if (bpmnLaneBounds == null || gNode == null) {
                logger.warning("invalid LaneSet - model can not be synchronized!");
                continue;
            }

            if (offsetWidth == 0 && offsetHeight == 0) {
                // Update absolute BPMN position
                bpmnLaneBounds.setPosition(bpmnLaneX, bpmnLaneY);
                // adjust laneY for the next iteration
                bpmnLaneY = (int) (bpmnLaneY + bpmnLaneBounds.getDimension().getHeight());
                // no further update needed
                continue;
            }

            // Update absolute BPMN position & dimension
            int laneHeight = (int) (bpmnLaneBounds.getDimension().getHeight() + offsetHeightRatio);

            // it can happen, that the height it to large for the current pool height. We
            // check this and adjust the height if needed.
            if ((!laneSetIterator.hasNext() && laneHeight != maxLaneHeight) || (laneHeight > maxLaneHeight)) {
                logger.info("BPMNlane height mismatch : current=" + laneHeight + "  max available=" + maxLaneHeight);
                laneHeight = maxLaneHeight;
                logger.info("new lane height : " + laneHeight);
            }

            bpmnLaneBounds.setDimension(bpmnLaneWidth, laneHeight);

            logger.info("  ===> Lane " + lane.getId() + " new Height = " + bpmnLaneBounds.getDimension().getHeight());

            bpmnLaneBounds.setPosition(bpmnLaneX, bpmnLaneY);
            // adjust laneY for the next iteration
            bpmnLaneY = (int) (bpmnLaneY + bpmnLaneBounds.getDimension().getHeight());

            GDimension newLaneSize = GraphUtil.dimension(bpmnLaneWidth, laneHeight);
            gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, newLaneSize.getWidth());
            gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, newLaneSize.getHeight());
            // calling the size method does not have an effect.
            // see:
            // https://github.com/eclipse-glsp/glsp/discussions/741#discussioncomment-3688606
            gNode.setSize(newLaneSize);

            gNode.setPosition(GraphUtil.point(gLaneX, gLaneY));
            // adjust gLaneY
            gLaneY = gLaneY + laneHeight;
            maxLaneHeight = maxLaneHeight - laneHeight;

        }

    }

    /**
     * This method updates the position for all BPMNFlowElements contained in a pool
     * given a x and y offset.
     *
     * @param process
     * @param participant
     * @throws BPMNMissingElementException
     */
    void updateFlowElements(final BPMNProcess process, final double offsetX, final double offsetY,
            final double offsetWidth, final double offsetHeight) throws BPMNMissingElementException {

        // Update all FlowElements
        Set<BPMNFlowElement> bpmnFlowElements = process.getBPMNFlowElements();
        for (BPMNFlowElement flowElement : bpmnFlowElements) {
            logger.info("update element bounds: " + flowElement.getId());
            try {
                BPMNBounds bounds = flowElement.getBounds();
                if (bounds != null) {
                    bounds.setPosition(bounds.getPosition().getX() + offsetX, bounds.getPosition().getY() + offsetY);
                }
                // if the flowElemen has a BPMNLabel element we adjust position of the label too
                BPMNLabel bpmnLabel = flowElement.getLabel();
                if (bpmnLabel != null) {
                    bpmnLabel.updateLocation(bpmnLabel.getPosition().getX() + offsetX,
                            bpmnLabel.getPosition().getY() + offsetY);
                }

            } catch (BPMNMissingElementException e) {
                logger.warning("Failed to update FlowElement bounds for : " + flowElement.getId());
            }
        }

    }
}