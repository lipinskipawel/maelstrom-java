package io.github.lipinskipawel.maelstrom.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.lipinskipawel.maelstrom.api.protocol.Event;
import io.github.lipinskipawel.maelstrom.api.protocol.EventType;

import java.io.IOException;
import java.util.Map;

final class EventDeserializer extends JsonDeserializer<Event<?>> {
    private final Map<String, Class<? extends EventType>> supportedTypes;

    EventDeserializer(Map<String, Class<? extends EventType>> supportedTypes) {
        this.supportedTypes = supportedTypes;
    }

    @Override
    public Event<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        final JsonNode tree = p.getCodec().readTree(p);

        final var id = tree.get("id").asInt();
        final var src = tree.get("src").asText();
        final var dst = tree.get("dest").asText();
        final var bodyNode = tree.get("body");
        final var intoType = supportedTypes.get(bodyNode.get("type").asText());

        final var object = p.getCodec().readValue(bodyNode.traverse(), intoType);

        return Event.createEvent(id, src, dst, object);
    }
}
