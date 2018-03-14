package com.raspberrymusic.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

@Controller
public class CoreController {

	
	
	public Process executeCommand(String command) throws IOException{
		Runtime rt = Runtime.getRuntime();
		return rt.exec(command);
	}
}
