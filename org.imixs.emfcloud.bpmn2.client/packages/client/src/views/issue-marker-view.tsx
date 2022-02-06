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
/* eslint-disable max-len */
import { injectable } from 'inversify';
import { VNode } from 'snabbdom';
import { IssueMarkerView, RenderingContext, setClass, SIssueMarker, SIssueSeverity, svg } from 'sprotty';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const JSX = { createElement: svg };

@injectable()
export class GIssueMarkerView extends IssueMarkerView {
    render(marker: SIssueMarker, _context: RenderingContext): VNode {
        const maxSeverity = super.getMaxSeverity(marker);
        const group = (
            <g class-sprotty-issue={true}>
                <g>
                    {this.getGlspIssueMarkerBackground(maxSeverity)}
                    <path d={this.getGlspIssueMarkerPath(maxSeverity)} />
                </g>
            </g>
        );
        setClass(group, 'sprotty-' + maxSeverity, true);
        return group;
    }

    protected getGlspIssueMarkerBackground(severity: SIssueSeverity): VNode {
        switch (severity) {
            case 'warning':
                return <polygon class-sprotty-issue-background={true} points='8 2, 15.9 14.9, 0.2 14.9' />;
            case 'error':
            case 'info':
                return <circle class-sprotty-issue-background={true} r={this.radius} cx={this.radius} cy={this.radius} />;
        }
    }

    protected get radius(): number {
        return 8; // var(--theia-icon-size)=16px => 16/2=8
    }

    protected getGlspIssueMarkerPath(severity: SIssueSeverity): string {
        switch (severity) {
            // paths used here are svg versions of codicons, resized to var(--theia-icon-size) 16px
            case 'error': // 'codicon-error'
                return 'm 8.8527788,0.00699568 c 1.8523692,0.115773 3.5889642,1.04195762 4.8624692,2.31546152 1.505048,1.620823 2.31546,3.5889653 2.31546,5.9044268 0,1.852369 -0.694637,3.588965 -1.852367,5.094015 -1.157733,1.389277 -2.778554,2.431235 -4.6309235,2.778554 C 7.6950478,16.446772 5.8426784,16.215226 4.2218552,15.289042 2.6010319,14.362857 1.3275281,12.97358 0.63288942,11.236984 -0.06174907,9.5003878 -0.17752216,7.5322456 0.4013433,5.7956494 0.98020876,3.9432802 2.0221665,2.4382302 3.6429898,1.3962725 5.14804,0.35431486 7.0004094,-0.10877742 8.8527788,0.00699568 Z M 9.4316445,14.941722 c 1.5050505,-0.347319 2.8943275,-1.15773 3.9362845,-2.431234 0.926185,-1.273504 1.50505,-2.778554 1.389277,-4.3993771 0,-1.8523692 -0.69464,-3.7047384 -1.968142,-4.9782422 C 11.631331,1.9751379 10.242054,1.2804995 8.6212324,1.1647264 7.1161824,1.0489533 5.4953592,1.3962725 4.2218552,2.3224572 2.9483511,3.2486418 2.0221665,4.5221456 1.5590742,6.1429686 c -0.4630923,1.50505 -0.4630923,3.1258731 0.2315462,4.6309234 0.6946384,1.50505 1.7365963,2.66278 3.1258734,3.473192 1.3892769,0.810411 3.0101002,1.041958 4.5151507,0.694638 z M 8.042367,7.5322456 10.82092,4.6379187 11.631331,5.4483302 8.8527788,8.3426571 11.631331,11.236984 10.82092,12.047395 8.042367,9.1530686 5.2638128,12.047395 4.4534013,11.236984 7.2319555,8.3426571 4.4534013,5.4483302 5.2638128,4.6379187 Z';
            case 'warning': // 'codicon-warning'
                return 'M 7.5587159,0.61312771 H 8.565869 L 16.050847,14.644601 15.54727,15.491525 H 0.55442396 L 0.05084746,14.644601 Z M 8.0622924,2.0780776 1.5157974,14.347033 H 14.585897 Z M 8.7776,13.202541 V 12.058049 H 7.3469848 v 1.144492 z M 7.3469848,10.913557 V 6.3355883 H 8.7776 v 4.5779687 z';
            case 'info': // 'codicon-info'
                return 'M 8.7624795,0.01729008 A 7.8878137,7.8878137 0 0 1 13.625085,2.3592747 8.1894066,8.1894066 0 0 1 14.158672,13.251418 7.9458123,7.9458123 0 0 1 4.2061071,15.26977 8.1198082,8.1198082 0 0 1 0.58699265,11.209866 8.259005,8.259005 0 0 1 0.32019895,5.7463948 8.1198082,8.1198082 0 0 1 3.533323,1.3500986 7.8878137,7.8878137 0 0 1 8.7624795,0.01729008 Z M 9.3099868,14.921778 A 6.8322386,6.8322386 0 0 0 13.265493,12.520635 7.0410337,7.0410337 0 0 0 12.801504,3.1712556 6.7510406,6.7510406 0 0 0 4.1829077,2.3128758 7.029434,7.029434 0 0 0 4.762894,14.248994 6.7394408,6.7394408 0 0 0 9.3099868,14.921778 Z M 7.3786322,5.7811939 H 8.8285979 V 4.6212213 H 7.3786322 Z M 8.8285979,6.9411665 V 11.581057 H 7.3786322 V 6.9411665 Z';
        }
    }
}
