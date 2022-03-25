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
package org.imixs.bpmn.glsp;

import org.eclipse.glsp.graph.GraphExtension;
import org.eclipse.glsp.server.di.GModelJsonDiagramModule;
import org.eclipse.glsp.server.di.MultiBinding;
import org.eclipse.glsp.server.diagram.DiagramConfiguration;
import org.eclipse.glsp.server.features.commandpalette.CommandPaletteActionProvider;
import org.eclipse.glsp.server.features.core.model.JsonFileGModelLoader;
import org.eclipse.glsp.server.features.core.model.ModelSourceLoader;
import org.eclipse.glsp.server.features.directediting.ContextEditValidator;
import org.eclipse.glsp.server.features.modelsourcewatcher.FileWatcher;
import org.eclipse.glsp.server.features.modelsourcewatcher.ModelSourceWatcher;
import org.eclipse.glsp.server.features.toolpalette.ToolPaletteItemProvider;
import org.eclipse.glsp.server.features.validation.ModelValidator;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.eclipse.glsp.server.operations.gmodel.LayoutOperationHandler;
import org.imixs.bpmn.glsp.elements.event.CreateEndEventHandler;
import org.imixs.bpmn.glsp.elements.event.CreateStartEventHandler;
import org.imixs.bpmn.glsp.elements.event.edit.ApplyEventUpdateOperationHandler;
import org.imixs.bpmn.glsp.elements.flow.CreateSequenceFlowHandler;
import org.imixs.bpmn.glsp.elements.gateway.CreateExclusiveGatewayHandler;
import org.imixs.bpmn.glsp.elements.gateway.CreateInclusiveGatewayHandler;
import org.imixs.bpmn.glsp.elements.pool.CreatePoolHandler;
import org.imixs.bpmn.glsp.elements.task.CreateManualTaskHandler;
import org.imixs.bpmn.glsp.elements.task.CreateScriptTaskHandler;
import org.imixs.bpmn.glsp.elements.task.CreateSendTaskHandler;
import org.imixs.bpmn.glsp.elements.task.CreateServiceTaskHandler;
import org.imixs.bpmn.glsp.elements.task.CreateUserTaskHandler;
import org.imixs.bpmn.glsp.provider.BPMNCommandPaletteActionProvider;
import org.imixs.bpmn.glsp.provider.BPMNToolPaletteItemProvider;
import org.imixs.bpmn.glsp.validators.BPMNModelValidator;
import org.imixs.bpmn.glsp.validators.LabelEditValidator;

/**
 * The DiagramModule contains the bindings in dedicated methods. Imixs BPMN
 * extends this module and customize it by overriding dedicated binding methods.
 *
 *
 * @author rsoika
 *
 */
public class BPMNDiagramModule extends GModelJsonDiagramModule {

    @Override
    protected Class<? extends DiagramConfiguration> bindDiagramConfiguration() {
        return BPMNDiagramConfiguration.class;
    }

    @Override
    protected Class<? extends ModelSourceLoader> bindSourceModelLoader() {
        return JsonFileGModelLoader.class;
    }

    @Override
    protected Class<? extends ModelSourceWatcher> bindModelSourceWatcher() {
        return FileWatcher.class;
    }

    @Override
    protected Class<? extends GraphExtension> bindGraphExtension() {
        return BPMNGraphExtension.class;
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

    @Override
    protected void configureOperationHandlers(final MultiBinding<OperationHandler> binding) {
        super.configureOperationHandlers(binding);

        // Tasks
        binding.add(CreateManualTaskHandler.class);
        binding.add(CreateServiceTaskHandler.class);
        binding.add(CreateSendTaskHandler.class);
        binding.add(CreateScriptTaskHandler.class);
        binding.add(CreateUserTaskHandler.class);

        // Events
        binding.add(CreateStartEventHandler.class);
        binding.add(CreateEndEventHandler.class);

        // Flows
        binding.add(CreateSequenceFlowHandler.class);
        // binding.add(XXCreateEdgeHandler.class);

        // Gateways
        binding.add(CreateExclusiveGatewayHandler.class);
        binding.add(CreateInclusiveGatewayHandler.class);

        // Pools
        binding.add(CreatePoolHandler.class);

        binding.remove(LayoutOperationHandler.class);

        // WICHTIG:

        // register apply operations send from the client
        binding.add(ApplyEventUpdateOperationHandler.class);
    }

    /**
     * Add Create actions to the palette that opens up on Ctrl+Space
     */
    @Override
    protected Class<? extends CommandPaletteActionProvider> bindCommandPaletteActionProvider() {
        return BPMNCommandPaletteActionProvider.class;
    }

    @Override
    protected Class<? extends ToolPaletteItemProvider> bindToolPaletteItemProvider() {
        return BPMNToolPaletteItemProvider.class;
    }

    @Override
    public String getDiagramType() {
        return "bpmn-diagram";
    }

}
