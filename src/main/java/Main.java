import controller.GameController;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.GameView;
import view.ViewManager;

import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        System.out.println("Hello world!");

        stage.setTitle("Higher Lower");

        // Get screen resolution
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        double x = screenBounds.getMaxX();
        double y = screenBounds.getMaxY();

        stage.setWidth(x * 0.8);
        stage.setHeight(y * 0.8);

        ViewManager vm = new ViewManager(stage);
        GameController gameController = new GameController();

        vm.addView(ViewManager.GAME, new GameView(gameController));
        vm.setStageView(ViewManager.GAME);

        stage.show();
    }
}