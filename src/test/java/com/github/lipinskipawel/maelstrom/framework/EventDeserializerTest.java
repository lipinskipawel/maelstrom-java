package com.github.lipinskipawel.maelstrom.framework;

import com.github.lipinskipawel.maelstrom.protocol.EventType;
import com.github.lipinskipawel.maelstrom.protocol.Init;
import com.github.lipinskipawel.maelstrom.protocol.InitOk;
import com.github.lipinskipawel.maelstrom.protocol.echo.Echo;
import com.github.lipinskipawel.maelstrom.protocol.echo.EchoOk;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class EventDeserializerTest implements WithAssertions {

    private final JsonSupport mapper = new JsonSupport(Map.of());

    @Test
    void should_serialize_event() {
        var event = new Event<>(new InitOk());
        event.id = 1;
        event.src = "c2";
        event.dst = "n0";

        var json = JsonSupport.writeRequest(event);

        assertThat(json).isEqualTo("""
                {
                    "id": 1,
                    "src": "c2",
                    "dest": "n0",
                    "body": {
                        "type": "init_ok",
                        "msg_id": 0,
                        "in_reply_to": 0
                    }
                }""".replaceAll("\n", "").replaceAll(" ", ""));
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

        var event = mapper.readRequest(json);

        var expected = new Event<>(new EchoOk("please-echo"));
        expected.id = 1;
        expected.src = "c2";
        expected.dst = "n0";
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

        var event = mapper.readRequest(json);

        var expected = new Event<>((EchoOk) event.body);
        expected.id = 1;
        expected.src = "c2";
        expected.dst = "n0";
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

        var event = mapper.readRequest(json);

        var expected = new Event<>(new EchoOk("please-echo"));
        expected.id = 1;
        expected.src = "c2";
        expected.dst = "n0";
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

        var event = mapper.readRequest(json);

        EventType echoBody = (EventType) event.body;
        if (echoBody instanceof Init) {
            fail("it is not a init event");
        }
        if (echoBody instanceof Echo) {
            fail("it is not a echo event");
        }

        var expected = new Event<>(new EchoOk("please-echo"));
        expected.id = 1;
        expected.src = "c2";
        expected.dst = "n0";
        assertThat(event)
                .isInstanceOf(Event.class)
                .isEqualTo(expected);
    }
}