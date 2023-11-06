package com.github.lipinskipawel.maelstrom.protocol.broadcast;

import com.github.lipinskipawel.maelstrom.protocol.EventType;

public final class Read extends EventType implements BroadcastWorkload {
    public Read() {
        super("read");
    }
}
