package org.openbpmn.dom;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.validation.XSDValidator;
import org.xml.sax.SAXException;

/**
 * This test class reads and validates a bpmn file against the XSD schemata.
 * 
 * See also
 * 
 * https://stackoverflow.com/questions/2342808/how-to-validate-an-xml-file-using-java-with-an-xsd-having-an-include
 * 
 * https://blog.frankel.ch/xml-validation-with-importedincluded-schemas/
 * 
 * @author rsoika
 *
 */
public class TestValidate {

    private static Logger logger = Logger.getLogger(TestValidate.class.getName());

    /**
     * This test parses a bpmn file
     * 
     * @throws BPMNModelException
     */
    @Test
    public void testReadEmptyModel() throws BPMNModelException {
        // Path to the XML file you want to validate
        String xmlFilePath = "/xsd/test-01.bpmn";

        // Path to the XSD files
        String xsdFilePath1 = "/xsd/BPMN20.xsd";

        InputStream xmlInputStream = getClass().getResourceAsStream(xmlFilePath);
        InputStream xsdInputStream1 = getClass().getResourceAsStream(xsdFilePath1);

        if (xmlInputStream == null || xsdInputStream1 == null) {
            fail("Failed to load test resources.");
        }
        logger.info("...validate model...");

        try {
            // create SchemaFactory

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schemaFactory.setResourceResolver(new ClasspathResourceResolver());

            // Lade die XSD-Dateien in ein Schema
            Schema schema = schemaFactory.newSchema(new Source[] {
                    new StreamSource(xsdInputStream1)
            });

            // Erstelle einen Validator aus dem Schema
            Validator validator = schema.newValidator();

            // Validiere die XML-Datei
            validator.validate(new StreamSource(xmlInputStream));

            System.out.println("Validation successful.");

        } catch (SAXException | IOException e) {
            System.err.println("Validation failed: " + e.getMessage());
            e.printStackTrace();
            fail();
        }

        logger.info("...validation completed");
    }

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
