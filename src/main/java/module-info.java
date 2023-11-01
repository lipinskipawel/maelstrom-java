module io.github.lipinskipawel.maelstrom_java {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    exports io.github.lipinskipawel.maelstrom.framework;
    exports io.github.lipinskipawel.maelstrom.protocol;
    exports io.github.lipinskipawel.maelstrom.protocol.echo;
    exports io.github.lipinskipawel.maelstrom.protocol.unique;
    exports io.github.lipinskipawel.maelstrom.protocol.broadcast;
}
