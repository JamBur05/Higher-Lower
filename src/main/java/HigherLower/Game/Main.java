package HigherLower.Game;

import HigherLower.Game.controller.GameController;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import HigherLower.Game.model.Leaderboard;
import HigherLower.Game.view.GameView;
import HigherLower.Game.view.LeaderboardView;
import HigherLower.Game.view.MenuView;
import HigherLower.Game.view.ViewManager;


public class Main extends Application {

    private Leaderboard leaderboard;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        leaderboard = new Leaderboard();

        stage.setTitle("Higher Lower");

        // Get screen resolution
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double x = screenBounds.getMaxX();
        double y = screenBounds.getMaxY();

        stage.setWidth(x * 0.8);
        stage.setHeight(y * 0.8);

        ViewManager vm = new ViewManager(stage);
        GameController gameController = new GameController(leaderboard);

        // Add all views to the viewmanager
        vm.addView(ViewManager.LEADERBOARD, new LeaderboardView(gameController));
        vm.addView(ViewManager.GAME, new GameView(gameController));
        vm.addView(ViewManager.MENU, new MenuView(gameController));
        vm.setStageView(ViewManager.MENU);

        stage.show();
    }
}