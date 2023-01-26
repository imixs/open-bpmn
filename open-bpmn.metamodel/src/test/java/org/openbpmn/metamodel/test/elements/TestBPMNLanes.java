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
import org.openbpmn.bpmn.elements.Lane;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class is testing Lanes 
 * 
 * @author rsoika
 *
 */
public class TestBPMNLanes {

    private static Logger logger = Logger.getLogger(TestBPMNLanes.class.getName());

    static BPMNModel model = null;


    /**
     * This test verifies the participant structure
     */
    @Test
    public void testReadLanes() {

        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-4.bpmn");
     
            Participant participant = model.findParticipantById("Participant_1");
            assertNotNull(participant);
            
            BPMNProcess process = participant.openProcess();
            assertNotNull(process);

            BPMNElementNode bpmnFlowElement = process.findElementNodeById("StartEvent_4");
            assertNotNull(bpmnFlowElement);
            
            // read laneset.....
            assertEquals(1, process.getLanes().size());
           

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    
    

    /**
     * This test class tests creating a Collaboration model with a BPMNLane and test the behavior of the lane
     */
    @Test
    public void testCreateCollaborationModelWithLane() {
        String out = "src/test/resources/output/create-laneset_1.bpmn";

        logger.info("...create collaboration model with lane");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            assertEquals(1, model.getProcesses().size());

            // create two participants
            Participant participantSales = model.addParticipant("Sales Team");
            assertTrue(model.isCollaborationDiagram());
            assertEquals(2, model.getProcesses().size());
            assertEquals(2, model.getParticipants().size());

            BPMNProcess process = participantSales.openProcess();
            // add a new Lane
            Lane lane=process.addLane("Lane 1");
            assertNotNull(lane);
            
            // add a task
            Activity task = process.addTask("task_1", "Task", BPMNTypes.TASK);

            // insert the task into the lane
            lane.insert(task);
            
            assertTrue(lane.contains(task));
            
            Set<String> flowElementList = lane.getFlowElementIDs();
            assertNotNull(flowElementList);
            assertEquals(1,flowElementList.size());
            assertEquals("task_1",flowElementList.iterator().next());
          
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

    
    

    /**
     * This test loads a model and inserts a new Lane before an existing
     */
    @Test
    public void testInsertNewLaneBeforeOther() {
        String out = "src/test/resources/output/create-laneset_2.bpmn";

        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-5.bpmn");
     
            Participant participant = model.findParticipantById("Participant_1");
            assertNotNull(participant);
            
            BPMNProcess process = participant.openProcess();
            assertNotNull(process);

            
            // add new Lane...
            Lane laneTest = process.addLane( "Lane Test");
            assertNotNull(laneTest);
            // read laneset.....
            assertEquals(3, process.getLanes().size());
            
            
            // insert new test-lane before Lane 2
            Lane lane2 = process.findLaneById("Lane_2");
          process.insertLaneBefore(laneTest, lane2) ;// laneTest.insertBefore(lane2);
           
            model.save(out);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    

}
