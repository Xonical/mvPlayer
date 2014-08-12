package de.xonical.mvplayer.presentation;

import de.xonical.mvplayer.MainApp;
import de.xonical.mvplayer.model.SubDirectory;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PersonOverviewController {
    @FXML
    private TableView<SubDirectory> personTable;
    @FXML
    private TableColumn<SubDirectory, String> subDirectoryNameColumn;
    @FXML
    private TableColumn<SubDirectory, Number> countedFilesColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private VBox vBox;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
    	subDirectoryNameColumn.setCellValueFactory(cellData -> cellData.getValue().subDirectoryProperty());

    	// Reason why number, not integer: http://stackoverflow.com/questions/24889638/javafx-properties-in-tableview
    	countedFilesColumn.setCellValueFactory(cellData -> cellData.getValue().countedFilesProperty());
    	//countedFilesColumn.setStyle("-fx-alignment: CENTER-RIGHT;");

    	showPersonDetails(null);
    	personTable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getSubDirectoryData());
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(SubDirectory person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getSubDirectory());


            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
}