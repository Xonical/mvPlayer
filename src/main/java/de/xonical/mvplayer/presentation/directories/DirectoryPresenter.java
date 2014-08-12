package de.xonical.mvplayer.presentation.directories;


import java.net.URL;
import java.util.ResourceBundle;

import de.xonical.mvplayer.model.Attendee;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author adam-bien.com
 */
public class DirectoryPresenter implements Initializable {

    @FXML
    private TableView<Attendee> directoriesTable;
    private ObservableList<Attendee> attendees;
    private BooleanProperty editingStarted;
    private ObjectProperty<Attendee> deletedAttendee;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.attendees = FXCollections.observableArrayList();
        this.editingStarted = new SimpleBooleanProperty();
        this.deletedAttendee = new SimpleObjectProperty<>();
        prepareTable();
        registerListeners();
    }

    public void prepareTable() {
       // this.attendeesTable.setEditable(true);
        ObservableList columns = directoriesTable.getColumns();
        final TableColumn firstNameColumn = createTextColumn("firstName", "First Name");
        firstNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Attendee, String>>() {
            @Override
            public void handle(CellEditEvent<Attendee, String> t) {
                getEditedAttendee(t).setFirstName(t.getNewValue());
                editingStarted.set(false);
            }
        });

        columns.add(firstNameColumn);

        final TableColumn lastNameColumn = createTextColumn("lastName", "Last Name");
        lastNameColumn.setOnEditCommit(new EventHandler<CellEditEvent<Attendee, String>>() {
            @Override
            public void handle(CellEditEvent<Attendee, String> t) {
                getEditedAttendee(t).setLastName(t.getNewValue());
                editingStarted.set(false);
            }
        });
        columns.add(lastNameColumn);

        final TableColumn companyColumn = createTextColumn("company", "Company");
        companyColumn.setOnEditCommit(new EventHandler<CellEditEvent<Attendee, String>>() {
            @Override
            public void handle(CellEditEvent<Attendee, String> t) {
                getEditedAttendee(t).setCompany(t.getNewValue());
                editingStarted.set(false);
            }
        });
        columns.add(companyColumn);

        final TableColumn paidColumn = createBooleanColumn("paid", "Paid");
        paidColumn.setOnEditCommit(new EventHandler<CellEditEvent<Attendee, Boolean>>() {
            @Override
            public void handle(CellEditEvent<Attendee, Boolean> t) {
                getEditedAttendee(t).setPaid(t.getNewValue());
                editingStarted.set(false);
            }
        });
        columns.add(paidColumn);

        directoriesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        directoriesTable.setItems(this.attendees);
        directoriesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    void registerListeners() {
        this.directoriesTable.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (editingStarted.getValue()) {
                    t.consume();
                    return;
                }
                if (t.getCode().equals(KeyCode.BACK_SPACE)) {
                    Attendee selectedItem = directoriesTable.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        fireAttendeeDeleted(selectedItem);
                    }
                }
            }
        });
    }

    private TableColumn createTextColumn(String name, String caption) {
        TableColumn column = new TableColumn(caption);
        appendEditListeners(column);
        column.setCellValueFactory(new PropertyValueFactory<Attendee, String>(name));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        return column;
    }

    private TableColumn createBooleanColumn(String name, String caption) {
        TableColumn column = new TableColumn(caption);
        appendEditListeners(column);
        column.setCellValueFactory(new PropertyValueFactory<Attendee, Boolean>(name));
        column.setCellFactory(CheckBoxTableCell.forTableColumn(column));
        return column;
    }

    public void add(Attendee newAttendee) {
        this.attendees.add(newAttendee);
    }

    Attendee getEditedAttendee(CellEditEvent<Attendee, ?> t) {
        return (Attendee) t.getTableView().getItems().get(
                t.getTablePosition().getRow());
    }

    private void appendEditListeners(TableColumn column) {
        column.setOnEditStart(new EventHandler() {
            @Override
            public void handle(Event t) {
                editingStarted.set(true);
            }
        });
        column.setOnEditCancel(new EventHandler() {
            @Override
            public void handle(Event t) {
                editingStarted.set(false);
            }
        });

    }

    public void clearAll() {
        this.directoriesTable.getItems().clear();
    }

    public ReadOnlyBooleanProperty editingStarted() {
        return this.editingStarted;
    }

    void fireAttendeeDeleted(Attendee deletedItem) {
        directoriesTable.getSelectionModel().clearSelection();
        this.deletedAttendee.set(deletedItem);
    }

    public ObjectProperty<Attendee> deletedAttendeeProperty() {
        return this.deletedAttendee;
    }

    public ReadOnlyObjectProperty<Attendee> selectedAttendeeProperty() {
        return this.directoriesTable.getSelectionModel().selectedItemProperty();
    }
}
