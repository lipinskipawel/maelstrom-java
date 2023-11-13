package com.github.lipinskipawel.maelstrom.api.protocol;

import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.BroadcastWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.echo.EchoWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.unique.UniqueWorkload;

public final class Quit extends EventType implements EchoWorkload, UniqueWorkload, BroadcastWorkload {
    public Quit() {
        super("quit");
    }
}
