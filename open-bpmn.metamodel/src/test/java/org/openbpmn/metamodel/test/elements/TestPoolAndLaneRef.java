package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This test class is testing the process assignment of a BPMNElement and also
 * the
 * bpmn2:flowNodeRef element of a Lane within a participant.
 * 
 * The BPMNProcess ref depends on the position of a flow element within a
 * collaboration diagram.
 * 
 * The flowNodeRef depends on the position of a flow element within a Pool. If
 * setPosition is called on a BPMNElementNode the flowNodeRef of a containing
 * lane is automatically updated.
 * 
 * @author rsoika
 *
 */
public class TestPoolAndLaneRef {

    private static Logger logger = Logger.getLogger(TestBPMNLanes.class.getName());

    static BPMNModel model = null;

    /**
     * This test class just creates a Collaboration model with 2 BPMNLanes
     */
    @Test
    public void testCreateSimpleParticipant() {
        String out = "src/test/resources/output/lane-test_0.bpmn";

        logger.info("...create collaboration model with 2 lanes");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            assertEquals(1, model.getBpmnProcesses().size());

            // create participant
            Participant participantSales = model.addParticipant("Sales Team");
            assertTrue(model.isCollaborationDiagram());
            assertEquals(2, model.getBpmnProcesses().size());
            assertEquals(2, model.getParticipants().size());

            BPMNProcess process = participantSales.getBpmnProcess();
            // add a new Lane
            Lane laneA = process.addLane("Team-A");
            assertNotNull(laneA);
            Lane laneB = process.addLane("Team-B");
            assertNotNull(laneB);

            // test nodeElement
            assertEquals("Sales Team", participantSales.getElementNode().getAttribute("name"));
            assertEquals(process.getId(), participantSales.getElementNode().getAttribute("processRef"));

            // <bpmndi:BPMNShape bpmnElement="lane_4PK0HQ"
            // check if we have exactly one Shape element
            Element participantPlane = participantSales.getBpmnProcess().getBpmnPlane();

            String elementRef = participantPlane.getAttribute("bpmnElement");
            assertEquals("collaboration_1", elementRef);

            // test all shapes.
            // We expect 3 shapes in total and only one bpmnShape for each lane!
            int countLaneA = 0;
            int countLaneB = 0;
            int countTotal = 0;
            NodeList bpmnShapeList = model.findElementsByName(participantPlane, BPMNNS.BPMNDI,
                    "BPMNShape");

            for (int i = 0; i < bpmnShapeList.getLength(); i++) {
                countTotal++;
                Element planeElement = (Element) bpmnShapeList.item(i);
                String bpmnElementID = planeElement.getAttribute("bpmnElement");
                // if the id matches we have a direct macht in a non-collaboration element
                if (laneA.getId().equals(bpmnElementID)) {
                    countLaneA++;
                }
                if (laneB.getId().equals(bpmnElementID)) {
                    countLaneB++;
                }
            }
            assertEquals(3, countTotal);
            assertEquals(1, countLaneA);
            assertEquals(1, countLaneB);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);

        model.save(out);
        logger.info("...model created successful: " + out);
    }

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
    public void testChangeOfLaneAssignment() {
        String out = "src/test/resources/output/flownode-ref_1.bpmn";

        logger.info("...create collaboration model with 2 lanes");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            assertEquals(1, model.getBpmnProcesses().size());

            // create participant
            Participant participantSales = model.addParticipant("Sales Team");
            assertTrue(model.isCollaborationDiagram());
            assertEquals(2, model.getBpmnProcesses().size());
            assertEquals(2, model.getParticipants().size());

            BPMNProcess process = participantSales.getBpmnProcess();
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

            // // next lets move the task a little bit higher so it is overlapping but the
            // // center is still in the second lane
            task.setPosition(160, 200);
            flowElementList = laneA.getFlowElementIDs();
            // in laneA no more nodeRefs are expected....
            assertEquals(0, flowElementList.size());
            // ...but in laneB....
            flowElementList = laneB.getFlowElementIDs();
            assertEquals(1, flowElementList.size());
            assertEquals("task_1", flowElementList.iterator().next());

            // finally lets move the task a little bit higher so it is overlapping but
            // the centre is again in the first lane
            task.setPosition(160, 180);
            flowElementList = laneA.getFlowElementIDs();
            // again in laneA
            assertEquals(1, flowElementList.size());
            assertEquals("task_1", flowElementList.iterator().next());
            // ...but no longer in laneB....
            flowElementList = laneB.getFlowElementIDs();
            assertEquals(0, flowElementList.size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);

        model.save(out);
        logger.info("...model created successful: " + out);
    }

    /**
     * This test class creates a Collaboration model with a 2 private processes
     * (Pools) and a flow
     * element in one of it. The test verifies if the bpmn process assignment is
     * updated correctly.
     * 
     * For this test we change the position of a task to become part of different
     * pools or the default process.
     * 
     */
    @Test
    public void testChangeOfPoolAssignment() {
        String out = "src/test/resources/output/pool-assignment_1.bpmn";

        logger.info("...create collaboration model with 2 Pools");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        BPMNProcess defaultProcess = model.openDefaultProcess();
        try {
            assertEquals(1, model.getBpmnProcesses().size());

            // create participant1
            Participant participantSales = model.addParticipant("Sales Team");
            assertTrue(model.isCollaborationDiagram());
            assertEquals(2, model.getBpmnProcesses().size());
            assertEquals(2, model.getParticipants().size());
            // Set position and size of this pool
            participantSales.setPosition(10, 10);
            participantSales.setDimension(700, 300);
            BPMNProcess salesProcess = participantSales.getBpmnProcess();

            // create participant2
            Participant participantMarketing = model.addParticipant("Marketing Team");
            assertTrue(model.isCollaborationDiagram());
            assertEquals(3, model.getBpmnProcesses().size());
            assertEquals(3, model.getParticipants().size());
            // Set position and size of this pool
            participantMarketing.setPosition(10, 400);
            participantMarketing.setDimension(700, 300);
            BPMNProcess marketingProcess = participantMarketing.getBpmnProcess();

            // add a task
            Activity task = defaultProcess.addTask("task_1", "Task", BPMNTypes.TASK);

            // per default the task should be part of the defaultProcess
            assertTrue(defaultProcess == task.getBpmnProcess());

            // change position to sales team...
            task.setPosition(50, 50);
            // task should now be part of the sales process
            assertTrue(salesProcess == task.getBpmnProcess());

            // change position to sales team...
            task.setPosition(50, 450);
            // task should now be part of the marketing process
            assertTrue(marketingProcess == task.getBpmnProcess());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);

        model.save(out);
        logger.info("...model created successful: " + out);
    }

}
