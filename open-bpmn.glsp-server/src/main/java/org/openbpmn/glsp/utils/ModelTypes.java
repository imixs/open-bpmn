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

public final class ModelTypes {
    private ModelTypes() {
    }

    public static final String LABEL_HEADING = "label:heading";
    // public static final String COMP_HEADER = "comp:header";
    public static final String ICON = "icon";
    public static final String CONTAINER = "container"; // used within Pools
    public static final String BPMN_ROUTER_KIND = "bpmn"; // custom BPMN Routing
    public static final String BPMN_TEXT_NODE = "bpmn-text-node";
    public static final String LANE_DIVIDER = "lane-divider";
    public static final String EXPANDABLE_SUBPROCESS = "expandable_subprocess";

}
