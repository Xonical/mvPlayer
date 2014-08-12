// http://www.java2s.com/Code/Java/JavaFX/ListViewselectionlistener.htm

package listViewTest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import de.xonical.mvplayer.model.VideoFile;
import de.xonical.mvplayer.presentation.Directory;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("");
		Group root = new Group();
		Scene scene = new Scene(root, 500, 250, Color.WHITE);
		// create a grid pane
		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(5));
		gridpane.setHgap(10);
		gridpane.setVgap(10);

		ObservableList<Directory> directories = FXCollections.observableArrayList();

		Path path1 = Paths.get("i:/MediathekView/Abendschau");
		Path path2 = Paths.get("i:/MediathekView/Frag_den_Lesch");
//		directoriesPane.add(new Directory (path1 , null));
//		directoriesPane.add(new Directory ( path2, null));


		ObservableList<Movie> movies = FXCollections.observableArrayList();
		movies.add(new Movie("Inglourious Basterds"));
		movies.add(new Movie("Star Wars"));
		movies.add(new Movie("Starship Troopers"));






		final ListView<Directory> leaderListView = new ListView<Directory>(
				directories);
		leaderListView.setPrefWidth(150);
		leaderListView.setPrefHeight(150);

		//
		leaderListView
				.setCellFactory(new Callback<ListView<Directory>, ListCell<Directory>>() {

					public ListCell<Directory> call(ListView<Directory> param) {
						final Label leadLbl1 = new Label();
						final Label leadLbl2 = new Label();
						// final Tooltip tooltip = new Tooltip();
						final HBox hBox = new HBox();
						hBox.getChildren().add(leadLbl1);
						hBox.getChildren().add(leadLbl2);

						hBox.setHgrow(leadLbl1, Priority.ALWAYS);
						hBox.setHgrow(leadLbl2, Priority.ALWAYS);
						leadLbl1.setMaxWidth(Double.MAX_VALUE);
						leadLbl2.setMaxWidth(Double.MAX_VALUE);

						leadLbl2.setAlignment(Pos.CENTER_RIGHT);

						final ListCell<Directory> cell = new ListCell<Directory>() {
							@Override
							public void updateItem(Directory item, boolean empty) {
								super.updateItem(item, empty);
								if (item != null) {
									leadLbl1.setText(item.getDirectoryName());
									leadLbl2.setText(item.getCountedFiles()
											.toString());
								}
							}
						}; // ListCell

						cell.setGraphic(hBox);
						return cell;
					}
				}); // setCellFactory


		gridpane.add(leaderListView, 0, 1);




//		leaderListView.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>() {
//		        @Override
//		        public ListCell<String> call(ListView<String> listView) {
//		            return new ListViewCell();
//		        }
//		    });








		leaderListView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Directory>() {
					public void changed(
							ObservableValue<? extends Directory> observable,
							Directory oldValue, Directory newValue) {

						// Erster Aufruf. Item in View wurde noch nicht gewählt
						// Ausgabe
						// oldValue: null
						// newValue: listViewTest.Directory@7a5dc95f
						// Danach java.lang.NullPointerException, weil es kein vorheriger Value bestand
						//System.out.println("oldValue: " + oldValue);
						System.out.println("newValue: " + newValue);
						System.out.println("=======================");
						//System.out.println("oldValue: " + oldValue.getDirectoryName() + " : " + oldValue.getCountedFiles());
						System.out.println("newValue: " + newValue.getDirectoryName() + " : " + newValue.getCountedFiles());

						SubDirectory subDir = new SubDirectory(newValue);
						List<VideoFile> videoFileList = subDir.getVideoFileList();

						for (VideoFile videoFile : videoFileList) {
							System.out.println(videoFile.getPath());

						}
					}
				});




		root.getChildren().add(gridpane);
//
//		Bindings.bindBidirectional(arg0, arg1)

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

// public class Main extends Application {
// public static void main(String[] args) {
// Application.launch(args);
// }
//
// @Override
// public void start(Stage primaryStage) {
// primaryStage.setTitle("");
// Group root = new Group();
// Scene scene = new Scene(root, 500, 250, Color.WHITE);
// // create a grid pane
// GridPane gridpane = new GridPane();
// gridpane.setPadding(new Insets(5));
// gridpane.setHgap(10);
// gridpane.setVgap(10);
//
// ObservableList<Person> leaders = FXCollections.observableArrayList();
// leaders.add(new Person("A", "B", "C"));
// leaders.add(new Person("D", "E", "F"));
// final ListView<Person> leaderListView = new ListView<Person>(leaders);
// leaderListView.setPrefWidth(150);
// leaderListView.setPrefHeight(150);
//
// //
// leaderListView
// .setCellFactory(new Callback<ListView<Person>, ListCell<Person>>() {
//
// public ListCell<Person> call(ListView<Person> param) {
// final Label leadLbl1 = new Label();
// final Label leadLbl2 = new Label();
// final Tooltip tooltip = new Tooltip();
// final HBox hBox = new HBox();
// hBox.getChildren().add(leadLbl1);
// hBox.getChildren().add(leadLbl2);
//
// hBox.setHgrow(leadLbl1, Priority.ALWAYS);
// hBox.setHgrow(leadLbl2, Priority.ALWAYS);
// leadLbl1.setMaxWidth(Double.MAX_VALUE);
// leadLbl2.setMaxWidth(Double.MAX_VALUE);
//
// leadLbl2.setAlignment(Pos.CENTER_RIGHT);
//
//
//
// final ListCell<Person> cell = new ListCell<Person>() {
// @Override
// public void updateItem(Person item, boolean empty) {
// super.updateItem(item, empty);
// if (item != null) {
// // leadLbl.setText(item.getAliasName());
// // leadLbl2.setText("333");
// leadLbl1.setText("Foo");
// leadLbl2.setText("333");
// leadLbl2.setStyle("-fx-alignment: right;");
// // setText(item.getFirstName() + " " +
// // item.getLastName());
// // tooltip.setText(item.getAliasName());
// // setTooltip(tooltip);
// }
// }
// }; // ListCell
//
// cell.setGraphic(hBox);
// return cell;
// }
// }); // setCellFactory

// public ListCell<Person> call(ListView<Person> param) {
// final Label leadLbl = new Label();
// final Tooltip tooltip = new Tooltip();
// final ListCell<Person> cell = new ListCell<Person>() {
// @Override
// public void updateItem(Person item, boolean empty) {
// super.updateItem(item, empty);
// if (item != null) {
// leadLbl.setText(item.getAliasName());
// setText(item.getFirstName() + " " + item.getLastName());
// tooltip.setText(item.getAliasName());
// setTooltip(tooltip);
// }
// }
// }; // ListCell
// return cell;
// }
// }); // setCellFactory

// gridpane.add(leaderListView, 0, 1);
//
// leaderListView.getSelectionModel().selectedItemProperty()
// .addListener(new ChangeListener<Person>() {
// public void changed(
// ObservableValue<? extends Person> observable,
// Person oldValue, Person newValue) {
// System.out.println("selection changed");
// }
// });
//
// root.getChildren().add(gridpane);
//
// primaryStage.setScene(scene);
// primaryStage.show();
// }
//
// }