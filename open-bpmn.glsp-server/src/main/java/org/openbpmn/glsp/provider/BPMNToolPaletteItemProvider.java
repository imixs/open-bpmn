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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.glsp.server.actions.TriggerEdgeCreationAction;
import org.eclipse.glsp.server.actions.TriggerElementCreationAction;
import org.eclipse.glsp.server.actions.TriggerNodeCreationAction;
import org.eclipse.glsp.server.features.toolpalette.PaletteItem;
import org.eclipse.glsp.server.features.toolpalette.ToolPaletteItemProvider;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.eclipse.glsp.server.operations.CreateOperation;
import org.eclipse.glsp.server.operations.CreateOperationHandler;
import org.eclipse.glsp.server.operations.OperationHandlerRegistry;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.extension.BPMNExtension;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * The BPMNToolPaletteItemProvider collects all registered instances of
 * CreateNodeOperationHandler and CreateEdgeOperationHandler and shows them in
 * different groups.
 * <p>
 * The class defines different palette groups
 * <p>
 * Note: some of the CreateOperationHandlers do not create new model elements
 * but extend existing ones with additional behavior (e.g. Event Definitions or
 * Extensions)
 *
 * @author rsoika
 */
public class BPMNToolPaletteItemProvider implements ToolPaletteItemProvider {

    @Inject
    protected Set<BPMNExtension> extensions;

    @Inject
    protected OperationHandlerRegistry operationHandlerRegistry;

    private int counter;

    @Override
    public List<PaletteItem> getItems(final Map<String, String> args) {
        List<CreateOperationHandler> handlers = operationHandlerRegistry.getAll().stream()
                .filter(CreateOperationHandler.class::isInstance).map(CreateOperationHandler.class::cast)
                .collect(Collectors.toList());
        counter = 0;

        // Create custom Palette Groups
        List<PaletteItem> edges = createPaletteItems(handlers, CreateEdgeOperation.class);
        return Lists.newArrayList(
                PaletteItem.createPaletteGroup("pool-group", "Pools", createPalettePools(), "symbol-property", "A"),
                PaletteItem.createPaletteGroup("task-group", "Tasks", createPaletteTaskItems(), "symbol-property", "B"),
                PaletteItem.createPaletteGroup("event-group", "Events", createPaletteEventItems(), "symbol-property",
                        "C"),
                PaletteItem.createPaletteGroup("event-group", "Event Definitions", createPaletteEventDefinitions(),
                        "symbol-property", "D"),
                PaletteItem.createPaletteGroup("gateway-group", "Gateways", createPaletteGatewayItems(),
                        "symbol-property", "E"),
                PaletteItem.createPaletteGroup("edge-group", "Edges", edges, "symbol-property", "F"),

                PaletteItem.createPaletteGroup("extension-group", "Extensions", createPaletteExtensions(),
                        "symbol-property", "G"));

    }

    /**
     * Create a palette Item Group with all SequenceFlow elements
     *
     * @return
     */
    protected List<PaletteItem> createPaletteSequenceFlowItems() {

        List<PaletteItem> result = new ArrayList<>();
        result.add(new PaletteItem(BPMNTypes.SEQUENCE_FLOW, "Sequence Flow",
                new TriggerEdgeCreationAction(BPMNTypes.SEQUENCE_FLOW)));

        return result;
    }

    /**
     * Create a palette Item Group with all Task elements
     *
     * @return
     */
    protected List<PaletteItem> createPaletteTaskItems() {

        List<PaletteItem> result = new ArrayList<>();
        result.add(new PaletteItem(BPMNTypes.TASK, "Task", new TriggerNodeCreationAction(BPMNTypes.TASK)));
        result.add(new PaletteItem(BPMNTypes.MANUAL_TASK, "Manual Task",
                new TriggerNodeCreationAction(BPMNTypes.MANUAL_TASK)));
        result.add(
                new PaletteItem(BPMNTypes.USER_TASK, "User Task", new TriggerNodeCreationAction(BPMNTypes.USER_TASK)));
        result.add(new PaletteItem(BPMNTypes.SERVICE_TASK, "Service Task",
                new TriggerNodeCreationAction(BPMNTypes.SERVICE_TASK)));
        result.add(
                new PaletteItem(BPMNTypes.SEND_TASK, "Send Task", new TriggerNodeCreationAction(BPMNTypes.SEND_TASK)));
        result.add(new PaletteItem(BPMNTypes.SCRIPT_TASK, "Script Task",
                new TriggerNodeCreationAction(BPMNTypes.SCRIPT_TASK)));

        return result;
    }

    /**
     * Create a palette Item Group with all Event elements
     *
     * @return
     */
    protected List<PaletteItem> createPaletteEventItems() {

        List<PaletteItem> result = new ArrayList<>();
        result.add(new PaletteItem(BPMNTypes.START_EVENT, "Start Event",
                new TriggerNodeCreationAction(BPMNTypes.START_EVENT)));
        result.add(
                new PaletteItem(BPMNTypes.END_EVENT, "End Event", new TriggerNodeCreationAction(BPMNTypes.END_EVENT)));

        result.add(new PaletteItem(BPMNTypes.CATCH_EVENT, "Catch Event",
                new TriggerNodeCreationAction(BPMNTypes.CATCH_EVENT)));
        result.add(new PaletteItem(BPMNTypes.THROW_EVENT, "Throw Event",
                new TriggerNodeCreationAction(BPMNTypes.THROW_EVENT)));
        return result;
    }

    /**
     * Create a palette Item Group with all Event Defintions
     *
     * @return
     */
    protected List<PaletteItem> createPaletteEventDefinitions() {

        List<PaletteItem> result = new ArrayList<>();
        result.add(new PaletteItem(BPMNTypes.EVENT_DEFINITION_CONDITIONAL, "Conditional",
                new TriggerNodeCreationAction(BPMNTypes.EVENT_DEFINITION_CONDITIONAL)));
        result.add(new PaletteItem(BPMNTypes.EVENT_DEFINITION_COMPENSATION, "Compensation",
                new TriggerNodeCreationAction(BPMNTypes.EVENT_DEFINITION_COMPENSATION)));
        result.add(new PaletteItem(BPMNTypes.EVENT_DEFINITION_ERROR, "Error",
                new TriggerNodeCreationAction(BPMNTypes.EVENT_DEFINITION_ERROR)));
        result.add(new PaletteItem(BPMNTypes.EVENT_DEFINITION_LINK, "Link",
                new TriggerNodeCreationAction(BPMNTypes.EVENT_DEFINITION_LINK)));
        result.add(new PaletteItem(BPMNTypes.EVENT_DEFINITION_MESSAGE, "Message",
                new TriggerNodeCreationAction(BPMNTypes.EVENT_DEFINITION_MESSAGE)));
        result.add(new PaletteItem(BPMNTypes.EVENT_DEFINITION_SIGNAL, "Signal",
                new TriggerNodeCreationAction(BPMNTypes.EVENT_DEFINITION_SIGNAL)));
        result.add(new PaletteItem(BPMNTypes.EVENT_DEFINITION_TERMINATE, "Terminate",
                new TriggerNodeCreationAction(BPMNTypes.EVENT_DEFINITION_TERMINATE)));
        result.add(new PaletteItem(BPMNTypes.EVENT_DEFINITION_TIMER, "Timer",
                new TriggerNodeCreationAction(BPMNTypes.EVENT_DEFINITION_TIMER)));
        return result;
    }

    /**
     * Create a palette Item Group with Pools and Lanes
     *
     * @return
     */
    protected List<PaletteItem> createPalettePools() {

        List<PaletteItem> result = new ArrayList<>();
        result.add(new PaletteItem("pool", "Pool", new TriggerNodeCreationAction(BPMNTypes.POOL)));
        result.add(new PaletteItem("lane", "Lane", new TriggerNodeCreationAction(BPMNTypes.LANE)));
        return result;
    }

    protected List<PaletteItem> createPaletteGatewayItems() {

        List<PaletteItem> result = new ArrayList<>();
        result.add(new PaletteItem(BPMNTypes.EXCLUSIVE_GATEWAY, "Exclusive",
                new TriggerNodeCreationAction(BPMNTypes.EXCLUSIVE_GATEWAY)));

        result.add(new PaletteItem(BPMNTypes.INCLUSIVE_GATEWAY, "Inclusive",
                new TriggerNodeCreationAction(BPMNTypes.INCLUSIVE_GATEWAY)));

        result.add(new PaletteItem(BPMNTypes.PARALLEL_GATEWAY, "Parallel",
                new TriggerNodeCreationAction(BPMNTypes.PARALLEL_GATEWAY)));

        return result;
    }

    /**
     * This method creates a PaletteItem for each registered extension.
     * <p>
     * The BPMN default extensions are skpped.
     *
     */
    protected List<PaletteItem> createPaletteExtensions() {
        List<PaletteItem> result = new ArrayList<>();
        List<String> extensionNamespaces = new ArrayList<>();
        if (extensions != null) {
            for (BPMNExtension extension : extensions) {
                // validate if the extension is no Default Extension Namspace.
                if (!BPMNNS.BPMN2.name().equals(extension.getNamespace())
                        && !extensionNamespaces.contains(extension.getNamespace())) {

                    String extensionID = "extension:" + extension.getNamespace();
                    result.add(new PaletteItem(extensionID, extension.getLabel(),
                            new TriggerNodeCreationAction(extensionID))); // BPMNTypes.BPMN_EXTENSION
                    // avoid duplicates
                    extensionNamespaces.add(extension.getNamespace());
                }
            }
        }

        return result;
    }

    /**
     * Create a default palette group for a given CreateOperation type
     *
     * @param handlers
     * @param operationClass
     * @return
     */
    protected List<PaletteItem> createPaletteItems(final List<CreateOperationHandler> handlers,
            final Class<? extends CreateOperation> operationClass) {
        return handlers.stream().filter(h -> operationClass.isAssignableFrom(h.getHandledOperationType())).flatMap(
                handler -> handler.getTriggerActions().stream().map(action -> create(action, handler.getLabel())))
                .sorted(Comparator.comparing(PaletteItem::getLabel)).collect(Collectors.toList());
    }

    protected PaletteItem create(final TriggerElementCreationAction action, final String label) {
        return new PaletteItem("palette-item" + counter++, label, action);
    }

}
