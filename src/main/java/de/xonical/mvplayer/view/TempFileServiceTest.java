package de.xonical.mvplayer.view;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class TempFileServiceTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreatedTempDirecory() {


        String tmp_dir_prefix = "MediathekView-Player_";

        //get the default temporary folders path
        String default_tmp = System.getProperty("java.io.tmpdir");
        System.out.println(default_tmp);

        Path tmp_2 = null;
		try {
            //set a prefix
            tmp_2 = Files.createTempDirectory(tmp_dir_prefix);
            System.out.println("TMP: " + tmp_2.toString());

        } catch (IOException e) {
            System.err.println(e);
        }

		Assert.assertEquals("",tmp_2.toString());
	}
}
