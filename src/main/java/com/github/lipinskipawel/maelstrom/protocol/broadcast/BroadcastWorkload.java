package com.github.lipinskipawel.maelstrom.protocol.broadcast;

import com.github.lipinskipawel.maelstrom.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.protocol.CustomRequest;
import com.github.lipinskipawel.maelstrom.protocol.Init;
import com.github.lipinskipawel.maelstrom.protocol.Quit;

public sealed interface BroadcastWorkload extends BaseWorkload permits Init, Quit, CustomRequest,
        Broadcast, Read, Topology {
}
