package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNParticipant;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests reading a Collaboration model and its elements
 * 
 * @author rsoika
 *
 */
public class TestCollaborationModel {

    private static Logger logger = Logger.getLogger(TestCollaborationModel.class.getName());

    static BPMNModel model = null;

    /**
     * This the collaboration model
     */
    @SuppressWarnings("unused")
    @Test
    public void testReadCollaborationModel() {
        logger.info("...read collaboration model");
        try {
            model = BPMNModelFactory.read("/collaboration_1.bpmn");
            // we expect the existence of 2 process elements

            Set<BPMNParticipant> participants = model.getParticipants();
            assertNotNull(participants);
            assertEquals(2, participants.size());

            // get first participant and load the process context
            BPMNParticipant bpmnParticipant = participants.iterator().next();

            BPMNProcess process = model.openProcess("Process_1");

            // we expect 1 Task element in this process
            assertEquals(1, process.getActivities().size());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read sucessful: ");
    }


    /**
     * This test class tests creating a Collaboration model and some elements
     */
    @SuppressWarnings("unused")
    @Test
    public void testCreateCollaborationModel() {
        String out = "src/test/resources/create-collaboration_1.bpmn";

        logger.info("...create collaboration model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            assertEquals(1, model.getProcesses().size());

            // create two participants
            BPMNParticipant participantSales = model.addParticipant("Sales Team");
            BPMNParticipant participantManagement = model.addParticipant("Management");

            assertTrue(model.isCollaborationDiagram());
            assertEquals(3, model.getProcesses().size());

            // the default process should now be part of the participant list too
            assertEquals(3, model.getParticipants().size());

            // add a task
            participantSales.openProcess().addTask("task_1", "Task", BPMNTypes.TASK);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

}
