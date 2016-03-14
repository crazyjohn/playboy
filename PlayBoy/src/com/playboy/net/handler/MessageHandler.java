package com.playboy.net.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.InvalidProtocolBufferException;
import com.playboy.core.net.packet.ProtobufPacket;
import com.playboy.proto.Chats.Chat;

public interface MessageHandler {
	Logger logger = LoggerFactory.getLogger("Server");

	void handle(ProtobufPacket packet);

	static MessageHandler dummy() {
		return new MessageHandler() {

			@Override
			public void handle(ProtobufPacket packet) {
				try {
					Chat.Builder builder = packet.getBuilder(Chat.newBuilder());
					logger.info(builder.getContent());
				} catch (InvalidProtocolBufferException e) {
					logger.error("Parse error", e);
				}
			}
		};
	}
}
