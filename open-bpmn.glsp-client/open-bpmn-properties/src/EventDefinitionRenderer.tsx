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
// import React from 'react';
import * as React from 'react';
import { withJsonFormsControlProps } from '@jsonforms/react';
import {
  scopeEndsWith,
  rankWith
} from '@jsonforms/core';
/**
 * This custom renderer provides the detail form for BPMN Event Definition lists.
 *
 */
interface BPMNEventDefinitionProps {
  value: any;
  updateValue: (newValue: any) => void;
}

interface BPMNEventDefinitionControlProps {
  data: any;
  handleChange(path: string, value: any): void;
  path: string;
}

// eslint-disable-next-line max-len
const BPMNEventDefinitionsComponent: React.FC<BPMNEventDefinitionProps> = ({ value, updateValue }: any) => (
    <div id='#/properties/conditions' className='eventDefinitions'>
      
      <div style={{ cursor: 'pointer', fontSize: '18px' }}>
      <span
              onClick={() => updateValue(value)}
            >
      Event Definitions 
      </span> 
      </div>
    </div>
);

const BPMNEventDefinitionControl = ({ data, handleChange, path }: BPMNEventDefinitionControlProps) => (
  <BPMNEventDefinitionsComponent
    value={data}
    updateValue={(newValue: number) => handleChange(path, newValue)}
  />
);

/**
 * Export the Custom Renderer and  Tester for EventDefinitions (eventdefinitions)
 */
export const BPMNEventDefinitionRenderer: any = { 
  tester: rankWith(7,scopeEndsWith('conditions')), 
  renderer: withJsonFormsControlProps(BPMNEventDefinitionControl)
};



