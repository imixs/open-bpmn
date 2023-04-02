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
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.operations.AbstractOperationHandler;
import org.eclipse.glsp.server.operations.PasteOperation;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This ActionHandler reacts on PasteOperation.
 * 
 * The handler expects a data element with the key 'bpmn' and a the value with a
 * list of element ids to be copied. This is how the
 * BPMNClipboardDataActionHandler stores the ids into memory.
 * <p>
 *
 * @author rsoika
 *
 */
public class BPMNPasteOperationHandler extends AbstractOperationHandler<PasteOperation> {

    private static Logger logger = LogManager.getLogger(BPMNPasteOperationHandler.class);

    @Inject
    protected BPMNGModelState modelState;

    /**
     * This method copies the current element selection into the diagram.
     * 
     * The method verifies if we have a data structure with the key 'bpmn'
     * containing the element IDs to be copied.
     * 
     * The position is computed based on the current mouse position
     */
    @Override
    protected void executeOperation(PasteOperation operation) {
        BPMNPoint refPoint = null;
        double xOffset = 0;
        double yOffset = 0;

        Map<String, String> data = operation.getClipboardData();
        // get list of ids...
        String idStringList = data.get("bpmn");
        if (idStringList == null || idStringList.length() == 0) {
            // no action needed
            return;
        }

        // split stringList
        List<String> selectedElements = List.of(idStringList.split(","));
        logger.debug("... paste " + selectedElements.size() + " elements...");

        // compute offset
        GPoint mousePosition = operation.getEditorContext().getLastMousePosition().orElse(null);
        refPoint = computeRefPoint(selectedElements);
        xOffset = mousePosition.getX() - refPoint.getX();
        yOffset = mousePosition.getY() - refPoint.getY();

        for (String id : selectedElements) {
            BPMNElement bpmnElement = modelState.getBpmnModel().findElementById(id);
            if (bpmnElement != null) {
                try {
                    // close BPMNElementNodes....
                    if (bpmnElement instanceof BPMNElementNode) {
                        BPMNProcess process = modelState.getBpmnModel().openProcess(bpmnElement.getProcessId());
                        BPMNElementNode bpmnElementNode = (BPMNElementNode) bpmnElement;
                        BPMNElementNode newElementNode = process.cloneBPMNElementNode(bpmnElementNode);
                        // adjust position...
                        newElementNode.setPosition(bpmnElementNode.getBounds().getPosition().getX() + xOffset,
                                bpmnElementNode.getBounds().getPosition().getY() + yOffset);
                        // adjust label position?
                        BPMNLabel label = newElementNode.getLabel();
                        if (label != null) {
                            label.updateLocation(bpmnElementNode.getLabel().getBounds().getPosition().getX() + xOffset,
                                    bpmnElementNode.getLabel().getBounds().getPosition().getY() + yOffset);
                        }
                    }
                } catch (BPMNModelException e) {
                    e.printStackTrace();
                }
            }
        }
        // reset model state..
        modelState.reset();
    }

    /**
     * This helper method computes the most upper left point from the list of
     * selected elements.
     * This ref point is used to clone elements and adjust its position according to
     * the mouse position.
     */
    private BPMNPoint computeRefPoint(List<String> ids) {
        double x = 0;
        double y = 0;
        for (String id : ids) {
            // find the BPMNNode
            BPMNElement bpmnElement = modelState.getBpmnModel().findElementById(id);
            if (bpmnElement != null && bpmnElement instanceof BPMNElementNode) {
                try {
                    BPMNElementNode bpmnElementNode = (BPMNElementNode) bpmnElement;
                    // compute most upper left ref position...
                    BPMNPoint _point = bpmnElementNode.getBounds().getPosition();
                    if (_point.getX() > 0) {
                        if ((x > 0 && _point.getX() < x) || x == 0) {
                            x = _point.getX();
                        }
                    }
                    if (_point.getY() > 0) {
                        if ((y > 0 && _point.getY() < y) || y == 0) {
                            y = _point.getY();
                        }
                    }
                    logger.debug("...  x=" + x + " y=" + y);
                } catch (BPMNModelException e) {
                    e.printStackTrace();
                }
            }
        }
        return new BPMNPoint(x, y);
    }
}