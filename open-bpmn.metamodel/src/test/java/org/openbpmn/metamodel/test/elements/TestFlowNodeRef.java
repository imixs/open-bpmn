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
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class is testing the bpmn2:flowNodeRef element of a Lane within a
 * participant.
 * 
 * The flowNodeRef depends on the position of a flow element. If setPosition is
 * called on a BPMNElementNode the flowNodeRef of a containing lane is
 * automatically updated.
 * 
 * @author rsoika
 *
 */
public class TestFlowNodeRef {

    private static Logger logger = Logger.getLogger(TestBPMNLanes.class.getName());

    static BPMNModel model = null;

    /**
     * This test class creates a Collaboration model with a 2 BPMNLane and a flow
     * element in one lane.
     * The test verifies if the flowNodeRef of the lane is updated correctly.
     * 
     * For this reason first a task is crated without a position. So it is not a
     * nodeRef of any lane.
     * Then the position is changed to become part of lane1 and next the position is
     * changed to lane2
     */
    @Test
    public void testCreateCollaborationModelWithLane() {
        String out = "src/test/resources/output/flownode-ref_1.bpmn";

        logger.info("...create collaboration model with 2 lanes");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            assertEquals(1, model.getProcesses().size());

            // create participant
            Participant participantSales = model.addParticipant("Sales Team");
            assertTrue(model.isCollaborationDiagram());
            assertEquals(2, model.getProcesses().size());
            assertEquals(2, model.getParticipants().size());

            BPMNProcess process = participantSales.openProcess();
            // add a new Lane
            Lane laneA = process.addLane("Team-A");
            assertNotNull(laneA);
            Lane laneB = process.addLane("Team-B");
            assertNotNull(laneB);

            // add a task
            Activity task = process.addTask("task_1", "Task", BPMNTypes.TASK);

            // if the task has not yet a position, than we expect that the lane has no
            // nodeRefs
            Set<String> flowElementList = laneA.getFlowElementIDs();
            assertNotNull(flowElementList);

            assertEquals(0, flowElementList.size());

            // now we set the position of the task ....
            task.setPosition(160, 50);
            // now the task should be part of laneA
            flowElementList = laneA.getFlowElementIDs();
            assertEquals(1, flowElementList.size());
            assertEquals("task_1", flowElementList.iterator().next());

            // next move the element to the second lane....
            task.setPosition(160, 220);
            flowElementList = laneA.getFlowElementIDs();
            // in laneA no more nodeRefs are expected....
            assertEquals(0, flowElementList.size());
            // ...but in laneB....
            flowElementList = laneB.getFlowElementIDs();
            assertEquals(1, flowElementList.size());
            assertEquals("task_1", flowElementList.iterator().next());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);

        model.save(out);
        logger.info("...model created successful: " + out);
    }

}
