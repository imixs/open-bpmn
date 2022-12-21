package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.core.BPMNElementEdge;
import org.w3c.dom.Element;

public class SequenceFlow extends BPMNElementEdge {
    protected BPMNProcess bpmnProcess = null;
    
    public SequenceFlow(BPMNModel model, Element node, String _type, BPMNProcess _bpmnProcess) {
        super(model, node, _type);
        this.bpmnProcess=_bpmnProcess;
    }


    /**
     * Returns the BPMN Process this element belongs to.
     * 
     * @return
     */
    public BPMNProcess getProcess() {
        return bpmnProcess;
    }

}
