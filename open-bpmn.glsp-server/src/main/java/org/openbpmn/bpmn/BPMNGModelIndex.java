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

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.glsp.graph.GEdge;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GModelIndex;
import org.eclipse.glsp.server.actions.ActionHandler;
import org.eclipse.glsp.server.operations.OperationHandler;

/**
 * The BPMNGModelIndex is needed for mapping between the graph model and the
 * source model, as is typically the case for {@link ActionHandler action
 * handlers} and {@link OperationHandler operation handlers}, it is the
 * responsibility of the graph model factory to create such an index while
 * producing the graph model from the source model. The index shall be put into
 * the model state too. Typically the {@link GModelIndex} is extended for a
 * particular model source type as well.
 * </p>
 *
 * @author rsoika
 *
 */
public class BPMNGModelIndex implements GModelIndex {

    @Override
    public Optional<GModelElement> get(final String elementId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<GModelElement> getAll(final Collection<String> elementIds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<GEdge> getIncomingEdges(final GModelElement node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<GEdge> getOutgoingEdges(final GModelElement node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<String> allIds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GModelElement getRoot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getCounter(final EClass eClass, final Function<Integer, String> idProvider) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTypeCount(final EClass eClass) {
        // TODO Auto-generated method stub
        return 0;
    }

}
