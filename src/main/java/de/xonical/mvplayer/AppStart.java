package de.xonical.mvplayer;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;
import com.airhacks.afterburner.injection.Injector;
import de.xonical.mvplayer.presentation.start.StartView;

public class AppStart extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*
         * Properties of any type can be easily injected.
         */
        LocalDate date = LocalDate.of(4242, Month.JULY, 21);
        Map<Object, Object> customProperties = new HashMap<>();
        customProperties.put("date", date);
        /*
         * any function which accepts an Object as key and returns
         * and return an Object as result can be used as source.
         */
        Injector.setConfigurationSource(customProperties::get);

        System.setProperty("happyEnding", " Enjoy the flight!");
        StartView startView = new StartView();
        Scene scene = new Scene(startView.getView());
        stage.setTitle("MV-Player 0.0.1");
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
    	Logger.info("Starte Applikation");
    	Logger.warn("Test Warnung");

    	launch(args);
    }
}