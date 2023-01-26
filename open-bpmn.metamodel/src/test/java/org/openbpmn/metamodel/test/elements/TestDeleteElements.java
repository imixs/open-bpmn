package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
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
public class TestDeleteElements {

    private static Logger logger = Logger.getLogger(TestDeleteElements.class.getName());

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
     * This test delete Task Element
     */
    @Test
    public void testDeleteTaskAndStartEvent() {
        BPMNModel model = null;
        String out = "src/test/resources/output/delete-process_2.bpmn";
        try {
            model = BPMNModelFactory.read("/refmodel-1.bpmn");

            BPMNProcess process = model.openProcess(null);
            assertEquals(2, process.getActivities().size());
            process.deleteTask("Task_1");

            assertEquals(1, process.getActivities().size());

            process.deleteEvent("StartEvent_1");
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        model.save(out);
        logger.info("...model update sucessful: " + out);
    }

    /**
     * This test build new model and delete element
     */
    @Test
    public void testBuildAndDelete() {

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        try {
            BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
            BPMNProcess processContext = model.openDefaultProcess();
            assertNotNull(processContext);

            // add a start and end event
            processContext.addEvent("start_1", "Start", BPMNTypes.START_EVENT);
            processContext.addEvent("end_1", "End", BPMNTypes.END_EVENT);
            Activity task = processContext.addTask("task_1", "Task", BPMNTypes.TASK);
            task.getBounds().setPosition(10.0, 10.0);
            task.getBounds().setDimension(140.0, 60.0);

            processContext.addSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            processContext.addSequenceFlow("SequenceFlow_2", "task_1", "end_1");
            processContext.deleteTask("task_1");
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

    }

    /**
     * This test delete TextAnnotation
     */
    @Test
    public void testDeleteTextAnnotation() {
        BPMNModel model = null;
        String out = "src/test/resources/output/delete-process_3.bpmn";
        try {
            model = BPMNModelFactory.read("/refmodel-6.bpmn");

            BPMNProcess process = model.openProcess(null);

            assertEquals(1, process.getTextAnnotations().size());

            process.deleteTextAnnotation("TextAnnotation_1");

            assertEquals(0, process.getTextAnnotations().size());

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
            BPMNProcess defaultProcess = model.openDefaultProcess();
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
            assertEquals(3, privateProcess.getAllNodes().size());
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

    /**
     * This test deletes a Lane from a participant
     */
    @Test
    public void testDeleteLane() {
        BPMNModel model = null;
        String out = "src/test/resources/output/delete-process_4.bpmn";
        try {
            model = BPMNModelFactory.read("/refmodel-5.bpmn");

            Participant participant = model.findParticipantById("Participant_1");
            assertNotNull(participant);
            BPMNProcess process = participant.openProcess();
            assertNotNull(process);
            assertEquals("Process_1", process.getId());

            // now delete 2nd line from process_1....
            process.deleteLane("Lane_2");

            // now the laneSet should only contain one more lane....
            assertTrue(process.hasLanes());
            assertEquals(1, process.getLanes().size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        model.save(out);
        logger.info("...model update sucessful: " + out);
    }

}
