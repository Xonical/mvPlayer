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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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

	@FXML
	ProgressBar progressBar;

	private File selectedDirectory;

	private MyService myService;

	private List<VideoFile> videoFiles;
	private ObservableList<ObservableList<VideoFile>> videoFilesData;

	private int countedVideoFiles;

	//private Duration videoFilesData;

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
																	// view										// ersetzen

		if (selectedDirectory == null) {
			if (selectedDirectoryLabel.getText().length() > 0) {
				selectedDirectoryLabel.setText("");
				startIndexButton.setDisable(true);
			}
		} else {
			selectedDirectoryLabel.setText(selectedDirectory.getAbsolutePath());
			startIndexButton.setDisable(false);
			this.selectedDirectory = selectedDirectory;
			//videoFilesData = new ObservableList< ObservableList<VideoFile>>();
		}
	}

	@FXML
	private void prepareIndex(ActionEvent event) {


        // Create task:
        TestTask    testTask = new TestTask();

        // Bind:
        GUIUpdater.bind(StarterController.this.statusbarLabel.textProperty(), testTask.curFileProperty());
        //GUIUpdater.bind(JavaFXTest.this.lblErrors.textProperty(), testTask.errorsProperty());
        //GUIUpdater.bind(JavaFXTest.this.lblBytesParsed.textProperty(), testTask.bytesParsedProperty());
        StarterController.this.progressBar.progressProperty().bind(testTask.progressProperty());   // No need to use GUIUpdater here, Task class provides the same functionality for progress.

        // Start task:
        Thread  tmpThread = new Thread(testTask);
        tmpThread.start();





//		myService = new MyService();
//
//
//        myService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent t) {
//               // statusbarLabel.setText("OnSucceeded");
//            	System.out.println("Bart: " + Settings.getInstance().getTempDirectory());
//            }
//        });
//
//        myService.setOnRunning(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent t) {
//                //statusbarLabel.setText("OnRunning");
//            }
//        });
//
//        myService.setOnFailed(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent t) {
//            	//statusbarLabel.setText("OnFailed");
//            }
//        });
//
//        progressBar.progressProperty().bind(myService.progressProperty());
//        //imageView.imageProperty().bind(myService.propProperty());
//        statusbarLabel.textProperty().bind(myService.messageProperty());
//
//		// We want an array of directories, that are above the root
//		File[] subDirectories = selectedDirectory.listFiles((File f) -> {
//			return f.isDirectory();
//		});
//
//
//		List<Directory> directories = new ArrayList<>();
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
//				directories.add(directory);
//			}
//		}
//		if (directories.size() <= 0) {
//			statusbarLabel.setText("No media files found!");
//		} else{
//
//			for (Directory dir : directories) {
//				String strFolder = dir.getDirectoryPath().toString();
//				String status = "Lese Verzeichnis:" + strFolder + " Datei: ";
//
//
//				ObservableList<VideoFile> videoFiles = dir.getVideoFiles();
//				//videoFiles =
//				//ObservableList<VideoFile> videoFiles2 = dir.getVideoFiles();
//				countedVideoFiles += videoFiles.size();
//				//videoFilesData.add(videoFiles);
//
//			}
//			myService.start();
//
//		}




		//WorkerThread myThread = new WorkerThread(selectedDirectory,imageView,statusbarLabel);
		//myThread.start();
		//foo();
	}

	private void foo() {
		//RootDirectory rootDirectory = new RootDirectory(
		//selectedDirectory.toPath());
	}


	  private class TestTask extends Task<Void> {
	        private SimpleStringProperty    curFile     =   new SimpleStringProperty();
	        private SimpleStringProperty    errors      =   new SimpleStringProperty();
	        private SimpleStringProperty    bytesParsed =   new SimpleStringProperty();

	        @Override
	        protected Void call() throws Exception {

	            // Count:
	            try {
	                int maxValue = 1000000;
	                long startTime = System.currentTimeMillis();

	                System.out.println("Starting...");
	                for(int i = 0; i < maxValue; i++) {
	                    this.updateProgress(i, maxValue - 1);

	                    // Simulate some progress variables:
	                    this.curFile.set("File_" + i + ".txt");
	                    if ((i % 1000) == 0) {
	                        //this.errors.set("" + (i / 1000) + " Errors");
	                    }
	                    //this.bytesParsed.set("" + (i / 1024) + " KBytes");
	                }
	                long stopTime = System.currentTimeMillis();
	                System.out.println("Done in " + (stopTime - startTime) + " msec!");
	            } catch(Exception e) {
	                e.printStackTrace();
	            }

	            // Unbind:
	            GUIUpdater.unbind(StarterController.this.statusbarLabel.textProperty(), this.curFileProperty());
	           // GUIUpdater.unbind(JavaFXTest.this.lblErrors.textProperty(), this.errorsProperty());
	            //GUIUpdater.unbind(JavaFXTest.this.lblBytesParsed.textProperty(), this.bytesParsedProperty());
	            return null;
	        }

	        public SimpleStringProperty curFileProperty() {
	            return this.curFile;
	        }

	        public SimpleStringProperty errorsProperty() {
	            return this.errors;
	        }

	        public SimpleStringProperty bytesParsedProperty() {
	            return this.bytesParsed;
	        }

	    }








	private class MyService extends Service<Void> {

		private ObjectProperty prop = new SimpleObjectProperty();

		public ObjectProperty propProperty(){
			return prop;
		}


		public MyService() {
//			for (Directory dir : directories) {
//    			String strFolder = dir.getDirectoryPath().toString();
//    			String status = "Lese Verzeichnis:" + strFolder + " Datei: ";
//
//    			videoFiles = dir.getVideoFiles();
//    		}
			VideoThumbnailer thumbnailer = new VideoThumbnailer();
		}



		@Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    int max = countedVideoFiles;
                    for (int i = 1; i <= max; i++) {
                    	System.out.println("OO: " + i + " von  " +countedVideoFiles);
                        if (isCancelled()) {
                            break;
                        }

                        //updateMessage(String.valueOf(i));


                        updateProgress(i, max);


                        //VideoFile videoFile = videoFiles.get(i);
                        //System.out.println("--> " + videoFile);



            	//		System.out.println("GG: " +videoFiles.size());
            		//	for (VideoFile videoFile : videoFiles) {
//            				System.out.println("Thread: " + i + " " +videoFile.getPath());
//            				//status.concat(videoFile.getPath().toString());
//
//            				// Creates a thumbnail and link it to the videoFile
//            				thumbnailer.create(videoFile);
//
//            				Image image = SwingFXUtils.toFXImage(thumbnailer.getImage(), null);
//
//
//            				imageView.setImage(image);
//
//
//            				statusbarLabel.setText("");
//            				if (videoFile.getLinkedFile() != null) {
//            					// System.out.println(videoFile.getLinkedFile().toString());
//            				}
            	//		}







                        Thread.sleep(150);
                    }
                    return null;
                }
            };
        }
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
