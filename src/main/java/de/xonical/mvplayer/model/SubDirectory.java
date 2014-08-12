package de.xonical.mvplayer.model;

import java.nio.file.Path;
import java.util.List;

import de.xonical.mvplayer.util.MyUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class SubDirectory {

	private  StringProperty subDirectory;
	private  ObjectProperty pathOfSubDirectory;
	private  IntegerProperty countedVideoFiles;
	//private final ObjectProperty<LocalDate> birthday;
	private ObservableList<VideoFile> videoFilesData;


//	/**
//	 * Default constructor.
//	 */
//	public SubDirectory() {
//		//this(null);
//	}

	/**
	 * Constructor with some initial data.
	 * @param path
	 *
	 * @param firstName
	 * @param lastName
	 */
	public SubDirectory(Path path) {
		this.setPath(path);

		//this.countedFiles = new SimpleIntegerProperty(new Integer (0));
		//this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}

	public StringProperty subDirectoryProperty() {
		return subDirectory;
	}

	public String getSubDirectory() {
		return subDirectory.get();
	}

	public void setSubDirectory(String firstName) {
		this.subDirectory.set(firstName);
	}

	public IntegerProperty countedFilesProperty() {
		return countedVideoFiles;
	}

	public Integer getCountedVideoFiles() {
		return videoFilesData.size();
	}

	public void setCountedVideoFiles(Integer value) {
		countedVideoFiles.set(value);
	}

	public ObservableList<VideoFile> getVideoFilesData() {
		return this.videoFilesData;
	}

	public void setVideoFilesData(ObservableList<VideoFile> observableList) {
		this.videoFilesData = observableList;
	}

	public ObjectProperty getPath() {
		return this.pathOfSubDirectory;
	}

	public void setPath(Path value){
		this.pathOfSubDirectory.set(value);
	}




//	public LocalDate getBirthday() {
//		return birthday.get();
//	}
//
//	public void setBirthday(LocalDate birthday) {
//		this.birthday.set(birthday);
//	}
//
//	public ObjectProperty<LocalDate> birthdayProperty() {
//		return birthday;
//	}
}