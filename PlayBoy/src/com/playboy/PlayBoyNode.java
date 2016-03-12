package com.playboy;

import com.playboy.scale.ScaleMode;

import io.vertx.core.Vertx;

/**
 * The play node
 * 
 * @author crazyjohn
 *
 */
public class PlayBoyNode {

	public static void main(String[] args) {
		// create vertx instance
		Vertx vertx = Vertx.vertx();
		// scale out mode
		int port = 8001;
		ScaleMode.scaleOutMode(vertx, port, 4);
	}

}
