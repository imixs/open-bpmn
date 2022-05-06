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
package org.openbpmn.glsp.provider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.glsp.server.actions.TriggerElementCreationAction;
import org.eclipse.glsp.server.actions.TriggerNodeCreationAction;
import org.eclipse.glsp.server.features.toolpalette.PaletteItem;
import org.eclipse.glsp.server.features.toolpalette.ToolPaletteItemProvider;
import org.eclipse.glsp.server.operations.CreateEdgeOperation;
import org.eclipse.glsp.server.operations.CreateOperation;
import org.eclipse.glsp.server.operations.CreateOperationHandler;
import org.eclipse.glsp.server.operations.OperationHandlerRegistry;
import org.openbpmn.bpmn.BPMNEventType;
import org.openbpmn.bpmn.BPMNGatewayType;
import org.openbpmn.bpmn.BPMNTaskType;
import org.openbpmn.glsp.utils.ModelTypes;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * The BPMNToolPaletteItemProvider collects all registered instances of
 * CreateNodeOperationHandler and CreateEdgeOperationHandler and shows them in
 * different groups.
 * <p>
 * The class defines different palette groups
 *
 * @author rsoika
 *
 */
public class BPMNToolPaletteItemProvider implements ToolPaletteItemProvider {

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
                PaletteItem.createPaletteGroup("gateway-group", "Gateways", createPaletteGatewayItems(),
                        "symbol-property", "D"),
                // show all edges
                PaletteItem.createPaletteGroup("edge-group", "Edges", edges, "symbol-property", "E"));

    }

    /**
     * Create a palette Item Group with all Task elements
     *
     * @return
     */
    protected List<PaletteItem> createPaletteTaskItems() {

        List<PaletteItem> result = new ArrayList<>();
//        result.add(
//                new PaletteItem("manual-task", "Manual Task", new TriggerNodeCreationAction(BPMNActivity.TaskType...M   . ModelTypes.MANUAL_TASK)));
        result.add(new PaletteItem(BPMNTaskType.MANUAL.getName(), "Manual Task",
                new TriggerNodeCreationAction(BPMNTaskType.MANUAL.getName())));
        result.add(new PaletteItem(BPMNTaskType.USER.getName(), "User Task",
                new TriggerNodeCreationAction(BPMNTaskType.USER.getName())));

        result.add(new PaletteItem(BPMNTaskType.SERVICE.getName(), "Service Task",
                new TriggerNodeCreationAction(BPMNTaskType.SERVICE.getName())));
        result.add(new PaletteItem(BPMNTaskType.SEND.getName(), "Send Task",
                new TriggerNodeCreationAction(BPMNTaskType.SEND.getName())));
        result.add(new PaletteItem(BPMNTaskType.SCRIPT.getName(), "Script Task",
                new TriggerNodeCreationAction(BPMNTaskType.SCRIPT.getName())));

        return result;
    }

    /**
     * Create a palette Item Group with all Event elements
     *
     * @return
     */
    protected List<PaletteItem> createPaletteEventItems() {

        List<PaletteItem> result = new ArrayList<>();
        result.add(new PaletteItem(BPMNEventType.START.name, "Start Event",
                new TriggerNodeCreationAction(BPMNEventType.START.name)));
        result.add(new PaletteItem(BPMNEventType.END.name, "End Event",
                new TriggerNodeCreationAction(BPMNEventType.END.name)));

        result.add(new PaletteItem(BPMNEventType.CATCH.name, "Catch Event",
                new TriggerNodeCreationAction(BPMNEventType.CATCH.name)));
        result.add(new PaletteItem(BPMNEventType.THROW.name, "Throw Event",
                new TriggerNodeCreationAction(BPMNEventType.THROW.name)));
        return result;
    }

    /**
     * Create a palette Item Group with Pools and Lanes
     *
     * @return
     */
    protected List<PaletteItem> createPalettePools() {

        List<PaletteItem> result = new ArrayList<>();
        result.add(new PaletteItem("pool", "Pool", new TriggerNodeCreationAction(ModelTypes.POOL)));
        return result;
    }

    protected List<PaletteItem> createPaletteGatewayItems() {

        List<PaletteItem> result = new ArrayList<>();
        result.add(new PaletteItem(BPMNGatewayType.EXCLUSIVE.name, "Exclusive",
                new TriggerNodeCreationAction(BPMNGatewayType.EXCLUSIVE.name)));

        result.add(new PaletteItem(BPMNGatewayType.INCLUSIVE.name, "Inclusive",
                new TriggerNodeCreationAction(BPMNGatewayType.INCLUSIVE.name)));

        result.add(new PaletteItem(BPMNGatewayType.PARALLEL.name, "Parallel",
                new TriggerNodeCreationAction(BPMNGatewayType.PARALLEL.name)));

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
