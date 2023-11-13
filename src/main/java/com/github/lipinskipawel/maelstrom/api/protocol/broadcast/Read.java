package com.github.lipinskipawel.maelstrom.api.protocol.broadcast;

import com.github.lipinskipawel.maelstrom.api.protocol.EventType;

public final class Read extends EventType implements BroadcastWorkload {
    public Read() {
        super("read");
    }
}
