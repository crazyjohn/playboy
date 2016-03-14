package com.playboy.core.net.packet;

import io.vertx.core.buffer.Buffer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message.Builder;

public class ProtobufPacket implements Packet {
	private short type;
	private Builder builder;
	private byte[] bodyBytes;

	public ProtobufPacket(short type) {
		this.type = type;
	}

	public ProtobufPacket setBody(byte[] bodyBytes) {
		this.bodyBytes = bodyBytes;
		return this;
	}

	@SuppressWarnings("unchecked")
	public <B extends Builder> B setBuilder(B builder) {
		this.builder = builder;
		return (B) this.builder;
	}

	@SuppressWarnings("unchecked")
	protected <B extends Builder> B parseBuilder(Builder builder) throws InvalidProtocolBufferException {
		this.builder = builder;
		this.builder = builder.mergeFrom(this.bodyBytes);
		return (B) this.builder;
	}

	@SuppressWarnings("unchecked")
	public <B extends Builder> B getBuilder(Builder builder) throws InvalidProtocolBufferException {
		if (this.builder == null) {
			this.parseBuilder(builder);
		}
		return (B) this.builder;
	}

	@Override
	public short type() {
		return type;
	}

	public Buffer buffer() {
		Buffer writeBuffer = Buffer.buffer();
		writeBuffer.appendShort(this.type);
		writeBuffer.appendInt(0);
		writeBuffer.appendBytes(this.builder.build().toByteArray());
		writeBuffer.setInt(2, writeBuffer.length());
		return writeBuffer;
	}

}
