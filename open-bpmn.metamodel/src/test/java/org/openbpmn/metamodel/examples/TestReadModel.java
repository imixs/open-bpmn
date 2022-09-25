package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNFlowElement;
import org.openbpmn.bpmn.elements.BPMNLabel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNInvalidIDException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the deletion of BPMN Model elements
 * 
 * @author rsoika
 *
 */
public class TestReadModel {

    private static Logger logger = Logger.getLogger(TestReadModel.class.getName());

    static BPMNModel model = null;

    /**
     * This test reads a simple bpmn file
     */
    @Test
    public void testReadSimpleModel() {
        try {
            logger.info("...read model");
            model = BPMNModelFactory.read("/refmodel-1.bpmn");

            BPMNProcess process = model.openDefaultProcess();
            assertNotNull(process);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, process.getProcessType());

            assertEquals(2, process.getActivities().size());
            assertEquals(2, process.getEvents().size());

            BPMNFlowElement startEvent = process.findBPMNFlowElementById("StartEvent_1");
            assertNotNull(startEvent);

            // test the label position of the event
            BPMNLabel label = startEvent.getLabel();
            assertNotNull(label);
            assertEquals(84.0, label.getPosition().getX());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful: ");
    }

    /**
     * This test reads a bpmn file with a process definition and tries to create an
     * additional process with the same id.
     * <p>
     * A DUPLICATE_ID exception is expected.
     * 
     */
    @Test
    public void testDupplicateProcessID() {
        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-1.bpmn");
            assertEquals(1, model.getProcesses().size());
            // try to create a dupplication process
            model.buildProcess("process_1", "Process 1", null);

            // exception expected
            fail();

        } catch (BPMNInvalidIDException e) {
            logger.warning(e.getMessage());
            assertEquals(BPMNInvalidIDException.DUPLICATE_ID, e.getErrorCode());
        } catch (BPMNModelException e) {

            e.printStackTrace();
            fail();
        }
    }

    /**
     * This test reads a collaboration bpmn file containing 2 participants but no
     * default process. We expect the a model creates a default process
     */
    @Test
    public void testReadCollaborationModelWithoutDefault() {
        try {
            logger.info("...read model");
            model = BPMNModelFactory.read("/refmodel-2.bpmn");

            // we expect 3 Processes
            assertEquals(3, model.getProcesses().size());

            BPMNProcess defaultProcess = model.openDefaultProcess();
            assertNotNull(defaultProcess);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, defaultProcess.getProcessType());
            // no activities expected
            assertEquals(0, defaultProcess.getActivities().size());

            // Open process 1
            BPMNProcess process1 = model.openProcess("Process_1");
            assertEquals(1, process1.getActivities().size());
            assertEquals(2, process1.getEvents().size());

            BPMNFlowElement startEvent = process1.findBPMNFlowElementById("StartEvent_1");
            assertNotNull(startEvent);

            // test the label position of the event
            BPMNLabel label = startEvent.getLabel();
            assertNotNull(label);
            assertEquals(146.0, label.getPosition().getX());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful: ");
    }

    /**
     * This test reads a collaboration bpmn file containing 3 participants including
     * a default process.
     * 
     */
    @Test
    public void testReadCollaborationModelWithDefault() {
        try {
            logger.info("...read model");
            model = BPMNModelFactory.read("/refmodel-3.bpmn");

            // we expect 3 Processes
            assertEquals(3, model.getProcesses().size());

            // verify Default Process
            BPMNProcess defaultProcess = model.openDefaultProcess();
            assertNotNull(defaultProcess);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, defaultProcess.getProcessType());
            assertEquals("Process_3", defaultProcess.getId());
            assertEquals("Default Pool Process", defaultProcess.getName());
            // 1 activities expected
            assertEquals(1, defaultProcess.getActivities().size());

            // verify process 1
            BPMNProcess process1 = model.openProcess("Process_1");
            assertEquals(1, process1.getActivities().size());
            assertEquals(2, process1.getEvents().size());
            BPMNFlowElement startEvent = process1.findBPMNFlowElementById("StartEvent_1");
            assertNotNull(startEvent);

            // test the label position of the event
            BPMNLabel label = startEvent.getLabel();
            assertNotNull(label);
            assertEquals(146.0, label.getPosition().getX());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful: ");
    }

}
