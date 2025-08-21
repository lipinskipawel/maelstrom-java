module com.github.lipinskipawel.maelstrom_java {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    exports io.github.lipinskipawel.maelstrom.api.framework;
    exports io.github.lipinskipawel.maelstrom.api.protocol;
    exports io.github.lipinskipawel.maelstrom.api.protocol.echo;
    exports io.github.lipinskipawel.maelstrom.api.protocol.unique;
    exports io.github.lipinskipawel.maelstrom.api.protocol.broadcast;

    exports io.github.lipinskipawel.maelstrom.spi.protocol;
}
