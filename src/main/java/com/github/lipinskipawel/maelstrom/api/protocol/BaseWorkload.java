package com.github.lipinskipawel.maelstrom.api.protocol;

import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.BroadcastWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.echo.EchoWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.unique.UniqueWorkload;

public sealed interface BaseWorkload permits EventType,
        EchoWorkload, UniqueWorkload, BroadcastWorkload {

    int msgId();

    int inReplyTo();
}
