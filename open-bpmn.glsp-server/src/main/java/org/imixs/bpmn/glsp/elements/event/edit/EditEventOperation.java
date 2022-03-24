/********************************************************************************
 * Copyright (c) 2020 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.imixs.bpmn.glsp.elements.event.edit;

import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.operations.Operation;

/**
 * The EditEventOperation is an {@link Action} that directly manipulates the
 * EventNode model representation on server side. The action is created from the
 * {@link ApplyEventUpdateOperationHandler}
 * <p>
 *
 * @author rsoika
 *
 */
public class EditEventOperation extends Operation {

    private static Logger logger = Logger.getLogger(EditEventOperation.class.getName());

    private String id;
    private String feature;
    private String value;

    public EditEventOperation() {
        super("editEvent");
    }

    public EditEventOperation(final String id, final String feature, final String value) {
        this();
        logger.info("...create new EventEditOperation - ID=" + id + " feature=" + feature + " value=" + value);
        this.id = id;
        this.feature = feature;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

}
