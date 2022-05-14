package org.openbpmn.dom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.util.BPMNModelFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This test class reads a BPMN Model instance and prints the node elements
 * 
 * @author rsoika
 *
 */
public class TestReadDom {

    private static Logger logger = Logger.getLogger(TestReadDom.class.getName());

    /**
     * This test parses a bpmn file
     */
    @Test
    public void testReadEmptyModel() {


        logger.info("...read model");
        
        
        BPMNModel model = BPMNModelFactory.read("/process_1.bpmn");
        System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());
        System.out.println("------");
        if (model.getDoc().hasChildNodes()) {
            printNote(model.getDoc().getChildNodes());
        }
        
        // next validate the BPMN Default Namespaces
        assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL",model.getNameSpaceUri(BPMNNS.BPMN2));
        
        logger.info("...model read sucessful");
    }
    
    
   
    /**
     * This Test verifies if a BPMN model with custom namespace URIs can be read. 
     * 
     */
    @Test
    public void testReadEmptyModelWithCustomNamespace() {

        logger.info("...read model");

        
        BPMNModel model = BPMNModelFactory.read("/process_1_custom_namespace.bpmn");
        System.out.println("Root Element :" + model.getDoc().getDocumentElement().getNodeName());
        // next validate the custom BPMN Namespace for bpmn2
        assertEquals("http://www.omg.org/spec/BPMN/20100000/MODEL",model.getNameSpaceUri(BPMNNS.BPMN2));
        
        // now we read  once again a model with the default namespace
        // we expect that the default namespace is set again!
         model = BPMNModelFactory.read("/process_1.bpmn");
        // next validate the BPMN Default Namespaces
        assertEquals("http://www.omg.org/spec/BPMN/20100524/MODEL",model.getNameSpaceUri(BPMNNS.BPMN2));
      
        logger.info("...model read sucessful");
        
    }

    private static void printNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());

                if (tempNode.hasAttributes()) {

                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++) {
                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());
                    }

                }

                if (tempNode.hasChildNodes()) {
                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());
                }

                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

            }

        }

    }

}
