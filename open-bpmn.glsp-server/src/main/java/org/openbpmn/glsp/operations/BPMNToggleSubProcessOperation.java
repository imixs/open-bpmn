package org.openbpmn.glsp.operations;

import org.eclipse.glsp.server.operations.Operation;

/**
 * Action definition to toggle the property panel on the client side
 * Possible Modes are 'collapse' and 'expand'
 * 
 */
public class BPMNToggleSubProcessOperation extends Operation {

    public static final String KIND = "toggleSubProcess";
    private String processId;
    private String mode;

    public BPMNToggleSubProcessOperation() {
        super(KIND);
    }

    public BPMNToggleSubProcessOperation(final String processId) {
        super(KIND);
        this.processId = processId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}