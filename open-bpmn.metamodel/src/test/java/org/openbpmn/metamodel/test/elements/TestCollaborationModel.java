package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
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
 * This test class tests reading a Collaboration model and its elements
 * 
 * @author rsoika
 *
 */
public class TestCollaborationModel {

    private static Logger logger = Logger.getLogger(TestCollaborationModel.class.getName());

    static BPMNModel model = null;

    /**
     * This test reads a collaboration model
     * The model does not have a default process. This process is automatically
     * added by the BPMNModel.
     */
    @SuppressWarnings("unused")
    @Test
    public void testReadCollaborationModel() {
        logger.info("...read collaboration model");
        try {
            model = BPMNModelFactory.read("/collaboration_1.bpmn");
            // we expect the existence of 2 process elements

            Set<Participant> participants = model.getParticipants();
            assertNotNull(participants);
            assertEquals(2, participants.size());

            // get first participant and load the process context
            Participant bpmnParticipant = participants.iterator().next();

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
        String out = "src/test/resources/output/collaboration-test-1.bpmn";

        logger.info("...create collaboration model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            assertEquals(1, model.getBpmnProcesses().size());

            // create two participants
            Participant participantSales = model.addParticipant("Sales Team");
            Participant participantManagement = model.addParticipant("Management");

            assertTrue(model.isCollaborationDiagram());
            assertEquals(3, model.getBpmnProcesses().size());

            // the default process should now be part of the participant list too
            assertEquals(3, model.getParticipants().size());

            // add a task
            participantSales.getBpmnProcess().addTask("task_1", "Task", BPMNTypes.TASK);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

    /**
     * This test shows how creating a Collaboration model with position information
     */
    @Test
    public void testCreateCollaborationModelWithPosition() {
        String out = "src/test/resources/output/collaboration-test-2.bpmn";

        logger.info("...create collaboration model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            // create two participants
            Participant participantSales = model.addParticipant("Sales Team");

            // add a task
            Activity task = participantSales.getBpmnProcess().addTask("task_1", "Task", BPMNTypes.TASK);
            task.setPosition(60, 40);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);
        model.save(out);
        logger.info("...model created successful: " + out);
    }

    /**
     * This test shows how creating a Collaboration model with Lanes and positions
     */
    @Test
    public void testCreateCollaborationModelWithLanesAndPosition() {
        String out = "src/test/resources/output/collaboration-test-3.bpmn";

        logger.info("...create collaboration model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            // create two participants
            Participant participantSales = model.addParticipant("Sales Team");
            participantSales.setBounds(10, 10, 500, 200);

            BPMNProcess salesProcess = participantSales.getBpmnProcess();
            // add a BPMNLane
            Lane lane1 = salesProcess.addLane("Europe");
            Lane lane2 = salesProcess.addLane("United States");
            lane1.setBounds(40, 10, 470, 100);
            lane2.setBounds(40, 110, 470, 100);

            // add a task
            Activity task = participantSales.getBpmnProcess().addTask("task_1", "Task", BPMNTypes.TASK);
            task.setPosition(100, 40);
            lane1.insert(task);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);
        model.save(out);
        logger.info("...model created successful: " + out);
    }

    /**
     * This test shows how creating a Collaboration model with Lanes but without
     * positions. The positions of the lanes should be computed by the model.
     */
    @SuppressWarnings("unused")
    @Test
    public void testCreateCollaborationModelWithLanesWithoutPosition() {
        String out = "src/test/resources/output/collaboration-test-4.bpmn";

        logger.info("...create collaboration model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            // create two participants
            Participant participantSales = model.addParticipant("Sales Team");
            participantSales.setBounds(10, 10, 500, 200);

            BPMNProcess salesProcess = participantSales.getBpmnProcess();
            // add a BPMNLane
            Lane lane1 = salesProcess.addLane("Europe");
            Lane lane2 = salesProcess.addLane("United States");
            Lane lane3 = salesProcess.addLane("Africa");

            // add a task
            Activity task = participantSales.getBpmnProcess().addTask("task_1", "Task", BPMNTypes.TASK);
            task.setPosition(100, 40);
            lane1.insert(task);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);
        model.save(out);
        logger.info("...model created successful: " + out);
    }

    /**
     * This test loads a collaboration model with a missing bpmn2:participant entry
     * for the default process.
     * The Metamodel should create the missing element on the fly
     * 
     */
    @SuppressWarnings("unused")
    @Test
    public void testMissingParticipant() {
        logger.info("...read corrupted collaboration model...");
        String out = "src/test/resources/output/missing-participant-element.bpmn";

        try {
            model = BPMNModelFactory.read("/missing-participant-element.bpmn");
            // we expect the existence of 2 participant elements even if the model file has
            // only one!

            Set<Participant> participants = model.getParticipants();
            assertNotNull(participants);
            assertEquals(2, participants.size());

            Iterator<Participant> partIter = model.getParticipants().iterator();
            while (partIter.hasNext()) {
                assertTrue(partIter.next().getBpmnProcess() != null);
            }
            // get first participant and load the process context
            Participant bpmnParticipant = participants.iterator().next();
            BPMNProcess process = model.openProcess("process_Ia6Vgg");
            // we expect 1 Task element in this process
            assertEquals(1, process.getEvents().size());

            model.save(new File(out));
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read sucessful: ");
    }

    /**
     * This test loads a collaboration model with a bpmn2:participant entry
     * missing a process.
     * The Metamodel should remove this participant node on the fly
     * The situation can be produced by Eclipse-BPMN2 and results in a inconsistent
     * bpmn model. See issue #299
     * 
     */
    @Test
    public void testMissingProcessForParticipant() {
        logger.info("...read corrupted collaboration model...");
        String out = "src/test/resources/output/missing-process-participant.bpmn";

        try {
            model = BPMNModelFactory.read("/missing-process-participant.bpmn");
            // we expect the existence of only 2 participant elements
            // Participant_2 should be removed
            Set<Participant> participants = model.getParticipants();
            assertNotNull(participants);
            assertEquals(2, participants.size());
            assertEquals(2, model.getBpmnProcesses().size());
            model.save(new File(out));
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful: ");
    }

    /**
     * This test loads reference model C.4.0.bpmn.
     * This model contains multipel collaboration models. We open the first one
     * But we also need to resolve the lanes correctly
     * See issue #409
     * 
     */
    @Test
    public void testMultipleCollaborationModels() {
        logger.info("...read corrupted collaboration model...");

        try {
            model = BPMNModelFactory.read("/reference/C.4.0.bpmn");

            Set<Participant> participants = model.getParticipants();
            assertNotNull(participants);
            assertEquals(2, participants.size());

            Participant participant = participants.iterator().next();
            assertNotNull(participant);
            assertEquals("Money Bank", participant.getName());
            BPMNProcess process = participant.getBpmnProcess().init();
            assertEquals(2, process.getLanes().size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model read successful: ");
    }
}
