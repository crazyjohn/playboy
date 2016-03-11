package com.playboy;

import com.playboy.core.log.Logger;
import com.playboy.net.IoHandler;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

public class ScaleMode {

	public static void singleMode(Vertx vertx, int port) {
		scaleOutMode(vertx, 1, port);
	}

	public static void scaleOutMode(Vertx vertx, int instanceCount, int port) {
		for (int i = 0; i < instanceCount; i++) {
			// net server
			NetServerOptions options = new NetServerOptions();
			NetServer server = vertx.createNetServer(options);
			server.connectHandler(socket -> {
				Logger.log(String.format("Connection comming: %s", socket.remoteAddress()));
				// io things
				IoHandler ioHandler = new IoHandler(socket);
				// received
				socket.handler(ioHandler::receive);
				socket.endHandler(ioHandler::end);
				// close
				socket.closeHandler(ioHandler::close);
				// exception
				socket.exceptionHandler(ioHandler::exception);
			});
			// listen on port
			server.listen(port, result -> {
				if (result.succeeded()) {
					Logger.log(String.format("PlayBoyServer listening on port: %d", port));
				} else {
					result.cause().printStackTrace();
				}
			});
		}
	}
}
