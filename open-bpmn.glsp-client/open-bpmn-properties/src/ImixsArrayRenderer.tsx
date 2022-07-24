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
//import * as React from 'react';
import React, { useMemo } from 'react';
import range from 'lodash/range';
import { JsonFormsDispatch } from '@jsonforms/react';
import {
  scopeEndsWith,ControlElement,Helpers,
  rankWith,ArrayControlProps, composePaths, createDefaultValue, findUISchema
} from '@jsonforms/core';
import { VanillaRendererProps } from '@jsonforms/vanilla-renderers';

// We rename ''control'' into 'component' 
const BPMNArrayComponent = ({
  classNames,
  data,
  label,
  path,
  schema,
  addItem,
  uischema,
  uischemas,
  renderers,
  rootSchema
}: ArrayControlProps & VanillaRendererProps) => {
  const childUiSchema = useMemo(
    () => findUISchema(uischemas, schema, uischema.scope, path, undefined, uischema, rootSchema),
    [uischemas, schema, uischema.scope, path, uischema, rootSchema]
  );
  return (
    <div className={classNames.wrapper}>
      <fieldset className={classNames.fieldSet}>
        <legend>
          <button
            className={classNames.button}
            onClick={addItem(path, createDefaultValue(schema))}
          >
            +
          </button>
          <label className={'array.label'}>{label}</label>
        </legend>
        <div className={classNames.children}>
          {data ? (
            range(0, data.length).map(index => {
              const childPath = composePaths(path, `${index}`);

              return (
                <JsonFormsDispatch
                  schema={schema}
                  uischema={childUiSchema || uischema}
                  path={childPath}
                  key={childPath}
                  renderers={renderers}
                />
              );
            })
          ) : (
              <p>No data</p>
            )}
        </div>
      </fieldset>
    </div>
  );
};

// we rename 'ControlRenderer' into 'Control'
// eslint-disable-next-line max-len
const BPMNArrayControl =
    ({
        schema,
        uischema,
        data,
        path,
        rootSchema,
        uischemas,
        addItem,
        getStyle,
        getStyleAsClassName,
        removeItems,
        id,
        visible,
        enabled,
        errors
    }: ArrayControlProps & VanillaRendererProps) => {

        const controlElement = uischema as ControlElement;
        const labelDescription = Helpers.createLabelDescriptionFrom(controlElement, schema);
        const label = labelDescription.show ? labelDescription.text : '';
        const controlClassName =
            `control ${(Helpers.convertToValidClassName(controlElement.scope))}`;
        const fieldSetClassName = getStyleAsClassName('array.layout');
        const buttonClassName = getStyleAsClassName('array.button');
        const childrenClassName = getStyleAsClassName('array.children');
        const classNames: { [className: string]: string } = {
            wrapper: controlClassName,
            fieldSet: fieldSetClassName,
            button: buttonClassName,
            children: childrenClassName
        };

        return (
            <BPMNArrayComponent
                errors={errors}
                getStyle={getStyle}
                getStyleAsClassName={getStyleAsClassName}
                removeItems={removeItems}
                classNames={classNames}
                data={data}
                label={label}
                path={path}
                addItem={addItem}
                uischemas={uischemas}
                uischema={uischema}
                schema={schema}
                rootSchema={rootSchema}
                id={id}
                visible={visible}
                enabled={enabled}
            />
        );
    };


/**
 * Export the Custom Renderer and  Tester for EventDefinitions (eventdefinitions)
 */
export const BPMNEventDefinitionRendererPrio: any = { 
  tester: rankWith(6,scopeEndsWith('conditions')), 
  renderer: BPMNArrayControl
  // withJsonFormsControlProps(BPMNArrayControl)
};



