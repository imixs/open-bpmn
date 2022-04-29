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
package org.openbpmn.bpmn;

import static org.eclipse.glsp.server.types.GLSPServerException.getOrThrow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.graph.GraphFactory;
import org.eclipse.glsp.server.features.core.model.ModelSourceLoader;
import org.eclipse.glsp.server.features.core.model.RequestModelAction;
import org.eclipse.glsp.server.gson.GraphGsonConfigurationFactory;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.types.GLSPServerException;
import org.eclipse.glsp.server.utils.ClientOptionsUtil;
import org.openbpmn.bpmn.elements.Process;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.glsp.BPMNDiagramModule;

import com.google.gson.Gson;
import com.google.inject.Inject;

public class BPMNModelSourceLoader implements ModelSourceLoader {
    private static Logger logger = Logger.getLogger(BPMNDiagramModule.class.getName());

    private static String EMPTY_ROOT_ID = "glsp-graph";

    @Inject
    private GraphGsonConfigurationFactory gsonConfiguratior;

    @Inject
    protected GModelState modelState;

    /**
     * Loads a source model into the modelState.
     */
    @Override
    public void loadSourceModel(final RequestModelAction action) {
        // TODO Auto-generated method stub
        logger.info("============= we are in loadSourceModel....");
        logger.info(" action kine=" + action.getKind());
        Map<String, String> options = action.getOptions();

        // needsClientLayout/true
        boolean bNeedsClientLayout = Boolean.parseBoolean(options.get("needsClientLayout"));
        String uri = options.get("uri");
        String sourceUri = options.get("sourceUri");
        String diagramType = options.get("diagramType");
        if (bNeedsClientLayout && uri != null && "bpmn-diagram".equals(diagramType)) {

            logger.info("===========================");

            final File file = convertToFile(modelState);

            // repalce .minimal with .bpmn
            // sourceUri = sourceUri.replace(".minimal", ".bpmn");
            // loading model
            // BPMNModel model = BPMNModelFactory.read(sourceUri);
            BPMNModel model;

            model = BPMNModelFactory.read(file);
            if (model != null) {
                logger.info("loaded model: " + sourceUri);
                List<Process> processList = model.getProcesList();
                if (processList != null && processList.size() > 0) {
                    logger.info("...found process...");
                    Process process = processList.get(0);

                    GGraph newGModel = BPMNGModelUtil.createGModelFromProcess(process, modelState);
                    modelState.setRoot(newGModel);
                    modelState.getRoot().setRevision(-1);

                }
            } else {
                logger.warning("unable to read bpmn file!");
            }

        }

        // fallback to default json model

        // old_loadSourceModel(action);

    }

    public void old_loadSourceModel(final RequestModelAction action) {
        final File file = convertToFile(modelState);
        old_loadSourceModel(file, modelState).ifPresent(root -> {
            modelState.setRoot(root);
            modelState.getRoot().setRevision(-1);
        });
    }

    /**
     * This helper method opens the corresponding java.io.File form the given
     * clientOptions
     *
     * @param modelState
     * @return
     */
    protected File convertToFile(final GModelState modelState) {
        return getOrThrow(ClientOptionsUtil.getSourceUriAsFile(modelState.getClientOptions()),
                "Invalid file URI:" + ClientOptionsUtil.getSourceUri(modelState.getClientOptions()));
    }

    protected Optional<GModelRoot> old_loadSourceModel(final File file, final GModelState modelState) {
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            Gson gson = gsonConfiguratior.configureGson().create();
            GGraph root = gson.fromJson(reader, GGraph.class);
            if (root == null) {
                boolean isEmpty = file.length() == 0;
                if (isEmpty) {
                    return Optional.of(old_createNewEmptyRoot(modelState));
                }
                throw new IOException("Could not deserialize file contents of: " + file.toURI().toString());
            }
            return Optional.ofNullable(root);
        } catch (IOException e) {

            throw new GLSPServerException("Could not load model from file: " + file.toURI().toString(), e);
        }
    }

    protected GModelRoot old_createNewEmptyRoot(final GModelState modelState) {
        GModelRoot root = GraphFactory.eINSTANCE.createGGraph();
        root.setId(EMPTY_ROOT_ID);
        root.setType(DefaultTypes.GRAPH);
        return root;
    }

}
