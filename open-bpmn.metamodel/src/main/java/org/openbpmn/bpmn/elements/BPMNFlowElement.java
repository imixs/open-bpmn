package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.exceptions.BPMNInvalidReferenceException;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * The FlowElement is the abstract super class for all elements that can appear
 * in a Process flow, which are FlowNodes consisting of Activities ,
 * Choreography Activities, Gateways , and Events , Data Objects , Data
 * Associations , and Sequence Flows.
 * <p>
 * A BPMNFlowElement holds a reference to the Bounds of the element
 * 
 * @author rsoika
 *
 */
public abstract class BPMNFlowElement extends BPMNBaseElement {

    protected String type = null;
    protected BPMNBounds bounds = null;
    protected BPMNLabel label = null;
    protected Element bpmnShape = null;
 
    /**
     * Creates a new BPMNFlowElement
     * 
     * @param _type
     * @param node
     * @param model
     * @throws BPMNModelException
     */
    public BPMNFlowElement(BPMNModel model, Node node, String _type) throws BPMNModelException {
        super(model, node);
        this.type = _type;
        // find the BPMNShape element. If not defined create a new one
        if (model.getContext() != null) {
            bpmnShape = (Element) BPMNModel.findChildNodeByName(model.getContext().bpmnPlane,
                    BPMNNS.BPMNDI.prefix + ":BPMNShape", getId());
            if (bpmnShape == null) {
                // create shape element
                bpmnShape = buildBPMNShape();
                this.setBounds(0.0, 0.0, getDefaultWidth(), getDefaultHeigth());
                // create BPMNLabel 
                label=this.getLabel();
                if (label!=null) {
                    // set default position
                    BPMNPoint pos = this.getBounds().getPosition();
                    label.updateLocation(0.0,0.0);
                }
            }
        }
    }

    /**
     * Defines the default width of an BPMN element. Must be overwritten by
     * subclasses.
     * 
     * @return
     */
    public abstract double getDefaultWidth();

    /**
     * Defines the default height of an BPMN element. Must be overwritten by
     * subclasses.
     * 
     * @return
     */
    public abstract double getDefaultHeigth();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BPMNLabel getLabel() throws BPMNMissingElementException {

        if (isEvent() || isGateway()) {
            if (label == null) {
                // lazy loading of bounds from a given bpmnShape
                label = new BPMNLabel(model,this.bpmnShape);
            }
        }
        return label;
    }

    public BPMNBounds getBounds() throws BPMNMissingElementException {
        if (bounds == null) {
            // lazy loading of bounds from a given bpmnShape
            bounds = new BPMNBounds(this.bpmnShape, model);
        }
        return bounds;
    }

    public BPMNBounds setBounds(double x, double y, double height, double width) throws BPMNMissingElementException {
        // init bound if not yet loaded
        getBounds();

        // update bounds
        bounds.updateDimension( width, height);
        bounds.updateLocation(x, y);
       

        return bounds;
    }

    public Element getBpmnShape() {
        return bpmnShape;
    }

    public void setBpmnShape(Element bpmnShape) {
        this.bpmnShape = bpmnShape;
    }

    public boolean isEvent() {
        return (BPMNTypes.EVENT.equals(type)//
                || BPMNTypes.START_EVENT.equals(type) //
                || BPMNTypes.END_EVENT.equals(type) //
                || BPMNTypes.CATCH_EVENT.equals(type) //
                || BPMNTypes.THROW_EVENT.equals(type) //
        );
    }

    public boolean isGateway() {
        return (BPMNTypes.GATEWAY.equals(type) //
                || BPMNTypes.EXCLUSIVE_GATEWAY.equals(type)//
                || BPMNTypes.INCLUSIVE_GATEWAY.equals(type)//
                || BPMNTypes.COMPLEX_GATEWAY.equals(type)//
                || BPMNTypes.PARALLEL_GATEWAY.equals(type)//
        );
    }

    /**
     * Creates a BPMN shape node for this element
     * <p>
     * <bpmndi:BPMNShape id="BPMNShape_1" bpmnElement="StartEvent_1">
     * 
     * @throws BPMNMissingElementException
     */
    protected Element buildBPMNShape() throws BPMNModelException {
        if (bpmnShape != null) {
            BPMNModel.getLogger().warning("bpmnShape already exits");
            return bpmnShape;
        }
        if (model.getContext().bpmnPlane == null) {
            throw new BPMNMissingElementException("Missing bpmnPlane in current model context");
        }
        if (this.getId() != null) {
            bpmnShape = model.createElement(BPMNNS.BPMNDI, "BPMNShape");
            bpmnShape.setAttribute("id", BPMNModel.generateShortID("BPMNShape"));
            bpmnShape.setAttribute("bpmnElement", this.getId());
            model.getContext().bpmnPlane.appendChild(bpmnShape);
            return bpmnShape;
        } else {
            throw new BPMNInvalidReferenceException("Missing ID attribute");
        }
    }
}
