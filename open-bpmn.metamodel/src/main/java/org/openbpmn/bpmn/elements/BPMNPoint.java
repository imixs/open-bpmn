package org.openbpmn.bpmn.elements;

public class BPMNPoint {

    private double x;
    private double y;

    public BPMNPoint(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public BPMNPoint(String x, String y) {
        this(Float.parseFloat(x), Float.parseFloat(y));
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

    @Override
    public String toString() {
        return "x=" + x + " y=" + y;
    }

}
