package musicPlayer;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller();
            controller.startApp("file:/c:/Users/Gian/Desktop/4221/Java/Java%20Programs/Test%20Projects/MusicPlayer/res/Media/");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
