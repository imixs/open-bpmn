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
import { ExternalMarkerManager, IActionDispatcher, Marker, MarkerKind, TYPES } from '@eclipse-glsp/client/lib';
import URI from '@theia/core/lib/common/uri';
import { Container, inject, injectable, optional, postConstruct } from '@theia/core/shared/inversify';
import { ProblemManager } from '@theia/markers/lib/browser/problem/problem-manager';
import { Diagnostic } from 'vscode-languageserver-types';

import { SelectionWithElementIds } from '../theia-opener-options-navigation-service';

export const TheiaMarkerManagerFactory = Symbol('TheiaMarkerManagerFactory');

export function connectTheiaMarkerManager(
    container: Container,
    markerManagerFactory: () => ExternalMarkerManager,
    languageLabel: string
): void {
    const markerManager = markerManagerFactory();
    if (markerManager instanceof ExternalMarkerManager) {
        if (container.isBound(ExternalMarkerManager)) {
            container.rebind(ExternalMarkerManager).toConstantValue(markerManager);
        } else {
            container.bind(ExternalMarkerManager).toConstantValue(markerManager);
        }
        markerManager.languageLabel = languageLabel;
        markerManager.connect(container.get<IActionDispatcher>(TYPES.IActionDispatcher));
    }
}

class DiagnosticMarkers {
    protected diagnotic2marker = new Map<Diagnostic, Marker>();
    get size(): number {
        return this.diagnotic2marker.size;
    }
    all(): IterableIterator<Marker> {
        return this.diagnotic2marker.values();
    }
    marker(diagnostic: Diagnostic): Marker | undefined {
        return this.diagnotic2marker.get(diagnostic);
    }
    add(diagnostic: Diagnostic, marker: Marker): Map<Diagnostic, Marker> {
        return this.diagnotic2marker.set(diagnostic, marker);
    }
    delete(diagnostic: Diagnostic): boolean {
        return this.diagnotic2marker.delete(diagnostic);
    }
    clear(): void {
        return this.diagnotic2marker.clear();
    }
}

@injectable()
export class TheiaMarkerManager extends ExternalMarkerManager {
    @inject(ProblemManager) @optional() protected readonly problemManager?: ProblemManager;

    protected uri2markers = new Map<URI, DiagnosticMarkers>();

    protected markers(uri: URI): DiagnosticMarkers {
        const marker = this.uri2markers.get(uri);
        if (marker === undefined) {
            const newMarker = new DiagnosticMarkers();
            this.uri2markers.set(uri, newMarker);
            return newMarker;
        }
        return marker;
    }

    @postConstruct()
    protected initialize(): void {
        if (this.problemManager) {
            this.problemManager.onDidChangeMarkers(uri => this.refreshMarker(uri));
        }
    }

    protected async refreshMarker(uri: URI): Promise<void> {
        if (this.problemManager === undefined || this.markers(uri).size < 1) {
            return;
        }
        const toDelete = [...this.markers(uri).all()];
        for (const existingMarker of this.problemManager.findMarkers({ uri })) {
            const diagnostic = existingMarker.data;
            const marker = this.markers(uri).marker(diagnostic);
            if (marker) {
                const index = toDelete.indexOf(marker);
                if (index > -1) {
                    toDelete.splice(index, 1);
                } else {
                    this.markers(uri).delete(diagnostic);
                }
            }
        }
        if (toDelete.length > 0) {
            this.removeMarkers(toDelete);
        }
    }

    setMarkers(markers: Marker[], sourceUri?: string): void {
        if (this.problemManager === undefined) {
            return;
        }
        const uri = new URI(sourceUri);
        this.markers(uri).clear();
        this.problemManager.setMarkers(
            uri,
            this.languageLabel,
            markers.map(marker => this.createDiagnostic(uri, marker))
        );
    }

    protected createDiagnostic(uri: URI, marker: Marker): Diagnostic {
        const range = SelectionWithElementIds.createRange([marker.elementId]);
        const diagnostic = Diagnostic.create(range, marker.label, this.toSeverity(marker.kind));
        this.markers(uri).add(diagnostic, marker);
        return diagnostic;
    }

    protected toSeverity(kind: string): 1 | 2 | 3 | 4 | undefined {
        switch (kind) {
            case MarkerKind.ERROR:
                return 1;
            case MarkerKind.WARNING:
                return 2;
            case MarkerKind.INFO:
                return 3;
            default:
                return undefined;
        }
    }
}
