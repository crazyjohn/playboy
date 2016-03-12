package com.playboy.bot;

import io.vertx.core.Vertx;

public class RobotApp {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 8001;
		int robotCount = 100;
		Vertx vertx = Vertx.vertx();
		for (int i = 0; i < robotCount; i++) {
			Robot bot = new Robot(vertx);
			bot.danceWithMe(host, port);
		}
	}

}
