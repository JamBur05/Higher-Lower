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

public class GameView extends VBox implements ManagedView {

    private GameController controller;
    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private Image imgCard;

    public GameView(GameController controller) {
        this.controller = controller;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        buildView();
    }

    private void buildView() {
        Text title = new Text("Game View");
        ImageView imageView = new ImageView();

        Button test = new Button("Test");
        test.setOnAction(e -> {
            imageView.setImage(null);
            imgCard = controller.getNextCard().getSprite();
            if(imgCard != null){
                imageView.setImage(imgCard);
            }
        });

        getChildren().addAll(title, test, imageView);
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
