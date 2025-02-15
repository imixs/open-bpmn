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

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.MessageAction;
import org.eclipse.glsp.server.actions.SaveModelAction;
import org.eclipse.glsp.server.actions.SetDirtyStateAction;
import org.eclipse.glsp.server.features.core.model.RequestModelAction;
import org.eclipse.glsp.server.features.core.model.SourceModelStorage;
import org.eclipse.glsp.server.model.GModelState;
import org.eclipse.glsp.server.utils.MapUtil;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.extensions.BPMNModelExtension;
import org.openbpmn.glsp.BPMNDiagramConfiguration;
import org.openbpmn.glsp.utils.BPMNActionUtil;

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

    @Inject
    protected Set<BPMNModelExtension> extensions;

    /**
     * Loads a source model into the modelState.
     */
    @Override
    public void loadSourceModel(final RequestModelAction action) {

        Map<String, String> options = action.getOptions();
        final File file = getSourceFile(modelState);

        logger.debug("loadSourceModel from : " + file);
        String diagramType = options.get("diagramType");
        if (file != null && BPMNDiagramConfiguration.DIAGRAM_TYPE.equals(diagramType)) {
            BPMNModel model;
            try {
                model = BPMNModelFactory.read(file);

                // Apply BPMNModelExtensions
                if (extensions != null) {
                    // sort extensions by priority
                    List<BPMNModelExtension> sortedExtensions = new ArrayList<>();
                    sortedExtensions.addAll(extensions);
                    Comparator<BPMNModelExtension> byPriority = Comparator.comparing(BPMNModelExtension::getPriority);
                    Collections.sort(sortedExtensions, byPriority);
                    for (BPMNModelExtension extension : sortedExtensions) {
                        extension.onLoad(model, Paths.get(file.getPath()));
                    }
                }

                // we store the BPMN meta model into the modelState
                modelState.setBpmnModel(model);
                // if the model is dirty (because linked-file content has change) we send a
                // DirtyState action...
                if (model.isDirty()) {
                    logger.info("....external model content has changed.");

                    // here we fire a noop-custom command instead of a SetDirtyStateAction to avoid
                    // the undo situation
                    modelState.execute(new SetDirtyCommand());

                    // For Theia the line above is enough,
                    // but to bypass the current VS Code bug
                    // (https://github.com/eclipse-glsp/glsp/issues/1006),
                    // we have to dispatch an additional `SetDirtyStateAction` with reason
                    // "operation" here. eg.:
                    this.actionDispatcher.dispatch(
                            new SetDirtyStateAction(true, SetDirtyStateAction.Reason.OPERATION));
                }
                // dispatch all model notifications...
                while (!model.getNotifications().isEmpty()) {
                    MessageAction serverMessage = BPMNActionUtil
                            .convertModelNotification(model.getNotifications().remove(0));
                    // actionDispatcher.dispatchAfterNextUpdate(serverMessage);
                    actionDispatcher.dispatch(serverMessage);
                }

            } catch (BPMNModelException e) {
                logger.error("Failed to load model source: " + e.getMessage());
            }
        }
    }

    /**
     * Saves a model. The SaveModelAction is sent from the client to the server in
     * order to persist the current model state back to the source model. A new
     * fileUri can be defined to save the model to a new destination different from
     * its original source model.
     */
    @Override
    public void saveSourceModel(final SaveModelAction action) {
        final File file = getTargetFile(modelState, action);
        logger.debug("saveSourceModel to : " + file);
        BPMNModel model = modelState.getBpmnModel();

        // Apply BPMNModelExtensions
        if (extensions != null) {
            // sort extensions by priority
            List<BPMNModelExtension> sortedExtensions = new ArrayList<>();
            sortedExtensions.addAll(extensions);
            Comparator<BPMNModelExtension> byPriority = Comparator.comparing(BPMNModelExtension::getPriority);
            Collections.sort(sortedExtensions, byPriority);
            for (BPMNModelExtension extension : sortedExtensions) {
                extension.onSave(model, Paths.get(file.getPath()));
            }
        }

        model.save(file);

        // process all model notifications...
        while (!model.getNotifications().isEmpty()) {
            MessageAction serverMessage = BPMNActionUtil
                    .convertModelNotification(model.getNotifications().remove(0));
            actionDispatcher.dispatchAfterNextUpdate(serverMessage);
        }
    }

    private static final String FILE_PREFIX = "file://";

    /**
     * Resolves to a source File instance from a GModelState to load a model. Can be
     * called from the method <code>SourceModelStorage.loadSourceModel</code>.
     * 
     * @param modelState
     * @return a File Instance - or null
     * @throws NullPointerException
     *                              If the sourceUri resolves to <code>null</code>
     */
    public static File getSourceFile(final GModelState modelState) {
        String filePath = MapUtil.getValue(modelState.getClientOptions(), "sourceUri").orElse(null);
        // strip the file:// prefix
        filePath = filePath.replace(FILE_PREFIX, "");
        logger.debug("source uri=" + filePath);
        return new File(filePath);
    }

    /**
     * Resolves to a target File instance from a SaveModelAction and the GModelState
     * to save a model. Can be called from the method
     * <code>SourceModelStorage.loadSourceModel</code>.
     * 
     * @param modelState
     * @return a File Instance - or null
     * @throws NullPointerException
     *                              If the sourceUri resolves to <code>null</code>
     */
    public static File getTargetFile(final GModelState modelState, final SaveModelAction action) {
        // first take the origin sourceUri from the current modelState
        String filePath = MapUtil.getValue(modelState.getClientOptions(), "sourceUri").orElse(null);
        // test if we have a new optional fileUri....
        String newFileURI = action.getFileUri().orElse(null);
        if (newFileURI != null && !newFileURI.isEmpty()) {
            // we got a new URI which means we have a 'saveAs' situation!
            filePath = newFileURI;
        }
        logger.warn("target uri=" + filePath);
        // strip the optional file:// prefix
        filePath = filePath.replace(FILE_PREFIX, "");
        return new File(filePath);
    }

}
