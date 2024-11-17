package view;

import javafx.event.EventHandler;

public interface ManagedView {
    EventHandler<? super ViewChangeEvent> getOnViewChange();

    void setOnViewChange(EventHandler<? super ViewChangeEvent> eventHandler);

}
