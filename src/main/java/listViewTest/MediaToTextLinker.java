package listViewTest;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import de.xonical.mvplayer.model.VideoFile;


public class MediaToTextLinker {

	// The developer of MediathekView appends an extension txt for his description
	// and do not remove the Expension of a media file
	private String mediaExtension = ".mp4";

	public MediaToTextLinker(List<VideoFile> videoFileList) {
		for (VideoFile videoFile : videoFileList){
			String path = videoFile.getPathWithoutExtension();
			String linkedTextFile = path + mediaExtension + ".txt";

			if (isTextFileExists(linkedTextFile)){
				//System.out.println("TextFile: " + linkedTextFile);
				videoFile.setLinkedFile(linkedTextFile);
			}
		}
	}

	private boolean isTextFileExists(String linkedTextFile) {

		Path path = Paths.get(linkedTextFile);
		return Files.exists(path);
	}
}
