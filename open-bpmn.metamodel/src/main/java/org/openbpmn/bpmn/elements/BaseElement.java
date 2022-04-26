package org.openbpmn.bpmn.elements;

import java.util.Map;

/**
 * The BaseElement is the abstract super class for most BPMN elements. It
 * provides a list of attributes with at least an id and a documentation. Other
 * element types can extend the attribute list.
 * 
 * @author rsoika
 */
public abstract class BaseElement {
    Map<String, String> attributes;

    public BaseElement() {
        super();
    }

    public BaseElement(Map<String, String> attributes) {
        super();
        this.attributes = attributes;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

}
