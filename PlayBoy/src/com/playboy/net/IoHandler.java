package com.playboy.net;

import com.playboy.core.log.Logger;

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

	public IoHandler(NetSocket socket) {
		this.socket = socket;
	}

	public void receive(Buffer buffer) {
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
