package com.cherkashyn.vitalii.education.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class HomeWork3 {

	private static MongoClient mongoClient=new MongoClient();

	public static void main(String[] args){
		System.out.println("-begin-");


		MongoCollection<Document> mongoCollection=HomeWork3.getMongoCollection("school", "students");

		List<Document> result=new ArrayList<Document>();
		// FindIterable<Document> documents=mongoCollection.find(Filters.and(Filters.exists("scores"), Filters.in("scores.type", "homework")));
		mongoCollection.find(Filters.and(Filters.exists("scores"), Filters.in("scores.type", "homework"))).into(result);


		//		MongoCursor<Document> iterator=documents.iterator();
		//		int counter=0;
		//		while(iterator.hasNext()){
		//			Document eachDocument=iterator.next();
		//			HomeWork3.removeLowestHomework(eachDocument);
		//			// re-comment next line, in case, if you want to replace documents
		//			// mongoCollection.replaceOne(new Document("_id", eachDocument.getInteger("_id")), eachDocument);
		//			counter++;
		//		}
		// 		System.out.println(" Records count: "+counter);
		System.out.println(result.get(0));
		System.out.println(result.size());

		HomeWork3.closeMongoConnection();
		System.out.println("--end--");
	}


	private static void closeMongoConnection(){
		HomeWork3.mongoClient.close();
	}

	private static MongoCollection<Document> getMongoCollection(String dbName, String collectionName){
		HomeWork3.mongoClient=new MongoClient();
		ThreadLocal<MongoClient> threadLocal=new ThreadLocal<>();
		threadLocal.set(HomeWork3.mongoClient);
		MongoDatabase database=HomeWork3.mongoClient.getDatabase(dbName);
		MongoCollection<Document> mongoCollection=database.getCollection(collectionName);
		return mongoCollection;
	}

}
