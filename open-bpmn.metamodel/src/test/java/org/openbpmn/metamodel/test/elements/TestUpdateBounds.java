package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Process;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.elements.core.BPMNDimension;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
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
    public static void init() throws BPMNModelException {
        logger.info("...read model");
        model = BPMNModelFactory.read("/refmodel-1.bpmn");
    }

    /**
     * This test parses a bpmn file
     */
    @Test
    public void testUpdatePosition() {
        String out = "src/test/resources/update-bounds-process_2.bpmn";
        logger.info("...read model");
        try {
            // read tasks....
            Process process = model.openProcess(null);
            assertNotNull(process);

            Activity task1 = (Activity) process.findBPMNNodeById("Task_1");
            assertNotNull(task1);

            BPMNBounds bounds = task1.getBounds();
            assertNotNull(task1);

            BPMNPoint point = bounds.getPosition();
            assertEquals(270.0, point.getX());
            BPMNDimension size = bounds.getDimension();

            // adjust position
            bounds.setPosition(300, point.getY() );
            bounds.setDimension(size.getWidth(), size.getHeight());
            bounds = task1.getBounds();
            point = bounds.getPosition();
            assertEquals(300.0, point.getX());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        model.save(out);

        logger.info("...model update sucessful: " + out);
    }
    
    
    
    
    
}
