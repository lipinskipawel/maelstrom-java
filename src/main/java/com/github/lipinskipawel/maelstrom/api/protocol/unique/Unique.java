package com.github.lipinskipawel.maelstrom.api.protocol.unique;

import com.github.lipinskipawel.maelstrom.api.protocol.EventType;

public final class Unique extends EventType implements UniqueWorkload {
    public Unique() {
        super("generate");
    }
}
