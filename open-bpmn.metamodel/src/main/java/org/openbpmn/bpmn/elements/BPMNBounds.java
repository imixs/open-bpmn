package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BPMNBounds {

    private double x;
    private double y;
    private double height;
    private double width;

    private Node bounds = null;
    private Node bpmnShape = null;

    /**
     * Creates the bounds out of the bpmn elemnet
     * <p>
     * <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>
     * </p>
     * 
     * @param width
     */
    public BPMNBounds(Node _bpmnShape) {
        this.bpmnShape = _bpmnShape;
        // find the dc:Bounds inside the given bpmnShape
        if (bpmnShape != null) {
            NodeList childList = bpmnShape.getChildNodes();
            for (int i = 0; i < childList.getLength(); i++) {
                Node child = childList.item(i);
                if ((BPMNModel.DC_NS + ":Bounds").equals(child.getNodeName()) && child.hasAttributes()) {
                    NamedNodeMap nodeMap = child.getAttributes();
                    for (int j = 0; j < nodeMap.getLength(); j++) {
                        Node attr = nodeMap.item(j);
                        if ("x".equals(attr.getNodeName())) {
                            x = Float.parseFloat(attr.getNodeValue());
                        }
                        if ("y".equals(attr.getNodeName())) {
                            y = Float.parseFloat(attr.getNodeValue());
                        }
                        if ("height".equals(attr.getNodeName())) {
                            height = Float.parseFloat(attr.getNodeValue());
                        }
                        if ("width".equals(attr.getNodeName())) {
                            width = Float.parseFloat(attr.getNodeValue());
                        }
                    }
                    bounds = child;
                    break;
                }
            }
        }
    }

    public void updateBounds(double x, double y, double width, double height) {
        System.out.println("....Bin in update boudns");
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        if (bounds == null) {
            System.out.println("....Bounds sin noch null - ich bua ewele");
            // <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>

            if (bpmnShape == null) {
                System.out.println("....leider ist bpmnShape null");
            }
            bounds = bpmnShape.getOwnerDocument().createElement(BPMNModel.DC_NS + ":Bounds");
            bpmnShape.appendChild(bounds);
        }
        // update attributes
        if (bounds instanceof org.w3c.dom.Element) {
            System.out.println("....So jetzt gehts loss");

            org.w3c.dom.Element e = (Element) bounds;
            e.setAttribute("x", "" + getX());

            e.setAttribute("y", "" + getY());
            e.setAttribute("width", "" + getWidth());
            e.setAttribute("height", "" + getHeight());
        }

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "x=" + x + " y=" + y + " width=" + width + " heigth=" + height;
    }

}
