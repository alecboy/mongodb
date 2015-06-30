package com.lanino.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// https://github.com/buckyroberts
// youtube thenewboston mongodb
public class MainApp {

	private MongoClient mongo;

	public MainApp() {

		List<ServerAddress> servers = new ArrayList<ServerAddress>();
		servers.add(new ServerAddress("localhost", 27017));

		MongoClientOptions.Builder builder = new MongoClientOptions.Builder();

		builder.connectionsPerHost(5);
		builder.threadsAllowedToBlockForConnectionMultiplier(10);

		builder.writeConcern(WriteConcern.MAJORITY);

		builder.readPreference(ReadPreference.secondaryPreferred());

		MongoClientOptions options = builder.build();

		mongo = new MongoClient(servers, options);
	}

	public MongoClient getClient() {
		return mongo;
	}

	public void closeClient() {
		mongo.close();
	}

	public static void main(String[] args) {

		MainApp app = new MainApp();

		MongoDatabase dataBase = app.getClient().getDatabase("hockey");

		MongoCollection<Document> players = dataBase.getCollection("players");

		MongoCollection<Document> users = dataBase.getCollection("users");

		PlayersDAO playersDao = new PlayersDAO(players);

		UsersDAO usersDao = new UsersDAO(users);

		System.out.println(playersDao.getAllPlayers().size());

		System.out.println(usersDao.getAgeLt(23));
		
		// it does not work
		System.out.println(usersDao.getEyeColorTotal());

		app.closeClient();
	}

}
