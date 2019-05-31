package com.cherkashyn.vitalii.education.mongo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.AbstractRoute;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class ExampleSparkFreemarker {

	/** execute main logic */
	public static void main(String[] args) throws IOException, TemplateException {
		MongoClient mongoClient=new MongoClient();
		MongoDatabase database=mongoClient.getDatabase("m101");
		MongoCollection<Document> mongoCollection=database.getCollection("funnynumbers");
		FindIterable<Document> documents=mongoCollection.find(Filters.gt("value", 10)).projection(Projections.excludeId()).sort(Sorts.ascending("value"));
		MongoCursor<Document> cursor=documents.iterator();
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
		Spark.get(new Route("/echo"){
			private final static String PARAM_NAME="message";
			@Override
			public Object handle(Request request, Response response) {
				String messageParameter=request.queryParams(PARAM_NAME);
				if(messageParameter==null){
					AbstractRoute.halt(500, "necessary parameter is not found: "+PARAM_NAME);
				}
				Map<String, String> dataSource=new HashMap<String, String>();
				dataSource.put("message", messageParameter);

				try {
					StringWriter writer=new StringWriter();
					ExampleSparkFreemarker.getTemplateForRequest(ExampleSparkFreemarker.configureFreemarker())
					.process(dataSource, writer);
					return writer.toString();
				} catch (TemplateException e) {
					AbstractRoute.halt(501, "template was not found");
					return null;
				} catch (IOException e) {
					AbstractRoute.halt(501, "output exception");
					return null;
				}
			};
		});
	}

	private final static String TEMPLATE_ECHO="echo.tml";

	private static Template getTemplateForRequest(Configuration configuration) throws IOException {
		return configuration.getTemplate(ExampleSparkFreemarker.TEMPLATE_ECHO);
	}

	/** create Spark configuration */
	private static Configuration configureFreemarker(){
		Configuration configuration=new Configuration();
		configuration.setClassForTemplateLoading(ExampleSparkFreemarker.class,"/");
		return configuration;
	}

}
