package io.github.lipinskipawel.maelstrom.protocol.broadcast;

import io.github.lipinskipawel.maelstrom.protocol.BaseWorkload;
import io.github.lipinskipawel.maelstrom.protocol.CustomRequest;
import io.github.lipinskipawel.maelstrom.protocol.Init;
import io.github.lipinskipawel.maelstrom.protocol.Quit;

public sealed interface BroadcastWorkload extends BaseWorkload permits Init, Quit, CustomRequest,
        Broadcast, Read, Topology {
}
