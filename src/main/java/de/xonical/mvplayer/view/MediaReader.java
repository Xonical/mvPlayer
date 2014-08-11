package de.xonical.mvplayer.view;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Calendar;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.DateTimeAtCompleted;

public class MediaReader implements Runnable {

	private MediaPlayer player;
	private MediaView mediaView;
	final int width = 200;
	final int height = 200;
	private File file;

	public MediaReader(MediaPlayer player, MediaView mediaview) {
		this.player = player;
		this.mediaView = mediaview;

	}

	@Override
	public void run() {
		file = new File("i:/test" + Calendar.getInstance().getTimeInMillis()
				+ ".png");
		player.seek(Duration.seconds(1));
		player.pause();
		WritableImage wi = new WritableImage(width, height);
		mediaView.snapshot(new SnapshotParameters(), wi);
		// Color c = wi.getPixelReader().getColor(100, 100);
		// System.out.println(c);
		// video.snapshot(params, image);
		//

		// http://java-buddy.blogspot.de/2012/12/save-writableimage-to-file.html
		player.stop();

		RenderedImage renderedImage = SwingFXUtils.fromFXImage(wi, null);
		try {
			ImageIO.write(renderedImage, "png", file);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fooo");
			e.printStackTrace();
		}
	}

	public File getFile() {
		return this.file;
	}



}
