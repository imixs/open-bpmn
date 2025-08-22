package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.Participant;
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

    /**
     * This test parses a bpmn file
     */
    @Test
    public void testUpdatePosition() {
        String out = "src/test/resources/update-bounds-process_2.bpmn";
        logger.info("...read model");
        try {
            BPMNModel model = BPMNModelFactory.read("/refmodel-1.bpmn");

            // read tasks....
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);

            Activity task1 = (Activity) process.findElementNodeById("Task_1");
            assertNotNull(task1);

            BPMNBounds bounds = task1.getBounds();
            assertNotNull(task1);

            BPMNPoint point = bounds.getPosition();
            assertEquals(270.0, point.getX());
            BPMNDimension size = bounds.getDimension();

            // adjust position
            bounds.setPosition(300, point.getY());
            bounds.setDimension(size.getWidth(), size.getHeight());
            bounds = task1.getBounds();
            point = bounds.getPosition();
            assertEquals(300.0, point.getX());
            model.save(out);
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model update successful: " + out);
    }

    /**
     * This test moves a pool with elements
     */
    @Test
    public void testMovePool() {
        String out = "src/test/resources/update-bounds-process_2.bpmn";
        logger.info("...read model");
        try {
            BPMNModel model = BPMNModelFactory.read("/refmodel-2.bpmn");

            // read tasks....
            BPMNProcess process = model.openProcess("Process_2");
            assertNotNull(process);

            // test pool
            Participant participant = model.findParticipantByProcessId(process.getId());
            assertNotNull(participant);
            assertEquals("Process_2", participant.getProcessId());
            assertEquals("Process_2", participant.getBpmnProcess().getId());
            BPMNBounds bounds = participant.getBounds();
            BPMNPoint point = bounds.getPosition();
            point = bounds.getPosition();
            assertEquals(100.0, point.getX());
            assertEquals(350.0, point.getY());

            // test task
            Activity task1 = (Activity) process.findElementNodeById("ManualTask_1");
            assertNotNull(task1);
            bounds = task1.getBounds();
            point = bounds.getPosition();
            assertEquals(260.0, point.getX());

            // Now move pool 10 points to the right....
            participant.setPosition(110, 50);
            // Test pool position
            bounds = participant.getBounds();
            point = bounds.getPosition();
            point = bounds.getPosition();
            assertEquals(110.0, point.getX());
            // test task position
            bounds = task1.getBounds();
            point = bounds.getPosition();
            assertEquals(270.0, point.getX());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

        logger.info("...model update successful: " + out);
    }

}
