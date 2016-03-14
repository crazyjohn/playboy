package com.playboy.net.dispatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.playboy.core.net.packet.ProtobufPacket;

public class LogDispatcher implements PacketDispatcher {
	Logger logger = LoggerFactory.getLogger("Server");

	@Override
	public void dispatch(ProtobufPacket packet) {
		logger.info(packet.toString());
	}
}
