package io.github.lipinskipawel.maelstrom.protocol.broadcast;

import io.github.lipinskipawel.maelstrom.protocol.EventType;

public final class Read extends EventType implements BroadcastWorkload {
    public Read() {
        super("read");
    }
}
