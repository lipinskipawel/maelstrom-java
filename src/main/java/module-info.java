module com.github.lipinskipawel.maelstrom_java {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    exports com.github.lipinskipawel.maelstrom.framework;
    exports com.github.lipinskipawel.maelstrom.protocol;
    exports com.github.lipinskipawel.maelstrom.protocol.echo;
    exports com.github.lipinskipawel.maelstrom.protocol.unique;
    exports com.github.lipinskipawel.maelstrom.protocol.broadcast;
}
