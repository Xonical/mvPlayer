package de.xonical.mvplayer.presentation.start;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.inject.Inject;
import com.google.common.io.Files;
import de.xonical.mvplayer.model.Directory;
import de.xonical.mvplayer.model.RegistrationService;
import de.xonical.mvplayer.model.VideoFile;
import de.xonical.mvplayer.presentation.VideoThumbnailer;
import de.xonical.mvplayer.presentation.main.MainView;
import de.xonical.mvplayer.service.GUIUpdater;
import de.xonical.mvplayer.util.MyUtil;
import de.xonical.mvplayer.util.Settings;

// http://java-buddy.blogspot.de/2013/03/javafx-simple-example-of.html

public class StarterController implements Initializable {

	@FXML private Button chooseDirectoryButton;

	@FXML private Button startIndexButton;

	@FXML private Label selectedDirectoryLabel;

	@FXML Label statusbarLabel;

	@FXML ImageView imageView;

	@FXML ProgressBar progressBar;

	@FXML TextArea textArea;

	@FXML Button startProgramm;

	@FXML VBox rootContainer;
	@Inject RegistrationService service;

	private File selectedDirectory;

	private SimpleIntegerProperty counterForLabel;

//	private ObservableList<ObservableList<VideoFile>> videoFilesData = FXCollections
//			.observableArrayList();

	private int countedVideoFiles;

	private List<Directory> directories;

//	private SimpleIntegerProperty counterForLabel;

	// private Duration videoFilesData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startIndexButton.setDisable(true);




		List<Directory> allDirectories = service.allDirectories();
		if(allDirectories.size() > 0){
			// Database with data exists
			statusbarLabel.setText("Datenbank bereits angelegt");
			startIndexButton.setDisable(true);
			chooseDirectoryButton.setDisable(true);
			startProgramm.setDisable(false);
		}else{
			chooseDirectoryButton.setDisable(false);
			startIndexButton.setDisable(true);
			startProgramm.setDisable(true);
		}
	}

	@FXML
	private void handleChooseDirectory(ActionEvent event) {
		System.out.println("Foo");
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File("i:/MediathekView"));

		File selectedDirectory = directoryChooser.showDialog(null); // TODO:
																	// null
																	// view //
																	// ersetzen

		if (selectedDirectory == null) {
			if (selectedDirectoryLabel.getText().length() > 0) {
				selectedDirectoryLabel.setText("Bitte Verzeichnis wählen");
			}
		} else {
			selectedDirectoryLabel.setText(selectedDirectory.getAbsolutePath());
			startIndexButton.setDisable(false);
			this.selectedDirectory = selectedDirectory;
		}
	}

	@FXML
	private void startIndex(ActionEvent event) {

		prepareIndex();



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

		testTask.setCountedFilesProperty(countedVideoFiles);
		testTask.setDirectoryData(this.directories);
		// GUIUpdater.bind(counterForLabel, testTask.countedFiles);

		// Start task:
		Thread tmpThread = new Thread(testTask);
		tmpThread.start();
	}

	private void prepareIndex() {

		// We want an array of directories, that are down the root
		File[] subDirectories = selectedDirectory.listFiles((File f) -> {
			return f.isDirectory();
		});

		int countedVideosInDirectories = 0;
		List<Directory> directories = new ArrayList<Directory>();
		for (File subDir :subDirectories){
			Directory dir = new Directory();
			dir.setDirectoryName(subDir.getAbsolutePath());

			// We want all Videofiles (only mp4)
			List<VideoFile> videoFiles = MyUtil.getVideoFiles(subDir.toPath());

			int videosInDirectory = videoFiles.size();
			dir.setCountedVideoFiles(videosInDirectory);
			countedVideosInDirectories += videosInDirectory;

			// Link the video data to the text description
			MediaToTextLinker linker = new MediaToTextLinker(videoFiles);

			// Now it's linked to text and a directory keep the references to their media
			dir.setVideoFiles(videoFiles);
			directories.add(dir);
		}


		if (directories.size() <= 0) {
			statusbarLabel.setText("Keine Mediendateien im Ordner " + selectedDirectory.getAbsolutePath() + " gefunden.");
			this.directories.clear();
		}else{
			statusbarLabel.setText(countedVideosInDirectories + " Mediendateien gefunden");
			this.directories = directories;
		}
	}


	@FXML
	private void startProgramm(ActionEvent event) {
		Path tempDirectory = Settings.getInstance().getInstance()
				.getTempDirectory();
		System.out.println("TempDIR: " + tempDirectory);
		openNewWindow2("../main/MainView.fxml");
	}

	private void openNewWindow2(String string) {

		//Settings.getInstance().setAllVideosInDirectory(videoFilesData);

		MainView appView = new MainView();
		Scene scene = new Scene(appView.getView());

		Stage stageTheLabelBelongs = (Stage) this.rootContainer.getScene()
				.getWindow();
		stageTheLabelBelongs.setScene(scene);
	}

	private void openNewWindow(String FXMLFile) {
		// ChildNode child;
		try {
			URL url = getClass().getResource(FXMLFile);
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(url);
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream());

			rootContainer.getChildren().clear();// /name of pane where you want
												// to put the fxml.
			rootContainer.getChildren().add(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class TestTask extends Task<Void> {
		private SimpleStringProperty statusbar = new SimpleStringProperty();
		private SimpleStringProperty errors = new SimpleStringProperty();
		private SimpleStringProperty bytesParsed = new SimpleStringProperty();
		private SimpleIntegerProperty countedFiles = new SimpleIntegerProperty();
		private SimpleStringProperty history = new SimpleStringProperty();

		private SimpleObjectProperty<WritableImage> imageProperty;
		private List<Directory> directories;

		@Override
		protected Void call() throws Exception {

			try {
				int counter = 0;
				int maxValue = countedFiles.get();

				long startTime = System.currentTimeMillis();

				System.out.println("Starting...");

				VideoThumbnailer nailer = new VideoThumbnailer();
				// Ordner durchgehen
				for (Directory dir : directories) {

					List<VideoFile> videoFiles = dir.getVideoFiles();

					for (VideoFile videoFile : videoFiles) {
						nailer.create(videoFile);
						// Thread.sleep(100);


						String nameWithoutExtension = Files.getNameWithoutExtension(videoFile.getVideoFileName());


						this.setHistory(nameWithoutExtension
								+ "\n");
						this.updateProgress(counter, maxValue - 1);
						counter++;
						String value = "Datei " + counter + " von " + maxValue
								+ " eingelesen";
						this.setStatusbarProperty(value);
					}
				}

				long stopTime = System.currentTimeMillis();
				String done = "Done in " + (stopTime - startTime)
						+ " msec!";
				System.out.println(done);
				this.setHistory(done);
				this.setHistory("Thumbnailordner -> " + Settings.getInstance().getTempDirectory());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Unbind:
			GUIUpdater.unbind(
					StarterController.this.statusbarLabel.textProperty(),
					this.statusbarTextProperty());
			GUIUpdater.unbind(StarterController.this.counterForLabel, this.countedFiles);
			GUIUpdater.unbind(StarterController.this.textArea.textProperty(),
					this.getHistoryProperty());

			return null;
		}

		public SimpleObjectProperty<WritableImage> getImageProperty() {
			if (this.imageProperty == null) {
				this.imageProperty = new SimpleObjectProperty<WritableImage>();
			}
			return this.imageProperty;
		}

		// private void setImage(WritableImage fxImage) {
		// this.imageProperty.set(fxImage);
		// }

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

		public void setDirectoryData(
				List <Directory> directories) {
			this.directories = directories;
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
