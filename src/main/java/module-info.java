module com.github.lipinskipawel.maelstrom_java {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    exports com.github.lipinskipawel.maelstrom.api.framework;
    exports com.github.lipinskipawel.maelstrom.api.protocol;
    exports com.github.lipinskipawel.maelstrom.api.protocol.echo;
    exports com.github.lipinskipawel.maelstrom.api.protocol.unique;
    exports com.github.lipinskipawel.maelstrom.api.protocol.broadcast;

    exports com.github.lipinskipawel.maelstrom.spi.protocol;
}
