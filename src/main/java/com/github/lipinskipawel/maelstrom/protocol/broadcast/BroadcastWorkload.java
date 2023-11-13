package com.github.lipinskipawel.maelstrom.protocol.broadcast;

import com.github.lipinskipawel.maelstrom.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.protocol.CustomEvent;
import com.github.lipinskipawel.maelstrom.protocol.Init;
import com.github.lipinskipawel.maelstrom.protocol.Quit;

public sealed interface BroadcastWorkload extends BaseWorkload permits Init, Quit, CustomEvent,
        Broadcast, Read, Topology {
}
