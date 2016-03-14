package com.playboy.startup;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.NetSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.playboy.net.IoHandler;
import com.playboy.net.dispatch.PacketDispatcher;

public class StartupMode {
	private static Logger logger = LoggerFactory.getLogger("Server");
	private static PacketDispatcher dispatcher = PacketDispatcher.dispatcher();

	public static void singleMode(Vertx vertx, int port) {
		scaleOutMode(vertx, port, 1);
	}

	public static void scaleOutMode(Vertx vertx, int port, int instanceCount) {
		for (int i = 0; i < instanceCount; i++) {
			// net server
			NetServerOptions options = new NetServerOptions();
			NetServer server = vertx.createNetServer(options);
			server.connectHandler(socket -> {
				ioPrepare(socket);
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

	private static void ioPrepare(NetSocket socket) {
		// io things
		IoHandler ioHandler = new IoHandler(socket, dispatcher);
		// open
		ioHandler.open(socket);
		// receive
		socket.handler(ioHandler::receive);
		// end
		socket.endHandler(ioHandler::end);
		// close
		socket.closeHandler(ioHandler::close);
		// exception
		socket.exceptionHandler(ioHandler::exception);
	}
}
