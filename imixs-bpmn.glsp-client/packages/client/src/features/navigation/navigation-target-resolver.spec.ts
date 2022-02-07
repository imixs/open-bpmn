/********************************************************************************
 * Copyright (c) 2020-2021 EclipseSource and others.
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
/* eslint-disable no-unused-expressions */
import { NavigationTarget } from '@eclipse-glsp/protocol';
import { expect } from 'chai';
import 'mocha';
import 'reflect-metadata';

describe('NavigationTarget', () => {
    it('should be able to set and get element IDs', () => {
        const navigationTarget: NavigationTarget = { uri: 'uri' };
        NavigationTarget.setElementIds(navigationTarget, ['id1', 'id2']);
        expect(NavigationTarget.getElementIds(navigationTarget)).to.be.eql(['id1', 'id2']);
    });

    it('should be able to set and get textual positions', () => {
        const navigationTarget: NavigationTarget = { uri: 'uri' };
        NavigationTarget.setTextPosition(navigationTarget, { line: 1, character: 2 });
        expect(NavigationTarget.getTextPosition(navigationTarget)).to.be.eql({ line: 1, character: 2 });
    });

    it('should be able to set and get custom query arguments', () => {
        const navigationTarget: NavigationTarget = { uri: 'uri' };
        NavigationTarget.addArgument(navigationTarget, 'name', 'test');
        expect(navigationTarget.args!.name).to.be.eql('test');
    });

    it('should specify whether it has arguments', () => {
        let navigationTarget: NavigationTarget = { uri: 'uri' };
        expect(NavigationTarget.hasArguments(navigationTarget)).to.be.false;
        NavigationTarget.addArgument(navigationTarget, 'name', 'test');
        expect(NavigationTarget.hasArguments(navigationTarget)).to.be.true;

        navigationTarget = { uri: 'uri' };
        expect(NavigationTarget.hasArguments(navigationTarget)).to.be.false;
        NavigationTarget.setElementIds(navigationTarget, ['id1', 'id2']);
        expect(NavigationTarget.hasArguments(navigationTarget)).to.be.true;

        navigationTarget = { uri: 'uri' };
        expect(NavigationTarget.hasArguments(navigationTarget)).to.be.false;
        NavigationTarget.setTextPosition(navigationTarget, { line: 1, character: 2 });
        expect(NavigationTarget.hasArguments(navigationTarget)).to.be.true;
    });
});
