package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;

public class BPMNParticipant extends BPMNFlowElement {

    public final static double DEFAULT_WIDTH = 500.0;
    public final static double DEFAULT_HEIGHT = 150.0;
    
    private String processRef=null;

    public BPMNParticipant(BPMNModel model, Element node, String type) throws BPMNModelException {
        super(model, node, type);
    }
    
    public String getProcessRef() {
        return processRef;
    }


    public void setProcessRef(String processRef) {
        this.processRef = processRef;
        this.getElementNode().setAttribute("processRef", processRef);
    }


    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public double getDefaultHeigth() {
        return DEFAULT_HEIGHT;
    }
}
