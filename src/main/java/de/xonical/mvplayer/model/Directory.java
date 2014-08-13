package de.xonical.mvplayer.model;

import java.util.List;
import java.util.UUID;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



/**
 *
 * @author adam-bien.com
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Directory.findAll, query = "SELECT a from Directory a")
})
public class Directory {

    public final static String PREFIX = "de.xonical.mvplayer.model.Directory.";
    public final static String findAll = PREFIX + "findAll";
    private StringProperty idProperty;
    private StringProperty directoryNameProperty;
    private SimpleIntegerProperty countedVideoFiles;
	private List<VideoFile> videoFiles;



    public Directory() {
        this.idProperty = new SimpleStringProperty(UUID.randomUUID().toString());
        this.directoryNameProperty = new SimpleStringProperty();
        this.countedVideoFiles = new SimpleIntegerProperty();
    }

    public Directory(String videoFileName, Integer countedVideoFile) {
        this();
        this.directoryNameProperty.set(videoFileName);
        this.countedVideoFiles.set(countedVideoFile);
    }

    @Id
    public String getId() {
        return idProperty.get();
    }

    public void setId(String id) {
        this.idProperty.set(id);
    }

    public StringProperty directoryNameProperty() {
    	return this.directoryNameProperty;
    }

    public String getDirectoryName() {
        return directoryNameProperty.get();
    }

    public void setDirectoryName(String name) {
        this.directoryNameProperty.set(name);
    }

    public int getCountedVideoFiles() {
        return countedVideoFiles.get();
    }

    public void setCountedVideoFiles(int files) {
        this.countedVideoFiles.set(files);
    }

	public void setVideoFiles(List<VideoFile> videoFiles) {
		this.videoFiles = videoFiles;
	}

	public List<VideoFile> getVideoFiles() {
		return videoFiles;
	}
}
