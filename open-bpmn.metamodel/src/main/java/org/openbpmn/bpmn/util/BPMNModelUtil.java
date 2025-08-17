/********************************************************************************
 * Copyright (c) 2022 Imixs Software Solutions GmbH and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.openbpmn.bpmn.util;

import java.util.logging.Logger;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.BPMNNS;
import org.openbpmn.bpmn.elements.DataObject;
import org.openbpmn.bpmn.elements.DataStoreReference;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.bpmn.elements.Gateway;
import org.openbpmn.bpmn.elements.Message;
import org.openbpmn.bpmn.elements.core.BPMNElementNode;
import org.openbpmn.bpmn.elements.core.BPMNLabel;
import org.openbpmn.bpmn.exceptions.BPMNMissingElementException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The BPMNModelUtil provides helper methods for a BPMNModel and the XML
 * representation of a model.
 * 
 * @author rsoika
 *
 */
public class BPMNModelUtil {
    private static Logger logger = Logger.getLogger(BPMNModelUtil.class.getName());

    /**
     * This method resets the Label Postion and bounds to its default values. The
     * method is used in cases where no bounds are set or in case the postion should
     * be reset to the default position of a label. This will also reflect to the
     * source model, if the model will be saved later by the user...
     * 
     * @throws BPMNMissingElementException
     */
    public static void resetLabelBounds(final BPMNElementNode flowElement) throws BPMNMissingElementException {
        double x = 0;
        double y = 0;

        if (BPMNModel.isGateway(flowElement)) {
            x = flowElement.getBounds().getPosition().getX() + (Gateway.DEFAULT_WIDTH / 2)
                    - (BPMNLabel.DEFAULT_WIDTH / 2);
            y = flowElement.getBounds().getPosition().getY() + Gateway.DEFAULT_HEIGHT + Gateway.LABEL_OFFSET;
        } else if (BPMNModel.isDataObject(flowElement)) {
            x = flowElement.getBounds().getPosition().getX() + (DataObject.DEFAULT_WIDTH / 2)
                    - (BPMNLabel.DEFAULT_WIDTH / 2);
            y = flowElement.getBounds().getPosition().getY() + DataObject.DEFAULT_HEIGHT + DataObject.LABEL_OFFSET;
        } else if (BPMNModel.isDataStore(flowElement)) {
            x = flowElement.getBounds().getPosition().getX() + (DataStoreReference.DEFAULT_WIDTH / 2)
                    - (BPMNLabel.DEFAULT_WIDTH / 2);
            y = flowElement.getBounds().getPosition().getY() + DataStoreReference.DEFAULT_HEIGHT
                    + DataStoreReference.LABEL_OFFSET;
        } else if (BPMNModel.isMessage(flowElement)) {
            x = flowElement.getBounds().getPosition().getX() + (Message.DEFAULT_WIDTH / 2)
                    - (BPMNLabel.DEFAULT_WIDTH / 2);
            y = flowElement.getBounds().getPosition().getY() + Message.DEFAULT_HEIGHT + Message.LABEL_OFFSET;
        } else {
            // default (events)
            x = flowElement.getBounds().getPosition().getX() + (Event.DEFAULT_WIDTH / 2)
                    - (BPMNLabel.DEFAULT_WIDTH / 2);
            y = flowElement.getBounds().getPosition().getY() + Event.DEFAULT_HEIGHT + Event.LABEL_OFFSET;
        }

        // update source model with default width/height!
        flowElement.getLabel().getBounds().setPosition(x, y);
        // update source model with default width/height!
        flowElement.getLabel().getBounds().setDimension(BPMNLabel.DEFAULT_WIDTH, BPMNLabel.DEFAULT_HEIGHT);
    }

    /**
     * This method removes whitespace around CDATA Tags like:
     * 
     * <imixs:value>
     * <![CDATA[ some data ]]>
     * </imixs:value>
     * 
     * results in:
     * 
     * <imixs:value><![CDATA[ some data ]]></imixs:value>
     * 
     * We can not use regex (replaceAll) here
     * 
     * xmlString =
     * xmlString.replaceAll(">\\s*+(<\\!\\[CDATA\\[(.|\\n|\\r\\n)*?]\\]>)\\s*</",
     * ">$1</");
     * 
     * 
     * because of a known bug with large XML Strings. See
     * https://stackoverflow.com/questions/7509905/java-lang-stackoverflowerror-while-using-a-regex-to-parse-big-strings
     * 
     * For this reason we parse and optimize the xml manually here.
     *
     * @param xml
     * @return
     */
    public static String cleanCDATAWhiteSpace(String xml) {

        boolean completed = false;
        int lastPos = 0;
        while (!completed) {
            /**
             * search for
             * CDATA start '<![CDATA['
             */
            int pos = xml.indexOf("<![CDATA[", lastPos);
            if (pos == -1) {
                // no more CDATA !
                completed = true;
                break;
            }
            // find upper '>'
            int backwardTagPos = xml.lastIndexOf(">", pos);
            // do we have whitespace?
            if (pos > backwardTagPos - 1) {
                xml = xml.substring(0, backwardTagPos + 1) + xml.substring(pos);
            }
            lastPos = backwardTagPos + 9;
            /**
             * next search for
             * CDATA end ']]>'
             */
            pos = xml.indexOf("]]>", lastPos);
            if (pos == -1) {
                // wrong CDATA!
                System.out.println("WRONG CDATA ELEMENT - unexpected end at: " + lastPos);
                completed = true;
                break;
            }
            pos = pos + 3;
            // find upper '>'
            int forwardTagPos = xml.indexOf("<", pos);
            // do we have whitespace?
            if (forwardTagPos > pos) {
                xml = xml.substring(0, pos) + xml.substring(forwardTagPos);
            }
            lastPos = pos + 1;
        }

        return xml;
    }

    /**
     * Searches for a BPMNShape element within a specific BPMNPlane that references
     * the given participant ID through its bpmnElement attribute.
     * 
     * @param model     the BPMNModel for accessing namespace prefixes
     * @param elementId the element ID to match against BPMNShape
     *                  bpmnElement attributes
     * @return the matching BPMNPlane element, or null
     */
    public static Element findBPMNPlane(BPMNModel model, String elementId) {

        // find the corresponding BPMNPlane
        NodeList planeList = model.findElementsByName(model.getDoc().getDocumentElement(), BPMNNS.BPMNDI, "BPMNPlane");

        for (int i = 0; i < planeList.getLength(); i++) {
            Element planeElement = (Element) planeList.item(i);
            String bpmnElementID = planeElement.getAttribute("bpmnElement");
            // if the id matches we have a direct macht in a non-collaboration element
            if (elementId != null && elementId.equals(bpmnElementID)) {
                return planeElement;
            }

        }
        logger.warning("No BPMNPlane found for element '" + elementId + "'");
        return null;
    }

    /**
     * Searches for a BPMNShape element within a specific BPMNPlane that references
     * the given participant ID through its bpmnElement attribute.
     * 
     * @param model     the BPMNModel for accessing namespace prefixes
     * @param plane     the BPMNPlane element to search within
     * @param elementId the element ID to match against BPMNShape
     *                  bpmnElement attributes
     * @return the matching BPMNShape element, or null if no match is found in this
     *         plane
     */
    public static Element findBPMNShapeInPlane(BPMNModel model, Element plane, String elementId) {
        String fullNodeName = model.getPrefix(BPMNNS.BPMNDI) + "BPMNShape";
        NodeList childList = plane.getChildNodes();
        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);
            if (fullNodeName.equals(child.getNodeName()) && child.hasAttributes()) {
                NamedNodeMap attributesMap = child.getAttributes();
                for (int j = 0; j < attributesMap.getLength(); j++) {
                    Node attr = attributesMap.item(j);
                    if ("bpmnElement".equals(attr.getNodeName()) &&
                            elementId.equals(attr.getNodeValue())) {
                        return (Element) child;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Searches for a BPMNEdge element within a specific BPMNPlane that references
     * the given participant ID through its bpmnElement attribute.
     * 
     * @param model     the BPMNModel for accessing namespace prefixes
     * @param plane     the BPMNPlane element to search within
     * @param elementId the element ID to match against BPMNEdge
     *                  bpmnElement attributes
     * @return the matching BPMNEdge element, or null if no match is found in this
     *         plane
     */
    public static Element findBPMNEdgeInPlane(BPMNModel model, Element plane, String elementId) {
        String fullNodeName = model.getPrefix(BPMNNS.BPMNDI) + "BPMNEdge";
        NodeList childList = plane.getChildNodes();

        for (int i = 0; i < childList.getLength(); i++) {
            Node child = childList.item(i);
            if (fullNodeName.equals(child.getNodeName()) && child.hasAttributes()) {
                NamedNodeMap attributesMap = child.getAttributes();
                for (int j = 0; j < attributesMap.getLength(); j++) {
                    Node attr = attributesMap.item(j);
                    if ("bpmnElement".equals(attr.getNodeName()) &&
                            elementId.equals(attr.getNodeValue())) {
                        return (Element) child;
                    }
                }
            }
        }
        return null;
    }

}
