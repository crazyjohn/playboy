package com.playboy.net.dispatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.playboy.net.packet.Packet;

public class LogDispatcher implements PacketDispatcher {
	Logger logger = LoggerFactory.getLogger("Server");

	@Override
	public void dispatch(Packet packet) {
		logger.info(packet.toString());
	}
}
