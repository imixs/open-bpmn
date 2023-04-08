package org.openbpmn.dom;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

public class TestCreateDom {

    private static Logger logger = Logger.getLogger(TestCreateDom.class.getName());

    /**
     * This test creates an empty BPMN model instance
     * 
     * @throws BPMNModelException
     */
    @Test
    public void testCreateEmptyModel() throws BPMNModelException {

        logger.info("...creating new empty model");

        BPMNModel model1 = BPMNModelFactory.createInstance("demo", "1.0.0", "http://org.openbpmn");
        model1.openDefaultProces().addTask("task-1", "Task", BPMNTypes.TASK);

        model1.save("src/test/resources/output/process_1-empty-1.bpmn");
        logger.info("...model creation sucessful");
    }

}
