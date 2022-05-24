package org.openbpmn.bpmn.elements;

public class BPMNDimension {

    private double width;
    private double height;

    public BPMNDimension(double width, double height) {
        super();
        this.width =width;
        this.height = height;
    }

    public BPMNDimension(String width, String height) {
        this(0.0, 0.0);
        try {
            this.setWidth(Float.parseFloat(width));
            this.setHeight(Float.parseFloat(height));
        } catch (NumberFormatException e) {
           // no op
        }
    }
    


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "width=" + width + " height=" + height;
    }

}
