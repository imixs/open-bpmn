package org.openbpmn.bpmn.elements.core;

import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The AbstractBPMNElement is the abstract super class for all BPMN Nodes and
 * Edges. It provides a list of attributes with at least an id. Other element
 * types can extend the attribute list.
 * <p>
 * 
 * @author rsoika
 */
public abstract class AbstractBPMNElement {
    protected NamedNodeMap attributeMap = null;
    protected Element elementNode = null;
    protected BPMNModel model = null;

    /**
     * Create a new BPMN Base Element. The constructor expects a model instnace and
     * a node.
     * 
     * @param node
     * @param model
     */
    public AbstractBPMNElement(BPMNModel model, Element node) {
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
     * Returns the ID of the element
     * 
     * @return
     */
    public String getId() {
        return getAttribute("id");
    }

    /**
     * This method returns the corresponding BPMNProcess ID for this Element.
     * 
     * @param bpmynElement
     * @return
     */
    public String getProcessId() {
        // get the parent node of the element
        Element parent = (Element) getElementNode().getParentNode();
        return parent.getAttribute("id");
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
     * Set the new childNode with a given content for this element in a CDATA
     * element.
     * <p>
     * The id is optional and is only set if not null
     * 
     * @param nodeName name of the new child node
     * @param content  the content
     * @param id       optional id
     */
    public Element setChildNodeContent(String nodeName, String content, String id) {
        Element childNode = null;
        // load the element
        Set<Element> elementList = BPMNModel.findChildNodesByName(elementNode, BPMNNS.BPMN2.prefix + ":" + nodeName);
        if (elementList.size() == 0) {
            // create new node
            childNode = model.createElement(BPMNNS.BPMN2, nodeName);
            if (id != null && !id.isEmpty()) {
                childNode.setAttribute("id", id);
            }
            elementNode.appendChild(childNode);
        } else {
            // get the first one and remove old values
            childNode = elementList.iterator().next();
            // remove old child nodes of the the node...
            NodeList subChildList = childNode.getChildNodes();
            for (int i = 0; i < subChildList.getLength(); i++) {
                Node child = subChildList.item(i);
                childNode.removeChild(child);
            }
        }
        return childNode;
    }

    /**
     * Returns the content of a given childNode as a string
     * 
     * @return String - can be empty
     */
    public String getChildNodeContent(String nodeName) {
        Element childNode = null;
        // lazy loading of documentation element
        Set<Element> elementList = BPMNModel.findChildNodesByName(elementNode, BPMNNS.BPMN2.prefix + ":" + nodeName);
        if (elementList.size() > 0) {
            // get the first one and update the value only
            childNode = elementList.iterator().next();
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

}
