package de.xonical.mvplayer.presentation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.pmw.tinylog.Logger;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;

import de.xonical.mvplayer.model.VideoFile;
import de.xonical.mvplayer.util.Settings;

//http://www.javacodegeeks.com/2011/02/xuggler-tutorial-frames-capture-video.html
//http://blog.knoldus.com/2012/11/26/extract-a-frame-image-from-video-in-scala/

public class VideoThumbnailer {

	private static int shootCounter = 0;
	private int sizeOfVideoList;
	private int videoListCounter;
	private ImageSnapListener2 imageSnapListener2;
	private static boolean isSnapshotWasTaken;
	private static Path tempDirectory;

	public static void main(String[] args) {
		// List<Path> paths = new ArrayList<>();
		// Path path1 = Paths.get("d:/test1.mp4");
		// Path path2 = Paths.get("d:/test2.mp4");
		// Path path3 = Paths.get("d:/test3.mp4");
		// // Path path4 = Paths.get("d:/test4");
		// // Path path5 = Paths.get("d:/test5");
		//
		// paths.add(path1);
		// paths.add(path2);
		// paths.add(path3);
		// // paths.add(path4);
		// // paths.add(path5);
		//
		// VideoThumbnailer video1 = new VideoThumbnailer(
		// Paths.get("i:/snapshots"), paths);
		// // video1.extractFrameFromVideo();
	}

	public VideoThumbnailer() {

	}

	private void extractFrameFromVideo(Path tempDirectory, VideoFile videoFile) {
		IMediaReader iMediaReader = ToolFactory.makeReader(videoFile.getPath()
				.toString());

		iMediaReader
				.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

		imageSnapListener2 = new ImageSnapListener2(tempDirectory, videoFile);
		iMediaReader.addListener(imageSnapListener2);

		while (!imageSnapListener2.isSnapshotWasTaken()) {
			// If 1, then a fram from the video was extracted and saved,
			// so the work is done for one file
			iMediaReader.readPacket();
			// System.out.println("Count 1: " + shootCounter);
		}
	}

	// public static class ImageSnapListener extends MediaListenerAdapter {
	//
	// private Path destinationFolder;
	// private VideoFile videoFile;
	// public BufferedImage image;
	//
	// public ImageSnapListener(Path tempDirectory, VideoFile videoFile) {
	// this.videoFile = videoFile;
	// this.destinationFolder = tempDirectory;
	// }
	//
	// @Override
	// public void onVideoPicture(IVideoPictureEvent event) {
	// // System.out.println("Count 2: " + shootCounter + " "
	// // + videoFile.toString());
	//
	// dumpImageToFile(event.getImage());
	// isSnapshotWasTaken = true;
	//
	// //shootCounter++;
	// }
	//
	// private String dumpImageToFile(BufferedImage image) {
	// this.image = image;
	// String outputFilename ="";
	// try {
	//
	// outputFilename = destinationFolder + "/"
	// + videoFile.getNameWithoutExtension() + ".png";
	// Logger.info("Try to write file: " + outputFilename);
	// ImageIO.write(image, "png", new File(outputFilename));
	// Logger.info("File: " + outputFilename + " written");
	// videoFile.setThumbnail(Paths.get(outputFilename));
	// return outputFilename;
	// } catch (IOException e) {
	// Logger.info("IOException @ File: " + outputFilename);
	// e.printStackTrace();
	// return null;
	// }
	// }
	// }

	public void create(VideoFile videoFile) {
		Path tempDirectory = Settings.getInstance().getTempDirectory();
		extractFrameFromVideo(tempDirectory, videoFile);
	}

	public BufferedImage getImage() {
		return imageSnapListener2.getImage();
	}
}
