package com.github.lipinskipawel.maelstrom.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.lipinskipawel.maelstrom.api.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.Event;
import com.github.lipinskipawel.maelstrom.api.protocol.EventType;

import java.util.Map;

public final class JsonSupport {
    private static ObjectMapper MAPPER;

    private JsonSupport() {
    }

    public static void createJsonMapper(Map<String, Class<? extends EventType>> customTypes) {
        MAPPER = createObjectMapper(customTypes);
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseWorkload> Event<T> readEvent(String event) {
        try {
            return MAPPER.readValue(event, Event.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String writeEvent(Event<?> event) {
        try {
            return MAPPER.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper createObjectMapper(Map<String, Class<? extends EventType>> customTypes) {
        return new ObjectMapper().registerModule(
            new SimpleModule().addDeserializer(Event.class, new EventDeserializer(customTypes))
        );
    }
}
