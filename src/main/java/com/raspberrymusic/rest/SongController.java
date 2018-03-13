package com.raspberrymusic.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raspberrymusic.model.Song;
import com.raspberrymusic.utility.SongUtil;

@RestController("/commands")
public class SongController {

	@RequestMapping(value = "/getSongsList", method = RequestMethod.GET, produces = "application/json")
	private ResponseEntity<List<Song>> getSongsList() throws IOException, InterruptedException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("mpd listall");
		pr.waitFor();
		BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		List<Song> returnList = new ArrayList<Song>();
		while ((line = br.readLine()) != null) {
			Song s = SongUtil.convertOutputLineToString(line);
			returnList.add(s);
		}
		return new ResponseEntity<List<Song>>(returnList, HttpStatus.OK);
	}

	@RequestMapping(value = "/play/{songPos}")
	private ResponseEntity<String> playSong(@PathVariable("songPos") int songPosition)
			throws InterruptedException, IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("mpd play " + songPosition);
		pr.waitFor();
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pause")
	private ResponseEntity<String> pauseSong()
			throws InterruptedException, IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("mpd pause");
		pr.waitFor();
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stop")
	private ResponseEntity<String> stopSong()
			throws InterruptedException, IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("mpd stop");
		pr.waitFor();
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/next")
	private ResponseEntity<String> nextSong()
			throws InterruptedException, IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("mpd next");
		pr.waitFor();
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/previous")
	private ResponseEntity<String> previousSong()
			throws InterruptedException, IOException {
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("mpd previous");
		pr.waitFor();
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}