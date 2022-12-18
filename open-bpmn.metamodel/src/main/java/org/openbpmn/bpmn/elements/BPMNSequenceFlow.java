package org.openbpmn.bpmn.elements;

import java.util.logging.Logger;

import org.openbpmn.bpmn.BPMNModel;
import org.w3c.dom.Element;

public class BPMNSequenceFlow extends BPMNElementEdge {

    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(BPMNSequenceFlow.class.getName());

    public BPMNSequenceFlow(BPMNModel model, Element node, String _type, BPMNProcess _bpmnProcess) {
        super(model, node, _type, _bpmnProcess);
    }

}
