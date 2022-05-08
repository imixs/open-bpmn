package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNEventType;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTaskType;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the creation of a BPMN Model with different content
 * 
 * @author rsoika
 *
 */
public class TestCreateModel {

    private static Logger logger = Logger.getLogger(TestCreateModel.class.getName());

    /**
     * This test creates a bpmn file
     */
    @Test
    public void testCreateEmptyModel() {
        String out = "src/test/resources/create-process_1.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
        assertNotNull(model);

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

    /**
     * This test creates a bpmn file with a process definition
     */
    @Test
    public void testCreateEmptyModelWithProcess() {
        String out = "src/test/resources/create-process_2.bpmn";

        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        model.addProcess("process_1");
        assertNotNull(model);

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

    /**
     * This test creates a bpmn file with a process definition containing Start and
     * End Events and a Task connected with SequenceFlows
     */
    @Test
    public void testCreateModelWithProcessAndElements() {
        String out = "src/test/resources/create-process_3.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        model.addProcess("process_1");

        BPMNProcess processContext = model.openContext("process_1");
        assertNotNull(processContext);

        // add a start and end event
        processContext.addEvent("start_1", "Start", BPMNEventType.START);
        processContext.addEvent("end_1", "End", BPMNEventType.END);
        processContext.addTask("task_1", "Task", BPMNTaskType.TASK);

        try {
            processContext.addSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            processContext.addSequenceFlow("SequenceFlow_2", "task_1", "end_1");
        } catch (BPMNInvalidReferenceException e) {
            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }

    /**
     * This test creates a bpmn file with a process definition containing Start and
     * End Events and a Task connected with SequenceFlows.
     * <p>
     * The test also adds shape information to task_1
     */
    @Test
    public void testCreateModelWithProcessAndElementsLayout() {
        String out = "src/test/resources/create-process_4.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        model.addProcess("process_1");

        BPMNProcess processContext = model.openContext("process_1");
        assertNotNull(processContext);

        // add a start and end event
        processContext.addEvent("start_1", "Start", BPMNEventType.START);
        processContext.addEvent("end_1", "End", BPMNEventType.END);
        BPMNActivity task = processContext.addTask("task_1", "Task", BPMNTaskType.TASK);
        task.getBounds().updateBounds(10.0, 10.0, 140.0, 60.0);
        try {
            processContext.addSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            processContext.addSequenceFlow("SequenceFlow_2", "task_1", "end_1");
        } catch (BPMNInvalidReferenceException e) {
            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }

}
