package com.raspberrymusic.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Configuration {

	private static Properties _config;
	public static final String DOMAIN_HOME = System.getenv("raspberrymusic");
	private static Logger logger = Logger.getLogger(Configuration.class);

	private Configuration() {
		init();
	}

	private static void init() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			String filePath = System.getProperty("raspberrymusic.config.file",
					DOMAIN_HOME + "/config/application.properties");
			logger.debug("Configuration filepath:" + filePath);
			input = new FileInputStream(filePath);
			prop.load(input);
			_config = prop;

		} catch (IOException e) {

		}
	}

	public static Properties getInstance() {
		if (_config == null) {
			init();
		}
		return _config;
	}

	public static String getProp(String key) {
		return _config.getProperty(key);
	}

	public static String getProp(String key, String defaultValue) {
		return _config.getProperty(key, defaultValue);
	}

}
