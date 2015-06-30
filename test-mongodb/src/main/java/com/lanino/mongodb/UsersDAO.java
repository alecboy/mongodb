package com.lanino.mongodb;

import static com.mongodb.client.model.Filters.lt;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class UsersDAO {

	private MongoCollection<Document> users;

	public UsersDAO(MongoCollection<Document> collection) {
		this.users = collection;
	}

	// db.users.createIndex({"age": 1})
	public List<User> getAgeLt(double age) {
		// db.users.find({"age": {$lt: 23}})
		FindIterable<Document> results = users.find(lt("age", age));
		List<User> users = new ArrayList<User>();
		if (users != null) {
			for (Document d : results) {
				users.add(fromDBObject(d));
			}
		}
		return users;
	}

	// db.users.aggregate({
	// $group: {
	// _id: "$eyeColor",
	// total: {$sum: 1}}
	// })
	public List<Document> getEyeColorTotal() {
		List<Document> ag = new ArrayList<Document>();
		/*
		 * ag.add(new Document("$group", (new Document()).append("_id",
		 * "$eyeColor").append("total", new Document("$sum", 1))));
		 */
		ag.add(Document
				.parse("{$group: {_id: \"$eyeColor\", total: {$sum: 1}} }"));
		AggregateIterable<Document> result = users.aggregate(ag);
		ag.clear();
		for (Document d : result) {
			ag.add(d);
		}
		return ag;
	}

	// db.users.aggregate({
	// $group: {
	// _id: "$gender",
	// total: {$sum: 1}}
	// })

	// db.users.aggregate({
	// $group: {
	// _id: "$gender",
	// avgAge: {$avg: "$age"}}
	// })

	/*
	 * private Document toDBObject(User player) { Document d = new Document();
	 * d.put("age", player.getAge()); d.put("name", player.getAge()); return d;
	 * }
	 */

	private User fromDBObject(Document document) {
		User p = new User();
		p.set_Id(document.getObjectId("_id"));
		p.setName(document.getString("name"));
		p.setAge(document.getDouble("age"));
		return p;
	}

}
