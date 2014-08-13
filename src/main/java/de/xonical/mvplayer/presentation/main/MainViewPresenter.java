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

import de.xonical.mvplayer.model.Directory;
import de.xonical.mvplayer.model.RegistrationService;
import de.xonical.mvplayer.model.SubDirectory;
import de.xonical.mvplayer.model.VideoFile;
import de.xonical.mvplayer.presentation.directories.DirectoryView;
import de.xonical.mvplayer.presentation.directories.DirectoryPresenter;
import de.xonical.mvplayer.util.Settings;

public class MainViewPresenter implements Initializable {

	@FXML
	AnchorPane directoriesPane;

	@Inject
	RegistrationService service;

	private DirectoryPresenter directoryPresenter;
	private DirectoryView directoryView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.directoryView = new DirectoryView();
		directoryPresenter = (DirectoryPresenter) this.directoryView
				.getPresenter();

		this.directoriesPane.getChildren().add(this.directoryView.getView());
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
}
