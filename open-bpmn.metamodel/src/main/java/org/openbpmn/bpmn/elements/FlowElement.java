package org.openbpmn.bpmn.elements;

import java.util.Map;

/**
 * The FlowElement is the abstract super class for all elements that can appear
 * in a Process flow, which are FlowNodes consisting of Activities ,
 * Choreography Activities, Gateways , and Events , Data Objects , Data
 * Associations , and Sequence Flows.
 * 
 * @author rsoika
 *
 */
public abstract class FlowElement extends BaseElement {

    protected String type = null;

    public FlowElement(String type, Map<String, String> attributes) {
        super(attributes);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
