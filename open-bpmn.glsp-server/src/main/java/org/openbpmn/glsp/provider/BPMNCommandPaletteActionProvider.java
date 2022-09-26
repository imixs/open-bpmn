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

import static org.eclipse.glsp.graph.DefaultTypes.EDGE;
import static org.eclipse.glsp.graph.util.GraphUtil.point;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.openbpmn.glsp.bpmn.EventNode;
import org.openbpmn.glsp.bpmn.GatewayNode;
import org.openbpmn.glsp.bpmn.TaskNode;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;

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
        Set<GModelElement> selectedElements = index.getAll(selectedIds);

        // Create node actions are always possible
        actions.addAll(Sets.newHashSet(
                new LabeledAction("Create Manual Task",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.MANUAL_TASK,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),
                new LabeledAction("Create User Task",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.USER_TASK,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),
                new LabeledAction("Create Send Task",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.SEND_TASK,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),
                new LabeledAction("Create Service Task",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.SERVICE_TASK,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),
                new LabeledAction("Create Script Task",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.SCRIPT_TASK,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),

                new LabeledAction("Create Start Event",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.START_EVENT,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),
                new LabeledAction("Create End Event",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.END_EVENT,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),
                new LabeledAction("Create Catch Event",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.CATCH_EVENT,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),
                new LabeledAction("Create Throw Event",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.THROW_EVENT,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),

                new LabeledAction("Create Exclusive Gateway",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.EXCLUSIVE_GATEWAY,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),
                new LabeledAction("Create Inclusive Gateway",
                        Lists.newArrayList(new CreateNodeOperation(BPMNTypes.INCLUSIVE_GATEWAY,
                                lastMousePosition.orElse(point(0, 0)), "fa-plus-square"))),

                // Pool
                new LabeledAction("Create Pool", Lists.newArrayList(new CreateNodeOperation(BPMNTypes.POOL,
                        lastMousePosition.orElse(point(0, 0)), "fa-plus-square")))

        ));

        // Create edge actions between two nodes
        if (selectedElements.size() == 1) {
            GModelElement element = selectedElements.iterator().next();
            if (element instanceof GNode) {
                actions.addAll(createEdgeActions((GNode) element, index.getAllByClass(TaskNode.class)));
            }
        } else if (selectedElements.size() == 2) {
            Iterator<GModelElement> iterator = selectedElements.iterator();
            GModelElement firstElement = iterator.next();
            GModelElement secondElement = iterator.next();
            if (firstElement instanceof TaskNode && secondElement instanceof TaskNode) {
                GNode firstNode = (GNode) firstElement;
                GNode secondNode = (GNode) secondElement;
                actions.add(createEdgeAction("Connect with Edge", firstNode, secondNode));
                actions.add(createSequenceFlowAction("Connect with Sequence Flow", firstNode, secondNode));
            }
        }

        // Delete action
        if (selectedElements.size() == 1) {
            actions.add(new LabeledAction("Delete", Lists.newArrayList(new DeleteOperation(selectedIds)),
                    "fa-minus-square"));
        } else if (selectedElements.size() > 1) {
            actions.add(new LabeledAction("Delete All", Lists.newArrayList(new DeleteOperation(selectedIds)),
                    "fa-minus-square"));
        }

        return actions;
    }

    private LabeledAction createEdgeAction(final String label, final GNode source, final GNode node) {
        return new LabeledAction(label, Lists.newArrayList(new CreateEdgeOperation(EDGE, source.getId(), node.getId())),
                "fa-plus-square");
    }

    private LabeledAction createSequenceFlowAction(final String label, final GNode source, final GNode node) {
        return new LabeledAction(label,
                Lists.newArrayList(new CreateEdgeOperation(BPMNTypes.SEQUENCE_FLOW, source.getId(), node.getId())),
                "fa-plus-square");
    }

    private Set<LabeledAction> createEdgeActions(final GNode source, final Set<? extends GNode> targets) {
        Set<LabeledAction> actions = Sets.newLinkedHashSet();
        // add first all edge, then all weighted edge actions to keep a nice order
        targets.forEach(node -> actions.add(createEdgeAction("Create Edge to " + getLabel(node), source, node)));
        targets.forEach(node -> actions
                .add(createSequenceFlowAction("Create Sequence Flow to " + getLabel(node), source, node)));
        return actions;
    }

    private String getLabel(final GNode node) {
        if (node instanceof TaskNode) {
            return ((TaskNode) node).getName();
        }

        if (node instanceof EventNode) {
            return ((EventNode) node).getName();
        }

        if (node instanceof GatewayNode) {
            return ((GatewayNode) node).getName();
        }
        return node.getId();
    }

}
