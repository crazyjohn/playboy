package com.playboy;

import com.playboy.core.log.Logger;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

/**
 * The tcp node;
 * 
 * @author crazyjohn
 *
 */
public class PlayBoyNode {

	public static void main(String[] args) {
		// create vertx instance
		Vertx vertx = Vertx.vertx();
		// net server
		NetServerOptions options = new NetServerOptions();
		NetServer server = vertx.createNetServer(options);
		server.connectHandler(socket -> {
			Logger.log(String.format("Connection comming: %s", socket));
		});
		int port = 8001;
		server.listen(port, result -> {
			if (result.succeeded()) {
				Logger.log(String.format("PlayBoyServer listening on port: %d", port));
			}
		});
	}

}
