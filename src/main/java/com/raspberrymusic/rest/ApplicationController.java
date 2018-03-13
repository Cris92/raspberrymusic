package com.raspberrymusic.rest;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raspberrymusic.utility.Configuration;

@RestController("/application")
public class ApplicationController {

	@RequestMapping(value = "/addNewMusic")
	private ResponseEntity<String> addNewMusic() throws IOException, InterruptedException {
		String downloadPath = Configuration.getInstance()
				.getProperty("raspberrymusic.application.property.download.folder", "C://downloadedMusic");
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("add " + downloadPath);
		pr.waitFor();
		Process pr2 = rt.exec("systemctl restart mpd");
		pr2.waitFor();
		return new ResponseEntity<String>(HttpStatus.OK);

	}

}
