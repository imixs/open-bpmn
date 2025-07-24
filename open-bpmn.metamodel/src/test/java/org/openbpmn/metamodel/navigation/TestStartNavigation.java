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
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.navigation.BPMNStartElementIterator;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.metamodel.examples.TestCreateEdges;

/**
 * Test class to test the StartNavigator.
 * 
 */
public class TestStartNavigation {
    private static Logger logger = Logger.getLogger(TestCreateEdges.class.getName());

    /**
     * This test loads model 'refmodel-navigation-01.bpmn' and finds the start
     * task-1.
     * 
     * @throws BPMNModelException
     * 
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testFindStartTask1() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-01.bpmn");
        BPMNProcess process = model.openDefaultProcess();

        // get start Task1
        BPMNStartElementIterator startElements = new BPMNStartElementIterator<>(process, n -> n instanceof Activity);
        assertNotNull(startElements);
        assertTrue(startElements.hasNext());

        BPMNElementNode task1 = startElements.next();
        assertNotNull(task1);

        assertEquals("Task-1", task1.getName());
        // no more elements exprected
        assertFalse(startElements.hasNext());
    }

    /**
     * This test loads model 'refmodel-navigation-01.bpmn' and finds the start
     * task-1.
     * 
     * @throws BPMNModelException
     * 
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testFindStartTask1WithEvents() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-01.bpmn");
        BPMNProcess process = model.openDefaultProcess();

        // get start Task1
        BPMNStartElementIterator startElements = new BPMNStartElementIterator<>(process,
                node -> (node instanceof Activity || node instanceof Event));
        assertNotNull(startElements);
        assertTrue(startElements.hasNext());

        BPMNElementNode task1 = startElements.next();
        assertNotNull(task1);

        assertEquals("Task-1", task1.getName());
        // no more elements expected
        assertFalse(startElements.hasNext());
    }

    /**
     * This test loads model 'refmodel-navigation-04.bpmn' and finds all conditional
     * start events.
     * 
     * In this test we ignore all conditions and expect 3 events
     * 
     * @throws BPMNModelException
     * 
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testFindConditionalStartEvents() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-04.bpmn");
        BPMNProcess process = model.openDefaultProcess();

        // get start Task1
        BPMNStartElementIterator startElements = new BPMNStartElementIterator<>(process,
                node -> (node instanceof Activity || node instanceof Event));
        assertNotNull(startElements);
        assertTrue(startElements.hasNext());

        List<BPMNElementNode> resultList = new ArrayList<>();
        while (startElements.hasNext()) {
            resultList.add(startElements.next());
        }

        assertEquals(3, resultList.size());
    }

}
