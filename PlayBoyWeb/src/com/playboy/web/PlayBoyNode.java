package com.playboy.web;

import io.vertx.core.Vertx;

import com.playboy.web.scale.ScaleMode;

/**
 * The playboy node;
 * <p>
 * easy scale out
 * 
 * @author crazyjohn
 *
 */
public class PlayBoyNode {

	/**
	 * entrance
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// create vertx instance
		Vertx vertx = Vertx.vertx();
		// processor count
		int processorCount = 1;
		// choose scale mode
		ScaleMode.scaleOutMode(vertx, processorCount, 8080);
	}

}
