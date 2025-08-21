package io.github.lipinskipawel.maelstrom.api.protocol;

import io.github.lipinskipawel.maelstrom.api.protocol.broadcast.BroadcastWorkload;
import io.github.lipinskipawel.maelstrom.api.protocol.echo.EchoWorkload;
import io.github.lipinskipawel.maelstrom.api.protocol.unique.UniqueWorkload;

public sealed interface BaseWorkload permits EventType,
        EchoWorkload, UniqueWorkload, BroadcastWorkload {

    int msgId();

    int inReplyTo();
}
