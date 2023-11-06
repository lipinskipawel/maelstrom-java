package com.github.lipinskipawel.maelstrom.protocol;

import com.github.lipinskipawel.maelstrom.protocol.broadcast.BroadcastWorkload;
import com.github.lipinskipawel.maelstrom.protocol.echo.EchoWorkload;
import com.github.lipinskipawel.maelstrom.protocol.unique.UniqueWorkload;

public sealed interface BaseWorkload permits EventType,
        EchoWorkload, UniqueWorkload, BroadcastWorkload {

    int msgId();

    int inReplyTo();
}
