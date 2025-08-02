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

import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.server.actions.ActionDispatcher;
import org.eclipse.glsp.server.model.DefaultGModelState;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.glsp.operations.BPMNPropertiesUpdateAction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.inject.Inject;

/**
 * The BPMNGModelState extends the DefaultGModelState and provides the property
 * 'bpmnModel' which holds an instance of the BPMN MetaModel.
 * <p>
 * The BPMNModelState also holds a revision stack of all model updates to
 * support the undo/redo actions that are triggered by ....?
 * 
 * 
 * @author rsoika
 * @version 1.0
 */
public class BPMNGModelState extends DefaultGModelState {

    private static Logger logger = LogManager.getLogger(BPMNGModelState.class);

    private BPMNModel bpmnModel;
    private Stack<Document> undoStack = null;
    private Stack<Document> redoStack = null;
    private int MAX_UNDOSTACK_SIZE = 20;
    private boolean initialized = false;
    private String rootID = "undefined_root_id";

    @Inject
    protected BPMNGModelFactory bpmnGModelFactory;

    @Inject
    protected ActionDispatcher actionDispatcher;

    public BPMNGModelState() {
        resetRevisions();
    }

    public BPMNModel getBpmnModel() {
        return bpmnModel;
    }

    /**
     * This method set the BPMNModel.
     * The method generates a new root ID if no root ID yet exits
     * 
     * @param bpmnModel
     */
    public void setBpmnModel(final BPMNModel bpmnModel) {
        this.bpmnModel = bpmnModel;
        if (rootID == null || rootID.isEmpty() || "undefined_root_id".equals(rootID)) {
            // create a new unique id
            rootID = "root_" + BPMNModel.generateShortID();
        }
        this.setRoot(null);
    }

    /**
     * This method sets / updates the auto align option
     * 
     * <open-bpmn:auto-align>true</open-bpmn:auto-align>
     */
    public void setAutoAlign(boolean autoAlign) {
        Element autoAlignElement;
        try {
            if (bpmnModel.getDefaultProcessElement() != null) {
                autoAlignElement = bpmnModel.findExtensionElement(bpmnModel.getDefaultProcessElement(),
                        BPMNModelFactory.OPEN_BPMN_NAMESPACE, "auto-align");
                autoAlignElement.setTextContent("" + autoAlign);
            }
        } catch (BPMNInvalidReferenceException e) {

            e.printStackTrace();
        }
    }

    /**
     * This method returns the current auto align option
     * 
     * <open-bpmn:auto-align>true</open-bpmn:auto-align>
     */
    public boolean getAutoAlign() {
        Element autoAlignElement;
        try {
            if (bpmnModel.getDefaultProcessElement() != null) {
                autoAlignElement = bpmnModel.findExtensionElement(bpmnModel.getDefaultProcessElement(),
                        BPMNModelFactory.OPEN_BPMN_NAMESPACE, "auto-align");
                return Boolean.parseBoolean(autoAlignElement.getTextContent());
            }
        } catch (BPMNInvalidReferenceException e) {
            // extension is not defined
        }
        return false;
    }

    /**
     * Helper method to store the current model revision on the revisions stack.
     * 
     * We use the <code>cloneNode</code> method here as this method is much faster
     * than transforming the doc into a string using the
     * <code>TransformerFactory</code>.
     * 
     */
    public void storeRevision() {
        logger.debug("...store revision " + this.getRoot().getRevision());
        long l = System.currentTimeMillis();
        Document doc = bpmnModel.getDoc();
        Document docClone = (Document) doc.cloneNode(true);
        logger.debug("...clone took " + (System.currentTimeMillis() - l) + "ms");
        undoStack.push(docClone);
        // check max size of undoStack!
        while (undoStack.size() > MAX_UNDOSTACK_SIZE) {
            undoStack.remove(0);
        }
    }

    /**
     * This method resets the revisionStore. The method is called in cases a undo
     * operation does not make any more sense. For example this is the case if the
     * user updates the properties of an element. This will change the underlying
     * model but we do not store all these changes as revisions. So we need to reset
     * the Revision store in such a case.
     */
    public void resetRevisions() {
        undoStack = new Stack<Document>();
        redoStack = new Stack<Document>();
    }

    @Override
    public boolean canUndo() {
        return (undoStack.size() > 1);
    }

    @Override
    public boolean canRedo() {
        return (redoStack.size() > 0);
    }

    /**
     * This method fetches the latest revision from the undoStack and updates the
     * current bpmnModel instance.
     * The method also pushes the latest version on the redoStack to support redo
     * actions as well.
     */
    @Override
    public void undo() {
        // we need to take 2 revisions from the stack!
        logger.debug("start undo - current stack size=" + undoStack.size());
        Document doc = undoStack.pop(); // current version
        // push current version to redoStack
        redoStack.push(doc);
        // check max size of undoStack!
        while (redoStack.size() > MAX_UNDOSTACK_SIZE) {
            redoStack.remove(0);
        }
        doc = undoStack.pop(); // previous version
        try {
            bpmnModel = new BPMNModel(doc);
            // Update revision number
            this.getRoot().setRevision(getRoot().getRevision() - 2);
        } catch (BPMNModelException e) {
            logger.warn("unable to undo model changes: " + e.getMessage());
            e.printStackTrace();
        }
        reset();
        super.undo();
    }

    /**
     * This method fetches the latest version from the redoStack and updates the
     * current bpmnModel instance.
     */
    @Override
    public void redo() {
        // we need to take 2 revisions from the stack!
        logger.debug("start redo - current stack size=" + redoStack.size());
        Document doc = redoStack.pop();
        try {
            bpmnModel = new BPMNModel(doc);
            // this.getRoot().setRevision(getRoot().getRevision() - 1);
        } catch (BPMNModelException e) {
            logger.warn("unable to undo model changes: " + e.getMessage());
            e.printStackTrace();
        }
        reset();
        super.redo();
    }

    public String getRootID() {
        return rootID;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(final boolean initialized) {
        this.initialized = initialized;
    }

    /**
     * Reset the model state which will force a re-generation in the
     * BPMNGModelFactory.createGModel() method
     */
    public void reset() {
        this.initialized = false;
    }

    /**
     * This method can be used to rebuild the GModel which results in a new Model
     * Root ID.
     * This method is for example called by the BPMNAutoAlignActionHandler to
     * refresh the Client side
     * 
     */
    public void refreshGModelState() {
        this.setBpmnModel(bpmnModel);
        GGraph newGModel = bpmnGModelFactory.buildGGraph(bpmnModel);
        this.updateRoot(newGModel);
        this.execute(new SetDirtyCommand());
    }

    /**
     * This method sends a PropertyUpdateAction to the client to refresh the
     * property panel.
     * 
     * @param elementID
     */
    public void refreshSelection(String elementID) {
        logger.debug("Refresh selection: " + elementID + " root id:" + getRoot().getId());
        refreshGModelState();
        GModelElement gModelElement = getIndex().get(elementID).orElse(null);
        if (gModelElement == null) {
            // reset to root (Default Process)
            gModelElement = getRoot();

        }
        if (gModelElement != null) {
            // Refresh the Data, Schema and UISchema (this could be changed by an extension)
            String _data = gModelElement.getArgs().get("JSONFormsData").toString();
            // logger.info(" -> new JSON String=" + _data);
            String _schema = gModelElement.getArgs().get("JSONFormsSchema").toString();
            String _uiSchema = gModelElement.getArgs().get("JSONFormsUISchema").toString();
            actionDispatcher.dispatch(new BPMNPropertiesUpdateAction(gModelElement.getId(), _data, _schema,
                    _uiSchema));
        }
    }
}
