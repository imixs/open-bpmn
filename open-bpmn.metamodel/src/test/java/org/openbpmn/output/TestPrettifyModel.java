package org.openbpmn.output;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the deletion of BPMN Model elements
 * 
 * @author rsoika
 *
 */
public class TestPrettifyModel {

    private static Logger logger = Logger.getLogger(TestPrettifyModel.class.getName());

    /**
     * Test if the save method works as expected and did remove white space
     * <p>
     * See solution from:
     * https://stackoverflow.com/questions/12669686/how-to-remove-extra-empty-lines-from-xml-file/12670194#12670194
     */
    @Test
    public void testPrettify() {

        BPMNModel model;
        try {
            model = BPMNModelFactory.read("/prettify_test1.bpmn");

            model.save("src/test/resources/prettify_test1.xml");
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        logger.info("...model save sucessful ");
    }

}
