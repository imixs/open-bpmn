package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the deletion of BPMN element.
 * 
 * @author rsoika
 *
 */
public class TestDeleteElement {

    private static Logger logger = Logger.getLogger(TestDeleteModel.class.getName());

    /**
     * This test creates a bpmn file.
     */
    @Test
    public void testDeleteTask() {
        BPMNModel model = null;
        String out = "src/test/resources/output/delete-element_1.bpmn";
        try {
            model = BPMNModelFactory.read("/refmodel-1.bpmn");
            BPMNProcess process = model.openProcess(null); // opens default process
            process.deleteTask("Task_1");
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        model.save(out);
        logger.info("...model update sucessful: " + out);
    }

}
