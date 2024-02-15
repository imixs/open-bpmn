package org.openbpmn.glsp.operations;

import java.util.List;

import org.eclipse.glsp.server.operations.Operation;

/**
 * Action defintion to auto align all elements to the grid
 * 
 */
public class BPMNAutoAlignOperation extends Operation {

    public static final String KIND = "autoAlign";

    private List<String> elementIds;

    public BPMNAutoAlignOperation() {
        super(KIND);
    }

    public BPMNAutoAlignOperation(final List<String> elementIds) {
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