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
import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GDimension;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.operations.ChangeBoundsOperation;
import org.eclipse.glsp.server.operations.GModelOperationHandler;
import org.eclipse.glsp.server.types.ElementAndBounds;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Association;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.elements.Message;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.elements.core.BPMNDimension;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNInvalidTypeException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.glsp.bpmn.BPMNGNode;
import org.openbpmn.glsp.bpmn.PoolGNode;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.utils.BPMNGModelUtil;
import org.openbpmn.glsp.utils.ModelTypes;

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
public class BPMNChangeBoundsOperationHandler extends GModelOperationHandler<ChangeBoundsOperation> {

    private static Logger logger = LogManager.getLogger(BPMNChangeBoundsOperationHandler.class);

    @Inject
    protected BPMNGModelState modelState;

    @Override
    public Optional<Command> createCommand(ChangeBoundsOperation operation) {
        return commandOf(() -> executeOperation(operation));
    }

    /**
     * Update the bounds for all selected elements in the GModel and also in the
     * BPMNModel.
     * <p>
     * BPMNLabels and the corresponding element can appear in the same selection. In
     * this case we remove the label from the selection because the
     * updateFlowElement method treads the label automatically.
     */
    private void executeOperation(final ChangeBoundsOperation operation) {
        // iterate over all new Bounds...
        logger.debug("=== ChangeBoundsOperation - " + operation.getNewBounds().size() + " new bounds");

        try {
            List<ElementAndBounds> elementBounds = operation.getNewBounds();

            // first sort out all elementBounds from BPMNLabel objects if the flowElement is
            // part of this selection (see method updateFlowElement)
            List<ElementAndBounds> filteredElementBounds = filterElements(elementBounds);

            boolean allowReset = filteredElementBounds.size() == 1;

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
                        updateFlowElement(gNode, bpmnElementNode, elementBound, newPoint, newSize, allowReset);
                    }
                } else {
                    // test if we have a BPMNLabel element was selected?
                    if (BPMNGModelUtil.isBPMNLabelID(id)) {
                        // find parent
                        String flowElementID = BPMNGModelUtil.resolveFlowElementIDfromLabelID(id);
                        BPMNElementNode _bpmnElement = modelState.getBpmnModel().findElementNodeById(flowElementID);
                        BPMNLabel bpmnLabel = _bpmnElement.getLabel();

                        updateLabel(gNode, bpmnLabel, offsetX, offsetY);
                    }

                    if (ModelTypes.LANE_DIVIDER.equals(gNode.getType())) {
                        updateLaneSizeByDividerPos(gNode, offsetY);
                    }
                }

            }
        } catch (BPMNMissingElementException | BPMNInvalidReferenceException | BPMNInvalidTypeException e) {
            e.printStackTrace();
        }
        // no more action - the GModel is now up to date
    }

    /**
     * This helper method updates the lane position and dimension based on a new
     * LaneDividerPos
     * 
     * @param gNode
     * @param offsetY
     * @throws BPMNMissingElementException
     */
    private void updateLaneSizeByDividerPos(GNode gNode, double offsetY) throws BPMNMissingElementException {
        Lane upperBpmnLane;
        Lane lowerBpmnLane;
        Participant participant = null;
        String upperLaneID = gNode.getArgs().get("upperlaneid").toString();
        String lowerLaneID = gNode.getArgs().get("lowerlaneid").toString();

        // Upper Lane
        GNode upperGLane = modelState.getIndex().findElementByClass(upperLaneID, GNode.class)
                .orElse(null);
        if (upperGLane == null) {
            throw new BPMNMissingElementException(BPMNMissingElementException.MISSING_ELEMENT,
                    "Lane " + upperLaneID + " not found in model!");
        }
        String poolID = upperGLane.getParent().getId();
        participant = modelState.getBpmnModel().findParticipantById(poolID);
        upperBpmnLane = participant.openProcess().findLaneById(upperLaneID);
        upperBpmnLane.setDimension(upperBpmnLane.getBounds().getDimension().getWidth(),
                upperBpmnLane.getBounds().getDimension().getHeight() + offsetY);

        // Lower Lane
        GNode lowerGLane = modelState.getIndex().findElementByClass(lowerLaneID, GNode.class)
                .orElse(null);
        if (lowerGLane == null) {
            throw new BPMNMissingElementException(BPMNMissingElementException.MISSING_ELEMENT,
                    "Lane " + upperLaneID + " not found in model!");
        }
        lowerBpmnLane = participant.openProcess().findLaneById(lowerLaneID);
        lowerBpmnLane.setPosition(lowerBpmnLane.getBounds().getPosition().getX(),
                lowerBpmnLane.getBounds().getPosition().getY() + offsetY);
        lowerBpmnLane.setDimension(lowerBpmnLane.getBounds().getDimension().getWidth(),
                lowerBpmnLane.getBounds().getDimension().getHeight() - offsetY);

        // Update divider position...
        gNode.setPosition(
                GraphUtil.point(gNode.getPosition().getX(), gNode.getPosition().getY() + offsetY));

        // finally we need to update the containment of all BPMN elements in the current
        // pool.
        if (participant != null) {
            Set<BPMNElementNode> flowElementList = participant.getBpmnProcess().getAllFlowElementNodes();
            for (BPMNElementNode elementNode : flowElementList) {
                elementNode.updateContainment();
            }
        }

        modelState.reset();
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
            final ElementAndBounds elementBound, final GPoint newPoint, final GDimension newSize, boolean allowReset)
            throws BPMNInvalidTypeException, BPMNMissingElementException, BPMNInvalidReferenceException {

        boolean clearRoutingPoints = false;
        double offsetX = newPoint.getX() - gNode.getPosition().getX();
        double offsetY = newPoint.getY() - gNode.getPosition().getY();

        if (offsetX != 0 && offsetY != 0 && allowReset) {
            clearRoutingPoints = true;
        }

        BPMNPoint oldBpmnPoint = bpmnElementNode.getBounds().getPosition();
        BPMNPoint newBpmnPoint = new BPMNPoint(oldBpmnPoint.getX() + offsetX, oldBpmnPoint.getY() + offsetY);

        // Updating the element bounds will automatically also update the Process and
        // Lane assignment...
        String oldProcessID = bpmnElementNode.getProcessId();
        bpmnElementNode.setBounds(newBpmnPoint.getX(), newBpmnPoint.getY(), newSize.getWidth(),
                newSize.getHeight());
        if (!oldProcessID.equals(bpmnElementNode.getProcessId())) {
            // Update the model as the relation ship may have changed
            modelState.reset();
        }

        // reset sequence flows?
        Set<SequenceFlow> sequenceFlows = bpmnElementNode.getIngoingSequenceFlows();
        for (SequenceFlow sequenceFlow : sequenceFlows) {
            if (clearRoutingPoints) {
                sequenceFlow.clearWayPoints();
                modelState.reset();
            }
        }
        sequenceFlows = bpmnElementNode.getOutgoingSequenceFlows();
        for (SequenceFlow sequenceFlow : sequenceFlows) {
            if (clearRoutingPoints) {
                sequenceFlow.clearWayPoints();
                modelState.reset();
            }
        }
        // reset associations?
        Set<Association> associations = bpmnElementNode.getAssociations();
        for (Association association : associations) {
            if (clearRoutingPoints) {
                association.clearWayPoints();
                modelState.reset();
            }
        }

        /*
         * Finally Update GNode dimension (if the model was not already reset)...
         */
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

            // If we have a Task, than we need to reposition the extension label too!
            if (bpmnElementNode instanceof Activity && gNode instanceof BPMNGNode) {
                GCompartment extensionCompartment = BPMNGModelUtil.findExtensionCompartment((BPMNGNode) gNode);
                if (extensionCompartment != null) {
                    extensionCompartment.setPosition(GraphUtil.point(3, newSize.getHeight() - 12));
                }
            }

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

        Participant participant = modelState.getBpmnModel().findParticipantById(id);
        if (participant == null) {
            throw new BPMNMissingElementException(BPMNMissingElementException.MISSING_ELEMENT,
                    "Participant " + id + " not found in model!");
        }

        double offsetX = newPoint.getX() - gNode.getPosition().getX();
        double offsetY = newPoint.getY() - gNode.getPosition().getY();

        double offsetWidth = newSize.getWidth() - gNode.getSize().getWidth();
        double offsetHeight = newSize.getHeight() - gNode.getSize().getHeight();

        // test if the computed height is >= the minimal height based on the lane count
        double minHeight = Participant.MIN_HEIGHT;

        if (participant.getBpmnProcess().hasLanes()) {
            minHeight = 0;
            Iterator<Lane> laneSetIterator = participant.getBpmnProcess().getLanes().iterator();
            while (laneSetIterator.hasNext()) {
                Lane _lane = laneSetIterator.next();
                if (laneSetIterator.hasNext()) {
                    minHeight = minHeight + _lane.getBounds().getDimension().getHeight();
                } else {
                    minHeight = minHeight + Lane.MIN_HEIGHT;
                }
            }
        }

        if (newSize.getHeight() < minHeight) {
            newSize.setHeight(minHeight);
        }

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
        BPMNDimension oldDimensions = new BPMNDimension(bpmnElementNode.getBounds().getDimension().getWidth(),
                bpmnElementNode.getBounds().getDimension().getHeight());
        BPMNPoint oldPosition = new BPMNPoint(bpmnElementNode.getBounds().getPosition().getX(),
                bpmnElementNode.getBounds().getPosition().getY());

        // update BPMNElement bounds....
        // The BPMN Position is always absolute so we can simply update the element
        // bounds by the new offset and new dimensions.
        // @see https://github.com/imixs/open-bpmn/issues/208
        bpmnElementNode.setBounds(bpmnBounds.getPosition().getX() + offsetX,
                bpmnBounds.getPosition().getY() + offsetY, newSize.getWidth(),
                newSize.getHeight());

        logger.debug("...update participant pool elements: offset= " + offsetX + " , " + offsetY);
        updateLaneSet(participant, offsetWidth, offsetHeight);
        updateEmbeddedElements(participant, offsetX, offsetY);
        updateMessageElements(oldPosition, oldDimensions, offsetX, offsetY);

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
     * This helper method updates the bounds of all BPMN lanes in the current
     * process by
     * a given x/y offset and width/height offset.
     * <p>
     * When the dimensions of a pool changes, than we compute a new size for all
     * lanes part of the current laneSet.
     * <p>
     * The method resets the model
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
        Lane lane = null;
        Set<Lane> bpmnLaneSet = process.getLanes();

        String processID = process.getId();
        logger.debug("update laneSet for Process : " + processID);
        if (bpmnLaneSet.size() == 0) {
            return; // no op
        }

        // first we update the position of all Lanes if available
        BPMNBounds poolBounds = participant.getBounds();
        int bpmnLaneX = (int) (poolBounds.getPosition().getX() + Participant.POOL_OFFSET);
        int bpmnLaneY = (int) poolBounds.getPosition().getY();
        int bpmnLaneWidth = (int) (poolBounds.getDimension().getWidth() - Participant.POOL_OFFSET);
        double poolHeight = (int) poolBounds.getDimension().getHeight();
        double lanesTotalHeight = 0;

        Iterator<Lane> laneSetIterator = bpmnLaneSet.iterator();
        while (laneSetIterator.hasNext()) {
            // get BPMNBounds and GNode for this lane...
            lane = laneSetIterator.next();
            BPMNBounds bpmnLaneBounds = lane.getBounds();

            // first update the width/height of the lane.
            double laneHeight = bpmnLaneBounds.getDimension().getHeight();
            if (!laneSetIterator.hasNext()) {
                // In case we process the last lane, we always maximize the height to the rest
                // of the pool height.
                laneHeight = poolHeight - lanesTotalHeight;
            }
            bpmnLaneBounds.setDimension(bpmnLaneWidth, laneHeight);
            lanesTotalHeight = lanesTotalHeight + laneHeight;

            // Next update absolute BPMN position
            lane.setPosition(bpmnLaneX, bpmnLaneY);
            // adjust laneY for the next iteration
            bpmnLaneY = (int) (bpmnLaneY + bpmnLaneBounds.getDimension().getHeight());
        }
        // resize the model
        modelState.reset();

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
    void updateEmbeddedElements(final Participant participant, final double offsetX, final double offsetY)
            throws BPMNMissingElementException {

        // Update all FlowElements
        BPMNProcess process = participant.getBpmnProcess();
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
     * This method updates the position off all BPMN Message elements contained in a
     * given bounds. In BPMN Message elements are not part of a pool or process.
     * This helper method tests if a Message element is contained by the given
     * bounds and if so it moves the element with the given offset.
     * 
     * This is a convenient way for moving pools and this feature was not supported
     * by Eclipse BPMN.
     * 
     * @param process - the bpmnProcess containing the bpmn element nodes.
     * @param offsetX - new X offset
     * @param offsetY - new Y offset
     * @throws BPMNMissingElementException
     */
    void updateMessageElements(final BPMNPoint oldPosition, final BPMNDimension oldDimension, final double offsetX,
            final double offsetY)
            throws BPMNMissingElementException {

        // Update all Message Elements contained in the given bounds
        Set<Message> messageElements = modelState.getBpmnModel().getMessages();
        for (Message _message : messageElements) {
            BPMNBounds bounds = _message.getBounds();
            BPMNPoint point = bounds.getCenter();

            double x = oldPosition.getX();
            double y = oldPosition.getY();
            double w = oldDimension.getWidth();
            double h = oldDimension.getHeight();
            // is the point within this dimensions?
            if (point.getX() >= x && point.getX() <= x + w
                    && point.getY() >= y && point.getY() <= y + h) {
                _message.setPosition(bounds.getPosition().getX() + offsetX, bounds.getPosition().getY() + offsetY);
                // adjust position of the label

                BPMNLabel bpmnLabel = _message.getLabel();
                if (bpmnLabel != null) {
                    bpmnLabel.updateLocation(bpmnLabel.getPosition().getX() + offsetX,
                            bpmnLabel.getPosition().getY() + offsetY);
                }
            }
        }

    }

    /**
     * 
     * 
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
            if (BPMNGModelUtil.isBPMNLabelID(id)) {
                String flowElementID = BPMNGModelUtil.resolveFlowElementIDfromLabelID(id);
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