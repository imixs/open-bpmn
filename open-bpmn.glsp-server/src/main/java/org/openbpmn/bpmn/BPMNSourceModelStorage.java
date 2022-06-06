/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH,
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You can receive a copy of the GNU General Public
 * License at http://www.gnu.org/licenses/gpl.html
 *
 * Project:
 *     https://github.com/imixs/open-bpmn
 *
 * Contributors:
 *     Imixs Software Solutions GmbH - Project Management
 *     Ralph Soika - Software Developer
 ********************************************************************************/
package org.openbpmn.bpmn;

import static org.eclipse.glsp.server.types.GLSPServerException.getOrThrow;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.SaveModelAction;
import org.eclipse.glsp.server.features.core.model.RequestModelAction;
import org.eclipse.glsp.server.features.core.model.SourceModelStorage;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.utils.ClientOptionsUtil;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

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
    private static Logger logger = Logger.getLogger(BPMNSourceModelStorage.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    /**
     * Loads a source model into the modelState.
     */
    @Override
    public void loadSourceModel(final RequestModelAction action) {
        logger.fine("loading BPMN Meta model....");
        Map<String, String> options = action.getOptions();
        boolean bNeedsClientLayout = Boolean.parseBoolean(options.get("needsClientLayout"));
        String uri = options.get("uri");
        String diagramType = options.get("diagramType");
        if (bNeedsClientLayout && uri != null && "bpmn-diagram".equals(diagramType)) {
            final File file = convertToFile(modelState);
            BPMNModel model;
            try {
                model = BPMNModelFactory.read(file);
                // we store the BPMN meta model into the modelState
                modelState.setBpmnModel(model);
            } catch (BPMNModelException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    @Override
    public void saveSourceModel(final SaveModelAction action) {

        Map<String, String> options = modelState.getClientOptions();
        String filePath = options.get("uri");
        Optional<String> uriOpt = action.getFileUri();
        if (uriOpt.isPresent() && !uriOpt.isEmpty()) {
            // we got a new URI which means we have a 'saveAs' situaiton!
            filePath = uriOpt.get();
        }
        BPMNModel model = modelState.getBpmnModel();
        try {
            java.net.URI targetURI = new URI(filePath);
            model.save(targetURI);
        } catch (URISyntaxException e) {
            logger.severe("Invalid Target URI: " + e.getMessage());
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
