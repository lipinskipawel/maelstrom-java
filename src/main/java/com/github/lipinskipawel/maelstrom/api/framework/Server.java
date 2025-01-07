package com.github.lipinskipawel.maelstrom.api.framework;

import com.github.lipinskipawel.maelstrom.api.protocol.Event;
import com.github.lipinskipawel.maelstrom.api.protocol.EventType;
import com.github.lipinskipawel.maelstrom.api.protocol.Quit;

import java.util.Map;
import java.util.Scanner;

import static com.github.lipinskipawel.maelstrom.api.protocol.Event.createEvent;
import static com.github.lipinskipawel.maelstrom.internal.JsonSupport.createJsonMapper;
import static com.github.lipinskipawel.maelstrom.internal.JsonSupport.readEvent;

public final class Server implements Runnable {
    private final EventHandler<?> handler;

    Server(EventHandler<?> handler, Map<String, Class<? extends EventType>> customTypes) {
        this.handler = handler;
        createJsonMapper(customTypes);
    }

    /**
     * Use {@link ServerBuilder#server()}
     *
     * @param workload
     * @return
     */
    @Deprecated(forRemoval = true, since = "0.3.0")
    public static Server workload(EventHandler<?> workload) {
        return workloadWithCustomEvent(workload, Map.of());
    }

    /**
     * Use {@link ServerBuilder#server()}
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
    @Override
    public void run() {
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

    @Deprecated(forRemoval = true, since = "0.3.0")
    public void loop() {
        run();
    }

    @SuppressWarnings("rawtypes")
    @Deprecated(forRemoval = true, since = "0.3.0")
    static Event createQuitEvent() {
        return createEvent(-1, "", "", new Quit());
    }
}
