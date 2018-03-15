package com.raspberrymusic.utility;

import org.apache.log4j.Logger;

import com.raspberrymusic.model.Song;
import com.raspberrymusic.model.Status;

public class ConvertUtil {

	private static Logger logger = Logger.getLogger(ConvertUtil.class);

	public static Song convertOutputSongLineToString(String line,int position) {
		logger.debug("Received line:");
		logger.debug(line);
		Song result = new Song();
		result.setTitle(line);
		result.setPosition(position);
		// Calculate Song from return of listall
		logger.debug("Result:");
		logger.debug(result);
		return null;
	}

	public static Status convertOutputStatusLineToString(String line) {
		logger.debug("Received line:");
		logger.debug(line);
		Status result = new Status();
		// Calculate Status from return of mpc
		logger.debug("Result:");
		logger.debug(result);
		return null;
	}
}
