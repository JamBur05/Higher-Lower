package model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Deck {
    private ArrayList<Card> deck;
    private Card topCard;
    public Deck(){
        deck = new ArrayList<>();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
        String[] suits = {"hearts", "diamonds", "spades", "clubs"};

        for(String suit : suits){
            for(String rank : ranks){
                String filename = rank + "_of_" + suit + ".png";
                Image cardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/cards/" + filename)));
                deck.add(new Card(Integer.parseInt(rank), suit, cardImage));
            }
        }

        shuffle();
        topCard = deck.getLast();
    }

    public Card getCurrentCard(){
        return topCard;
    }

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
