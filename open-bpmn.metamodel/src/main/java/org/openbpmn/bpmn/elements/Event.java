package org.openbpmn.bpmn.elements;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.BPMNTypes;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.openbpmn.bpmn.validation.BPMNValidationMarker;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Event extends BPMNElementNode {

    public final static double DEFAULT_WIDTH = 36.0;
    public final static double DEFAULT_HEIGHT = 36.0;
    public static final double LABEL_OFFSET = 3;

    protected Event(BPMNModel model, Element node, String type, BPMNProcess bpmnProcess) throws BPMNModelException {
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
        // in case of a Signal Definition we need to add a reference to the first
        // existing Signal.
        if (BPMNTypes.EVENT_DEFINITION_SIGNAL.equals(type)) {
            // do we have at least one signal ?
            if (model.getSignals().isEmpty()) {
                // create a dummy signal
                model.addSignal("signal_1", "Signal 1");
            }
            // take the first one
            Signal signal = model.getSignals().iterator().next();
            addEventDefinition(signal.getId(), type);
            return;
        }

        // in case of a Message Definition we need to add a reference to the first
        // existing Message.
        if (BPMNTypes.EVENT_DEFINITION_MESSAGE.equals(type)) {
            // do we have at least one message ?
            if (model.getMessages().isEmpty()) {
                // create a dummy message
                model.addMessage("message_1", "Message 1");
            }
            // take the first one
            Message message = model.getMessages().iterator().next();
            addEventDefinition(message.getId(), type);
        }
    }

    public void addEventDefinition(String id, String type) throws BPMNModelException {
        if (this.getElementNode() == null) {
            throw new BPMNMissingElementException("Missing ElementNode!");
        }
        Element eventDefinition = model.createElement(BPMNNS.BPMN2, type);
        eventDefinition.setAttribute("id", BPMNModel.generateShortID(type));

        if (BPMNTypes.EVENT_DEFINITION_SIGNAL.equals(type)) {
            eventDefinition.setAttribute("signalRef", id);
        }

        if (BPMNTypes.EVENT_DEFINITION_MESSAGE.equals(type)) {
            eventDefinition.setAttribute("messageRef", id);
        }
        this.getElementNode().appendChild(eventDefinition);

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
        this.deleteChildNodeByID(id);
    }

    /**
     * Returns a list of all EventDefinitions associated with this element.
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
        return getEventDefinitionsByType(null);

    }

    /**
     * Returns a list of all EventDefinitions of a given type, associated with this
     * element.
     * <p>
     * The return type is a list of eventDefinition Nodes of this type.
     * <p>
     * If the element has no EventDefinitions of this type, the method returns an
     * empty list.
     * <p>
     * E.g:
     * 
     * bpmn2:conditionalEventDefinition
     * 
     * @return - list of EventDefinitions - can be empty if no EventDefinitions
     *         exist or no EventDefintion is matching the type
     * @throws BPMNModelException
     */
    public Set<Element> getEventDefinitionsByType(String definitionType) {

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
                    // if we have a definitionType than we check the full name
                    if (definitionType != null && !definitionType.isEmpty()) {
                        if (name.endsWith(definitionType)) {
                            result.add((Element) child);
                        }
                    } else {
                        // We do not have a definition type, so we return all EventDefinitions
                        if (name.endsWith("EventDefinition")) {
                            result.add((Element) child);
                        }

                    }
                }
            }
        }
        return result;

    }

    /**
     * Validate Start, End, Catch and Throw event types
     */
    @Override
    public List<BPMNValidationMarker> validate() {
        resetValidation();
        // Start event?
        if (BPMNTypes.START_EVENT.equals(this.getType())) {

            if (this.getIngoingSequenceFlows().size() > 0) {
                this.addValidationMarker(new BPMNValidationMarker("Start Event",
                        "A Start Event may not have incoming Sequence Flows!", this.getId(),
                        BPMNValidationMarker.ErrorType.ERROR));
            }
            if (this.getOutgoingSequenceFlows().size() != 1) {
                this.addValidationMarker(new BPMNValidationMarker("Start Event",
                        "A Start Event must have exactly one outgoing  Sequence Flow!", this.getId(),
                        BPMNValidationMarker.ErrorType.ERROR));
            }
        }

        // END event?
        if (BPMNTypes.END_EVENT.equals(this.getType())) {

            if (this.getIngoingSequenceFlows().size() == 0) {
                this.addValidationMarker(new BPMNValidationMarker("End Event",
                        "An End Event must have at least one incoming Sequence Flow!", this.getId(),
                        BPMNValidationMarker.ErrorType.ERROR));
            }
            if (this.getOutgoingSequenceFlows().size() > 0) {
                this.addValidationMarker(new BPMNValidationMarker("End Event",
                        "An End Event must NOT have any outgoing  Sequence Flow!", this.getId(),
                        BPMNValidationMarker.ErrorType.ERROR));
            }
        }

        // Catch Event?
        if (BPMNTypes.CATCH_EVENT.equals(this.getType())) {
            if (this.getOutgoingSequenceFlows().size() == 0) {
                this.addValidationMarker(new BPMNValidationMarker("Catch Event",
                        "A Catch Event must have at least one outgoing  Sequence Flow!", this.getId(),
                        BPMNValidationMarker.ErrorType.ERROR));
            }
        }

        // Throw Event?
        if (BPMNTypes.THROW_EVENT.equals(this.getType())) {
            if (this.getIngoingSequenceFlows().size() == 0) {
                this.addValidationMarker(new BPMNValidationMarker("Throw Event",
                        "A Throw Event must have at least one ingoing  Sequence Flow!", this.getId(),
                        BPMNValidationMarker.ErrorType.ERROR));
            }
        }

        // validate if the definition reference for message definitions
        getEventDefinitionsByType(BPMNTypes.EVENT_DEFINITION_MESSAGE)
                .forEach(ed -> {
                    boolean hasMessage = model.getMessages().stream()
                            .anyMatch(m -> Objects.equals(
                                    ed.getAttribute("messageRef"),
                                    m.getId()));
                    if (!hasMessage) {
                        this.addValidationMarker(
                                new BPMNValidationMarker("Event",
                                        "A Event must have a corresponding Message!", this.getId(),
                                        BPMNValidationMarker.ErrorType.ERROR));
                    }
                });

        // validate if the definition reference for signal definitions
        getEventDefinitionsByType(BPMNTypes.EVENT_DEFINITION_SIGNAL)
                .forEach(ed -> {
                    boolean hasMessage = model.getSignals().stream()
                            .anyMatch(m -> Objects.equals(
                                    ed.getAttribute("signalRef"),
                                    m.getId()));
                    if (!hasMessage) {
                        this.addValidationMarker(
                                new BPMNValidationMarker("Event",
                                        "A Event must have a corresponding Signal!", this.getId(),
                                        BPMNValidationMarker.ErrorType.ERROR));
                    }
                });

        return this.getValidationMarkers();
    }

}
