package io.github.lipinskipawel.maelstrom.protocol.broadcast;

import io.github.lipinskipawel.maelstrom.protocol.EventType;

public final class TopologyOk extends EventType {
    public TopologyOk() {
        super("topology_ok");
    }
}