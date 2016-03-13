package com.playboy.bot;

import io.vertx.core.Vertx;

public class RobotApp {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 8001;
		final long from = 0;
		final long to = 0;
		Vertx vertx = Vertx.vertx();
		for (long i = from; i <= to; i++) {
			Robot bot = new Robot(vertx, i);
			bot.danceWithMe(host, port);
		}
	}

}
