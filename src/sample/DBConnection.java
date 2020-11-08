package sample;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class DBConnection {

    public static MongoCollection<Document> getConnection(){

        MongoClient mongoClient = new MongoClient(
                new MongoClientURI(
                        "mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false"
                )
        );
        MongoDatabase database = mongoClient.getDatabase("gymmanagement");
        MongoCollection<Document> collection = database.getCollection("memberdetails");

//        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
//        mongoLogger.setLevel(Level.OFF);


        return collection;


    }

}
