package de.xonical.mvplayer.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.xonical.mvplayer.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlaygroundController implements Initializable {

	@FXML
	private VBox vbox;

	@FXML
	private VBox vbox2;

	@FXML
	private ScrollPane scrollPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// for (int i = 0; i < 10; i++) {
		// Label l = new Label(""+i);
		// hbox.getChildren().addAll(l);
		// }

		// try {
		// Parent root = FXMLLoader.load(getClass().getResource
		// ("MovieContainer.fxml"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// URL location = getClass().getResource("MovieContainer.fxml");
		//
		// FXMLLoader fxmlLoader = new FXMLLoader();
		// fxmlLoader.setLocation(location);
		// fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		//
		// Parent root = null;
		// try {
		// root = (Parent) fxmlLoader.load(location.openStream());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// for (int i = 0; i < 10; i++) {
		// hbox.getChildren().addAll(root);
		// }

		// MovieContainerController controller = (MovieContainerController)
		// fxmlLoader.getController();

		try {
			for (int i = 0; i < 7; i++) {
				// Works
				// hbox.getChildren().add(
				// FXMLLoader.load(getClass().getResource(
				// "MovieContainer.fxml")));

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(PlaygroundController.class
						.getResource("MovieContainer.fxml"));
				HBox personOverview = (HBox) loader.load();
				//
				// // Set person overview into the center of root layout.
				// rootLayout.setCenter(personOverview);
				//vbox.getChildren().add(personOverview);
				vbox2.getChildren().add(personOverview);
				//scrollPane.setContent(personOverview);
				//Scene scene = new Scene(personOverview);
//
				Button btn = (Button) personOverview.lookup("#showNameButton");
				// Label label = (Label) scene.lookup("#mylabel");


				final int zz =i;
				btn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						btn.setText(""+zz);
					}
				});

				// // Give the controller access to the main app.
//				MovieContainerController controller = loader.getController();
//				controller.setMainApp(this);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
