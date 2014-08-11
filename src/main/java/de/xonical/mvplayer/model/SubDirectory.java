package de.xonical.mvplayer.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class SubDirectory {

	private final StringProperty subDirectory;
	private  IntegerProperty countedFiles;
	//private final ObjectProperty<LocalDate> birthday;

	/**
	 * Default constructor.
	 */
	public SubDirectory() {
		this(null);
	}

	/**
	 * Constructor with some initial data.
	 *
	 * @param firstName
	 * @param lastName
	 */
	public SubDirectory(String subDirectory) {
		this.subDirectory = new SimpleStringProperty(subDirectory);
		this.countedFiles = new SimpleIntegerProperty(new Integer (0));
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
		return countedFiles;
	}

	public Integer getCountedFiles() {
		return countedFiles.get();
	}

	public void setCountedFiles(Integer countedFiles) {
		this.countedFiles.set(countedFiles);
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