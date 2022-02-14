/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
import org.eclipse.glsp.server.actions.ActionHandler;
import org.eclipse.glsp.server.di.GModelJsonDiagramModule;
import org.eclipse.glsp.server.di.MultiBinding;
import org.eclipse.glsp.server.di.ServerModule;
import org.eclipse.glsp.server.diagram.DiagramConfiguration;
import org.eclipse.glsp.server.features.commandpalette.CommandPaletteActionProvider;
import org.eclipse.glsp.server.features.contextactions.ContextActionsProvider;
import org.eclipse.glsp.server.features.contextactions.RequestContextActionsHandler;
import org.eclipse.glsp.server.features.contextmenu.ContextMenuItemProvider;
import org.eclipse.glsp.server.features.core.model.JsonFileGModelLoader;
import org.eclipse.glsp.server.features.core.model.ModelSourceLoader;
import org.eclipse.glsp.server.features.directediting.ContextEditValidator;
import org.eclipse.glsp.server.features.modelsourcewatcher.FileWatcher;
import org.eclipse.glsp.server.features.modelsourcewatcher.ModelSourceWatcher;
import org.eclipse.glsp.server.features.validation.ModelValidator;
import org.eclipse.glsp.server.layout.LayoutEngine;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.eclipse.glsp.server.operations.gmodel.LayoutOperationHandler;
import org.eclipse.glsp.server.protocol.GLSPServer;
import org.imixs.bpmn.glsp.handler.CreateEdgeHandler;
import org.imixs.bpmn.glsp.handler.CreateManualTaskHandler;
import org.imixs.bpmn.glsp.handler.CreateSendTaskHandler;
import org.imixs.bpmn.glsp.handler.CreateServiceTaskHandler;
import org.imixs.bpmn.glsp.handler.LogActionHandler;
import org.imixs.bpmn.glsp.handler.WorkflowRequestContextActionsHandler;
import org.imixs.bpmn.glsp.marker.WorkflowModelValidator;
import org.imixs.bpmn.glsp.provider.BPMNCommandPaletteActionProvider;
import org.imixs.bpmn.glsp.provider.WorkflowContextMenuItemProvider;
import org.imixs.bpmn.glsp.taskedit.TaskEditContextActionProvider;
import org.imixs.bpmn.glsp.taskedit.TaskEditValidator;

/**
 * Class for diagram implementations that operate on the plain gmodel and use JSON for serialization.
 * <p>
 * The diagram module is the central configuration artifact for configuring a client session specific injector. For each
 * session that is initialized by a {@link GLSPServer} a new client session injector is created. The diagram module
 * provides the base bindings necessary to provide diagram implementation (i.e. diagram language). In addition to the
 * diagram specific bindings, session specific bindings like the {@link GModelState} are configured
 *
 * Client session injectors are child injectors of a server injector and therefore inherit the bindings from
 * {@link ServerModule}.
 * <p>
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

   @Override
   protected void configureContextActionsProviders(final MultiBinding<ContextActionsProvider> binding) {
      super.configureContextActionsProviders(binding);
      binding.add(TaskEditContextActionProvider.class);
   }

   @Override
   protected void configureContextEditValidators(final MultiBinding<ContextEditValidator> binding) {
      super.configureContextEditValidators(binding);
      binding.add(TaskEditValidator.class);
   }

   // @Override
   // protected void configureNavigationTargetProviders(final MultiBinding<NavigationTargetProvider> binding) {
   // super.configureNavigationTargetProviders(binding);
   // binding.add(NextNodeNavigationTargetProvider.class);
   // binding.add(PreviousNodeNavigationTargetProvider.class);
   // binding.add(NodeDocumentationNavigationTargetProvider.class);
   // }

   @Override
   protected void configureOperationHandlers(final MultiBinding<OperationHandler> binding) {
      super.configureOperationHandlers(binding);
      // binding.add(CreateAutomatedTaskHandler.class);
      // binding.add(CreateManualTaskHandler.class);
      // binding.add(CreateDecisionNodeHandler.class);
      // binding.add(CreateMergeNodeHandler.class);
      // binding.add(CreateForkNodeHandler.class);
      // binding.add(CreateJoinNodeHandler.class);
      binding.add(CreateEdgeHandler.class);
      // binding.add(CreateWeightedEdgeHandler.class);
      // binding.add(CreateCategoryHandler.class);
      // binding.add(EditTaskOperationHandler.class);
      // binding.add(ApplyTaskEditOperationHandler.class);

      super.configureOperationHandlers(binding);

      // binding.add(MinimalCreateNodeOperationHandler.class);

      // Add Task types
      binding.add(CreateManualTaskHandler.class);
      binding.add(CreateServiceTaskHandler.class);
      binding.add(CreateSendTaskHandler.class);

      binding.remove(LayoutOperationHandler.class);
   }

   @Override
   protected void configureActionHandlers(final MultiBinding<ActionHandler> binding) {
      super.configureActionHandlers(binding);
      binding.rebind(RequestContextActionsHandler.class, WorkflowRequestContextActionsHandler.class);
      binding.add(LogActionHandler.class);
   }

   // @Override
   // public Class<? extends PopupModelFactory> bindPopupModelFactory() {
   // return WorkflowPopupFactory.class;
   // }

   @Override
   protected Class<? extends ModelValidator> bindModelValidator() {
      return WorkflowModelValidator.class;
   }

   // @Override
   // protected Class<? extends LabelEditValidator> bindLabelEditValidator() {
   // return WorkflowLabelEditValidator.class;
   // }

   @Override
   protected Class<? extends LayoutEngine> bindLayoutEngine() {
      return BPMNLayoutEngine.class;
   }

   @Override
   protected Class<? extends ContextMenuItemProvider> bindContextMenuItemProvider() {
      return WorkflowContextMenuItemProvider.class;
   }

   /**
    * Add Palette options by providing the BPMN PaletteActionProvider
    */
   @Override
   protected Class<? extends CommandPaletteActionProvider> bindCommandPaletteActionProvider() {
      return BPMNCommandPaletteActionProvider.class;
   }

   // @Override
   // protected Class<? extends NavigationTargetResolver> bindNavigationTargetResolver() {
   // return WorkflowNavigationTargetResolver.class;
   // }

   @Override
   public String getDiagramType() { return "minimal-diagram"; }

}
