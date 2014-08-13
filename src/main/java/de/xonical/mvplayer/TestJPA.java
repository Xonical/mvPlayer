package de.xonical.mvplayer;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import javafx.concurrent.Service;

import javax.inject.Inject;

import de.xonical.mvplayer.model.Directory;
import de.xonical.mvplayer.model.RegistrationService;
import de.xonical.mvplayer.model.VideoFile;

import com.airhacks.afterburner.injection.Injector;

public class TestJPA {

	@Inject
	RegistrationService service;

	public static void main(String[] args) {
		new TestJPA();
	}


	public TestJPA() {
//		String str = "i:/MediathekView/Abendschau/Abendschau-Start-Up_Schmiede__Rocket_Internet_-abendschau_20140620_rocket_m_16_9_512x288.mp4";
//		VideoFile v = new VideoFile(new File(str).toPath());
//		v.setLinkedFile("i:/MediathekView/Abendschau/Abendschau-Start-Up_Schmiede__Rocket_Internet_-abendschau_20140620_rocket_m_16_9_512x288.mp4.txt");
//		String t = "C:/Users/Alfa/AppData/Local/Temp/MediathekView-Player_8379203186689531745";
//		v.setThumbnail(new File(t).toPath());
//
//
//		service.save(v);


		Directory dir = new Directory();
		dir.setDirectoryName("i:/MediathekView/Abendschau");
		dir.setCountedVideoFiles(42);
		service.save(dir);

		service.save();
		service.close();
		//List<VideoFile> allVideoFiles = service.allVideoFiles();
		List<Directory> allDirectories = service.allDirectories();
		//System.out.println(allVideoFiles.size());
		System.out.println(allDirectories.size());
	}
}