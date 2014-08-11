package com.airhacks.followme.business.flightcontrol.boundary;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinkedMediaTest {

	private static File firstSubDirectory;
	private static LinkedMedia LinkedMedia;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		firstSubDirectory = new File("i:\\MediathekView\\Abendschau");
		List <File> entries = new ArrayList();
		entries.add(new File("i:\\MediathekView\\Abendschau\\Abendschau-Start-Up_Schmiede__Rocket_Internet_-abendschau_20140620_rocket_m_16_9_512x288.mp4"));
		entries.add(new File("i:\\MediathekView\\Abendschau\\Abendschau-Start-Up_Schmiede__Rocket_Internet_-abendschau_20140620_rocket_m_16_9_512x288.txt"));


		LinkedMedia = new LinkedMedia(firstSubDirectory,entries);
	}

//	@Test
//	public void test() {
//
//	}

}
