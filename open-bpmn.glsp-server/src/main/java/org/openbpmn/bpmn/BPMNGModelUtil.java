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
package org.openbpmn.bpmn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GGraph;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GModelRoot;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.GraphFactory;
import org.eclipse.glsp.graph.builder.impl.GGraphBuilder;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.model.GModelState;
import org.openbpmn.bpmn.elements.Activity;
import org.openbpmn.bpmn.elements.Event;
import org.openbpmn.glsp.BPMNDiagramModule;
import org.openbpmn.glsp.bpmn.EventNode;
import org.openbpmn.glsp.bpmn.TaskNode;
import org.openbpmn.glsp.elements.event.EventNodeBuilder;
import org.openbpmn.glsp.elements.task.TaskNodeBuilder;
import org.openbpmn.glsp.utils.ModelTypes;

/**
 * Util Class to generate a GModel form a BPMN model
 * </p>
 **/
public class BPMNGModelUtil {
    private static Logger logger = Logger.getLogger(BPMNDiagramModule.class.getName());

    public static GGraph createGModelFromProcess(final org.openbpmn.bpmn.elements.Process process,
            final GModelState modelState) {

        List<GModelElement> entityNodes = new ArrayList<>();

        // Add all Events...
        for (Event event : process.getEvents()) {
            System.out.println("event: " + event.getName());
            GPoint point = GraphUtil.point(event.getBounds().getX(), event.getBounds().getY());

            EventNodeBuilder builder = new EventNodeBuilder(ModelTypes.START_EVENT, event.getName());// , "event:start"
            EventNode eventNode = builder.position(point).build();

            entityNodes.add(eventNode);

        }

        // Add all Tasks
        for (Activity activity : process.getActivities()) {
            System.out.println("activity: " + activity.getName());
            GPoint point = GraphUtil.point(activity.getBounds().getX(), activity.getBounds().getY());

            TaskNodeBuilder builder = new TaskNodeBuilder(ModelTypes.MANUAL_TASK, activity.getName());// , "event:start"
            TaskNode taskNode = builder.position(point).build();

            entityNodes.add(taskNode);

        }

        // add to root node...
        GGraph newGModel = new GGraphBuilder() //
                .id(process.getId()) //
                .addAll(entityNodes) //
                .build();

        return newGModel;
    }

    public static GModelRoot createNewEmptyRoot(final String rootID) {
        GModelRoot root = GraphFactory.eINSTANCE.createGGraph();
        root.setId(rootID);
        root.setType(DefaultTypes.GRAPH);
        return root;
    }
}
