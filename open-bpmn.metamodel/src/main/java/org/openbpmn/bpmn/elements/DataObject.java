package org.openbpmn.bpmn.elements;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;

/**
 * A DataObject is the primary construct for modeling data within the Process
 * flow.
 * <p>
 * Data Object elements MUST be contained within Process or Sub- Process
 * elements. Data Object elements are visually displayed on a Process diagram.
 * Data Object References are a way to reuse Data Objects in the same diagram.
 * They can specify different states of the same Data Object at different points
 * in a Process. Data Object Reference cannot specify item definitions, and Data
 * Objects cannot specify states. The names of Data Object References are
 * derived by concatenating the name of the referenced Data Data Object the
 * state of the Data Object Reference in square brackets as follows: <Data
 * Object Name> [ <Data Object Reference State> ].
 * 
 * 
 * @author rsoika
 *
 */
public class DataObject extends BPMNElementNode {

    public final static double DEFAULT_WIDTH = 35.0;
    public final static double DEFAULT_HEIGHT = 50.0;
    public static final double LABEL_OFFSET = 5;

    protected DataObject(BPMNModel model, Element node, String type, BPMNProcess bpmnProcess)
            throws BPMNModelException {
        super(model, node, type, bpmnProcess);
    }

    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public double getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }
}
