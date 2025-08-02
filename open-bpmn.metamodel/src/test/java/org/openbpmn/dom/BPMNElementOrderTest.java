package org.openbpmn.dom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openbpmn.bpmn.elements.BPMNElementOrder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Test class to test the correct order of elements within a parent element.
 * This is important for BPMN and the BPMNElementOrder class supports helper
 * methods.
 */
public class BPMNElementOrderTest {

    private Document document;

    @BeforeEach
    void setUp() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();
    }

    @Test
    void testBasicElementPositions() {
        // Basis-Elemente testen
        assertTrue(
                BPMNElementOrder.getElementPosition("documentation") < BPMNElementOrder.getElementPosition("outgoing"));

        assertTrue(BPMNElementOrder.getElementPosition("incoming") < BPMNElementOrder.getElementPosition("outgoing"));

        System.out.println("documentation Position: " + BPMNElementOrder.getElementPosition("documentation"));
        System.out.println("outgoing Position: " + BPMNElementOrder.getElementPosition("outgoing"));
    }

    @Test
    void testWaypointVsBPMNLabel() {
        // Das kritische Problem testen
        int waypointPos = BPMNElementOrder.getElementPosition("waypoint");
        int bpmnLabelPos = BPMNElementOrder.getElementPosition("BPMNLabel");

        System.out.println("waypoint Position: " + waypointPos);
        System.out.println("BPMNLabel Position: " + bpmnLabelPos);

        assertTrue(waypointPos < bpmnLabelPos,
                "waypoint sollte vor BPMNLabel kommen");

        assertTrue(BPMNElementOrder.shouldComeBefore("waypoint", "BPMNLabel"),
                "waypoint sollte vor BPMNLabel stehen");
    }

    @Test
    void testAppendChildWithWaypointFirst() throws Exception {
        // BPMNEdge Element erstellen
        Element bpmnEdge = document.createElementNS("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNEdge");
        document.appendChild(bpmnEdge);

        // Erst waypoint hinzufügen
        Element waypoint1 = document.createElementNS("http://www.omg.org/spec/DD/20100524/DI", "waypoint");
        waypoint1.setAttribute("x", "100");
        waypoint1.setAttribute("y", "200");

        BPMNElementOrder.appendChild(bpmnEdge, waypoint1);

        // Dann BPMNLabel hinzufügen
        Element bpmnLabel = document.createElementNS("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNLabel");

        BPMNElementOrder.appendChild(bpmnEdge, bpmnLabel);

        // Dann noch einen waypoint hinzufügen
        Element waypoint2 = document.createElementNS("http://www.omg.org/spec/DD/20100524/DI", "waypoint");
        waypoint2.setAttribute("x", "300");
        waypoint2.setAttribute("y", "400");

        BPMNElementOrder.appendChild(bpmnEdge, waypoint2);

        // Reihenfolge prüfen
        NodeList children = bpmnEdge.getChildNodes();
        assertEquals(3, children.getLength());

        // waypoint1 sollte an Position 0 stehen
        assertEquals("waypoint", children.item(0).getLocalName());
        assertEquals("100", ((Element) children.item(0)).getAttribute("x"));

        // waypoint2 sollte an Position 1 stehen
        assertEquals("waypoint", children.item(1).getLocalName());
        assertEquals("300", ((Element) children.item(1)).getAttribute("x"));

        // BPMNLabel sollte an Position 2 stehen (am Ende)
        assertEquals("BPMNLabel", children.item(2).getLocalName());

        System.out.println("=== Reihenfolge nach waypoint-zuerst Test ===");
        printChildOrder(bpmnEdge);
    }

    @Test
    void testAppendChildWithLabelFirst() throws Exception {
        // BPMNEdge Element erstellen
        Element bpmnEdge = document.createElementNS("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNEdge");
        document.appendChild(bpmnEdge);

        // Erst BPMNLabel hinzufügen
        Element bpmnLabel = document.createElementNS("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNLabel");

        BPMNElementOrder.appendChild(bpmnEdge, bpmnLabel);

        // Dann waypoints hinzufügen
        Element waypoint1 = document.createElementNS("http://www.omg.org/spec/DD/20100524/DI", "waypoint");
        waypoint1.setAttribute("x", "100");
        waypoint1.setAttribute("y", "200");

        BPMNElementOrder.appendChild(bpmnEdge, waypoint1);

        Element waypoint2 = document.createElementNS("http://www.omg.org/spec/DD/20100524/DI", "waypoint");
        waypoint2.setAttribute("x", "300");
        waypoint2.setAttribute("y", "400");

        BPMNElementOrder.appendChild(bpmnEdge, waypoint2);

        // Reihenfolge prüfen
        NodeList children = bpmnEdge.getChildNodes();
        assertEquals(3, children.getLength());

        // waypoint1 sollte an Position 0 stehen (vor dem Label eingefügt)
        assertEquals("waypoint", children.item(0).getLocalName());
        assertEquals("100", ((Element) children.item(0)).getAttribute("x"));

        // waypoint2 sollte an Position 1 stehen
        assertEquals("waypoint", children.item(1).getLocalName());
        assertEquals("300", ((Element) children.item(1)).getAttribute("x"));

        // BPMNLabel sollte an Position 2 stehen (am Ende)
        assertEquals("BPMNLabel", children.item(2).getLocalName());

        System.out.println("=== Reihenfolge nach label-zuerst Test ===");
        printChildOrder(bpmnEdge);
    }

    @Test
    void testDocumentationOrder() throws Exception {
        // StartEvent Element erstellen
        Element startEvent = document.createElementNS("http://www.omg.org/spec/BPMN/20100524/MODEL", "startEvent");
        document.appendChild(startEvent);

        // Erst outgoing hinzufügen
        Element outgoing = document.createElementNS("http://www.omg.org/spec/BPMN/20100524/MODEL", "outgoing");
        outgoing.setTextContent("flow1");

        BPMNElementOrder.appendChild(startEvent, outgoing);

        // Dann documentation hinzufügen
        Element documentation = document.createElementNS("http://www.omg.org/spec/BPMN/20100524/MODEL",
                "documentation");
        documentation.setTextContent("Test Documentation");

        BPMNElementOrder.appendChild(startEvent, documentation);

        // Reihenfolge prüfen
        NodeList children = startEvent.getChildNodes();
        assertEquals(2, children.getLength());

        // documentation sollte an Position 0 stehen (vor outgoing)
        assertEquals("documentation", children.item(0).getLocalName());
        assertEquals("Test Documentation", children.item(0).getTextContent());

        // outgoing sollte an Position 1 stehen
        assertEquals("outgoing", children.item(1).getLocalName());
        assertEquals("flow1", children.item(1).getTextContent());

        System.out.println("=== Reihenfolge Documentation Test ===");
        printChildOrder(startEvent);
    }

    @Test
    void testFindInsertPositionDebugging() {
        // Debug-Informationen für das waypoint/BPMNLabel Problem
        Element parent = document.createElementNS("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNEdge");

        // BPMNLabel bereits vorhanden
        Element existingLabel = document.createElementNS("http://www.omg.org/spec/BPMN/20100524/DI", "BPMNLabel");
        parent.appendChild(existingLabel);

        // Wo würde waypoint eingefügt werden?
        Element insertBefore = BPMNElementOrder.findInsertPosition(parent, "waypoint");

        System.out.println("=== Debug findInsertPosition ===");
        System.out.println("Existing children in parent:");
        printChildOrder(parent);
        System.out.println("insertBefore for 'waypoint': " +
                (insertBefore != null ? insertBefore.getLocalName() : "null (append at end)"));

        if (insertBefore != null) {
            assertEquals("BPMNLabel", insertBefore.getLocalName(),
                    "waypoint sollte vor BPMNLabel eingefügt werden");
        } else {
            fail("waypoint sollte vor BPMNLabel eingefügt werden, nicht am Ende");
        }
    }

    private void printChildOrder(Element element) {
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element child = (Element) children.item(i);
                System.out.println("  [" + i + "] " + child.getLocalName());
            }
        }
    }
}
