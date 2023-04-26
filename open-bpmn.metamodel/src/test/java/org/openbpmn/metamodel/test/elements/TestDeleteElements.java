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
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.NodeList;

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

    /**
     * This test creates a collaboration diagram with 2 lanes and a task element.
     * Than the test deletes the task and verifies the lane references (task should
     * be deleted too)
     * 
     * Issue #234
     */
    @Test
    public void testDeleteTaskFromLane() {
        BPMNModel model = null;
        String out = "src/test/resources/output/delete-process_5.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            BPMNProcess publicProcess = model.openDefaultProces();
            assertNotNull(publicProcess);
            // Create a pool with two lanes
            Participant participant = model.addParticipant("example-workflow");
            participant.setPosition(50, 50);
            BPMNProcess exampleProcess = participant.getBpmnProcess();
            Lane laneSales = exampleProcess.addLane("Sales");
            Lane laneMarketing = exampleProcess.addLane("Marketing");
            Activity task1 = exampleProcess.addTask("task_1", "Task 1", BPMNTypes.MANUAL_TASK);
            // move task to 2nd lane ....
            task1.setPosition(150, 70);

            Activity task2 = exampleProcess.addTask("task_2", "Task 2", BPMNTypes.MANUAL_TASK);
            // move task to 1nd lane ....
            task2.setPosition(150, 350);

            // We expect that the 2nd laneSet contains a bpmn2:flowNodeRef for 'task_1'
            NodeList refElementList = laneSales.getElementNode().getElementsByTagName("bpmn2:flowNodeRef");
            assertNotNull(refElementList);
            assertEquals(1, refElementList.getLength());
            refElementList = laneMarketing.getElementNode().getElementsByTagName("bpmn2:flowNodeRef");
            assertNotNull(refElementList);
            assertEquals(1, refElementList.getLength());

            // now we delete the task2....
            exampleProcess.deleteTask(task2.getId());
            // and we expect that the Lane 'sales' has still the reference from task1...
            refElementList = laneSales.getElementNode().getElementsByTagName("bpmn2:flowNodeRef");
            assertNotNull(refElementList);
            assertEquals(1, refElementList.getLength());

            // ...but the lane 'marketing' has no more references...
            refElementList = laneMarketing.getElementNode().getElementsByTagName("bpmn2:flowNodeRef");
            assertNotNull(refElementList);
            assertEquals(0, refElementList.getLength());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(2, model.getProcesses().size());

        model.save(out);

        logger.info("...model update successful: " + out);
    }

}
