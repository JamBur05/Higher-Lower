package view;

import javafx.event.Event;
import javafx.event.EventType;

public class ViewChangeEvent extends Event {
    private static final EventType<ViewChangeEvent> TYPE = new EventType<>("ViewChange");
    private final String view;

    public ViewChangeEvent(String view) {
        super(TYPE);
        this.view = view;
    }

    public String getView() {
        return view;
    }

}
