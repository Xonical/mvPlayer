package com.airhacks.followme.business.flightcontrol.boundary;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.xonical.mvplayer.RootDirectory;

public class TestRootDirectory {

	private static Path rootPath;
	private static File firstSubDirectory;
	private RootDirectory rootDirectory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	 //per class attributes initialized here
		//rootPath = Paths.get("g:/MediathekView");
		//root = new RootDirectory(rootPath);
		//firstSubDirectory = new File("g:\\MediathekView\\Abendschau");
	}




	@Test
	public void testNotExistingPath() {
		rootPath = Paths.get("z:/foo/bar/foobar/dummyPath");
		rootDirectory = new RootDirectory(rootPath);
		Assert.assertTrue(rootDirectory.isValidDirectory());
	}

//	@Test
//	public void testFileContainingOfASubDirectory() {
//		List<File> files = root.getListOfFilesFromSubdirectory(firstSubDirectory);
//		Assert.assertEquals(4, files.size());
//	}

//	@Test
//	public void testIsValidPath(){
//		rootPath = Paths.get("i:/MediathekView");
//		rootDirectory = new RootDirectory(rootPath);
//		Assert.assertFalse(rootDirectory.isValidDirectory());
//	}

}
