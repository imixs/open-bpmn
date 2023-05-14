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
package org.openbpmn.glsp.model;

import static org.eclipse.glsp.server.types.GLSPServerException.getOrThrow;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SaveModelAction;
import org.eclipse.glsp.server.actions.SetDirtyStateAction;
import org.eclipse.glsp.server.features.core.model.RequestModelAction;
import org.eclipse.glsp.server.features.core.model.SourceModelStorage;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.utils.ClientOptionsUtil;
import org.eclipse.glsp.server.utils.MapUtil;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.glsp.BPMNDiagramConfiguration;

import com.google.inject.Inject;

/**
 * The BPMNSourceModelStorage handles loading and the persistence of BPMN source
 * models.
 * <p>
 * The SourceModelStorage is called by the GLSP Server when the client requests
 * to open a new BPMN file.
 *
 * @author rsoika
 * @version 1.0
 */
public class BPMNSourceModelStorage implements SourceModelStorage {
    private static Logger logger = LogManager.getLogger(BPMNSourceModelStorage.class);

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    /**
     * Loads a source model into the modelState.
     */
    @Override
    public void loadSourceModel(final RequestModelAction action) {
        logger.debug("loading BPMN Meta model....");
        Map<String, String> options = action.getOptions();
        boolean bNeedsClientLayout = Boolean.parseBoolean(options.get("needsClientLayout"));
        // resolve file location....
        String uri = MapUtil.getValue(options, "sourceUri").orElse(null);
        if (uri == null || uri.isEmpty()) {
            // fallback
            uri = options.get("uri");
        }

        String diagramType = options.get("diagramType");
        if (bNeedsClientLayout && uri != null && BPMNDiagramConfiguration.DIAGRAM_TYPE.equals(diagramType)) {
            final File file = convertToFile(modelState);
            BPMNModel model;
            try {
                model = BPMNModelFactory.read(file);
                // we store the BPMN meta model into the modelState
                modelState.setBpmnModel(model);
                // if the model is dirty (because linked-file content has change) we send a
                // DirtyState action...
                if (model.isDirty()) {
                    logger.info("....external model content has changed.");
                    SetDirtyStateAction dirtyAction = new SetDirtyStateAction();
                    dirtyAction.setDirty(true);
                    dirtyAction.setReason("Updated linked File Content");
                    actionDispatcher.dispatchAfterNextUpdate(dirtyAction);
                }
            } catch (BPMNModelException e) {
                logger.error("Failed to load model source: " + e.getMessage());
            }

        }
    }

    @Override
    public void saveSourceModel(final SaveModelAction action) {
        Map<String, String> options = modelState.getClientOptions();
        // resolve file location....
        String uri = MapUtil.getValue(options, "sourceUri").orElse(null);
        if (uri == null || uri.isEmpty()) {
            // fallback
            uri = options.get("uri");
        }
        Optional<String> uriOpt = action.getFileUri();
        if (uriOpt.isPresent() && !uriOpt.isEmpty()) {
            // we got a new URI which means we have a 'saveAs' situaiton!
            uri = uriOpt.get();
        }
        BPMNModel model = modelState.getBpmnModel();
        try {
            // check protocol
            if (!uri.contains("://")) {
                uri = "file://" + uri;
            }
            java.net.URI targetURI = new URI(uri);
            model.save(targetURI);
        } catch (URISyntaxException e) {
            logger.error("Invalid Target URI: " + e.getMessage());
        }

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

}
