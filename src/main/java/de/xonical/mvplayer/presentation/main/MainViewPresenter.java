package de.xonical.mvplayer.presentation.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import de.xonical.mvplayer.model.Directory;
import de.xonical.mvplayer.model.RegistrationService;
import de.xonical.mvplayer.model.SubDirectory;
import de.xonical.mvplayer.model.VideoFile;
import de.xonical.mvplayer.presentation.directories.DirectoryView;
import de.xonical.mvplayer.presentation.directories.DirectoryPresenter;
import de.xonical.mvplayer.util.Settings;

//http://stackoverflow.com/questions/11180884/how-to-populate-a-tableview-that-is-defined-in-an-fxml-file-that-is-designed-in

public class MainViewPresenter implements Initializable {

	@FXML
	TableView<Directory>					tableView;
	@FXML
	private TableColumn<Directory, String>	directoryColumn;
	@FXML
	private TableColumn<Directory, Number>	countedFilesColumn;

	@FXML
	ImageView								imageView;

	@Inject
	RegistrationService						service;

	@FXML
	AnchorPane								anchorPane	= new AnchorPane();

	@FXML
	FlowPane								flowPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<Directory> allDirectories = service.allDirectories();
		ObservableList<Directory> oListDirectories = FXCollections
				.observableList(allDirectories);

		directoryColumn
				.setCellValueFactory(new PropertyValueFactory<Directory, String>(
						"directoryName"));
		countedFilesColumn
				.setCellValueFactory(new PropertyValueFactory<Directory, Number>(
						"countedVideoFiles"));

		tableView.getItems().setAll(oListDirectories);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		// final Label selected = new Label();
		tableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					System.out.println(newValue.getDirectoryName());

					// First we remove the child nodes of flowpane, so that it's
					// clear for new images
					flowPane.getChildren().clear();
						readImageData(newValue);

					});
	}

	private void readImageData(Directory newValue) {
		List<VideoFile> videoFiles = newValue.getVideoFiles();

		for (VideoFile video : videoFiles) {
			String thumbnailFile = video.getThumbnailFile();

			System.out.println(thumbnailFile);

			ImageboxView buildImagebox = null;
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(new File(thumbnailFile));
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				buildImagebox = buildImagebox(image, video.getVideoFileName());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Node node = buildImagebox.getViewWithoutRootContainer();
			// VBox dd = new VBox();
			flowPane.getChildren().add(node);
			// this.anchorPane.
		}
	}

	private ImageboxView buildImagebox(Image image, String string) {
		ImageboxView box = new ImageboxView();
		ImageboxPresenter presenter = (ImageboxPresenter) box.getPresenter();
		presenter.setTextImageLabel(string);
		presenter.setImage(image);
		return box;
	}

	private List<SubDirectory> createListOfSubDirectories(
			ObservableList<ObservableList<VideoFile>> allVideosInRootDirectory) {

		final List<SubDirectory> allSubDirectories = new ArrayList();

		// Every folder in root
		for (final ObservableList<VideoFile> videoFiles : allVideosInRootDirectory) {

			SubDirectory subDirectory = null;

			// Every video file of each subfolder
			for (final VideoFile videoFile : videoFiles) {
				final Path parent = Paths.get(new File(videoFile
						.getVideoFileName()).toURI());
				subDirectory = new SubDirectory(parent);
				// we can break the loop, because we have the parent directory
				break;
			}
			final ObservableList<VideoFile> observableList = FXCollections
					.observableList(videoFiles);
			subDirectory.setVideoFilesData(observableList);
			allSubDirectories.add(subDirectory);
		}
		return allSubDirectories;
	}
}

// private TableView<TableRow> myTable;
// @FXML
// private TableColumn<TableRow, Object> myTableColumnOne;
// @FXML
// private TableColumn<TableRow, Object> myTableColumnTwo;
//
// public void populateMyTable()
// {
// myTableColumnOne.setCellValueFactory(new PropertyValueFactory<TableRow,
// Object>("one"));
// myTableColumnTwo.setCellValueFactory(new PropertyValueFactory<TableRow,
// Object>("two"));
//
// ObservableList data = FXCollections.observableArrayList();
// for(Map.Entry<String, Integer> entry: myHashMap.entrySet())
// {
// data.add(new TableRow(entry.getKey(), entry.getValue()));
// }
// myTable.getItems().setAll(data);
// }
