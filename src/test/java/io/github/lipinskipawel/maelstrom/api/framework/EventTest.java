package io.github.lipinskipawel.maelstrom.api.framework;

import io.github.lipinskipawel.maelstrom.api.protocol.broadcast.Broadcast;
import io.github.lipinskipawel.maelstrom.api.protocol.broadcast.BroadcastOk;
import io.github.lipinskipawel.maelstrom.api.protocol.Event;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

final class EventTest implements WithAssertions {

    @Test
    void should_correctly_reply() {
        var broadcast = new Broadcast(12);
        broadcast.msgId = 12;
        var event = Event.createEvent(90, "src1", "dst2", broadcast);

        var reply = event.reply(new BroadcastOk());

        var expectedBroadcastOk = new BroadcastOk();
        expectedBroadcastOk.msgId = 13;
        expectedBroadcastOk.inReplyTo = 12;
        expectedBroadcastOk.type = "broadcast_ok";
        var expected = Event.createEvent(90, "dst2", "src1", expectedBroadcastOk);
        assertThat(reply).isEqualTo(expected);
    }
}
