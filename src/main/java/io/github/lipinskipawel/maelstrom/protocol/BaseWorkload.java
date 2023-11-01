package io.github.lipinskipawel.maelstrom.protocol;

import io.github.lipinskipawel.maelstrom.protocol.broadcast.BroadcastWorkload;
import io.github.lipinskipawel.maelstrom.protocol.echo.EchoWorkload;
import io.github.lipinskipawel.maelstrom.protocol.unique.UniqueWorkload;

public sealed interface BaseWorkload permits EventType,
        EchoWorkload, UniqueWorkload, BroadcastWorkload {

    int msgId();

    int inReplyTo();
}
