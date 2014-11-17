package de.xonical.mvplayer.presentation.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import de.xonical.mvplayer.model.VideoFile;
import de.xonical.mvplayer.presentation.main.AdvancedMedia.MediaControl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class ImageboxPresenter implements Initializable {

	@FXML
	ImageView imageView;

	@FXML
	Label imageLabel;

	StringProperty text = new SimpleStringProperty();
	StringProperty description = new SimpleStringProperty();

	private VideoFile video;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		imageLabel.textProperty().bind(text);
		imageView.setOnMouseClicked((MouseEvent event) -> {
            showVideoDetails();
            if(event.getClickCount() == 2){
            	showVideoStage();
            }
        });

	}

	private void showVideoStage() {



//		AdvancedMedia am = new AdvancedMedia();
//		MediaControl mediaControl = am.getMediaControl();
//
//
//
//      File file = new File(video.getVideoFileName());
//      final String MEDIA_URL = file.toURI().toString();

//      Group root = new Group();
//      Scene scene = new Scene(root, 480, 280);
//
//      // create media player
//      Media media = new Media(MEDIA_URL);
//      MediaPlayer mediaPlayer = new MediaPlayer(media);
//      mediaPlayer.setAutoPlay(true);
//
//      // create mediaView and add media player to the viewer
//      MediaView mediaView = new MediaView(mediaPlayer);
//
//      ((Group)scene.getRoot()).getChildren().add(mediaControl);
//      Stage s2 = new Stage();
//      s2.setScene(scene);
//      s2.show();

        File file = new File(video.getVideoFileName());
        final String MEDIA_URL = file.toURI().toString();

        Group root = new Group();
        Scene scene = new Scene(root, 540, 210);

        // create media player
        Media media = new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        // create mediaView and add media player to the viewer
        MediaView mediaView = new MediaView(mediaPlayer);
        ((Group)scene.getRoot()).getChildren().add(mediaView);
        Stage s2 = new Stage();
        s2.setScene(scene);
        s2.show();
	}

	private void showVideoDetails() {
		if(this.video!=null){
			String descriptionFileName = video.getDescriptionFileName();

			if(descriptionFileName == null || !descriptionFileName.isEmpty()){
				this.description.set(video.getDescriptionFileName());
			}

			if(descriptionFileName == null || descriptionFileName.isEmpty()){
				this.description.set("Keine Beschreibung vorhanden");
			}


//			List<String> lines = null;
//			try {
//				lines = Files.readLines(new File(descriptionFileName), Charsets.UTF_8);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}

	public void setTextImageLabel(String string) {
		// this.imageLabel.setText("Blaa");
		File f = new File(string);
		String name = f.getName();
		String nameWithoutExtension = Files.getNameWithoutExtension(name);

		// Cut the name please...99 length of chars is to much

		if (nameWithoutExtension.length() > 12) {
			String substring = nameWithoutExtension.substring(0, 12);
			text.set(substring);
		} else {
			text.set(nameWithoutExtension);
		}
	}

	public void setImage(Image image) {
		this.imageView.setImage(image);
	}

	public void setVideoFile(VideoFile video) {
		this.video = video;
	}

	public void setDescriptionTextArea(TextArea descriptionTextArea) {
		descriptionTextArea.textProperty().bind(description);
	}

}
