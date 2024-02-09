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
import org.eclipse.glsp.server.gmodel.GModelCutOperationHandler;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.openbpmn.extensions.BPMNCreateExtensionHandler;
import org.openbpmn.extensions.BPMNElementExtension;
import org.openbpmn.extensions.BPMNModelExtension;
import org.openbpmn.extensions.elements.ConditionalEventDefinitionExtension;
import org.openbpmn.extensions.elements.DefaultBPMNDataObjectExtension;
import org.openbpmn.extensions.elements.DefaultBPMNDefinitionsExtension;
import org.openbpmn.extensions.elements.DefaultBPMNEdgeExtension;
import org.openbpmn.extensions.elements.DefaultBPMNEventExtension;
import org.openbpmn.extensions.elements.DefaultBPMNGatewayExtension;
import org.openbpmn.extensions.elements.DefaultBPMNMessageExtension;
import org.openbpmn.extensions.elements.DefaultBPMNParticipantExtension;
import org.openbpmn.extensions.elements.DefaultBPMNSequenceFlowExtension;
import org.openbpmn.extensions.elements.DefaultBPMNTaskExtension;
import org.openbpmn.extensions.elements.DefaultBPMNTextAnnotationExtension;
import org.openbpmn.extensions.elements.LinkEventDefinitionExtension;
import org.openbpmn.extensions.elements.SignalEventDefinitionExtension;
import org.openbpmn.extensions.elements.TimerEventDefinitionExtension;
import org.openbpmn.extensions.model.FileLinkExtension;
import org.openbpmn.glsp.elements.data.BPMNCreateDataObjectHandler;
import org.openbpmn.glsp.elements.data.BPMNCreateMessageHandler;
import org.openbpmn.glsp.elements.data.BPMNCreateTextAnnotationHandler;
import org.openbpmn.glsp.elements.edge.BPMNGEdgeCreateHandler;
import org.openbpmn.glsp.elements.event.BPMNCreateEventDefinitionHandler;
import org.openbpmn.glsp.elements.event.BPMNCreateEventHandler;
import org.openbpmn.glsp.elements.gateway.BPMNCreateGatewayHandler;
import org.openbpmn.glsp.elements.label.BPMNApplyEditLabelOperationHandler;
import org.openbpmn.glsp.elements.pool.CreateLaneHandler;
import org.openbpmn.glsp.elements.pool.CreatePoolHandler;
import org.openbpmn.glsp.elements.task.BPMNCreateTaskHandler;
import org.openbpmn.glsp.model.BPMNGModelFactory;
import org.openbpmn.glsp.model.BPMNGModelState;
import org.openbpmn.glsp.model.BPMNSourceModelStorage;
import org.openbpmn.glsp.operations.BPMNApplyPropertiesUpdateOperationHandler;
import org.openbpmn.glsp.operations.BPMNAutoAlignActionHandler;
import org.openbpmn.glsp.operations.BPMNChangeBoundsOperationHandler;
import org.openbpmn.glsp.operations.BPMNChangeRoutingPointsOperationHandler;
import org.openbpmn.glsp.operations.BPMNClipboardDataActionHandler;
import org.openbpmn.glsp.operations.BPMNComputedBoundsActionHandler;
import org.openbpmn.glsp.operations.BPMNDeleteNodeHandler;
import org.openbpmn.glsp.operations.BPMNPasteOperationHandler;
import org.openbpmn.glsp.operations.BPMNReconnectEdgeOperationHandler;
import org.openbpmn.glsp.operations.BPMNResetRoutingActionHandler;
import org.openbpmn.glsp.provider.BPMNCommandPaletteActionProvider;
import org.openbpmn.glsp.provider.BPMNToolPaletteItemProvider;
import org.openbpmn.glsp.validators.BPMNGLSPValidator;
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

    private Multibinder<BPMNElementExtension> bpmnElementExtensionBinder;
    private Multibinder<BPMNModelExtension> bpmnModelExtensionBinder;

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

        // Clipboard
        binding.add(BPMNClipboardDataActionHandler.class);

        // Compute Routing Points
        binding.add(BPMNComputedBoundsActionHandler.class);

        // Clar Routing Points
        binding.add(BPMNResetRoutingActionHandler.class);

        // Auto Align all elements
        binding.add(BPMNAutoAlignActionHandler.class);

    }

    @Override
    protected void configureOperationHandlers(final MultiBinding<OperationHandler<?>> binding) {
        super.configureOperationHandlers(binding);

        // Inline Edit
        binding.add(BPMNApplyEditLabelOperationHandler.class);

        binding.add(GModelCutOperationHandler.class);
        // binding.add(DeleteOperationHandler.class);
        // binding.add(LayoutOperationHandler.class);

        // Clipboard
        binding.add(BPMNPasteOperationHandler.class);
        binding.add(BPMNDeleteNodeHandler.class);

        // Bounds & Routing Points
        binding.add(BPMNChangeBoundsOperationHandler.class);
        binding.add(BPMNChangeRoutingPointsOperationHandler.class);
        binding.add(BPMNReconnectEdgeOperationHandler.class);

        // Tasks
        binding.add(BPMNCreateTaskHandler.class);

        // Events
        binding.add(BPMNCreateEventHandler.class);
        binding.add(BPMNCreateEventDefinitionHandler.class);

        // Flows
        binding.add(BPMNGEdgeCreateHandler.class);

        // Gateways
        binding.add(BPMNCreateGatewayHandler.class);

        // Data Elements
        binding.add(BPMNCreateDataObjectHandler.class);
        binding.add(BPMNCreateMessageHandler.class);
        binding.add(BPMNCreateTextAnnotationHandler.class);

        // Pools & Lanes
        binding.add(CreatePoolHandler.class);
        binding.add(CreateLaneHandler.class);

        // binding.remove(LayoutOperationHandler.class);

        // register apply operations send from the client
        binding.add(BPMNApplyPropertiesUpdateOperationHandler.class);

        // Extension handler
        binding.add(BPMNCreateExtensionHandler.class);

    }

    /**
     * Each handler that should be handled by the GLSP client has to be
     * configured as dedicated client action to indicate that it needs to be
     * dispatched to the client.
     * 
     * The BPMNPropertyPanelUpdateAction is used by Extensions to signal the
     * propertyPanel that we have an update of the current selected element
     */
    // issue #308
    // @Override
    // protected void configureClientActions(MultiBinding<Action> binding) {
    // super.configureClientActions(binding);
    // binding.add(BPMNPropertyPanelUpdateAction.class);
    // }

    /**
     * This method creates a new Multibinder to bind BPMNExension
     */
    @Override
    protected void configureAdditionals() {
        super.configureAdditionals();
        // create the BPMNExtension binder
        bpmnElementExtensionBinder = Multibinder.newSetBinder(binder(), BPMNElementExtension.class);
        configureBPMNElementExtensions(bpmnElementExtensionBinder);
        bpmnModelExtensionBinder = Multibinder.newSetBinder(binder(), BPMNModelExtension.class);
        configureBPMNModelExtensions(bpmnModelExtensionBinder);

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
        return BPMNGLSPValidator.class;
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
        // return "bpmn-diagram";
        return BPMNDiagramConfiguration.DIAGRAM_TYPE;
    }

    /**
     * This method adds the BPMN default element extensions
     * <p>
     * Overwrite this method to add custom BPMN Extensions
     *
     * @param binding
     */
    public void configureBPMNElementExtensions(final Multibinder<BPMNElementExtension> binding) {
        // bind BPMN default element extensions
        binding.addBinding().to(DefaultBPMNDefinitionsExtension.class);
        binding.addBinding().to(DefaultBPMNEventExtension.class);
        binding.addBinding().to(DefaultBPMNTaskExtension.class);
        binding.addBinding().to(DefaultBPMNGatewayExtension.class);
        binding.addBinding().to(DefaultBPMNParticipantExtension.class);
        binding.addBinding().to(DefaultBPMNDataObjectExtension.class);
        binding.addBinding().to(DefaultBPMNMessageExtension.class);
        binding.addBinding().to(DefaultBPMNTextAnnotationExtension.class);
        binding.addBinding().to(DefaultBPMNEdgeExtension.class);
        binding.addBinding().to(DefaultBPMNSequenceFlowExtension.class);
        binding.addBinding().to(TimerEventDefinitionExtension.class);
        binding.addBinding().to(SignalEventDefinitionExtension.class);
        binding.addBinding().to(ConditionalEventDefinitionExtension.class);
        binding.addBinding().to(LinkEventDefinitionExtension.class);

    }

    /**
     * This method adds the BPMN default model extensions
     * <p>
     * Overwrite this method to add custom BPMN Extensions
     *
     * @param binding
     */
    public void configureBPMNModelExtensions(final Multibinder<BPMNModelExtension> binding) {

        // bind BPMN default model extensions
        binding.addBinding().to(FileLinkExtension.class);

    }
}
