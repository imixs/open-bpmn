package org.openbpmn.glsp.operations;

import java.util.List;

import org.eclipse.glsp.server.actions.Action;

/**
 * Action defintion to auto align all elements to the grid
 * 
 */
public class BPMNAutoAlignAction extends Action {

    public static final String KIND = "autoAlign";

    private List<String> elementIds;

    public BPMNAutoAlignAction() {
        super(KIND);
    }

    public BPMNAutoAlignAction(final List<String> elementIds) {
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