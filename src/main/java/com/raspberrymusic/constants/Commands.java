package com.raspberrymusic.constants;

public interface Commands {

	public static final String PLAY = "mpc play ";
	public static final String PAUSE = "mpc pause ";
	public static final String STOP = "mpc stop ";
	public static final String NEXT = "mpc next ";
	public static final String PREVIOUS = "mpc previous ";
	public static final String UPDATE_ALL = "mpc update ";
	public static final String RESTART_MPD = "systemctl restart mpd ";
	public static final String LIST_ALL_SONGS = "mpc listall ";
	public static final String ADD_ALL_TO_PLAYLIST = "mpc ls | mpc add";
	public static final String CLEAR_PLAYLIST = "mpc clear";
	public static final String STATUS = "mpc ";
	public static final String ADD = "mpc add ";

}
