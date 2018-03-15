package com.raspberrymusic.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raspberrymusic.constants.Commands;
import com.raspberrymusic.controller.CoreController;
import com.raspberrymusic.model.Status;
import com.raspberrymusic.utility.ConvertUtil;

@RestController
@RequestMapping(value = "/application")
public class ApplicationController {

	@Autowired
	private CoreController coreController;

	@RequestMapping(value = "/addNewMusic")
	private ResponseEntity<String> addNewMusic() throws IOException, InterruptedException {
		coreController.executeCommand(Commands.UPDATE_ALL);
		coreController.executeCommand(Commands.RESTART_MPD);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@RequestMapping(value = "/getStatus")
	private ResponseEntity<String> getStatus() throws IOException, InterruptedException {
		Process pr = coreController.executeCommand(Commands.STATUS);
		BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = br.readLine();
		Status s = ConvertUtil.convertOutputStatusLineToString(line);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

}
