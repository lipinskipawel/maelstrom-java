package io.github.lipinskipawel.maelstrom.api.protocol.unique;

import io.github.lipinskipawel.maelstrom.api.protocol.EventType;

public final class Unique extends EventType implements UniqueWorkload {
    public Unique() {
        super("generate");
    }
}
