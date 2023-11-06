package com.github.lipinskipawel.maelstrom.protocol;

import com.github.lipinskipawel.maelstrom.protocol.broadcast.BroadcastWorkload;
import com.github.lipinskipawel.maelstrom.protocol.echo.EchoWorkload;
import com.github.lipinskipawel.maelstrom.protocol.unique.UniqueWorkload;

public final class Quit extends EventType implements EchoWorkload, UniqueWorkload, BroadcastWorkload {
    public Quit() {
        super("quit");
    }
}
