package org.openbpmn.glsp.operations;

import org.eclipse.glsp.server.actions.Action;

/**
 * Action defintion to toggle the property panel on the client side
 * 
 */
public class BPMNPropertyPanelToggleAction extends Action {

    public static final String KIND = "propertyPanelToggle";

    public BPMNPropertyPanelToggleAction() {
        super(KIND);
    }

}