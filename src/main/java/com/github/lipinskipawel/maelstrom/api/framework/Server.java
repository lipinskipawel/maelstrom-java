package com.github.lipinskipawel.maelstrom.api.framework;

import com.github.lipinskipawel.maelstrom.api.protocol.EventType;
import com.github.lipinskipawel.maelstrom.api.protocol.Quit;

import java.util.Map;
import java.util.Scanner;

import static com.github.lipinskipawel.maelstrom.api.framework.Event.createEvent;
import static com.github.lipinskipawel.maelstrom.internal.JsonSupport.jsonSupportRegisterCustomTypes;
import static com.github.lipinskipawel.maelstrom.internal.JsonSupport.readEvent;

public final class Server {
    private final EventHandler<?> handler;

    private Server(EventHandler<?> handler, Map<String, Class<? extends EventType>> customTypes) {
        this.handler = handler;
        jsonSupportRegisterCustomTypes(customTypes);
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
            handler.handle(readEvent(initEvent));

            while (scanner.hasNextLine()) {
                final var event = scanner.nextLine();
                handler.handle(readEvent(event));
            }
            handler.handle(createQuitEvent());
        }
    }

    @SuppressWarnings("rawtypes")
    static Event createQuitEvent() {
        return createEvent(-1, "", "", new Quit());
    }
}
