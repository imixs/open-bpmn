package org.openbpmn.metamodel.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.BPMNDataObject;
import org.openbpmn.bpmn.elements.BPMNProcess;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.util.BPMNModelFactory;

/**
 * This test class tests the creation of specific BPMN Edge types
 * 
 * @author rsoika
 *
 */
public class TestCreateEdges {

    private static Logger logger = Logger.getLogger(TestCreateEdges.class.getName());

   
    /**
     * This test creates a bpmn file with two data objects linked with an association.
     * 
     */
    @Test
    public void testCreateDataObject() {
        String out = "src/test/resources/output/process-example-7.bpmn";

        logger.info("...create empty model");

        String exporter = "demo";
        String version = "1.0.0";
        String targetNameSpace = "http://org.openbpmn";
        BPMNModel model = BPMNModelFactory.createInstance(exporter, version, targetNameSpace);

        try {
            BPMNProcess process = model.openDefaultProcess();
            // Create a DataObject
            BPMNDataObject dataObject1 = process.addDataObject("dataobject_1", "DataObject-1");
            dataObject1.setPosition(100,50);
            BPMNDataObject dataObject2=process.addDataObject("dataobject_2", "DataObject-2");
            dataObject2.setPosition(200,50);
            
            // create associaton
            process.addAssociation("association_1",dataObject1.getId(), dataObject2.getId());
        } catch (BPMNModelException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(model);
        assertEquals(1, model.getProcesses().size());

        model.save(out);
        logger.info("...model created sucessful: " + out);
    }

}
