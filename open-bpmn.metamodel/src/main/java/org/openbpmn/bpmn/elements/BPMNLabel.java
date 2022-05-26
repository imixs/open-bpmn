package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BPMNLabel {

    private Element bounds = null;
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
                NodeList labelChildList = label.getChildNodes();
                for (int j = 0; j < labelChildList.getLength(); j++) {
                    Node labelchild = labelChildList.item(j);
                    if ((BPMNNS.DC.prefix + ":Bounds").equals(labelchild.getNodeName()) && labelchild.hasAttributes()) {
                        bounds = (Element) labelchild;
                        break;
                    }
                    if (bounds != null) {
                        break;
                    }
                }

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
            bounds = model.createElement(BPMNNS.DC, "Bounds");
            label.appendChild(bounds);
        }
    }

    /**
     * Updates the x y position
     * 
     * @param x
     * @param y
     */
    public void updateLocation(double x, double y) {
        bounds.setAttribute("x", "" + x);
        bounds.setAttribute("y", "" + y);
    }

    public void updateDimension( double width, double height) {
        // update attributes
        bounds.setAttribute("width", "" + width);
        bounds.setAttribute("height", "" + height);
    }
    
    public BPMNPoint getPosition() {
        return new BPMNPoint(bounds.getAttribute("x"), bounds.getAttribute("y"));
    }

    public BPMNDimension getDimension() {
        return new BPMNDimension(bounds.getAttribute("width"), bounds.getAttribute("height"));
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

    @Override
    public String toString() {
        return "x=" + bounds.getAttribute("x") + " y=" + bounds.getAttribute("y") + " width="
                + bounds.getAttribute("width") + " heigth=" + bounds.getAttribute("height");
    }

}
