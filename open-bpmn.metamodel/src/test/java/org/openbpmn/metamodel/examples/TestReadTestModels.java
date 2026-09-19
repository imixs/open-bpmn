package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests some test models
 * 
 * @author rsoika
 *
 */
public class TestReadTestModels {

    private static Logger logger = Logger.getLogger(TestReadTestModels.class.getName());

    static BPMNModel model = null;

    /**
     * This test reads a simple bpmn file and searches for a event by id
     */
    @Test
    public void testReadSimpleModel() {
        try {
            logger.info("...read model");
            model = BPMNModelFactory.read("/default-process-test-001.bpmn");

            BPMNProcess process = model.openDefaultProcess();
            assertNotNull(process);
            assertEquals(BPMNTypes.PROCESS_TYPE_PUBLIC, process.getProcessType());

            assertEquals(4, process.getActivities().size());
            assertEquals(7, process.getEvents().size());

            BPMNElementNode testEvent = process.findElementNodeById("IntermediateCatchEvent_5");
            assertNotNull(testEvent);

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful: ");
    }

}
