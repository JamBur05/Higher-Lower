package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Logic for leaderboard, including writing and reading from the CSV
 * */
public class Leaderboard {
    private static final String FILE_PATH = "resources/SCORES_DATA.csv";

    public void writeScore(String playerName, int score) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
            if(playerName == null || playerName.equals("Username")){
                playerName = "Player";
            }
            writer.write(playerName + "," + score);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to the CSV file: " + e.getMessage());
        }
    }

    // Method to read all scores from the CSV file and return them as a list
    public ArrayList<String> getAllScores() {
        ArrayList<String> scores = new ArrayList<>();
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            scores.addAll(lines);
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
        }
        return scores;
    }

}
