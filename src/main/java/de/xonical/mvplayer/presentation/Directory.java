package de.xonical.mvplayer.presentation;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import de.xonical.mvplayer.model.VideoFile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
// http://stackoverflow.com/questions/23643898/does-the-use-of-observablelist-in-javafx-go-against-model-view-controller-separa
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Directory {
	private StringProperty directoryName;
	private ObjectProperty<Path> directoryPath;
	//private ObjectProperty< List<VideoFile> > videoFiles;
	private IntegerProperty countedFiles;
//	private ObservableList<Directory> subDirectories = FXCollections
//			.observableArrayList();
	private ObservableList<VideoFile> videoFiles;

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



	public final void setVideoFiles(ObservableList<VideoFile> videoFiles) {
		this.videoFiles = videoFiles;
	}

	public final ObservableList<VideoFile> getVideoFiles() {
		return videoFiles;
	}

//	public ObjectProperty ObservableList<VideoFile>  videoFilesProperty() {
//		return videoFiles;
//	}


	public final void setDirectoryPath(Path path) {
		if(directoryPath == null){
			directoryPath = new SimpleObjectProperty<Path>();
		}
		directoryPath.set(path);
	}

	public final Path getDirectoryPath() {
		return directoryPath.get();
	}

	public ObjectProperty<Path>  directoryPathProperty() {
		return directoryPath;
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





	// Warum klappt der Code...von mir?
//	public ObservableList<Directory> subDirectoriesProperty() {
//		return subDirectories;
//	}

	public Directory(Path path, List<VideoFile> videoFiles) {

		ObservableList<VideoFile> list = FXCollections.observableArrayList(videoFiles);

		setDirectoryPath(path);
		setVideoFiles(list);
	}
}