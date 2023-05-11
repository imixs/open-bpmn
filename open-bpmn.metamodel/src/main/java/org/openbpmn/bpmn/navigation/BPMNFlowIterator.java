package org.openbpmn.bpmn.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;

public class BPMNFlowIterator<T> implements Iterator<BPMNElementNode> {

    private Predicate<BPMNElementNode> filter;
    private int index;
    BPMNElementNode bpmnElementNode = null;
    List<SequenceFlow> outgoingFlows;
    Iterator<SequenceFlow> flowIterator;
    BPMNElementNode targetNode = null;

    public BPMNFlowIterator(BPMNElementNode bpmnElementNode, Predicate<BPMNElementNode> filter) {
        this.bpmnElementNode = bpmnElementNode;
        this.filter = filter;
        this.index = 0;

        // find outgoing flows
        outgoingFlows = new ArrayList<>(bpmnElementNode.getOutgoingSequenceFlows());

    }

    @Override
    public boolean hasNext() {
        while (index < outgoingFlows.size()) {

            SequenceFlow flow = outgoingFlows.get(index);

            targetNode = flow.getTargetElement();

            if (filter.test(targetNode)) {
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public BPMNElementNode next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        index++;
        return (BPMNElementNode) targetNode;
    }
}
