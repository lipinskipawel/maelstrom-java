package io.github.lipinskipawel.maelstrom.api.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lipinskipawel.maelstrom.api.protocol.broadcast.BroadcastWorkload;
import io.github.lipinskipawel.maelstrom.api.protocol.echo.EchoWorkload;
import io.github.lipinskipawel.maelstrom.api.protocol.unique.UniqueWorkload;

import java.util.List;
import java.util.Objects;

public final class Init
    extends EventType
    implements EchoWorkload, UniqueWorkload, BroadcastWorkload {
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
