package com.raspberrymusic.exceptions;

public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errCode = "500";
	private String errMsg;

	// getter and setter methods

	public InternalServerErrorException(String errMsg) {
		this.errMsg = errMsg;
	}

}
