package model;

import javafx.scene.image.Image;

public class Card {
    private final String rank; // E.g., "2", "Jack", "Ace"
    private final String suit; // E.g., "Hearts", "Diamonds", "Spades", "Clubs"
    private final Image sprite; // The image of the card sprite

    public Card(String rank, String suit, Image sprite) {
        this.rank = rank;
        this.suit = suit;
        this.sprite = sprite;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public Image getSprite() {
        return sprite;
    }
}
