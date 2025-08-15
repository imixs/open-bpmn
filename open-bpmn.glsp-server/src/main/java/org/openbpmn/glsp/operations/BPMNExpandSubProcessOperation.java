package org.openbpmn.glsp.operations;

import org.eclipse.glsp.server.operations.Operation;

/**
 * Action definition to toggle the property panel on the client side
 * 
 */
public class BPMNExpandSubProcessOperation extends Operation {

    public static final String KIND = "expandSubProcess";
    private String processId;

    public BPMNExpandSubProcessOperation() {
        super(KIND);
    }

    public BPMNExpandSubProcessOperation(final String processId) {
        super(KIND);
        this.processId = processId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

}