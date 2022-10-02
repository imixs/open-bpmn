package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNFlowElement;
import org.openbpmn.bpmn.elements.BPMNLane;
import org.openbpmn.bpmn.elements.BPMNParticipant;
import org.openbpmn.bpmn.elements.BPMNProcess;
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
     * This test verifies bounds of the start event
     */
    @Test
    public void testReadLanes() {

        logger.info("...read model");
        try {
            model = BPMNModelFactory.read("/refmodel-4.bpmn");
     
            BPMNParticipant participant = model.findBPMNParticipantById("Participant_1");
            assertNotNull(participant);
            
            BPMNProcess process = participant.openProcess();
            assertNotNull(process);

            BPMNFlowElement bpmnFlowElement = process.findBPMNFlowElementById("StartEvent_4");
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
        String out = "src/test/resources/create-laneset_1.bpmn";

        logger.info("...create collaboration model with lane");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            assertEquals(1, model.getProcesses().size());

            // create two participants
            BPMNParticipant participantSales = model.addParticipant("Sales Team");
            assertTrue(model.isCollaborationDiagram());
            assertEquals(2, model.getProcesses().size());
            assertEquals(2, model.getParticipants().size());

            BPMNProcess process = participantSales.openProcess();
            // add a new Lane
            BPMNLane lane=process.addLane(model,"Lane 1");
            assertNotNull(lane);
            
            // add a task
            BPMNActivity task = process.addTask("task_1", "Task", BPMNTypes.TASK);

            // insert the task into the lane
            lane.insert(task);
            
            assertTrue(lane.contains(task));
            
            List<String> flowElementList = lane.getFlowElementIDs();
            assertNotNull(flowElementList);
            assertEquals(1,flowElementList.size());
            assertEquals("task_1",flowElementList.get(0));
          
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }


}
