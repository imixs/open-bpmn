package org.openbpmn.bpmn.elements.core;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The BPMNBounds object represents bounds of a Shape
 * <p>
 * e.g.: 
 * <p>{@code  
 * <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>
 * }
 * </p>
 * A dc:Bounds element is part of a bpmndi:BPMNShape or bpmndi:BPMNLabel
 * 
 * @author rsoika
 *
 */
public class BPMNBounds {

    private Element elementNode = null;

    /**
     * Creates the bounds out of the bpmn elemnet
     * <p>
     * <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>
     * </p>
     * 
     * @param width
     * @throws BPMNMissingElementException
     */
    public BPMNBounds(Node _bpmndiElement, BPMNModel model) throws BPMNMissingElementException {
        Node bpmndiElement = _bpmndiElement;
        if (bpmndiElement == null) {
            throw new BPMNMissingElementException("Missing bpmndi element ");
        }

        // find the dc:Bounds inside the given bpmndi elmeent
        NodeList childList = bpmndiElement.getChildNodes();
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
            bpmndiElement.appendChild(elementNode);
        }

    }

    /**
     * Returns the Bounds element
     * 
     * @return
     */
    public Element getElementNode() {
        return elementNode;
    }

    public void setDimension(double width, double height) {
        // update attributes
        elementNode.setAttribute("width", "" + width);
        elementNode.setAttribute("height", "" + height);
    }

    public BPMNDimension getDimension() {
        return new BPMNDimension(elementNode.getAttribute("width"), elementNode.getAttribute("height"));
    }

    /**
     * Updates the x y position
     * 
     * @param x
     * @param y
     */
    public void setPosition(double x, double y) {
        elementNode.setAttribute("x", "" + x);
        elementNode.setAttribute("y", "" + y);
    }
    public void setPosition(BPMNPoint point) {
        elementNode.setAttribute("x", "" + point.getX());
        elementNode.setAttribute("y", "" + point.getY());
    }

    public BPMNPoint getPosition() {
        return new BPMNPoint(elementNode.getAttribute("x"), elementNode.getAttribute("y"));
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
        return new BPMNPoint(_x, _y);
    }

    @Override
    public String toString() {
        return "x=" + elementNode.getAttribute("x") + " y=" + elementNode.getAttribute("y") + " width="
                + elementNode.getAttribute("width") + " heigth=" + elementNode.getAttribute("height");
    }

    /**
     * This method returns true, if a given BPMN Point is within this BPMNBounds
     * @param point
     * @return
     */
    public boolean containsPoint(BPMNPoint point) {
        
        double x = this.getPosition().getX();
        double y = this.getPosition().getY();
        double w = this.getDimension().getWidth();
        double h = this.getDimension().getHeight();
        // is the point within this dimensions?
        if (point.getX()>=x && point.getX()<=x+w 
                && point.getY()>=y && point.getY()<=y+h) {
            return true;
        }
        return false;
    }

}
