package com.playboy.core.net.packet;

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

	public Builder setBuilder(Builder builder) {
		this.builder = builder;
		return this.builder;
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

}
