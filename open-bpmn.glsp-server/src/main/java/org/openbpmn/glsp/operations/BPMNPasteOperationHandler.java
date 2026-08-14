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
import org.openbpmn.glsp.BPMNClipboardNode;
import org.openbpmn.glsp.BPMNClipboardService;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    protected BPMNClipboardService clipboardService;

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

        if (clipboardService.getNodes() == null) {
            // nothing to paste
            return;
        }

        List<BPMNClipboardNode> clipboardNodes = clipboardService.getNodes();
        List<Element> clipboardEdges = clipboardService.getEdges();

        Map<String, String> clonedIDs = new HashMap<>(); // old semantic id -> new semantic id
        List<String> newElementIDList = new ArrayList<>();
        List<String> originElementIDList = new ArrayList<>();

        BPMNProcess targetProcess = modelState.getBpmnModel().getDefaultProcess(); // TODO-LIB: correct way to get the
                                                                                   // "current" target process for
                                                                                   // paste?
        Document targetDocument = targetProcess.getElementNode().getOwnerDocument();

        // --- Step 1: compute reference point (most upper-left) from the cloned bounds
        // ---
        BPMNPoint refPoint = computeRefPointFromClipboardNodes(clipboardNodes);
        GPoint mousePosition = operation.getEditorContext().getLastMousePosition().orElse(null);
        double xOffset = mousePosition.getX() - refPoint.getX();
        double yOffset = mousePosition.getY() - refPoint.getY();

        // --- Step 2: import + insert nodes into target process ---
        for (BPMNClipboardNode clipboardNode : clipboardNodes) {
            try {
                String oldId = clipboardNode.getSemanticElement().getAttribute("id");

                // import the detached semantic clone into the target document
                Element importedSemanticElement = (Element) targetDocument.importNode(
                        clipboardNode.getSemanticElement(), true);

                BPMNElementNode newElementNode = targetProcess.adoptElementNode(importedSemanticElement);
                clonedIDs.put(oldId, newElementNode.getId());
                newElementIDList.add(newElementNode.getId());
                originElementIDList.add(oldId);

                // import + apply the cloned bounds (position/size)
                Element importedBoundsElement = (Element) targetDocument.importNode(
                        clipboardNode.getShapeElement(), true);
                BPMNPoint originalPos = new BPMNPoint(
                        importedBoundsElement.getAttribute("x"),
                        importedBoundsElement.getAttribute("y"));

                double width = Double.parseDouble(importedBoundsElement.getAttribute("width"));
                double height = Double.parseDouble(importedBoundsElement.getAttribute("height"));

                newElementNode.setPosition(originalPos.getX() + xOffset, originalPos.getY() + yOffset);
                newElementNode.setDimension(width, height);
                BPMNLabel label = newElementNode.getLabel();
                if (label != null) {
                    BPMNModelUtil.resetLabelBounds(newElementNode);
                }
            } catch (BPMNModelException e) {
                e.printStackTrace();
            }
        }

        // --- Step 3: import + insert edges, but only if both ends were copied ---
        if (clipboardEdges != null) {
            for (Element clipboardEdgeElement : clipboardEdges) {
                String oldSourceRef = clipboardEdgeElement.getAttribute("sourceRef");
                String oldTargetRef = clipboardEdgeElement.getAttribute("targetRef");

                String newSourceID = clonedIDs.get(oldSourceRef);
                String newTargetID = clonedIDs.get(oldTargetRef);

                // skip edges pointing to elements outside the copied selection
                if (newSourceID == null || newTargetID == null) {
                    continue;
                }

                try {
                    Element importedEdgeElement = (Element) targetDocument.importNode(clipboardEdgeElement, true);

                    BPMNElementEdge newElementEdge = targetProcess.adoptElementEdge(importedEdgeElement, newSourceID,
                            newTargetID);

                    newElementEdge.setSourceRef(newSourceID);
                    newElementEdge.setTargetRef(newTargetID);

                    // move the new sequenceFlow into the correct target process
                    // (relevant if source/target ended up in a Lane/SubProcess)
                    BPMNElement sourceElement = modelState.getBpmnModel().findElementById(newSourceID);
                    String processID = sourceElement.getBpmnProcess().getId();
                    BPMNProcess actualTargetProcess = modelState.getBpmnModel().findProcessById(processID);
                    ((SequenceFlow) newElementEdge).updateBPMNProcess(actualTargetProcess);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        modelState.reset();
        actionDispatcher.dispatchAfterNextUpdate(new SelectAction(newElementIDList, originElementIDList));
    }

    /**
     * Computes the most upper-left BPMNPoint across all copied clipboard nodes,
     * based on the cloned dc:Bounds elements (x/y attributes).
     */
    private BPMNPoint computeRefPointFromClipboardNodes(List<BPMNClipboardNode> clipboardNodes) {
        BPMNPoint result = null;
        for (BPMNClipboardNode clipboardNode : clipboardNodes) {
            Element boundsElement = clipboardNode.getShapeElement();
            double x = Double.parseDouble(boundsElement.getAttribute("x"));
            double y = Double.parseDouble(boundsElement.getAttribute("y"));
            if (result == null) {
                result = new BPMNPoint(x, y);
            } else {
                if (x < result.getX()) {
                    result.setX(x);
                }
                if (y < result.getY()) {
                    result.setY(y);
                }
            }
        }
        return result != null ? result : new BPMNPoint(0, 0);
    }
}