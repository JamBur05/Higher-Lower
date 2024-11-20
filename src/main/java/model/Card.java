package model;

import javafx.scene.image.Image;

/**
 * Model for storing instances of each card in the deck
 * @param rank The rank of the card
 * @param suit The suit of the card
 * @param sprite The image of the card
 */
public class Card {
    private final int rank;
    private final String suit;
    private final Image sprite;

    public Card(int rank, String suit, Image sprite) {
        this.rank = rank;
        this.suit = suit;
        this.sprite = sprite;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public Image getSprite() {
        return sprite;
    }
}
