package org.openbpmn.bpmn.elements;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Bounds {

    private float x;
    private float y;
    private float height;
    private float width;

    /**
     * Creates the bounds out of the bpmn elemnet
     * <p>
     * <dc:Bounds height="36.0" width="36.0" x="572.0" y="261.0"/>
     * </p>
     * 
     * @param width
     */
    public Bounds(Node bpmnShape) {

        // find the dc:Bounds inside the given bpmnShape
        if (bpmnShape != null) {
            NodeList childList = bpmnShape.getChildNodes();
            for (int i = 0; i < childList.getLength(); i++) {
                Node child = childList.item(i);
                if ("dc:Bounds".equals(child.getNodeName()) && child.hasAttributes()) {
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
                    break;
                }
            }
        }
    }

    public Bounds(float x, float y, float height, float width) {
        super();
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "x=" + x + " y=" + y + " width=" + width + " heigth=" + height;
    }

}
