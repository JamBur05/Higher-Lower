package view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;

public class MenuView implements ManagedView {

    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;

    public MenuView() {
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
    }


    @Override
    public EventHandler<? super ViewChangeEvent> getOnViewChange() {
        return onViewChange.get();
    }

    @Override
    public void setOnViewChange(EventHandler<? super ViewChangeEvent> eventHandler) {
        onViewChange.set(eventHandler);
    }
}
