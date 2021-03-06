package com.playboy.web.scale;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

import com.playboy.core.api.WebApi;
import com.playboy.core.log.Logger;
import com.playboy.web.controller.self.Self;
import com.playboy.web.controller.test.Test;

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
			// cookie
			router.route().handler(CookieHandler.create());
			// session handler
			router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
			// test case
			Test testController = new Test();
			router.route(WebApi.SAY_HI).handler(testController::sayHi);
			// counter
			router.route(WebApi.COUNTER).handler(testController::counter);
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
