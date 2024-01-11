/********************************************************************************
 * Copyright (c) 2023 EclipseSource and others.
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
    MoveFinishedEventAction, MoveInitializedEventAction,
    SetBoundsFeedbackAction
} from '@eclipse-glsp/client';
import {
    FeatureModule,
    MoveAction,
    SetBoundsAction,
    TYPES,
    bindAsService,
    configureActionHandler,
    configureCommand,
    configureModelElement
} from '@eclipse-glsp/sprotty';

// import { SetBoundsFeedbackAction } from '../bounds/set-bounds-feedback-command';
// import { MoveFinishedEventAction, MoveInitializedEventAction } from '../tools/change-bounds/change-bounds-tool-feedback';
import { DrawHelperLinesFeedbackCommand, RemoveHelperLinesFeedbackCommand } from './bpmn-helper-line-feedback';
import { HelperLineManager } from './bpmn-helper-line-manager-default';
import { HELPER_LINE, HelperLine, SELECTION_BOUNDS, SelectionBounds } from './model';
import { HelperLineView, SelectionBoundsView } from './view';

export const bpmnHelperLineModule = new FeatureModule((bind, unbind, isBound, rebind) => {
    const context = { bind, unbind, isBound, rebind };
    configureModelElement(context, HELPER_LINE, HelperLine, HelperLineView);
    configureModelElement(context, SELECTION_BOUNDS, SelectionBounds, SelectionBoundsView);
    configureCommand(context, DrawHelperLinesFeedbackCommand);
    configureCommand(context, RemoveHelperLinesFeedbackCommand);

    bindAsService(bind, TYPES.IHelperLineManager, HelperLineManager);
    configureActionHandler(context, SetBoundsAction.KIND, TYPES.IHelperLineManager);
    configureActionHandler(context, SetBoundsFeedbackAction.KIND, TYPES.IHelperLineManager);
    configureActionHandler(context, MoveAction.KIND, TYPES.IHelperLineManager);
    configureActionHandler(context, MoveInitializedEventAction.KIND, TYPES.IHelperLineManager);
    configureActionHandler(context, MoveFinishedEventAction.KIND, TYPES.IHelperLineManager);
});
