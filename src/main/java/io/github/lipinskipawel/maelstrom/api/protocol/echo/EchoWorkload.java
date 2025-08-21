package io.github.lipinskipawel.maelstrom.api.protocol.echo;

import io.github.lipinskipawel.maelstrom.api.protocol.BaseWorkload;
import io.github.lipinskipawel.maelstrom.api.protocol.Init;
import io.github.lipinskipawel.maelstrom.api.protocol.Quit;

public sealed interface EchoWorkload extends BaseWorkload permits Init, Quit,
    Echo {
}
