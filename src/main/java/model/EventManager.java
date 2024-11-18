package model;

import javafx.event.Event;
import javafx.event.EventType;

public class EventManager extends Event {
    public static final EventType<EventManager> GAME_END = new EventType<>(Event.ANY, "GAME_END");

    public EventManager(EventType<? extends EventManager> eventType) {
        super(eventType);
    }
}
