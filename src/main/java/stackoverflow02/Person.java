package stackoverflow02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
    public final String getFirstName() {
        return firstName.get();
    }
    public final void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    public StringProperty firstNameProperty() {
        return firstName ;
    }

    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
    public final String getLastName() {
        return lastName.get();
    }
    public final void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public StringProperty lastNameProperty() {
        return lastName ;
    }

    private final StringProperty email = new SimpleStringProperty(this, "email");
    public final String getEmail() {
        return email.get();
    }
    public final void setEmail(String email) {
        this.email.set(email);
    }
    public StringProperty emailProperty() {
        return email ;
    }

    public Person(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }
}