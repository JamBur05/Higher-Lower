# Higher-Lower

Higher lower card game created with JavaFX

## Build Instructions

### Build From Source

1. [Install Java](https://www.java.com/download/ie_manual.jsp)
2. [Install JavaFX](https://gluonhq.com/products/javafx/)
3. Build the project

```bash
./gradlew build
```

3. Run the following command and specify the location of your javafx installation

```bash
java --module-path "C:\Program Files\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar .\build\libs\HigherLower-1.0-SNAPSHOT.jar
```

- Make sure to replace the path "C:\Program Files\javafx-sdk-23.0.1\lib" with the actual location of the lib folder inside your JavaFX SDK if it's installed in a different directory.

### Ideas for Improvement

In the future, I plan on adding the following features:

- Timed challenge
  - Players have to make a guess within a given time limit
- Easy mode
  - Show probability of higher/lower to the player, calculated from the cards remaining in the deck
- Add animations
  - Flip card when guess is made
- Global leaderboard
  - Show additional leaderboard of global scores

### Video Demo

<video width="320" height="240" controls>
  <source src="/resources/Demo_Video.mp4" type="video/mp4">
</video>
