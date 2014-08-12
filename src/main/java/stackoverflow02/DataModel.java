package stackoverflow02;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {
    private final ObservableList<Person> people = FXCollections.observableArrayList();
    private final ObjectProperty<Person> currentPerson = new SimpleObjectProperty<>(this, "currentPerson");

    public ObservableList<Person> getPeople() {
        return people ;
    }

    public final Person getCurrentPerson() {
        return currentPerson.get();
    }

    public final void setCurrentPerson(Person person) {
        this.currentPerson.set(person);
    }

    public ObjectProperty<Person> currentPersonProperty() {
        return currentPerson ;
    }
}