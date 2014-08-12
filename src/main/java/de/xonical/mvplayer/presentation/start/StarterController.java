package de.xonical.mvplayer.presentation.start;

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

import com.airhacks.followme.App;

import de.xonical.mvplayer.RootDirectory;
import de.xonical.mvplayer.model.SubDirectory;
import de.xonical.mvplayer.model.VideoFile;
import de.xonical.mvplayer.presentation.Directory;
import de.xonical.mvplayer.presentation.VideoThumbnailer;
import de.xonical.mvplayer.presentation.main.MainView;
import de.xonical.mvplayer.service.GUIUpdater;
import de.xonical.mvplayer.util.MyUtil;
import de.xonical.mvplayer.util.Settings;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import listViewTest.MediaToTextLinker;

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

	@FXML
	ProgressBar progressBar;

	@FXML
	TextArea textArea;

	@FXML
	Button startProgramm;

	@FXML
	VBox rootContainer;

	private File selectedDirectory;

	//private MyService myService;

	private List<VideoFile> videoFiles;
	// private ObservableList<ObservableList<VideoFile>> videoFilesData;
	private ObservableList<ObservableList<VideoFile>> videoFilesData = FXCollections
			.observableArrayList();

	private int countedVideoFiles;

	private SimpleIntegerProperty foo;

	// private Duration videoFilesData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startIndexButton.setDisable(true);
		textArea.textProperty().addListener(new ChangeListener<Object>() {
		    @Override
		    public void changed(ObservableValue<?> observable, Object oldValue,
		            Object newValue) {
		    	//textArea.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
		        //use Double.MIN_VALUE to scroll to the top
		    	//System.out.println("Scroo");
		    	//textArea.appendText("");
		    }
		});
	}

	@FXML
	private void handleChooseDirectory(ActionEvent event) {
		System.out.println("Foo");
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File("g:/MediathekView"));

		File selectedDirectory = directoryChooser.showDialog(null); // TODO:
																	// null
																	// durch
																	// view //
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
			// videoFilesData = new ObservableList<
			// ObservableList<VideoFile>>();
		}
	}

	@FXML
	private void prepareIndex(ActionEvent event) {

		// Create task:
		TestTask testTask = new TestTask();

		// Bind:
		GUIUpdater.bind(StarterController.this.statusbarLabel.textProperty(),
				testTask.statusbarTextProperty());
		GUIUpdater.bind(StarterController.this.textArea.textProperty(),
				testTask.getHistoryProperty());

		StarterController.this.progressBar.progressProperty().bind(
				testTask.progressProperty()); // No need to use GUIUpdater here,
												// Task class provides the same
												// functionality for progress.

		foobar();
		testTask.setCountedFilesProperty(countedVideoFiles);
		testTask.setVideoFilesData(this.videoFilesData);
		// GUIUpdater.bind(foo, testTask.countedFiles);

		// Start task:
		Thread tmpThread = new Thread(testTask);
		tmpThread.start();
	}

	private void foobar() {

		// We want an array of directoriesPane, that are above the root
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

			for (Directory dir : directories) {
				String strFolder = dir.getDirectoryPath().toString();
				String status = "Lese Verzeichnis:" + strFolder + " Datei: ";

				ObservableList<VideoFile> videoFiles = dir.getVideoFiles();

				countedVideoFiles += videoFiles.size();
				System.out.println("Foo1: " + countedVideoFiles);
				videoFilesData.add(videoFiles);

			}
		}
	}

	private void foo() {
		// RootDirectory rootDirectory = new RootDirectory(
		// selectedDirectory.toPath());
	}

	@FXML
	private void startProgramm(ActionEvent event){
		openNewWindow2("../main/MainView.fxml");
	}


 	private void openNewWindow2(String string) {

 		Settings.getInstance().setAllVideosInDirectory(videoFilesData);

 		MainView appView = new MainView ();
 		Scene scene = new Scene(appView.getView());

 		Stage stageTheLabelBelongs = (Stage) this.rootContainer.getScene().getWindow();
 		stageTheLabelBelongs.setScene(scene);
	}

	private void openNewWindow(String FXMLFile) {
 	     //ChildNode child;
 	    try {
 	        URL url = getClass().getResource(FXMLFile);
 	        FXMLLoader fxmlLoader = new FXMLLoader();
 	        fxmlLoader.setLocation(url);
 	        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
 	        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream());

 	        rootContainer.getChildren().clear();///name of pane where you want to put the fxml.
 	        rootContainer.getChildren().add(page);
 	    }
 	    catch (IOException e) {
 	        e.printStackTrace();
 	    }
	}


	private class TestTask extends Task<Void> {
		private SimpleStringProperty statusbar = new SimpleStringProperty();
		private SimpleStringProperty errors = new SimpleStringProperty();
		private SimpleStringProperty bytesParsed = new SimpleStringProperty();
		private SimpleIntegerProperty countedFiles = new SimpleIntegerProperty();
		private SimpleStringProperty history = new SimpleStringProperty();
		private ObservableList<ObservableList<VideoFile>> videoFilesData;
		private SimpleObjectProperty<WritableImage> imageProperty;

		@Override
		protected Void call() throws Exception {

			try {
				int counter = 0;
				int maxValue = countedFiles.get();

				long startTime = System.currentTimeMillis();


				System.out.println("Starting...");

				VideoThumbnailer nailer = new VideoThumbnailer();
					// Ordner durchgehen
					for (ObservableList<VideoFile> videoFiles : this.videoFilesData) {



						for(VideoFile videoFile: videoFiles){
							nailer.create(videoFile);
							//Thread.sleep(100);
							this.setHistory(videoFile.getNameWithoutExtension()
									+ "\n");
							 this.updateProgress(counter, maxValue - 1);
							counter++;
							String value = "Datei " + counter + " von " + maxValue + " eingelesen";
							this.setStatusbarProperty(value);
						}
					}



				long stopTime = System.currentTimeMillis();
				System.out.println("Done in " + (stopTime - startTime)
						+ " msec!");
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Unbind:
			GUIUpdater.unbind(
					StarterController.this.statusbarLabel.textProperty(),
					this.statusbarTextProperty());
			GUIUpdater.unbind(StarterController.this.foo, this.countedFiles);
			GUIUpdater.unbind(StarterController.this.textArea.textProperty(),
					this.getHistoryProperty());

			return null;
		}

		public SimpleObjectProperty <WritableImage> getImageProperty() {
			if(this.imageProperty== null){
				this.imageProperty = new SimpleObjectProperty<WritableImage>();
			}
			return this.imageProperty;
		}

//		private void setImage(WritableImage fxImage) {
//			this.imageProperty.set(fxImage);
//		}

		public SimpleStringProperty getHistoryProperty() {
			return this.history;
		}

		public void setHistory(String value) {
			if (this.history == null) {
				history = new SimpleStringProperty();
			}
			String strTemp = this.history.get();
			strTemp += value;
			this.history.set(strTemp);
		}

		public void setVideoFilesData(
				ObservableList<ObservableList<VideoFile>> videoFilesData) {
			this.videoFilesData = videoFilesData;
		}

		public SimpleStringProperty statusbarTextProperty() {
			return this.statusbar;
		}

		public SimpleIntegerProperty getCountedFilesProperty() {
			return this.countedFiles;
		}

		public void setCountedFilesProperty(int value) {
			this.countedFiles.set(value);
		}

		public void setStatusbarProperty(String value) {
			this.statusbar.set(value);
		}

	}
}