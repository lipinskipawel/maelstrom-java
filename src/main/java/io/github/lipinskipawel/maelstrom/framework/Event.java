package io.github.lipinskipawel.maelstrom.framework;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lipinskipawel.maelstrom.protocol.BaseWorkload;
import io.github.lipinskipawel.maelstrom.protocol.EventType;

import java.util.Objects;

public final class Event<T extends BaseWorkload> {
    @JsonProperty("id")
    public int id;
    @JsonProperty("src")
    public String src;
    @JsonProperty("dest")
    public String dst;
    @JsonProperty("body")
    public T body;

    Event(T body) {
        this.body = body;
    }

    private Event(int id, String src, String dest, T body) {
        this.id = id;
        this.src = src;
        this.dst = dest;
        this.body = body;
    }

    public static <T extends BaseWorkload> Event<T> createEvent(int id, String src, String dest, T body) {
        return new Event<>(id, src, dest, body);
    }

    <E extends EventType> Event<E> reply(E body) {
        final var response = new Event<>(body);
        response.id = this.id;
        response.src = this.dst;
        response.dst = this.src;
        response.body.inReplyTo = this.body.msgId();
        response.body.msgId = this.body.msgId() + 1;
        return response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event<?> event = (Event<?>) o;
        return id == event.id && Objects.equals(src, event.src) && Objects.equals(dst, event.dst) && Objects.equals(body, event.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, src, dst, body);
    }
}
