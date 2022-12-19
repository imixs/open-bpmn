package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.w3c.dom.Element;

public class SequenceFlow extends BPMNElementEdge {

    public SequenceFlow(BPMNModel model, Element node, String _type, Process _bpmnProcess) {
        super(model, node, _type, _bpmnProcess);
    }

}
