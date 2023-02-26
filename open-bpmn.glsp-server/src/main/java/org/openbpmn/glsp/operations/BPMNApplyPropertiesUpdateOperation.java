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
package org.openbpmn.glsp.operations;

import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.operations.Operation;

/**
 * The ApplyEventEditOperation is an {@link Action} that directly manipulates
 * the EventNode model representation on server side. The action is send from
 * the client property panel to the server.
 * <p>
 * Each operation is uniquely defined by its KIND
 * <p>
 * The Operations are handled by the
 * {@link BPMNApplyPropertiesUpdateOperationHandler}. The operation handler is
 * responsible of processing the operation and updates the model representation
 * accordingly.
 * <p>
 * The operation provides the Element ID, a JSON data structure with the
 * new/updated data and an optional category. The category can be used by the
 * {@link BPMNApplyPropertiesUpdateOperationHandler} to update only parts on an
 * element. This is to optimize the update performance as the data structure can
 * become very complex or various BPMN elements.
 *
 */
public class BPMNApplyPropertiesUpdateOperation extends Operation {

    public static final String OPERATION_KIND = "applyBPMNPropertiesUpdate";

    public static final String DOCUMENTATION_PREFIX = "documentation:";
    public static final String NAME_PREFIX = "name:";

    private String id;
    private String jsonData;
    private String category;

    public BPMNApplyPropertiesUpdateOperation() {
        super(OPERATION_KIND);
    }

    public BPMNApplyPropertiesUpdateOperation(final String nodeId, final String jsonData, final String category) {
        this();
        this.id = nodeId;
        this.jsonData = jsonData;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(final String nodeId) {
        this.id = nodeId;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(final String jsonData) {
        this.jsonData = jsonData;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
