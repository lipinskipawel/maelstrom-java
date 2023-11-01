package io.github.lipinskipawel.maelstrom.framework;

import io.github.lipinskipawel.maelstrom.protocol.EventType;
import io.github.lipinskipawel.maelstrom.protocol.broadcast.Broadcast;
import io.github.lipinskipawel.maelstrom.protocol.broadcast.BroadcastOk;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

final class EventTest implements WithAssertions {

    @Test
    void should_correctly_reply() {
        var broadcast = new Broadcast(12);
        broadcast.msgId = 12;
        var event = new Event<EventType>(broadcast);
        event.id = 90;
        event.src = "src1";
        event.dst = "dst2";

        var reply = event.reply(new BroadcastOk());

        var expectedBroadcastOk = new BroadcastOk();
        expectedBroadcastOk.msgId = 13;
        expectedBroadcastOk.inReplyTo = 12;
        expectedBroadcastOk.type = "broadcast_ok";
        var expected = new Event<>(expectedBroadcastOk);
        expected.id = 90;
        expected.src = "dst2";
        expected.dst = "src1";
        assertThat(reply).isEqualTo(expected);
    }
}
