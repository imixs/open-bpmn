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
package org.openbpmn.glsp.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.server.actions.AbstractActionHandler;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.features.clipboard.RequestClipboardDataAction;
import org.eclipse.glsp.server.features.clipboard.SetClipboardDataAction;
import org.eclipse.glsp.server.types.EditorContext;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This ActionHandler reacts on RequestClipboardDataActions
 * <p>
 * This clipboard handler creates a clipboard data map with the selected BPMN
 * elements. This data map can be used in the PastOperation
 *
 * @author rsoika
 *
 */
public class BPMNClipboardDataActionHandler extends AbstractActionHandler<RequestClipboardDataAction> {

    private static Logger logger = LogManager.getLogger(BPMNClipboardDataActionHandler.class);

    @Inject
    protected BPMNGModelState modelState;

    /**
     * The RequestClipboardDataAction contains an editor context contains contextual
     * information about the editor state on the client.
     *
     * This context covers aspects, like selection or mouse position, but can be
     * extended with custom arguments.
     * An editor context is sent by the client as part of certain actions, where
     * additional context information may be
     * needed to process certain actions.
     * 
     */
    @Override
    protected List<Action> executeAction(final RequestClipboardDataAction actualAction) {
        EditorContext ctx = actualAction.getEditorContext();
        List<String> selectedElements = ctx.getSelectedElementIds();
        logger.debug("... copy " + selectedElements.size() + " elements...");

        // return a data action with the selected ids
        Map<String, String> data = new HashMap<String, String>();
        data.put("bpmn", String.join(",", selectedElements));
        SetClipboardDataAction clipboardDataAction = new SetClipboardDataAction(data);
        return this.listOf(clipboardDataAction);
    }

}