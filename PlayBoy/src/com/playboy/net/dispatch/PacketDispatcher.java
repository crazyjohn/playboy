package com.playboy.net.dispatch;

import com.playboy.core.net.packet.Packet;

public interface PacketDispatcher {

	void dispatch(Packet packet);

	static PacketDispatcher dispatcher() {
		return new TypeDispatcher();
	}
}
