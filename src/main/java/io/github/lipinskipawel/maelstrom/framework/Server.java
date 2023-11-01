package io.github.lipinskipawel.maelstrom.framework;

import io.github.lipinskipawel.maelstrom.protocol.EventType;
import io.github.lipinskipawel.maelstrom.protocol.Quit;

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

            final var initRequest = scanner.nextLine();
            handler.handle(json.readRequest(initRequest));

            while (scanner.hasNextLine()) {
                final var request = scanner.nextLine();
                handler.handle(json.readRequest(request));
            }
            handler.handle(createQuitEvent());
        }
    }

    @SuppressWarnings("rawtypes")
    static Event createQuitEvent() {
        return new Event<>(new Quit());
    }
}
