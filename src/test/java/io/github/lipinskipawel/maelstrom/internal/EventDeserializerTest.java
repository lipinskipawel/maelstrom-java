package io.github.lipinskipawel.maelstrom.internal;

import io.github.lipinskipawel.maelstrom.api.protocol.Event;
import io.github.lipinskipawel.maelstrom.api.protocol.EventType;
import io.github.lipinskipawel.maelstrom.api.protocol.Init;
import io.github.lipinskipawel.maelstrom.api.protocol.InitOk;
import io.github.lipinskipawel.maelstrom.api.protocol.echo.Echo;
import io.github.lipinskipawel.maelstrom.api.protocol.echo.EchoOk;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.json;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;

class EventDeserializerTest implements WithAssertions {

    static {
        JsonSupport.createJsonMapper(Map.of(
            "echo_ok", EchoOk.class
        ));
    }

    @Test
    void should_serialize_event() {
        var event = Event.createEvent(1, "c2", "n0", new InitOk());

        var json = JsonSupport.writeEvent(event);

        assertThatJson(json)
            .when(IGNORING_ARRAY_ORDER)
            .isEqualTo(json("""
                {
                    "id": 1,
                    "src": "c2",
                    "dest": "n0",
                    "body": {
                        "type": "init_ok",
                        "msg_id": 0,
                        "in_reply_to": 0
                    }
                }"""));
    }

    @Test
    void should_deserialize_event() {
        var json = """
            {
                "id": 1,
                "src": "c2",
                "dest": "n0",
                "body": {
                    "type" : "echo_ok",
                    "echo": "please-echo"
                }
            }""";

        var event = JsonSupport.readEvent(json);

        var expected = Event.createEvent(1, "c2", "n0", new EchoOk("please-echo"));
        assertThat(event)
            .isInstanceOf(Event.class)
            .isEqualTo(expected);
    }

    @Test
    void should_deserialize_event_with_cast() {
        var json = """
            {
                "id": 1,
                "src": "c2",
                "dest": "n0",
                "body": {
                    "type" : "echo_ok",
                    "echo": "please-echo"
                }
            }""";

        var event = JsonSupport.readEvent(json);

        var expected = Event.createEvent(1, "c2", "n0", event.body);
        assertThat(event)
            .isInstanceOf(Event.class)
            .isEqualTo(expected);
    }

    @Test
    void should_deserialize_event_with_type_reference() {
        var json = """
            {
                "id": 1,
                "src": "c2",
                "dest": "n0",
                "body": {
                    "type" : "echo_ok",
                    "echo": "please-echo"
                }
            }""";

        var event = JsonSupport.readEvent(json);

        var expected = Event.createEvent(1, "c2", "n0", new EchoOk("please-echo"));
        assertThat(event)
            .isInstanceOf(Event.class)
            .isEqualTo(expected);
    }

    @Test
    void should_deserialize_event_with_instance_of() {
        var json = """
            {
                "id": 1,
                "src": "c2",
                "dest": "n0",
                "body": {
                    "type" : "echo_ok",
                    "echo": "please-echo"
                }
            }""";

        var event = JsonSupport.readEvent(json);

        EventType echoBody = (EventType) event.body;
        if (echoBody instanceof Init) {
            fail("it is not a init event");
        }
        if (echoBody instanceof Echo) {
            fail("it is not a echo event");
        }

        var expected = Event.createEvent(1, "c2", "n0", new EchoOk("please-echo"));
        assertThat(event)
            .isInstanceOf(Event.class)
            .isEqualTo(expected);
    }
}
