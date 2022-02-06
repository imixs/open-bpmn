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
package org.imixs.emfcloud.bpmn2;



import org.eclipse.glsp.graph.GraphExtension;
import org.eclipse.glsp.server.actions.ActionHandler;
import org.eclipse.glsp.server.di.GModelJsonDiagramModule;
import org.eclipse.glsp.server.di.MultiBinding;
import org.eclipse.glsp.server.diagram.DiagramConfiguration;
import org.eclipse.glsp.server.features.commandpalette.CommandPaletteActionProvider;
import org.eclipse.glsp.server.features.contextactions.ContextActionsProvider;
import org.eclipse.glsp.server.features.contextactions.RequestContextActionsHandler;
import org.eclipse.glsp.server.features.contextmenu.ContextMenuItemProvider;
import org.eclipse.glsp.server.features.core.model.JsonFileGModelLoader;
import org.eclipse.glsp.server.features.core.model.ModelSourceLoader;
import org.eclipse.glsp.server.features.directediting.ContextEditValidator;
import org.eclipse.glsp.server.features.directediting.LabelEditValidator;
import org.eclipse.glsp.server.features.modelsourcewatcher.FileWatcher;
import org.eclipse.glsp.server.features.modelsourcewatcher.ModelSourceWatcher;
import org.eclipse.glsp.server.features.navigation.NavigationTargetProvider;
import org.eclipse.glsp.server.features.navigation.NavigationTargetResolver;
import org.eclipse.glsp.server.features.popup.PopupModelFactory;
import org.eclipse.glsp.server.features.validation.ModelValidator;
import org.eclipse.glsp.server.layout.LayoutEngine;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.imixs.emfcloud.bpmn2.handler.CreatePoolHandler;
import org.imixs.emfcloud.bpmn2.handler.CreateTaskHandler;
import org.imixs.emfcloud.bpmn2.irgendwas.ApplyTaskEditOperationHandler;
import org.imixs.emfcloud.bpmn2.irgendwas.EditTaskOperationHandler;
import org.imixs.emfcloud.bpmn2.irgendwas.LogActionHandler;
import org.imixs.emfcloud.bpmn2.irgendwas.NextNodeNavigationTargetProvider;
import org.imixs.emfcloud.bpmn2.irgendwas.NodeDocumentationNavigationTargetProvider;
import org.imixs.emfcloud.bpmn2.irgendwas.PreviousNodeNavigationTargetProvider;
import org.imixs.emfcloud.bpmn2.irgendwas.TaskEditContextActionProvider;
import org.imixs.emfcloud.bpmn2.irgendwas.TaskEditValidator;
import org.imixs.emfcloud.bpmn2.irgendwas.WorkflowCommandPaletteActionProvider;
import org.imixs.emfcloud.bpmn2.irgendwas.WorkflowContextMenuItemProvider;
import org.imixs.emfcloud.bpmn2.irgendwas.WorkflowLabelEditValidator;
import org.imixs.emfcloud.bpmn2.irgendwas.WorkflowModelValidator;
import org.imixs.emfcloud.bpmn2.irgendwas.WorkflowNavigationTargetResolver;
import org.imixs.emfcloud.bpmn2.irgendwas.WorkflowPopupFactory;
import org.imixs.emfcloud.bpmn2.irgendwas.WorkflowRequestContextActionsHandler;
import org.imixs.emfcloud.bpmn2.layout.WorkflowLayoutEngine;
import org.imixs.emfcloud.bpmn2.util.WorkflowBuilder.PoolNodeBuilder;

/**
 * This class is -- FOR WHAT???
 * @author rsoika
 *
 */
public class WorkflowDiagramModule extends GModelJsonDiagramModule {

   @Override
   protected Class<? extends DiagramConfiguration> bindDiagramConfiguration() {
      return WorkflowDiagramConfiguration.class;
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
      return WFGraphExtension.class;
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

   @Override
   protected void configureNavigationTargetProviders(final MultiBinding<NavigationTargetProvider> binding) {
      super.configureNavigationTargetProviders(binding);
      binding.add(NextNodeNavigationTargetProvider.class);
      binding.add(PreviousNodeNavigationTargetProvider.class);
      binding.add(NodeDocumentationNavigationTargetProvider.class);
   }

   @Override
   protected void configureOperationHandlers(final MultiBinding<OperationHandler> binding) {
      super.configureOperationHandlers(binding);
      binding.add(CreateTaskHandler.class);
      binding.add(CreatePoolHandler.class);
      binding.add(EditTaskOperationHandler.class);
      binding.add(ApplyTaskEditOperationHandler.class);
   }

   @Override
   protected void configureActionHandlers(final MultiBinding<ActionHandler> binding) {
      super.configureActionHandlers(binding);
      binding.rebind(RequestContextActionsHandler.class, WorkflowRequestContextActionsHandler.class);
      binding.add(LogActionHandler.class);
   }

   @Override
   public Class<? extends PopupModelFactory> bindPopupModelFactory() {
      return WorkflowPopupFactory.class;
   }

   @Override
   protected Class<? extends ModelValidator> bindModelValidator() {
      return WorkflowModelValidator.class;
   }

   @Override
   protected Class<? extends LabelEditValidator> bindLabelEditValidator() {
      return WorkflowLabelEditValidator.class;
   }

   @Override
   protected Class<? extends LayoutEngine> bindLayoutEngine() {
      return WorkflowLayoutEngine.class;
   }

   @Override
   protected Class<? extends ContextMenuItemProvider> bindContextMenuItemProvider() {
      return WorkflowContextMenuItemProvider.class;
   }

   @Override
   protected Class<? extends CommandPaletteActionProvider> bindCommandPaletteActionProvider() {
      return WorkflowCommandPaletteActionProvider.class;
   }

   @Override
   protected Class<? extends NavigationTargetResolver> bindNavigationTargetResolver() {
      return WorkflowNavigationTargetResolver.class;
   }

   @Override
   public String getDiagramType() { return "workflow-diagram"; }

}
