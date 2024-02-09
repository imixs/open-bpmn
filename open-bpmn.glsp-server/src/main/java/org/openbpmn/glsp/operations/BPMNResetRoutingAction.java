package org.openbpmn.glsp.operations;

import java.util.List;

import org.eclipse.glsp.server.actions.Action;

/**
 * Action defintion to auto align all elements to the grid
 * 
 */
public class BPMNResetRoutingAction extends Action {

    public static final String KIND = "resetRoutingPoints";
    private List<String> elementIds;

    public BPMNResetRoutingAction() {
        super(KIND);
    }

    public BPMNResetRoutingAction(final List<String> elementIds) {
        super(KIND);
        this.elementIds = elementIds;
    }

    public List<String> getElementIds() {
        return elementIds;
    }

    public void setElementIds(final List<String> elementIds) {
        this.elementIds = elementIds;
    }

}