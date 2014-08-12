package de.xonical.mvplayer.presentation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.pmw.tinylog.Logger;

import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.event.IVideoPictureEvent;

import de.xonical.mvplayer.model.VideoFile;

public class ImageSnapListener2 extends MediaListenerAdapter {

	private Path destinationFolder;
	private VideoFile videoFile;
	public BufferedImage image;
	private boolean isSnapshotWasTaken;

	public ImageSnapListener2(Path tempDirectory, VideoFile videoFile) {
		this.videoFile = videoFile;
		this.destinationFolder = tempDirectory;
	}

	@Override
	public void onVideoPicture(IVideoPictureEvent event) {
//		System.out.println("Count 2: " + shootCounter + " "
//				+ videoFile.toString());

		dumpImageToFile(event.getImage());
		isSnapshotWasTaken = true;
	}

	public boolean isSnapshotWasTaken(){
		return isSnapshotWasTaken;
	}

	private String dumpImageToFile(BufferedImage image) {
		this.image = image;
		String outputFilename ="";
		try {

		outputFilename = destinationFolder + "/"
					+ videoFile.getNameWithoutExtension() + ".png";
			Logger.info("Try to write file: " + outputFilename);
			ImageIO.write(image, "png", new File(outputFilename));
			Logger.info("File: " + outputFilename + " written");
			videoFile.setThumbnail(Paths.get(outputFilename));
			return outputFilename;
		} catch (IOException e) {
			Logger.info("IOException @ File: " + outputFilename);
			e.printStackTrace();
			return null;
		}
	}

	public BufferedImage getImage(){
		if(this.image == null){
			image = new BufferedImage(0, 0, 0);
			return image;
		}else{
			return this.image;
		}
	}

}
