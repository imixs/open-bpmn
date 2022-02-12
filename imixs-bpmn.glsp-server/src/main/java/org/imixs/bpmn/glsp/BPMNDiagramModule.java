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

import org.eclipse.glsp.server.di.GModelJsonDiagramModule;
import org.eclipse.glsp.server.di.MultiBinding;
import org.eclipse.glsp.server.diagram.DiagramConfiguration;
import org.eclipse.glsp.server.features.commandpalette.CommandPaletteActionProvider;
import org.eclipse.glsp.server.features.core.model.JsonFileGModelLoader;
import org.eclipse.glsp.server.features.core.model.ModelSourceLoader;
import org.eclipse.glsp.server.features.modelsourcewatcher.FileWatcher;
import org.eclipse.glsp.server.features.modelsourcewatcher.ModelSourceWatcher;
import org.eclipse.glsp.server.operations.OperationHandler;
import org.eclipse.glsp.server.operations.gmodel.LayoutOperationHandler;
import org.imixs.bpmn.glsp.handler.CreateManualTaskHandler;
import org.imixs.bpmn.glsp.handler.MinimalCreateNodeOperationHandler;
import org.imixs.bpmn.glsp.provider.BPMNCommandPaletteActionProvider;

/**
 * The DiagramModule contains the bindings in dedicated methods. Imixs BPMN extends this module and customize it by
 * overriding dedicated binding methods.
 *
 *
 * @author rsoika
 *
 */
public class BPMNDiagramModule extends GModelJsonDiagramModule {

   @Override
   protected Class<? extends DiagramConfiguration> bindDiagramConfiguration() {
      return MinimalDiagramConfiguration.class;
   }

   @Override
   protected void configureOperationHandlers(final MultiBinding<OperationHandler> binding) {
      super.configureOperationHandlers(binding);
      binding.add(MinimalCreateNodeOperationHandler.class);
      binding.add(CreateManualTaskHandler.class);
      binding.remove(LayoutOperationHandler.class);
   }

   @Override
   protected Class<? extends ModelSourceLoader> bindSourceModelLoader() {
      return JsonFileGModelLoader.class;
   }

   @Override
   protected Class<? extends ModelSourceWatcher> bindModelSourceWatcher() {
      return FileWatcher.class;
   }

   /**
    * Add Palette options by providing the BPMN PaletteActionProvider
    */
   @Override
   protected Class<? extends CommandPaletteActionProvider> bindCommandPaletteActionProvider() {
      return BPMNCommandPaletteActionProvider.class;
   }

   @Override
   public String getDiagramType() { return "minimal-diagram"; }

}
