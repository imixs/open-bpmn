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
  and,
  computeLabel,
  ControlProps,
  isDescriptionHidden,
  JsonFormsRendererRegistryEntry, optionIs, OwnPropsOfEnum, rankWith
} from '@jsonforms/core';
import { withJsonFormsEnumProps } from '@jsonforms/react';
import { VanillaRendererProps, withVanillaControlProps } from '@jsonforms/vanilla-renderers';
import merge from 'lodash/merge';
import React, { useState } from 'react';

/***********************
 * This is a custom renderer for textare which can be optional linked to a file.
 * The optional file path is identified if the content starts with 'file://'
 * <p>
 * This allows to edit the text content in a separate file within the same IDE.
 * <p>
 */

/**
 * A Custom Textarea linking to a file
 * @param props
 * @returns
 */
// eslint-disable-next-line @typescript-eslint/explicit-function-return-type
export const TextFileEditor = ({
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
  const appliedUiSchemaOptions = merge({}, config, uischema.options);
  const showDescription = !isDescriptionHidden(
    visible,
    description,
    isFocused,
    appliedUiSchemaOptions.showUnfocusedDescription
  );

  return (
    <div
      className={'control  text-file-editor'}
      hidden={!visible}
      onFocus={() => setFocus(true)}
      onBlur={() => setFocus(false)}
    >
      <label htmlFor={id}>
          <span className={'file-selector-label'}>{computeLabel(
            label,
            false,
            appliedUiSchemaOptions.hideRequiredAsterisk
          )}
          </span>
          <span className={'file-selector'}>
            <label
              title='Link the content to a filename starting with file://'
            >File Link: {(data.startsWith('file://'))?'ACTIVE':'NONE'}</label>
          </span>
        </label>

      <textarea
        id={id}
        disabled={!enabled}
        autoFocus={uischema.options && uischema.options.focus}
        value={data}
        className={(data.startsWith('file://'))?'fileInputMode':''}
        onChange={ev => {
            // change layout depending on input mode
            const fileSelector = ev.target.closest('div')?.querySelector('span.file-selector');
            if (ev.target.value.startsWith('file://')) {
              ev.target.classList.add('fileInputMode');
              if (fileSelector) {
                fileSelector.textContent='File Link: ACTIVE';
              }
            } else {
              ev.target.classList.remove('fileInputMode');
              if (fileSelector) {
                fileSelector.textContent='File Link: NONE';
              }
            }
            handleChange(path, ev.target.value);
          }
        }
      >{data}</textarea>
      <div className={'input-description'}>
        {showDescription ? description : undefined}
      </div>
    </div>
  );
};

// eslint-disable-next-line @typescript-eslint/explicit-function-return-type
export const TextFileEditorControl = (props: ControlProps & VanillaRendererProps) => <TextFileEditor {...props} />;

/**
 * Export the Custom Renderer and  Tester for TextFIleEditorGroupControl
 */

export const TextFileEditorRendererEntry: JsonFormsRendererRegistryEntry = {
  tester: rankWith(3,  and( optionIs('format', 'textFileEditor'))),
  renderer: withVanillaControlProps(withJsonFormsEnumProps(TextFileEditor))
};

// export const textAreaCellTester: RankedTester = rankWith(2, isMultiLineControl);

// export default withJsonFormsCellProps(withVanillaCellProps(TextAreaCell));
