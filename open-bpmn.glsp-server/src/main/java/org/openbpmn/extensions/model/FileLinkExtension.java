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
package org.openbpmn.extensions.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.ModelNotification;
import org.openbpmn.extensions.BPMNModelExtension;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The TimerEventDefinitionExtension is responsible to read and update optional
 * TimerEventDefinitions from the BPMN model. The Extension builds a custom
 * property section named 'Timers' shown a list of all TimerEventDefinitions
 * define in a Event.
 * 
 * @author rsoika
 */
public class FileLinkExtension implements BPMNModelExtension {
    protected static Logger logger = Logger.getLogger(FileLinkExtension.class.getName());

    @Override
    public int getPriority() {
        return 101;
    }

    /**
     * This method is called during the load method from the
     * BPMNModelFactory. The method resolves all elements with the attribute
     * 'open-bpmn:file-link'
     * e.g.
     * <bpmn2:script id="script_1" open-bpmn:file-link="file://imixs.script.js">
     * <![CDATA[file://imixs.script.js]]></bpmn2:script>
     * 
     * The method compares the content of the corresponding file. In case of a
     * mismatch we assume that the file content is actual and so we
     * mark the model immediately as dirty.
     * 
     * The method marks the model as dirty if linked file content has changed
     * 
     * @param path - file path
     * @return boolean - true if the linked file content has changed.
     */

    @Override
    public void onLoad(BPMNModel model, Path path) {
        if (model == null || path == null) {
            return;
        }
        // Resolve the parent path
        Path parent = path.getParent();
        long l = System.currentTimeMillis();

        // Find all elements with the attribute "x"
        NodeList elements = model.getDoc().getElementsByTagName("*");
        for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);
            if (element.hasAttribute("open-bpmn:file-link")) {
                String fileLinkRelative = element.getAttribute("open-bpmn:file-link");
                String fileLink = fileLinkRelative.substring(6);
                fileLink = parent + fileLink;
                Path pathLinkFileContent = Paths.get(fileLink);
                try {
                    // read content...
                    logger.fine(element.getNodeName() + " has attribute open-bpmn:file-link: "
                            + fileLink);

                    byte[] bytes = Files.readAllBytes(pathLinkFileContent);
                    String fileData = new String(bytes, StandardCharsets.UTF_8);
                    // Now we compare the content with the content of the CDATA Tag. If not equal we
                    // update the file!! Because we assume the .bpmn file is always right.
                    String bpmnContent = getElementContent(element);

                    if (!bpmnContent.equals(fileData)) {
                        logger.fine(
                                "File content of open-bpmn:file-link '" + fileLink + "' updated.");
                        // mark model as dirty
                        model.setDirty(true);
                        model.getNotifications().add(new ModelNotification(ModelNotification.Severity.WARNING,
                                "Linked file content was updated!",
                                "The linked file  resource '" + fileLink
                                        + "' was updated. Model file should be saved!"));
                    }

                    // Now replace the content with the filename
                    while (element.hasChildNodes()) {
                        element.removeChild(element.getFirstChild());
                    }
                    // create new cdata section for this child node and add the content....
                    CDATASection cdataSection = model.getDoc().createCDATASection(fileLinkRelative);
                    element.appendChild(cdataSection);

                } catch (IOException e) {
                    logger.warning(
                            "Failed to read content of open-bpmn:file-link '" + fileLink + "' : " + e.getMessage());
                    model.getNotifications().add(new ModelNotification(ModelNotification.Severity.WARNING,
                            "Failed to read linked file content '" + fileLinkRelative + "' !",
                            "Failed to read content of open-bpmn:file-link '" + fileLinkRelative + "' in element "
                                    + element.getAttribute("id")));
                }
            }
        }
        logger.fine("...resolveFileLinksOnLoad took " + (System.currentTimeMillis() - l) + "ms");
    }

    /**
     * Helper method that gets the content of an element and supports an optional
     * CDATA node
     * 
     * @param element
     * @return
     */
    private String getElementContent(Element element) {
        // collect all the node content
        // This can again include CData sections! (see issue #)
        String result = "";
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node instanceof CDATASection) {
                result = result + node.getNodeValue();
            } else {
                // normal text node
                result = result + node.getTextContent();
            }
        }
        return result;
    }

    /**
     * This helper method is called during the save method. The method resolves all
     * elements with the attribute 'open-bpmn:file-link'
     * e.g.
     * <bpmn2:script id="script_1" open-bpmn:file-link="file://imixs.script.js">
     * <![CDATA[file://imixs.script.js]]></bpmn2:script>
     * 
     * The method opens the corresponding file and replaces the element content.
     * If no file was found, the method prints a warning.
     */
    @Override
    public void onSave(BPMNModel model, final Path path) {
        // Resolve the parent path
        Path parent = path.getParent();
        long l = System.currentTimeMillis();

        // Find all elements with the attribute "x"
        NodeList elements = model.getDoc().getElementsByTagName("*");
        for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);
            if (element.hasAttribute("open-bpmn:file-link")) {
                String fileLink = element.getAttribute("open-bpmn:file-link");
                fileLink = fileLink.substring(6);
                fileLink = parent + fileLink;
                Path pathLinkFileContent = Paths.get(fileLink);
                try {
                    // read content...
                    logger.fine(element.getNodeName() + " has attribute open-bpmn:file-link: "
                            + fileLink);

                    byte[] bytes = Files.readAllBytes(pathLinkFileContent);
                    String fileData = new String(bytes, StandardCharsets.UTF_8);

                    // remove old sub_child nodes of this childNode...
                    while (element.hasChildNodes()) {
                        element.removeChild(element.getFirstChild());
                    }
                    // create new cdata section for this child node and add the content....
                    CDATASection cdataSection = model.getDoc().createCDATASection(fileData);
                    element.appendChild(cdataSection);

                } catch (IOException e) {
                    // We do not create the file here and print just a warning. This is because
                    // if the user has activated autosave mode files will be created even if the
                    // user does not expect it.
                    // Files.createFile(pathLinkFileContent);
                    String message = "Missing resource open-bpmn:file-link '" + fileLink + "'!";
                    logger.warning(message);
                    model.getNotifications().add(new ModelNotification(ModelNotification.Severity.WARNING, message,
                            "The linked file  resource '" + fileLink + "' does not exist!"));

                }

            }
        }
        logger.fine("...resolveFileLinksOnSave took " + (System.currentTimeMillis() - l) + "ms");

    }

}
