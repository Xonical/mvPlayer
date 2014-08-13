package de.xonical.mvplayer.presentation.directories;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import javax.inject.Inject;

import de.xonical.mvplayer.model.DataModel;
import de.xonical.mvplayer.model.Directory;
import de.xonical.mvplayer.model.RegistrationService;
import de.xonical.mvplayer.model.SubDirectory;
import de.xonical.mvplayer.model.VideoFile;

public class DirectoryPresenter implements Initializable {

	@FXML
	private TableView<Directory> directoriesTable;
	private ObservableList<Directory> directories;
	private ObjectProperty<Directory> selectedDirectoryProperty;

	@Inject
	private DataModel dataModel;

	@Inject
	RegistrationService service;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		directoriesTable.setItems(dataModel.getDirectories());

		directoriesTable.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldDirectory, newDirectory) -> {
					dataModel.setCurrentDirectory(newDirectory);
					System.out.println(newDirectory.getDirectoryName());
				});

		dataModel.currentDirectoryProperty().addListener(
				(obs, oldDirectory, newDirectory) -> {
					if (newDirectory == null) {
						directoriesTable.getSelectionModel().clearSelection();
					} else {
						directoriesTable.getSelectionModel().select(
								newDirectory);
					}
				});

		this.directories = FXCollections.observableArrayList();

		// selectedDirectoryProperty = new SimpleObjectProperty<Directory>();
		// //selectedDirectoryProperty.addListener(new
		// ListChangeListener<String>() {
		//
		// this.selectedDirectoryProperty.addListener(new
		// ChangeListener<Directory>() {
		// @Override
		// public void changed(ObservableValue<? extends Directory> observable,
		// Directory oldValue, Directory newValue) {
		// if (newValue != null) {
		// System.out.println("C1: " + newValue);
		// System.out.println(newValue.getDirectoryName());
		// } else {
		// // resetUI();
		// System.out.println("else");
		// }
		// }
		// });

		// TODO
		List<Directory> all = service.allDirectories();
		dataModel.getDirectories().addAll(all);

		// for (int j = 0; j < 4; j++) {
		// Directory dir = new Directory();
		// System.out.println("BarB: " + j);
		// dir.setDirectoryName("A: " + j);
		// dir.setCountedVideoFiles(j);
		// dataModel.getDirectories().add(dir);
		// }

	}

	private List<SubDirectory> createListOfSubDirectories(
			ObservableList<ObservableList<VideoFile>> allVideosInRootDirectory) {

//		final List<Directory> allSubDirectories = new ArrayList();
//
//		// Every folder in root
//		for (final ObservableList<VideoFile> videoFiles : allVideosInRootDirectory) {
//
//			SubDirectory subDirectory = null;
//
//			// Every video file of each subfolder
//			for (final VideoFile videoFile : videoFiles) {
//				final Path parent = videoFile.getPath().getParent();
//				subDirectory = new SubDirectory(parent);
//				// we can break the loop, because we have the parent directory
//				break;
//			}
//			final ObservableList<VideoFile> observableList = FXCollections
//					.observableList(videoFiles);
//			subDirectory.setVideoFilesData(observableList);
//			// allSubDirectories.add(subDirectory);
//		}
		return null;
	}
}
