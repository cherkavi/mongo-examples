package com.cherkashyn.vitalii.education.mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

public class HomeWork2 {

	public static void main(String[] args){
		System.out.println("-begin-");

		MongoClient mongoClient=new MongoClient();
		MongoDatabase database=mongoClient.getDatabase("students");
		MongoCollection<Document> mongoCollection=database.getCollection("grades");
		FindIterable<Document> documents=mongoCollection.find(Filters.eq("type", "homework")).sort(Sorts.ascending("student_id","score"));
		MongoCursor<Document> cursor=documents.iterator();

		String lastStudentId="";
		int counter=0;
		while(cursor.hasNext()){
			Document document=cursor.next();
			if(lastStudentId.equals(Integer.toString(document.getInteger("student_id")))){
				continue;
			}
			lastStudentId=Integer.toString(document.getInteger("student_id"));
			// mongoCollection.deleteOne(document);
			counter++;
		}
		System.out.println("count:"+counter);

		mongoClient.close();
		System.out.println("--end--");
	}
}
