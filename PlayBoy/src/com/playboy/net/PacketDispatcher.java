package com.playboy.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.playboy.net.packet.Packet;

public interface PacketDispatcher {
	static Logger logger = LoggerFactory.getLogger("Server");

	void dispatch(Packet packet);

	static PacketDispatcher dispatcher() {
		return new PacketDispatcher() {

			@Override
			public void dispatch(Packet packet) {
				logger.info(packet.toString());
			}
		};
	}
}
