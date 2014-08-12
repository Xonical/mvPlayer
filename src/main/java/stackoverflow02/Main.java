package stackoverflow02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.airhacks.afterburner.injection.Injector;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView.getView(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}