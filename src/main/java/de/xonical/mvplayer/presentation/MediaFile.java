package de.xonical.mvplayer.presentation;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.common.io.Files;

public abstract class MediaFile {

	private Path mediaFile;

	public MediaFile(Path mediaFile) {
		this.mediaFile = mediaFile;
	}

	public String getExtension() {
		return Files.getFileExtension(mediaFile.toString());
	}

	public String getNameWithoutExtension() {
		return Files.getNameWithoutExtension(mediaFile.toString());
	}

	public Path getPath() {
		return mediaFile;
	}

	public String getPathWithoutExtension() {
		Path parentDirectory = mediaFile.getParent();
		String returnValue = parentDirectory.toString()
				+  FileSystems.getDefault().getSeparator()
				+  Files.getNameWithoutExtension(mediaFile.toString());
		return returnValue;
	}
}
