package org.openbpmn.metamodel.examples;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the deletion of BPMN Model elements
 * 
 * @author rsoika
 *
 */
public class TestDeleteModel {

    private static Logger logger = Logger.getLogger(TestDeleteModel.class.getName());

    static BPMNModel model = null;

    @BeforeAll
    public static void init() {
        logger.info("...read model");
        model = BPMNModelFactory.read("/refmodel-process_2.bpmn");
        
    }
    
    /**
     * This test creates a bpmn file
     */
    @Test
    public void testDeleteTask() {
        String out = "src/test/resources/delete-process_1.bpmn";
        BPMNModel model = BPMNModelFactory.read("/refmodel-process_2.bpmn");
       
        BPMNProcess process = model.openContext(null);
        process.deleteTask("Task_1");

        model.save(out);
        logger.info("...model update sucessful: " + out);
    }
    
    
    /**
     * This test creates a bpmn file
     */
    @Test
    public void testDeleteTaskAndStartEvent() {
        String out = "src/test/resources/delete-process_2.bpmn";
        BPMNModel model = BPMNModelFactory.read("/refmodel-process_2.bpmn");
       
        BPMNProcess process = model.openContext(null);
        process.deleteTask("Task_1");
        process.deleteEvent("StartEvent_1");

        model.save(out);
        logger.info("...model update sucessful: " + out);
    }

   

}
