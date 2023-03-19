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
import { Action } from '@eclipse-glsp/protocol';
import { injectable } from 'inversify';
import {
  IActionHandler
} from 'sprotty';

/****************************************************************************
 * This module provides BPMN action listeners for custom behavior.
 *
 ****************************************************************************/

export interface BPMNPropertyAction extends Action {
  kind: typeof BPMNPropertyAction.KIND;
}

export namespace BPMNPropertyAction {
  export const KIND = 'properties';

  export function is(object: any): object is BPMNPropertyAction {
      return Action.hasKind(object, KIND);
  }

  export function create(): BPMNPropertyAction {
      return { kind: KIND };
  }
}

@injectable()
export class BPMNPropertyActionHandler implements IActionHandler {
  handle(action: BPMNPropertyAction): void | BPMNPropertyAction {
    console.log('--------> custom action arrived');
    // implement your custom logic to handle the action
    // Optionally issue a response action
  }
}
