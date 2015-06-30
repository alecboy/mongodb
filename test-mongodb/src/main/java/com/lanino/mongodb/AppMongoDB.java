package com.lanino.mongodb;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class AppMongoDB {

	public static void main(String[] args) {

		System.out.println("Connecting...");
		
		MongoClient client = new MongoClient("127.0.0.1", 27017);

		MongoDatabase testdb = client.getDatabase("hockey");

		MongoCollection<Document> table = testdb.getCollection("players");

		Document user = new Document();
		user.put("name", "Alejandro Nino");
		user.put("age", 32);
		user.put("birthdate", "March 18, 1983");

		System.out.println("Inserting...");
		table.insertOne(user);

		Document search = new Document();
		search.put("age", 32);

		System.out.println("Searching 1...");
		MongoCursor<Document> result = table.find(search).iterator();
		while (result.hasNext()) {
			System.out.println(result.next());
		}

		table.updateOne(eq("name", "alejo"), new Document("$set", new Document(
				"name", "Alejo Update")));
		
		System.out.println("Updating...");
		search = new Document();
		search.put("name", "Alejo Update");

		System.out.println("Searching 2...");
		result = table.find(search).iterator();
		while (result.hasNext()) {
			System.out.println(result.next());
		}
		
		System.out.println("Closing...");
		client.close();
		
	}

}
