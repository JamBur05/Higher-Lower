package HigherLower.Game.view;

import HigherLower.Game.controller.GameController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

/**
 * View logic for the leaderboard view
 * @param controller instance of the game controller
 * */
public class LeaderboardView extends VBox implements ManagedView {

    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private GameController controller;

    public LeaderboardView(GameController gameController) {
        this.controller = gameController;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        buildView();
    }

    private void buildView(){
        getChildren().clear();

        Text title = new Text("Top Scores");
        Button btnReturn = new Button("Return to menu");


        title.setId("title");
        title.setFill(Color.color(0.9137254901960784, 0.1843137254901961, 0.4666666666666667));
        title.setStrokeWidth(1);
        title.setStroke(Color.WHITE);

        List<String> scoresArrayList = controller.getTopScores(5);

        // Observable list to store scores so it can dynamically update as list is modified
        ObservableList<String> observableScores = FXCollections.observableArrayList(scoresArrayList);

        ListView<String> listView = new ListView<>();
        listView.setItems(observableScores);

        // Event listener for return to main menu button, switch view to menu
        btnReturn.setOnAction(e -> {
            var eh = onViewChange.get();
            if (eh != null) {
                eh.handle(new ViewChangeEvent(ViewManager.MENU));
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1200, 650);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(title, 0, 0);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setValignment(title, VPos.CENTER);
        gridPane.add(listView, 0, 1);
        gridPane.add(btnReturn, 0, 2);
        GridPane.setHalignment(btnReturn, HPos.CENTER);
        GridPane.setValignment(btnReturn, VPos.CENTER);

        getChildren().add(gridPane);
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
