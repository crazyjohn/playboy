package com.playboy.bot;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.playboy.core.net.packet.ProtobufPacket;
import com.playboy.proto.Chats.Chat;

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

	/**
	 * come on baby, dance with me
	 * 
	 * @param host
	 * @param port
	 */
	public void danceWithMe(String host, int port) {
		client.connect(port, host, result -> {
			if (result.succeeded()) {
				logger.info(String.format("Connect succeed: %s, id: %d", result.result().localAddress(), id));
				sayHi(result.result());
			} else {
				logger.error(String.format("Connect failed: %s:%d, id: %d", host, port, id), result.cause());
			}
		});
	}

	private void sayHi(NetSocket socket) {
		ProtobufPacket packet = new ProtobufPacket((short) 1001);
		int defaultServerId = 1;
		packet.setBuilder(Chat.newBuilder()).setServerId(defaultServerId).setPlayerId(id)
				.setContent(String.format("I am ok! id: %d", id));
		socket.write(packet.buffer());
	}
}
