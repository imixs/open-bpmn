package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.w3c.dom.Element;

public class Association extends BPMNElementEdge {
    public Association(BPMNModel model, Element node, String _type, Process _bpmnProcess) {
        super(model, node, _type, _bpmnProcess);
    }
}
