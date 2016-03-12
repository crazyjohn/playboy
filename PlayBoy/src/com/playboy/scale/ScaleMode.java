package com.playboy.scale;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.playboy.net.IoHandler;

public class ScaleMode {
	private static Logger logger = LoggerFactory.getLogger("Server");

	public static void singleMode(Vertx vertx, int port) {
		scaleOutMode(vertx, port, 1);
	}

	public static void scaleOutMode(Vertx vertx, int port, int instanceCount) {
		for (int i = 0; i < instanceCount; i++) {
			// net server
			NetServerOptions options = new NetServerOptions();
			NetServer server = vertx.createNetServer(options);
			server.connectHandler(socket -> {
				logger.info(String.format("Connection comming: %s", socket.remoteAddress()));
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
					logger.info(String.format("PlayBoyServer listening on port: %d", port));
				} else {
					result.cause().printStackTrace();
				}
			});
		}
	}
}
