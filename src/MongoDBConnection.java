import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    static MongoClient client = MongoClients.create("mongodb://localhost:27017");
    static MongoDatabase database = client.getDatabase("MusicFestivalDB");
}