package org.openbpmn.dom;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

public class TestModifyDom {

    private static Logger logger = Logger.getLogger(TestModifyDom.class.getName());

    /**
     * This test parses a bpmn file
     * 
     * @throws BPMNModelException
     */
    @Test
    public void testModifiyEmptyModel() {

        logger.info("...load existing model");

        BPMNModel model;
        try {
            model = BPMNModelFactory.read("/process_1-empty-1.bpmn");

            // add a new task....
            model.openDefaultProces().addTask("task-2", "Task", BPMNTypes.TASK);
            // store the model
            model.save("src/test/resources/output/process_1-update-1.bpmn");
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model update sucessful");
    }

}
