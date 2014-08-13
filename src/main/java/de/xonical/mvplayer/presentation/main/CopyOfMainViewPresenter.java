//package de.xonical.mvplayer.presentation.main;
//
//import java.net.URL;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.layout.AnchorPane;
//
//import javax.inject.Inject;
//
//import de.xonical.mvplayer.model.Directory;
//import de.xonical.mvplayer.model.RegistrationService;
//import de.xonical.mvplayer.model.SubDirectory;
//import de.xonical.mvplayer.model.VideoFile;
//import de.xonical.mvplayer.presentation.directories.DirectoryPresenter;
//import de.xonical.mvplayer.presentation.directories.DirectoryView;
//
//public class CopyOfMainViewPresenter implements Initializable {
//
//	@FXML
//	private TableView<SubDirectory>				directoryTable;
//	@FXML
//	private TableColumn<SubDirectory, String>	directoryNameColumn;
//	@FXML
//	private TableColumn<SubDirectory, Number>	countedVideoFilesColumn;
//
//	@FXML
//	AnchorPane									directoriesPane;
//	private DirectoryView						directoryView;
//
//	@Inject
//	RegistrationService							service;
//
//	private ObjectProperty<Directory>			selectedDirectory;
//	private ObjectProperty<Directory>			newDirectory;
//	private DirectoryPresenter					directoryPresenter;
//	private ObjectProperty<Directory>			Directorys;
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//
//		this.selectedDirectory = new SimpleObjectProperty<>();
//		//this.newDirectory = new SimpleObjectProperty<>();
////		newDirectoryProperty().addListener(new ChangeListener<Directory>() {
////			@Override
////			public void changed(ObservableValue<? extends Directory> ov,
////					Directory old, Directory newDirectory) {
////				System.out.println("D1: " + newDirectory.getFirstName());
////				if (newDirectory != null) {
////					//newDirectoryProperty().set(newDirectory);
////				}
////			}
////		});
//
////		final ChangeListener<Directory> storingListener = new ChangeListener<Directory>() {
////			@Override
////			public void changed(ObservableValue<? extends Directory> ov,
////					Directory old, Directory newDirectory) {
////				System.out.println("e1: ");
////				if (newDirectory != null) {
////					service.save(newDirectory);
////					//loadFromStore();
////				}
////			}
////		};
//
//		this.selectedDirectory = new SimpleObjectProperty<>();
//
//		//this.Directorys = new SimpleObjectProperty<>();
//		//this.newDirectory.addListener(storingListener);
//
//
//		// // Reason why number, not integer:
//		// //
//		// http://stackoverflow.com/questions/24889638/javafx-properties-in-tableview
//		// countedVideoFilesColumn.setCellValueFactory(cellData -> cellData
//		// .getValue().countedFilesProperty());
//		 //
//
//
//
//		this.directoryView = new DirectoryView();
//		directoryPresenter = (DirectoryPresenter) this.directoryView
//				.getPresenter();
//
//		this.registerDirectoryListeners();
//		this.directoriesPane.getChildren().add(this.directoryView.getView());
//
//		this.selectedDirectory.addListener(new ChangeListener<Directory>() {
//			@Override
//			public void changed(ObservableValue<? extends Directory> observable,
//					Directory oldValue, Directory newValue) {
//				if (newValue != null) {
//					System.out.println("C1: " + newValue);
//					System.out.println(newValue.videoFileNameProperty().get());
//				} else {
//					// resetUI();
//					System.out.println("else");
//				}
//			}
//		});
//
//		directoryPresenter.clearAll();
//		loadFromStore();
//
//		for (int i = 0; i < 2; i++) {
//			System.out.println("Foo: " + i);
//			save(i);
//		}
//
//	}
//
//	void loadFromStore() {
//		List<Directory> all = service.all();
//		for (Directory Directory : all) {
//			directoryPresenter.add(Directory);
//		}
//	}
//
//	void registerDirectoryListeners() {
//		ChangeListener<Directory> deletionListener = new ChangeListener<Directory>() {
//			@Override
//			public void changed(ObservableValue<? extends Directory> observable,
//					Directory oldValue, Directory newValue) {
//				if (newValue != null) {
//					System.out.println("a1");
//					service.remove(newValue);
//					loadFromStore();
//				}
//			}
//		};
//
//		ChangeListener<Directory> selectionListener = new ChangeListener<Directory>() {
//			@Override
//			public void changed(ObservableValue<? extends Directory> observable,
//					Directory oldValue, Directory newValue) {
//				System.out.println("b1");
//				selectedDirectory.set(newValue);
//			}
//		};
////		this.directoryPresenter.deletedDirectoryProperty().addListener(
////				deletionListener);
//		this.directoryPresenter.selectedDirectoryProperty().addListener(
//				selectionListener);
//
//	}
//
//	private List<SubDirectory> createListOfSubDirectories(
//			ObservableList<ObservableList<VideoFile>> allVideosInRootDirectory) {
//
//		final List<SubDirectory> allSubDirectories = new ArrayList();
//
//		// Every folder in root
//		for (final ObservableList<VideoFile> videoFiles : allVideosInRootDirectory) {
//
//			SubDirectory subDirectory = null;
//
//			// Every video file of each subfolder
//			for (final VideoFile videoFile : videoFiles) {
//				final Path parent = videoFile.getPath().getParent();
//				subDirectory = new SubDirectory(parent);
//				// we can break the loop, because we have the parent directory
//				break;
//			}
//			final ObservableList<VideoFile> observableList = FXCollections
//					.observableList(videoFiles);
//			subDirectory.setVideoFilesData(observableList);
//			allSubDirectories.add(subDirectory);
//		}
//		return allSubDirectories;
//	}
//
//	private void save(int i) {
//		Directory Directory= new Directory();
//		System.out.println("BarB: " + i);
//		Directory.setVideoFileName("A: " + i);
//		Directory.setCountedVideoFiles( i);
//		this.newDirectory.set(Directory);
//	}
//
//	public ObjectProperty<Directory> newDirectoryProperty() {
//		return newDirectory;
//	}
//}
