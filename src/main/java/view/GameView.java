package view;

import controller.GameController;
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

    public GameView(GameController controller) {
        this.controller = controller;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        addEventHandler(EventManager.GAME_END, event -> onGameEnded());
        buildView();
        gameEventListener();
    }

    private void buildView() {
        Text title = new Text("Game View");
        ImageView imageView = new ImageView();

        Button btnHigher = new Button("Higher");
        Button btnLower = new Button("Lower");

        Button test = new Button("Test");
        test.setOnAction(e -> {
            imageView.setImage(null);
            imgCard = controller.getNextCard().getSprite();
            if(imgCard != null){
                imageView.setImage(imgCard);
                imageView.setFitHeight(300);
                imageView.setFitWidth(150);
                imageView.setPreserveRatio(true);
            }
        });


        getChildren().addAll(title, test, imageView);
    }

    private void gameEventListener() {
        controller.setEventListener(event -> {
            if (event.getEventType() == EventManager.GAME_END) {
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
