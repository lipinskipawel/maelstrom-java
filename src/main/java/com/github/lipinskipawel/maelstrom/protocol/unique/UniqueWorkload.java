package com.github.lipinskipawel.maelstrom.protocol.unique;

import com.github.lipinskipawel.maelstrom.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.protocol.Init;
import com.github.lipinskipawel.maelstrom.protocol.Quit;

public sealed interface UniqueWorkload extends BaseWorkload permits Init, Quit,
        Unique {
}
