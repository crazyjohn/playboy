package com.playboy.net.dispatch;

import com.playboy.core.net.packet.ProtobufPacket;

public interface PacketDispatcher {

	void dispatch(ProtobufPacket packet);

	static PacketDispatcher dispatcher() {
		return new TypeDispatcher();
	}
}
