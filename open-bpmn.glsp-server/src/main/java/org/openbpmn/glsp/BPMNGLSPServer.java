package org.openbpmn.glsp;

import static org.eclipse.glsp.server.types.GLSPServerException.getOrThrow;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.glsp.server.protocol.DefaultGLSPServer;
import org.eclipse.glsp.server.protocol.InitializeResult;
import org.eclipse.glsp.server.utils.MapUtil;

public class BPMNGLSPServer extends DefaultGLSPServer {
    private static final Logger LOGGER = LogManager.getLogger(BPMNGLSPServer.class);
    private static final String MESSAGE_KEY = "message";
    private static final String TIMESTAMP_KEY = "timestamp";

    @Override
    public CompletableFuture<InitializeResult> handleIntializeArgs(final InitializeResult result,
            final Map<String, String> args) {
        CompletableFuture<InitializeResult> completableResult = CompletableFuture.completedFuture(result);
        if (args.isEmpty()) {
            return completableResult;
        }

        String timestamp = getOrThrow(MapUtil.getValue(args, TIMESTAMP_KEY),
                "No value present for the given key: " + TIMESTAMP_KEY);
        String message = getOrThrow(MapUtil.getValue(args, MESSAGE_KEY),
                "No value present for the given key: " + MESSAGE_KEY);
        LOGGER.debug(timestamp + ": " + message);

        return completableResult;
    }
}