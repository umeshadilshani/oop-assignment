package inventoryControlSystem;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DbConnection {
    public static MongoDatabase connect() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("inventoryDb");

        return database;
    }
}
