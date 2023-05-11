package org.openbpmn.bpmn.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNValidationException;

public class BPMNFlowNavigator<T> implements Iterator<BPMNElementNode> {

    private Predicate<BPMNElementNode> filter;
    private int index;
    BPMNElementNode bpmnElementNode = null;
    List<SequenceFlow> outgoingFlows;
    Iterator<SequenceFlow> flowIterator;
    BPMNElementNode targetNode = null;

    public BPMNFlowNavigator(BPMNElementNode bpmnElementNode, Predicate<BPMNElementNode> filter)
            throws BPMNValidationException {
        this.bpmnElementNode = bpmnElementNode;
        this.filter = filter;
        this.index = 0;

        // find outgoing flows
        outgoingFlows = new ArrayList<>(bpmnElementNode.getOutgoingSequenceFlows());

        // if we have more than one outgoing flow, we thrown an exception
        if (outgoingFlows.size() > 1) {
            throw new BPMNValidationException(
                    "Element '" + bpmnElementNode.getId() + "' should not have more than one outgoing SequenceFlow!");
        }

    }

    public BPMNElementNode findTarget(Predicate<T> filter) {
        BPMNElementNode result = null;

        return result;
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
        // return (T) outgoingFlows.get(index++);
        index++;
        return targetNode;
    }
}
