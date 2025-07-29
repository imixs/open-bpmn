package org.openbpmn.bpmn.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Manages the correct order of BPMN elements according to the XSD schema.
 * Based on the BPMN 2.0 inheritance hierarchy.
 */
public class BPMNElementOrder {
    private static Logger logger = Logger.getLogger(BPMNElementOrder.class.getName());

    /**
     * Defines the general order of all BPMN elements.
     * This list is sorted according to the XSD inheritance hierarchy:
     * tBaseElement -> tFlowElement ->
     * tFlowNode -> specific element types.
     */
    private static final List<String> ELEMENT_ORDER = Arrays.asList(
            // tBaseElement (base for all BPMN-Elements)
            "documentation",
            "extensionElements",

            // tFlowElement
            "auditing",
            "monitoring",
            "categoryValueRef",

            // tFlowNode
            "incoming",
            "outgoing",

            // tEvent
            "property",

            // tActivity (parallel to tEvent)
            "ioSpecification",
            "dataInputAssociation",
            "dataOutputAssociation",
            "resourceRole",
            "loopCharacteristics",

            // tCatchEvent (StartEvent, IntermediateCatchEvent, etc.)
            "dataOutput",
            "dataOutputAssociation",
            "outputSet",

            // tThrowEvent (EndEvent, IntermediateThrowEvent, etc.)
            "dataInput",
            "dataInputAssociation",
            "inputSet",

            // EventDefinitions (for all Event-Typs)
            "eventDefinition",
            "cancelEventDefinition",
            "compensateEventDefinition",
            "conditionalEventDefinition",
            "errorEventDefinition",
            "escalationEventDefinition",
            "linkEventDefinition",
            "messageEventDefinition",
            "signalEventDefinition",
            "terminateEventDefinition",
            "timerEventDefinition",

            // SequenceFlow specific
            "conditionExpression",

            // SubProcess/Activity specific
            "laneSet",
            "flowElement",
            "artifact",
            "correlationSubscription",
            "supports",

            // Task specific
            "script",
            "rendering",

            // Gateway specific
            "activationCondition",

            // Collaboration/Process specific
            "participant",
            "messageFlow",
            "conversationNode",
            "conversationAssociation",
            "participantAssociation",
            "messageFlowAssociation",
            "correlationKey",
            "choreographyRef",
            "conversationLink");

    /**
     * Returns the position of an element in the defined order.
     * 
     * @param elementName Name of the element (without namespace)
     * @return Position in the order or -1 if not found
     */
    public static int getElementPosition(String elementName) {
        return ELEMENT_ORDER.indexOf(elementName);
    }

    /**
     * Checks if one element should be in front of another element.
     * 
     * @param element1 First element
     * @param element2 Second element
     * @return true if element1 should be in front of element2
     */
    public static boolean shouldComeBefore(String element1, String element2) {
        int pos1 = getElementPosition(element1);
        int pos2 = getElementPosition(element2);

        // unknown Element add to end
        if (pos1 == -1)
            return false;
        if (pos2 == -1)
            return true;

        return pos1 < pos2;
    }

    /**
     * Find the first existing child element before which the new element should be
     * inserted.
     * 
     * @param parentElement  The parent element
     * @param newElementName Name of the element to be inserted
     * @return The element before which the insertion should occur, or null if at
     *         the end.
     */
    public static org.w3c.dom.Element findInsertPosition(org.w3c.dom.Element parentElement, String newElementName) {
        if (parentElement == null)
            return null;

        int newElementPos = getElementPosition(newElementName);
        if (newElementPos == -1)
            return null; // unknown Element -> end

        org.w3c.dom.NodeList children = parentElement.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            org.w3c.dom.Node child = children.item(i);

            if (child.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                String childName = child.getLocalName();
                int childPos = getElementPosition(childName);

                // If the child element should come after our new element, we will insert before
                // this child.
                if (childPos > newElementPos || childPos == -1) {
                    return (org.w3c.dom.Element) child;
                }
            }
        }

        return null; // insert at the end
    }

    /**
     * Helper method: Returns all element names in the correct order.
     * Useful for debugging or tests.
     */
    public static List<String> getElementOrder() {
        return new ArrayList<>(ELEMENT_ORDER);
    }

    /**
     * Debug-Method
     */
    public static void printElementPosition(String elementName) {
        int pos = getElementPosition(elementName);
        if (pos == -1) {
            logger.info("Element '" + elementName + "' Is not included in the defined order.");
        } else {
            logger.info("Element '" + elementName + "' has Position " + pos + ".");
        }
    }
}