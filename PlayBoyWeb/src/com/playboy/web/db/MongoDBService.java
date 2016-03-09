package com.playboy.web.db;

import com.playboy.web.log.Logger;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class MongoDBService {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		JsonObject config = new JsonObject();
		config.put("connection_string", "mongodb://localhost:27017");
		config.put("db_name", "test");
		MongoClient client = MongoClient.createShared(vertx, config);
		JsonObject doc = new JsonObject().put("title", "The vertx mongo");
		client.save("books", doc, result -> {
			if (result.succeeded()) {
				Logger.log("Save succeed.");
			} else {
				result.cause().printStackTrace();
			}
		});
	}
}
