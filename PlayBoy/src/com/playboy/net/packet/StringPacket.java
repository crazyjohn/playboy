package com.playboy.net.packet;

import io.vertx.core.buffer.Buffer;

public class StringPacket implements Packet {
	private Buffer buffer;
	private short type;

	public StringPacket(short type, byte[] bytes) {
		buffer = Buffer.buffer(bytes);
		this.type = type;
	}

	@Override
	public String toString() {
		return buffer.getString(0, buffer.length(), "UTF-8");
	}

	@Override
	public short type() {
		return type;
	}
}
