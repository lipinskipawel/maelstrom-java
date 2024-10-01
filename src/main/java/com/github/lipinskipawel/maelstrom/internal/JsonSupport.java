package com.github.lipinskipawel.maelstrom.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.lipinskipawel.maelstrom.api.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.Event;
import com.github.lipinskipawel.maelstrom.api.protocol.EventType;
import com.github.lipinskipawel.maelstrom.api.protocol.Init;
import com.github.lipinskipawel.maelstrom.api.protocol.InitOk;
import com.github.lipinskipawel.maelstrom.api.protocol.Quit;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.Broadcast;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.BroadcastOk;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.Read;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.ReadOk;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.Topology;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.TopologyOk;
import com.github.lipinskipawel.maelstrom.api.protocol.echo.Echo;
import com.github.lipinskipawel.maelstrom.api.protocol.echo.EchoOk;
import com.github.lipinskipawel.maelstrom.api.protocol.unique.Unique;
import com.github.lipinskipawel.maelstrom.api.protocol.unique.UniqueOk;

import java.util.HashMap;
import java.util.Map;

public final class JsonSupport {
    private static final Map<String, Class<? extends EventType>> POSSIBLE_TYPES = createMappings();
    private static ObjectMapper staticMapper = createObjectMapper(POSSIBLE_TYPES);

    private JsonSupport() {
    }

    public static void jsonSupportRegisterCustomTypes(Map<String, Class<? extends EventType>> customTypes) {
        POSSIBLE_TYPES.putAll(customTypes);
        staticMapper = createObjectMapper(POSSIBLE_TYPES);
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseWorkload> Event<T> readEvent(String event) {
        try {
            return staticMapper.readValue(event, Event.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String writeEvent(Event<?> event) {
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

    private static ObjectMapper createObjectMapper(Map<String, Class<? extends EventType>> customTypes) {
        return new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(Event.class, new EventDeserializer(customTypes))
        );
    }
}
