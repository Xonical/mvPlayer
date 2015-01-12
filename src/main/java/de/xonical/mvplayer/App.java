package de.xonical.mvplayer;

import com.airhacks.afterburner.injection.Injector;

import de.xonical.mvplayer.presentation.main.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sebastian Schön
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainView appView = new MainView();
        Scene scene = new Scene(appView.getView());
        stage.setTitle("AirHacks Control");
        final String uri = getClass().getResource("app.css").toExternalForm();
        scene.getStylesheets().add(uri);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
