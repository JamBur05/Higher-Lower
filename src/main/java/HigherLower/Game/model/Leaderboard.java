package HigherLower.Game.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Leaderboard {
    private static final String RESOURCE_FILE_PATH = "/SCORES_DATA.csv"; // Path inside the JAR
    private static final String USER_FILE_PATH = "user_data/SCORES_DATA.csv"; // Path where the CSV will be written on the user's system

    // Method to write score to a new location on the user's file system
    public void writeScore(String playerName, int score) {
        try {
            // Create the user_data directory if it doesn't exist
            Path userFilePath = Paths.get(USER_FILE_PATH);
            if (Files.notExists(userFilePath.getParent())) {
                Files.createDirectories(userFilePath.getParent());
            }

            // Open the file for appending the score
            try (BufferedWriter writer = Files.newBufferedWriter(userFilePath, StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {
                if (playerName == null || playerName.equals("Username")) {
                    playerName = "Player";
                }
                writer.write(playerName + "," + score);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the CSV file: " + e.getMessage());
        }
    }

    // Method to read all scores from the CSV file inside the JAR resources
    public ArrayList<String> getAllScores() {
        ArrayList<String> scores = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream(RESOURCE_FILE_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
        }
        return scores;
    }
}
