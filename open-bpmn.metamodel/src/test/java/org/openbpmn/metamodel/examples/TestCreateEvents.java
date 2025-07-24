package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the creation of Events
 * 
 * @author rsoika
 *
 */
public class TestCreateEvents {

    private static Logger logger = Logger.getLogger(TestCreateEvents.class.getName());

    /**
     * This test creates a bpmn file with Task and a Boundary Event
     */
    @Test
    public void testCreateBoundaryEvent() {
        String out = "src/test/resources/output/process-example-10.bpmn";

        logger.info("...create model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version,
                targetNameSpace);

        try {
            BPMNProcess process = model.openDefaultProcess();
            // Create a Task
            Activity task = process.addTask("task-1", "Task", BPMNTypes.TASK);

            // add a boundary event
            Event boundaryEvent = task.addBoundaryEvent("boundary-event-1", "Boundary Event");
            assertEquals(task.getId(), boundaryEvent.getAttribute("attachedToRef"));

            // we expect one boundary event
            List<Event> boundaryEventList = task.getAllBoundaryEvents();
            assertEquals(1, boundaryEventList.size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);
        assertEquals(1, model.getProcesses().size());

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

    /**
     * This test creates a bpmn file with multiple Tasks and a Boundary Events
     */
    @Test
    public void testCreateBoundaryMultiEvent() {
        String out = "src/test/resources/output/process-example-11.bpmn";

        logger.info("...create model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version,
                targetNameSpace);

        try {
            BPMNProcess process = model.openDefaultProcess();
            // Create a Task
            Activity task1 = process.addTask("task-1", "Task", BPMNTypes.TASK);
            Activity task2 = process.addTask("task-2", "Task", BPMNTypes.TASK);

            // add a boundary event
            Event boundaryEvent = task1.addBoundaryEvent("boundary-event-1", "Boundary Event");
            assertEquals(task1.getId(), boundaryEvent.getAttribute("attachedToRef"));

            boundaryEvent = task2.addBoundaryEvent("boundary-event-2", "Boundary Event");

            boundaryEvent = task2.addBoundaryEvent("boundary-event-3", "Boundary Event");

            // we expect one boundary event
            List<Event> boundaryEventList = task1.getAllBoundaryEvents();
            assertEquals(1, boundaryEventList.size());
            // we expect tgwo boundary event
            boundaryEventList = task2.getAllBoundaryEvents();
            assertEquals(2, boundaryEventList.size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);
        assertEquals(1, model.getProcesses().size());

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

}
