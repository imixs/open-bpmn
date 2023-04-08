package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.Document;

/**
 * This test class tests the deep copy of a BPMN Model instance
 * 
 * @author rsoika
 *
 */
public class TestDeepCopy {

    private static Logger logger = Logger.getLogger(TestDeepCopy.class.getName());

    /**
     * This test creates a bpmn file with a process definition containing Start and
     * End Events and a Task connected with SequenceFlows.
     * <p>
     * The test also adds shape information to task_1
     */
    @Test
    public void testCreateModelWithProcessAndElementsLayout() {
        String out = "src/test/resources/output/clone-process_4.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
        try {
            BPMNProcess processContext = model.openDefaultProces();
            assertNotNull(processContext);
            // add a start and end event
            processContext.addEvent("start_1", "Start", BPMNTypes.START_EVENT);
            processContext.addEvent("end_1", "End", BPMNTypes.END_EVENT);
            Activity task = processContext.addTask("task_1", "Task", BPMNTypes.TASK);
            task.setPosition(10.0, 10.0);
            task.setDimension(140.0, 60.0);

            processContext.addSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            processContext.addSequenceFlow("SequenceFlow_2", "task_1", "end_1");

            // now generate a deep copy
            long l = System.currentTimeMillis();
            Document clone = (Document) model.getDoc().cloneNode(true);
            logger.info("clone model took " + (System.currentTimeMillis() - l) + "ms");
            // remove elements from origin
            processContext.deleteEvent("start_1");

            // one Process expected
            assertEquals(1, model.getProcesses().size());

            model.save(out);

            // test structure of clone....
            BPMNModel modelClone = new BPMNModel(clone);
            BPMNProcess processContextClone = modelClone.openProcess("process_1");
            assertEquals(2, processContextClone.getEvents().size());

            assertEquals(1, processContext.getEvents().size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model created sucessful: " + out);

    }

}
