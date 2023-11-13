package com.github.lipinskipawel.maelstrom.api.protocol.broadcast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lipinskipawel.maelstrom.api.protocol.EventType;

import java.util.Objects;

public final class Broadcast extends EventType implements BroadcastWorkload {
    @JsonProperty("message")
    public Integer message;

    public Broadcast() {
        super("broadcast");
    }

    public Broadcast(Integer message) {
        super("broadcast");
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Broadcast broadcast = (Broadcast) o;
        return Objects.equals(message, broadcast.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), message);
    }
}
