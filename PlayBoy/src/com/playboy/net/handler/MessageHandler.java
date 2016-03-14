package com.playboy.net.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.playboy.net.packet.Packet;

public interface MessageHandler {
	Logger logger = LoggerFactory.getLogger("Server");

	short type();

	void handle(Packet packet);

	static MessageHandler dummy() {
		return new MessageHandler() {
			@Override
			public short type() {
				return 0;
			}

			@Override
			public void handle(Packet packet) {
				logger.info(packet.toString());
			}
		};
	}
}
