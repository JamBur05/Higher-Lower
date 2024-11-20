package view;

import controller.GameController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import model.EventManager;

/**
 * View logic for the main game functions
 * @param controller instance of the game controller
 * */
public class GameView extends VBox implements ManagedView {

    private GameController controller;
    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private Image imgCard;
    private Text scoreText;

    public GameView(GameController controller) {
        this.controller = controller;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        // Event handler for game end event
        addEventHandler(EventManager.GAME_END, event -> onGameEnded());
        shuffleDeck();
        // Add event listener
        gameEventListener();
    }

    // Function to begin any game by shuffling the deck
    private void shuffleDeck(){
        getChildren().clear();

        Text shuffleText = new Text("Shuffle the deck to start!");
        Button btnShuffle = new Button("Shuffle");
        Button btnMenu = new Button("Return to Menu");

        shuffleText.setFill(Color.color(0.9137254901960784, 0.1843137254901961, 0.4666666666666667));
        shuffleText.setId("title");
        shuffleText.setStrokeWidth(1);
        shuffleText.setStroke(Color.WHITE);

        btnShuffle.setOnAction(e -> {
            controller.shuffle();
            buildView();
        });

        btnMenu.setOnAction(e -> {
            var eh = onViewChange.get();
            if (eh != null){
                eh.handle(new ViewChangeEvent(ViewManager.MENU));
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1200, 650);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        // Add all elements to grid pane
        gridPane.add(shuffleText, 0, 0);
        GridPane.setHalignment(shuffleText, HPos.CENTER);
        GridPane.setValignment(shuffleText, VPos.CENTER);
        gridPane.add(btnShuffle, 0, 1);
        GridPane.setHalignment(btnShuffle, HPos.CENTER);
        GridPane.setValignment(btnShuffle, VPos.CENTER);
        gridPane.add(btnMenu, 0, 2);
        GridPane.setHalignment(btnMenu, HPos.CENTER);
        GridPane.setValignment(btnMenu, VPos.CENTER);

        getChildren().add(gridPane);
    }

    // Function to build main game view
    private void buildView() {
        getChildren().clear();

        scoreText = new Text();
        Button btnHigher = new Button("Higher");
        Button btnLower = new Button("Lower");

        scoreText.setFill(Color.color(0.9137254901960784, 0.1843137254901961, 0.4666666666666667));
        scoreText.setId("scoreText");
        scoreText.setStrokeWidth(1);
        scoreText.setStroke(Color.WHITE);

        ImageView imageView = new ImageView();

        scoreText.textProperty().bind(controller.scoreObject().asString("Score: %d"));

        // Set image of the card for the player to play against
        imageView.setImage(null);
        imgCard = controller.getNextCard().getSprite();
        if(imgCard != null) {
            imageView.setImage(imgCard);
            imageView.setFitHeight(300);
            imageView.setFitWidth(150);
            imageView.setPreserveRatio(true);
        }

        // Event listener for higher input
        btnHigher.setOnAction(e -> {
            // Call controller to run logic for higher input
            if(controller.inputHigher() != null){
                imgCard = controller.getCurrentCard().getSprite();
                if(imgCard != null){
                    imageView.setImage(imgCard);
                    imageView.setFitHeight(300);
                    imageView.setFitWidth(150);
                    imageView.setPreserveRatio(true);
                }
            }
        });

        // Event listener for lower input
        btnLower.setOnAction(e -> {
            // Call controller to run logic for lower input
            if(controller.inputLower() != null){
                imgCard = controller.getCurrentCard().getSprite();
                if(imgCard != null){
                    imageView.setImage(imgCard);
                    imageView.setFitHeight(300);
                    imageView.setFitWidth(150);
                    imageView.setPreserveRatio(true);
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1200, 650);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        // Add all elements to grid pane
        gridPane.add(scoreText, 1, 0);
        gridPane.add(btnHigher, 0, 2);
        gridPane.add(btnLower, 2, 2);
        gridPane.add(imageView, 1, 1);

        getChildren().addAll(gridPane);
    }

    // Function to handle when a game ends
    private void gameEventListener() {
        controller.setEventListener(event -> {
            if (event.getEventType() == EventManager.GAME_END) {
                getChildren().clear();

                // Write score to CSV file
                controller.writeScore();

                Text title = new Text("Game Over!");
                scoreText = new Text();
                Button btnRestart = new Button("Play Again");
                Button btnQuit = new Button("Quit Game");

                title.setFill(Color.color(0.9137254901960784, 0.1843137254901961, 0.4666666666666667));
                title.setId("scoreText");
                title.setStrokeWidth(1);
                title.setStroke(Color.WHITE);

                scoreText.setFill(Color.color(0.9137254901960784, 0.1843137254901961, 0.4666666666666667));
                scoreText.setId("scoreText");
                scoreText.setStrokeWidth(1);
                scoreText.setStroke(Color.WHITE);

                // Bind score property to text
                scoreText.textProperty().bind(controller.scoreObject().asString("Score: %d"));

                ImageView imageView = new ImageView();
                imgCard = controller.getCurrentCard().getSprite();
                if(imgCard != null){
                    imageView.setImage(imgCard);
                    imageView.setFitHeight(300);
                    imageView.setFitWidth(150);
                    imageView.setPreserveRatio(true);
                }

                // Event listener for restart game button
                btnRestart.setOnAction(e -> {
                    controller.restartGame();
                    shuffleDeck();
                });

                // Event listener for quit game button
                btnQuit.setOnAction(e -> {
                    System.exit(0);

                });

                GridPane gridPane = new GridPane();
                gridPane.setMinSize(1200, 650);
                gridPane.setPadding(new Insets(10, 10, 10, 10));
                gridPane.setVgap(5);
                gridPane.setHgap(5);

                gridPane.setAlignment(Pos.CENTER);

                // Add all elements to grid pane
                gridPane.add(title, 1, 0);
                gridPane.add(imageView, 1, 2);
                gridPane.add(btnRestart, 0, 3);
                gridPane.add(btnQuit, 2, 3);
                gridPane.add(scoreText, 1, 1);
                GridPane.setHalignment(scoreText, HPos.CENTER);
                GridPane.setValignment(scoreText, VPos.CENTER);
                GridPane.setHalignment(imageView, HPos.CENTER);
                GridPane.setValignment(imageView, VPos.CENTER);

                getChildren().addAll(gridPane);
            }
        });
    }

    private void onGameEnded(){
        System.out.println("Game Ended");
    }


    @Override
    public EventHandler<? super ViewChangeEvent> getOnViewChange() {
        return onViewChange.get();
    }

    @Override
    public void setOnViewChange(EventHandler<? super ViewChangeEvent> eventHandler) {
        onViewChange.set(eventHandler);
    }
}
