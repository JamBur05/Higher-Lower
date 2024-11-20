package model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Model for storing the instance of the deck of cards
 *
 */
public class Deck {
    private ArrayList<Card> deck;
    private Card topCard;
    public Deck(){
        deck = new ArrayList<>();
        String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
        String[] suits = {"hearts", "diamonds", "spades", "clubs"};

        // Iterate through all 52 cards and add them to the deck
        for(String suit : suits){
            for(String rank : ranks){
                String filename = rank + "_of_" + suit + ".png";
                Image cardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/cards/" + filename)));
                deck.add(new Card(Integer.parseInt(rank), suit, cardImage));
            }
        }

        shuffle();
        topCard = deck.getLast();
    }

    // Add jokers to the deck and reshuffle
    public void addJokers(){
        for(int i = 0; i < 2; i++){
            Image cardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/cards/joker_" + (i + 1) + ".png")));
            deck.add(new Card(14, "joker", cardImage));
        }
        shuffle();
    }

    public Card getCurrentCard(){
        return topCard;
    }

    // Get the next card of the deck and return it
    public Card getNextCard(){
        System.out.println(deck.size());
        if (deck.isEmpty()) {
            return null;
        }

        topCard = deck.getLast();

        deck.removeLast();

        return topCard;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

}
