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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.GModelOperationHandler;
import org.eclipse.glsp.server.operations.PasteOperation;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelUtil;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This ActionHandler reacts on PasteOperation.
 * 
 * The handler expects a data element with the key 'bpmn' and a value with a
 * list of element ids to be copied. This is how the
 * {@link BPMNClipboardDataActionHandler} stores the ids into memory.
 * 
 * @see {@link BPMNClipboardDataActionHandler}
 * @author rsoika
 */
public class BPMNPasteOperationHandler extends GModelOperationHandler<PasteOperation> {

    private static Logger logger = LogManager.getLogger(BPMNPasteOperationHandler.class);

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    @Override
    public Optional<Command> createCommand(PasteOperation operation) {
        return commandOf(() -> executeOperation(operation));
    }

    /**
     * This method copies the current element selection into the diagram. The
     * selection is stored in the data structure with the key 'bpmn' which is
     * created by the BPMNClipboardDataActionHandler.
     * 
     * The position is computed based on the current mouse position
     * 
     * The method first copies all BPMN Nodes and creates a mapping table for the
     * new ElementIDs. Next the method copies the BPMN Edges by reconnecting the
     * clone to the new ElementIDs.
     */
    private void executeOperation(PasteOperation operation) {
        BPMNPoint refPoint = null;
        double xOffset = 0;
        double yOffset = 0;
        Map<String, String> clonedIDs = new HashMap<String, String>();
        List<String> newElementIDList = new ArrayList<String>();

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
            BPMNElementNode bpmnElementNode = modelState.getBpmnModel().findElementNodeById(id);
            if (bpmnElementNode != null) {
                try {
                    // clone BPMNElementNodes....
                    BPMNProcess process = bpmnElementNode.getBpmnProcess();
                    BPMNElementNode newElementNode = process.cloneBPMNElementNode(bpmnElementNode);
                    if (newElementNode != null) {
                        clonedIDs.put(bpmnElementNode.getId(), newElementNode.getId());
                        newElementIDList.add(newElementNode.getId());

                        // adjust position. This operation will automatically also update the
                        // containment!
                        newElementNode.setPosition(bpmnElementNode.getBounds().getPosition().getX() + xOffset,
                                bpmnElementNode.getBounds().getPosition().getY() + yOffset);
                        // adjust label position?
                        BPMNLabel label = newElementNode.getLabel();
                        if (label != null) {
                            BPMNModelUtil.resetLabelBounds(newElementNode);
                            // label.updateLocation(bpmnElementNode.getLabel().getBounds().getPosition().getX()
                            // + xOffset,
                            // bpmnElementNode.getLabel().getBounds().getPosition().getY() + yOffset);
                        }
                    }
                } catch (BPMNModelException e) {
                    e.printStackTrace();
                }
            }
        }

        // Next we try to copy the BPMNEdges....
        for (String id : selectedElements) {
            BPMNElementEdge bpmnElementEdge = modelState.getBpmnModel().findElementEdgeById(id);
            if (bpmnElementEdge != null && bpmnElementEdge instanceof SequenceFlow) {
                try {
                    logger.debug("...copy SequenceFlow - " + bpmnElementEdge.getId());

                    // do we have the corresponding source and target element?
                    String newSourceID = clonedIDs.get(bpmnElementEdge.getSourceRef());
                    String newTargetID = clonedIDs.get(bpmnElementEdge.getTargetRef());

                    if (newSourceID != null && newTargetID != null) {
                        BPMNProcess process = modelState.getBpmnModel().openProcess(bpmnElementEdge.getProcessId());
                        BPMNElementEdge newElementEdge = process.cloneBPMNElementEdge(bpmnElementEdge);
                        if (newElementEdge != null) {
                            newElementEdge.setSourceRef(newSourceID);
                            newElementEdge.setTargetRef(newTargetID);
                            // and finally we need to move the new sequenceFlow into the target process...
                            BPMNElement sourceElement = modelState.getBpmnModel().findElementById(newSourceID);
                            String processID = sourceElement.getProcessId();
                            BPMNProcess targetProcess = modelState.getBpmnModel().findProcessById(processID);
                            ((SequenceFlow) newElementEdge).updateBPMNProcess(targetProcess);

                            // update waypoints
                            Set<BPMNPoint> sourceWayPoints = bpmnElementEdge.getWayPoints();
                            newElementEdge.clearWayPoints();
                            for (BPMNPoint _point : sourceWayPoints) {
                                BPMNPoint newPoint = new BPMNPoint(_point.getX() + xOffset, _point.getY() + yOffset);
                                newElementEdge.addWayPoint(newPoint);
                            }
                        }
                    }
                } catch (BPMNModelException e) {
                    e.printStackTrace();
                }
            }
        }

        // reset model state..
        modelState.reset();

        // preselect new pasted elements
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(newElementIDList));
    }

    /**
     * This helper method computes the most upper left point from the list of
     * selected elements. This ref point is used to clone elements and adjust its
     * position according to the mouse position.
     */
    private BPMNPoint computeRefPoint(List<String> ids) {

        if (ids == null || ids.size() == 0) {
            // no list provided
            return new BPMNPoint(0, 0);
        }
        BPMNPoint result = null;

        for (String id : ids) {
            // find the BPMNNode
            BPMNElement bpmnElement = modelState.getBpmnModel().findElementById(id);
            if (bpmnElement != null && bpmnElement instanceof BPMNElementNode) {
                try {
                    BPMNElementNode bpmnElementNode = (BPMNElementNode) bpmnElement;
                    // compute most upper left ref position...
                    BPMNPoint _point = bpmnElementNode.getBounds().getPosition();
                    // The first element in the list is the default result
                    if (result == null) {
                        result = new BPMNPoint(_point.getX(), _point.getY());
                    } else {
                        // test if the current point is outside of our best guest....
                        if (_point.getX() < result.getX()) {
                            result.setX(_point.getX());
                        }
                        if (_point.getY() < result.getY()) {
                            result.setY(_point.getY());
                        }
                        logger.debug("...  x=" + result.getX() + " y=" + result.getY());
                    }
                } catch (BPMNModelException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}