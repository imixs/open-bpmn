package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNActivity;
import org.openbpmn.bpmn.elements.BPMNBounds;
import org.openbpmn.bpmn.elements.BPMNDimension;
import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class is testing updates of the position of flow elements
 * 
 * @author rsoika
 *
 */
public class TestUpdateBounds {

    private static Logger logger = Logger.getLogger(TestUpdateBounds.class.getName());

    static BPMNModel model = null; 

    @BeforeAll
    public static void init() {
        logger.info("...read model");
        model = BPMNModelFactory.read("/refmodel-process_2.bpmn");
    }

    /**
     * This test parses a bpmn file
     */
    @Test
    public void testUpdatePosition() {
        String out = "src/test/resources/update-bounds-process_2.bpmn";
        logger.info("...read model");

        // read tasks....
        BPMNProcess process = model.openContext(null);
        assertNotNull(process);

        BPMNActivity task1 = (BPMNActivity) process.findBaseElementById("Task_1");
        assertNotNull(task1);

        BPMNBounds bounds = task1.getBounds();
        assertNotNull(task1);

        BPMNPoint point = bounds.getPosition();
        assertEquals(270.0, point.getX());
        BPMNDimension size = bounds.getDimension();

        // adjust position
        bounds.updateBounds(300, point.getY(), size.getWidth(), size.getHeight());
        bounds = task1.getBounds();
        point = bounds.getPosition();
        assertEquals(300.0, point.getX());

        model.save(out);

        logger.info("...model update sucessful: " + out);
    }

}
