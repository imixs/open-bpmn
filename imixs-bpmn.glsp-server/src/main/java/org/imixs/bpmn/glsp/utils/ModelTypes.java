/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
package org.imixs.bpmn.glsp.utils;

public final class ModelTypes {
   private ModelTypes() {}

   public static final String LABEL_HEADING = "label:heading";
   public static final String LABEL_TEXT = "label:text";
   public static final String COMP_HEADER = "comp:header";
   public static final String LABEL_ICON = "label:icon";
   public static final String SEQUENCE_FLOW = "edge:sequenceflow";
   public static final String ICON = "icon";
   public static final String ACTIVITY_NODE = "activityNode";
   // public static final String DECISION_NODE = ACTIVITY_NODE + ":decision";
   // public static final String MERGE_NODE = ACTIVITY_NODE + ":merge";
   // public static final String FORK_NODE = ACTIVITY_NODE + ":fork";
   // public static final String JOIN_NODE = ACTIVITY_NODE + ":join";
   public static final String TASK = "task";
   public static final String MANUAL_TASK = TASK + ":manual";
   public static final String USER_TASK = TASK + ":user";
   public static final String SEND_TASK = TASK + ":send";
   public static final String SERVICE_TASK = TASK + ":service";
   public static final String SCRIPT_TASK = TASK + ":script";

   // public static final String AUTOMATED_TASK = TASK + ":automated";
   public static final String POOL = "pool";
   public static final String STRUCTURE = "struct";

   public static String toNodeType(final String type) {
      switch (type) {

         case POOL:
            return "pool";
         default:
            return "unknown";
      }
   }

}
