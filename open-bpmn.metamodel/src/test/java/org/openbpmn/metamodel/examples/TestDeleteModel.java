package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the deletion of BPMN Model elements
 * 
 * @author rsoika
 *
 */
public class TestDeleteModel {

    private static Logger logger = Logger.getLogger(TestDeleteModel.class.getName());

    /**
     * This test creates a bpmn file
     */
    @Test
    public void testDeleteTask() {
        BPMNModel model = null;
        String out = "src/test/resources/output/delete-process_1.bpmn";
        try {
            model = BPMNModelFactory.read("/refmodel-1.bpmn");
            BPMNProcess process = model.openProcess(null);
            process.deleteTask("Task_1");
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        model.save(out);
        logger.info("...model update sucessful: " + out);
    }

    /**
     * This test build new model one participant and a task. The test delete
     * participant - we expect the task is also deleted, but we still have a default
     * process and a collaboration diagram.
     */
    @Test
    public void testDeleteParticipant() {

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        try {
            BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
            BPMNProcess defaultProcess = model.openDefaultProces();
            assertNotNull(defaultProcess);

            // create Participant
            Participant sales = model.addParticipant("Sales");

            // add Task
            BPMNProcess privateProcess = sales.openProcess();
            // add a start and end event
            privateProcess.addEvent("start_1", "Start", BPMNTypes.START_EVENT);
            privateProcess.addEvent("end_1", "End", BPMNTypes.END_EVENT);
            privateProcess.addTask("task_1", "Task", BPMNTypes.TASK);
            privateProcess.addSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            privateProcess.addSequenceFlow("SequenceFlow_2", "task_1", "end_1");

            // we expect 3 Flow elements and 2 Processes
            assertEquals(3, privateProcess.getAllElementNodes().size());
            assertEquals(2, model.getProcesses().size());
            assertEquals(2, model.getParticipants().size());

            // now delete the Participant
            model.deleteParticipant(sales);
            assertEquals(1, model.getParticipants().size());
            assertEquals(1, model.getProcesses().size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

    }

}
