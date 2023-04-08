package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the deletion of BPMN Model elements
 * 
 * @author rsoika
 *
 */
public class TestReadModelTypes {

    private static Logger logger = Logger.getLogger(TestReadModelTypes.class.getName());

    static BPMNModel model = null;

    /**
     * This test reads a simple bpmn file
     */
    @Test
    public void testReadSimpleModel() {
        try {
            logger.info("...read model");
            model = BPMNModelFactory.read("/refmodel-1.bpmn");

            BPMNProcess process = model.openDefaultProces();
            assertNotNull(process);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, process.getProcessType());

            assertEquals(2, process.getActivities().size());
            assertEquals(2, process.getEvents().size());

            BPMNElementNode startEvent = process.findElementNodeById("StartEvent_1");
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

            BPMNProcess defaultProcess = model.openDefaultProces();
            assertNotNull(defaultProcess);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, defaultProcess.getProcessType());
            // no activities expected
            assertEquals(0, defaultProcess.getActivities().size());

            // Open process 1
            BPMNProcess process1 = model.openProcess("Process_1");
            assertEquals(1, process1.getActivities().size());
            assertEquals(2, process1.getEvents().size());

            BPMNElementNode startEvent = process1.findElementNodeById("StartEvent_1");
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
            BPMNProcess defaultProcess = model.openDefaultProces();
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
            BPMNElementNode startEvent = process1.findElementNodeById("StartEvent_1");
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
