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
import java.util.logging.Logger;

import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.actions.SelectAction;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.openbpmn.bpmn.elements.BPMNBaseElement;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.glsp.BPMNDiagramConfiguration;
import org.openbpmn.glsp.elements.CreateBPMNNodeOperationHandler;
import org.openbpmn.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * OperationHandler to add a Extension to a BPMN Event node.
 *
 * @author rsoika
 *
 */
public class BPMNCreateExtensionHandler extends CreateBPMNNodeOperationHandler {

    private static Logger logger = Logger.getLogger(BPMNCreateExtensionHandler.class.getName());

    @Inject
    protected Set<BPMNExtension> extensions;

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
        // accept all possible Extensions
        List<String> extensionKinds = new ArrayList<>();
        if (extensions != null) {
            for (BPMNExtension extension : extensions) {
                // validate if the extension is no Default Extension kind.
                if (!AbstractBPMNElementExtension.DEFAULT_EXTENSION_KIND.equals(extension.getKind())
                        && !extensionKinds.contains(extension.getKind())) {
                    extensionKinds.add(extension.getKind());
                }
            }
        }

        this.setHandledElementTypeIds(extensionKinds);
    }

    /**
     * We expect that the EventDefintion was dropped on a Event. See
     * {@link BPMNDiagramConfiguration} method getShapeTypeHints
     */
    @Override
    public void executeOperation(final CreateNodeOperation operation) {
        String elementID = null;
        String extensionID = operation.getElementTypeId();
        // now we add this extension directly into the BPMN element of the source
        // model
        Optional<GModelElement> container = this.getContainer(operation);

        if (container.isPresent()) {
            Optional<GModelElement> element = modelState.getIndex().get(container.get().getId());
            if (element.isPresent()) {
                elementID = element.get().getId();
                logger.info("===== > add extension for element id: " + elementID);

                BPMNProcess process = modelState.getBpmnModel().getContext();
                BPMNBaseElement bpmnElement = process.findBaseElementById(elementID);
                if (bpmnElement != null) {
                    // add the new definition
                    logger.warning("sett extension nor yest implemented - " + elementID + " !!!");
                } else {
                    logger.warning("Element " + elementID + " does not exist in current source model!");
                }

            }

        }

        modelState.reset();
        if (elementID != null) {
            // select event
            actionDispatcher.dispatchAfterNextUpdate(new SelectAction(), new SelectAction(List.of(elementID)));
        }
    }

}
