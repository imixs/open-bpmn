package org.openbpmn.dom;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.util.BPMNModelFactory;

public class TestModifyDom {

    private static Logger logger = Logger.getLogger(TestModifyDom.class.getName());

    /**
     * This test parses a bpmn file
     */
    @Test
    public void testReadEmptyModel() {

        logger.info("...load existing model");

        BPMNModel model = BPMNModelFactory.read("/process_1-empty-1.bpmn");

        // add a new process....
        model.addProcess("P-000002");

        // store the model
        model.save("src/test/resources/process_1-update-1.bpmn");

        logger.info("...model update sucessful");
    }

}
