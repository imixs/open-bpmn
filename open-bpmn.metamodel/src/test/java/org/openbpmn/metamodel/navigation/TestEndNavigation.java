package org.openbpmn.metamodel.navigation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.navigation.BPMNEndElementIterator;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.openbpmn.metamodel.examples.TestCreateEdges;

/**
 * Test class to test the EndNavigator.
 * 
 */
public class TestEndNavigation {
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
    public void testFindEndTask1() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-01.bpmn");
        BPMNProcess process = model.openDefaultProces();

        // get start Task1
        BPMNEndElementIterator endElements = new BPMNEndElementIterator<>(process, n -> n instanceof Activity);
        assertNotNull(endElements);
        assertTrue(endElements.hasNext());

        BPMNElementNode task2 = endElements.next();
        assertNotNull(task2);

        assertEquals("Task-2", task2.getName());
        // no more elements expected
        assertFalse(endElements.hasNext());
    }

    /**
     * This test loads model 'refmodel-navigation-02.bpmn' and finds 2 End Elements
     * (Task-2 and Task-3)
     * 
     * @throws BPMNModelException
     * 
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testFindStartTask1WithEvents() throws BPMNModelException {

        logger.info("...read model");
        BPMNModel model = BPMNModelFactory.read("/refmodel-navigation-02.bpmn");
        BPMNProcess process = model.openDefaultProces();

        // get start Task1
        BPMNEndElementIterator endElements = new BPMNEndElementIterator<>(process, n -> n instanceof Activity);
        assertNotNull(endElements);
        assertTrue(endElements.hasNext());

        BPMNElementNode task = endElements.next();
        assertNotNull(task);
        assertEquals("Task-3", task.getName());

        // next should be task-2
        task = endElements.next();
        assertNotNull(task);
        assertEquals("Task-2", task.getName());

        // no more elements expected
        assertFalse(endElements.hasNext());
    }

}
