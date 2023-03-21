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
package org.openbpmn.extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.glsp.BPMNDiagramConfiguration;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * OperationHandler to add a Extension to a BPMN Element node.
 * <p>
 * During the call of method executeOperation, the corresponding extension is
 * added to the source model element. Also the extension namespace is added to
 * the model. Finally we reset the modelState.
 * <p>
 * The extension IDs are loaded lazy in the method getHandledElementTypeIds.
 * This is because the extension are not yet registered when this
 * OperationHandler is created.
 *
 * @author rsoika
 */
public class BPMNCreateExtensionHandler extends CreateBPMNNodeOperationHandler {

    private static Logger logger = LogManager.getLogger(BPMNCreateExtensionHandler.class);

    @Inject
    protected Set<BPMNExtension> extensions;

    private List<String> extensionIds = null;

    @Inject
    protected BPMNGModelState modelState;

    @Inject
    protected ActionDispatcher actionDispatcher;

    /**
     * Default constructor
     * <p>
     * We use this constructor to overwrite the handledElementTypeIds with the
     * registered Extensions
     */
    public BPMNCreateExtensionHandler() {
        super();

    }

    /**
     * Lazy loading of registered extension ids.
     */
    @Override
    public List<String> getHandledElementTypeIds() {

        if (extensionIds == null && extensions != null) {
            extensionIds = new ArrayList<>();
            for (BPMNExtension extension : extensions) {
                String extensionID = "extension:" + extension.getNamespace();
                // validate if the extension is no Default Extension Namspace.
                if (!BPMNNS.BPMN2.name().equals(extension.getNamespace()) && !extensionIds.contains(extensionID)) {
                    extensionIds.add(extensionID);
                }
            }
        }
        return extensionIds;
    }

    /**
     * We expect that the EventDefintion was dropped on a Event. See
     * {@link BPMNDiagramConfiguration} method getShapeTypeHints
     */
    @Override
    public void executeOperation(final CreateNodeOperation operation) {
        String elementID = null;
        // now we add this extension directly into the BPMN element of the source
        // model
        Optional<GModelElement> container = this.getContainer(operation);
        logger.debug(" Extension Create Operation - elementTypeID=" + operation.getElementTypeId());
        if (container.isPresent()) {
            Optional<GModelElement> element = modelState.getIndex().get(container.get().getId());
            if (element.isPresent()) {
                elementID = element.get().getId();
                logger.debug("===== > add extension for element id: " + elementID);
                // open bpmn event element
                BPMNElementNode bpmnElement = modelState.getBpmnModel().findElementNodeById(elementID);
                if (bpmnElement != null) {
                    // add the new extension
                    if (extensions != null) {
                        for (BPMNExtension extension : extensions) {
                            if (extension.handlesElementTypeId(element.get().getType())) {
                                // verify that the namespace is added to the model
                                modelState.getBpmnModel().addNamespace(extension.getNamespace(),
                                        extension.getNamespaceURI());
                                // add the extension to the element
                                extension.addExtension(bpmnElement);
                            }
                        }
                    }
                }
            }

        }

        modelState.reset();
        if (elementID != null) {
            // select event
            actionDispatcher.dispatchAfterNextUpdate(new SelectAction(List.of(elementID)));
        }
    }

}
