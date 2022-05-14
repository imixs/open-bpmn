package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.w3c.dom.Node;

public class BPMNGateway extends BPMNFlowElement {

    public BPMNGateway(BPMNModel model, Node node, String type) {
        super(model, node, type);
    }

}
