package com.playboy.bot;

import com.playboy.core.log.Logger;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;

public class Robot {
	protected Vertx vertx;
	protected NetClient client;

	public Robot(Vertx vertx) {
		this.vertx = vertx;
		client = vertx.createNetClient();
	}

	public void danceWithMe(String host, int port) {

		client.connect(port, host, result -> {
			if (result.succeeded()) {
				Logger.log(String.format("Connect succeed: %s", result.result().localAddress()));
				result.result().write("I am ok!");
			} else {
				Logger.log(String.format("Connect failed: %s:%d", host, port));
			}
		});
	}

}
