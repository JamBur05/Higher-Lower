package view;

import controller.GameController;
import javafx.beans.property.IntegerProperty;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import model.EventManager;

public class GameView extends VBox implements ManagedView {

    private GameController controller;
    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private Image imgCard;
    private Text scoreText;

    public GameView(GameController controller) {
        this.controller = controller;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        addEventHandler(EventManager.GAME_END, event -> onGameEnded());
        buildView();
        gameEventListener();
    }

    private void buildView() {
        getChildren().clear();

        Text title = new Text("Game View");
        scoreText = new Text();
        scoreText.setId("scoreText");
        ImageView imageView = new ImageView();

        scoreText.textProperty().bind(controller.scoreObject().asString("Score: %d"));

        imageView.setImage(null);
        imgCard = controller.getNextCard().getSprite();
        if(imgCard != null) {
            imageView.setImage(imgCard);
            imageView.setFitHeight(300);
            imageView.setFitWidth(150);
            imageView.setPreserveRatio(true);
        }

        Button btnHigher = new Button("Higher");
        btnHigher.setOnAction(e -> {
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
        Button btnLower = new Button("Lower");
        btnLower.setOnAction(e -> {
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

        //gridPane.add(title, 0, 0);
        gridPane.add(scoreText, 1, 0);
        gridPane.add(btnHigher, 0, 2);
        gridPane.add(btnLower, 2, 2);
        gridPane.add(imageView, 1, 1);




        getChildren().addAll(gridPane);
    }


    private void gameEventListener() {
        controller.setEventListener(event -> {
            if (event.getEventType() == EventManager.GAME_END) {
                getChildren().clear();
                ImageView imageView = new ImageView();
                imgCard = controller.getCurrentCard().getSprite();
                if(imgCard != null){
                    imageView.setImage(imgCard);
                    imageView.setFitHeight(300);
                    imageView.setFitWidth(150);
                    imageView.setPreserveRatio(true);
                }
                Text title = new Text("Game Over!");
                title.setId("scoreText");
                Button btnRestart = new Button("Play Again");
                btnRestart.setOnAction(e -> {
                    controller.restartGame();
                    buildView();
                });

                Button btnQuit = new Button("Quit Game");
                btnQuit.setOnAction(e -> {
                    System.exit(0);

                });

                GridPane gridPane = new GridPane();
                gridPane.setMinSize(1200, 650);
                gridPane.setPadding(new Insets(10, 10, 10, 10));
                gridPane.setVgap(5);
                gridPane.setHgap(5);

                gridPane.setAlignment(Pos.CENTER);

                gridPane.add(title, 1, 0);

                GridPane.setHalignment(imageView, HPos.CENTER); // Ensure the image is horizontally centered in its cell
                GridPane.setValignment(imageView, VPos.CENTER); // Ensure the image is vertically centered in its cell

                gridPane.add(imageView, 1, 1);

                gridPane.add(btnRestart, 0, 2);
                gridPane.add(btnQuit, 2, 2);

                getChildren().addAll(gridPane);
                System.out.println("The game has ended!");
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
