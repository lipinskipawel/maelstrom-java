package com.github.lipinskipawel.maelstrom.api.protocol.broadcast;

import com.github.lipinskipawel.maelstrom.api.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.Init;
import com.github.lipinskipawel.maelstrom.api.protocol.Quit;
import com.github.lipinskipawel.maelstrom.spi.protocol.CustomEvent;

public sealed interface BroadcastWorkload extends BaseWorkload permits CustomEvent, Init, Quit, Broadcast, Read, Topology {
}
