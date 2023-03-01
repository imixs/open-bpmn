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
// eslint-disable-next-line @typescript-eslint/explicit-function-return-type
export const ImixsCheckboxGroup = ({
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

  // onChange={ev => handleChange(path, ev.currentTarget.value)}
  const handleCheckboxChange = (path,value) => {
      console.log('Hello Checkbox Click');
      console.log('path='+path);
      console.log('value='+value);
      // add value only if not yed done
      if (!data.includes(value)) {
        data.push(value);
      } else {
        console.log('we unchcke it');
        const ipos = data.indexOf(value);
        if (ipos !== -1) {
          data.splice(ipos, 1);
        }
      }
      // call main handler
      handleChange(path, data);
  };

  return (
    <div
      className={'control imixs-checkbox-group'}
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
            <h4>{data.length}</h4>
            <input
              type='checkbox'
              value={option.value}
              id={option.value}
              name={id}
              checked={data.includes(option.value)}
              onChange={ev => handleCheckboxChange(path, ev.currentTarget.value)}
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

