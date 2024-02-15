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
  ActionDispatcher,
  GModelElement,
  GModelRoot,
  ISelectionListener,
  ISnapper,
  Point,
  SelectAction,
  TYPES,
  filter,
  getElements,
  hasArgs
} from '@eclipse-glsp/client';
import {
  isBPMNLabelNode,
  isBoundaryEvent,
  isLaneNode,
  isPoolNode,
  isTaskNode
} from '@open-bpmn/open-bpmn-model';
import { inject, injectable } from 'inversify';

/****************************************************************************
 * This module provides BPMN select listeners for custom behavior.
 *
 ****************************************************************************/

/**
 * A {@link ISnapper} implementation that snaps BPMN elements onto a fixed gride size.
 * This snapper calulates the grid size based on the selected element to allign tasks, gateways and events.
 *
 */
@injectable()
export class BPMNElementSnapper implements ISnapper {
  constructor(public grid: { x: number; y: number } = { x: 1, y: 1 }) { }

  snap(position: Point, _element: GModelElement): Point {

    // if (isLaneDivider(_element)) {
    //   return this.findLaneDividerSnapPoint(_element, position);
    // }

    // move routing-points by 5x5
    if ('volatile-routing-point' === _element.type) {
      return {
        x: Math.round(position.x / 5) * 5,
        y: Math.round(position.y / 5) * 5
      };
    }

    // default move 10x10...
    return {
      x: Math.round(position.x / this.grid.x) * this.grid.x,
      y: Math.round(position.y / this.grid.y) * this.grid.y
    };
  }

  /*
   * This helper method computes the snap Position of a Lane-Divider.
   * The position is based on the Bounds of the containing Pool.
   * The final position is always on the x position of the Pool.
   */
  /*
  private findLaneDividerSnapPoint(element: GModelElement, position: Point): Point {
    const x = 0;
    let y = position.y;

    console.log(' snap lane devider current pos = ' + x + ',' + y)
    // test min / max position
    if (hasArgs(element)) {
      const yMin = Number(element.args.ymin);
      const yMax = Number(element.args.ymax);
      if (y < yMin) {
        y = yMin;
      }
      if (y > yMax) {
        y = yMax;
      }
    }
    // return the new position;
    return { x: x, y: y };
  }
  */
}

/**
 * This selectionListener selects additional associated BoundaryEvents and BPMNLabels.
 * This allows to move both independent Nodes (TaskNode and BoundaryEvent, Event|Gateway and BPMNLabel)
 */
@injectable()
export class BPMNSelectionHelper implements ISelectionListener {
  @inject(TYPES.IActionDispatcher)
  protected actionDispatcher: ActionDispatcher;

  selectionChanged(root: Readonly<GModelRoot>, selectedElements: string[]): void {
    const additionalSelection: string[] = [''];

    // First look for BoundaryEvents ...
    const selectedTaskNodes = getElements(root.index, selectedElements, isTaskNode);
    if (selectedTaskNodes.length > 0) {
      // get a list of all selected TaskIDs
      const taskIds = selectedTaskNodes.map(task => task.id);
      // - next iterate over all BoundaryEvents
      const boundaryEvents = filter(root.index, isBoundaryEvent);
      // do we have a boundaryEvent that matches this taskID?
      boundaryEvents.forEach(b => {
        if (hasArgs(b)) {
          const taskRef = b.args.attachedToRef + '';
          if (taskIds.includes(taskRef)) {
            additionalSelection.push(b.id);
          }
        }
      });
    }

    // And next we are interested in BPMNLabelNodes
    const eventNodes = getElements(root.index, selectedElements, isBPMNLabelNode);
    if (eventNodes.length > 0) {
      // find the associated BPMNLabels
      const eventLabelIds = eventNodes.map(node => node.id + '_bpmnlabel');
      eventLabelIds.forEach(l => {
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
export class BPMNMultiNodeSelectionListener implements ISelectionListener {
  @inject(TYPES.IActionDispatcher)
  protected actionDispatcher: ActionDispatcher;

  selectionChanged(root: Readonly<GModelRoot>, selectedElements: string[]): void {
    // react only if more than one element is selected
    if (selectedElements.length < 2) {
      return;
    }
    const containerIDs: string[] = [];
    // We are interested in Pools and Lanes ...
    const selectedPools = getElements(root.index, selectedElements, isPoolNode);
    const selectedLanes = getElements(root.index, selectedElements, isLaneNode);
    // collect pools...
    selectedPools.forEach(elementNode => {
      containerIDs.push(elementNode.id);
    });
    // collect lanes...
    selectedLanes.forEach(elementNode => {
      containerIDs.push(elementNode.id);
    });
    // if the size of the selected containers is equals the size of selectionElements
    // this means we only have containers selected and can skip this method
    if (selectedElements.length === containerIDs.length) {
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
