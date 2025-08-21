package io.github.lipinskipawel.maelstrom.api.protocol;

import io.github.lipinskipawel.maelstrom.api.protocol.broadcast.BroadcastWorkload;
import io.github.lipinskipawel.maelstrom.api.protocol.echo.EchoWorkload;
import io.github.lipinskipawel.maelstrom.api.protocol.unique.UniqueWorkload;

public final class Quit
    extends EventType
    implements EchoWorkload, UniqueWorkload, BroadcastWorkload {
    public Quit() {
        super("quit");
    }
}
