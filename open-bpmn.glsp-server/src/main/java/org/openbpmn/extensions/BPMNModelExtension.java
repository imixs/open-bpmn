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
package org.openbpmn.extensions;

import org.openbpmn.bpmn.BPMNModel;

/**
 * The BPMNModelExtension is an extension point which allows you adapt the load
 * and save events of a model.
 * 
 * In difference to the BPMNExtension the BPMNModelExtension is not bound to a
 * namespace or specific part of the model.
 * 
 *
 * @author rsoika
 *
 */
public interface BPMNModelExtension {

    /**
     * Called after the BPMNModel was loaded the first time.
     * 
     * An injected ModelState already holds the generated GModel Tree.
     * 
     * @param model - the BPMNModel
     */
    void onLoad(final BPMNModel model);

    /**
     * Called before the BPMNModel is stored to disk.
     * 
     * @param model - the BPMNModel
     */
    void onSave(final BPMNModel model);

}
