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

## Design Choices

### MVC Architecture

To structure the project in a clean and maintainable way, I followed the Model View Controller (MVC) design pattern. This separation of concerns also makes it easier to extend the game with new features, such as adding any additional views, or game mechanics.

Model:

- Card: Stores data for each card in a deck of cards, including its rank, suit and sprite.
- Deck: Initialises and stores a collection of cards.
- EventManager: Generic implementation for handling game events such as the game ending.

View:

- MenuView: Contains all view logic for the main menu
- GameView: Contains all view logic for the game
- LeaderboardView: Contains all view logic for displaying the stored, local leaderboard
- ManagedView: Generic interface, allowing the ViewManager to manage a view
- ViewChangeEvent: Used to raise an event when a ViewManager changes the displayed view
- ViewManager: Manages the scenes available in the application

Controller:

- GameController: Handles all game logic by interacting with the Model and View

## Ideas for Improvement

In the future, I plan on adding the following features:

- Timed challenge
  - Players have to make a guess within a given time limit
- Easy mode
  - Show probability of higher/lower to the player, calculated from the cards remaining in the deck
- Add animations
  - Flip card when guess is made
- Global leaderboard
  - Show additional leaderboard of global scores

## Video Demo

[Watch the demo on YouTube here](https://youtu.be/ch9s0mqqC60)
