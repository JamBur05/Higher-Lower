package view;

import controller.GameController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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



        getChildren().addAll(title, imageView, btnHigher, btnLower, scoreText);
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
                Button btnRestart = new Button("Play Again");
                btnRestart.setOnAction(e -> {
                    controller.restartGame();
                    buildView();
                });
                getChildren().addAll(title, btnRestart, imageView);
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
