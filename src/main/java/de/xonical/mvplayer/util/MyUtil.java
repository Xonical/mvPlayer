package de.xonical.mvplayer.util;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Files;

import de.xonical.mvplayer.model.VideoFile;

public class MyUtil {

	public static boolean isVideoFile(Path path) {
		//System.out.println(path);
		String fileExtension = Files.getFileExtension(path.toString());
		if (fileExtension.equals("mp4")) {
			return true;
		} else {
			return false;
		}
	}

	public static List<VideoFile> getVideoFiles(Path path) {

		List<VideoFile> videoFiles = new ArrayList<VideoFile>();
		File[] files = path.toFile().listFiles();

		// check, if the directory contains media
		for (File file : files) {
			if (MyUtil.isVideoFile(file.toPath())) {
				VideoFile videoFile = new VideoFile();
				videoFile.setVideoFileName(file.getAbsolutePath());
				videoFiles.add(videoFile);
			}
		}

		return videoFiles;
	}
}
