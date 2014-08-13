package de.xonical.mvplayer.model;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import com.google.common.io.Files;

@Entity
@NamedQueries({
    @NamedQuery(name = VideoFile.findAll, query = "SELECT a from VideoFile a")
})
public class VideoFile {

    public final static String PREFIX = "de.xonical.mvplayer.model.VideoFile.";
    public final static String findAll = PREFIX + "findAll";
    private StringProperty idProperty;

//	private Path pathVideoFile;
//	private Path pathTextFile;
//	private Path pathThumbnailFile;
	private StringProperty thumbnailFileProperty;
	private StringProperty videoFileNameProperty;
	private StringProperty descriptionFileNameProperty;
//	private Path mediaFile;

	public VideoFile() {
        this.idProperty = new SimpleStringProperty(UUID.randomUUID().toString());
        this.videoFileNameProperty = new SimpleStringProperty();
        this.descriptionFileNameProperty = new SimpleStringProperty();
        this.thumbnailFileProperty = new SimpleStringProperty();
        descriptionFileNameProperty.set("");
        thumbnailFileProperty.set("");
		//System.out.println("new VideoFile" + mp4File);
        //this.videoFileNameProperty.set(mp4File.toString());
       // this.mediaFile = mp4File;
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

    public StringProperty thumbnailFileProperty() {
    	return this.thumbnailFileProperty;
    }

    public String getThumbnailFile() {
        return thumbnailFileProperty.get();
    }

    public void setThumbnailFile(String name) {
        this.thumbnailFileProperty.set(name);
    }

    public StringProperty descriptionFileNameProperty() {
    	return this.descriptionFileNameProperty;
    }

    public String getDescriptionFileName() {
        return descriptionFileNameProperty.get();
    }

    public void setDescriptionFileName(String name) {
    	if (descriptionFileNameProperty == null) {
			descriptionFileNameProperty = new SimpleStringProperty();
		}
        this.descriptionFileNameProperty.set(name);
    }

//	public void setLinkedFile(String linkedTextFile) {
//		pathTextFile = Paths.get(linkedTextFile);
//	}

//	public Path getLinkedFile(){
//		return pathTextFile;
//	}

//	public void setThumbnail(Path path) {
//		this.pathThumbnailFile = path;
//	}
//	public String getThumbnail() {
//		return this.pathThumbnailFile.toString();
//	}

//	public String getExtension() {
//		return Files.getFileExtension(mediaFile.toString());
//	}
//
//	public String getNameWithoutExtension() {
//		return Files.getNameWithoutExtension(mediaFile.toString());
//	}
//
//	public Path getPath() {
//		return mediaFile;
//	}
//
//	public String getPathWithoutExtension() {
//		Path parentDirectory = mediaFile.getParent();
//		String returnValue = parentDirectory.toString()
//				+  FileSystems.getDefault().getSeparator()
//				+  Files.getNameWithoutExtension(mediaFile.toString());
//		return returnValue;
//	}
}
