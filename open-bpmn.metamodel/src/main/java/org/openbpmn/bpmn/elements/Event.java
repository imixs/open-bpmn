package org.openbpmn.bpmn.elements;

import java.util.LinkedHashSet;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Event extends BPMNElementNode {

    public final static double DEFAULT_WIDTH = 36.0;
    public final static double DEFAULT_HEIGHT = 36.0;
    public static final double LABEL_OFFSET = 0;

    protected Event(BPMNModel model, Element node, String type, BPMNProcess bpmnProcess) throws BPMNModelException {
        super(model, node, type, bpmnProcess);
    }

    @Override
    public double getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public double getDefaultHeigth() {
        return DEFAULT_HEIGHT;
    }

    /**
     * Creates a new Event Definition for this element
     * <p>
     * <bpmn2:terminateEventDefinition id="TerminateEventDefinition_1"/>
     * <p>
     * In case of a Signal Definition we need to add a reference to the first
     * existing Signal. If not yet defined we create one from scratch.
     * 
     * @throws BPMNMissingElementException
     */
    public void addEventDefinition(String type) throws BPMNModelException {

        if (this.getElementNode() == null) {
            throw new BPMNMissingElementException("Missing ElementNode!");
        }
        Element eventDefintion = model.createElement(BPMNNS.BPMN2, type);
        eventDefintion.setAttribute("id", BPMNModel.generateShortID(type));

        // in case of a Signal Defintiion we need to add a reference to the first
        // existing Signal.
        if (BPMNTypes.EVENT_DEFINITION_SIGNAL.equals(type)) {
            // do we have a signal ?
            if ( model.getSignals().size()==0) {
                // create a dummy signal
                model.addSignal("signal_1", "Signal 1");
            }
             // take the first one
             Signal signal= model.getSignals().iterator().next();
             eventDefintion.setAttribute("signalRef", signal.getId());
            
        }
        this.getElementNode().appendChild(eventDefintion);

    }

    /**
     * Deletes a Event Definition from this element. The defintion is identified by
     * its id
     * <p>
     * <bpmn2:terminateEventDefinition id="TerminateEventDefinition_1"/>
     * 
     * @throws BPMNMissingElementException
     */
    public void deleteEventDefinition(String id) throws BPMNModelException {
        this.deleteChild(id);
    }

    /**
     * Returns a list of EventDefintions associated with this element.
     * <p>
     * The return type is a list of eventDefinition Nodes
     * <p>
     * If the element has no EventDefinitions the method returns an empty list.
     * 
     * @return - list of EventDefinitions - can be empty if no EventDefinitions
     *         exist
     * @throws BPMNModelException
     */
    public Set<Element> getEventDefinitions() {

        Set<Element> result = new LinkedHashSet<Element>();

        if (this.getElementNode() == null) {
            return result;
        }

        // iterate over all childs
        NodeList childList = this.getElementNode().getChildNodes();
        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                // check the attribute bpmnElement
                if (!child.getNodeName().isEmpty() && child.hasAttributes()) {
                    String name = child.getNodeName();
                    // check if this is a EventDefinition
                    if (name.endsWith("EventDefinition")) {
                        result.add((Element) child);
                    }
                }
            }
        }
        return result;

    }

}
