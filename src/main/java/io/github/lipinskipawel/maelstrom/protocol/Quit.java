package io.github.lipinskipawel.maelstrom.protocol;

import io.github.lipinskipawel.maelstrom.protocol.broadcast.BroadcastWorkload;
import io.github.lipinskipawel.maelstrom.protocol.echo.EchoWorkload;
import io.github.lipinskipawel.maelstrom.protocol.unique.UniqueWorkload;

public final class Quit extends EventType implements EchoWorkload, UniqueWorkload, BroadcastWorkload {
    public Quit() {
        super("quit");
    }
}
