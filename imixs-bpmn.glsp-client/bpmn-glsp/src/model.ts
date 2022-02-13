/********************************************************************************
 * Copyright (c) 2020-2022 EclipseSource and others.
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
    boundsFeature,
    connectableFeature,
    deletableFeature,
    DiamondNode,
    EditableLabel,
    fadeFeature,
    hoverFeedbackFeature,
    isEditableLabel,
    layoutableChildFeature,
    LayoutContainer,
    layoutContainerFeature,
    moveFeature,
    Nameable,
    nameFeature,
    popupFeature,
    RectangularNode,
    SChildElement,
    SEdge,
    selectFeature,
    SModelElement,
    SShapeElement,
    WithEditableLabel,
    withEditLabelFeature
} from '@eclipse-glsp/client';

export class TaskNode extends RectangularNode implements Nameable, WithEditableLabel {
    static readonly DEFAULT_FEATURES = [
        connectableFeature,
        deletableFeature,
        selectFeature,
        boundsFeature,
        moveFeature,
        layoutContainerFeature,
        fadeFeature,
        hoverFeedbackFeature,
        popupFeature,
        nameFeature,
        withEditLabelFeature
    ];
    duration?: number;
    taskType?: string;
    reference?: string;

    get editableLabel(): (SChildElement & EditableLabel) | undefined {
        const label = this.children.find(element => element.type === 'label:heading');
        if (label && isEditableLabel(label)) {
            return label;
        }
        return undefined;
    }

    get name(): string {
        const labelText = this.editableLabel?.text;
        return labelText ? labelText : '<unknown>';
    }
}

export function isTaskNode(element: SModelElement): element is TaskNode {
    return element instanceof TaskNode || false;
}

export class WeightedEdge extends SEdge {
    probability?: string;
}

export class ActivityNode extends DiamondNode {
    nodeType: string = ActivityNode.Type.UNDEFINED;
    size = {
        width: 32,
        height: 32
    };
    strokeWidth = 1;
}

export namespace ActivityNode {
    export namespace Type {
        export const INITIAL = 'initalNode';
        export const FINAL = 'finalNode';
        export const DECISION = 'decisionNode';
        export const MERGE = 'mergeNode';
        export const JOIN = 'joinNode';
        export const FORK = 'forkNode';
        export const UNDEFINED = 'undefined';
    }
}

export class Icon extends SShapeElement implements LayoutContainer {
    static readonly DEFAULT_FEATURES = [boundsFeature, layoutContainerFeature, layoutableChildFeature, fadeFeature];

    layout: string;
    layoutOptions?: { [key: string]: string | number | boolean };
    size = {
        width: 32,
        height: 32
    };
}

export class CategoryNode extends RectangularNode implements Nameable, WithEditableLabel {
    static readonly DEFAULT_FEATURES = [
        deletableFeature,
        selectFeature,
        boundsFeature,
        moveFeature,
        layoutContainerFeature,
        fadeFeature,
        hoverFeedbackFeature,
        popupFeature,
        nameFeature,
        withEditLabelFeature
    ];

    name = '';

    get editableLabel(): (SChildElement & EditableLabel) | undefined {
        const label = this.children.find(element => element.type === 'label:heading');
        if (label && isEditableLabel(label)) {
            return label;
        }
        return undefined;
    }
}
