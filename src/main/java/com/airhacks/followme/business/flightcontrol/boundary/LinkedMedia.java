package com.airhacks.followme.business.flightcontrol.boundary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import javafx.scene.media.Media;

/*
 * http://www.torsten-horn.de/techdocs/java-lambdas.htm
 * http://www.tinylog.org/de
 */




public class LinkedMedia {

	private List<File> entries;
	private Object subDirectory;
	private List<Path> filesWithExtensionMP4;

	public LinkedMedia() {

		// TODO Auto-generated constructor stub
	}

	public LinkedMedia(File subDirectory, List<File> entries) {
		this.subDirectory = subDirectory;
		this.entries = entries;
		filterFilesWithExtensionMP4(entries);
		createMapMP4andTXT();
	}

	private boolean createMapMP4andTXT() {
//		filesWithExtensionMP4.forEach((Path p) -> {
//
//		});
		return false;


		//HashMap<Path, List<Media> > gds = manager.getLinkedMedia();

	}

	private void filterFilesWithExtensionMP4(List<File> entries) {
		Stream<Path> stream = null;
		try {
			stream = Files.list(Paths.get( entries.get(0).getParentFile().toString()) )
			        .filter( path -> path.toString().endsWith( ".mp4" ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		filesWithExtensionMP4 = stream.collect(Collectors.toList());
	}
}
