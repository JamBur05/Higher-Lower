package HigherLower.Game.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import HigherLower.Game.model.Card;
import HigherLower.Game.model.Deck;
import HigherLower.Game.model.EventManager;
import HigherLower.Game.model.Leaderboard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
*   Controller for handling game logic
*   @param leaderboard Handles leaderboard logic
*/

public class GameController {

    private EventHandler<? super EventManager> eventDispatcher;
    private Deck deck;
    private IntegerProperty playerScore;
    private boolean jokerSet;
    private Leaderboard leaderboard;
    private String username;

    public GameController(Leaderboard leaderboard) {
        deck = new Deck();
        username = null;
        this.leaderboard = leaderboard;
        jokerSet = false;
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
        if(jokerSet){
            deck.addJokers();
        }
    }

    public void setJoker(){
        jokerSet = true;
        deck.addJokers();
    }

    public void shuffle(){
        deck.shuffle();
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void writeScore(){
        leaderboard.writeScore(username, playerScore.get());
    }

    public List<String> getTopScores(int n) {
        ArrayList<String> allScores = leaderboard.getAllScores();

        // Sort the list by score (in descending order)
        List<String> sortedScores = allScores.stream()
                .filter(line -> line.split(",").length == 2)
                .sorted((score1, score2) -> {
                    try {
                        String[] parts1 = score1.split(",");
                        String[] parts2 = score2.split(",");

                        // Extract the score and convert it to an integer
                        int score1Value = Integer.parseInt(parts1[1].trim());
                        int score2Value = Integer.parseInt(parts2[1].trim());

                        // Sort in descending order
                        return Integer.compare(score2Value, score1Value);
                    } catch (Exception e) {
                        return 0;
                    }
                })
                .collect(Collectors.toList());

        // Return the top n scores using limit
        return sortedScores.stream()
                .limit(n)
                .collect(Collectors.toList());
    }
}
