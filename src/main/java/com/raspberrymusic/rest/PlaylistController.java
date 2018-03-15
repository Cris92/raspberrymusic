package com.raspberrymusic.rest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raspberrymusic.constants.Commands;
import com.raspberrymusic.controller.CoreController;
import com.raspberrymusic.exceptions.InternalServerErrorException;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private CoreController coreController;

	@RequestMapping(value = "/add/{idSong}")
	private ResponseEntity<String> addSongToCurrentPlaylist(@PathVariable("idSong") int idSong) {
		try {
			logger.debug("Calling of /add/" + idSong);
			logger.debug("Calling 'mpc add " + idSong + "' command");
			coreController.executeCommand(Commands.ADD + idSong);
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("/add/" + idSong + " failed");
			logger.error("Cause: " + e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/startAll")
	private ResponseEntity<String> startAllSongs() throws IOException {
		try {
			logger.debug("Calling of /startAll");
			logger.debug("Calling 'mpc ls | mpc add' command");
			coreController.executeCommand(Commands.ADD_ALL_TO_PLAYLIST);
			logger.debug("AddAll succeded");
			logger.debug("Calling 'mpc play' command");
			coreController.executeCommand(Commands.PLAY);
			logger.debug("Play Succeded");
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("/startAll failed");
			logger.error("Cause: " + e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/clearAll")
	private ResponseEntity<String> clearPlaylist() throws IOException {
		try {
			logger.debug("Calling of /clearAll");
			logger.debug("Calling 'mpc clear' command");
			coreController.executeCommand(Commands.CLEAR_PLAYLIST);
			logger.debug("Exiting with status " + HttpStatus.OK);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("/cleartAll failed");
			logger.error("Cause: " + e);
			throw new InternalServerErrorException(e.getMessage());
		}
	}
}
