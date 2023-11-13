package com.github.lipinskipawel.maelstrom.api.framework;

import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.Broadcast;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.BroadcastOk;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import static com.github.lipinskipawel.maelstrom.api.framework.Event.createEvent;

final class EventTest implements WithAssertions {

    @Test
    void should_correctly_reply() {
        var broadcast = new Broadcast(12);
        broadcast.msgId = 12;
        var event = createEvent(90, "src1", "dst2", broadcast);

        var reply = event.reply(new BroadcastOk());

        var expectedBroadcastOk = new BroadcastOk();
        expectedBroadcastOk.msgId = 13;
        expectedBroadcastOk.inReplyTo = 12;
        expectedBroadcastOk.type = "broadcast_ok";
        var expected = createEvent(90, "dst2", "src1", expectedBroadcastOk);
        assertThat(reply).isEqualTo(expected);
    }
}
