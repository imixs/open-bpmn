package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Node;

public class BPMNEvent extends BPMNFlowElement {


    public final static double DEFAULT_WIDTH = 36.0;
    public final static double DEFAULT_HEIGHT = 36.0;
    public static final double LABEL_OFFSET = 0;

    
    protected BPMNEvent(BPMNModel model,Node node, String type) throws BPMNModelException {
        super(model,node,type);
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
