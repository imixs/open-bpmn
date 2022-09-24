package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
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
public class TestReadCollaborationModel {

    private static Logger logger = Logger.getLogger(TestReadCollaborationModel.class.getName());

    static BPMNModel model = null;

    @BeforeAll
    public static void init() throws BPMNModelException {
        logger.info("...read collaboration model");
        model = BPMNModelFactory.read("/collaboration_1.bpmn");
    }

    /**
     * This the collaboration model
     */
    @Test
    public void testModelElements() {

        // we expect the existence of 2 process elements

        Set<BPMNParticipant> participants = model.getParticipants();
        assertNotNull(participants);
        assertEquals(2, participants.size());
        
        // get first participant and load the process context
        BPMNParticipant bpmnParticipant= participants.iterator().next();

  
        try {
            BPMNProcess process = model.openProcess("Process_1");
            
            // we expect 1 Task element in this process
            assertEquals(1,process.getActivities().size());
        } catch (BPMNModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
        
        logger.info("...model read sucessful: ");
    }

}
