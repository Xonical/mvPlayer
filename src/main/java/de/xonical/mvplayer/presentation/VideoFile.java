package de.xonical.mvplayer.presentation;

import java.nio.file.Path;
import java.nio.file.Paths;


public class VideoFile extends MediaFile  {

	private Path pathVideoFile;
	private Path pathTextFile;

	public VideoFile(Path mp4File) {
		super(mp4File);
		//System.out.println("new VideoFile" + mp4File);
	}

	public static void main(String[] args) {
		VideoFile videoFile = new VideoFile(Paths.get("d:/test/test.mp4"));
		//System.out.println(videoFile.getPathWithoutExtension());
	}

	public void setLinkedFile(String linkedTextFile) {
		pathTextFile = Paths.get(linkedTextFile);
	}

	public Path getLinkedFile(){
		return pathTextFile;
	}
}
