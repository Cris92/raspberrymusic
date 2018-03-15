package com.raspberrymusic.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raspberrymusic.constants.Commands;
import com.raspberrymusic.controller.CoreController;
import com.raspberrymusic.model.Song;
import com.raspberrymusic.utility.ConvertUtil;

@RestController
@RequestMapping(value = "/commands")
public class SongController {

	@Autowired
	private CoreController coreController;

	@RequestMapping(value = "/getSongsList", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<List<Song>> getSongsList() throws IOException, InterruptedException {
		Process pr = coreController.executeCommand(Commands.LIST_ALL_SONGS);
		BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		List<Song> returnList = new ArrayList<Song>();
		while ((line = br.readLine()) != null) {
			Song s = ConvertUtil.convertOutputSongLineToString(line);
			returnList.add(s);
		}
		return new ResponseEntity<List<Song>>(returnList, HttpStatus.OK);
	}

	@RequestMapping(value = "/play/{songPos}")
	private ResponseEntity<String> playSong(@PathVariable("songPos") int songPosition)
			throws InterruptedException, IOException {
		coreController.executeCommand(Commands.PLAY + songPosition);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/pause")
	private ResponseEntity<String> pauseSong() throws InterruptedException, IOException {
		coreController.executeCommand(Commands.PAUSE);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/stop")
	private ResponseEntity<String> stopSong() throws InterruptedException, IOException {
		coreController.executeCommand(Commands.STOP);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/next")
	private ResponseEntity<String> nextSong() throws InterruptedException, IOException {
		coreController.executeCommand(Commands.NEXT);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/previous")
	private ResponseEntity<String> previousSong() throws InterruptedException, IOException {
		coreController.executeCommand(Commands.PREVIOUS);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
