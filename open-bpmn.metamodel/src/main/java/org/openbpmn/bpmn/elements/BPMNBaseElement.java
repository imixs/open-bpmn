package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The BaseElement is the abstract super class for most BPMN elements. It
 * provides a list of attributes with at least an id and a documentation. Other
 * element types can extend the attribute list.
 * <p>
 * A BPMNFlowElement holds a reference to the bpmnShape element
 * 
 * @author rsoika
 */
public abstract class BPMNBaseElement {
    private NamedNodeMap attributeMap = null;
    private Node elementNode = null;
   
    protected BPMNModel model = null;

    public BPMNBaseElement() {
        super();
    }

    /**
     * Create a new BPMN Base Element. The constructor expects a model instnace and
     * a node.
     * 
     * @param node
     * @param model
     */
    public BPMNBaseElement(BPMNModel model, Node node) {
        super();
        this.model = model;
        this.elementNode = node;

        if (this.elementNode.hasAttributes()) {
            // get attributes names and values
            this.attributeMap = this.elementNode.getAttributes();
        }
       
    }

    /**
     * Returns the Document object associated with this Element. The document object
     * can be used to create new nodes.
     * 
     * @return
     */
    public Document getDoc() {
        Document doc = this.getElementNode().getOwnerDocument();
        return doc;
    }

    /**
     * Returns the ID of the element
     * 
     * @return
     */
    public String getId() {
        return getAttribute("id");
    }

    /**
     * Returns the name of the element
     * 
     * @return
     */
    public String getName() {
        return getAttribute("name");
    }

    /**
     * Returns the value of a given attribute by name.
     * <p>
     * The method operates directly on the attriubteMap loaded in the constructor.
     * 
     * @param name
     * @return
     */
    public String getAttribute(String name) {
        if (name == null || name.isEmpty() || attributeMap == null) {
            return null;
        }
        for (int i = 0; i < attributeMap.getLength(); i++) {
            Node node = attributeMap.item(i);
            if (name.equals(node.getNodeName())) {
                return node.getNodeValue();
            }
        }
        return null;
    }

    /**
     * Returns the corresponding dom element node
     * 
     * @return
     */
    public Node getElementNode() {
        return elementNode;
    }

    /**
     * Returns a map with all attributes of the Element node.
     * 
     * @param node
     * @return
     */
    public NamedNodeMap getAttributes() {
        return attributeMap;
    }


  
}
