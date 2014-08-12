package stackoverflow02;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class MainPresenter {
    @FXML
    private TableView<Person> table ;

    // This is the controller (presenter) for the included fxml
    // It is injected by the FXMLLoader; the rule is that "Controller" needs to be
    // appended to the fx:id attribute of the <fx:include> tag.
    // This is not used in this example but is here to demonstrate how to access it
    // if needed.
    @FXML
    private EditorPresenter editorController ;

    @Inject
    private DataModel dataModel ;

    public void initialize() {
        table.setItems(dataModel.getPeople());

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldPerson, newPerson) -> dataModel.setCurrentPerson(newPerson));

        dataModel.currentPersonProperty().addListener((obs, oldPerson, newPerson) -> {
            if (newPerson == null) {
                table.getSelectionModel().clearSelection();
            } else {
                table.getSelectionModel().select(newPerson);
            }
        });

        dataModel.getPeople().addAll(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );
    }
}