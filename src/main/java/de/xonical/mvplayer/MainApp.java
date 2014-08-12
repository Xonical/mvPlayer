package de.xonical.mvplayer;

import java.io.IOException;

import com.airhacks.afterburner.injection.Injector;

import de.xonical.mvplayer.model.SubDirectory;
import de.xonical.mvplayer.presentation.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private VBox rootLayout2;
	private VBox rootLayout3;

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<SubDirectory> subDirectories = FXCollections
			.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {
		// Add some sample data
		//subDirectories.add(new SubDirectory("d:/enzo"));
		//subDirectories.add(new SubDirectory("d:/javacv"));
	}

	/**
	 * Returns the data as an observable list of Persons.
	 *
	 * @return
	 */
	public ObservableList<SubDirectory> getSubDirectoryData() {
		return subDirectories;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MediathekView-Player");

		initRootLayout();

		// showPersonOverview();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			// FXMLLoader loader = new FXMLLoader();
			// loader.setLocation(MainApp.class
			// .getResource("view/RootLayout.fxml"));
			// rootLayout = (BorderPane) loader.load();

//			 FXMLLoader loader = new FXMLLoader();
//			 loader.setLocation(MainApp.class
//			 .getResource("Playground.fxml"));
//			 rootLayout3 = (VBox) loader.load();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("presentation/start/Starter.fxml"));
			rootLayout3 = (VBox) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout3);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPersonOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 *
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		Injector.forgetAll();
	}
}