package org.openbpmn.bpmn.navigation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNValidationException;

/**
 * The BPMNEndElementIterator returns Elements that are immediately followed by
 * a end event. This can be either an Intermediate Catch/Throw Events or an
 * Activity (Task).
 * <p>
 * With the filter argument (Functional Interface Predicate) an argument can be
 * provided to return only specific elements.
 * 
 */
public class BPMNEndElementIterator<T> implements Iterator<BPMNElementNode> {

    protected static Logger logger = Logger.getLogger(BPMNElementNode.class.getName());
    Set<Event> _endEventNodes;

    private List<BPMNElementNode> resultElementList;
    private Iterator<BPMNElementNode> allEndElementsIterator;

    /**
     * Creates an Iterator with a given filter criteria.
     * The method collects all BPMNElements following the given start element and
     * matching the given filter
     * 
     * @param bpmnElementNode
     * @param filter
     * @throws BPMNValidationException
     */
    public BPMNEndElementIterator(BPMNProcess process, Predicate<BPMNElementNode> filter) {
        resultElementList = new ArrayList<>();

        // First find all Start Events in the model
        _endEventNodes = new HashSet<>();
        Set<Event> allEventNodes = process.getEvents();
        for (Event _event : allEventNodes) {
            if (BPMNTypes.END_EVENT.equals(_event.getType())) {
                _endEventNodes.add(_event);
            }
        }

        // next resolve all immediate predecessors.
        for (BPMNElementNode element : _endEventNodes) {
            Set<SequenceFlow> incomingFlows = element.getIngoingSequenceFlows();
            for (SequenceFlow _flow : incomingFlows) {
                BPMNElementNode sourceNode = _flow.getSourceElement();
                if (filter.test(sourceNode)) {
                    resultElementList.add(sourceNode);
                }
            }
        }
        // create a local iterator instance
        allEndElementsIterator = resultElementList.iterator();
    }

    @Override
    public boolean hasNext() {
        return allEndElementsIterator.hasNext();
    }

    @Override
    public BPMNElementNode next() {
        return allEndElementsIterator.next();
    }

}
