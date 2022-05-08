package org.openbpmn.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;

/**
 * This test class tests the creation of a BPMN Model with different content
 * 
 * @author rsoika
 *
 */
public class TestShortID {

    private static Logger logger = Logger.getLogger(TestShortID.class.getName());

    /**
     * This test creates random short id used in bpmn models
     */
    @Test
    public void testShortRandomID() {

        String id = BPMNModel.generateShortID();
        assertNotNull(id);
        logger.info("id=" + id);
        id = BPMNModel.generateShortID();
        assertNotNull(id);
        logger.info("id=" + id);
        id = BPMNModel.generateShortID();
        assertNotNull(id);
        logger.info("id=" + id);
        
        
        id = BPMNModel.generateShortID("BPMNShape");
        assertNotNull(id);
        logger.info("id=" + id);
        id = BPMNModel.generateShortID("BPMNShape");
        assertNotNull(id);
        logger.info("id=" + id);
        id = BPMNModel.generateShortID("BPMNShape");
        assertNotNull(id);
        logger.info("id=" + id);
    }

}
