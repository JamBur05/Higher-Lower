package controller;

import javafx.event.Event;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import model.Card;
import model.Deck;
import model.EventManager;

public class GameController {

    private EventHandler<? super EventManager> eventDispatcher;
    private Deck deck;

    public GameController() {
        deck = new Deck();
    }

    public Card getNextCard(){
        return deck.getNextCard();
    }

    public void setEventListener(EventHandler<? super EventManager> listener) {
        this.eventDispatcher = listener;
    }

    public void endGame() {
        if (eventDispatcher != null) {
            eventDispatcher.handle(new EventManager(EventManager.GAME_END));
        }
    }

}
