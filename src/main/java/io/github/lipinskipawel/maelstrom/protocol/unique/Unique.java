package io.github.lipinskipawel.maelstrom.protocol.unique;

import io.github.lipinskipawel.maelstrom.protocol.EventType;

public final class Unique extends EventType implements UniqueWorkload {
    public Unique() {
        super("generate");
    }
}
