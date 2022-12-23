package org.openbpmn.bpmn.elements;

import java.util.Set;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.exceptions.BPMNModelException;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Text Annotations are a mechanism for a modeler to provide additional
 * information for the reader of a BPMN Diagram. A Text Annotation is an open
 * rectangle that MUST be drawn with a solid single line.
 * <p>
 * A Text Annotation object can be connected to a specific object on the Diagram
 * with an Association, but does not affect the flow
 * 
 * 
 * @author rsoika
 *
 */
public class TextAnnotation extends BPMNElementNode {

    public final static double DEFAULT_WIDTH = 75.0;
    public final static double DEFAULT_HEIGHT = 50.0;
    public static final double LABEL_OFFSET = 0;
    
    //public String text="";

    private Element textNode = null;
    
    protected TextAnnotation(BPMNModel model, Element node, String type, BPMNProcess bpmnProcess)
            throws BPMNModelException {
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
     * The content of a TextAnnotation
     * @return
     */
    public String getText() {
        if (textNode == null) {
            // lazy loading of text element
            Set<Element> elementList = BPMNModel.findChildNodesByName(elementNode,
                    BPMNNS.BPMN2.prefix + ":text");
            if (elementList.size() > 0) {
                // get the first one and update the value only
                textNode = elementList.iterator().next();
            }
        }
        if (textNode != null && textNode.getFirstChild() != null) {
            return textNode.getFirstChild().getNodeValue();
        } else {
            return ""; // element
        }
    }

    public void setText(String text) {
         if (textNode == null) {
            // lazy loading of documentation element
            Set<Element> elementList = BPMNModel.findChildNodesByName(elementNode,
                    BPMNNS.BPMN2.prefix + ":text");
            if (elementList.size() == 0) {
                // create new node
                textNode = model.createElement(BPMNNS.BPMN2, "text");
                elementNode.appendChild(textNode);
            } else {
                // get the first one and update the value only
                textNode = elementList.iterator().next();
            }
        }
        // remove old child nodes
        NodeList docChildList = textNode.getChildNodes();
        for (int i = 0; i < docChildList.getLength(); i++) {
            Node child = docChildList.item(i);
            textNode.removeChild(child);
        }
        CDATASection cdata = getDoc().createCDATASection(text);
        textNode.appendChild(cdata);
    }
    
    
}
