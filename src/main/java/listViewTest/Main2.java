// http://www.java2s.com/Code/Java/JavaFX/ListViewselectionlistener.htm

package listViewTest;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

class Directory2 {
	private StringProperty directoryName;
	private IntegerProperty countedFiles;
	private ObservableList<Directory2> subDirectories = FXCollections
			.observableArrayList();

	public final void setDirectoryName(String value) {
		directoryNameProperty().set(value);
	}

	public final String getDirectoryName() {
		return directoryNameProperty().get();
	}

	public StringProperty directoryNameProperty() {
		if (directoryName == null) {
			directoryName = new SimpleStringProperty();
		}
		return directoryName;
	}

	public final void setCountedFiles(Integer value) {
		countedFilesProperty().set(value);
	}

	public final Integer getCountedFiles() {
		return countedFilesProperty().get();
	}

	public IntegerProperty countedFilesProperty() {
		if (countedFiles == null) {
			countedFiles = new SimpleIntegerProperty();
		}
		return countedFiles;
	}

	public ObservableList<Directory2> subDirectoriesProperty() {
		return subDirectories;
	}

	public Directory2(String name, Integer countedFiles) {
		setDirectoryName(name);
		setCountedFiles(countedFiles);
	}
}

public class Main2 extends Application {
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

		ObservableList<Directory2> leaders = FXCollections.observableArrayList();
		leaders.add(new Directory2("Foo", new Integer(42)));
		leaders.add(new Directory2("Bar", new Integer(1024)));
		final ListView<Directory2> leaderListView = new ListView<Directory2>(
				leaders);
		leaderListView.setPrefWidth(150);
		leaderListView.setPrefHeight(150);

		//
		leaderListView
				.setCellFactory(new Callback<ListView<Directory2>, ListCell<Directory2>>() {

					public ListCell<Directory2> call(ListView<Directory2> param) {
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

						final ListCell<Directory2> cell = new ListCell<Directory2>() {
							@Override
							public void updateItem(Directory2 item, boolean empty) {
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

		leaderListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {

				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 1) {
						Directory2 item = leaderListView.getSelectionModel()
								.getSelectedItem();
						if (item != null) {
							StackPane pane = new StackPane();
							Scene scene = new Scene(pane);
							Stage stage = new Stage();
							stage.setScene(scene);

							pane.getChildren().add(
									new TextField(item.getDirectoryName()));

							stage.show();
						}

					}
				}
			}
		});

		gridpane.add(leaderListView, 0, 1);

		leaderListView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Directory2>() {
					public void changed(
							ObservableValue<? extends Directory2> observable,
							Directory2 oldValue, Directory2 newValue) {
						System.out.println("selection changed");
					}
				});

		root.getChildren().add(gridpane);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

// class Person {
// private StringProperty aliasName;
// private StringProperty firstName;
// private StringProperty lastName;
// private ObservableList<Person> employees = FXCollections
// .observableArrayList();
//
// public final void setAliasName(String value) {
// aliasNameProperty().set(value);
// }
//
// public final String getAliasName() {
// return aliasNameProperty().get();
// }
//
// public StringProperty aliasNameProperty() {
// if (aliasName == null) {
// aliasName = new SimpleStringProperty();
// }
// return aliasName;
// }
//
// public final void setFirstName(String value) {
// firstNameProperty().set(value);
// }
//
// public final String getFirstName() {
// return firstNameProperty().get();
// }
//
// public StringProperty firstNameProperty() {
// if (firstName == null) {
// firstName = new SimpleStringProperty();
// }
// return firstName;
// }
//
// public final void setLastName(String value) {
// lastNameProperty().set(value);
// }
//
// public final String getLastName() {
// return lastNameProperty().get();
// }
//
// public StringProperty lastNameProperty() {
// if (lastName == null) {
// lastName = new SimpleStringProperty();
// }
// return lastName;
// }
//
// public ObservableList<Person> employeesProperty() {
// return employees;
// }
//
// public Person(String alias, String firstName, String lastName) {
// setAliasName(alias);
// setFirstName(firstName);
// setLastName(lastName);
// }
//
// }

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