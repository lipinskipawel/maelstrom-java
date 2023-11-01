package io.github.lipinskipawel.maelstrom.protocol.echo;

import io.github.lipinskipawel.maelstrom.protocol.BaseWorkload;
import io.github.lipinskipawel.maelstrom.protocol.Init;
import io.github.lipinskipawel.maelstrom.protocol.Quit;

public sealed interface EchoWorkload extends BaseWorkload permits Init, Quit,
        Echo {
}
