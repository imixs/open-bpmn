/********************************************************************************
 * Copyright (c) 2020-2022 EclipseSource and others.
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
package org.openbpmn.glsp;

import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.ActionHandler;
import org.eclipse.glsp.server.di.DiagramModule;
import org.eclipse.glsp.server.di.MultiBinding;
import org.eclipse.glsp.server.diagram.DiagramConfiguration;
import org.eclipse.glsp.server.features.clipboard.RequestClipboardDataActionHandler;
import org.eclipse.glsp.server.features.commandpalette.CommandPaletteActionProvider;
import org.eclipse.glsp.server.features.core.model.GModelFactory;
import org.eclipse.glsp.server.features.core.model.SourceModelStorage;
import org.eclipse.glsp.server.features.directediting.ApplyLabelEditOperationHandler;
import org.eclipse.glsp.server.features.directediting.ContextEditValidator;
import org.eclipse.glsp.server.features.toolpalette.ToolPaletteItemProvider;
import org.eclipse.glsp.server.features.validation.ModelValidator;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.eclipse.glsp.server.operations.gmodel.ChangeBoundsOperationHandler;
import org.eclipse.glsp.server.operations.gmodel.ChangeRoutingPointsHandler;
import org.eclipse.glsp.server.operations.gmodel.CutOperationHandler;
import org.eclipse.glsp.server.operations.gmodel.LayoutOperationHandler;
import org.eclipse.glsp.server.operations.gmodel.PasteOperationHandler;
import org.eclipse.glsp.server.operations.gmodel.ReconnectEdgeOperationHandler;
import org.openbpmn.bpmn.BPMNGModelFactory;
import org.openbpmn.bpmn.BPMNGModelState;
import org.openbpmn.bpmn.BPMNSourceModelStorage;
import org.openbpmn.bpmn.operations.BPMNDeleteNodeHandler;
import org.openbpmn.glsp.elements.event.BPMNCreateEventHandler;
import org.openbpmn.glsp.elements.event.edit.ApplyEventUpdateOperationHandler;
import org.openbpmn.glsp.elements.flow.BPMNCreateSequenceFlowHandler;
import org.openbpmn.glsp.elements.gateway.BPMNCreateGatewayHandler;
import org.openbpmn.glsp.elements.pool.CreatePoolHandler;
import org.openbpmn.glsp.elements.task.BPMNCreateTaskHandler;
import org.openbpmn.glsp.provider.BPMNCommandPaletteActionProvider;
import org.openbpmn.glsp.provider.BPMNToolPaletteItemProvider;
import org.openbpmn.glsp.validators.BPMNModelValidator;
import org.openbpmn.glsp.validators.LabelEditValidator;

/**
 * The DiagramModule contains the bindings in dedicated methods. Imixs BPMN
 * extends this module and customize it by overriding dedicated binding methods.
 *
 *
 * @author rsoika
 *
 */
public class BPMNDiagramModule extends DiagramModule {
    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(BPMNDiagramModule.class.getName());

    @Override
    protected Class<? extends GModelState> bindGModelState() {
        return BPMNGModelState.class;
    }

    @Override
    protected Class<? extends SourceModelStorage> bindSourceModelStorage() {
        return BPMNSourceModelStorage.class;
    }

    @Override
    protected Class<? extends GModelFactory> bindGModelFactory() {
        return BPMNGModelFactory.class;
    }

    @Override
    protected void configureActionHandlers(final MultiBinding<ActionHandler> binding) {
        super.configureActionHandlers(binding);
        binding.add(RequestClipboardDataActionHandler.class);
    }

    @Override
    protected void configureOperationHandlers(final MultiBinding<OperationHandler> binding) {
        super.configureOperationHandlers(binding);
        binding.add(ApplyLabelEditOperationHandler.class);
        binding.add(ChangeBoundsOperationHandler.class);
        binding.add(ChangeRoutingPointsHandler.class);
        binding.add(CutOperationHandler.class);
        // binding.add(DeleteOperationHandler.class);
        binding.add(LayoutOperationHandler.class);
        binding.add(PasteOperationHandler.class);
        binding.add(ReconnectEdgeOperationHandler.class);

        // Tasks
        binding.add(BPMNCreateTaskHandler.class);

        // Events
        binding.add(BPMNCreateEventHandler.class);
//        binding.add(CreateEndEventHandler.class);
//        binding.add(CreateCatchEventHandler.class);
//        binding.add(CreateThrowEventHandler.class);

        // Flows
        binding.add(BPMNCreateSequenceFlowHandler.class);
        // binding.add(XXCreateEdgeHandler.class);

        // Gateways
        binding.add(BPMNCreateGatewayHandler.class);
        // binding.add(CreateInclusiveGatewayHandler.class);

        // Pools
        binding.add(CreatePoolHandler.class);

        binding.remove(LayoutOperationHandler.class);

        // WICHTIG:

        // register apply operations send from the client
        binding.add(ApplyEventUpdateOperationHandler.class);

        // GLSP Operation handlers for ModelUpdates
        binding.add(BPMNDeleteNodeHandler.class);

    }

    @Override
    protected Class<? extends ToolPaletteItemProvider> bindToolPaletteItemProvider() {
        return BPMNToolPaletteItemProvider.class;
    }

    @Override
    protected Class<? extends DiagramConfiguration> bindDiagramConfiguration() {
        return BPMNDiagramConfiguration.class;
    }

    /**
     * Register validator classes
     */
    @Override
    protected Class<? extends ModelValidator> bindModelValidator() {
        return BPMNModelValidator.class;
    }

    @Override
    protected void configureContextEditValidators(final MultiBinding<ContextEditValidator> binding) {
        super.configureContextEditValidators(binding);
        binding.add(LabelEditValidator.class);
    }

    /**
     * Add Create actions to the palette that opens up on Ctrl+Space
     */
    @Override
    protected Class<? extends CommandPaletteActionProvider> bindCommandPaletteActionProvider() {
        return BPMNCommandPaletteActionProvider.class;
    }

    @Override
    public String getDiagramType() {
        return "bpmn-diagram";
    }

}
