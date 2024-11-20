import controller.GameController;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Leaderboard;
import view.GameView;
import view.LeaderboardView;
import view.MenuView;
import view.ViewManager;


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