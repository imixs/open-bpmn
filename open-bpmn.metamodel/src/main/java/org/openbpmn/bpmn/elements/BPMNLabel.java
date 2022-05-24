package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BPMNLabel {

    private double x;
    private double y;
    private double height;
    private double width;

    private Node bounds = null;
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
     */
    public BPMNLabel(Node _bpmnShape, BPMNModel model) {
        this.model = model;
        this.bpmnShape = _bpmnShape;
        // find the bpmndi:BPMNLabel inside the given bpmnShape
        if (bpmnShape != null) {
            NodeList childList = bpmnShape.getChildNodes();
            for (int i = 0; i < childList.getLength(); i++) {
                Node child = childList.item(i);
                if ((BPMNNS.BPMNDI.prefix + ":BPMNLabel").equals(child.getNodeName())) {

                    // find the dc:Bounds element of the label...
                    NodeList labelChildList = child.getChildNodes();
                    for (int j = 0; j < labelChildList.getLength(); j++) {
                        Node labelchild = labelChildList.item(j);
                        if ((BPMNNS.DC.prefix + ":Bounds").equals(labelchild.getNodeName()) && labelchild.hasAttributes()) {

                            NamedNodeMap nodeMap = labelchild.getAttributes();
                            for (int k = 0; k < nodeMap.getLength(); k++) {
                                Node attr = nodeMap.item(k);
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
                            bounds = labelchild;
                            break;
                        }
                        if (bounds != null) {
                            break;
                        }
                    }
                    
                }
            }
        }
    }

  

    /**
     * Updates the x y position
     * 
     * @param x
     * @param y
     */
    public void updateLocation(double x, double y) {
        setX(x);
        setY(y);
        if (bounds == null) {
            // <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>
            if (bpmnShape == null) {
                BPMNModel.getLogger().warning("bpmnShape missing!");
            } else {
                // create bpmnLabel and Bounds
                Element bpmnLabel = model.createElement(BPMNNS.BPMNDI, "BPMNLabel");
                bpmnShape.appendChild(bpmnLabel);
                // create new bounds element and append it to the BPMNLabel
                bounds = model.createElement(BPMNNS.DC, "Bounds");
                bpmnLabel.appendChild(bounds);
            }
        }
        // update attributes
        if (bounds instanceof org.w3c.dom.Element) {
            org.w3c.dom.Element e = (Element) bounds;
            e.setAttribute("x", "" + getX());
            e.setAttribute("y", "" + getY());
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

    /**
     * Returns the center of the element
     * 
     * @return
     */
    public BPMNPoint getCenter() {
        Double _x = x + (width * 0.5);
        Double _y = y + (height * 0.5);
        return new BPMNPoint(_x.intValue(), _y.intValue());
    }

    @Override
    public String toString() {
        return "x=" + x + " y=" + y + " width=" + width + " heigth=" + height;
    }

}
