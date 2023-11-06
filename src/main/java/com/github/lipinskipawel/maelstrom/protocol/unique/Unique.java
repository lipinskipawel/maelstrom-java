package com.github.lipinskipawel.maelstrom.protocol.unique;

import com.github.lipinskipawel.maelstrom.protocol.EventType;

public final class Unique extends EventType implements UniqueWorkload {
    public Unique() {
        super("generate");
    }
}
