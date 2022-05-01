package org.openbpmn.bpmn.elements;

public class BPMNPoint {

    private float x;
    private float y;

    public BPMNPoint(float x, float y) {
        super();
        this.x = x;
        this.y = y;
    }
    public BPMNPoint(String x, String y) {
        this(Float.parseFloat(x),Float.parseFloat(y));
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

  
    @Override
    public String toString() {
        return "x=" + x + " y=" + y ;
    }

}
