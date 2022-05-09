package org.openbpmn.bpmn.elements;

import org.w3c.dom.Node;

/**
 * The FlowElement is the abstract super class for all elements that can appear
 * in a Process flow, which are FlowNodes consisting of Activities ,
 * Choreography Activities, Gateways , and Events , Data Objects , Data
 * Associations , and Sequence Flows.
 * <p>
 * A BPMNFlowElement holds a reference to the bpmnShape element 
 * 
 * @author rsoika
 *
 */
public abstract class BPMNFlowElement extends BPMNBaseElement {

    protected String type = null;
    protected Node bpmnShape = null;
    protected BPMNBounds bounds = null;


    
    public BPMNFlowElement(String _type, Node node) {
        super(node);
        this.type = _type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Node getBpmnShape() {
        return bpmnShape;
    }

    public void setBpmnShape(Node bpmnShape) {
        this.bpmnShape = bpmnShape;
    }
    
    public BPMNBounds getBounds() {
        if (bounds == null) {
            // lazy loading of bounds from a given bpmnShape
            bounds = new BPMNBounds(this.bpmnShape);
        }
        return bounds;
    }
    
    public BPMNBounds setBounds(float x, float y, float height, float width) {
        // init bound if not yet loaded
        getBounds();
        
        // update bounds
        bounds.updateBounds(x, y, width, height);
        
        return bounds;
    }
    
}
