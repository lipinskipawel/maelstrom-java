package com.github.lipinskipawel.maelstrom.api.framework;

import com.github.lipinskipawel.maelstrom.api.protocol.Event;
import com.github.lipinskipawel.maelstrom.api.protocol.EventType;
import com.github.lipinskipawel.maelstrom.api.protocol.Init;
import com.github.lipinskipawel.maelstrom.api.protocol.InitOk;
import com.github.lipinskipawel.maelstrom.api.protocol.Quit;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.Broadcast;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.BroadcastOk;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.BroadcastWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.Read;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.ReadOk;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.Topology;
import com.github.lipinskipawel.maelstrom.api.protocol.broadcast.TopologyOk;
import com.github.lipinskipawel.maelstrom.api.protocol.echo.Echo;
import com.github.lipinskipawel.maelstrom.api.protocol.echo.EchoOk;
import com.github.lipinskipawel.maelstrom.api.protocol.echo.EchoWorkload;
import com.github.lipinskipawel.maelstrom.api.protocol.unique.Unique;
import com.github.lipinskipawel.maelstrom.api.protocol.unique.UniqueOk;
import com.github.lipinskipawel.maelstrom.api.protocol.unique.UniqueWorkload;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.github.lipinskipawel.maelstrom.api.protocol.Event.createEvent;
import static com.github.lipinskipawel.maelstrom.internal.JsonSupport.createJsonMapper;
import static com.github.lipinskipawel.maelstrom.internal.JsonSupport.readEvent;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

public final class Server {
    private final EventHandler<?> handler;

    private Server(EventHandler<?> handler, Map<String, Class<? extends EventType>> customTypes) {
        this.handler = handler;
        createJsonMapper(customTypes);
    }

    /**
     * Use {@link Builder}
     *
     * @param workload
     * @return
     */
    @Deprecated(forRemoval = true, since = "0.3.0")
    public static Server workload(EventHandler<?> workload) {
        return workloadWithCustomEvent(workload, Map.of());
    }

    /**
     * Use {@link Builder}
     *
     * @param workloadHandler
     * @param eventClass
     * @return
     */
    @Deprecated(forRemoval = true, since = "0.3.0")
    public static Server workloadWithCustomEvent(EventHandler<?> workloadHandler, Map<String, Class<? extends EventType>> eventClass) {
        return new Server(workloadHandler, eventClass);
    }

    @SuppressWarnings("unchecked")
    public void loop() {
        try (var scanner = new Scanner(System.in)) {

            final var initEvent = scanner.nextLine();
            handler.handle(readEvent(initEvent));

            while (scanner.hasNextLine()) {
                final var event = scanner.nextLine();
                // https://stackoverflow.com/questions/40217285/java-generics-capture-of
                handler.handle(readEvent(event));
            }
            handler.handle(createQuitEvent());
        }
    }

    @SuppressWarnings("rawtypes")
    static Event createQuitEvent() {
        return createEvent(-1, "", "", new Quit());
    }

    public static class Builder {
        private EventHandler<EchoWorkload> echoHandler;
        private EventHandler<UniqueWorkload> uniqueHandler;
        private EventHandler<BroadcastWorkload> broadcastHandler;

        private final Map<String, Class<? extends EventType>> commonEvents = Map.of(
            "init", Init.class,
            "init_ok", InitOk.class,
            "quit", Quit.class
        );
        private final Map<String, Class<? extends EventType>> handlerSpecificEvents = new HashMap<>();
        private final Map<String, Class<? extends EventType>> customEvents = new HashMap<>();

        private Builder() {
        }

        public static Builder server() {
            return new Builder();
        }

        public Builder echo(EventHandler<EchoWorkload> echoHandler) {
            this.echoHandler = requireNonNull(echoHandler);
            this.handlerSpecificEvents.clear();
            this.handlerSpecificEvents.putAll(Map.of(
                "echo", Echo.class,
                "echo_ok", EchoOk.class
            ));
            return this;
        }

        public Builder unique(EventHandler<UniqueWorkload> uniqueHandler) {
            this.uniqueHandler = requireNonNull(uniqueHandler);
            this.handlerSpecificEvents.clear();
            this.handlerSpecificEvents.putAll(Map.of(
                "generate", Unique.class,
                "generate_ok", UniqueOk.class
            ));
            return this;
        }

        public Builder broadcast(EventHandler<BroadcastWorkload> broadcastHandler) {
            this.broadcastHandler = requireNonNull(broadcastHandler);
            this.handlerSpecificEvents.clear();
            this.handlerSpecificEvents.putAll(Map.of(
                "broadcast", Broadcast.class,
                "broadcast_ok", BroadcastOk.class,
                "read", Read.class,
                "read_ok", ReadOk.class,
                "topology", Topology.class,
                "topology_ok", TopologyOk.class
            ));
            return this;
        }

        public Builder customEvents(Map<String, Class<? extends EventType>> eventClass) {
            this.customEvents.putAll(requireNonNull(eventClass));
            return this;
        }

        public Server build() {
            if (echoHandler != null) {
                return new Server(echoHandler, merge(commonEvents, handlerSpecificEvents));
            }
            if (uniqueHandler != null) {
                return new Server(uniqueHandler, merge(commonEvents, handlerSpecificEvents));
            }
            if (broadcastHandler != null) {
                return new Server(broadcastHandler, merge(commonEvents, handlerSpecificEvents, customEvents));
            }
            throw new RuntimeException("EventHandler<W extends BaseWorkload> must be specified");
        }

        @SafeVarargs
        private Map<String, Class<? extends EventType>> merge(Map<String, Class<? extends EventType>>... maps) {
            return Stream.of(maps)
                .flatMap(it -> it.entrySet().stream())
                .collect(toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue
                ));
        }
    }
}
