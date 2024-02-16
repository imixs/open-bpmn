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
package org.openbpmn.glsp.provider;

import static org.eclipse.glsp.graph.util.GraphUtil.point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GModelIndex;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.server.features.commandpalette.CommandPaletteActionProvider;
import org.eclipse.glsp.server.features.directediting.LabeledAction;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.operations.DeleteOperation;
import org.eclipse.glsp.server.types.EditorContext;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.glsp.bpmn.DataObjectGNode;
import org.openbpmn.glsp.bpmn.EventGNode;
import org.openbpmn.glsp.bpmn.GatewayGNode;
import org.openbpmn.glsp.bpmn.LabelGNode;
import org.openbpmn.glsp.bpmn.MessageGNode;
import org.openbpmn.glsp.bpmn.TaskGNode;
import org.openbpmn.glsp.operations.BPMNAutoAlignOperation;
import org.openbpmn.glsp.operations.BPMNPropertyPanelToggleAction;
import org.openbpmn.glsp.operations.BPMNResetRoutingOperation;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * Creates the command actions for a diagram (ctrl+space).
 * 
 * The used icons can be found here:
 * https://microsoft.github.io/vscode-codicons/dist/codicon.html
 * 
 */
public class BPMNCommandPaletteActionProvider implements CommandPaletteActionProvider {

    @Inject
    protected GModelState modelState;

    @Override
    public List<LabeledAction> getActions(final EditorContext editorContext) {
        List<LabeledAction> actions = Lists.newArrayList();
        if (modelState.isReadonly()) {
            return actions;
        }
        GModelIndex index = modelState.getIndex();
        List<String> selectedIds = editorContext.getSelectedElementIds();
        // Optional<GPoint> lastMousePosition =
        // GridSnapper.snap(editorContext.getLastMousePosition());
        Optional<GPoint> lastMousePosition = editorContext.getLastMousePosition();

        // create a list of selected GModelElements but keep the order of the
        // selectedIDs ....
        List<GModelElement> selectedElements = new ArrayList<GModelElement>();
        for (String _id : selectedIds) {
            Optional<GModelElement> _node = index.get(_id);
            if (_node.isPresent()) {
                selectedElements.add(_node.get());
            }
        }
        // filter BPMNLabels from the selection (but keep the order)
        List<GModelElement> selectedBPMNNodeElements = new ArrayList<GModelElement>();
        for (GModelElement _gModelElement : selectedElements) {
            if (!(_gModelElement instanceof LabelGNode)) {
                selectedBPMNNodeElements.add(_gModelElement);
            }
        }

        if (selectedBPMNNodeElements.size() == 0) {
            actions.add(new LabeledAction("Create Task",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.TASK,
                            lastMousePosition.orElse(point(0, 0)))),
                    "inspect"));
            actions.add(new LabeledAction("Create Manual Task",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.MANUAL_TASK,
                            lastMousePosition.orElse(point(0, 0)))),
                    "inspect"));
            actions.add(new LabeledAction("Create User Task",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.USER_TASK,
                            lastMousePosition.orElse(point(0, 0)))),
                    "inspect"));
            actions.add(new LabeledAction("Create Send Task",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.SEND_TASK,
                            lastMousePosition.orElse(point(0, 0)))),
                    "inspect"));
            actions.add(new LabeledAction("Create Service Task",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.SERVICE_TASK,
                            lastMousePosition.orElse(point(0, 0)))),
                    "inspect"));
            actions.add(new LabeledAction("Create Script Task",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.SCRIPT_TASK,
                            lastMousePosition.orElse(point(0, 0)))),
                    "inspect"));

            // Events
            actions.add(new LabeledAction("Create Start Event",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.START_EVENT,
                            lastMousePosition.orElse(point(0, 0)))),
                    "circle-filled"));

            actions.add(new LabeledAction("Create End Event",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.END_EVENT,
                            lastMousePosition.orElse(point(0, 0)))),
                    "circle-filled"));
            actions.add(new LabeledAction("Create Catch Event",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.CATCH_EVENT,
                            lastMousePosition.orElse(point(0, 0)))),
                    "circle-filled"));
            actions.add(new LabeledAction("Create Throw Event",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.THROW_EVENT,
                            lastMousePosition.orElse(point(0, 0)))),
                    "circle-filled"));

            // Gateways
            actions.add(new LabeledAction("Create Exclusive Gateway",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.EXCLUSIVE_GATEWAY,
                            lastMousePosition.orElse(point(0, 0)))),
                    "debug-breakpoint-log-unverified"));

            actions.add(new LabeledAction("Create Parallel Gateway",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.PARALLEL_GATEWAY,
                            lastMousePosition.orElse(point(0, 0)))),
                    "debug-breakpoint-log-unverified"));

            actions.add(new LabeledAction("Create Inclusive Gateway",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.INCLUSIVE_GATEWAY,
                            lastMousePosition.orElse(point(0, 0)))),
                    "debug-breakpoint-log-unverified"));

            actions.add(new LabeledAction("Create Event Gateway",
                    Lists.newArrayList(new CreateNodeOperation(BPMNTypes.EVENTBASED_GATEWAY,
                            lastMousePosition.orElse(point(0, 0)))),
                    "debug-breakpoint-log-unverified"));

        }

        // Create edge actions between two nodes only if to BPMN nodes are selected
        if (selectedBPMNNodeElements.size() == 2) {
            Iterator<GModelElement> iterator = selectedBPMNNodeElements.iterator();
            GModelElement firstElement = iterator.next();
            GModelElement secondElement = iterator.next();
            if (isBPMNFlowElementNode(firstElement) && isBPMNFlowElementNode(secondElement)) {
                GNode firstNode = (GNode) firstElement;
                GNode secondNode = (GNode) secondElement;
                actions.add(createSequenceFlowAction("Connect with Sequence Flow", firstNode,
                        secondNode));
            }
        }

        // Auto Align Action
        actions.add(new LabeledAction("Auto align all elements...",
                Lists.newArrayList(new BPMNAutoAlignOperation(selectedIds)),
                "layout"));

        // Reset Routing Points Action
        if (selectedBPMNNodeElements.size() >= 1) {
            actions.add(new LabeledAction("Reset routing...",
                    Lists.newArrayList(new BPMNResetRoutingOperation(selectedIds)),
                    "indent"));
        }

        // Delete action
        if (selectedBPMNNodeElements.size() >= 1) {
            actions.add(new LabeledAction("Delete", Lists.newArrayList(new DeleteOperation(selectedIds)),
                    "trash"));
        }

        // Property Toggle Action
        actions.add(new LabeledAction("Properties...",
                Lists.newArrayList(new BPMNPropertyPanelToggleAction()),
                "browser"));

        return actions;
    }

    /**
     * Returns true for Tasks, Events and Gateways
     * 
     * @param element
     */
    public boolean isBPMNFlowElementNode(GModelElement element) {
        return (element instanceof TaskGNode || element instanceof EventGNode
                || element instanceof GatewayGNode);
    }

    /**
     * Returns true for all BPMN element GNodes
     * 
     * @param element
     */
    public boolean isBPMNElementNode(GModelElement element) {
        return (isBPMNFlowElementNode(element)
                || element instanceof MessageGNode || element instanceof DataObjectGNode);
    }

    private LabeledAction createSequenceFlowAction(final String label, final GNode source, final GNode node) {
        return new LabeledAction(label,
                Lists.newArrayList(new CreateEdgeOperation(BPMNTypes.SEQUENCE_FLOW, source.getId(),
                        node.getId())),
                "export");
    }

    private String getLabel(final GNode node) {
        if (node instanceof TaskGNode) {
            return ((TaskGNode) node).getName();
        }

        if (node instanceof EventGNode) {
            return ((EventGNode) node).getName();
        }

        if (node instanceof GatewayGNode) {
            return ((GatewayGNode) node).getName();
        }
        return node.getId();
    }

}
