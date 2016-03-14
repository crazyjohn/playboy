package com.playboy.core.net.packet;

public interface Packet {
	int MESSAGE_TYPE_LENGTH = 2;
	int MESSAGE_LEN_LENGTH = 4;
	int HEAD_SIZE = MESSAGE_TYPE_LENGTH + MESSAGE_LEN_LENGTH;

	static Packet packet(short type, byte[] bytes) {
		return new StringPacket(type, bytes);
	}

	short type();
}
