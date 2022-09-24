package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNFlowElement;
import org.openbpmn.bpmn.elements.BPMNLabel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the deletion of BPMN Model elements
 * 
 * @author rsoika
 *
 */
public class TestReadModel {

    private static Logger logger = Logger.getLogger(TestReadModel.class.getName());

    static BPMNModel model = null;

    @BeforeAll
    public static void init() throws BPMNModelException {
        logger.info("...read model");
        model = BPMNModelFactory.read("/refmodel-process_2.bpmn");

    }

    /**
     * This test creates a bpmn file
     */
    @Test
    public void testModelElements() {
        try {
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);
            assertEquals(2, process.getActivities().size());
            assertEquals(2, process.getEvents().size());

            BPMNFlowElement startEvent = process.findBPMNFlowElementById("StartEvent_1");
            assertNotNull(startEvent);

            // test the label position of the event
            BPMNLabel label = startEvent.getLabel();
            assertNotNull(label);
            assertEquals(84.0, label.getPosition().getX());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model read sucessful: ");
    }

}
