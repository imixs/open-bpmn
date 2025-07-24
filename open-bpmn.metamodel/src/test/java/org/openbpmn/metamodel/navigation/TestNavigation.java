package org.openbpmn.metamodel.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.navigation.BPMNFlowIterator;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.metamodel.examples.TestCreateEdges;

/**
 * Test class to test simple navigation cases.
 * 
 */
public class TestNavigation {
    private static Logger logger = Logger.getLogger(TestCreateEdges.class.getName());

    /**
     * This test loads model 'refmodel-navigation-01.bpmn' and finds all events
     * followed by task-1.
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testFindAllEventsFromTask1() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-01.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProcess();

        // get Task1
        BPMNElementNode task1 = process.findElementNodeById("task_SBK01w");
        assertNotNull(task1);

        BPMNFlowIterator<Event> eventNavigator = new BPMNFlowIterator<Event>(task1,
                n -> n instanceof Event);
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
     * This test loads model 'refmodel-navigation-01.bpmn' and finds the target task
     * of Event-2
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testFindTargetFromEvent2() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-01.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProcess();

        // get Event2
        BPMNElementNode event2 = process.findElementNodeById("event_IyD2LA");
        assertNotNull(event2);

        BPMNFlowIterator<Event> taskNavigator = new BPMNFlowIterator<Event>(event2,
                n -> n instanceof Activity);
        assertNotNull(taskNavigator);

        // we expect 3 Event Nodes

        List<String> elementNames = new ArrayList();
        while (taskNavigator.hasNext()) {
            Activity task = (Activity) taskNavigator.next();
            elementNames.add(task.getName());
        }

        assertEquals(1, elementNames.size());
        assertTrue(elementNames.contains("Task-2"));

    }

    /**
     * This test loads model 'refmodel-navigation-01.bpmn' and finds the target task
     * of Event-1. Here we have a follow up event. So no result is expected here!
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testFindTargetFromEvent1() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-01.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProcess();

        // get Event2
        BPMNElementNode eventForwardAndSubmit = process.findElementNodeById("event_forwardAndSubmit");
        assertNotNull(eventForwardAndSubmit);

        // Find the target Task... (task-2)
        BPMNFlowIterator<Event> taskNavigator = new BPMNFlowIterator<Event>(eventForwardAndSubmit,
                n -> n instanceof Activity);
        assertNotNull(taskNavigator);

        // we expect 3 Event Nodes
        assertTrue(taskNavigator.hasNext());
        BPMNElementNode targetTask = taskNavigator.next();
        assertEquals("Task-2", targetTask.getName());
        // note more task exprected
        assertFalse(taskNavigator.hasNext());

    }

    /**
     * This test loads model 'refmodel-navigation-02.bpmn' and finds all events
     * followed by task-1. In this case whe have Gateway between the Source task and
     * the events.
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testFindAllEventsFromTask1ByGateway() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-02.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProcess();

        // get Task1
        BPMNElementNode task1 = process.findElementNodeById("task_SBK01w");
        assertNotNull(task1);

        BPMNFlowIterator<Event> eventNavigator = new BPMNFlowIterator<Event>(task1,
                n -> n instanceof Event);
        assertNotNull(eventNavigator);

        // we expect 3 Event Nodes

        List<String> elementNames = new ArrayList();
        while (eventNavigator.hasNext()) {
            Event event = (Event) eventNavigator.next();
            elementNames.add(event.getName());
        }

        assertEquals(3, elementNames.size());
        assertTrue(elementNames.contains("Event-1"));
        assertTrue(elementNames.contains("Event-2"));
        assertTrue(elementNames.contains("Event-3"));

    }

    /**
     * This test loads model 'refmodel-navigation-02.bpmn' and finds the gateway
     * followed by task-1.
     * 
     * @throws BPMNModelException
     * 
     */
    @Test
    public void testFindGatewayFromTask1() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-02.bpmn");

        assertEquals(1, model.getProcesses().size());
        BPMNProcess process = model.openDefaultProcess();

        // get Task1
        BPMNElementNode task1 = process.findElementNodeById("task_SBK01w");
        assertNotNull(task1);

        BPMNFlowIterator<Event> eventNavigator = new BPMNFlowIterator<Event>(task1,
                n -> n instanceof Gateway);
        assertNotNull(eventNavigator);

        // we expect 3 Event Nodes

        List<String> elementNames = new ArrayList();
        while (eventNavigator.hasNext()) {
            Gateway gateway = (Gateway) eventNavigator.next();
            elementNames.add(gateway.getName());
        }

        assertEquals(1, elementNames.size());
        assertTrue(elementNames.contains("Gateway-1"));

    }

}
