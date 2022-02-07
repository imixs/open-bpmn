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

import static org.eclipse.glsp.server.types.GLSPServerException.getOrThrow;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.eclipse.glsp.server.protocol.DefaultGLSPServer;
import org.eclipse.glsp.server.protocol.InitializeResult;
import org.eclipse.glsp.server.utils.MapUtil;

public class WorkflowGLSPServer extends DefaultGLSPServer {
   private static final Logger LOGGER = Logger.getLogger(WorkflowGLSPServer.class);
   private static final String MESSAGE_KEY = "message";
   private static final String TIMESTAMP_KEY = "timestamp";

   @Override
   public CompletableFuture<InitializeResult> handleIntializeArgs(final InitializeResult result,
      final Map<String, String> args) {
      CompletableFuture<InitializeResult> completableResult = CompletableFuture.completedFuture(result);
      if (args.isEmpty()) {
         return completableResult; 
      }

      String timestamp = getOrThrow(MapUtil.getValue(args, TIMESTAMP_KEY),
         "No value present for the given key: " + TIMESTAMP_KEY);
      String message = getOrThrow(MapUtil.getValue(args, MESSAGE_KEY),
         "No value present for the given key: " + MESSAGE_KEY);
      LOGGER.debug(timestamp + ": " + message);

      return completableResult;
   }
}
