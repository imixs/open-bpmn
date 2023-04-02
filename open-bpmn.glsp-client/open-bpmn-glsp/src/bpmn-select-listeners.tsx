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
import {
  ActionDispatcher, filter, getElements, hasArguments, SelectAction,
  SModelRoot, TYPES
} from '@eclipse-glsp/client';
import { SelectionListener } from '@eclipse-glsp/client/lib/features/select/selection-service';
import {
  isBoundaryEvent, isBPMNLabelNode, isLaneNode, isPoolNode, isTaskNode
} from '@open-bpmn/open-bpmn-model';
import { inject, injectable } from 'inversify';

/****************************************************************************
 * This module provides BPMN select listeners for custom behavior.
 *
 ****************************************************************************/

/**
 * This selectionListener selects additional associated BoundaryEvents and BPMNLabels.
 * This allows to move both independent Nodes (TaskNode and BoundaryEvent, GNode and GLabel)
 */
@injectable()
export class BPMNLabelNodeSelectionListener implements SelectionListener {
    @inject(TYPES.IActionDispatcher)
    protected actionDispatcher: ActionDispatcher;

    selectionChanged(root: Readonly<SModelRoot>, selectedElements: string[]): void {
        const additionalSelection: string[]=[''];
        // We are interested in Tasks with BoundaryEvents ...
        const selectedTaskNodes = getElements(root.index, selectedElements, isTaskNode);
        // - first get a list of all selected TaskIDs
        const taskIds = selectedTaskNodes.map(task => task.id);
        // - next iterate over all BoundaryEvents
        const boundaryEvents=filter(root.index,isBoundaryEvent);
        if (selectedTaskNodes.length > 0) {
          // do we have a boundaryEvent that matches this taskID?
          boundaryEvents.forEach( b => {
            if (hasArguments(b)) {
              const taskRef=b.args.attachedToRef+'';
              if (taskIds.includes(taskRef)) {
                additionalSelection.push(b.id);
              }
            }
          });
        }
        // ... and we are interested in BPMNLabelNodes
        const eventNodes = getElements(root.index, selectedElements, isBPMNLabelNode);
        if (eventNodes.length > 0) {
            // find the associated BPMNLabels
            const eventLabelIds = eventNodes.map(node => node.id + '_bpmnlabel');
            eventLabelIds.forEach( l => {
              additionalSelection.push(l);
            });
        }
        // finally dispatch the additional elementIDs...
        this.actionDispatcher.dispatch(SelectAction.create({ selectedElementsIDs: additionalSelection }));
    }
}

/**
 * This selectionListener reacts on multiple selection. In case the selection list contains
 * a Pool or a Lane, these elements are removed from the selection. This is to support
 * multi-node selections within a Pool with the Marquee Tool.
 * As a consequence it is not possible to select multiple Pools
 */
@injectable()
export class BPMNMultiNodeSelectionListener implements SelectionListener {
    @inject(TYPES.IActionDispatcher)
    protected actionDispatcher: ActionDispatcher;

    selectionChanged(root: Readonly<SModelRoot>, selectedElements: string[]): void {
        // react only if more than one element is selected
        if (selectedElements.length<2) {
          return;
        }
        const containerIDs: string[]=[];
        // We are interested in Pools and Lanes ...
        const selectedPools = getElements(root.index, selectedElements, isPoolNode);
        const selectedLanes = getElements(root.index, selectedElements, isLaneNode);
        // collect pools...
        selectedPools.forEach( elementNode => {
          containerIDs.push(elementNode.id);
        });
        // collect lanes...
        selectedLanes.forEach( elementNode => {
          containerIDs.push(elementNode.id);
        });
        // if the size of the selected containers is equals the size of selectionElements
        // this means we only have containers selected and can skip this method
        if (selectedElements.length===containerIDs.length)   {
          return;
        }
        // filter current selection...
        selectedElements = selectedElements.filter(function (element: string) {
          return !containerIDs.includes(element);
        });
        // finally dispatch the updated selected and unselected IDs...
        this.actionDispatcher.dispatch(SelectAction.create({ selectedElementsIDs: selectedElements, deselectedElementsIDs: containerIDs }));
    }
}
