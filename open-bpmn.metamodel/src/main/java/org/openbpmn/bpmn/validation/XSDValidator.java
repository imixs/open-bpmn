package org.openbpmn.bpmn.validation;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/**
 * 
 * The XSDValidator can be used for a strict validation of a BPMN file against
 * the BPMNF 2.0 XSD files. The XSD files are part of this package.
 * 
 * The validation is a strict mode and is not used directly in the Open-BPMN
 * validation architecture.
 * 
 * See also
 * 
 * https://stackoverflow.com/questions/2342808/how-to-validate-an-xml-file-using-java-with-an-xsd-having-an-include
 * 
 * https://blog.frankel.ch/xml-validation-with-importedincluded-schemas/
 * 
 */
public class XSDValidator {
    private static Logger logger = Logger.getLogger(XSDValidator.class.getName());

    public boolean validate(String xmlFilePath) throws SAXException, IOException {

        // Path to the XSD files (located in class path)
        String xsdFilePath1 = "/BPMN20.xsd";

        InputStream xmlInputStream = getClass().getResourceAsStream(xmlFilePath);
        InputStream xsdInputStream1 = getClass().getResourceAsStream(xsdFilePath1);

        logger.finest("...validate bpmn 2.0 model...");

        // create SchemaFactory
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        schemaFactory.setResourceResolver(new XSDClasspathResourceResolver());
        // create the validator from BPMN 2.0 Schema
        Schema schema = schemaFactory.newSchema(new Source[] {
                new StreamSource(xsdInputStream1)
        });
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xmlInputStream));

        logger.finest("...validation completed");
        return true;
    }

}
