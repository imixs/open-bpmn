package org.openbpmn.bpmn.elements.core;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A BPMNLabel is accociated with a BPMNFlowElement which can provide
 * independent bounds and styles
 * 
 * @author rsoika
 *
 */
public class BPMNLabel {

    public final static double DEFAULT_WIDTH = 100.0;
    public final static double DEFAULT_HEIGHT = 24.0;

    protected BPMNBounds bounds = null;
    private Element label = null;
    private Node bpmnShape = null;
    protected BPMNModel model = null;

    /**
     * Creates the bounds out of the bpmn label
     * <p>
     * <bpmndi:BPMNLabel id="BPMNLabel_3" labelStyle="BPMNLabelStyle_1">
     * <dc:Bounds height="14.0" width="66.0" x="485.0" y="136.0"/>
     * </bpmndi:BPMNLabel>
     * </p>
     * 
     * @param width
     * @throws BPMNMissingElementException
     */
    public BPMNLabel(BPMNModel model, Node _bpmnShape) throws BPMNMissingElementException {
        this.model = model;
        this.bpmnShape = _bpmnShape;

        if (this.bpmnShape == null) {
            throw new BPMNMissingElementException("Missing bpmnShape ");
        }

        // find the bpmndi:BPMNLabel inside the given bpmnShape
        NodeList childList = bpmnShape.getChildNodes();
        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);
            if ((BPMNNS.BPMNDI.prefix + ":BPMNLabel").equals(child.getNodeName())) {
                label = (Element) child;
                // find the dc:Bounds element of the label...

                bounds = new BPMNBounds(label, model);

            }
        }

        // if we still have no label we create an empty one
        // <bpmndi:BPMNLabel id="BPMNLabel_1" labelStyle="BPMNLabelStyle_1">
        // <dc:Bounds height="14.0" width="69.0" x="84.0" y="136.0"/>
        // </bpmndi:BPMNLabel>
        // init label if not found
        if (label == null) {
            // <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>
            label = model.createElement(BPMNNS.BPMNDI, "BPMNLabel");
            label.setAttribute("id", BPMNModel.generateShortID("BPMNLabel"));
            bpmnShape.appendChild(label);
            bounds = new BPMNBounds(label, model);
        }
    }

    /**
     * Returns the BPMNShape bounds.
     * 
     * @return
     * @throws BPMNMissingElementException
     */
    public BPMNBounds getBounds() throws BPMNMissingElementException {
        return bounds;
    }

    /**
     * Updates the x y position
     * 
     * @param x
     * @param y
     */
    public void updateLocation(double x, double y) {
        bounds.setPosition(x, y);
    }

    public void updateDimension(double width, double height) {
        // update attributes
        bounds.setDimension(width, height);
    }

    public BPMNPoint getPosition() {
        return bounds.getPosition();
    }

    public BPMNDimension getDimension() {
        return bounds.getDimension();
    }

    /**
     * Returns the center of the element
     * 
     * @return
     */
    public BPMNPoint getCenter() {
        BPMNPoint point = getPosition();
        BPMNDimension size = getDimension();
        Double _x = point.getX() + (size.getWidth() * 0.5);
        Double _y = point.getY() + (size.getHeight() * 0.5);
        return new BPMNPoint(_x.intValue(), _y.intValue());
    }

}
