package com.koszykplugins.playtimelogger;
import com.mongodb.MongoClient;

public class PlaytimeSaver {
    public void savePlaytime(String username, String password, String host, String port, String database, String collection) {
        MongoClientURI uri = new MongoClientURI("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/" + database + "?authSource=admin");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
    }
}
