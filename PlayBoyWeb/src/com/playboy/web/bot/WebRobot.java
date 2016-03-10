package com.playboy.web.bot;

import com.playboy.web.api.WebApi;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

/**
 * The web robot
 * 
 * @author crazyjohn
 *
 */
public class WebRobot {

	public static void main(String[] args) throws InterruptedException {
		Vertx vertx = Vertx.vertx();
		HttpClient client = vertx.createHttpClient();
		int callTimes = 100000;
		int sleepTimes = 1;
		for (int i = 0; i < callTimes; i++) {
			Thread.sleep(sleepTimes);
			client.getNow(8080, "127.0.0.1", WebApi.SAY_HI, response -> {
				// Logger.log("Received response with status code " +
				// response.statusCode());

				});
			// upload
			client.getNow(8080, "127.0.0.1", WebApi.UPLOAD_FORM, response -> {
			});
		}
		vertx.close();
	}
}
