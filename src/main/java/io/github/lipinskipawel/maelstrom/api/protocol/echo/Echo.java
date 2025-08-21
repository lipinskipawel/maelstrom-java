package io.github.lipinskipawel.maelstrom.api.protocol.echo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lipinskipawel.maelstrom.api.protocol.EventType;

import java.util.Objects;

public final class Echo extends EventType implements EchoWorkload {
    @JsonProperty("echo")
    public String echo;

    public Echo() {
        super("echo");
    }

    public Echo(String echo) {
        super("echo");
        this.echo = echo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Echo echo1 = (Echo) o;
        return Objects.equals(echo, echo1.echo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), echo);
    }
}
