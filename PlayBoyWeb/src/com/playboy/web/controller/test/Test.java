package com.playboy.web.controller.test;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;

import java.util.concurrent.atomic.AtomicInteger;

import com.playboy.web.log.Logger;

public class Test {
	static AtomicInteger count = new AtomicInteger(0);

	public void sayHi(RoutingContext context) {
		context.response().putHeader("content-type", "text/html")
				.end("<font size='10'>Hello biatch, this is playboy!</font>");
		Logger.log("Say hi: " + count.incrementAndGet());
	}

	public void counter(RoutingContext context) {
		Session session = context.session();
		Integer counter = session.get("counter");
		counter = (counter == null ? 0 : counter) + 1;
		session.put("counter", counter);
		context.response().putHeader("content-type", "text/html")
				.end("<html><body><h1>Hitcount: " + counter + "</h1></body></html>");
	}
}
