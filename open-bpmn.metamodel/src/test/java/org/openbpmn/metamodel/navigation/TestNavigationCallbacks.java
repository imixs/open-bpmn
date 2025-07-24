package org.openbpmn.metamodel.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.navigation.BPMNFlowIterator;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.metamodel.examples.TestCreateEdges;

/**
 * Test class to test navigation cases with complex callback methods.
 * 
 */
public class TestNavigationCallbacks {
    private static Logger logger = Logger.getLogger(TestCreateEdges.class.getName());

    /**
     * This test loads model 'refmodel-navigation-01.bpmn' and finds all events with
     * the word Submit in its name
     * 
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testFindAllEventsFromTask1WithComplexRule() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-01.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProcess();

        // get Task1
        BPMNElementNode task1 = process.findElementNodeById("task_SBK01w");
        assertNotNull(task1);

        BPMNFlowIterator<Event> eventNavigator = new BPMNFlowIterator<Event>(task1,
                n -> isSubmitEvent(n));
        assertNotNull(eventNavigator);

        // we expect 3 Event Nodes

        List<String> elementNames = new ArrayList();
        while (eventNavigator.hasNext()) {
            Event event = (Event) eventNavigator.next();
            elementNames.add(event.getName());
        }

        assertEquals(2, elementNames.size());
        assertTrue(elementNames.contains("Submit"));
        assertTrue(elementNames.contains("Forward and submit"));

    }

    /**
     * This callback method returns true if the given element is a an Event and has
     * the word 'submit' in its name attribute.
     * 
     * @param element
     * @return
     */
    private boolean isSubmitEvent(BPMNElementNode element) {
        if ((element instanceof Event)
                && element.getName().toLowerCase().contains("submit")) {
            return true;
        }
        // not match
        return false;
    }

    /**
     * This test loads model 'refmodel-navigation-03.bpmn' and finds all events by
     * evaluating a the conditions by a callback method
     * 
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testFindAllEventsFromTask1WithCondition() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-03.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProcess();

        // get Task1
        BPMNElementNode task1 = process.findElementNodeById("task_SBK01w");
        assertNotNull(task1);

        // Build a navigator with a callback method to eval the condition
        BPMNFlowIterator<BPMNElementNode> eventNavigator = new BPMNFlowIterator<>(
                task1,
                node -> node instanceof Event || node instanceof Event,
                condition -> evalCondition(condition));

        assertNotNull(eventNavigator);

        // we expect only one Event Node!
        List<String> elementNames = new ArrayList();
        while (eventNavigator.hasNext()) {
            Event event = (Event) eventNavigator.next();
            elementNames.add(event.getName());
        }

        assertEquals(1, elementNames.size());
        assertTrue(elementNames.contains("Event-2"));

    }

    /**
     * This callback method evaluates conditions.
     * It only returns true if the condition is 'c=d'
     * 
     * @param element
     * @return
     */
    private boolean evalCondition(String condition) {
        // eval dummy condition only
        return ("c=d".equals(condition));
    }

}
