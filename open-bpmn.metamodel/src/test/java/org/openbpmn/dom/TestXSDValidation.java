package org.openbpmn.dom;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.validation.XSDValidator;
import org.xml.sax.SAXException;

/**
 * This test class reads and validates a bpmn file against the XSD schemata.
 * 
 * @author rsoika
 *
 */
public class TestXSDValidation {

    private static Logger logger = Logger.getLogger(TestXSDValidation.class.getName());

    /**
     * Test the XSDValidator
     * 
     * @throws BPMNModelException
     */
    @Test
    public void testXSDValidator() throws BPMNModelException {
        // Path to the XML file you want to validate
        String xmlFilePath = "/xsd/test-01.bpmn";

        try {

            XSDValidator xsdValidator = new XSDValidator();

            boolean result = xsdValidator.validate(xmlFilePath);

            if (result) {
                System.out.println("Validation successful.");
            } else {
                fail();
            }

        } catch (SAXException | IOException e) {
            System.err.println("Validation failed: " + e.getMessage());
            e.printStackTrace();
            fail();
        }

        logger.info("...validation completed");
    }

}
