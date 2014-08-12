package stackoverflow02;


import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class EditorPresenter {
    @FXML
    private TextField firstNameTextField ;
    @FXML
    private TextField lastNameTextField ;
    @FXML
    private TextField emailTextField ;
    @FXML
    private Button addEditButton ;

    @Inject
    private DataModel dataModel ;

    public void initialize() {
        addEditButton.textProperty().bind(
            Bindings.when(Bindings.isNull(dataModel.currentPersonProperty()))
                .then("Add")
                .otherwise("Update")
        );

        dataModel.currentPersonProperty().addListener((obs, oldPerson, newPerson) -> {
            if (newPerson == null) {
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                emailTextField.setText("");
            } else {
                firstNameTextField.setText(newPerson.getFirstName());
                lastNameTextField.setText(newPerson.getLastName());
                emailTextField.setText(newPerson.getEmail());
            }
        });
    }

    @FXML
    private void addEdit() {
        Person person = dataModel.getCurrentPerson();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        if (person == null) {
            dataModel.getPeople().add(new Person(firstName, lastName, email));
        } else {
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setEmail(email);
        }
    }
}