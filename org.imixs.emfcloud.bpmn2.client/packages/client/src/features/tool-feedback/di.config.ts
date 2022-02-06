/********************************************************************************
 * Copyright (c) 2019-2021 EclipseSource and others.
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
import { ContainerModule } from 'inversify';
import { configureCommand, configureView, LocationPostprocessor, MoveCommand, TYPES } from 'sprotty';

import { GLSP_TYPES } from '../../base/types';
import { SResizeHandle } from '../change-bounds/model';
import { HideChangeBoundsToolResizeFeedbackCommand, ShowChangeBoundsToolResizeFeedbackCommand } from './change-bounds-tool-feedback';
import { DrawFeedbackEdgeCommand, FeedbackEdgeEnd, RemoveFeedbackEdgeCommand } from './creation-tool-feedback';
import { ModifyCssFeedbackCommand } from './css-feedback';
import {
    DrawFeedbackEdgeSourceCommand,
    HideEdgeReconnectHandlesFeedbackCommand,
    ShowEdgeReconnectHandlesFeedbackCommand,
    SwitchRoutingModeCommand
} from './edge-edit-tool-feedback';
import { FeedbackActionDispatcher } from './feedback-action-dispatcher';
import { DrawMarqueeCommand, RemoveMarqueeCommand } from './marquee-tool-feedback';
import { FeedbackEdgeEndView, SResizeHandleView } from './view';

const toolFeedbackModule = new ContainerModule((bind, _unbind, isBound) => {
    bind(GLSP_TYPES.IFeedbackActionDispatcher).to(FeedbackActionDispatcher).inSingletonScope();

    configureCommand({ bind, isBound }, ModifyCssFeedbackCommand);

    // create node and edge tool feedback
    configureCommand({ bind, isBound }, DrawFeedbackEdgeCommand);
    configureCommand({ bind, isBound }, RemoveFeedbackEdgeCommand);

    configureCommand({ bind, isBound }, DrawMarqueeCommand);
    configureCommand({ bind, isBound }, RemoveMarqueeCommand);

    configureView({ bind, isBound }, FeedbackEdgeEnd.TYPE, FeedbackEdgeEndView);
    // move tool feedback: we use sprotties MoveCommand as client-side visual feedback
    configureCommand({ bind, isBound }, MoveCommand);

    // resize tool feedback
    configureCommand({ bind, isBound }, ShowChangeBoundsToolResizeFeedbackCommand);
    configureCommand({ bind, isBound }, HideChangeBoundsToolResizeFeedbackCommand);
    configureView({ bind, isBound }, SResizeHandle.TYPE, SResizeHandleView);

    // reconnect edge tool feedback
    configureCommand({ bind, isBound }, ShowEdgeReconnectHandlesFeedbackCommand);
    configureCommand({ bind, isBound }, HideEdgeReconnectHandlesFeedbackCommand);
    configureCommand({ bind, isBound }, DrawFeedbackEdgeSourceCommand);

    configureCommand({ bind, isBound }, SwitchRoutingModeCommand);

    bind(TYPES.IVNodePostprocessor).to(LocationPostprocessor);
    bind(TYPES.HiddenVNodePostprocessor).to(LocationPostprocessor);
});

export default toolFeedbackModule;
