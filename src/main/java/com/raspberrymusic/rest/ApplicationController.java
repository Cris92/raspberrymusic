package com.raspberrymusic.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raspberrymusic.constants.Commands;
import com.raspberrymusic.controller.CoreController;
import com.raspberrymusic.exceptions.InternalServerErrorException;
import com.raspberrymusic.model.Status;
import com.raspberrymusic.utility.ConvertUtil;

@RestController
@RequestMapping(value = "/application")
public class ApplicationController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private CoreController coreController;

	@RequestMapping(value = "/addNewMusic")
	private ResponseEntity<String> addNewMusic() {
		try {
			logger.debug("Calling of /addNewMusic");
			logger.debug("Calling 'mpc update' command");
			coreController.executeCommand(Commands.UPDATE_ALL);
			logger.debug("Update command succeded");
			logger.debug("Calling 'systemctl restart mpd' command");
			coreController.executeCommand(Commands.RESTART_MPD);
			logger.debug("Restart succeded");
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.OK);

		} catch (Exception e) {
			logger.error("/addNewMusic failed");
			logger.error("Cause: " + e);
			throw new InternalServerErrorException(e.getMessage());
		}
		
	}

	@RequestMapping(value = "/getStatus")
	private ResponseEntity<Status> getStatus() throws IOException, InterruptedException {
		try {
			logger.debug("Calling of /getStatus");
			logger.debug("Calling 'mpc' command");
			Process pr = coreController.executeCommand(Commands.STATUS);
			logger.debug("Status command succeded");
			BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = br.readLine();
			Status status = ConvertUtil.convertOutputStatusLineToString(line);
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("/getStatus failed");
			logger.error("Cause: " + e);
			throw new InternalServerErrorException(e.getMessage());
		}
		

	}

}
