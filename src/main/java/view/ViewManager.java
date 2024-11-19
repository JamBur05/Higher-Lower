package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ViewManager {
    public static final String MENU = "menu_view";
    public static final String GAME = "game_view";

    private final Stage stage;
    private final Map<String, Parent> scenes = new HashMap<>();

    public ViewManager(Stage stage) {
        this.stage = stage;
    }

    public <T extends Parent & ManagedView> void addView(String key, T s) {
        scenes.put(key, s);
        s.setOnViewChange(e -> setStageView(e.getView()));
    }

    public void setStageView(String key) {
        if (!scenes.containsKey(key)) {
            return;
        }

        Parent view = scenes.get(key);
        StackPane root = new StackPane(view);
        root.setId("pane");

        Scene s = new Scene(root);
        s.getStylesheets().addAll(this.getClass().getResource("/style.css").toExternalForm());
        stage.setScene(s);
    }


}
