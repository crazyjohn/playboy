package com.playboy.net;

import com.playboy.core.log.Logger;

import io.vertx.core.buffer.Buffer;

public class NetServerIoHandler {
	public void receive(Buffer buffer) {
		Logger.log("Call receive: " + buffer.length());
	}

	public void close(Void nothing) {
	}

	public void exception(Throwable e) {
	}

	public void end(Void nothing) {
		Logger.log("Call end.");
	}
}
