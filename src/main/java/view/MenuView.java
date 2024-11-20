package view;

import controller.GameController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 * View logic for the main menu functions
 * @param controller instance of the game controller
 * */
public class MenuView extends VBox implements ManagedView {

    protected ObjectProperty<EventHandler<? super ViewChangeEvent>> onViewChange;
    private GameController controller;

    public MenuView(GameController controller) {
        this.controller = controller;
        this.onViewChange = new SimpleObjectProperty<>("onViewChange", null);
        buildView();
    }


    private void buildView() {
        getChildren().clear();
        Text title = new Text("Higher or Lower?");
        title.setId("title");
        title.setFill(Color.color(0.9137254901960784, 0.1843137254901961, 0.4666666666666667));
        title.setStrokeWidth(1);
        title.setStroke(Color.WHITE);

        Button btnStart = new Button("Start Game");
        Button btnQuit = new Button("Quit Game");
        Button btnRules = new Button("Rules");
        Button btnLeaderboard = new Button("View Leaderboard");
        CheckBox chkJoker = new CheckBox("Include Jokers");
        chkJoker.setId("check");
        chkJoker.setTextFill(Color.color(0.9137254901960784, 0.1843137254901961, 0.4666666666666667));

        TextField username = new TextField();
        username.setText("Username");

        btnRules.setOnAction(e -> {
            showRules();
        });

        btnStart.setOnAction(e -> {
            var eh = onViewChange.get();
            if (eh != null) {
                if(chkJoker.isSelected()){
                    controller.setJoker();
                }
                controller.setUsername(username.getText());
                eh.handle(new ViewChangeEvent(ViewManager.GAME));
            }
        });

        btnLeaderboard.setOnAction(e -> {
            var eh = onViewChange.get();
            if (eh != null) {
                eh.handle(new ViewChangeEvent(ViewManager.LEADERBOARD));
            }
        });

        btnQuit.setOnAction(e -> {
           System.exit(0);
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1200, 650);
        gridPane.setPadding(new Insets(20, 10, 10, 10));
        gridPane.setVgap(20); // Increase vertical spacing between rows
        gridPane.setHgap(20); // Horizontal spacing between columns

        gridPane.setAlignment(Pos.CENTER);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(50);
        row1.setPrefHeight(80);

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(50);
        row2.setPrefHeight(80);

        RowConstraints row3 = new RowConstraints();
        row3.setMinHeight(50);
        row3.setPrefHeight(80);

        gridPane.getRowConstraints().addAll(row1, row2, row3);

        gridPane.add(title, 0, 0, 3, 1);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setValignment(title, VPos.TOP);

        gridPane.add(chkJoker, 1, 1);
        GridPane.setHalignment(chkJoker, HPos.CENTER);
        GridPane.setValignment(chkJoker, VPos.CENTER);
        gridPane.add(username, 1, 2);
        GridPane.setHalignment(username, HPos.CENTER);
        GridPane.setValignment(username, VPos.CENTER);

        gridPane.add(btnStart, 0, 3);
        gridPane.add(btnRules, 2, 3);

        gridPane.add(btnLeaderboard, 1, 4);
        GridPane.setHalignment(btnQuit, HPos.CENTER);
        GridPane.setValignment(btnQuit, VPos.CENTER);

        gridPane.add(btnQuit, 1, 5);
        GridPane.setHalignment(btnQuit, HPos.CENTER);
        GridPane.setValignment(btnQuit, VPos.CENTER);

        getChildren().add(gridPane);
    }

    private void showRules() {
        getChildren().clear();

        Text title = new Text("How to play!");
        title.setId("title");
        title.setFill(Color.color(0.9137254901960784, 0.1843137254901961, 0.4666666666666667));
        title.setStrokeWidth(1);
        title.setStroke(Color.WHITE);
        Text rules = new Text("To play the game, simply select higher or lower depending\n" +
                "on what you think the next card will be when compared to the current card.\n" +
                "\nAces count as a 1\n" +
                "Jokers count as 14");
        rules.setId("scoreText");
        rules.setFill(Color.color(0.9137254901960784, 0.1843137254901961, 0.4666666666666667));
        rules.setStrokeWidth(0.5);
        rules.setStroke(Color.WHITE);
        Button btnReturn = new Button("Return to menu");
        btnReturn.setOnAction(e -> {
           buildView();
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1200, 650);
        gridPane.setPadding(new Insets(20, 10, 10, 10));
        gridPane.setVgap(20); // Increase vertical spacing between rows
        gridPane.setHgap(20); // Horizontal spacing between columns

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(title, 0, 0);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setValignment(title, VPos.CENTER);
        gridPane.add(rules, 0, 1);
        GridPane.setHalignment(rules, HPos.CENTER);
        GridPane.setValignment(rules, VPos.CENTER);
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
