package com.raspberrymusic.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raspberrymusic.controller.CoreController;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistController {

	@Autowired
	private CoreController coreController;

	@RequestMapping(value = "/add/{idSong}")
	private ResponseEntity<String> addSongToCurrentPlaylist(@PathVariable("idSong") int idSong) {
		return null;
	}
}
