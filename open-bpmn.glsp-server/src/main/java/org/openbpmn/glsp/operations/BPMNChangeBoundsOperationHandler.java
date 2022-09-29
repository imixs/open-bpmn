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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GDimension;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.ChangeBoundsOperation;
import org.eclipse.glsp.server.types.ElementAndBounds;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNBounds;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNFlowElement;
import org.openbpmn.bpmn.elements.BPMNGateway;
import org.openbpmn.bpmn.elements.BPMNLabel;
import org.openbpmn.bpmn.elements.BPMNParticipant;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.model.BPMNGModelState;

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

    @Override
    public void executeOperation(final ChangeBoundsOperation operation) {
        // iterate over all new Bounds...
        try {
            List<ElementAndBounds> elementBounds = operation.getNewBounds();
            for (ElementAndBounds elementBound : elementBounds) {
                String id = elementBound.getElementId();

                GPoint newPoint = elementBound.getNewPosition();
                GDimension newSize = elementBound.getNewSize();

                // find the corresponding BPMN and GNode element

                logger.info("...bounds update for: " + id);
                BPMNBounds bpmnBounds = modelState.getBpmnModel().findBPMNBoundsById(id);
                if (bpmnBounds != null) {
                    double offsetX = newPoint.getX() - bpmnBounds.getPosition().getX();
                    double offsetY = newPoint.getY() - bpmnBounds.getPosition().getY();

                    // find the corresponding GNode element and update the dimension and position...
                    Optional<GNode> _node = modelState.getIndex().findElementByClass(id, GNode.class);
                    if (_node.isPresent()) {
                        GNode gNode = _node.get();
                        // update GNode position....
                        gNode.setPosition(newPoint);
                        // Update GNode dimension....
                        gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, newSize.getWidth());
                        gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, newSize.getHeight());
                        // calling the size method does not have an effect.
                        // see:
                        // https://github.com/eclipse-glsp/glsp/discussions/741#discussioncomment-3688606
                        gNode.setSize(newSize);
                    } else {
                        // this case should not happen!
                        logger.warning("GNode '" + id + "' not found in current modelState!");
                    }

                    // finally update BPMNElement bounds....
                    bpmnBounds.updateLocation(newPoint.getX(), newPoint.getY());
                    bpmnBounds.updateDimension(newSize.getWidth(), newSize.getHeight());

                    // if we have a Participant element selected than we need to iterate over all
                    // embedded BPMNFlow Elements and update the bounds too.
                    BPMNParticipant participant = modelState.getBpmnModel().findBPMNParticipantById(id);
                    if (participant != null) {
                        logger.info("...update participant pool elements: offset= " + offsetX + " , " + offsetY);
                        updateProcessElementBounds(participant.openProcess(), offsetX, offsetY);
                    }

                } else {
                    // test if we have a BPMNLable element was selected?
                    if (id.endsWith("_bpmnlabel")) {
                        // yes, update the GLabel position
                        Optional<GLabel> _node = modelState.getIndex().findElementByClass(id, GLabel.class);
                        if (_node.isPresent()) {
                            GLabel node = _node.get();
                            // update GNode position....
                            node.setPosition(newPoint);
                        } else {
                            // this case should not happen!
                            logger.warning("GLabel '" + id + "' not found in current modelState!");
                        }

                        // update the BPMN model information
                        String labelID = id.substring(0, id.lastIndexOf("_bpmnlabel"));
                        BPMNBaseElement bpmnBaseElement = modelState.getBpmnModel().findBPMNBaseElementById(labelID);
                        if (bpmnBaseElement != null) {
                            if (bpmnBaseElement instanceof BPMNEvent || bpmnBaseElement instanceof BPMNGateway) {
                                BPMNLabel label = ((BPMNFlowElement) bpmnBaseElement).getLabel();
                                if (label != null) {
                                    label.updateLocation(newPoint.getX(), newPoint.getY());
                                }
                            }
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
     * This method updates the position for all BPMNFlowElements contained in a pool
     * given a x and y offset.
     *
     * @param process
     * @param participant
     * @throws BPMNMissingElementException
     */
    void updateProcessElementBounds(final BPMNProcess process, final double offsetX, final double offsetY) {
        Set<BPMNFlowElement> bpmnFlowElements = process.getBPMNFlowElements();
        // Add all Tasks
        for (BPMNFlowElement flowElement : bpmnFlowElements) {
            logger.info("update element bounds: " + flowElement.getId());
            try {
                BPMNBounds bounds = flowElement.getBounds();
                if (bounds != null) {
                    bounds.updateLocation(bounds.getPosition().getX() + offsetX, bounds.getPosition().getY() + offsetY);
                }
            } catch (BPMNMissingElementException e) {
                logger.warning("Failed to update FlowElement bounds for : " + flowElement.getId());
            }
        }
    }

}