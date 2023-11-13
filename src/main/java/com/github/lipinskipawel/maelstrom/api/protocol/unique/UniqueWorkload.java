package com.github.lipinskipawel.maelstrom.api.protocol.unique;

import com.github.lipinskipawel.maelstrom.api.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.Init;
import com.github.lipinskipawel.maelstrom.api.protocol.Quit;

public sealed interface UniqueWorkload extends BaseWorkload permits Init, Quit,
        Unique {
}
