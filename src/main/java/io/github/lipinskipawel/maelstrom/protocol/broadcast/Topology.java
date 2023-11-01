package io.github.lipinskipawel.maelstrom.protocol.broadcast;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lipinskipawel.maelstrom.protocol.EventType;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Topology extends EventType implements BroadcastWorkload {
    @JsonProperty("topology")
    public Map<String, List<String>> topology;

    public Topology() {
        super("topology");
    }

    public Topology(Map<String, List<String>> topology) {
        super("topology");
        this.topology = topology;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Topology topology1 = (Topology) o;
        return Objects.equals(topology, topology1.topology);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), topology);
    }
}
