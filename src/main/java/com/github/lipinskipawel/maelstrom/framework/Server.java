package com.github.lipinskipawel.maelstrom.framework;

import com.github.lipinskipawel.maelstrom.protocol.EventType;
import com.github.lipinskipawel.maelstrom.protocol.Quit;

import java.util.Map;
import java.util.Scanner;

public final class Server {
    private final JsonSupport json;
    private final EventHandler<?> handler;

    private Server(EventHandler<?> handler, Map<String, Class<? extends EventType>> customTypes) {
        this.handler = handler;
        this.json = new JsonSupport(customTypes);
    }

    public static Server workload(EventHandler<?> workload) {
        return workloadWithCustomEvent(workload, Map.of());
    }

    public static Server workloadWithCustomEvent(EventHandler<?> workloadHandler, Map<String, Class<? extends EventType>> eventClass) {
        return new Server(workloadHandler, eventClass);
    }

    @SuppressWarnings("unchecked")
    public void loop() {
        try (var scanner = new Scanner(System.in)) {

            final var initEvent = scanner.nextLine();
            handler.handle(json.readEvent(initEvent));

            while (scanner.hasNextLine()) {
                final var event = scanner.nextLine();
                handler.handle(json.readEvent(event));
            }
            handler.handle(createQuitEvent());
        }
    }

    @SuppressWarnings("rawtypes")
    static Event createQuitEvent() {
        return new Event<>(new Quit());
    }
}
