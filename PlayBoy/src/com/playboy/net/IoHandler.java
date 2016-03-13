package com.playboy.net;

import com.playboy.core.log.Logger;
import com.playboy.net.packet.Packet;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

/**
 * Io handler
 * 
 * @author crazyjohn
 *
 */
public class IoHandler {
	protected NetSocket socket;
	protected Buffer readBuffer = Buffer.buffer();
	protected PacketDispatcher dispatcher;

	public IoHandler(NetSocket socket, PacketDispatcher dispatcher) {
		this.socket = socket;
		this.dispatcher = dispatcher;
	}

	public void receive(Buffer buffer) {
		readBuffer.appendBuffer(buffer);
		while (readBuffer.length() > 0) {
			if (readBuffer.length() < Packet.HEAD_SIZE) {
				break;
			}
			short type = readBuffer.getShort(0);
			int length = readBuffer.getInt(2);
			if (readBuffer.length() < length) {
				break;
			}
			// dispatch
			Packet packet = Packet.packet(type, readBuffer.getBytes(6, length - 6));
			dispatcher.dispatch(packet);
			System.out.println(readBuffer.length());
		}
		Logger.log("Call receive: " + buffer.getString(0, buffer.length()));
	}

	public void close(Void nothing) {
	}

	public void exception(Throwable e) {
	}

	public void end(Void nothing) {
		Logger.log("Call end.");
	}
}
