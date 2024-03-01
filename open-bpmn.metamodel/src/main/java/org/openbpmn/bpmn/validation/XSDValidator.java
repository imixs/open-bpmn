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

public class XSDValidator {
    private static Logger logger = Logger.getLogger(XSDValidator.class.getName());

    public boolean validate(String xmlFilePath) throws SAXException, IOException {

        // Path to the XSD files
        String xsdFilePath1 = "/BPMN20.xsd";

        InputStream xmlInputStream = getClass().getResourceAsStream(xmlFilePath);
        InputStream xsdInputStream1 = getClass().getResourceAsStream(xsdFilePath1);

        if (xmlInputStream == null || xsdInputStream1 == null) {

        }
        logger.info("...validate model...");

        // create SchemaFactory

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        schemaFactory.setResourceResolver(new XSDClasspathResourceResolver());

        // Lade die XSD-Dateien in ein Schema
        Schema schema = schemaFactory.newSchema(new Source[] {
                new StreamSource(xsdInputStream1)
        });

        // Erstelle einen Validator aus dem Schema
        Validator validator = schema.newValidator();

        // Validiere die XML-Datei
        validator.validate(new StreamSource(xmlInputStream));

        System.out.println("Validation successful.");

        logger.info("...validation completed");
        return true;
    }

}
