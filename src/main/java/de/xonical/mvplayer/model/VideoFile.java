package de.xonical.mvplayer.model;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.google.common.io.Files;

import listViewTest.MediaFile;

@Entity
@NamedQueries({
    @NamedQuery(name = VideoFile.findAll, query = "SELECT a from VideoFile a")
})
public class VideoFile {

    public final static String PREFIX = "de.xonical.mvplayer.model.VideoFile.";
    public final static String findAll = PREFIX + "findAll";
    private StringProperty idProperty;

	private Path pathVideoFile;
	private Path pathTextFile;
	private Path pathThumbnailFile;
	private SimpleStringProperty videoFileNameProperty;
	private Path mediaFile;

	public VideoFile() {
        this.idProperty = new SimpleStringProperty(UUID.randomUUID().toString());
        this.videoFileNameProperty = new SimpleStringProperty();
	}

	public VideoFile(Path mp4File) {
	    this();
		//super(mp4File);
        this.idProperty = new SimpleStringProperty(UUID.randomUUID().toString());
		//System.out.println("new VideoFile" + mp4File);
        this.videoFileNameProperty.set(mp4File.toString());
        this.mediaFile = mp4File;
	}

    @Id
    public String getId() {
        return idProperty.get();
    }

    public void setId(String id) {
        this.idProperty.set(id);
    }

//	public static void main(String[] args) {
//		VideoFile videoFile = new VideoFile(Paths.get("d:/test/test.mp4"));
//		System.out.println(videoFile.getPathWithoutExtension());
//	}

    public StringProperty videoFileNameProperty() {
    	return this.videoFileNameProperty;
    }

    public String getVideoFileName() {
        return videoFileNameProperty.get();
    }

    public void setVideoFileName(String name) {
        this.videoFileNameProperty.set(name);
    }

	public void setLinkedFile(String linkedTextFile) {
		pathTextFile = Paths.get(linkedTextFile);
	}

	public Path getLinkedFile(){
		return pathTextFile;
	}

	public void setThumbnail(Path path) {
		this.pathThumbnailFile = path;
	}
	public Path getThumbnail() {
		return this.pathThumbnailFile;
	}

	public String getExtension() {
		return Files.getFileExtension(mediaFile.toString());
	}

	public String getNameWithoutExtension() {
		return Files.getNameWithoutExtension(mediaFile.toString());
	}

	public Path getPath() {
		return mediaFile;
	}

	public String getPathWithoutExtension() {
		Path parentDirectory = mediaFile.getParent();
		String returnValue = parentDirectory.toString()
				+  FileSystems.getDefault().getSeparator()
				+  Files.getNameWithoutExtension(mediaFile.toString());
		return returnValue;
	}
}
