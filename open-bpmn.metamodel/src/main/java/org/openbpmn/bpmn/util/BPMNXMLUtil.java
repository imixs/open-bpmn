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

/**
 * The BPMNXMLUtil provides helper methods for xml strings
 *
 * @author rsoika
 *
 */
public class BPMNXMLUtil {

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

}
