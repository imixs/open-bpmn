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
  computeLabel,
  ControlProps,
  isDescriptionHidden,
  OwnPropsOfEnum
} from '@jsonforms/core';
import { VanillaRendererProps } from '@jsonforms/vanilla-renderers';
import merge from 'lodash/merge';
import React, { useState } from 'react';

export const ImixsRadioGroup = ({
  classNames,
  id,
  label,
  options,
  required,
  description,
  errors,
  data,
  uischema,
  visible,
  config,
  enabled,
  path,
  handleChange
}: ControlProps & VanillaRendererProps & OwnPropsOfEnum) => {
  const [isFocused, setFocus] = useState(false);
  const isValid = errors.length === 0;
  const appliedUiSchemaOptions = merge({}, config, uischema.options);
  const showDescription = !isDescriptionHidden(
    visible,
    description,
    isFocused,
    appliedUiSchemaOptions.showUnfocusedDescription
  );

  let groupStyle: { [x: string]: any } = {};
  // compute flexDirection based on the optional option 'orientation=vertical|horizontal'
    groupStyle = {
      display: 'flex',
      flexDirection: ('vertical' === uischema!.options!.orientation) ? 'column' : 'row'
    };

  return (
    <div
      className={'control imixs-radio-group'}
      hidden={!visible}
      onFocus={() => setFocus(true)}
      onBlur={() => setFocus(false)}
    >
      <label htmlFor={id} className={'imixs'}>
        {computeLabel(
          label,
          false,
          appliedUiSchemaOptions.hideRequiredAsterisk
        )}
      </label>
      <div style={groupStyle}>
        {options!.map(option => (
          <div key={option.label}>
            <input
              type='radio'
              value={option.value}
              id={option.value}
              name={id}
              checked={data === option.value}
              onChange={ev => handleChange(path, ev.currentTarget.value)}
              disabled={!enabled}
            />
            <label
              htmlFor={option.value}
            >
              {option.label}
            </label>
          </div>
        ))}
      </div>
      <div className={'input-description'}>
        {!isValid ? errors : showDescription ? description : undefined}
      </div>
    </div>
  );
};

