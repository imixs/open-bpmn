package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
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

   
    
    /**
     * This test build new model and delete element
     */
    @Test
    public void testBuildAndDelete() {

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        model.buildProcess("process_1");

        BPMNProcess processContext = model.openContext("process_1");
        assertNotNull(processContext);

        // add a start and end event
        processContext.buildEvent("start_1", "Start", BPMNTypes.START_EVENT);
        processContext.buildEvent("end_1", "End",BPMNTypes.END_EVENT);
        BPMNActivity task = processContext.buildTask("task_1", "Task", BPMNTypes.TASK);
        task.getBounds().updateBounds(10.0, 10.0, 140.0, 60.0);
        try {
            processContext.buildSequenceFlow("SequenceFlow_1", "start_1", "task_1");
            processContext.buildSequenceFlow("SequenceFlow_2", "task_1", "end_1");
        } catch (BPMNInvalidReferenceException e) {
            e.printStackTrace();
            fail();
        }
        
   
        processContext.deleteTask("task_1");
       // logger.info("...model update sucessful: " + out);
    }

}
