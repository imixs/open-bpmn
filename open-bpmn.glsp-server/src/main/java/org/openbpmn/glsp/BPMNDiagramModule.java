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
package org.openbpmn.glsp;

import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.ActionHandler;
import org.eclipse.glsp.server.di.DiagramModule;
import org.eclipse.glsp.server.di.MultiBinding;
import org.eclipse.glsp.server.diagram.DiagramConfiguration;
import org.eclipse.glsp.server.features.commandpalette.CommandPaletteActionProvider;
import org.eclipse.glsp.server.features.core.model.GModelFactory;
import org.eclipse.glsp.server.features.core.model.SourceModelStorage;
import org.eclipse.glsp.server.features.directediting.ContextEditValidator;
import org.eclipse.glsp.server.features.toolpalette.ToolPaletteItemProvider;
import org.eclipse.glsp.server.features.validation.ModelValidator;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.CutOperationHandler;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.openbpmn.bpmn.BPMNGModelFactory;
import org.openbpmn.bpmn.BPMNGModelState;
import org.openbpmn.bpmn.BPMNSourceModelStorage;
import org.openbpmn.bpmn.operations.BPMNApplyPropertiesUpdateOperationHandler;
import org.openbpmn.bpmn.operations.BPMNChangeBoundsOperationHandler;
import org.openbpmn.bpmn.operations.BPMNChangeRoutingPointsOperationHandler;
import org.openbpmn.bpmn.operations.BPMNDeleteNodeHandler;
import org.openbpmn.extension.BPMNExtension;
import org.openbpmn.extension.DefaultBPMNEventExtension;
import org.openbpmn.extension.DefaultBPMNTaskExtension;
import org.openbpmn.glsp.elements.event.BPMNCreateEventDefinitionHandler;
import org.openbpmn.glsp.elements.event.BPMNCreateEventHandler;
import org.openbpmn.glsp.elements.flow.BPMNCreateSequenceFlowHandler;
import org.openbpmn.glsp.elements.gateway.BPMNCreateGatewayHandler;
import org.openbpmn.glsp.elements.pool.CreatePoolHandler;
import org.openbpmn.glsp.elements.task.BPMNCreateTaskHandler;
import org.openbpmn.glsp.provider.BPMNCommandPaletteActionProvider;
import org.openbpmn.glsp.provider.BPMNToolPaletteItemProvider;
import org.openbpmn.glsp.validators.BPMNModelValidator;
import org.openbpmn.glsp.validators.LabelEditValidator;

import com.google.inject.multibindings.Multibinder;

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

    private Multibinder<BPMNExtension> bpmnExtensionBinder;

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
        // binding.add(RequestClipboardDataActionHandler.class);
    }

    @Override
    protected void configureOperationHandlers(final MultiBinding<OperationHandler> binding) {
        super.configureOperationHandlers(binding);
//        binding.add(ApplyLabelEditOperationHandler.class);
//        binding.add(ChangeBoundsOperationHandler.class);
//        binding.add(ChangeRoutingPointsHandler.class);
        binding.add(CutOperationHandler.class);
        // binding.add(DeleteOperationHandler.class);
//        binding.add(LayoutOperationHandler.class);
//        binding.add(PasteOperationHandler.class);
//        binding.add(ReconnectEdgeOperationHandler.class);

        binding.add(BPMNChangeBoundsOperationHandler.class);
        binding.add(BPMNChangeRoutingPointsOperationHandler.class);

        // Tasks
        binding.add(BPMNCreateTaskHandler.class);

        // Events
        binding.add(BPMNCreateEventHandler.class);
        binding.add(BPMNCreateEventDefinitionHandler.class);

        // Flows
        binding.add(BPMNCreateSequenceFlowHandler.class);

        // Gateways
        binding.add(BPMNCreateGatewayHandler.class);

        // Pools
        binding.add(CreatePoolHandler.class);

        // binding.remove(LayoutOperationHandler.class);

        // register apply operations send from the client
        binding.add(BPMNApplyPropertiesUpdateOperationHandler.class);

        // GLSP Operation handlers for ModelUpdates
        binding.add(BPMNDeleteNodeHandler.class);

    }

    /**
     * This method creates a new Multibinder to bind BPMNExension
     */
    @Override
    protected void configureAdditionals() {
        super.configureAdditionals();
        // create the BPMNExtension binder
        bpmnExtensionBinder = Multibinder.newSetBinder(binder(), BPMNExtension.class);
        configureBPMNExtensions(bpmnExtensionBinder);
    }

    /**
     * This method adds the BPMN default extensions
     * <p>
     * Overwrite this method to add custom BPMN Extensions
     *
     * @param binding
     */
    protected void configureBPMNExtensions(final Multibinder<BPMNExtension> binding) {
        // bind BPMN default extensions
        binding.addBinding().to(DefaultBPMNEventExtension.class);
        binding.addBinding().to(DefaultBPMNTaskExtension.class);

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
