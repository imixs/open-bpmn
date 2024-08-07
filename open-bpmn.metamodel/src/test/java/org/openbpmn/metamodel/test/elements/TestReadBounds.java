package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.core.BPMNBounds;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.elements.core.BPMNPoint;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class is testing BPMNBounds elements in a model
 * 
 * @author rsoika
 *
 */
public class TestReadBounds {

    private static Logger logger = Logger.getLogger(TestReadBounds.class.getName());

    /**
     * This test verifies bounds of the start event
     */
    @Test
    public void testEventBounds() {

        logger.info("...read model");
        try {
            BPMNModel model = BPMNModelFactory.read("/refmodel-1.bpmn");
            // read tasks....
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);

            BPMNElementNode bpmnFlowElement = process.findElementNodeById("StartEvent_1");
            assertNotNull(bpmnFlowElement);

            BPMNBounds bounds = bpmnFlowElement.getBounds();

            assertNotNull(bounds);
            BPMNPoint point = bounds.getPosition();

            assertEquals(100.0, point.getX());
            assertEquals(100.0, point.getY());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * This test finds the BPMNLabel bounds for a given BPMNFlowElement (Event) in a
     * bpmn file
     */
    @Test
    public void testGetBPMNLabelBounds() {

        logger.info("...read model");
        try {
            BPMNModel model = BPMNModelFactory.read("/refmodel-1.bpmn");
            // read tasks....
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);

            BPMNElementNode bpmnFlowElement = process.findElementNodeById("StartEvent_1");

            assertNotNull(bpmnFlowElement);

            BPMNLabel label = bpmnFlowElement.getLabel();

            assertNotNull(label);
            BPMNPoint point = label.getPosition();
            assertNotNull(label);
            assertEquals(84.0, point.getX());
            assertEquals(136.0, point.getY());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }

    }

}
