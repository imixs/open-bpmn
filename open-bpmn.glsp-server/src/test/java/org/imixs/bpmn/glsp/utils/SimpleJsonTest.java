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
package org.imixs.bpmn.glsp.utils;

import static org.eclipse.glsp.graph.GraphPackage.Literals.GEDGE;
import static org.eclipse.glsp.graph.GraphPackage.Literals.GGRAPH;
import static org.eclipse.glsp.graph.GraphPackage.Literals.GNODE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.glsp.graph.gson.GraphGsonConfigurator;
import org.eclipse.glsp.server.features.core.model.RequestModelAction;
import org.eclipse.glsp.server.utils.ClientOptionsUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleJsonTest {
   private static Logger logger = Logger.getLogger(SimpleJsonTest.class.getName());

   private Path workingDir;

   private GraphGsonConfigurator gsonConfigurator;

   @Before
   public void init() {
      this.workingDir = Path.of("", "src/test/resources");

      // initializeGsonConfigurator
      gsonConfigurator = new GraphGsonConfigurator().withDefaultTypes();

   }

   @Test
   public void testJsonFile() {
      Map<String, EClass> customTypes = new HashMap<>();
      customTypes.put("mygraph", GGRAPH);
      customTypes.put("mynode", GNODE);
      customTypes.put("myedge", GEDGE);
      gsonConfigurator.withTypes(customTypes);
      Path file = this.workingDir.resolve("empty.minimal");

      String resourceName = file.toString();
      logger.info("file=" + resourceName);
      // simulate a ModelState...
      RequestModelAction action = new RequestModelAction();

      action.getOptions().put(ClientOptionsUtil.SOURCE_URI, resourceName);

      logger.info("File Path=" + resourceName);

      TestModelState testModelState = new TestModelState();
      testModelState.setClientOptions(action.getOptions());

      BPMNFileGModelLoader bpmnFileGModelLoader = new BPMNFileGModelLoader();
      bpmnFileGModelLoader.setModelState(testModelState);
      bpmnFileGModelLoader.loadSourceModel(action);

      Assert.assertNotNull(testModelState.getRoot());

      Assert.assertNotNull(testModelState.getRoot().getChildren());

      // we expect one child
      Assert.assertEquals(1, testModelState.getRoot().getChildren().size());

   }

   /**
    * Just read the test file and print its content
    *
    * @throws IOException
    */
   @Test
   public void readTestFile() throws IOException {
      Path file = this.workingDir.resolve("empty.minimal");
      String content = Files.readString(file);
      logger.info(content);
   }

}
