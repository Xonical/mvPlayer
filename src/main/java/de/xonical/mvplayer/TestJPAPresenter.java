package de.xonical.mvplayer;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import de.xonical.mvplayer.model.Directory;
import de.xonical.mvplayer.model.RegistrationService;
import de.xonical.mvplayer.model.VideoFile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class TestJPAPresenter implements Initializable {

	@Inject
	RegistrationService service;


	@FXML
	Button buttonSave;

	@FXML
	Button buttonRead;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("INIT");
		//new TestJPA();


		Platform.runLater(new Runnable() {
		    public void run() {



				//service.save();
				//service.close();
				//List<VideoFile> allVideoFiles = service.allVideoFiles();

				//System.out.println(allVideoFiles.size());


		    }
		});
	}

	@FXML
	private void handleButtonSave(ActionEvent e){
		System.out.println("Click");
		Directory dir = new Directory();
		dir.setDirectoryName("i:/MediathekView/Abendschau");
		dir.setCountedVideoFiles(42);
		service.save(dir);
		//service.save();
		//service.close();

		String str = "i:/MediathekView/Abendschau/Abendschau-Start-Up_Schmiede__Rocket_Internet_-abendschau_20140620_rocket_m_16_9_512x288.mp4";
		VideoFile v = new VideoFile(new File(str).toPath());
//		v.setLinkedFile("i:/MediathekView/Abendschau/Abendschau-Start-Up_Schmiede__Rocket_Internet_-abendschau_20140620_rocket_m_16_9_512x288.mp4.txt");
//		String t = "C:/Users/Alfa/AppData/Local/Temp/MediathekView-Player_8379203186689531745";
//		v.setThumbnail(new File(t).toPath());
//
//
		service.save(v);
	}

	@FXML
	private void handleButtonRead(ActionEvent e){
		List<Directory> allDirectories = service.allDirectories();
		System.out.println("Anz. Dir: " + allDirectories.size());
		List<VideoFile> allVideoFiles = service.allVideoFiles();
		System.out.println("Anz. Videos: " + allVideoFiles.size());
	}
}

