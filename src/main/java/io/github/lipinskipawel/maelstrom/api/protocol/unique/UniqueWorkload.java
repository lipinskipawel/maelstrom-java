package io.github.lipinskipawel.maelstrom.api.protocol.unique;

import io.github.lipinskipawel.maelstrom.api.protocol.BaseWorkload;
import io.github.lipinskipawel.maelstrom.api.protocol.Init;
import io.github.lipinskipawel.maelstrom.api.protocol.Quit;

public sealed interface UniqueWorkload extends BaseWorkload permits Init, Quit,
    Unique {
}
