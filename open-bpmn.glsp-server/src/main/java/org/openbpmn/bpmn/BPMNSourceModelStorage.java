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
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.SaveModelAction;
import org.eclipse.glsp.server.features.core.model.RequestModelAction;
import org.eclipse.glsp.server.features.core.model.SourceModelStorage;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.utils.ClientOptionsUtil;
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
        // TODO Auto-generated method stub
        logger.info("loading BPMN Meta model....");
        Map<String, String> options = action.getOptions();
        // needsClientLayout/true
        boolean bNeedsClientLayout = Boolean.parseBoolean(options.get("needsClientLayout"));
        String uri = options.get("uri");
        String diagramType = options.get("diagramType");
        if (bNeedsClientLayout && uri != null && "bpmn-diagram".equals(diagramType)) {
            final File file = convertToFile(modelState);
            BPMNModel model = BPMNModelFactory.read(file);
            // we store the BPMN meta model into the modelState
            modelState.setBpmnModel(model);
        }
    }

    @Override
    public void saveSourceModel(final SaveModelAction action) {
        logger.info("================> We are in the saveSourceModel action!");
        // TODO Auto-generated method stub

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
