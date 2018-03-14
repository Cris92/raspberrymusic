package com.raspberrymusic.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raspberrymusic.controller.CoreController;
import com.raspberrymusic.utility.Configuration;

@RestController
@RequestMapping(value = "/application")
public class ApplicationController {

	@Autowired
	private CoreController coreController;

	@RequestMapping(value = "/addNewMusic")
	private ResponseEntity<String> addNewMusic() throws IOException, InterruptedException {
		String downloadPath = Configuration.getInstance()
				.getProperty("raspberrymusic.application.property.download.folder", "C://downloadedMusic");
		coreController.executeCommand("add " + downloadPath);
		coreController.executeCommand("systemctl restart mpd");
		return new ResponseEntity<String>(HttpStatus.OK);

	}

}
