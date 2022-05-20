package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.w3c.dom.Node;

public class BPMNEvent extends BPMNFlowElement {


    public final static double DEFAULT_WIDTH = 40.0;
    public final static double DEFAULT_HEIGHT = 40.0;

    
    protected BPMNEvent(BPMNModel model,Node node, String type) {
        super(model,node,type);
    }
    
   

}
