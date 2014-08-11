package de.xonical.mvplayer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import listViewTest.VideoFile;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;

// http://www.javacodegeeks.com/2011/02/xuggler-tutorial-frames-capture-video.html
// http://blog.knoldus.com/2012/11/26/extract-a-frame-image-from-video-in-scala/

public class Temp {

//	private static int shootCounter = 0;
//	private int sizeOfVideoList;
//	private int videoListCounter;
//	private static boolean isSnapshotWasTaken;
//	private static Path tempDirectory;
//
//	public static void main(String[] args) {
//		// List<Path> paths = new ArrayList<>();
//		// Path path1 = Paths.get("d:/test1.mp4");
//		// Path path2 = Paths.get("d:/test2.mp4");
//		// Path path3 = Paths.get("d:/test3.mp4");
//		// // Path path4 = Paths.get("d:/test4");
//		// // Path path5 = Paths.get("d:/test5");
//		//
//		// paths.add(path1);
//		// paths.add(path2);
//		// paths.add(path3);
//		// // paths.add(path4);
//		// // paths.add(path5);
//		//
//		// VideoThumbnailer video1 = new VideoThumbnailer(
//		// Paths.get("i:/snapshots"), paths);
//		// // video1.extractFrameFromVideo();
//	}
//
//	public VideoThumbnailer(Path destinationFolder, List<Path> videos) {
//		sizeOfVideoList = videos.size();
//		for (Path video : videos) {
//			extractFrameFromVideo(destinationFolder, video);
//			isSnapshotWasTaken = false;
//		}
//	}
//
//	public VideoThumbnailer() {
//
//	}
//
//	private void extractFrameFromVideo(Path destinationFolder, Path video) {
//		IMediaReader iMediaReader = ToolFactory.makeReader(video.toFile()
//				.toString());
//
//		iMediaReader
//				.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
//
//		iMediaReader
//				.addListener(new ImageSnapListener(destinationFolder, video));
//
//		while (!isSnapshotWasTaken) {
//			// If 1, then a fram from the video was extracted and saved,
//			// so the work is done for one file
//			iMediaReader.readPacket();
//			System.out.println("Count 1: " + shootCounter);
//
//			// A new picture for a new video and we should increment the counter
//			// and check the number of shooten files
//
//			// if (shootCounter < sizeOfVideoList) {
//			// shootCounter = 0;
//			// }else{
//			// // All videos are shooten
//			// }
//		}
//	}
//
//	private static class ImageSnapListener extends MediaListenerAdapter {
//
//		private Path video;
//		private Path destinationFolder;
//		private String preDumpImageToFile;
//		private VideoFile videoFile;
//
//		public ImageSnapListener(Path destinationFolder, Path video) {
//			this.destinationFolder = destinationFolder;
//			this.video = video;
//		}
//
//		public ImageSnapListener(Path destinationFolder, VideoFile videoFile) {
//			this.destinationFolder = destinationFolder;
//			this.videoFile = videoFile;
//		}
//
//		@Override
//		public void onVideoPicture(IVideoPictureEvent event) {
//			System.out.println("Count 2: " + shootCounter + " "
//					+ videoFile.toString());
//
//			// if (preDumpImageToFile.equals(destinationFolder + "/"
//			// + video.getFileName() + ".png")) {
//			// // Workaround --> do not save. Not save the image more than one
//			// time
//			// // Reason: The listener
//			// }else{
//			preDumpImageToFile = dumpImageToFile(event.getImage());
//			isSnapshotWasTaken = true;
//			//
//
//			shootCounter++;
//			// super.onVideoPicture(event);
//		}
//
//		private String dumpImageToFile(BufferedImage image) {
//			try {
//				String outputFilename = destinationFolder + "/"
//						+ video.getFileName() + ".png";
//				ImageIO.write(image, "png", new File(outputFilename));
//
//				return outputFilename;
//			} catch (IOException e) {
//				e.printStackTrace();
//				return null;
//			}
//		}
//	}
//
//	public void create(VideoFile videoFile) {
//		Path tempDirectory = Settings.getInstance().getTempDirectory();
//
//		extractFrameFromVideo(tempDirectory, videoFile);
//	}
//
//	private void extractFrameFromVideo(Path tempDirectory, VideoFile videoFile) {
//		IMediaReader iMediaReader = ToolFactory.makeReader(videoFile.getPath()
//				.toFile().toString());
//
//		iMediaReader
//				.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
//
//		iMediaReader
//				.addListener(new ImageSnapListener(tempDirectory, videoFile));
//
//		while (!isSnapshotWasTaken) {
//			// If 1, then a fram from the video was extracted and saved,
//			// so the work is done for one file
//			iMediaReader.readPacket();
//			System.out.println("Count 1: " + shootCounter);
//		}
//
//	}
}
