package com.github.lipinskipawel.maelstrom.protocol.unique;

import com.github.lipinskipawel.maelstrom.protocol.EventType;

import java.util.UUID;

public final class UniqueOk extends EventType {
    public UUID id;

    public UniqueOk(UUID uuid) {
        super("generate_ok");
        this.id = uuid;
    }
}
