package com.raspberrymusic.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raspberrymusic.constants.Commands;
import com.raspberrymusic.controller.CoreController;
import com.raspberrymusic.exceptions.InternalServerErrorException;
import com.raspberrymusic.model.Song;
import com.raspberrymusic.utility.ConvertUtil;

@RestController
@RequestMapping(value = "/commands")
public class SongController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private CoreController coreController;

	@RequestMapping(value = "/getSongsList", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<List<Song>> getSongsList() throws IOException, InterruptedException {
		try {
			logger.debug("Calling of /getSongsList");
			logger.debug("Calling 'mpc listall' command");
			Process pr = coreController.executeCommand(Commands.LIST_ALL_SONGS);
			logger.debug("Listall command succeded");
			BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			List<Song> returnList = new ArrayList<Song>();
			logger.debug("Output:");
			while ((line = br.readLine()) != null) {
				Song s = ConvertUtil.convertOutputSongLineToString(line);
				logger.debug(s);
				returnList.add(s);
			}
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<List<Song>>(returnList, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("/getSongsList failed");
			logger.error("Cause: " + e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/play/{songPos}")
	private ResponseEntity<String> playSong(@PathVariable("songPos") int songPosition)
			throws InterruptedException, IOException {
		try {
			logger.debug("Calling of /play");
			logger.debug("Calling 'mpc play" + songPosition + "' command");
			coreController.executeCommand(Commands.PLAY + songPosition);
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("/play/" + songPosition + " failed");
			logger.error("Cause: " + e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/pause")
	private ResponseEntity<String> pauseSong() throws InterruptedException, IOException {
		try {
			logger.debug("Calling of /pause");
			logger.debug("Calling 'mpc pause' command");
			coreController.executeCommand(Commands.PAUSE);
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("/pause failed");
			logger.error("Cause: " + e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/stop")
	private ResponseEntity<String> stopSong() throws InterruptedException, IOException {
		try {
			logger.debug("Calling of /stop");
			logger.debug("Calling 'mpc stop' command");
			coreController.executeCommand(Commands.STOP);
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("/stop failed");
			logger.error("Cause: " + e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/next")
	private ResponseEntity<String> nextSong() throws InterruptedException, IOException {
		try {
			logger.debug("Calling of /next");
			logger.debug("Calling 'mpc next' command");
			coreController.executeCommand(Commands.NEXT);
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("/next failed");
			logger.error("Cause: " + e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/previous")
	private ResponseEntity<String> previousSong() throws InterruptedException, IOException {
		try {
			logger.debug("Calling of /previous");
			logger.debug("Calling 'mpc previous' command");
			coreController.executeCommand(Commands.PREVIOUS);
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("/previous failed");
			logger.error("Cause: " + e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}
}
