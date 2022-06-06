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
package org.openbpmn.bpmn.operations;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GDimension;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.ChangeBoundsOperation;
import org.eclipse.glsp.server.types.ElementAndBounds;
import org.openbpmn.bpmn.BPMNGModelState;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNBounds;
import org.openbpmn.bpmn.elements.BPMNDimension;
import org.openbpmn.bpmn.elements.BPMNEvent;
import org.openbpmn.bpmn.elements.BPMNFlowElement;
import org.openbpmn.bpmn.elements.BPMNGateway;
import org.openbpmn.bpmn.elements.BPMNLabel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;

import com.google.inject.Inject;

/**
 * This OperationHandler is used to update the BPMN model regarding new Bounds
 * of elements.
 * <p>
 * The operation synchronizes the BPMN Model and also the GModel. In this way we
 * avoid a complete new initialization of the GModel which is not necessary in
 * this scenario.
 *
 * @author rsoika
 *
 */
public class BPMNChangeBoundsOperationHandler extends AbstractOperationHandler<ChangeBoundsOperation> {
    @SuppressWarnings("unused")
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
                // find the corresponding BPMN element
                BPMNFlowElement bpmnElement = context.findBPMNFlowElementById(id);
                if (bpmnElement != null) {
                    BPMNBounds bpmnBounds = bpmnElement.getBounds();
                    // for Task Elements update position and size
                    if (bpmnElement instanceof BPMNActivity) {
                        bpmnBounds.updateLocation(newPoint.getX(), newPoint.getY());
                        bpmnBounds.updateDimension(newSize.getWidth(), newSize.getHeight());
                    } else {
                        // for all other elements update the position only
                        BPMNDimension dimension = bpmnBounds.getDimension();
                        bpmnBounds.updateLocation(newPoint.getX(), newPoint.getY());
                        bpmnBounds.updateDimension(dimension.getWidth(), dimension.getHeight());
                    }
                    // Finally we update the Gmodel to avoid a new initialization.
                    Optional<GNode> _node = modelState.getIndex().findElementByClass(id, GNode.class);
                    if (_node.isPresent()) {
                        GNode node = _node.get();
                        node.setPosition(newPoint);
                        node.setSize(newSize);
                    }
                } else {
                    // test if we have a BPMNLable element?
                    if (id.endsWith("_bpmnlabel")) {
                        // update the source model
                        bpmnElement = context.findBPMNFlowElementById(id.substring(0, id.lastIndexOf("_bpmnlabel")));
                        if (bpmnElement != null) {
                            if (bpmnElement instanceof BPMNEvent || bpmnElement instanceof BPMNGateway) {
                                BPMNLabel label = bpmnElement.getLabel();
                                label.updateLocation(newPoint.getX(), newPoint.getY());
                            }

                        }

                        // update Glabel
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