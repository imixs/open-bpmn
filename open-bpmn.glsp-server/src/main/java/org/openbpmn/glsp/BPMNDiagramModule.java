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
import org.imixs.openbpmn.ImixsBPMNTaskExtension;
import org.openbpmn.extension.BPMNCreateExtensionHandler;
import org.openbpmn.extension.BPMNExtension;
import org.openbpmn.extension.DefaultBPMNEventExtension;
import org.openbpmn.extension.DefaultBPMNGatewayExtension;
import org.openbpmn.extension.DefaultBPMNTaskExtension;
import org.openbpmn.glsp.elements.event.BPMNCreateEventDefinitionHandler;
import org.openbpmn.glsp.elements.event.BPMNCreateEventHandler;
import org.openbpmn.glsp.elements.flow.BPMNCreateSequenceFlowHandler;
import org.openbpmn.glsp.elements.gateway.BPMNCreateGatewayHandler;
import org.openbpmn.glsp.elements.pool.CreatePoolHandler;
import org.openbpmn.glsp.elements.task.BPMNCreateTaskHandler;
import org.openbpmn.glsp.operations.BPMNApplyPropertiesUpdateOperationHandler;
import org.openbpmn.glsp.operations.BPMNChangeBoundsOperationHandler;
import org.openbpmn.glsp.operations.BPMNChangeRoutingPointsOperationHandler;
import org.openbpmn.glsp.operations.BPMNDeleteNodeHandler;
import org.openbpmn.glsp.provider.BPMNCommandPaletteActionProvider;
import org.openbpmn.glsp.provider.BPMNToolPaletteItemProvider;
import org.openbpmn.glsp.validators.BPMNModelValidator;
import org.openbpmn.glsp.validators.LabelEditValidator;
import org.openbpmn.model.BPMNGModelFactory;
import org.openbpmn.model.BPMNGModelState;
import org.openbpmn.model.BPMNSourceModelStorage;

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

        // Extension handler
        binding.add(BPMNCreateExtensionHandler.class);

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
        binding.addBinding().to(DefaultBPMNGatewayExtension.class);

        // Imixs Extensions (just for testing)
        binding.addBinding().to(ImixsBPMNTaskExtension.class);

    }

}
