package com.playboy.web.scale;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import com.playboy.web.api.WebApi;
import com.playboy.web.controller.self.Self;
import com.playboy.web.controller.test.Test;
import com.playboy.web.log.Logger;

public class ScaleMode {

	public static void singleMode(Vertx vertx, int port) {
		scaleOutMode(vertx, 1, port);
	}

	public static void scaleOutMode(Vertx vertx, int instanceCount, int port) {
		for (int i = 0; i < instanceCount; i++) {
			HttpServerOptions options = new HttpServerOptions();
			HttpServer server = vertx.createHttpServer(options);
			// create router
			Router router = Router.router(vertx);
			// test case
			Test testController = new Test();
			router.route(WebApi.SAY_HI).handler(testController::sayHi);
			// upload case
			Self selfController = new Self();
			// Enable multipart form data parsing
			Logger.log(String.format("Upload dir: %s",
					System.getProperty("user.dir") + System.getProperty("file.separator") + "upload"));
			router.route().handler(
					BodyHandler.create().setUploadsDirectory(
							System.getProperty("user.dir") + System.getProperty("file.separator") + "upload"));
			// form
			router.route(WebApi.UPLOAD_FORM).handler(selfController::uploadForm);
			router.route(WebApi.FORM).handler(selfController::form);
			// start server
			server.requestHandler(router::accept).listen(port, result -> {
				if (result.succeeded()) {
					Logger.log("The playboy ready!");
				} else {
					Logger.log("Some fucking error???: " + result.cause());
				}
			});
		}
	}

}
