package org.openbpmn.glsp;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

/**
 * Session-wide clipboard store, bound as a Singleton in the ServerModule so
 * that it is shared across all diagram sessions (editors) within the same
 * client connection.
 */
public class BPMNClipboardService {

    private List<BPMNClipboardNode> nodes;
    private List<Element> edges;

    public void putNode(final BPMNClipboardNode node) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        nodes.add(node);
    }

    public void putEdge(final Element edge) {
        if (edges == null) {
            edges = new ArrayList<>();
        }
        edges.add(edge);
    }

    public List<BPMNClipboardNode> getNodes() {
        return nodes;
    }

    public List<Element> getEdges() {
        return edges;
    }

    public void clear() {
        nodes = null;
        edges = null;
    }
}