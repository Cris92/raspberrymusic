package com.raspberrymusic.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raspberrymusic.constants.Commands;
import com.raspberrymusic.controller.CoreController;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistController {

	@Autowired
	private CoreController coreController;

	@RequestMapping(value = "/add/{idSong}")
	private ResponseEntity<String> addSongToCurrentPlaylist(@PathVariable("idSong") int idSong) {
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/startAll")
	private ResponseEntity<String> startAllSongs() throws IOException {
		coreController.executeCommand(Commands.ADD_ALL_TO_PLAYLIST);
		coreController.executeCommand(Commands.PLAY);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/clearAll")
	private ResponseEntity<String> clearPlaylist() throws IOException {
		coreController.executeCommand(Commands.CLEAR_PLAYLIST);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
