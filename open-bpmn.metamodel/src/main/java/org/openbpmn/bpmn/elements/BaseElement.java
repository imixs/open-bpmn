package org.openbpmn.bpmn.elements;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The BaseElement is the abstract super class for most BPMN elements. It
 * provides a list of attributes with at least an id and a documentation. Other
 * element types can extend the attribute list.
 * 
 * @author rsoika
 */
public abstract class BaseElement {
    Map<String, String> attributes=null;
    private Node elementNode=null;
    protected Node bpmnShape=null;
    protected Bounds bounds=null;

    public BaseElement() {
        super();
    }

    public BaseElement(Node node) {
        super();
        this.elementNode = node;
    }
    
    public Node getBpmnShape() {
        return bpmnShape;
    }

    
    
    public void setBpmnShape(Node bpmnShape) {
        this.bpmnShape = bpmnShape;
    }

    /**
     * Returns the ID of the element
     * @return
     */
    public String getId() {
        return getAttributes().get("id");
    }
    /**
     * Returns the name of the element
     * @return
     */
    public String getName() {
        return getAttributes().get("name");
    }
    
    /**
     * Returns the corresponding dom element node 
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
    public  Map<String, String> getAttributes() {
       // lazy loading of attributes
        if (attributes==null) {
            attributes= new HashMap<String, String>();
            if (elementNode.hasAttributes()) {
                // get attributes names and values
                NamedNodeMap nodeMap = elementNode.getAttributes();
                for (int i = 0; i < nodeMap.getLength(); i++) {
                    Node node = nodeMap.item(i);
                    attributes.put(node.getNodeName(), node.getNodeValue());
                }
            }
        }
    
        return attributes;
    }
    
    public Bounds getBounds() {
        if (bounds==null) {
            // lazy loading of bounds from a given bpmnShape
            bounds=new Bounds (this.bpmnShape);
        }
        return bounds;
    }
}
