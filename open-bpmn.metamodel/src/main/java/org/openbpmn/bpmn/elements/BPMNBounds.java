package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BPMNBounds {

    private Element elementNode = null;
    private Node bpmnShape = null;
    protected BPMNModel model = null;

    /**
     * Creates the bounds out of the bpmn elemnet
     * <p>
     * <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>
     * </p>
     *  
     * @param width
     * @throws BPMNMissingElementException
     */
    public BPMNBounds(Node _bpmnShape, BPMNModel model) throws BPMNMissingElementException {
        this.model = model;
        this.bpmnShape = _bpmnShape;

        if (this.bpmnShape == null) {
            throw new BPMNMissingElementException("Missing bpmnShape ");
        }

        // find the dc:Bounds inside the given bpmnShape
        NodeList childList = bpmnShape.getChildNodes();
        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);
            if ((BPMNNS.DC.prefix + ":Bounds").equals(child.getNodeName()) && child.hasAttributes()) {
                elementNode = (Element) child;
                break;
            }
        }

        // init bounds if not found
        if (elementNode == null) {
            // <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>
            elementNode = model.createElement(BPMNNS.DC, "Bounds");
            bpmnShape.appendChild(elementNode);
        }

    }

    public void updateDimension( double width, double height) {
        // update attributes
        elementNode.setAttribute("width", "" + width);
        elementNode.setAttribute("height", "" + height);
    }

    /**
     * Updates the x y position
     * 
     * @param x
     * @param y
     */
    public void updateLocation(double x, double y) {
        elementNode.setAttribute("x", "" + x);
        elementNode.setAttribute("y", "" + y);
    }

    /**
     * Returns the Bounds element
     * 
     * @return
     */
    public Element getElementNode() {
        return elementNode;
    }

    public BPMNPoint getPosition() {
        return new BPMNPoint(elementNode.getAttribute("x"), elementNode.getAttribute("y"));
    }

    public BPMNDimension getDimension() {
        return new BPMNDimension(elementNode.getAttribute("width"), elementNode.getAttribute("height"));
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
        return "x=" + elementNode.getAttribute("x") + " y=" + elementNode.getAttribute("y") + " width="
                + elementNode.getAttribute("width") + " heigth=" + elementNode.getAttribute("height");
    }

}
