package com.github.lipinskipawel.maelstrom.api.protocol.echo;

import com.github.lipinskipawel.maelstrom.api.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.Init;
import com.github.lipinskipawel.maelstrom.api.protocol.Quit;

public sealed interface EchoWorkload extends BaseWorkload permits Init, Quit,
        Echo {
}
