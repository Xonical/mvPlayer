package de.xonical.mvplayer;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.pmw.tinylog.Logger;
//import org.apache.commons.lang.StringUtils;



import de.xonical.mvplayer.view.Directory;

public class RootDirectory {

	private File directory;
	private boolean isValidDirectory;
	private boolean isValidPath;
	private boolean isPathADirectory;
	private boolean isNotEmptyDirectory;

	public RootDirectory(Path rootPath) {

		checkIsValidPath(rootPath);
		directory = rootPath.toFile();
		// All the requirements are given, therefore it's a valid directory
		if (!isValidPath && !isPathADirectory && !isNotEmptyDirectory) {
			this.isValidDirectory = true;
		} else {
			this.isValidDirectory = false;
		}
	}

	/**
	 * First check if it exists, then if it's a directory and finally if it's
	 * not empty. The order and is important to avoid an Exception
	 *
	 * @param rootPath
	 */
	private void checkIsValidPath(Path rootPath) {

		if (Files.exists(rootPath)) {
			isValidPath = true;
		} else {
			isValidPath = false;
			Logger.warn("Path not exists");
			return;
		}

		if (rootPath.toFile().isDirectory()) {
			isPathADirectory = true;
		} else {
			isPathADirectory = false;
			Logger.warn("Path is not a directory");
			return;
		}

		if (rootPath.toFile().listFiles().length > 0) {
			isNotEmptyDirectory = true;
		} else {
			isNotEmptyDirectory = false;
			Logger.warn("Directory is empty");
			return;
		}
	}

	public boolean isValidDirectory() {
		Logger.debug("Root Directory is valid: " + isValidDirectory);
		return this.isValidDirectory;
	}

	/**
	 * @return
	 */
	public List<File> getAllSubDirectories() {
		File[] listSubDirectories = directory.listFiles((File f) -> {

				return f.isDirectory();

		});


		return Arrays.asList(listSubDirectories);
	}

	private List<File> extractSubDirectories() {

		// File[] listSubDirectories = directory.listFiles((File f) -> {
		// return f.isFile();
		// });
		//
		// return Arrays.asList(listSubDirectories);

		// WWW
		// FilenameFilter filter = (f, s) -> f.isDirectory();
		// File[] listSubDirectories = directory.listFiles(filter);
		// return Arrays.asList(listSubDirectories);

		return null;

	}

	/**
	 * @param firstSubDirectory
	 * @return
	 */
	public List<File> getListOfFilesFromSubdirectory(File firstSubDirectory) {

		File[] listFilesFromDirectory = firstSubDirectory
				.listFiles((File f) -> {
					return f.isFile();
				});

		return Arrays.asList(listFilesFromDirectory);
	}

	public static void main(String[] args) {
		Path root = Paths.get("z:/foo/bar/foobar/dummyPath");
		Path root2 = Paths.get("g:/MediathekView");
		RootDirectory rootDirectory = new RootDirectory(root2);
		System.out.println(rootDirectory.isValidPath);
	}
}
