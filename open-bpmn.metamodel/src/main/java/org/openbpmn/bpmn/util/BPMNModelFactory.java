package org.openbpmn.bpmn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openbpmn.bpmn.BPMNModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * The BPMNModelFactory can be used to load or create a BPMNModel instance.
 * 
 * @author rsoika
 *
 */
public class BPMNModelFactory {
    private static Logger logger = Logger.getLogger(BPMNModelFactory.class.getName());

    /**
     * This method creates a new BPMNModel instance.
     * 
     * @param exporter
     * @param exporterVersion
     * @param targetNamespace
     * @return
     */
    public static BPMNModel createInstance(String exporter, String exporterVersion, String targetNamespace) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();

            /*
             * <bpmn2:definitions xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL"
             * xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
             * xmlns:dc="http://www.omg.org/spec/DD/20100524/DC"
             * xmlns:di="http://www.omg.org/spec/DD/20100524/DI"
             * xmlns:ext="http://org.eclipse.bpmn2/ext"
             * xmlns:xs="http://www.w3.org/2001/XMLSchema"
             * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             * exporter="org.eclipse.bpmn2.modeler.core"
             * exporterVersion="1.5.3.Final-v20210519-2007-B1" id="Definitions_1"
             * targetNamespace="http://org.eclipse.bpmn2/default/process">
             */
            // root elements
            Document doc = docBuilder.newDocument();

            Element definitions = doc.createElementNS("http://www.omg.org/spec/BPMN/20100524/MODEL",
                    "bpmn2:definitions");
            definitions.setAttribute("xmlns:dc", "http://www.omg.org/spec/DD/20100524/DC");
            definitions.setAttribute("xmlns:di", "http://www.omg.org/spec/DD/20100524/DI");
            definitions.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema");
            definitions.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

            definitions.setAttribute("exporter", exporter);
            definitions.setAttribute("exporterVersion", exporterVersion);
            definitions.setAttribute("targetNamespace", targetNamespace);

            doc.appendChild(definitions);

            return new BPMNModel(doc);
        } catch (ParserConfigurationException e1) {

            e1.printStackTrace();
        }

        return null;
    }

    /**
     * Reads a BPMNModel instance from an java.io.File
     * 
     * @param modelFile
     * @return a BPMNModel instance
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static BPMNModel read(File modelFile) {
        try {
            return read(new FileInputStream(modelFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads a BPMNModel instance from an given file path
     * 
     * @param modelFile
     * @return a BPMNModel instance
     * @throws IOException
     */
    public static BPMNModel read(String modelFilePath) {
        return read(BPMNModel.class.getResourceAsStream(modelFilePath));
    }

    /**
     * Reads a BPMNModel instance from an InputStream
     * 
     * @param modelFile
     * @return a BPMNModel instance
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static BPMNModel read(InputStream is) {

        logger.info("read from inputStream...");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware(true);
        try {

            // parse XML file
            DocumentBuilder db = docFactory.newDocumentBuilder();

            // read from a project's resources folder
            Document doc = db.parse(is);

            Element root = doc.getDocumentElement();
            if (!"bpmn2:definitions".equals(root.getNodeName())) {
                logger.severe("Missing root element 'bpmn2:definitions'!");
                return null;
            } else {
                return new BPMNModel(doc);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return null;

    }

}
