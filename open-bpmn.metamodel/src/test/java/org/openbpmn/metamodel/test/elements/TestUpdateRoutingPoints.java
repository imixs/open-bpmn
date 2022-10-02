package org.openbpmn.metamodel.test.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNPoint;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.elements.BPMNSequenceFlow;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class is testing updates of the position of flow elements
 * 
 * @author rsoika
 *
 */
public class TestUpdateRoutingPoints {

    private static Logger logger = Logger.getLogger(TestUpdateRoutingPoints.class.getName());

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
    public void testUpdateRoutingPoints() {
        String out = "src/test/resources/update-routingpoints-process_2.bpmn";
        logger.info("...read model");
        try {
            // read Sequcen....
            BPMNProcess process = model.openProcess(null);
            assertNotNull(process);

            // udpate routing points for SequenceFlow_1

            BPMNSequenceFlow flow1 = (BPMNSequenceFlow) process.findBaseElementById("SequenceFlow_1");
            assertNotNull(flow1);

            assertEquals(3, flow1.getWayPoints().size());

            // remove all routing points
            flow1.clearWayPoints();
            assertEquals(0, flow1.getWayPoints().size());

            // add two new waypoints
            // <di:waypoint xsi:type="dc:Point" x="118.0" y="174.0"/>
            // <di:waypoint xsi:type="dc:Point" x="270.0" y="174.0"/>
            flow1.addWayPoint(new BPMNPoint(118.0, 174.0));
            flow1.addWayPoint(new BPMNPoint(270.0, 174.0));
            assertEquals(2, flow1.getWayPoints().size());

        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        model.save(out);

        logger.info("...model update sucessful: " + out);
    }

}
