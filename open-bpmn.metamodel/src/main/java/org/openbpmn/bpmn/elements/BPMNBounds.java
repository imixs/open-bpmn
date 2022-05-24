package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BPMNBounds {

    private Element bounds = null;
    private Node bpmnShape = null;
    protected BPMNModel model = null;

    /**
     * Creates the bounds out of the bpmn elemnet
     * <p>
     * <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>
     * </p>
     * 
     * @param width
     */
    public BPMNBounds(Node _bpmnShape, BPMNModel model) {
        this.model = model;
        this.bpmnShape = _bpmnShape;
        // find the dc:Bounds inside the given bpmnShape
        if (bpmnShape != null) {
            NodeList childList = bpmnShape.getChildNodes();
            for (int i = 0; i < childList.getLength(); i++) {
                Node child = childList.item(i);
                if ((BPMNNS.DC.prefix + ":Bounds").equals(child.getNodeName()) && child.hasAttributes()) {
                    bounds = (Element) child;
                    break;
                }
            }
        }

        // init bounds if not found
        if (bounds == null) {
            // <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>
            if (bpmnShape == null) {
                BPMNModel.getLogger().warning("bpmnShape missing!");
            } else {
                bounds = model.createElement(BPMNNS.DC, "Bounds");
                bpmnShape.appendChild(bounds);
            }
        }

    }

    public void updateBounds(double x, double y, double width, double height) {
        // update attributes
        bounds.setAttribute("x", "" + x);
        bounds.setAttribute("y", "" + y);
        bounds.setAttribute("width", "" + width);
        bounds.setAttribute("height", "" + height);
    }

    /**
     * Returns the Bounds element
     * 
     * @return
     */
    public Element getBounds() {
        return bounds;
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
