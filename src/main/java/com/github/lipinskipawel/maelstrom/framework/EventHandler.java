package com.github.lipinskipawel.maelstrom.framework;

import com.github.lipinskipawel.maelstrom.protocol.BaseWorkload;
import com.github.lipinskipawel.maelstrom.protocol.CustomEvent;
import com.github.lipinskipawel.maelstrom.protocol.EventType;

import static com.github.lipinskipawel.maelstrom.framework.JsonSupport.writeEvent;

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
     * Replies to original event send by maelstrom cluster from {@link EventHandler#handle(Event)}. This method is
     * mostly used to send events that are known to maelstrom cluster. Those events can be InitOk, EchOk and similar.
     *
     * @param originalEvent event to reply to
     * @param responseBody  response payload
     * @param <B>           type of response
     */
    public <B extends EventType> void replyAndSend(Event<W> originalEvent, B responseBody) {
        System.out.println(parse(originalEvent.reply(responseBody)));
    }

    /**
     * Sends an event plain event to maelstrom cluster.
     *
     * @param event event that must be sent
     * @param <C>   custom event type defined by the client
     */
    public <C extends CustomEvent> void send(Event<C> event) {
        System.out.println(parse(event));
    }

    private String parse(Event<?> event) {
        return writeEvent(event);
    }
}
