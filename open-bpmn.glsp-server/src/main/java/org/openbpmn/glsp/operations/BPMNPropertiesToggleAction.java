package org.openbpmn.glsp.operations;

import org.eclipse.glsp.server.actions.Action;

/**
 * Action definition to toggle the property panel on the client side
 * 
 */
public class BPMNPropertiesToggleAction extends Action {

    public static final String KIND = "BPMNPropertiesToggleAction";

    public BPMNPropertiesToggleAction() {
        super(KIND);
    }

}