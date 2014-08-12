package de.xonical.mvplayer.presentation.main;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import javax.inject.Inject;

import de.xonical.mvplayer.model.Attendee;
import de.xonical.mvplayer.model.RegistrationService;
import de.xonical.mvplayer.model.SubDirectory;
import de.xonical.mvplayer.model.VideoFile;
import de.xonical.mvplayer.presentation.directories.DirectoryPresenter;
import de.xonical.mvplayer.presentation.directories.DirectoryView;

public class MainViewPresenter implements Initializable {

	@FXML
	private TableView<SubDirectory>				directoryTable;
	@FXML
	private TableColumn<SubDirectory, String>	directoryNameColumn;
	@FXML
	private TableColumn<SubDirectory, Number>	countedVideoFilesColumn;

	@FXML
	AnchorPane									directoriesPane;
	private DirectoryView						directoryView;

	@Inject
	RegistrationService							service;

	private ObjectProperty<Attendee>			selectedAttendee;
	private ObjectProperty<Attendee>			newAttendee;
	private DirectoryPresenter					directoryPresenter;
	private ObjectProperty<Attendee>			attendees;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.selectedAttendee = new SimpleObjectProperty<>();
		this.newAttendee = new SimpleObjectProperty<>();





		newAttendeeProperty().addListener(new ChangeListener<Attendee>() {
			@Override
			public void changed(ObservableValue<? extends Attendee> ov,
					Attendee old, Attendee newAttendee) {
				System.out.println("D1: " + newAttendee.getFirstName());
				if (newAttendee != null) {
					//newAttendeeProperty().set(newAttendee);
				}
			}
		});

		final ChangeListener<Attendee> storingListener = new ChangeListener<Attendee>() {
			@Override
			public void changed(ObservableValue<? extends Attendee> ov,
					Attendee old, Attendee newAttendee) {
				System.out.println("e1: ");
				if (newAttendee != null) {
					service.save(newAttendee);
					//loadFromStore();
				}
			}
		};

		this.selectedAttendee = new SimpleObjectProperty<>();

		this.attendees = new SimpleObjectProperty<>();
		this.newAttendee.addListener(storingListener);
		// final ObservableList<Directory> directoriesPane = FXCollections
		// .observableArrayList();
		// //
		// final ObservableList<ObservableList<VideoFile>>
		// allVideosInRootDirectory = Settings
		// .getInstance().getAllVideosInDirectory();
		// //
		// // Initialize the person table with the two columns.
		// directoryNameColumn.setCellValueFactory(cellData ->
		// cellData.getValue()
		// .subDirectoryProperty());
		//
		// // Reason why number, not integer:
		// //
		// http://stackoverflow.com/questions/24889638/javafx-properties-in-tableview
		// countedVideoFilesColumn.setCellValueFactory(cellData -> cellData
		// .getValue().countedFilesProperty());
		// countedFilesColumn.setStyle("-fx-alignment: CENTER-RIGHT;");

		// directoryTable
		// .getSelectionModel()
		// .selectedItemProperty()
		// .addListener(
		// (observable, oldValue, newValue) -> showDirectoryDetails(newValue));
		// //
		// List<SubDirectory> listOfSubDirectories =
		// createListOfSubDirectories(allVideosInRootDirectory);
		// ObservableList<SubDirectory> oListOfSubDirectories =
		// FXCollections.observableArrayList(listOfSubDirectories);
		// directoryTable.setItems(oListOfSubDirectories);

		this.directoryView = new DirectoryView();
		directoryPresenter = (DirectoryPresenter) this.directoryView
				.getPresenter();

		this.registerAttendeeListeners();
		this.directoriesPane.getChildren().add(this.directoryView.getView());

		this.selectedAttendee.addListener(new ChangeListener<Attendee>() {
			@Override
			public void changed(ObservableValue<? extends Attendee> observable,
					Attendee oldValue, Attendee newValue) {
				if (newValue != null) {
					System.out.println("C1: " + newValue);
					System.out.println(newValue.firstNameProperty().get());
				} else {
					// resetUI();
					System.out.println("else");
				}
			}
		});

		directoryPresenter.clearAll();
		loadFromStore();

		for (int i = 0; i < 2; i++) {
			System.out.println("Foo: " + i);
			save(i);
		}

	}

	void loadFromStore() {
		List<Attendee> all = service.all();
		for (Attendee attendee : all) {
			directoryPresenter.add(attendee);
		}
	}

	void registerAttendeeListeners() {
		ChangeListener<Attendee> deletionListener = new ChangeListener<Attendee>() {
			@Override
			public void changed(ObservableValue<? extends Attendee> observable,
					Attendee oldValue, Attendee newValue) {
				if (newValue != null) {
					System.out.println("a1");
					service.remove(newValue);
					loadFromStore();
				}
			}
		};

		ChangeListener<Attendee> selectionListener = new ChangeListener<Attendee>() {
			@Override
			public void changed(ObservableValue<? extends Attendee> observable,
					Attendee oldValue, Attendee newValue) {
				System.out.println("b1");
				selectedAttendee.set(newValue);
			}
		};
		this.directoryPresenter.deletedAttendeeProperty().addListener(
				deletionListener);
		this.directoryPresenter.selectedAttendeeProperty().addListener(
				selectionListener);

	}

	private List<SubDirectory> createListOfSubDirectories(
			ObservableList<ObservableList<VideoFile>> allVideosInRootDirectory) {

		final List<SubDirectory> allSubDirectories = new ArrayList();

		// Every folder in root
		for (final ObservableList<VideoFile> videoFiles : allVideosInRootDirectory) {

			SubDirectory subDirectory = null;

			// Every video file of each subfolder
			for (final VideoFile videoFile : videoFiles) {
				final Path parent = videoFile.getPath().getParent();
				subDirectory = new SubDirectory(parent);
				// we can break the loop, because we have the parent directory
				break;
			}
			final ObservableList<VideoFile> observableList = FXCollections
					.observableList(videoFiles);
			subDirectory.setVideoFilesData(observableList);
			allSubDirectories.add(subDirectory);
		}
		return allSubDirectories;
	}

	private void showDirectoryDetails(SubDirectory newValue) {

	}

	private void save(int i) {

		Attendee attendee;// = selectedAttendee.get();
		//if (attendee == null) {
			//System.out.println("Bar: " + i);
			attendee = new Attendee();
		//}
		System.out.println("BarB: " + i);
		attendee.setFirstName("A: " + i);
		attendee.setLastName("B: " + i);
		attendee.setCompany("C: " + i);
//		attendee.setPaid(true);
//		attendee.setArchitecture(true);
//		attendee.setBootstrap(true);
//		attendee.setEffective(true);
//		attendee.setUi(true);
//		attendee.setJavaee(true);

		this.newAttendee.set(attendee);
	}

	public ObjectProperty<Attendee> newAttendeeProperty() {
		return newAttendee;
	}

}

// --------------------
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
// package com.airhacks.control.presentation.attendeeinput;
//
// import com.airhacks.control.business.registrations.entity.Attendee;
// import java.net.URL;
// import java.util.ResourceBundle;
// import javafx.beans.binding.BooleanBinding;
// import javafx.beans.property.BooleanProperty;
// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.CheckBox;
// import javafx.beans.property.ObjectProperty;
// import javafx.beans.property.SimpleBooleanProperty;
// import javafx.beans.property.SimpleObjectProperty;
// import javafx.beans.value.ChangeListener;
// import javafx.beans.value.ObservableValue;
// import javafx.fxml.Initializable;
// import javafx.scene.control.TextField;
//
// /**
// * FXML Controller class
// *
// * @author adam-bien.com
// */
// public class AttendeeInputPresenter implements Initializable {
//
// @FXML
// Button saveButton;
// @FXML
// TextField firstName;
// @FXML
// TextField lastName;
// @FXML
// TextField company;
// @FXML
// CheckBox paid;
// @FXML
// CheckBox boot;
// @FXML
// CheckBox effect;
// @FXML
// CheckBox architect;
// @FXML
// CheckBox ui;
// @FXML
// CheckBox javaee;
// private ObjectProperty<Attendee> selectedAttendee;
// private ObjectProperty<Attendee> newAttendee;
//
// /**
// * Initializes the controller class.
// */
// @Override
// public void initialize(URL url, ResourceBundle rb) {
// this.saveButton.disableProperty().bind(getSaveButtonDisabledBinding());
//
// this.selectedAttendee = new SimpleObjectProperty<>();
// this.newAttendee = new SimpleObjectProperty<>();
//
// this.selectedAttendee.addListener(new ChangeListener<Attendee>() {
// @Override
// public void changed(ObservableValue<? extends Attendee> observable, Attendee
// oldValue, Attendee newValue) {
// if (newValue != null) {
// boot.setSelected(newValue.isBootstrap());
// effect.setSelected(newValue.isEffective());
// architect.setSelected(newValue.isArchitecture());
// ui.setSelected(newValue.isUi());
// javaee.setSelected(newValue.isJavaee());
//
// firstName.setText(newValue.getFirstName());
// lastName.setText(newValue.getLastName());
// company.setText(newValue.getCompany());
// paid.setSelected(newValue.isPaid());
// } else {
// resetUI();
// }
// }
// });
// }
//
//
//
// public void save() {
//
// Attendee attendee = selectedAttendee.get();
// if (attendee == null) {
// attendee = new Attendee();
// }
// attendee.setFirstName(firstName.getText());
// attendee.setLastName(lastName.getText());
// attendee.setCompany(company.getText());
// attendee.setPaid(paid.isSelected());
// attendee.setArchitecture(architect.isSelected());
// attendee.setBootstrap(boot.isSelected());
// attendee.setEffective(effect.isSelected());
// attendee.setUi(ui.isSelected());
// attendee.setJavaee(javaee.isSelected());
//
// this.newAttendee.set(attendee);
// }
//
// public ObjectProperty<Attendee> selectedAttendeeProperty() {
// return selectedAttendee;
// }
//
// public ObjectProperty<Attendee> newAttendeeProperty() {
// return newAttendee;
// }
// }

