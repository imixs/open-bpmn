package org.openbpmn.glsp.launch;

import org.eclipse.glsp.server.di.ServerModule;
import org.openbpmn.glsp.BPMNClipboardService;

import com.google.inject.Singleton;

/**
 * Custom ServerModule that extends the default GLSP {@link ServerModule}.
 *
 * By default, GLSP creates a new injector for each {@code DiagramModule}
 * (i.e. for each opened diagram/editor). Any binding declared inside
 * {@link org.openbpmn.glsp.BPMNDiagramModule} is therefore scoped per
 * diagram, which makes it impossible to share state (like clipboard data)
 * between two independently opened editors.
 *
 * The {@code ServerModule} however lives one level above the diagram
 * modules and is shared across all diagrams opened within the same client
 * connection. Bindings declared here are therefore visible to every
 * {@code BPMNDiagramModule} instance created for that connection.
 *
 * We use this module to bind {@link org.openbpmn.glsp.BPMNClipboardService}
 * as a Singleton, so that copy/paste data can be exchanged between
 * different diagram editors that are opened at the same time.
 */
public class BPMNServerModule extends ServerModule {

    @Override
    protected void configure() {
        super.configure();
        // Bind the clipboard service as a session-wide singleton so it is shared
        // across all diagram editors within the same client connection.
        bind(BPMNClipboardService.class).in(Singleton.class);
    }
}