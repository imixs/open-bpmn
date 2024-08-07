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
    FeatureModule,
    TYPES,
    configureActionHandler
} from '@eclipse-glsp/client';
import {
    BPMNPropertiesToggleAction,
    BPMNPropertiesUpdateAction,
    BPMNPropertyPanel
} from './bpmn-property-panel';
// css styles
import '../css/bpmn-properties.css';
import '../css/jsonforms-theia.css';

export const BPMNPropertyModule = new FeatureModule((bind, unbind, isBound, rebind) => {
    const context = { bind, unbind, isBound, rebind };

    bind(BPMNPropertyPanel).toSelf().inSingletonScope();
    bind(TYPES.IUIExtension).toService(BPMNPropertyPanel);
    bind(TYPES.IDiagramStartup).toService(BPMNPropertyPanel);
    bind(TYPES.ISelectionListener).toService(BPMNPropertyPanel);

    configureActionHandler(context, BPMNPropertiesToggleAction.KIND, BPMNPropertyPanel);
    configureActionHandler(context, BPMNPropertiesUpdateAction.KIND, BPMNPropertyPanel);
});

export {
    BPMNPropertiesMouseListener, BPMNPropertiesToggleAction, BPMNPropertyPanel
} from './bpmn-property-panel';

