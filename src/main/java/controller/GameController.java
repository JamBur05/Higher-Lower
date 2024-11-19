package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import model.Card;
import model.Deck;
import model.EventManager;

public class GameController {

    private EventHandler<? super EventManager> eventDispatcher;
    private Deck deck;
    private IntegerProperty playerScore;

    public GameController() {
        deck = new Deck();
        playerScore = new SimpleIntegerProperty(0);
    }

    public Card getNextCard(){
        return deck.getNextCard();
    }

    public Card getCurrentCard(){
        return deck.getCurrentCard();
    }

    public void setEventListener(EventHandler<? super EventManager> listener) {
        this.eventDispatcher = listener;
    }

    private void endGame() {
        if (eventDispatcher != null) {
            eventDispatcher.handle(new EventManager(EventManager.GAME_END));
        }
    }

    public Card inputHigher(){
        Card currentCard = deck.getCurrentCard();
        Card nextCard = deck.getNextCard();

        // Whats the edge case on this?
        if(currentCard.getRank() <= nextCard.getRank()){
            increaseScore();
            return nextCard;
        }
        else{
            endGame();
            return null;
        }
    }

    public Card inputLower(){
        Card currentCard = deck.getCurrentCard();
        Card nextCard = deck.getNextCard();

        // Whats the edge case on this?
        if(currentCard.getRank() >= nextCard.getRank()){
            increaseScore();
            return nextCard;
        }
        else{
            endGame();
            return null;
        }
    }

    public IntegerProperty scoreObject() {
        return playerScore;
    }

    public void setScore(int score) {
        this.playerScore.set(score);
    }

    private void increaseScore() {
        playerScore.set(playerScore.get() + 1);
    }

    public void restartGame(){
        setScore(0);
        deck = new Deck();
    }

}
