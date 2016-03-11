package com.playboy.bot;

import com.playboy.core.log.Logger;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;

public class Robot {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		NetClient client = vertx.createNetClient();
		String host = "localhost";
		int port = 8001;
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
