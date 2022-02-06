/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
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

import { expect } from 'chai';
import { IMarqueeBehavior, MarqueeUtil } from './marquee-behavior';

describe('MarqueeUtil', () => {
    function initUtil(marqueeBehavior?: IMarqueeBehavior): MarqueeUtil {
        const util = new MarqueeUtil(marqueeBehavior);
        util.updateStartPoint({ x: 0, y: 0 });
        util.updateCurrentPoint({ x: 0, y: 0 });
        return util;
    }

    describe('Node marquee', () => {
        const node = { x: 20, y: 20, width: 200, height: 50 };

        it('touch element', () => {
            const util = initUtil();
            expect(util.isNodeMarked(node)).is.equals(false);
            util.updateCurrentPoint({ x: 30, y: 50 });
            expect(util.isNodeMarked(node)).is.equals(true);
            util.updateCurrentPoint({ x: 250, y: 85 });
            expect(util.isNodeMarked(node)).is.equals(true);
            util.updateStartPoint({ x: 30, y: 25 });
            expect(util.isNodeMarked(node)).is.equals(true);
        });

        it('touch element (reverse)', () => {
            const util = initUtil();
            expect(util.isNodeMarked(node)).is.equals(false);
            util.updateStartPoint({ x: 30, y: 50 });
            expect(util.isNodeMarked(node)).is.equals(true);
            util.updateStartPoint({ x: 250, y: 85 });
            expect(util.isNodeMarked(node)).is.equals(true);
            util.updateCurrentPoint({ x: 30, y: 25 });
            expect(util.isNodeMarked(node)).is.equals(true);
        });

        it('mark entire element', () => {
            const util = initUtil({ entireElement: true, entireEdge: false });
            expect(util.isNodeMarked(node)).is.equals(false);
            util.updateCurrentPoint({ x: 30, y: 50 });
            expect(util.isNodeMarked(node)).is.equals(false);
            util.updateCurrentPoint({ x: 250, y: 85 });
            expect(util.isNodeMarked(node)).is.equals(true);
            util.updateStartPoint({ x: 30, y: 85 });
            expect(util.isNodeMarked(node)).is.equals(false);
        });

        it('mark entire element (reverse)', () => {
            const util = initUtil({ entireElement: true, entireEdge: false });
            expect(util.isNodeMarked(node)).is.equals(false);
            util.updateStartPoint({ x: 30, y: 50 });
            expect(util.isNodeMarked(node)).is.equals(false);
            util.updateStartPoint({ x: 250, y: 85 });
            expect(util.isNodeMarked(node)).is.equals(true);
            util.updateCurrentPoint({ x: 30, y: 85 });
            expect(util.isNodeMarked(node)).is.equals(false);
        });
    });

    describe('Edge marquee', () => {
        const edge = [
            { x: 20, y: 40 },
            { x: 40, y: 20 }
        ];
        const edge2 = [
            { x: 20, y: 20 },
            { x: 40, y: 40 },
            { x: 50, y: 10 }
        ];

        it('touch edge', () => {
            const util = initUtil();
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
            util.updateCurrentPoint({ x: 20, y: 20 });
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(true);
            util.updateCurrentPoint({ x: 30, y: 30 });
            expect(util.isEdgeMarked(edge)).is.equals(true);
            expect(util.isEdgeMarked(edge2)).is.equals(true);
            util.updateCurrentPoint({ x: 50, y: 50 });
            expect(util.isEdgeMarked(edge)).is.equals(true);
            expect(util.isEdgeMarked(edge2)).is.equals(true);
            util.updateStartPoint({ x: 25, y: 25 });
            expect(util.isEdgeMarked(edge)).is.equals(true);
            expect(util.isEdgeMarked(edge2)).is.equals(true);
        });

        it('touch edge (reverse)', () => {
            const util = initUtil();
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
            util.updateStartPoint({ x: 20, y: 20 });
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(true);
            util.updateStartPoint({ x: 30, y: 30 });
            expect(util.isEdgeMarked(edge)).is.equals(true);
            expect(util.isEdgeMarked(edge2)).is.equals(true);
            util.updateStartPoint({ x: 50, y: 50 });
            expect(util.isEdgeMarked(edge)).is.equals(true);
            expect(util.isEdgeMarked(edge2)).is.equals(true);
            util.updateCurrentPoint({ x: 25, y: 25 });
            expect(util.isEdgeMarked(edge)).is.equals(true);
            expect(util.isEdgeMarked(edge2)).is.equals(true);
        });

        it('mark entire edge', () => {
            const util = initUtil({ entireElement: false, entireEdge: true });
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
            util.updateCurrentPoint({ x: 20, y: 20 });
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
            util.updateCurrentPoint({ x: 30, y: 30 });
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
            util.updateCurrentPoint({ x: 45, y: 50 });
            expect(util.isEdgeMarked(edge)).is.equals(true);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
            util.updateCurrentPoint({ x: 55, y: 50 });
            expect(util.isEdgeMarked(edge)).is.equals(true);
            expect(util.isEdgeMarked(edge2)).is.equals(true);
            util.updateStartPoint({ x: 25, y: 25 });
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
        });

        it('mark entire edge (reverse)', () => {
            const util = initUtil({ entireElement: false, entireEdge: true });
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
            util.updateStartPoint({ x: 20, y: 20 });
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
            util.updateStartPoint({ x: 30, y: 30 });
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
            util.updateStartPoint({ x: 45, y: 50 });
            expect(util.isEdgeMarked(edge)).is.equals(true);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
            util.updateStartPoint({ x: 55, y: 50 });
            expect(util.isEdgeMarked(edge)).is.equals(true);
            expect(util.isEdgeMarked(edge2)).is.equals(true);
            util.updateCurrentPoint({ x: 25, y: 25 });
            expect(util.isEdgeMarked(edge)).is.equals(false);
            expect(util.isEdgeMarked(edge2)).is.equals(false);
        });

        it('edge path should be marked', () => {
            const path = 'M 599.968775008469,272.3972942960205 L 677.0904664855072,232';
            const util = initUtil({ entireElement: false, entireEdge: true });
            expect(util.isEdgePathMarked(path)).is.equals(false);
            util.updateCurrentPoint({ x: 600, y: 300 });
            expect(util.isEdgePathMarked(path)).is.equals(false);
            util.updateCurrentPoint({ x: 700, y: 300 });
            expect(util.isEdgePathMarked(path)).is.equals(true);
        });
    });
});
