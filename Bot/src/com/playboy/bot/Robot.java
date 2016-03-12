package com.playboy.bot;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Robot {
	private Logger logger = LoggerFactory.getLogger("Server");
	protected Vertx vertx;
	protected NetClient client;
	protected final long id;

	public Robot(Vertx vertx, long id) {
		this.vertx = vertx;
		client = vertx.createNetClient();
		this.id = id;
	}

	public void danceWithMe(String host, int port) {
		client.connect(port, host, result -> {
			if (result.succeeded()) {
				logger.info(String.format("Connect succeed: %s, id: %d", result.result().localAddress(), id));
				result.result().write(String.format("I am ok! id: %d", id));
			} else {
				logger.error(String.format("Connect failed: %s:%d, id: %d", host, port, id), result.cause());
			}
		});
	}

}
