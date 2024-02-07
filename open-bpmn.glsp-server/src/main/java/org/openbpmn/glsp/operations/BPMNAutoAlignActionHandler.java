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

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.glsp.server.actions.AbstractActionHandler;
import org.eclipse.glsp.server.actions.Action;
import org.openbpmn.glsp.model.BPMNGModelState;

import com.google.inject.Inject;

/**
 * This ActionHandler reacts on AutoAlign actions send from the client
 *
 * @author rsoika
 *
 */
public class BPMNAutoAlignActionHandler extends AbstractActionHandler<BPMNAutoAlignAction> {

    private static Logger logger = Logger.getLogger(BPMNAutoAlignActionHandler.class.getName());

    @Inject
    protected BPMNGModelState modelState;

    @Override
    protected List<Action> executeAction(final BPMNAutoAlignAction actualAction) {

        logger.info("----------> Auto Align Action received!");

        // no more action - the GModel is now up to date
        return none();
    }

}