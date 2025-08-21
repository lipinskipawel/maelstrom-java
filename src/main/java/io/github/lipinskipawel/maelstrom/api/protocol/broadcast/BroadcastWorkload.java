package io.github.lipinskipawel.maelstrom.api.protocol.broadcast;

import io.github.lipinskipawel.maelstrom.api.protocol.BaseWorkload;
import io.github.lipinskipawel.maelstrom.api.protocol.Init;
import io.github.lipinskipawel.maelstrom.api.protocol.Quit;
import io.github.lipinskipawel.maelstrom.spi.protocol.CustomEvent;

public sealed interface BroadcastWorkload
    extends BaseWorkload
    permits CustomEvent, Init, Quit, Broadcast, Read, Topology {
}
