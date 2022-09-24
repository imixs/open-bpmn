package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNProcess;
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

        try {
            model.buildProcess("process_1",null);
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
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
        try {
            BPMNProcess processContext = model.buildProcess("process_1",null);

            assertNotNull(processContext);

            // add a start and end event
            processContext.buildEvent("start_1", "Start", BPMNTypes.START_EVENT);
            processContext.buildEvent("end_1", "End", BPMNTypes.END_EVENT);
            processContext.buildTask("task_1", "Task", BPMNTypes.TASK);

            processContext.buildSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            processContext.buildSequenceFlow("SequenceFlow_2", "task_1", "end_1");
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
        String out = "src/test/resources/create-process_5.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
        try {
            model.buildProcess("process_1",null);

            BPMNProcess processContext = model.openContext("process_1");
            assertNotNull(processContext);

            // add a start and end event
            processContext.buildEvent("start_1", "Start", BPMNTypes.START_EVENT);
            processContext.buildEvent("end_1", "End", BPMNTypes.END_EVENT);
            processContext.buildTask("task_1", "Task", BPMNTypes.TASK);
            processContext.buildEvent("cathEvent_1", "Catch Event", BPMNTypes.CATCH_EVENT);
            processContext.buildGateway("gateway_1", "Gateway", BPMNTypes.EXCLUSIVE_GATEWAY);

            processContext.buildSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            processContext.buildSequenceFlow("SequenceFlow_2", "task_1", "end_1");
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
        String out = "src/test/resources/create-process_4.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
        try {
            model.buildProcess("process_1",null);

            BPMNProcess processContext = model.openContext("process_1");
            assertNotNull(processContext);

            // add a start and end event
            processContext.buildEvent("start_1", "Start", BPMNTypes.START_EVENT);
            processContext.buildEvent("end_1", "End", BPMNTypes.END_EVENT);
            BPMNActivity task = processContext.buildTask("task_1", "Task", BPMNTypes.TASK);
            task.getBounds().updateLocation(10.0, 10.0);
            task.getBounds().updateDimension( 140.0, 60.0);
            processContext.buildSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            processContext.buildSequenceFlow("SequenceFlow_2", "task_1", "end_1");
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        model.save(out);
        logger.info("...model created sucessful: " + out);

    }
    
    
    

    /**
     * This test class tests creating a Collaboration model and some elements
     */
    @Test
    public void testCreateCollaborationModel() {
        String out = "src/test/resources/create-collaboration_1.bpmn";

        logger.info("...create collaboration model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            model.buildParticipant("participant_1", "Sales Team");
            model.buildParticipant("participant_2", "Management");
            
            
            assertTrue(model.isCollaborationDiagram());
           
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

}
