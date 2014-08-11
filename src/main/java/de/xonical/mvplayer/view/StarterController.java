package de.xonical.mvplayer.view;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import de.xonical.mvplayer.RootDirectory;
import de.xonical.mvplayer.model.SubDirectory;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;
import listViewTest.MediaToTextLinker;
import listViewTest.VideoFile;

// http://java-buddy.blogspot.de/2013/03/javafx-simple-example-of.html

public class StarterController implements Initializable {

	@FXML
	private Button chooseDirectoryButton;

	@FXML
	private Button startIndexButton;

	@FXML
	private Label selectedDirectoryLabel;

	@FXML
	Label statusbarLabel;

	@FXML
	ImageView imageView;

	private File selectedDirectory;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startIndexButton.setDisable(true);
	}

	@FXML
	private void handleChooseDirectory(ActionEvent event) {
		System.out.println("Foo");
		DirectoryChooser directoryChooser = new DirectoryChooser();

		File selectedDirectory = directoryChooser.showDialog(null); // TODO:
																	// null
																	// durch
																	// view
																	// ersetzen

		if (selectedDirectory == null) {
			if (selectedDirectoryLabel.getText().length() > 0) {
				selectedDirectoryLabel.setText("");
				startIndexButton.setDisable(true);
			}
		} else {
			selectedDirectoryLabel.setText(selectedDirectory.getAbsolutePath());
			startIndexButton.setDisable(false);
			this.selectedDirectory = selectedDirectory;
		}
	}

	@FXML
	private void prepareIndex(ActionEvent event) {
		// RootDirectory rootDirectory = new RootDirectory(
		// selectedDirectory.toPath());

		// We want an array of directories, that are above the root
		File[] subDirectories = selectedDirectory.listFiles((File f) -> {
			return f.isDirectory();
		});

		List<Directory> directories = new ArrayList<>();

		// Let's crap all VideoFiles from a directory
		for (File file : subDirectories) {
			List<VideoFile> videoFiles = MyUtil.getVideoFiles(file.toPath());

			// And link to textfiles, if there are some.
			MediaToTextLinker linker = new MediaToTextLinker(videoFiles);

			// And now add them to a directory bean, but only if a folder
			// contains a media file
			if (videoFiles.size() > 0) {
				Directory directory = new Directory(file.toPath(), videoFiles);
				directories.add(directory);
			}
		}

		if (directories.size() <= 0) {
			statusbarLabel.setText("No media files found!");
		} else {
			startIndex(directories);
		}
	}

	private void startIndex(List<Directory> directories) {
		for (Directory dir : directories) {
			String strFolder = dir.getDirectoryPath().toString();
			String status = "Lese Verzeichnis:" + strFolder + " Datei: ";

			ObservableList<VideoFile> videoFiles = dir.getVideoFiles();


			VideoThumbnailer thumbnailer = new VideoThumbnailer();
			for (VideoFile videoFile : videoFiles) {
				status.concat(videoFile.getPath().toString());

				// Creates a thumbnail and link it to the videoFile
				thumbnailer.create(videoFile);

				Image image = SwingFXUtils.toFXImage(thumbnailer.getImage(), null);

				Platform.runLater(new Runnable(){
					@Override
					public void run() {
						imageView.setImage(image);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});


				statusbarLabel.setText(status);
				if (videoFile.getLinkedFile() != null) {
					// System.out.println(videoFile.getLinkedFile().toString());
				}
			}
		}
	}

	private int counter = 0;

	// http://stackoverflow.com/questions/13691390/automate-video-snapshots
	private void takeSnapshot(VideoFile videoFile) {

	}
}

// player.setOnReady(new Runnable() {
//
// @Override
// public void run() {
// player.seek(Duration.seconds(1));
// player.pause();
// WritableImage wi = new WritableImage(width,height);
// mediaView.snapshot(new SnapshotParameters(), wi);
// //Color c = wi.getPixelReader().getColor(100, 100);
// //System.out.println(c);
// // video.snapshot(params, image);
// //
//
// //http://java-buddy.blogspot.de/2012/12/save-writableimage-to-file.html
// player.stop();
//
//
//
// File file = new File("i:/test" +counter+ ".png");
//
// RenderedImage renderedImage = SwingFXUtils.fromFXImage(wi, null);
// try {
// ImageIO.write(
// renderedImage,
// "png",
// file);
//
// } catch (IOException e) {
// // TODO Auto-generated catch block
// System.out.println("Fooo");
// e.printStackTrace();
// }
//
//
//
//
//
//
//
//
// }});

// while(true){
// try {
// Thread.sleep(500);
// if (Files.exists(Paths.get(temp), LinkOption.NOFOLLOW_LINKS)){
// break;
// }
// } catch (InterruptedException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// }
