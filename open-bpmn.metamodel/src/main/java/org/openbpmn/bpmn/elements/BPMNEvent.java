package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNEventType;
import org.w3c.dom.Node;

public class BPMNEvent extends BPMNFlowElement {

 
    public BPMNEvent(BPMNEventType type, Node node) {
        super(type.name, node);
    }

    public BPMNEvent(String type, Node node) {
        super(type, node);
    }

}
