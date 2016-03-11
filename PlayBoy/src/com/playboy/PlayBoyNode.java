package com.playboy;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

import com.playboy.core.log.Logger;
import com.playboy.net.NetServerIoHandler;

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
		// io things
		server.connectHandler(socket -> {
			Logger.log(String.format("Connection comming: %s", socket.remoteAddress()));
			NetServerIoHandler ioHandler = new NetServerIoHandler();
			// received
			socket.handler(ioHandler::receive);
			socket.endHandler(ioHandler::end);
			// close
			socket.closeHandler(ioHandler::close);
			// exception
			socket.exceptionHandler(ioHandler::exception);
		});
		// listen on port
		int port = 8001;
		server.listen(port, result -> {
			if (result.succeeded()) {
				Logger.log(String.format("PlayBoyServer listening on port: %d", port));
			}
		});
	}

}
