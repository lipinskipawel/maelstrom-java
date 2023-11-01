package io.github.lipinskipawel.maelstrom.protocol.unique;

import io.github.lipinskipawel.maelstrom.protocol.BaseWorkload;
import io.github.lipinskipawel.maelstrom.protocol.Init;
import io.github.lipinskipawel.maelstrom.protocol.Quit;

public sealed interface UniqueWorkload extends BaseWorkload permits Init, Quit,
        Unique {
}
