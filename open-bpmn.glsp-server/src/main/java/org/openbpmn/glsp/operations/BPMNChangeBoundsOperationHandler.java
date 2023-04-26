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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GDimension;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.ChangeBoundsOperation;
import org.eclipse.glsp.server.types.ElementAndBounds;
import org.openbpmn.bpmn.elements.Association;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNInvalidTypeException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.PoolGNode;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.utils.BPMNGraphUtil;

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
 * In case the new bounds are now contained by another Participant or dropped
 * outside of Participant, than the method verifies if the BPMN element need to
 * be placed in a new process within the bpmn model.
 *
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

    private static Logger logger = LogManager.getLogger(BPMNChangeBoundsOperationHandler.class);

    @Inject
    protected BPMNGModelState modelState;

    /**
     * Update the bounds for all selected elements in the GModel and also in the
     * BPMNModel.
     * <p>
     * BPMNLabels and the corresponding element can appear in the same selection. In
     * this case we remove the label from the selection because the
     * updateFlowElement method treads the label automatically.
     */
    @Override
    public void executeOperation(final ChangeBoundsOperation operation) {
        // iterate over all new Bounds...
        logger.debug("=== ChangeBoundsOperation - " + operation.getNewBounds().size() + " new bounds");

        try {
            List<ElementAndBounds> elementBounds = operation.getNewBounds();

            // first sort out all elementBounds from BPMNLabel objects if the flowElment is
            // part of this selection (see method updateFlowElement)
            List<ElementAndBounds> filteredElementBounds = filterElements(elementBounds);

            for (ElementAndBounds elementBound : filteredElementBounds) {
                String id = elementBound.getElementId();
                GPoint newPoint = elementBound.getNewPosition();
                GDimension newSize = elementBound.getNewSize();
                // find the corresponding GNode Element
                Optional<GNode> _node = modelState.getIndex().findElementByClass(id, GNode.class);
                if (!_node.isPresent()) {
                    // this case should not happen!
                    logger.error("GNode '" + id + "' not found in current modelState!");
                    continue;
                }

                GNode gNode = _node.get();
                // compute the new offset x/y
                double offsetX = newPoint.getX() - gNode.getPosition().getX();
                double offsetY = newPoint.getY() - gNode.getPosition().getY();
                logger.debug("...bounds update for: " + id);

                // find the BPMNElementNode
                BPMNElementNode bpmnElementNode = (BPMNElementNode) modelState.getBpmnModel().findElementById(id);
                if (bpmnElementNode != null) {

                    if (bpmnElementNode instanceof Participant) {
                        updatePool(gNode, bpmnElementNode, id, newPoint, newSize);
                    } else {
                        // it is a normal bpmn flow element...
                        updateFlowElement(gNode, bpmnElementNode, elementBound, newPoint, newSize);
                    }
                } else {
                    // test if we have a BPMNLabel element was selected?
                    if (BPMNGraphUtil.isBPMNLabelID(id)) {
                        // find parent
                        String flowElementID = BPMNGraphUtil.resolveFlowElementIDfromLabelID(id);
                        BPMNElementNode _bpmnElement = modelState.getBpmnModel().findElementNodeById(flowElementID);
                        BPMNLabel bpmnLabel = _bpmnElement.getLabel();

                        updateLabel(gNode, bpmnLabel, offsetX, offsetY);
                    }
                }

            }
        } catch (BPMNMissingElementException | BPMNInvalidReferenceException | BPMNInvalidTypeException e) {
            e.printStackTrace();
        }
        // no more action - the GModel is now up to date
    }

    /**
     * This helper method updates the position and dimension of a bpmnElementNode.
     * The new absolute position can be computed with the given x/y offset in the
     * BPMN model.
     * <p>
     * In addition the method computes the absolute position on the diagram and
     * verifies if the bpmnElementNode has a new parent BPMN Pool. This is the case
     * when a Flow Element is dropped on a Pool or outside a Pool. In this case the
     * method changes the containing BPMN Process in the source model for this
     * element.
     * <p>
     * The method also takes care about a BPMNLable assigned to the Flow element and
     * adjusts the label position relative to the new element position.
     *
     * @param gNode
     * @param bpmnElementNode
     * @param elementBound
     * @param offsetX
     * @param offsetY
     * @param newSize
     * @throws BPMNInvalidTypeException
     * @throws BPMNMissingElementException
     * @throws BPMNInvalidReferenceException
     */
    private void updateFlowElement(final GNode gNode, final BPMNElementNode bpmnElementNode,
            final ElementAndBounds elementBound, final GPoint newPoint, final GDimension newSize)
            throws BPMNInvalidTypeException, BPMNMissingElementException, BPMNInvalidReferenceException {

        double offsetX = newPoint.getX() - gNode.getPosition().getX();
        double offsetY = newPoint.getY() - gNode.getPosition().getY();
        BPMNPoint oldBpmnPoint = bpmnElementNode.getBounds().getPosition();
        BPMNPoint newBpmnPoint = new BPMNPoint(oldBpmnPoint.getX() + offsetX, oldBpmnPoint.getY() + offsetY);

        // Updating the element bounds will automatically also update the Process and
        // Lane assignment...
        String oldProcessID = bpmnElementNode.getProcessId();
        bpmnElementNode.setBounds(newBpmnPoint.getX(), newBpmnPoint.getY(), newSize.getWidth(),
                newSize.getHeight());
        // boolean gNodeUpdate = true;
        if (!oldProcessID.equals(bpmnElementNode.getProcessId())) {
            // gNodeUpdate = false;
            modelState.reset();
        }

        // lets see if the offset is to large so that we should remove the waypoints
        // from edges..
        if (Math.abs(offsetX) > gNode.getSize().getWidth() * 0.5
                || Math.abs(offsetY) > gNode.getSize().getHeight() * 0.5) {

            Set<SequenceFlow> sequenceFlows = bpmnElementNode.getIngoingSequenceFlows();
            for (SequenceFlow sf : sequenceFlows) {
                sf.clearWayPoints();
            }
            sequenceFlows = bpmnElementNode.getOutgoingSequenceFlows();
            for (SequenceFlow sf : sequenceFlows) {
                sf.clearWayPoints();
            }
            // reset associations
            Set<Association> associations = bpmnElementNode.getAssociations();
            for (Association a : associations) {
                a.clearWayPoints();
            }
            // gNodeUpdate = false;
            modelState.reset();
        }

        /* */
        // Finally Update GNode dimension (if the model was not alreday reset)...
        if (modelState.isInitialized()) {
            gNode.setPosition(newPoint);

            gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH,
                    newSize.getWidth());
            gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT,
                    newSize.getHeight());
            // calling the size method does not have an effect.
            // see:
            // https://github.com/eclipse-glsp/glsp/discussions/741#discussioncomment-3688606
            gNode.setSize(newSize);
        }
        // if the flow Element has a BPMNLabel, than we need to adjust finally the
        // position of the label too
        if (bpmnElementNode.hasBPMNLabel()) {
            BPMNLabel bpmnLabel = bpmnElementNode.getLabel();
            Optional<GNode> _labelnode = modelState.getIndex()
                    .findElementByClass(bpmnElementNode.getId() + "_bpmnlabel", GNode.class);
            updateLabel(_labelnode.get(), bpmnLabel, offsetX, offsetY);
        }

    }

    /**
     * This helper method updates the position and dimension of a BPMN Pool and all
     * included BPMN Node Elements
     *
     * @param gNode
     * @param bpmnElementNode
     * @param id
     * @param offsetX
     * @param offsetY
     * @param newSize
     * @throws BPMNMissingElementException
     */
    private void updatePool(final GNode gNode, final BPMNElementNode bpmnElementNode, final String id,
            final GPoint newPoint, final GDimension newSize) throws BPMNMissingElementException {

        double offsetX = newPoint.getX() - gNode.getPosition().getX();
        double offsetY = newPoint.getY() - gNode.getPosition().getY();

        double offsetWidth = newSize.getWidth() - gNode.getSize().getWidth();
        double offsetHeight = newSize.getHeight() - gNode.getSize().getHeight();

        // Update GNode according to the new position....
        gNode.setPosition(newPoint);
        // Update GNode dimension....
        gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, newSize.getWidth());
        gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, newSize.getHeight());
        // calling the size method does not have an effect.
        // see:
        // https://github.com/eclipse-glsp/glsp/discussions/741#discussioncomment-3688606
        gNode.setSize(newSize);

        BPMNBounds bpmnBounds = bpmnElementNode.getBounds();
        // update BPMNElement bounds....
        // The BPMN Position is always absolute so we can simply update the element
        // bounds by the new offset and new dimensions.
        // @see https://github.com/imixs/open-bpmn/issues/208
        bpmnElementNode.setBounds(bpmnBounds.getPosition().getX() + offsetX,
                bpmnBounds.getPosition().getY() + offsetY, newSize.getWidth(),
                newSize.getHeight());

        // if we have a Participant element selected than we also need to update
        // all embedded FlowElements and also the LaneSet if available.
        Participant participant = modelState.getBpmnModel().findParticipantById(id);
        if (participant != null) {
            logger.debug("...update participant pool elements: offset= " + offsetX + " , " + offsetY);
            BPMNProcess process = participant.openProcess();
            updateLaneSet(participant, offsetWidth, offsetHeight);
            updateEmbeddedElementNodes(process, offsetX, offsetY);
        }
    }

    /**
     * This helper method updates the position of a BPMNLabel. The method expects
     * the x/y offset and first updates the absolute position in the BPMN plane.
     * Than the method computes the current container and the relative GPoint
     * position in the GModel.
     *
     * @param gNode
     * @param newPoint
     * @param id
     * @param offsetX
     * @param offsetY
     * @throws BPMNMissingElementException
     */
    private void updateLabel(final GNode gLabel, final BPMNLabel bpmnLabel, final double offsetX, final double offsetY)
            throws BPMNMissingElementException {

        if (bpmnLabel != null) {
            // now we can update the absolute position of the label
            BPMNPoint labelPoint = bpmnLabel.getBounds().getPosition();
            bpmnLabel.updateLocation(labelPoint.getX() + offsetX, labelPoint.getY() + offsetY);
        }

        // next update the GLabel position
        // if the label is in a container (Pool) we need to compute the relative
        // position
        GModelElement parent = gLabel.getParent();
        GPoint newLabelGPoint = null;
        double absoluteX = bpmnLabel.getPosition().getX();
        double absoluteY = bpmnLabel.getPosition().getY();

        // is it a pool?
        if (parent instanceof PoolGNode) {
            GPoint poolPosition = ((GNode) parent).getPosition();
            double relativeX = absoluteX - poolPosition.getX();
            double relativeY = absoluteY - poolPosition.getY();
            newLabelGPoint = GraphUtil.point(relativeX, relativeY);
        } else {
            // parent is the root plane
            newLabelGPoint = GraphUtil.point(absoluteX, absoluteY);
        }
        if (newLabelGPoint != null) {
            gLabel.setPosition(newLabelGPoint);
        }

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
    private void updateLaneSet(final Participant participant, final double offsetWidth, final double offsetHeight)
            throws BPMNMissingElementException {
        BPMNProcess process = participant.openProcess();
        Set<Lane> bpmnLaneSet = process.getLanes();

        String processID = process.getId();
        logger.debug("update laneSet for Process : " + processID);
        if (bpmnLaneSet.size() == 0) {
            return; // no op
        }

        // first we update the position of all Lanes if available
        int laneCount = process.getLanes().size();
        int offsetHeightRatio = (int) (offsetHeight / laneCount);
        BPMNBounds poolBounds = participant.getBounds();
        logger.debug("  ===> Pool Height = " + poolBounds.getDimension().getHeight());

        int bpmnLaneX = (int) (poolBounds.getPosition().getX() + Participant.POOL_OFFSET);
        int bpmnLaneY = (int) poolBounds.getPosition().getY();
        int bpmnLaneWidth = (int) (poolBounds.getDimension().getWidth() - Participant.POOL_OFFSET);
        int gLaneX = (int) Participant.POOL_OFFSET;
        int gLaneY = 0;
        int maxLaneHeight = (int) poolBounds.getDimension().getHeight();

        Iterator<Lane> laneSetIterator = bpmnLaneSet.iterator();
        while (laneSetIterator.hasNext()) {
            // get BPMNBounds and GNode for this lane...
            Lane lane = laneSetIterator.next();
            BPMNBounds bpmnLaneBounds = lane.getBounds();
            GNode gNode = null;
            Optional<GNode> _node = modelState.getIndex().findElementByClass(lane.getId(), GNode.class);
            if (_node.isPresent()) {
                gNode = _node.get();
            }
            if (gNode == null) {
                logger.warn("invalid LaneSet - model can not be synchronized!");
                continue;
            }

            if (offsetWidth == 0 && offsetHeight == 0) {
                // Update absolute BPMN position
                lane.setPosition(bpmnLaneX, bpmnLaneY);
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
                logger.warn("BPMNlane height mismatch : current=" + laneHeight + "  max available=" + maxLaneHeight);
                laneHeight = maxLaneHeight;
                logger.info("new lane height : " + laneHeight);
            }

            bpmnLaneBounds.setDimension(bpmnLaneWidth, laneHeight);

            logger.debug("  ===> Lane " + lane.getId() + " new Height = " + bpmnLaneBounds.getDimension().getHeight());

            lane.setPosition(bpmnLaneX, bpmnLaneY);
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
     * This method updates the position for all BPMNElementNodes contained in a pool
     * given a x and y offset. This method is needed because in BPMN all positions
     * are absolute and in GLSP the position of a element embedded in a container is
     * relative.
     *
     * @param process - the bpmnProcess containing the bpmn element nodes.
     * @param offsetX - new X offset
     * @param offsetY - new Y offset
     * @throws BPMNMissingElementException
     */
    void updateEmbeddedElementNodes(final BPMNProcess process, final double offsetX, final double offsetY)
            throws BPMNMissingElementException {

        // Update all FlowElements
        Set<BPMNElementNode> bpmnFlowElements = process.getAllElementNodes();
        for (BPMNElementNode flowElement : bpmnFlowElements) {
            logger.debug("update element bounds: " + flowElement.getId());
            try {
                BPMNBounds bounds = flowElement.getBounds();
                flowElement.setPosition(bounds.getPosition().getX() + offsetX, bounds.getPosition().getY() + offsetY);
                // if the flowElement has a BPMNLabel element we adjust position of the label
                // too
                BPMNLabel bpmnLabel = flowElement.getLabel();
                if (bpmnLabel != null) {
                    bpmnLabel.updateLocation(bpmnLabel.getPosition().getX() + offsetX,
                            bpmnLabel.getPosition().getY() + offsetY);
                }

            } catch (BPMNMissingElementException e) {
                logger.warn("Failed to update FlowElement bounds for : " + flowElement.getId());
            }
        }

    }

    /**
     * This helper method removes all BPMNLabels from a list of ElementAndBounds if
     * the corresponding Node is already part of the list.
     *
     * @param elementBounds
     * @return
     */
    private List<ElementAndBounds> filterElements(final List<ElementAndBounds> elementBounds) {
        // first sort out all elementBounds from BPMNLable objects if the flowElment is
        // part of this selection (see method updateFlowElement)
        List<ElementAndBounds> filteredElementBounds = new ArrayList<>();
        // copy all
        filteredElementBounds.addAll(elementBounds);
        Iterator<ElementAndBounds> iter = filteredElementBounds.iterator();
        while (iter.hasNext()) {
            ElementAndBounds element = iter.next();
            String id = element.getElementId();
            if (BPMNGraphUtil.isBPMNLabelID(id)) {
                String flowElementID = BPMNGraphUtil.resolveFlowElementIDfromLabelID(id);
                // test if the id is part of our primary list...
                for (ElementAndBounds e : elementBounds) {
                    if (flowElementID.equals(e.getElementId())) {
                        iter.remove();
                        break;
                    }
                }
            }
        }
        return filteredElementBounds;
    }
}