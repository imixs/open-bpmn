package org.openbpmn.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Participant;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the Bounds Object
 * 
 * @author rsoika
 *
 */
public class TestBounds {

    private static Logger logger = Logger.getLogger(TestShortID.class.getName());

    /**
     * This test contains
     */
    @Test
    public void testContains() {

        String out = "src/test/resources/output/process-example-1.bpmn";
        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);
        assertNotNull(model);

        Participant container;
        try {
            container = model.addParticipant("container");

            Activity task = container.getBpmnProcess().addTask("taks_1", "Task 1", BPMNTypes.TASK);

            /**
             * 
             * Container:
             * BPMNBounds@135 "x=88.2730484008789 y=257.4853515625 width=611.0 heigth=200.0"
             * 
             * Element:
             * BPMNBounds@132 "x=302.04754638671875 y=177.68368530273438 width=30.0
             * heigth=20.0"
             * 
             * Center: "x=317.04754638671875 y=187.68368530273438"
             * 
             */
            container.setBounds(88.2730484008789, 257.4853515625, 611.0, 200.0);
            task.setBounds(302.04754638671875, 277.68368530273438, 30, 20);

            BPMNBounds containerBounds = container.getBounds();
            BPMNBounds taskBounds = task.getBounds();

            assertNotNull(taskBounds);

            boolean result = containerBounds.containsPoint(taskBounds.getCenter());

            assertTrue(result);

        } catch (BPMNModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
