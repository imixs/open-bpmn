package org.openbpmn.bpmn.elements.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * BPMNElement is the abstract super class for all BPMN elements including
 * Nodes, Edges and processes. It provides a list of attributes with at least an
 * id. Other element types can extend the attribute list.
 * <p>
 * 
 * @author rsoika
 */
public abstract class BPMNElement {
    protected NamedNodeMap attributeMap = null;
    protected Element elementNode = null;
    protected BPMNModel model = null;
    protected Map<String, Element> childNodes = null;
    protected Map<String, Object> args = null;

    /**
     * Create a new BPMN Base Element. The constructor expects a model instnace and
     * a node.
     * 
     * @param node
     * @param model
     */
    public BPMNElement(BPMNModel model, Element node) {
        super();
        this.model = model;
        this.elementNode = node;
        if (this.elementNode.hasAttributes()) {
            // get attributes names and values
            this.attributeMap = this.elementNode.getAttributes();
        }
        childNodes = new HashMap<String, Element>();
        args = new HashMap<String, Object>();

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
     * Returns the Documentation
     * 
     * @return String - can be empty
     */
    public String getDocumentation() {
        return this.getChildNodeContent(BPMNNS.BPMN2, "documentation");
    }

    /**
     * Set the new documentation content for this element.
     * 
     * @param content
     */
    public void setDocumentation(String content) {
        this.setChildNodeContent(BPMNNS.BPMN2, "documentation", content, true);

        // if we have a file:// link than we create an additional open-bpmn attribute
        Element childElement = this.getChildNode(BPMNNS.BPMN2, "documentation");
        if (childElement != null) {
            if (content.startsWith("file://")) {
                childElement.setAttribute("open-bpmn:file-link", content);
            } else {
                childElement.removeAttribute("open-bpmn:file-link");
            }
        }
    }

    /**
     * Returns an optional argument map which can be used to hold additional
     * arguments
     * 
     * @return the value of the '<em>Args</em>' map
     */
    public Map<String, Object> getArgs() {
        return args;
    }

    /**
     * Set the content for this element. The content can be set as normal text
     * content or as a CDATA element. If the value is null or empty the method will
     * create an empty tag.
     * <p>
     * The method operates directly on the dom-tree. If the content has not changed,
     * the method
     * will not update the element.
     * <p>
     * The method returns the new generated child node
     * 
     * @param nodeName - name of the new child node
     * @param content  - the content
     * @param cdata    - if true the a CDATA element will be created
     * @return child node
     */
    public Element setChildNodeContent(BPMNNS ns, String nodeName, String content, boolean cdata) {

        // test if the child node was already loaded (lazy loading)
        Element childNode = loadOrCreateChildNode(ns, nodeName);
        if (childNode != null) {

            // test if the content has changed
            String oldContent = getChildNodeContent(ns, nodeName);
            if ((content == null && oldContent == null) || content.equals(oldContent)) {
                // no update needed
                return childNode;
            }

            // remove old sub_child nodes of this childNode...
            while (childNode.hasChildNodes()) {
                childNode.removeChild(childNode.getFirstChild());
            }

            // we only create a text node if the content is not null
            if (content != null && !content.isEmpty()) {
                // create new cdata section for this child node and add the content....
                if (cdata) {
                    CDATASection cdataSection = getDoc().createCDATASection(content);
                    childNode.appendChild(cdataSection);
                } else {
                    // normal text node
                    childNode.setTextContent(content);
                }
            }
            return childNode;
        }
        return null;
    }

    /**
     * This helper method returns the content of a given childNode as a string.
     *
     * @return String - can be empty
     */
    public String getChildNodeContent(BPMNNS ns, String nodeName) {
        // lazy loading child node
        Element childNode = loadOrCreateChildNode(ns, nodeName);

        Node cdata = findCDATA(childNode);
        if (cdata != null) {
            return cdata.getNodeValue();
        } else {
            // normal text node
            return childNode.getTextContent();
        }

    }

    /**
     * Helper method that finds an optional CDATA node within the current element
     * content.
     * 
     * @param element
     * @return
     */
    private Node findCDATA(Element element) {
        // search CDATA node
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node instanceof CDATASection) {
                return (CDATASection) node;

            }
        }
        return null;
    }

    /**
     * This helper method returns true if a childNode with the given node name
     * exits.
     *
     * @return boolean - true if a child node exits
     */
    public boolean hasChildNode(BPMNNS ns, String nodeName) {
        // lazy loading child node
        return loadChildNode(ns, nodeName) != null;
    }

    /**
     * This helper method returns a childNode by name. If no child node with the
     * name exists, the method creates a new empty node. The method expects the full
     * node name including the namespace
     * <p>
     * The method uses a lazy loading mechanism and a cache to access the same node
     * faster
     * 
     * @return String - can be empty
     */
    private Element loadOrCreateChildNode(BPMNNS ns, String nodeName) {

        // test if the child node was already loaded (la7y loading)
        Element childNode = childNodes.get(nodeName);
        if (childNode == null) {

            // lazy loading of documentation element
            Set<Element> elementList = model.findChildNodesByName(elementNode, ns, nodeName);
            if (elementList.size() > 0) {
                // get the first one and update the value only
                childNode = elementList.iterator().next();

            } else {
                // create a new childnode....
                // create new node
                childNode = model.createElement(ns, nodeName);
                childNode.setAttribute("id", BPMNModel.generateShortID(nodeName));
                elementNode.appendChild(childNode);
            }
            // put into cache
            childNodes.put(nodeName, childNode);
        }
        return childNode;
    }

    /**
     * This helper method returns a childNode by name. If no child node with the
     * name exists, the method return null.
     * <p>
     * The method uses a lazy loading mechanism and a cache to access the same node
     * faster
     * 
     * @return String - can be empty
     */
    private Element loadChildNode(BPMNNS ns, String nodeName) {

        // test if the child node was already loaded (la7y loading)
        Element childNode = childNodes.get(nodeName);
        if (childNode == null) {

            // lazy loading of child node element
            Set<Element> elementList = model.findChildNodesByName(elementNode,
                    ns, nodeName);
            if (elementList.size() > 0) {
                // get the first one and update the value only
                childNode = elementList.iterator().next();
            }
            // put into cache
            childNodes.put(nodeName, childNode);
        }
        return childNode;
    }

    /**
     * Returns the value of a given attribute by name.
     * <p>
     * The method operates directly on the attributeMap loaded in the constructor.
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
     * Set the value of a given attribute by name. If the value is null, the
     * attribute will be removed.
     * <p>
     * The method operates directly on the attributeMap loaded in the constructor.
     * 
     * @param name
     * @return
     */
    public void setAttribute(String name, String value) {
        if (name == null || name.isEmpty() || attributeMap == null) {
            return;
        }
        // if the value is null we remove the attibute
        if (value == null) {
            this.getElementNode().removeAttribute(name);
        } else {
            this.getElementNode().setAttribute(name, value);
        }
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
     * This method removes a specific attribute.
     * 
     * @param extensionNamespace
     * @param attribute
     * @param value
     */
    public void removeAttribute(String attribute) {
        this.getElementNode().removeAttribute(attribute);
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
     * Returns the corresponding child element node.
     * 
     * If no child node with the name exists, the method return null.
     * 
     * @return
     */
    public Element getChildNode(BPMNNS ns, String nodeName) {
        return this.loadChildNode(ns, nodeName);
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
     * This method removes a specific extension attribute. The extensionNamespace
     * defines the attribute prefix within the BPMNElement:
     * 
     * @param extensionNamespace
     * @param attribute
     * @param value
     */
    public void removeExtensionAttribute(String extensionNamespace, String attribute) {
        this.getElementNode().removeAttribute(extensionNamespace + ":" + attribute);
    }

    /**
     * Returns the value of a given extension attribute by name. If empty or not
     * set, the method returns null.
     * <p>
     * 
     * @param name
     * @return attribute value
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
     * Deletes a Child Element from this element. The node is identified by its id
     * <p>
     * <bpmn2:terminateEventDefinition id="TerminateEventDefinition_1"/>
     * 
     * @throws BPMNMissingElementException
     */
    public void deleteChildNodeByID(String id) throws BPMNModelException {

        if (this.getElementNode() == null) {
            throw new BPMNMissingElementException("Missing ElementNode!");
        }

        // iterate over all child nodes
        NodeList childList = this.getElementNode().getChildNodes();
        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                // check the attribute bpmnElement
                if (!child.getNodeName().isEmpty() && child.hasAttributes()) {

                    String defID = ((Element) child).getAttribute("id");
                    if (id.equals(defID)) {
                        this.getElementNode().removeChild(child);
                    }

                }
            }
        }

    }

    /**
     * Deletes each child node that has the specified local.
     * 
     * @param localName
     * @throws BPMNModelException
     */
    public void deleteChildNodesByName(String localName) throws BPMNModelException {
        if (this.getElementNode() == null) {
            throw new BPMNMissingElementException("Missing ElementNode!");
        }
        NodeList childNodes = this.getElementNode().getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getLocalName().equals(localName)) {
                // clear cache
                this.childNodes.remove(childNode.getLocalName());
                this.getElementNode().removeChild(childNode);
            }
        }
    }
}
