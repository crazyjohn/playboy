package com.playboy.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.playboy.core.net.packet.Packet;
import com.playboy.net.dispatch.PacketDispatcher;

/**
 * Io handler
 * 
 * @author crazyjohn
 *
 */
public class IoHandler {
	private Logger logger = LoggerFactory.getLogger("Server");
	protected NetSocket socket;
	protected ByteBuf readBuffer = PooledByteBufAllocator.DEFAULT.ioBuffer();
	protected PacketDispatcher dispatcher;

	public IoHandler(NetSocket socket, PacketDispatcher dispatcher) {
		this.socket = socket;
		this.dispatcher = dispatcher;
	}

	public void open(NetSocket socket) {
		logger.info(String.format("Session opened: %s", socket.remoteAddress()));
	}

	/**
	 * The receive callback
	 * 
	 * @param buffer
	 */
	public void receive(Buffer buffer) {
		readBuffer.writeBytes(buffer.getBytes());
		while (readBuffer.isReadable()) {
			if (readBuffer.readableBytes() < Packet.HEAD_SIZE) {
				break;
			}
			// get type and length
			readBuffer.markReaderIndex();
			short type = readBuffer.readShort();
			int length = readBuffer.readInt();
			if (readBuffer.readableBytes() < length - Packet.HEAD_SIZE) {
				readBuffer.resetReaderIndex();
				break;
			}
			// dispatch
			Packet packet = Packet.packet(type, readBuffer.readBytes(length - Packet.HEAD_SIZE).array());
			dispatcher.dispatch(packet);
		}
	}

	/**
	 * The close callback
	 * 
	 * @param nothing
	 */
	public void close(Void nothing) {
		logger.info(String.format("Session closed: %s", socket.remoteAddress()));
	}

	/**
	 * The exception callback
	 * 
	 * @param e
	 */
	public void exception(Throwable e) {
		logger.error("exception cach", e);
	}

	/**
	 * What is the fuck??
	 * 
	 * @param nothing
	 */
	public void end(Void nothing) {
		logger.info("Call end: " + socket.remoteAddress());
	}
}
