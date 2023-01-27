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
package org.openbpmn.glsp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.glsp.server.model.DefaultGModelState;
import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.extension.BPMNExtension;

import com.google.inject.Inject;

/**
 * The BPMNGModelState extends the DefaultGModelState and provides the property
 * 'bpmnModel' which holds an instance of the BPMN MetaModel.
 *
 * @author rsoika
 * @version 1.0
 */
public class BPMNGModelState extends DefaultGModelState {

    private BPMNModel bpmnModel;
    private boolean initalized = false;
    List<String> extensionKinds = new ArrayList<>();

    @Inject
    protected Set<BPMNExtension> extensions;

    public BPMNModel getBpmnModel() {
        return bpmnModel;
    }

    public void setBpmnModel(final BPMNModel bpmnModel) {
        this.bpmnModel = bpmnModel;
        this.setRoot(null);
    }

    public boolean isInitalized() {
        return initalized;
    }

    public void setInitalized(final boolean initalized) {
        this.initalized = initalized;
    }

    /**
     * Reset the model state which will force a re-generation in the
     * BPMNGModelFactory.createGModel() method
     */
    public void reset() {
        this.initalized = false;
    }

}
