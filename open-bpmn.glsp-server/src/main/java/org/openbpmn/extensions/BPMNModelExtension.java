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

import java.nio.file.Path;

import org.openbpmn.bpmn.BPMNModel;
import org.openbpmn.bpmn.elements.core.BPMNElement;

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
     * @param path  - the file path the model is loaded from
     */
    void onLoad(final BPMNModel model, final Path path);

    /**
     * Called before the BPMNModel is stored to disk.
     * 
     * @param model - the BPMNModel
     * @param path  - the file path the model is loaded from
     */
    void onSave(final BPMNModel model, final Path path);

    /**
     * Returns the priority of this action handler. The priority is used to derive
     * the execution order if multiple extension handlers should execute the same
     * {@link BPMNElement}. The default priority is `0` and the priority is sorted
     * descending. This means handlers with a priority &gt; 0 are executed before
     * handlers with a default priority and handlers with a priority &lt; 0 are
     * executed afterwards.
     *
     * @return the priority as integer.
     */
    default int getPriority() {
        return 0;
    }
}
