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
package org.openbpmn.glsp.utils;

import org.eclipse.glsp.server.actions.ServerMessageAction;
import org.eclipse.glsp.server.types.Severity;
import org.openbpmn.bpmn.ModelNotification;

/**
 * The BPMNActionUtil provides helper methods to create GLSP Server Actions
 *
 * @author rsoika
 *
 */
public class BPMNActionUtil {

    /**
     * Helper Method that converts a Open-BPMN ModelNotification into a
     * ServerMessageAction
     * 
     * @param notification
     * @return
     */
    public static ServerMessageAction convertModelNotification(ModelNotification notification) {
        ServerMessageAction serverMessage = null;

        if (notification.getSeverity() == ModelNotification.Severity.ERROR) {
            serverMessage = new ServerMessageAction(Severity.ERROR,
                    notification.getMessage(), notification.getDetails(), 10);
        } else if (notification.getSeverity() == ModelNotification.Severity.WARNING) {
            serverMessage = new ServerMessageAction(Severity.WARNING,
                    notification.getMessage(), notification.getDetails(), 10);
        } else {
            serverMessage = new ServerMessageAction(Severity.INFO,
                    notification.getMessage(), notification.getDetails(), 10);
        }

        return serverMessage;
    }

}
