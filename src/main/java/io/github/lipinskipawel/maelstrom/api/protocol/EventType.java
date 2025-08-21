package io.github.lipinskipawel.maelstrom.api.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public non-sealed class EventType implements BaseWorkload {
    public String type;
    @JsonProperty("msg_id")
    public int msgId;
    @JsonProperty("in_reply_to")
    public int inReplyTo;

    public EventType(String type) {
        this.type = type;
    }

    @Override
    public int msgId() {
        return msgId;
    }

    @Override
    public int inReplyTo() {
        return inReplyTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventType eventType = (EventType) o;
        return msgId == eventType.msgId && inReplyTo == eventType.inReplyTo && Objects.equals(type, eventType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, msgId, inReplyTo);
    }
}
