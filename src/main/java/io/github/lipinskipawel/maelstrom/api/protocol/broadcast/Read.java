package io.github.lipinskipawel.maelstrom.api.protocol.broadcast;

import io.github.lipinskipawel.maelstrom.api.protocol.EventType;

public final class Read extends EventType implements BroadcastWorkload {
    public Read() {
        super("read");
    }
}
