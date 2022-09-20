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
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GDimension;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.ChangeBoundsOperation;
import org.eclipse.glsp.server.types.ElementAndBounds;
import org.openbpmn.bpmn.elements.BPMNActivity;
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
 * This OperationHandler is used to update the BPMN model regarding new Bounds
 * of elements.
 * <p>
 * The operation synchronizes the BPMN Model and also the GModel. In this way we
 * avoid a complete new initialization of the GModel which is not necessary in
 * this scenario.
 * <p>
 * For BPMN Event and Gateway elements we only update the position. Resize is
 * not supported for this kind of elements. But these elements have a special
 * handling of the corresponding label. In case only the label was moved (id
 * ends with "_bpmnlabel") then the method updates the Label position for these
 * elements in the BPMN source model. In case a event or gateway symbol was
 * selected, the label is moved automatically, because we select the label with
 * a selection listener in those cases. (See the client implementation
 * BPMNLabelNodeSelectionListener)
 * <p>
 * For Activities (Tasks) and Participants (Pools) we also update the
 * dimensions. This is done by updating the node properties
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

        BPMNProcess context = modelState.getBpmnModel().getContext();
        // iterate over all new Bounds...
        try {
            List<ElementAndBounds> elementBounds = operation.getNewBounds();
            for (ElementAndBounds elementBound : elementBounds) {
                String id = elementBound.getElementId();
                GPoint newPoint = elementBound.getNewPosition();
                GDimension newSize = elementBound.getNewSize();
                // find the corresponding BPMN and GNode element
                BPMNFlowElement bpmnFlowElement = context.findBPMNFlowElementById(id);
                if (bpmnFlowElement != null) {
                    GNode gNode = null;
                    Optional<GNode> _node = modelState.getIndex().findElementByClass(id, GNode.class);
                    if (_node.isPresent()) {
                        gNode = _node.get();
                    } else {
                        // this case should not happen!
                        logger.warning("Node Element '" + id + "' not found in current modelState!");
                        continue;
                    }

                    BPMNBounds bpmnBounds = bpmnFlowElement.getBounds();
                    // Update BPMNElement position for all kind of BPMN elements
                    bpmnBounds.updateLocation(newPoint.getX(), newPoint.getY());
                    // update GNode position
                    gNode.setPosition(newPoint);

                    // for Task and Pool Elements update also the dimensions
                    if (bpmnFlowElement instanceof BPMNActivity || bpmnFlowElement instanceof BPMNParticipant) {
                        bpmnBounds.updateDimension(newSize.getWidth(), newSize.getHeight());
                        // Update GNode
                        gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_WIDTH, newSize.getWidth());
                        gNode.getLayoutOptions().put(GLayoutOptions.KEY_PREF_HEIGHT, newSize.getHeight());
                        // calling the size method does not have an effect.
                        // see:
                        // https://github.com/eclipse-glsp/glsp/discussions/741#discussioncomment-3688606
                        gNode.setSize(newSize);
                    } else {
                        // for all other elements update the position only
                        // BPMNDimension dimension = bpmnBounds.getDimension();
                        // bpmnBounds.updateDimension(dimension.getWidth(), dimension.getHeight());
                    }
                } else {
                    // test if we have a BPMNLable element was selected?
                    if (id.endsWith("_bpmnlabel")) {
                        // yes - so we update the BPMN model information
                        bpmnFlowElement = context
                                .findBPMNFlowElementById(id.substring(0, id.lastIndexOf("_bpmnlabel")));
                        if (bpmnFlowElement != null) {
                            if (bpmnFlowElement instanceof BPMNEvent || bpmnFlowElement instanceof BPMNGateway) {
                                BPMNLabel label = bpmnFlowElement.getLabel();
                                label.updateLocation(newPoint.getX(), newPoint.getY());
                            }
                        }
                        // finally update the GLabel position
                        Optional<GLabel> _node = modelState.getIndex().findElementByClass(id, GLabel.class);
                        if (_node.isPresent()) {
                            GLabel node = _node.get();
                            node.setPosition(newPoint);
                        }
                    }

                }

            }
        } catch (BPMNMissingElementException e) {
            e.printStackTrace();
        }
        // no more action - the GModel is now up to date
    }

}