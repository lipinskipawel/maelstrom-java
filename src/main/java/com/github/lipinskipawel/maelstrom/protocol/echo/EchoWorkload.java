package com.github.lipinskipawel.maelstrom.protocol.echo;

import com.github.lipinskipawel.maelstrom.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.protocol.Init;
import com.github.lipinskipawel.maelstrom.protocol.Quit;

public sealed interface EchoWorkload extends BaseWorkload permits Init, Quit,
        Echo {
}
