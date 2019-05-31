package course;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class PhotoAlbum {
	
	public static void main(String[] args){
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));

        MongoDatabase db = mongoClient.getDatabase("test");
        MongoCollection<Document> animals = db.getCollection("animals");

       Document animal = new Document("animal", "monkey");

        animals.insertOne(animal);
        System.out.println(animal);
        animal.remove("animal");
        animal.append("animal", "cat");
        animals.insertOne(animal);
        animal.remove("animal");
        animal.append("animal", "lion");
        animals.insertOne(animal);
        
        mongoClient.close();
	}
	
}
