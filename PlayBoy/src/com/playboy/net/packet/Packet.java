package com.playboy.net.packet;

import io.vertx.core.buffer.Buffer;

public interface Packet {
	public static final int MESSAGE_TYPE_LENGTH = 2;
	public static final int MESSAGE_LEN_LENGTH = 4;
	public static final int HEAD_SIZE = MESSAGE_TYPE_LENGTH + MESSAGE_LEN_LENGTH;

	public static Packet packet(short type, byte[] bytes) {
		return new StringPacket(type, bytes);
	}

	public static class StringPacket implements Packet {
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

	short type();
}
