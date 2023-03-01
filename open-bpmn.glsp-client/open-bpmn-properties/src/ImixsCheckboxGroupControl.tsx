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
  ControlProps,
  isEnumControl,
  optionIs, RankedTester, rankWith
} from '@jsonforms/core';
import { withJsonFormsEnumProps } from '@jsonforms/react';
import { VanillaRendererProps, withVanillaControlProps } from '@jsonforms/vanilla-renderers';
import React from 'react';
import { ImixsCheckboxGroup } from './ImixsCheckboxGroup';

/***********************
 * This is a custom Checkbox renderer that allows vertical and horizontal orientation
 * <p>
 * In addition the renderer support label|value pairs separated by a | char.
 *
 */
/* eslint-disable arrow-body-style */
// eslint-disable-next-line @typescript-eslint/explicit-function-return-type
export const ImixsCheckboxGroupControl = (props: ControlProps & VanillaRendererProps) => {
  return <ImixsCheckboxGroup {...props} />;
};

export const imixsCheckboxGroupControlTester: RankedTester = rankWith(
  3,
  and(isEnumControl, optionIs('format', 'checkbox-imixs'))
);
export default withVanillaControlProps(withJsonFormsEnumProps(ImixsCheckboxGroupControl));
