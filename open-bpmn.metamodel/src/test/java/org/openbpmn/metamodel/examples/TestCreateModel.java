package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

/**
 * This test class tests the creation of a BPMN Model with different content
 * 
 * @author rsoika
 *
 */
public class TestCreateModel {

    private static Logger logger = Logger.getLogger(TestCreateModel.class.getName());

    /**
     * This test creates a bpmn file. The model instance creates a public default
     * process automatically which can be opened by calling openDefaultProcess()
     */
    @Test
    public void testCreateEmptyModel() {
        String out = "src/test/resources/output/process-example-1.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
        assertNotNull(model);

        BPMNProcess defaultProcess = model.openDefaultProces();
        assertNotNull(defaultProcess);
        assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, defaultProcess.getProcessType());

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

    /**
     * This test creates a bpmn file with a process definition
     */
    @Test
    public void testCreateEmptyModelAndAddNewTask() {
        String out = "src/test/resources/output/process-example-2.bpmn";

        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            BPMNProcess process = model.openDefaultProces();
            process.addTask("task_1", "Task-1", null);
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
     * This test creates a bpmn file with a process definition containing Start and
     * End Events and a Task connected with SequenceFlows
     */
    @Test
    public void testCreateModelWithProcessAndElements() {
        String out = "src/test/resources/output/process-example-3.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
        try {
            BPMNProcess processContext = model.openDefaultProces();

            assertNotNull(processContext);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, processContext.getProcessType());

            // add a start and end event
            processContext.addEvent("start_1", "Start", BPMNTypes.START_EVENT);
            processContext.addEvent("end_1", "End", BPMNTypes.END_EVENT);
            processContext.addTask("task_1", "Task", BPMNTypes.TASK);

            processContext.addSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            processContext.addSequenceFlow("SequenceFlow_2", "task_1", "end_1");
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }

    /**
     * This test creates a bpmn file with a process definition containing Start and
     * End Events and a Task connected with SequenceFlows
     */
    @Test
    public void testCreateModelWithProcessAndGatewayElements() {
        String out = "src/test/resources/output/process-example-5.bpmn";
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
            processContext.addTask("task_1", "Task", BPMNTypes.TASK);
            processContext.addEvent("cathEvent_1", "Catch Event", BPMNTypes.CATCH_EVENT);
            processContext.addGateway("gateway_1", "Gateway", BPMNTypes.EXCLUSIVE_GATEWAY);

            processContext.addSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            processContext.addSequenceFlow("SequenceFlow_2", "task_1", "end_1");
        } catch (BPMNModelException e) {
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
        String out = "src/test/resources/output/process-example-4.bpmn";
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
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }

    /**
     * This test shows how creating a Collaboration model
     */
    @Test
    public void testCreateCollaborationModel() {
        String out = "src/test/resources/output/collaboration-example-1.bpmn";

        logger.info("...create collaboration model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            // create two participants
            Participant participantSales = model.addParticipant("Sales Team");

            // add a task
            Activity task = participantSales.openProcess().addTask("task_1", "Task", BPMNTypes.TASK);
            task.setPosition(60, 40);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);
        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

    /**
     * This test shows how creating a Collaboration model with Lanes
     */
    @SuppressWarnings("unused")
    @Test
    public void testCreateCollaborationModelWithLanes() {
        String out = "src/test/resources/output/collaboration-example-2.bpmn";

        logger.info("...create collaboration model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            // create two participants
            Participant participantSales = model.addParticipant("Sales Team");
            participantSales.setBounds(10, 10, 500, 100);

            BPMNProcess salesProcess = participantSales.openProcess();
            // add a BPMNLane
            Lane lane1 = salesProcess.addLane("Europe");
            Lane lane2 = salesProcess.addLane("United States");

            // add a task
            Activity task = participantSales.openProcess().addTask("task_1", "Task", BPMNTypes.TASK);
            task.setPosition(100, 40);
            lane1.insert(task);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);
        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

}
