package org.openbpmn.bpmn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * A BPMNModel represents the dom tree of a BPMN model.
 * <p>
 * The model elements can be read or updated. 
 * The method 'save' writes the mode back into the filesystem.
 *  
 * @author rsoika
 *
 */
public class BPMNModel {
    // All final attributes

    private Element definitions;
    private Document doc;

    public BPMNModel(Document doc) {
        this.doc = doc;

        definitions = doc.getDocumentElement();
    }

    public Element getDefinitions() {
        return definitions;
    }

    public Document getDoc() {
        return doc;
    }

    public BPMNModel addProcess(String id) {
        Element process = doc.createElement("bpmn2:process");
        process.setAttribute("id", id);
        definitions.appendChild(process);

        return this;
    }

    
    /**
     * Writes the current instance to the file system.
     * 
     * @param file
     */
    public void save(String file) {
        try (FileOutputStream output = new FileOutputStream(file)) {
            writeXml(doc, output);
        } catch (TransformerException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    // write doc to output stream
    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(source, result);

    }
}
