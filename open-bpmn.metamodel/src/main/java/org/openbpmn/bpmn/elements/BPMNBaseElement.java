package org.openbpmn.bpmn.elements;

import java.util.List;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The BaseElement is the abstract super class for most BPMN elements. It
 * provides a list of attributes with at least an id and a documentation. Other
 * element types can extend the attribute list.
 * <p>
 * A BPMNBaseElement holds a reference to an optional bpmnShape element
 * 
 * @author rsoika
 */
public abstract class BPMNBaseElement {
    private NamedNodeMap attributeMap = null;
    private Element elementNode = null;
    protected BPMNModel model = null;
    private Element documentationNode = null;
    protected Element bpmnShape = null;
    protected BPMNBounds bounds = null;
    
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
    public BPMNBaseElement(BPMNModel model, Element node) {
        super();
        this.model = model;
        this.elementNode = node;

        if (this.elementNode.hasAttributes()) {
            // get attributes names and values
            this.attributeMap = this.elementNode.getAttributes();
        }

    }

    /**
     * Returns the current Model Instance
     * 
     * @return
     */
    public BPMNModel getModel() {
        return model;
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
     * Returns the name attribute of the BPMN element
     * 
     * @return
     */
    public String getName() {
        return getAttribute("name");
    }

    /**
     * Updates the name attribute of the BPMN element
     * 
     * @param name
     */
    public void setName(String name) {
        setAttribute("name", name);
    }

    /**
     * Returns the Documentation
     * 
     * @return String - can be empty
     */
    public String getDocumentation() {
        if (documentationNode == null) {
            // lazy loading of documentation element
            List<Element> elementList = BPMNModel.findChildNodesByName(elementNode,
                    BPMNNS.BPMN2.prefix + ":documentation");
            if (elementList.size() > 0) {
                // get the first one and update the value only
                documentationNode = elementList.get(0);
            }
        }
        if (documentationNode != null && documentationNode.getFirstChild() != null) {
            return documentationNode.getFirstChild().getNodeValue();
        } else {
            return ""; // element
        }
    }

    /**
     * Set the new documentation content for this element.
     * 
     * @param content
     */
    public void setDocumentation(String content) {
        if (documentationNode == null) {
            // lazy loading of documentation element
            List<Element> elementList = BPMNModel.findChildNodesByName(elementNode,
                    BPMNNS.BPMN2.prefix + ":documentation");
            if (elementList.size() == 0) {
                // create new node
                documentationNode = model.createElement(BPMNNS.BPMN2, "documentation");
                documentationNode.setAttribute("id", BPMNModel.generateShortID("documentation"));
                elementNode.appendChild(documentationNode);
            } else {
                // get the first one and update the value only
                documentationNode = elementList.get(0);
            }
        }
        // remove old child nodes
        NodeList docChildList = documentationNode.getChildNodes();
        for (int i = 0; i < docChildList.getLength(); i++) {
            Node child = docChildList.item(i);
            documentationNode.removeChild(child);
        }
        CDATASection cdata = getDoc().createCDATASection(content);
        documentationNode.appendChild(cdata);
    }

    /**
     * Set the new childNode with a given content for this element in a CDATA
     * element.
     * <p>
     * The id is optional and is only set if not null
     * 
     * @param nodeName name of the new child node
     * @param content  the content
     * @param id       optional id
     */
    public void setChildNodeContent(String nodeName, String content, String id) {
        Element childNode = null;
        // load the element
        List<Element> elementList = BPMNModel.findChildNodesByName(elementNode, BPMNNS.BPMN2.prefix + ":" + nodeName);
        if (elementList.size() == 0) {
            // create new node
            childNode = model.createElement(BPMNNS.BPMN2, nodeName);
            if (id != null && !id.isEmpty()) {
                childNode.setAttribute("id", id);
            }
            elementNode.appendChild(childNode);
        } else {
            // get the first one and remove old values
            childNode = elementList.get(0);
            // remove old child nodes of the the node...
            NodeList subChildList = childNode.getChildNodes();
            for (int i = 0; i < subChildList.getLength(); i++) {
                Node child = subChildList.item(i);
                childNode.removeChild(child);
            }
        }

        // set content as CDATA text
        CDATASection cdata = getDoc().createCDATASection(content);
        childNode.appendChild(cdata);
    }

    /**
     * Returns the content of a given childNode as a string
     * 
     * @return String - can be empty
     */
    public String getChildNodeContent(String nodeName) {
        Element childNode = null;
        // lazy loading of documentation element
        List<Element> elementList = BPMNModel.findChildNodesByName(elementNode, BPMNNS.BPMN2.prefix + ":" + nodeName);
        if (elementList.size() > 0) {
            // get the first one and update the value only
            childNode = elementList.get(0);
            if (childNode.getFirstChild() != null) {
                return childNode.getFirstChild().getNodeValue();
            }
        }
        return ""; // no element found
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
//        for (int i = 0; i < attributeMap.getLength(); i++) {
//            Node node = attributeMap.item(i);
//            if (name.equals(node.getNodeName())) {
//                return node.getNodeValue();
//            }
//        }
//        return null;
        return getElementNode().getAttribute(name);
    }

    /**
     * Set the value of a given attribute by name.
     * <p>
     * The method operates directly on the attriubteMap loaded in the constructor.
     * 
     * @param name
     * @return
     */
    public void setAttribute(String name, String value) {
        if (name == null || name.isEmpty() || attributeMap == null) {
            return;
        }
//        for (int i = 0; i < attributeMap.getLength(); i++) {
//            Node node = attributeMap.item(i);
//            if (name.equals(node.getNodeName())) {
//                // update the attribute value
//                node.setNodeValue(value);
//                return;
//            }
//        }
        // if we did not found the attribute, we add a new one...
        this.getElementNode().setAttribute(name, value);

    }

    /**
     * Returns true when an attribute with a given name is specified on this BPMN
     * element or has a default value, false otherwise.
     * 
     * @param name
     * @return
     */
    public boolean hasAttribute(String name) {
        return this.getElementNode().hasAttribute(name);
    }

    /**
     * Returns the corresponding dom element node
     * 
     * @return
     */
    public Element getElementNode() {
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

    /**
     * This method sets a specific extension attribute. The extensionNamespace
     * defines the attribute prefix within the BPMNElement:
     * <p>
     * {@code<bpmn2:task id="Task_1" imixs:processid="6100" name="Task 1">}
     * 
     * @param extensionNamespace
     * @param attribute
     * @param value
     */
    public void setExtensionAttribute(String extensionNamespace, String attribute, String value) {
        setAttribute(extensionNamespace + ":" + attribute, value);
    }

    /**
     * Returns the value of a given extension attribute by name.
     * <p>
     * 
     * @param name
     * @return
     */
    public String getExtensionAttribute(String extensionNamespace, String name) {
        if (name == null || name.isEmpty() || attributeMap == null) {
            return null;
        }
        return getElementNode().getAttribute(extensionNamespace + ":" + name);
    }

    /**
     * Returns true when an attribute with a given extension namespace and name is
     * specified on this BPMN element, false otherwise.
     * 
     * @param extensionNamespace
     * @param attribute
     * @return
     */
    public boolean hasExtensionAttribute(String extensionNamespace, String attribute) {
        return hasAttribute(extensionNamespace + ":" + attribute);
    }


    /**
     * Returns the BPMNShape bounds.
     * 
     * @return
     * @throws BPMNMissingElementException
     */
    public BPMNBounds getBounds() throws BPMNMissingElementException {
        if (bounds == null) {
            // lazy loading of bounds from a given bpmnShape
            bounds = new BPMNBounds(this.bpmnShape, model);
        }
        return bounds;
    }
    
    public void setPosition(double x, double y) {
        try {
            this.getBounds().setPosition(x, y);
        } catch (BPMNMissingElementException e) {
            BPMNModel.error("Failed to update bounds position for element '"+this.getId() + "'");
        }
    }
    public void setDimension(double width, double height) {
        try {
            this.getBounds().setDimension( width, height);
        } catch (BPMNMissingElementException e) {
            BPMNModel.error("Failed to update bounds position for element '"+this.getId() + "'");
        }
    }

    /**
     * Updates the BPMN Shape bounds.
     * 
     * @param x
     * @param y
     * @param height
     * @param width
     * @return
     * @throws BPMNMissingElementException
     */
    public BPMNBounds setBounds(double x, double y, double width, double height) throws BPMNMissingElementException {
        // init bound if not yet loaded
        getBounds();

        // update bounds
        bounds.setDimension( width, height);
        bounds.setPosition(x, y);
       

        return bounds;
    }

    /**
     * Returns the BPMNShape element
     * 
     * @return
     */
    public Element getBpmnShape() {
        return bpmnShape;
    }

    /**
     * Set the BPMNShape element
     * 
     * @param bpmnShape
     */
    public void setBpmnShape(Element bpmnShape) {
        this.bpmnShape = bpmnShape;
    }
   
}
