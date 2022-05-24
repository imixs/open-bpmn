/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
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
                    bpmnBounds.updateBounds(newPoint.getX(), newPoint.getY(), newSize.getWidth(), newSize.getHeight());
                } else {
                    // for all other elements update the position only
                    BPMNDimension dimension = bpmnBounds.getDimension();
                    bpmnBounds.updateBounds(newPoint.getX(), newPoint.getY(), dimension.getWidth(),
                            dimension.getHeight());
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
        // no more action - the GModel is now up to date
    }

}