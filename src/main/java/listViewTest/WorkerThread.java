package listViewTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class WorkerThread extends Thread {
	private File selectedDirectory;
	private ImageView imageView;
	private Label statusbarLabel;
	private File [] subDirectories;
	private WritableImage image;

	public WorkerThread(File selectedDirectory, ImageView imageView,
			Label statusbarLabel) {
//		setDaemon(true);
//		setName("Thread " + "");
//		this.selectedDirectory = selectedDirectory;
//		this.imageView = imageView;
//		this.statusbarLabel = statusbarLabel;
//
//
//			// We want an array of directoriesPane, that are above the root
//		subDirectories = selectedDirectory.listFiles((File f) -> {
//			return f.isDirectory();
//		});
//
//
//
//	}
//
//	private void initThread() {
//
//		// RootDirectory rootDirectory = new RootDirectory(
//		// selectedDirectory.toPath());
//
//
//
//
//      List<Directory> directoriesPane = new ArrayList<>();
//		// Let's crap all VideoFiles from a directory
//		for (File file : subDirectories) {
//			List<VideoFile> videoFiles = MyUtil.getVideoFiles(file.toPath());
//
//			// And link to textfiles, if there are some.
//			MediaToTextLinker linker = new MediaToTextLinker(videoFiles);
//
//			// And now add them to a directory bean, but only if a folder
//			// contains a media file
//			if (videoFiles.size() > 0) {
//				Directory directory = new Directory(file.toPath(), videoFiles);
//				directoriesPane.add(directory);
//			}
//		}
//
//		if (directoriesPane.size() <= 0) {
//			statusbarLabel.setText("No media files found!");
//		} else {
//			startIndex(directoriesPane);
//		}
//
//	}
//
//	private void startIndex(List<Directory> directoriesPane) {
//		for (Directory dir : directoriesPane) {
//			String strFolder = dir.getDirectoryPath().toString();
//			String status = "Lese Verzeichnis:" + strFolder + " Datei: ";
//
//			ObservableList<VideoFile> videoFiles = dir.getVideoFiles();
//
//			VideoThumbnailer thumbnailer = new VideoThumbnailer();
//			for (VideoFile videoFile : videoFiles) {
//				status.concat(videoFile.getPath().toString());
//
//				// Creates a thumbnail and link it to the videoFile
//				thumbnailer.create(videoFile);
//
//				image = SwingFXUtils.toFXImage(thumbnailer.getImage(),
//						null);
//
//
//						//imageView.setImage(image);
//
//
//
//				if (videoFile.getLinkedFile() != null) {
//					// System.out.println(videoFile.getLinkedFile().toString());
//				}
//			}
//		}
//	}
//
//	@Override
//	public void run() {
//
//		while (!this.isInterrupted()) {
//			initThread();
//			// UI updaten
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					// entsprechende UI Komponente updaten
//					//this.imageView.;
//					imageView.setImage(image);
//					statusbarLabel.setText("");
//				}
//			});
//
//			// Thread schlafen
//			try {
//				// fuer 3 Sekunden
//				sleep(TimeUnit.SECONDS.toMillis(3));
//			} catch (InterruptedException ex) {
//				Logger.getLogger(WorkerThread.class.getName()).log(
//						Level.SEVERE, null, ex);
//			}
//		}
//
	}

}
