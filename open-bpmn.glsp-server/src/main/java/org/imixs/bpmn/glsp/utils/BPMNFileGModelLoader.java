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

import static org.eclipse.glsp.server.types.GLSPServerException.getOrThrow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.graph.GraphFactory;
import org.eclipse.glsp.server.features.core.model.GModelFactory;
import org.eclipse.glsp.server.features.core.model.ModelSourceLoader;
import org.eclipse.glsp.server.features.core.model.RequestModelAction;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.types.GLSPServerException;
import org.eclipse.glsp.server.utils.ClientOptionsUtil;
import org.imixs.bpmn.bpmngraph.Task;
import org.imixs.bpmn.bpmngraph.impl.BpmngraphFactoryImpl;

import com.google.inject.Inject;

/**
 * The source model loader that reads the graph model directly from a BPMN file
 * and loads the model into the model state.
 * <p>
 * A <i>source model</i> is an arbitrary model from which the graph model of the
 * diagram is to be created. Implementations of source model loaders are
 * specific to the type of source model or persistence format that is used for a
 * type of source model. A source model loader obtains the information on which
 * source model shall loaded from a {@link RequestModelAction}; typically its
 * client options. Once the source model is loaded, a model loader is expected
 * to put the loaded source model into the model state for further processing,
 * such as transforming the loaded model into a graph model (see
 * {@link GModelFactory}).
 * </p>
 *
 */
public class BPMNFileGModelLoader implements ModelSourceLoader {
    private static Logger logger = Logger.getLogger(BPMNFileGModelLoader.class.getName());
    private static String EMPTY_ROOT_ID = "glsp-graph";

    @Inject
    protected GModelState modelState;

    /**
     * Loads a source model into the <code>modelState</code>.
     *
     * @param action Action sent by the client to specifying the information needed
     *               to load the source model.
     */
    @Override
    public void loadSourceModel(final RequestModelAction action) {

        File file = null;
        if (modelState != null) {
            file = convertToFile(modelState);
        } else {
            // take action element
            Optional<File> actionFile = ClientOptionsUtil.getSourceUriAsFile(action.getOptions());
            if (actionFile.isPresent()) {
                file = actionFile.get();
            }
        }
        if (file != null) {
            logger.info("...load file...");
        } else {

        }

        // Finally we set the new model and the revision status to -1
        loadSourceModel(file, modelState).ifPresent(root -> {
            modelState.setRoot(root);
            modelState.getRoot().setRevision(-1);
        });
    }

    /**
     * Helper method to
     *
     * @param modelState
     * @return
     */
    protected File convertToFile(final GModelState modelState) {
        return getOrThrow(ClientOptionsUtil.getSourceUriAsFile(modelState.getClientOptions()),
                "Invalid file URI:" + ClientOptionsUtil.getSourceUri(modelState.getClientOptions()));
    }

    protected Optional<GModelRoot> loadSourceModel(final File file, final GModelState modelState) {
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {

            BpmngraphFactoryImpl bpmnGraphFactory = new BpmngraphFactoryImpl();

            // simulate something
            GModelRoot root = createNewEmptyRoot(modelState);
            Task taskNode = bpmnGraphFactory.createTask();
            taskNode.setId("someTaskID");
            root.getChildren().add(taskNode);

            return Optional.ofNullable(root);
        } catch (IOException e) {
            logger.severe(e.getMessage());
            throw new GLSPServerException("Could not load model from file: " + file.toURI().toString(), e);
        }
    }

    protected GModelRoot createNewEmptyRoot(final GModelState modelState) {
        GModelRoot root = GraphFactory.eINSTANCE.createGGraph();
        root.setId(EMPTY_ROOT_ID);
        root.setType(DefaultTypes.GRAPH);
        return root;
    }

    public void setModelState(final GModelState testModelState) {
        modelState = testModelState;

    }
}
