package com.playboy.net.dispatch;

import java.util.HashMap;
import java.util.Map;

import com.playboy.core.net.packet.ProtobufPacket;
import com.playboy.net.handler.MessageHandler;

public class TypeDispatcher implements PacketDispatcher {
	private Map<Short, MessageHandler> handlers = new HashMap<Short, MessageHandler>();


	@Override
	public void dispatch(ProtobufPacket packet) {
		MessageHandler handler = handlers.getOrDefault(packet.type(), MessageHandler.dummy());
		handler.handle(packet);
	}

}
