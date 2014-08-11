package de.xonical.mvplayer.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Settings {
    private static Settings instance = null;
	private Path tempDirectory;

    /**
     * Default-Konstruktor, der nicht außerhalb dieser Klasse
     * aufgerufen werden kann
     */
    private Settings() {}

    /**
     * Statische Methode, liefert die einzige Instanz dieser
     * Klasse zurück
     */
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }


    // http://www.java2s.com/Tutorials/Java/java.nio.file/Files/Java_Files_createTempDirectory_String_prefix_FileAttribute_lt_gt_attrs_.htm
    public Path getTempDirectory(){
    	if(tempDirectory ==null){
    		String tmp_dir_prefix = "MediathekView-Player_";
    		// get the default temporary folders path
    		String default_tmp = System.getProperty("java.io.tmpdir");
    		System.out.println(default_tmp);

    		Path tmp_2 = null;
    		try {
    			tmp_2 = Files.createTempDirectory(tmp_dir_prefix);
    			System.out.println("TMP: " + tmp_2.toString());

    		} catch (IOException e) {
    			System.err.println(e);
    		}
    		return tmp_2;
    	}else{
    		return tempDirectory;
    	}
    }
}