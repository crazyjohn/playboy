package com.playboy.net;

import com.google.protobuf.Message.Builder;

public interface MessageSender {
	public void send(int type, Builder body);
}
