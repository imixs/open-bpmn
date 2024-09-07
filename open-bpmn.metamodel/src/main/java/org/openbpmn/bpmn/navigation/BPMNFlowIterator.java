package org.openbpmn.bpmn.navigation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.SequenceFlow;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNValidationException;

/**
 * The BPMNFlowIterator can be used to iterate through a BPMN model. Based on
 * a Source BPMNElement the class routes to the next Flow Element fulfilling
 * a given criteria. For example you can find all Event Nodes followed by an
 * Activity Node. Or you can navigate to the next Activity from an Event.
 * <p>
 * The BPMNFlowIterator expects a filter argument (Functional Interface
 * Predicate) with one BPMNElementNode argument that return a boolean value.
 * <p>
 * In case the filter does not match the current element, the Iterator will
 * recursive follow the sequence flow until the next matching element was found.
 * If the start element is null the Iterator will become empty.
 * 
 */
public class BPMNFlowIterator<T> implements Iterator<BPMNElementNode> {

    protected static Logger logger = Logger.getLogger(BPMNElementNode.class.getName());

    private Predicate<BPMNElementNode> filter = null;
    private Predicate<String> conditionEvaluator = null;
    private int index;
    BPMNElementNode bpmnElementNode = null;
    List<SequenceFlow> outgoingFlows;
    private List<BPMNElementNode> targetNodes;

    /**
     * Creates a new BPMNFlowIterator with a given filter criteria.
     * The method collects all BPMNElements following the given start element and
     * matching the given filter. If the start element is null the Iterator is
     * empty.
     * 
     * @param bpmnElementNode
     * @param filter
     * @throws BPMNValidationException
     */
    public BPMNFlowIterator(BPMNElementNode bpmnElementNode, Predicate<BPMNElementNode> filter) {
        this.filter = filter;
        this.targetNodes = new ArrayList<>();
        this.index = 0;
        // find all elements
        if (bpmnElementNode != null) {
            findValidNodes(bpmnElementNode);
        }
    }

    /**
     * Creates a new BPMNFlowIterator with a given filter criteria.
     * The method collects all BPMNElements following the given start element and
     * matching the given filter. If the start element is null the Iterator is
     * empty.
     * 
     * @param bpmnElementNode
     * @param filter
     * @param conditionEvaluator optional conditional evaluator
     * @throws BPMNValidationException
     */
    public BPMNFlowIterator(BPMNElementNode bpmnElementNode, Predicate<BPMNElementNode> filter,
            Predicate<String> conditionEvaluator)
            throws BPMNValidationException {
        this.filter = filter;
        this.conditionEvaluator = conditionEvaluator;
        this.targetNodes = new ArrayList<>();
        this.index = 0;
        // find all elements
        if (bpmnElementNode != null) {
            findValidNodes(bpmnElementNode);
        }
    }

    @Override
    public boolean hasNext() {
        return index < targetNodes.size();
    }

    @Override
    public BPMNElementNode next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        return targetNodes.get(index++);
    }

    /**
     * Iterates tough all outgoing sequence flows and tests if the target element
     * matches the filter criteria.
     * <p>
     * If an element does not match the filter criteria the method will recursive
     * call itself and follow the further sequence flow.
     * 
     * @param currentNode
     */
    private void findValidNodes(BPMNElementNode currentNode) {
        if (currentNode == null) {
            return;
        }
        Set<SequenceFlow> flowSet = currentNode.getOutgoingSequenceFlows();
        // Check if the sequence flow has a condition and evaluate it if a
        // conditionEvaluator is defined
        if (currentNode instanceof Gateway && conditionEvaluator != null) {
            flowSet = filterConditionalFlows((Gateway) currentNode, flowSet);
        }

        for (SequenceFlow flow : flowSet) {
            BPMNElementNode node = getTargetNode(flow);
            if (node != null) {
                if (filter.test(node)) {
                    targetNodes.add(node);
                } else {
                    // if the node does not match the filter go ahead by a recursive call....
                    findValidNodes(node);
                }
            }
        }
    }

    /**
     * The method getTargetNode returns the target element of a SequenceFlow. This
     * method can be overwritten by sub classes to implement more complex routings.
     * <p>
     * For example a class can test if the target is a
     * <code>bpmn:intermediateThrowEvent</code> with a
     * <code>bpmn2:linkEventDefinition</code> to navigate automatically to the
     * corresponding <code>bpmn:intermediateCatchEvent</code>
     * 
     * @param flow
     * @return
     */
    public BPMNElementNode getTargetNode(SequenceFlow flow) {
        return flow.getTargetElement();
    }

    /**
     * This method filters all conditional flows outgoing from a gateway that did
     * not eval to true by the given conditionEvaluator. The result is a Set with
     * exactly one flow !
     * 
     * 
     * @return
     */
    private Set<SequenceFlow> filterConditionalFlows(Gateway gateway, Set<SequenceFlow> flowSet) {
        LinkedHashSet<SequenceFlow> result = new LinkedHashSet<SequenceFlow>();

        List<SequenceFlow> sortedList = new ArrayList<>(flowSet);
        // Sort the list using a comparator that places empty conditions at the end
        sortedList.sort(Comparator.comparing(flow -> {
            String condition = flow.getConditionExpression();
            return (condition == null || condition.trim().isEmpty()) ? 1 : 0;
        }));

        // now we eval each condition. As soon as we have a match or the condition is
        // empty we stop.
        for (SequenceFlow flow : sortedList) {
            String condition = flow.getConditionExpression();
            if (condition != null && (conditionEvaluator.test(condition) == true)) {
                // the condition matches - return this as the result;
                result.add(flow);
                return result;

            }
            // In case the condition is empty this is the default flow...
            if (condition == null || condition.trim().isEmpty()) {
                // default condition
                result.add(flow);
                return result;
            }
        }
        // no match - should not happen
        logger.warning("Gateway " + gateway.getName() + " (" + gateway.getId()
                + ") has conditional flows but non of the outgoing sequence flows is matching the condition!");
        return result;

    }

}
