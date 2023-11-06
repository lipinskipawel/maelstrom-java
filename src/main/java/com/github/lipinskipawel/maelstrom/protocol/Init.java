package com.github.lipinskipawel.maelstrom.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lipinskipawel.maelstrom.protocol.broadcast.BroadcastWorkload;
import com.github.lipinskipawel.maelstrom.protocol.echo.EchoWorkload;
import com.github.lipinskipawel.maelstrom.protocol.unique.UniqueWorkload;

import java.util.List;
import java.util.Objects;

public final class Init extends EventType implements EchoWorkload, UniqueWorkload, BroadcastWorkload {
    @JsonProperty("node_id")
    public String nodeId;
    @JsonProperty("node_ids")
    public List<String> nodeIds;

    public Init() {
        super("init");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Init init = (Init) o;
        return Objects.equals(nodeId, init.nodeId) && Objects.equals(nodeIds, init.nodeIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nodeId, nodeIds);
    }
}
