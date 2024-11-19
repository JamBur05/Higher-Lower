package model;

import javafx.scene.image.Image;

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
