package controller;

import model.Card;
import model.Deck;

public class GameController {

    Deck deck;

    public GameController() {
        deck = new Deck();
    }

    public Card getNextCard(){
        return deck.getNextCard();
    }
}
