package org.openbpmn.glsp.operations;

import org.eclipse.glsp.server.actions.Action;

/**
 * Action defintion to auto align all elements to the grid
 * 
 */
public class BPMNAutoAlignAction extends Action {

    public static final String KIND = "autoAlign";

    public BPMNAutoAlignAction() {
        super(KIND);

    }

}