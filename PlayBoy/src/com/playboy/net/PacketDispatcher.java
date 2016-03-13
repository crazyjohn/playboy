package com.playboy.net;

import com.playboy.net.packet.Packet;

public interface PacketDispatcher {
	void dispatch(Packet packet);

	static PacketDispatcher dispatcher() {
		return new PacketDispatcher() {

			@Override
			public void dispatch(Packet packet) {
				System.out.println(packet);
			}
		};
	}
}
