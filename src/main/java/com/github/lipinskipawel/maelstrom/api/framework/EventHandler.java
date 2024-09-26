package com.github.lipinskipawel.maelstrom.api.framework;

import com.github.lipinskipawel.maelstrom.api.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.Event;

import static com.github.lipinskipawel.maelstrom.internal.JsonSupport.writeEvent;

/**
 * Every class that wishes to handle maelstrom workload must extend this class.
 *
 * @param <W> type of workload
 */
public abstract class EventHandler<W extends BaseWorkload> {

    /**
     * Clients will receive maelstrom events through this method.
     *
     * @param event from maelstrom cluster
     */
    public abstract void handle(Event<W> event);

    /**
     * Sends an plain event to maelstrom cluster.
     *
     * @param event event that must be sent
     */
    public void send(Event<?> event) {
        System.out.println(parse(event));
        System.out.flush();
    }

    /**
     * Prints debug message to the standard error.
     * In order to see debug messages start server with `--log-stderr` flag.
     *
     * @param debugMessage that will be printed
     */
    public void debug(String debugMessage) {
        System.err.println(debugMessage);
        System.err.flush();
    }

    /**
     * Prints debug message to standard error with event. Internally `debugMessage` is calling formatted method with
     * event as a argument.
     * In order to see debug messages start server with `--log-stderr` flag.
     *
     * @param debugMessage that will be printed
     * @param event        that will be formatted into the debug message
     */
    public void debug(String debugMessage, Event<?> event) {
        debug(debugMessage.formatted(parse(event)));
    }

    private String parse(Event<?> event) {
        return writeEvent(event);
    }
}
