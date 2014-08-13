package listViewTest;

import java.awt.MediaTracker;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import de.xonical.mvplayer.model.VideoFile;
import de.xonical.mvplayer.presentation.Directory;
import de.xonical.mvplayer.presentation.start.MediaToTextLinker;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SubDirectory {
	private StringProperty subDirectory;
	private Path path;
	private List<VideoFile> videoFileList;


	public SubDirectory(Directory name) {
		//setSubDirectory(name.toString());

		// map all Files with the extension mp4 to a List of Path
		doIndexMoviesFromParentDirectory(name);

		// and now we can link the media Files to a textfile
		if(videoFileList != null && !videoFileList.isEmpty()){
			MediaToTextLinker linker = new MediaToTextLinker(videoFileList);
		}
		// Now the video files are linked, if there is a reference to a text file.
	}

	public List<VideoFile> getVideoFileList() {
		return videoFileList;
	}

	//	private void linkTextFilesToMedia() {
//
//		for (Path mp4File : filesWithExtensionMP4) {
//			VideoFile videoFile = new VideoFile(mp4File);
//			String extension = videoFile.getExtension();
//
//			// System.out.println(videoFile.getNameWithoutExtension());
//			// System.out.println(extension);
//		}
//
//	}

//	public final void setSubDirectory(String value) {
//		subDirectoryProperty().set(value);
//	}

	//
//	public final String getSubDirectory() {
//		return subDirectoryProperty().get();
//	}

	//
//	public StringProperty subDirectoryProperty() {
//		if (subDirectory == null) {
//			subDirectory = new SimpleStringProperty();
//		}
//		return subDirectory;
//	}

	private void doIndexMoviesFromParentDirectory(Directory newValue) {
		String directoryName = newValue.getDirectoryName();
		Path path = Paths.get(directoryName);
		this.path = path;
		//filterFilesWithExtensionMP4(newValue);
	}

	// public List<File> getListOfFilesFromSubdirectory(File firstSubDirectory)
	// {
	//
	// File[] listFilesFromDirectory = firstSubDirectory
	// .listFiles((File f) -> {
	// return f.isFile();
	// });
	//
	// return Arrays.asList(listFilesFromDirectory);
	// }

//	private void filterFilesWithExtensionMP4(Directory newValue) {
//		Stream<Path> stream = null;
//		try {
//			stream = Files.list(Paths.get(newValue.getDirectoryName())).filter(
//					path -> path.toString().endsWith(".mp4"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		stream.forEach(p -> {
//			if (videoFileList == null) {
//				videoFileList = new ArrayList<VideoFile>();
//			}
//			VideoFile vFile = new VideoFile();
//			vFile.setVideoFileName(p.)
//			videoFileList.add(new VideoFile(p));
//		});
//	}

	public static void main(String[] args) {
		//SubDirectory subDirectory2 = new SubDirectory(new Directory("d:/test", 33));

	}

}