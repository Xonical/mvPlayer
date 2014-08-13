package de.xonical.mvplayer.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {

	   private final ObservableList<Directory> directories = FXCollections.observableArrayList();
	    private final ObjectProperty<Directory> currentDirectory = new SimpleObjectProperty<>(this, "currentDirectory");

	    public ObservableList<Directory> getDirectories() {
	        return directories ;
	    }

	    public final Directory getCurrentDirectory() {
	        return currentDirectory.get();
	    }

	    public final void setCurrentDirectory(Directory directory) {
	        this.currentDirectory.set(directory);
	    }

	    public ObjectProperty<Directory> currentDirectoryProperty() {
	        return currentDirectory ;
	    }
}
