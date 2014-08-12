package de.xonical.mvplayer.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.xonical.mvplayer.model.VideoFile;
import javafx.collections.ObservableList;

public class Settings {
	private static Settings instance = null;
	private Path tempDirectory;
	private ObservableList<ObservableList<VideoFile>> videoFilesData;

	/**
	 * Default-Konstruktor, der nicht außerhalb dieser Klasse aufgerufen werden
	 * kann
	 */
	private Settings() {
	}

	/**
	 * Statische Methode, liefert die einzige Instanz dieser Klasse zurück
	 */
	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}

	// http://www.java2s.com/Tutorials/Java/java.nio.file/Files/Java_Files_createTempDirectory_String_prefix_FileAttribute_lt_gt_attrs_.htm
	public Path getTempDirectory() {
		if (tempDirectory == null) {
			String tmp_dir_prefix = "MediathekView-Player_";
			// get the default temporary folders path
			String default_tmp = System.getProperty("java.io.tmpdir");
			System.out.println(default_tmp);

			Path tmp_2 = null;
			try {
				tmp_2 = Files.createTempDirectory(tmp_dir_prefix);
				// System.out.println("TMP: " + tmp_2.toString());
				// tmp_2 = Paths.get(default_tmp + "/" + tmp_dir_prefix);

			} catch (IOException e) {
				System.err.println(e);
			}
			System.out.println("a1: " + tmp_2);
			tempDirectory = tmp_2;
			return tmp_2;
		} else {
			System.out.println("a2: " + tempDirectory);
			return tempDirectory;
		}
	}

	public ObservableList<ObservableList<VideoFile>> getAllVideosInDirectory() {
		return this.videoFilesData;
	}

	public void setAllVideosInDirectory(ObservableList<ObservableList<VideoFile>> videoFileData) {
		this.videoFilesData = videoFileData;
	}
}