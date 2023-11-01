package io.github.lipinskipawel.maelstrom.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.lipinskipawel.maelstrom.protocol.BaseWorkload;
import io.github.lipinskipawel.maelstrom.protocol.EventType;
import io.github.lipinskipawel.maelstrom.protocol.Init;
import io.github.lipinskipawel.maelstrom.protocol.InitOk;
import io.github.lipinskipawel.maelstrom.protocol.Quit;
import io.github.lipinskipawel.maelstrom.protocol.broadcast.Broadcast;
import io.github.lipinskipawel.maelstrom.protocol.broadcast.BroadcastOk;
import io.github.lipinskipawel.maelstrom.protocol.broadcast.Read;
import io.github.lipinskipawel.maelstrom.protocol.broadcast.ReadOk;
import io.github.lipinskipawel.maelstrom.protocol.broadcast.Topology;
import io.github.lipinskipawel.maelstrom.protocol.broadcast.TopologyOk;
import io.github.lipinskipawel.maelstrom.protocol.echo.Echo;
import io.github.lipinskipawel.maelstrom.protocol.echo.EchoOk;
import io.github.lipinskipawel.maelstrom.protocol.unique.Unique;
import io.github.lipinskipawel.maelstrom.protocol.unique.UniqueOk;

import java.util.HashMap;
import java.util.Map;

final class JsonSupport {
    private static final Map<String, Class<? extends EventType>> POSSIBLE_TYPES = createMappings();
    private static ObjectMapper staticMapper = new ObjectMapper();
    private final ObjectMapper mapper;

    JsonSupport(Map<String, Class<? extends EventType>> customTypes) {
        POSSIBLE_TYPES.putAll(customTypes);
        mapper = new ObjectMapper().registerModule(
                new SimpleModule().addDeserializer(Event.class, new EventDeserializer(POSSIBLE_TYPES))
        );
        staticMapper = mapper.copy();
    }

    @SuppressWarnings("unchecked")
    <T extends BaseWorkload> Event<T> readRequest(String request) {
        try {
            return mapper.readValue(request, Event.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    static String writeRequest(Event<?> event) {
        try {
            return staticMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Class<? extends EventType>> createMappings() {
        final var mappings = new HashMap<String, Class<? extends EventType>>();
        mappings.put("init", Init.class);
        mappings.put("init_ok", InitOk.class);
        mappings.put("quit", Quit.class);
        mappings.put("echo", Echo.class);
        mappings.put("echo_ok", EchoOk.class);
        mappings.put("generate", Unique.class);
        mappings.put("generate_ok", UniqueOk.class);
        mappings.put("broadcast", Broadcast.class);
        mappings.put("broadcast_ok", BroadcastOk.class);
        mappings.put("read", Read.class);
        mappings.put("read_ok", ReadOk.class);
        mappings.put("topology", Topology.class);
        mappings.put("topology_ok", TopologyOk.class);
        return mappings;
    }
}
