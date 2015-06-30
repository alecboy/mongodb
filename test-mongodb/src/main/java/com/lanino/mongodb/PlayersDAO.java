package com.lanino.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class PlayersDAO {

	private MongoCollection<Document> players;

	public PlayersDAO(MongoCollection<Document> collection) {
		this.players = collection;
	}

	public void createPlayer(Player player) {
		//db.players.insert({"name": "SSSS", "age": 5, ...})
		Document p = toDBObject(player);
		try {
			players.insertOne(p);
		} catch (MongoException mex) {
			mex.printStackTrace();
		}
	}

	public void updatePlayer(Player player) {
		//db.players.update({"_id": ObjectId("SSSSSS")}, {"name": "SSSSS" ...})
		players.findOneAndUpdate(new Document("_id", player.get_Id()),
				toDBObject(player));
	}

	public void removePlayer(String id) {
		//db.players.remove({"_id": ObjectId("SSSSSS")})
		players.findOneAndDelete(new Document("_id", id));
	}

	public List<Player> getPlayersByPosition(String position) {
		//db.players.find({"position": ...})
		FindIterable<Document> results = players.find(new Document("position", position));
		List<Player> players = new ArrayList<Player>();
		if (players != null) {
			for (Document d : results) {
				players.add(fromDBObject(d));
			}
		}
		return players;		
	}
	
	public List<Player> getAllPlayers() {
		//db.players.find()
		FindIterable<Document> results = players.find();
		List<Player> players = new ArrayList<Player>();
		if (players != null) {
			for (Document d : results) {
				players.add(fromDBObject(d));
			}
		}
		return players;
	}

	private Document toDBObject(Player player) {
		Document d = new Document();
		d.put("position", player.getPosition());
		d.put("id", player.getId());
		d.put("weight", player.getWeight());
		d.put("height", player.getHeight());
		d.put("imageUrl", player.getImageUrl());
		d.put("birthplace", player.getBirthplace());
		d.put("age", player.getAge());
		d.put("name", player.getAge());
		d.put("birthdate", player.getBirthdate());
		d.put("number", player.getNumber());
		return d;
	}

	private Player fromDBObject(Document document) {
		Player p = new Player();
		p.set_Id(document.getObjectId("_id"));
		p.setPosition(document.getString("position"));
		p.setId(document.getDouble("id"));
		p.setWeight(document.getDouble("weight"));
		p.setHeight(document.getString("height"));
		p.setImageUrl(document.getString("imageUrl"));
		p.setBirthplace(document.getString("birthplace"));
		p.setAge(document.getDouble("age"));
		return p;
	}

}
