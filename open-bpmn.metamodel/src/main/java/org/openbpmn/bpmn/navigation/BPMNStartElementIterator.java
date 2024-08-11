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
import org.openbpmn.bpmn.elements.core.BPMNElement;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNValidationException;

/**
 * The BPMNStartElementIterator returns Elements that immediately follow a start
 * event. This can be either an Intermediate Catch/Throw Events or an Activity
 * (Task).
 * 
 * <p>
 * With the filter argument (Functional Interface Predicate) an argument can be
 * provided to return only specific elements.
 * 
 * 
 */
public class BPMNStartElementIterator<T> implements Iterator<BPMNElementNode> {

    protected static Logger logger = Logger.getLogger(BPMNElementNode.class.getName());
    Set<Event> _startEventNodes;

    private List<BPMNElementNode> resultElementList;
    private Iterator<BPMNElementNode> allStartElementsIterator;

    /**
     * Creates an Iterator with a given filter criteria.
     * The method collects all BPMNElements following the given start element and
     * matching the given filter
     * 
     * @param bpmnElementNode
     * @param filter
     * @throws BPMNValidationException
     */
    public BPMNStartElementIterator(BPMNProcess process, Predicate<BPMNElementNode> filter) {
        resultElementList = new ArrayList<>();

        // First find all Start Events in the model
        _startEventNodes = new HashSet<>();
        Set<Event> allEventNodes = process.getEvents();
        for (Event _event : allEventNodes) {
            if (BPMNTypes.START_EVENT.equals(_event.getType())) {
                _startEventNodes.add(_event);
            }
        }

        // next resolve all follow up tasks and events based on the given Predicate
        for (BPMNElement element : _startEventNodes) {
            BPMNFlowIterator<BPMNElement> followUpElements = new BPMNFlowIterator<BPMNElement>(
                    (BPMNElementNode) element,
                    filter);
            // add the followup elements to the result list
            while (followUpElements.hasNext()) {
                resultElementList.add(followUpElements.next());
            }
        }
        // create a local iterator instance
        allStartElementsIterator = resultElementList.iterator();
    }

    @Override
    public boolean hasNext() {
        return allStartElementsIterator.hasNext();
    }

    @Override
    public BPMNElementNode next() {
        return allStartElementsIterator.next();
    }

}
